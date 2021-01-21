import com.imooc.spring.ioc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//将Junit4的执行权交由Spring Test,在测试用例执行前自动初始化IoC容器
@RunWith(SpringJUnit4ClassRunner.class)
//用于说明加载哪个配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTestor {
    @Resource
    // 在这里注入我们需要的属性
    private UserService userService;

    @Test
    public void testUserService(){
        userService.createUser();
    }
}
