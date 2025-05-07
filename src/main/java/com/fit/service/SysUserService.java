package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.SysRightDAO;
import com.fit.dao.SysRoleDAO;
import com.fit.dao.SysUserDAO;
import com.fit.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserService {

    @Autowired
    private SysUserDAO userDao;
    @Autowired
    private SysRoleDAO roleDao;

    // 查询所有用户信息
    public PageResult findAll(Map paramMap) {
        return userDao.findAll(paramMap);
    }

    // 查询出所有角色名称
    public PageResult findAllRoleName() {
        return roleDao.pageAll();
    }

    // 根据编号查询用户信息
    public boolean findById(Long usrId) {
        if (userDao.findById(usrId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 添加用户信息
    public void add(SysUser sysUser) {
        userDao.save(sysUser);
    }

    // 返回一个SysUser的对象
    public SysUser findByUsrId(Long usrId) {
        return userDao.findById(usrId);
    }

    // 修改用户信息
    public void update(SysUser sysUser) {
        userDao.merge(sysUser);
    }

    // 删除用户信息
    public void del(SysUser sysUser) {
        userDao.delete(sysUser);
    }
}