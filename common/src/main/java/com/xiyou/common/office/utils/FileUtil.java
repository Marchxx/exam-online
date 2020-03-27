package com.xiyou.common.office.utils;

import com.xiyou.common.enums.CodeEnum;
import com.xiyou.common.exception.CustomException;
import com.xiyou.common.office.pojo.CompressFile;
import com.xiyou.common.utils.RandomUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: multi-module
 * @description: 文件操作工具
 * @author: tangcan
 * @create: 2019-07-09 14:56
 **/
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    /*
    生成随机文件名，不压缩
     */
    public static String upload(MultipartFile file, String parentPath) {
        String realFileName = RandomUtil.createFileName();
        return upload(file, parentPath, realFileName, false);
    }

    /*
    生成随机文件名，可选择是否压缩
     */
    public static String upload(MultipartFile file, String parentPath, boolean compress) {
        String realFileName = RandomUtil.createFileName();
        return upload(file, parentPath, realFileName, compress);
    }

    /*
    指定文件名，不压缩
     */
    public static String upload(MultipartFile file, String parentPath, String fileName) {
        return upload(file, parentPath, fileName, false);
    }

    /*
    指定文件名，可选择是否压缩
     */
    public static String upload(MultipartFile file, String savePath, String fileName, boolean compress) {
        if (file == null || fileName == null) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String realFileName;
        if (StringUtils.isNotBlank(fileName)) {
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            realFileName = fileName + suffixName;
        } else {
            realFileName = originalFilename;
        }
        String picPath = savePath + realFileName;
        try {
            File dest = new File(picPath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            throw new CustomException(CodeEnum.FILE_UPLOAD_FAIL.getCode(), e.getMessage());
        }
        if (compress) {
            compressPic(picPath);
        }
        return realFileName;
    }

    /**
     * @Author: tangcan
     * @Description: 文件批量上传，返回文件名称，用逗号隔开
     * @Param: [files, savePath]
     * @date: 2019/9/10
     */
    public static List<String> uploadFiles(MultipartFile[] files, String savePath) {
        // 检查参数合法性
        if (ArrayUtils.isEmpty(files) || StringUtils.isBlank(savePath)) {
            return null;
        }
        List<String> fileNameList = new ArrayList<>();
        // 单独处理每个文件
        for (MultipartFile file : files) {
            // 文件原始名
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            // 取出文件后缀
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 文件重命名
            String fileName = RandomUtil.createFileName() + suffixName;
            // 文件存放路径+文件名称
            String filePath = savePath + fileName;
            try {
                File dest = new File(filePath);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            // 使用逗号隔开
            fileNameList.add(fileName);
        }
        return fileNameList;
    }

    /*
    导出任意文件格式
     */
    public static void exportFile(HttpServletResponse response, String filePath, String exportName) throws IOException {
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(exportName, "utf-8"));
        export(response, filePath);
    }

    /*
    导出图片
     */
    public static void exportPic(HttpServletResponse response, String picPath) throws IOException {
        response.setContentType("image/jpeg");
        export(response, picPath);
    }

    /*
    导出视频
     */
    public static void exportVideo(HttpServletResponse response, String videoPath, String exportName) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(exportName, "utf-8"));
        export(response, videoPath);
    }

    /*
    文件导出具体实现
     */
    private static void export(HttpServletResponse response, String filePath) throws IOException {
        File file = new File(filePath);
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        /*
          一次仅传输1K，不会溢出
         */
        int buf_size = 1024;
        byte[] buffer = new byte[buf_size];
        int len;
        while (-1 != (len = is.read(buffer, 0, buf_size))) {
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close();
    }

    /*
     * 压缩图片
     */
    private static void compressPic(String picPath) {
        final int FILE_SIZE_LIMIT = 500;
        if (StringUtils.isBlank(picPath)) {
            return;
        }
        File file = new File(picPath);
        int size = (int) ((file.length() + 0.5) / 1024);
        log.info("压缩前大小：" + size);
        if (size < FILE_SIZE_LIMIT) {
            return;
        }
        try {
            while (size > FILE_SIZE_LIMIT) {
                double accuracy = getAccuracy(size);
                Thumbnails.of(picPath).scale(accuracy).outputQuality(accuracy).toFile(picPath);
                size = (int) ((file.length() + 0.5) / 1024);
            }
        } catch (Exception ignored) {
        }
        log.info("压缩后大小：" + size);
    }

    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }

    /*
    文件压缩并下载
    由于传入的文件不能进行中文命名，因此单独使用fileNameList对应每个文件的导出名称
     */
    public static void compressAndDownload(HttpServletResponse response, List<CompressFile> compressFiles, String zipName) throws IOException {
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(zipName, "utf-8"));
        byte[] buffer = new byte[1024];
        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            for (CompressFile compressFile : compressFiles) {
                FileInputStream fis = new FileInputStream(compressFile.getFile());
                zos.putNextEntry(new ZipEntry(compressFile.getOrgFileName()));
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, len);
                }
                zos.flush();
                zos.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
