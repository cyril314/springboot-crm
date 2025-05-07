package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.SysRightDAO;
import com.fit.dao.SysRoleDAO;
import com.fit.dao.SysRoleRightDAO;
import com.fit.entity.SysRight;
import com.fit.entity.SysRole;
import com.fit.entity.SysRoleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleService {

    @Autowired
    private SysRoleDAO roleDao;
    @Autowired
    private SysRightDAO rightDao;
    @Autowired
    private SysRoleRightDAO srrDao;

    // 创建角色信息
    public void add(SysRole sysRole) {
        roleDao.save(sysRole);
    }

    // 修改角色信息
    public void update(SysRole sysRole) {
        roleDao.merge(sysRole);
    }

    // 找出所有的权限
    public List<SysRight> findAllRight() {
        return rightDao.findAll();
    }

    // 根据角色找出它有或没有的权限
    public Map<String, Object> findRightByRoleId(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        List<SysRoleRight> roleRights = this.srrDao.findAllForMap(map);
        map.clear();
        List<SysRight> hasRight = new ArrayList<>();
        List<SysRight> rights = rightDao.findAll();
        map.put("allRight", rights);
        for (SysRoleRight roleRight : roleRights) {
            SysRight right = roleRight.getSysRight();
            if (rights.contains(right)) {
                hasRight.add(right);
                rights.remove(right);
            }
        }
        map.put("hasRight", hasRight);
        map.put("hasNotRight", rights);
        return map;
    }

    // 判断该角色的权限是否存在
    public boolean findRightExist(Long roleId, String rightCode) {
        boolean exist = false;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("rolesId", rightCode);
        if (srrDao.findAllForMap(map).size() > 0) {
            exist = true;
        }
        return exist;
    }

    // 根据角色编号找出SysRoleRight中的主键
    public List<SysRoleRight> findIdByRoleId(Long roleId) {
        return srrDao.findByProperty("sysRole.roleId", roleId);
    }

    // 删除该角色的所有权限
    public void deleteRight(SysRoleRight srr) {
        srrDao.delete(srr);
    }

    // 保存权限
    public void updateRight(SysRoleRight srr) {
        srrDao.save(srr);
    }

    // 查询角色信息
    public PageResult findAllRole(Map paramMap) {
        return roleDao.findAll(paramMap);
    }

    public SysRole findByRoleId(Long roleId) {
        return roleDao.findById(roleId);
    }

    // 删除角色信息
    public void del(SysRole sysRole) {
        roleDao.delete(sysRole);
    }

    // 查询所有权限名称
    public List<SysRight> findAllRightName() {
        return rightDao.findAll();
    }
}