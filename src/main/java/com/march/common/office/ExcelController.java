package com.march.common.office;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.march.common.enums.CodeEnum;
import com.march.common.office.pojo.QuestionExcel;
import com.march.common.utils.R;
import com.march.main.biz.ImportBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@Api(tags = "Excel导入")
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ImportBiz importBiz;

    @ApiOperation(value = "通过Excel导入试题题库")
    @PostMapping("/importQuestion")
    public R importExcel(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        // 数据处理
        //importParams.setHeadRows(1);
        //importParams.setTitleRows(1);
        // 需要验证
        importParams.setNeedVerfiy(false);
        try {
            ExcelImportResult<QuestionExcel> result = ExcelImportUtil.importExcelMore(file.getInputStream(), QuestionExcel.class, importParams);
            List<QuestionExcel> resultList = result.getList();
            int typeId = 0;
            for (QuestionExcel q : resultList) {
                typeId = q.getQuestionTypeId();
                //按照题型类别，12处理单、多选题；34处理判断、填空题
                if (typeId == 1 || typeId == 2)
                    importBiz.importOpts(q);
                if (typeId == 3 || typeId == 4)
                    importBiz.importOthers(q);
            }
            System.out.println("从Excel导入数据一共" + resultList.size() + "行");
            return R.success("成功导入" + resultList.size() + "行数据");
        } catch (IOException e) {
            System.out.println("导入失败：{}" + e.getMessage());
            return R.error(CodeEnum.OTHER_ERROR);
        } catch (Exception e1) {
            System.out.println("导入失败：{}" + e1.getMessage());
        }
        return R.error(CodeEnum.OTHER_ERROR);
    }
}
