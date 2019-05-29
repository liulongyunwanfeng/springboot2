package com.django.springboot2.dao.typehandler;

import com.django.springboot2.Enum.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liulongyun
 * @create 2019/5/29 19:58
 * @Desc mybatis 性别枚举类的typeHandler
 *
 **/
@MappedJdbcTypes(JdbcType.INTEGER) //数据库数据类型
@MappedTypes(value = SexEnum.class) // 映射到java数据的类型
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index,
                                    SexEnum sexEnum, JdbcType jdbcType) throws SQLException {

        // 参数设置
        preparedStatement.setInt(index,sexEnum.getId());


    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String columName) throws SQLException {
        int sex = resultSet.getInt(columName);
        if(sex!=SexEnum.MALE.getId()&&sex!=SexEnum.FEMALE.getId()){
            return null;
        }
        return SexEnum.getEnumById(sex);

    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int index) throws SQLException {
        int sex = resultSet.getInt(index);
        if(sex!=SexEnum.MALE.getId()&&sex!=SexEnum.FEMALE.getId()){
            return null;
        }
        return SexEnum.getEnumById(sex);

    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
        int sex = callableStatement.getInt(index);
        if(sex!=SexEnum.MALE.getId()&&sex!=SexEnum.FEMALE.getId()){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
}
