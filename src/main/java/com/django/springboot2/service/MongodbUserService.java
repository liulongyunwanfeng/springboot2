package com.django.springboot2.service;

import com.django.springboot2.pojo.domain.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;


import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 17:00
 **/
public interface MongodbUserService {

    public void saveUser(User user);
    public List<User> findUser(String userName,String note,int skip,int limit);
    public DeleteResult deleteUser(Long id);
    public UpdateResult updateUser(Long id, String userName, String note);
    public User getUser(Long id);
}
