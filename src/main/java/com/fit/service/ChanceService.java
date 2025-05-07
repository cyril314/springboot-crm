package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.SalChanceDAO;
import com.fit.entity.SalChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/5/7
 */
@Service
public class ChanceService {

    @Autowired
    private SalChanceDAO chanceDao;

    // 查询所有销售机会信息
    public PageResult findAll(Map paramMap) {
        return chanceDao.findAll(paramMap);
    }

    // 根据编号查询营销机会信息
    public boolean findById(Long chcId) {
        if (chanceDao.findById(chcId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 返回一个SalChance的对象
    public SalChance findByChcId(Long chcId) {
        return chanceDao.findById(chcId);
    }

    // 新增销售机会信息
    public void add(SalChance chance) {
        chanceDao.save(chance);
    }

    // 修改销售机会信息
    public void update(SalChance chance) {
        chanceDao.merge(chance);
    }

    // 删除营销机会信息
    public void del(SalChance salChance) {
        chanceDao.delete(salChance);
    }
}