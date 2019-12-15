package com.itheima.ssm.service.impl;


import com.itheima.ssm.domain.Product1;
import com.itheima.ssm.service.IProductService;
import com.itheima.ssm.dao.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional//这个注解的类或者方法表示该类里面的所有方法或者这个方法的事务由
// spring处理，来保证事务的原子性，即是方法里面对数据库操作，如果失败则spring负责回滚操作，成功则提交操作。
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public void save(Product1 product) throws Exception {
        productDao.save(product);
    }

    @Override
    public List<Product1> findAll() throws Exception {
        return productDao.findAll();
    }
}
