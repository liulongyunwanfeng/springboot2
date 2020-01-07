package com.django.springboot2.service.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.django.springboot2.dao.mapper.OrgnizationMapper;
import com.django.springboot2.pojo.domain.Organization;
import com.django.springboot2.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liulongyun
 * @create 2019/6/4 21:53
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrgnizationMapper organizationMapper = null;

    @Override
    @Transactional
    public void add(Organization org) {
        organizationMapper.insert(org);
    }

    @Override
    @Transactional
    public void delete(BigDecimal id) {
        organizationMapper.deleteById(id);

    }

    @Override
    @Transactional
    public void update(Organization org) {
        organizationMapper.updateById(org);

    }

    @Override
    public Organization getById(BigDecimal id) {
        return organizationMapper.selectById(id);
    }

    @Override
    public List<Organization> getByWhere(Organization org) {

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("org_name",org.getOrgName());
        return organizationMapper.selectByMap(queryMap);
    }


    public IPage<Organization> getPageByWhere(Organization organization){
        Page<Organization> page = new Page<>();
        page.setSize(10l);
        page.setCurrent(1l);
        return organizationMapper.queryPageByWhere(page,organization);
    }


}
