import com.hngc.entry.User;
import com.hngc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDmeo {
    InputStream io = null;
    SqlSession sqlSession = null;
    UserMapper userMapper= null;
    @Before
    public void init(){

        try {
            io = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(io);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destory(){
        sqlSession.commit();
        sqlSession.close();
        try {
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert(){
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setNick_name("熊er老师");
        user.setPhone("18638147931");
        user.setUser_name("laoyan");
        userMapper.save(user);

    }
    @Test
    public void  testSelect(){
        List<User> list = userMapper.select();
        System.out.println(list);
    }

    @Test
    public void  testUpdate(){

        User user = new User();
        user.setId(1);
        user.setNick_name("熊二老师");
        user.setPhone("18638147931");
        user.setUser_name("laoyan");
        userMapper.update(user);
    }

    @Test
    public void  testDelete(){
        userMapper.deleteById(2);
    }
}
