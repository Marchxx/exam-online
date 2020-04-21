package com.march.common.office;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    public List<Excel> getAllUser() {
        List<Excel> excelList = new ArrayList<>();
        for (int i=0;i<100;i++){
            Excel excel=new Excel("张三","123456",i);
            excelList.add(excel);
        }
        return excelList;
    }

}
