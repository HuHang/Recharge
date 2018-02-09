package com.webkit.recharge.controller;

import com.webkit.recharge.bean.Discount;
import com.webkit.recharge.bean.message.Message;
import com.webkit.recharge.bean.util.ExportExcelUtil;
import com.webkit.recharge.dao.IDiscountDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2018/1/23.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Resource
    DiscountController discountController;

    /**
     * 导出excel
     * @return
     * @throws
     */
    @RequestMapping("/export.do")
    @ResponseBody
    public String exportRecords(Integer currentPage, Integer pageSize,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Message message = discountController.query();

        // 导出参数
        String fileName = "折扣信息.xls";
        Map<String,String> exportMap=new HashMap<String,String>();
        exportMap.put("业务类型", "businessTypeStr");
        exportMap.put("折扣", "standardDiscount");
        exportMap.put("创建时间", "gmtCreate");
        exportMap.put("修改时间", "gmtModified");
        ExportExcelUtil.exportExcel(fileName, exportMap, (List<Discount>) message.getData(), response);
        return "member/grade_change_log";
    }
}
