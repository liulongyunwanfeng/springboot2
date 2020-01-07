package com.django.springboot2.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.django.springboot2.pojo.domain.Organization;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liulongyun
 * @create 2019/6/4 21:30
 **/
@Repository
public interface OrgnizationMapper extends BaseMapper<Organization> {
    /**
     * <p>
     * 查询 : 根据字段匹配查询组织列表，分页显示
     *
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,
     *             传递参数 Page 即自动分页,必须放在第一位
     *             (你可以继承Page实现自己的分页对象)
     * @param organization 查询map
     * @return 分页对象
     */
    IPage<Organization> queryPageByWhere(Page page, @Param("org") Organization organization);


}
