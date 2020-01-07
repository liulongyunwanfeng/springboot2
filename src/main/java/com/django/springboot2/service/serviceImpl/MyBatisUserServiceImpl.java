package com.django.springboot2.service.serviceImpl;

import com.django.springboot2.dao.mapper.UserMapper;
import com.django.springboot2.pojo.domain.Role;
import com.django.springboot2.pojo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liulongyun
 * @create 2019/5/29 20:53
 **/
@Service
public class MyBatisUserServiceImpl implements ApplicationContextAware {

    @Autowired
    private UserMapper userMapper;


    private ApplicationContext applicationContext =null;

    @Cacheable(value = "redisCache",key = "'redis_user_'+#id")
    public User getUser(Long id) throws Exception{
        return  userMapper.selectById(id);
    }

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,timeout = 1)
    public int addUser(User user) throws Exception{
          return  userMapper.insert(user);
    }

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,timeout = 1)
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    public User insertUser(User user) throws Exception{
        userMapper.insert(user);
        return user;
    }




    @CachePut(value = "redisCache",key = "'redis_user_'+#id",condition = "#result != 'null'")
    public User updateUserName(Long id,String userName) throws Exception{

        try {
            // 这里调用是不走缓存的，而且在这个业务中我们也不希望走缓存
            // 不走缓存的原因是这样直接自调用不走spring的aop代理，当然springredis的缓存就失效了（spring的缓存也是通过aop实现的）
            User user = this.getUser(id);


            if(null==user){
                return  null;
            }
            user.setUserName(userName);
            userMapper.updateById(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }




    // 缓存命中率，不推荐使用缓存

    public List<User> findUsers(String name,String note){
       return userMapper.findUsers(name,note);
    }

    @Transactional
    @CacheEvict(value="redisCache",key = "'redis_user_'+#id",beforeInvocation = false)
    public int deleteUser(Long id){
        return  userMapper.deleteById(id);
    }


    @CachePut(value = "redisCache",key = "'redis_user_'+#id",condition = "#result != 'null'")
    public User queryByUserNameAndPwd(String userName,String pwd){

       return userMapper.selectByUserNameAndPwd(userName,pwd);

    }

    @CachePut(value = "redisCache",key = "'redis_user_'+#id",condition = "#result != 'null'")
    public User selectUserByUserName(String userName){

        return userMapper.selectUserByUserName(userName);

    }


    @CachePut(value = "redisCache",key = "'redis_user_'+#id",condition = "#result != 'null'")
    public List<Role> selectRoleByUserName(String userName){

        return userMapper.selectRoleByUserName(userName);

    }
































    /**
     *
     *
     *
     *
     * @param users
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUserBatch1(List<User> users) throws Exception{
        int count = 0;
        for (User user:users) {
            MyBatisUserServiceImpl myBatisUserService = applicationContext.getBean(MyBatisUserServiceImpl.class);
            count+=myBatisUserService.addUser(user);
        }

        return count;
    }

    /**
     * @param users
     * @return
     * @throws Exception
     * 这里直接在类里面调用了addUser（）方法
     * addUser()方法我们设置了propagation = Propagation.REQUIRES_NEW，说明需要开启事务来执行方法的每一次调用
     * spring事务是通过代理实现的，这样直接调用是使用了类的方法执行，没有经过spring的aop代理，自然无法实现
     * spring的aop事务代理。所有如果这个批量插入用户中如果某条数据的插入有问题，比如，数据库内现在sex字段自能取值为
     * 1或2，如果有某条记录设置了3这这条数据就插入异常。
     * 而直接this.addUser()调用的方式因为没有spring的aop代理实现事务提交，所以整个业务的事务还是
     * insertUserBatch()方法是的事务，这样就所有插入都会回滚。
     * 要在方法内部调用其他带事务的字方法，子方法要能正确使用事务的方式，需要从spring容器获取到类
     * 在用类调用，因为从容器获取到的类是通过代理的类。所有子方法也有了spring的aop事务代理。
     * 这样如果业务需求是正常执行的数据提交，出错的数据回滚的话，我们在执行这三条批量插入的时候
     * 只有出错的数据插入不成功，正确的数据会插入成功。
     * 如上insertUserBatch1() 的使用
     *
     *
     * 总结：在事务方法中调用子方法，需要注意事务的传播方式Propagation 这个类
     *
     *
     *
     */

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUserBatch(List<User> users) throws Exception{

        int count = 0;
        for (User user:users) {
           count+= this.addUser(user);
        }
        return count;
    }
    /**
     *
     * @param applicationContext
     * @throws BeansException
     * 实现生命周期方法，设置Ioc容器
     *
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
























}
