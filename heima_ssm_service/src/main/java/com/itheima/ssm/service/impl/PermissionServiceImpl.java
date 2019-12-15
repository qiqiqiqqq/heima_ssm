package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {


    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public void  save(Permission permission) throws  Exception{
        iPermissionDao.save(permission);
    }
    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }
}
