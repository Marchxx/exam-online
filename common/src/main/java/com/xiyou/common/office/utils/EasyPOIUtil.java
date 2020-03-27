package com.xiyou.common.office.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.xiyou.common.office.pojo.ExcelCellAddress;
import com.xiyou.common.office.pojo.ImportExcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @program: multi-module
 * @description: excel操作
 * @author: tangcan
 * @create: 2019-06-26 10:04
 **/
public class EasyPOIUtil {

    private final static String EXCEL_CONTENT_TYPE = "application/vnd.ms-office;charset=UTF-8";

    /**
     * @Author: tangcan
     * @Description: excel上传:需要验证（不适合大量数据导入）
     * @Param: [file, pojoClass]
     * @date: 2019/1/23
     */
    public static <T> ExcelImportResult<T> importAndVerfiy(MultipartFile file, Class<T> pojoClass) throws Exception {
        return importAndVerfiy(file, -1, -1, pojoClass);
    }

    public static <T> ExcelImportResult<T> importAndVerfiy(MultipartFile file, int titleRows, int headRows, Class<T> pojoClass) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);
        if (headRows >= 0) {
            importParams.setHeadRows(headRows);
        }
        if (titleRows >= 0) {
            importParams.setTitleRows(titleRows);
        }
        return ExcelImportUtil.importExcelMore(file.getInputStream(), pojoClass, importParams);
    }

    public static List<String> getFailMsgList(List<? extends ImportExcel> failList, int titleRows, int headRows) {
        // 取前十条错误提示信息返回
        List<String> faileMsgList = new ArrayList<>();
        int num = 10;
        for (ImportExcel importExcel : failList) {
            if (num <= 0) {
                // 表示还有更多
                faileMsgList.add("...");
                break;
            }
            num--;
            // rowNum是从第一条数据开始计算，不包括标题和头部
            faileMsgList.add(importExcel.getErrorInfo(titleRows, headRows));
        }
        return faileMsgList;
    }

    /**
     * @Author: tangcan
     * @Description: 根据单元格的位置获取对应单元格的值
     * @Param: [file, sheetNum, cellAddressList]
     * @date: 2019/7/4
     */
    public static List<ExcelCellAddress> getCellValueByAddress(MultipartFile file, String sheetName, List<ExcelCellAddress> excelCellAddressList) {
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        assert workbook != null;
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return null;
        }
        for (ExcelCellAddress cellAddress : excelCellAddressList) {
            // 该方法会先判断是否是合并单元格
            cellAddress.setValue(MyPoiCellUtil.getCellValue(sheet, cellAddress.getRow(), cellAddress.getCol()));
        }
        return excelCellAddressList;
    }

    public static void exportExcelByPath(HttpServletResponse response, String filePath, String fileName) throws IOException {
        response.setContentType(EXCEL_CONTENT_TYPE);
        if (fileName != null && !fileName.endsWith(".xlsx")) {
            fileName += ".xlsx";
        }
        assert fileName != null;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(filePath);
        // 创建缓冲区
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();

    }

    /**
     * @Author: tangcan
     * @Description: easypoi导出多sheet的excel模板
     * @Param: [response, fileName, templatePath, map]
     * @date: 2018/12/15
     */
    public static void exportMultiSheetExcelByMap(HttpServletResponse response,
                                                  String fileName,
                                                  String templatePath,
                                                  Map<String, Object> map,
                                                  boolean multiSheet) throws IOException {
        TemplateExportParams params = new TemplateExportParams(templatePath, multiSheet);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        if (workbook == null) {
            return;
        }
        workbookExport(response, workbook, fileName);
    }

    /**
     * @Author: tangcan
     * @Description: 导出workbook
     * @Param: [response, workbook]
     * @date: 2018/12/11
     */
    public static void saveWorkbook(Workbook workbook, String filePath, String fileName) {
        File savefile = new File(filePath);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath + fileName);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IOUtils.closeQuietly(fos);
    }

    /**
     * @Author: tangcan
     * @Description: 导出workbook
     * @Param: [response, workbook]
     * @date: 2018/12/11
     */
    public static void workbookExport(HttpServletResponse response, Workbook workbook, String fileName) throws IOException {
        response.setContentType(EXCEL_CONTENT_TYPE);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }

    public static void workbookExport(FileOutputStream fos, Workbook workbook) throws IOException {
        workbook.write(fos);
        fos.close();
    }
}
