package com.xiyou.common.validator;

import com.xiyou.common.annotations.ValidFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: multi-module
 * @description: 文件验证器
 * @author: tangcan
 * @create: 2019-06-18 14:51
 **/
public class MultipartFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private ValidFile validFile;

    private String[] file;

    @Override
    public void initialize(ValidFile constraintAnnotation) {
        this.validFile = constraintAnnotation;
        this.file = validFile.file();
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext cvc) {
        String fieldName = multipartFile.getName();
        if (multipartFile.isEmpty()) {
            setMessage("上传文件不能为空,参数名：" + fieldName, cvc);
            return false;
        }

        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null) {
            setMessage("文件上传错误,参数名：" + fieldName, cvc);
            return false;
        }
        if (validFile.ignoreCase()) {
            fileName = fileName.toLowerCase();
        }

        // 文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
        boolean correct = false;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < file.length; i++) {
            if (file[i].equals(suffixName)) {
                correct = true;
                break;
            }
            if (i == 0) {
                buffer.append(file[i]);
            } else {
                buffer.append(",").append(file[i]);
            }
        }
        if (!correct) {
            setMessage("只能上传" + buffer.toString() + "格式的文件", cvc);
            return false;
        }

        return true;
    }

    /**
     * 验证错误信息提示
     */
    private void setMessage(String message, ConstraintValidatorContext cvc) {
        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

}
