package com.xiyou.common.office.pojo;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

/**
 * @program: multi-module
 * @description: excel导入校验错误信息
 * @author: tangcan
 * @create: 2019-06-29 17:16
 **/
public class ImportExcel extends ExcelVerifyHandlerResult implements IExcelModel, IExcelDataModel {
    // 校验出错行（不包括标题和头部）
    private int rowNum;

    // 校验出错信息
    private String errorMsg;

    /**
     * @Author: tangcan
     * @Description: 获取错误信息
     * @Param: [titleRows, headRows]
     * @date: 2019/6/29
     */
    public String getErrorInfo(int titleRows, int headRows) {
        return "第 " + (titleRows + headRows + rowNum) + " 行的数据：" + errorMsg;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String s) {
        this.errorMsg = s;
    }

    @Override
    public int getRowNum() {
        return rowNum;
    }

    @Override
    public void setRowNum(int i) {
        rowNum = i;
    }
}
