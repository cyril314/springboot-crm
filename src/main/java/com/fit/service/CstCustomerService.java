package com.fit.service;

import com.fit.base.PageResult;
import com.fit.dao.CstCustomerDAO;
import com.fit.entity.CstCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CstCustomerService {

    @Autowired
    private CstCustomerDAO custDao;

    /*
     * 客户信息管理 @method findAll,findById,findByCustName,add,update,del
     */
    // 查询客户信息
    public PageResult findAll(Map paramMap) {
        return custDao.findAll(paramMap);
    }

    //判断客户是否存在
    public boolean findById(Long custNo) {
        if (custDao.findById(custNo) == null) {
            return false;
        } else {
            return true;
        }
    }

    public CstCustomer findByCustNo(Long custNo) {
        return custDao.findById(custNo);
    }

    // 根据客户名称查询客户信息
    public List<CstCustomer> findByCustName(String svrCustName) {
        return custDao.findByProperty("custName", svrCustName);
    }

    // 新增客户信息
    public void add(CstCustomer customer) {
        custDao.save(customer);
    }

    // 修改客户信息
    public void update(CstCustomer customer) {
        custDao.merge(customer);
    }

    // 删除客户信息
    public void del(CstCustomer customer) {
        custDao.delete(customer);
    }
}
