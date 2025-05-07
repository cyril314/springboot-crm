package com.fit.web;

import com.fit.base.BaseController;
import com.fit.entity.SysUser;
import com.fit.service.LoginService;
import com.fit.service.SysRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/5/6
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysRightService rightService;

    @ResponseBody
    @PostMapping({"/login", "/login.do"})
    public Object login(HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = getRequestParamsMap(request);
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        map.put("success", false);
        try {
            List<SysUser> users = loginService.validUser(userName);
            if (users.isEmpty()) {
                map.put("msg", "此用户不存在");
            } else {
                for (int i = 0; i < users.size(); i++) {
                    if (password.equals(users.get(i).getUsrPassword())) {
                        map.put("success", true);
                        map.put("msg", "登录成功");
                        Long userId = users.get(i).getUsrId();
                        Long roleId = users.get(i).getSysRole().getRoleId();
                        // 把用户的名称、角色名称还有编号放入到session中
                        session.setAttribute("userId", userId);
                        session.setAttribute("userName", userName);
                        session.setAttribute("roleId", roleId);
                        // 用户成功登录之后把他的权限(即可以访问的地址)放入到Session中
                        String[] rightUrl = rightService.findRight(users.get(i).getSysRole().getRoleId());
                        session.setAttribute("rightUrl", rightUrl);
                        for (int j = 0; j < rightUrl.length; j++) {
                            System.out.println("该用户可以访问的地址有：" + rightUrl[j].toString());
                        }
                    } else {
                        map.put("msg", "登录失败,密码不正确");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "系统异常,登录失败");
        }
        return map;
    }
}
