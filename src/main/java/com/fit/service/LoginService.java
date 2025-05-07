package com.fit.service;

import java.util.List;

import com.fit.dao.SysUserDAO;
import com.fit.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SysUserDAO sysUserDao;

    // 验证用户
    public List<SysUser> validUser(String userName) {
        return sysUserDao.findByProperty("usrName", userName);
    }

    // 修改用户密码
    public void updatePwd(SysUser user) {
        sysUserDao.merge(user);
    }

    public SysUser findById(Long userId) {
        return sysUserDao.findById(userId);
    }
}
