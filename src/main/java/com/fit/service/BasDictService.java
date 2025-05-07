package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.BasDictDAO;
import com.fit.entity.BasDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasDictService {

    @Autowired
    private BasDictDAO bDao;        //数据字典数据访问层

    // 查询数据
    public PageResult findAll(Map paramMap) {
        return bDao.findAll(paramMap);
    }

    public Boolean findById(Long dictId) {//判断数据字典编号是否存在
        if (bDao.findById(dictId) == null) {
            return false;
        } else {
            return true;
        }
    }

    public BasDict findByDictId(Long dictId) {//通过数据字典编号查找
        return bDao.findById(dictId);
    }

    public void add(BasDict basdict) {// 新增数据字典信息
        bDao.save(basdict);
    }

    public void update(BasDict basdict) {// 修改数据字典信息
        bDao.merge(basdict);
    }

    public void delete(BasDict basdict) {// 删除数字典信息
        bDao.delete(basdict);
    }
}