package com.xiyou.common.office.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @program: multi-module
 * @description: 获取单元格内容
 * 由于easypoi的PoiCellUtil的获取单元格的值会出现问题：表达式不计算结果，整数变成小数
 * @author: tangcan
 * @create: 2019-07-04 14:29
 **/
class MyPoiCellUtil {
    static String getCellValue(Sheet sheet, int row, int column) {
        if (isMergedRegion(sheet, row, column)) {
            return getMergedRegionValue(sheet, row, column);
        } else {
            Row rowData = sheet.getRow(row);
            Cell cell = rowData.getCell(column);
            return getCellValue(cell);
        }
    }

    private static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; ++i) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                Row fRow = sheet.getRow(firstRow);
                Cell fCell = fRow.getCell(firstColumn);
                return getCellValue(fCell);
            }
        }

        return null;
    }

    private static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; ++i) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return true;
            }
        }

        return false;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            try {
                return String.valueOf(cell.getNumericCellValue());
            } catch (Exception var2) {
                return cell.getCellFormula();
            }
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }
    }
}
