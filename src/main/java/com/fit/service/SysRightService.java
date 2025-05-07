package com.fit.service;

import java.util.List;
import java.util.Map;

import com.fit.base.PageResult;
import com.fit.dao.SysRightDAO;
import com.fit.dao.SysRoleRightDAO;
import com.fit.entity.SysRight;
import com.fit.entity.SysRoleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRightService {

    @Autowired
    private SysRightDAO rightDao;
    @Autowired
    private SysRoleRightDAO roleRightDao;

    public String[] findRight(Long roleId) {
        List<SysRoleRight> sysRoleRightList = roleRightDao.findByProperty("sysRole.roleId", roleId);
        String[] rightUrl = new String[sysRoleRightList.size()];
        for (int i = 0; i < rightUrl.length; i++) {
            rightUrl[i] = sysRoleRightList.get(i).getSysRight().getRightUrl();
        }
        return rightUrl;
    }

    // 查询所有的数据。。。。
    public PageResult findAll(Map paramMap) {
        return rightDao.findAll(paramMap);
    }

    // 查找ID。。。。
    public SysRight findRightId(Long rightId) {
        return rightDao.findById(rightId);
    }

    // 判断rightCode是否存在。。。
    public boolean isRightCode(Long rightCode) {
        if (rightDao.findById(rightCode) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 判断rightCode在role_right表中是否存在。。
    public boolean isRoleRight(Long rightCode) {
        if (roleRightDao.findByProperty("sysRight.rightCode", rightCode).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 添加。。。。。
    public void addRight(SysRight right) {
        rightDao.save(right);
    }

    // 修改。。。
    public void updateRight(SysRight right) {
        rightDao.merge(right);
    }

    // 删除数据。。。。
    public void delRight(SysRight right) {
        rightDao.delete(right);
    }
}
