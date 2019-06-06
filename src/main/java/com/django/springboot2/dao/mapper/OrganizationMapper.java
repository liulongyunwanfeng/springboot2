package com.django.springboot2.dao.mapper;

import com.django.springboot2.pojo.domain.Organization;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 21:30
 **/
@Repository
public interface OrganizationMapper {

    public void insert(Organization org);

    public void delete(BigDecimal id);

    public void update(Organization org);

    public Organization queryById(BigDecimal id);

    public List<Organization> queryByWhere(Organization org);


}
