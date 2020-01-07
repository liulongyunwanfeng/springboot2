package com.django.springboot2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.django.springboot2.pojo.domain.Organization;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 21:50
 **/
public interface OrganizationService {

    public void add(Organization org);

    public void delete(BigDecimal id);

    public void update(Organization org);

    public Organization getById(BigDecimal id);

    public List<Organization> getByWhere(Organization org);

    public IPage<Organization> getPageByWhere(Organization organization);

}
