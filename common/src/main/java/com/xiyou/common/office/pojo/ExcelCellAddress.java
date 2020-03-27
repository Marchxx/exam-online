package com.xiyou.common.office.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * @program: multi-module
 * @description: excel单元格位置
 * @author: tangcan
 * @create: 2019-07-04 13:13
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExcelCellAddress {
    // 行
    private int row;
    // 列
    private int col;
    // 编号
    private int num;
    // 单元格的值
    private String value;

    public void setValue(String value) {
        if (isENum(value)) {
            // 解决数字变成科学计数的问题
            BigDecimal db = new BigDecimal(value);
            this.value = db.toPlainString();
        } else {
            int length = value.length();
            // 如果是.0结束的小数，转换成整数
            if (isNum(value) && value.charAt(length - 2) == '.' && value.charAt(length - 1) == '0') {
                this.value = value.substring(0, length - 2);
            } else {
                this.value = value;
            }
        }
    }

    /**
     * @Author: tangcan
     * @Description: 判断输入字符串是否为科学计数法
     * @Param: [value]
     * @date: 2019/7/4
     */
    private boolean isENum(String value) {
        if ("".equals(value) || value == null) {
            return false;
        }
        // 科学计数法正则表达式
        return value.matches("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");
    }

    private boolean isNum(String value) {
        return value.matches("-?[0-9]+.*[0-9]*");
    }
}
