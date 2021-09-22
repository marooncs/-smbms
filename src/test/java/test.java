import com.shi.Utiles.mybatis_utils;
import com.shi.dao.user.userDao;
import com.shi.dao.user.userDaoImpl;
import com.shi.pojo.User;
import com.shi.service.user.userServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    @Test
    public void test1() {
        Map<String,Object> map = new HashMap<>();
        map.put("username", "赵");
        map.put("userRole", 3);
        int userCount = new userDaoImpl().getUserCount(map);
        System.out.println(userCount);
    }
    @Test
    public void test2() {
        List<User> userList = new userServiceImpl().getUserList(null,0,0,5);
        for (User user : userList) {
            System.out.println(user.getUserName());
        }
    }
}
