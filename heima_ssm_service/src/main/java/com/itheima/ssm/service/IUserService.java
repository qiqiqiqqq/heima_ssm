package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

//Spring Security中进行身份验证的是AuthenticationManager接口，ProviderManager是它的一个默认实现，但它并不用来处理身份认证
// 而是委托给配置好的AuthenticationProvider，每个AuthenticationProvider会轮流检查身份认证。检查后或者返回Authentication对象或者抛出异常。
//    验证身份就是加载响应的UserDetails，看看是否和用户输入的账号、密码、权限等信息匹配。
public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll()throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId) throws  Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}
