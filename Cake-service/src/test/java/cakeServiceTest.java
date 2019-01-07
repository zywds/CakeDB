import com.zhangyuwei.cake.service.IcakeService;
import com.zhangyuwei.cake.service.cakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class cakeServiceTest {
    /*@Autowired
    IcakeService service;*/
    //查询所有管理员
    @Test
    public void selectAllRegsistAdmin(){
        //System.out.println(service.selectAllRegsistAdmin());
    }
}
