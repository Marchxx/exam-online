package com.xiyou.common.office.pojo;

import com.xiyou.common.exception.CustomException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: multi-module
 * @description: 封装需要进行压缩的文件File，不能包含中文文件名，因此需要单独制定压缩后的文件名
 * @author: tangcan
 * @create: 2019-12-10 16:30
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompressFile {

    File file;
    // 文件名，为了单独给文件命名
    String orgFileName;

    public void setFile(String filePath) {
        if (isContainChinese(filePath)) {
            throw new CustomException("待压缩文件路径不能包含中文");
        }
        this.file = new File(filePath);
    }

    public void setFile(File file) {
        this.file = file;
    }

    private boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }
}
