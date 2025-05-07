package com.fit.web;

import com.fit.base.BaseController;
import com.fit.base.PageResult;
import com.fit.service.ChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/5/7
 */
@Controller
public class SaleController extends BaseController {

    @Autowired
    private ChanceService chanceService;

    // 查询销售机会信息
    @ResponseBody
    @PostMapping("/sale.do")
    public Object sale(HttpServletRequest request) {
        Map<String, Object> map = getRequestParamsMap(request);
        PageResult pgr = chanceService.findAll(map);
        return pgr;
    }
}
