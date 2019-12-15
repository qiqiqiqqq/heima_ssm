package com.itheima.ssm.service;



import com.itheima.ssm.domain.Product1;

import java.util.List;

public interface IProductService {

    public List<Product1> findAll() throws Exception;

    void save(Product1 product) throws Exception;
}
