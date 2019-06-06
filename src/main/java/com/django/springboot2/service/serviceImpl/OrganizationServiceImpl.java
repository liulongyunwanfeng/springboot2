package com.django.springboot2.service.serviceImpl;

import com.django.springboot2.dao.mapper.OrganizationMapper;
import com.django.springboot2.pojo.domain.Organization;
import com.django.springboot2.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 21:53
 **/
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper = null;

    @Override
    @Transactional
    public void add(Organization org) {
        organizationMapper.insert(org);
    }

    @Override
    @Transactional
    public void delete(BigDecimal id) {
        organizationMapper.delete(id);

    }

    @Override
    @Transactional
    public void update(Organization org) {
        organizationMapper.update(org);

    }

    @Override
    public Organization getById(BigDecimal id) {
        return organizationMapper.queryById(id);
    }

    @Override
    public List<Organization> getByWhere(Organization org) {
        return organizationMapper.queryByWhere(org);
    }
}
