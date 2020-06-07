package me.daqiang;

import me.daqiang.dao.IUserMapper;
import me.daqiang.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MyBaitisRunner
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/1/28 6:29 下午
 * @Version 1.0
 **/
public class MyBaitisRunner {

    private InputStream in = null;
    private SqlSession sqlSession = null;
    private IUserMapper userMapper = null;

    public MyBaitisRunner() throws IOException {
        this.in = Resources.getResourceAsStream("myBatisConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        this.sqlSession = factory.openSession();
        this.userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    public void testSaveUser() {
        User user = new User();
        user.setUser_name("daqiang");
        user.setBirthday(new Date());
        user.setAge(22);
        user.setPhone("15810567786");
        System.out.println(user);
        userMapper.saveUser(user);
        System.out.println(user);
    }

    public void getAllUsers() {
        List<User> users = userMapper.findAll();
        users.forEach(user -> {
            System.out.println(user.getUser_name());
        });
    }

    public void updateUserInfo(UserConfirm action) {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            User newUser = action.valid(user);
            if (newUser != null) {
                userMapper.updateUser(newUser);
            }
        }
    }
}


@FunctionalInterface
interface UserConfirm {
    User valid(User user);
}