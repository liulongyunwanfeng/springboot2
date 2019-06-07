package com.django.springboot2.web.validator;

import com.django.springboot2.pojo.domain.Organization;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author liulongyun
 * @create 2019/6/6 20:40
 **/
public class OrgnazitionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // 该验证只支持Orgnaziton类型的验证
        if(Organization.class.equals(clazz)){
            return true;
        }
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 对象为空
        if(target == null){
            // 直接在参数处报错，这样就不能进入控制器的方法
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        Organization org = (Organization) target;
        if(org.getOrgName()==null||org.getOrgName().length()<1){
            errors.rejectValue("orgName",null,"组织名称不能为空");
        }


    }
}
