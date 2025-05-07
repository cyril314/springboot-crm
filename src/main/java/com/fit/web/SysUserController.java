package com.fit.web;

import com.fit.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/5/7
 */
@Controller
public class SysUserController extends BaseController {

    @ResponseBody
    @RequestMapping
    public Object list(HttpServletRequest request) {

        return null;
    }
}
