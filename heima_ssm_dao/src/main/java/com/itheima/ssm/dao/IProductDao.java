package com.itheima.ssm.dao;



import com.itheima.ssm.domain.Product1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    @Select("select * from product where id=#{id}")
    public  Product1 findById(String id) throws Exception;

    @Select("select * from product")
    public List<Product1> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product1 product);
}
