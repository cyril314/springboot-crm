package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.CstManagerDAO;
import com.fit.entity.CstManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CstManagerService {

    @Autowired
    private CstManagerDAO cstManagerDao;

    // 查询出所有客户经理的名称
    public PageResult findAllCstManager(Map paramMap) {
        return cstManagerDao.findAll(paramMap);
    }

    // 根据经理名称查编号
    public Long findManId(String manName) {
        Long manId = null;
        List<CstManager> cstManager = cstManagerDao.findByProperty("manName", manName);
        for (CstManager manager : cstManager) {
            manId = manager.getManId();
        }
        return manId;
    }
}