import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dingcheng.confirms.publish.DeadLetterPublishService;  
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:application-context.xml"})  
public class TestDeadLetter {  
    @Autowired  
    private DeadLetterPublishService publishService;  
    
    @Test  
    public void test1() throws InterruptedException{  
    	String message = "currentTime:"+System.currentTimeMillis();
    	System.out.println("test1---message:"+message);
    	publishService.send("dead-letter-queue",message);  
        Thread.sleep(10000);
    }

    @Test
    public void testALL() throws InterruptedException{
        String message = "currentTime:" + System.currentTimeMillis();
        System.out.println("test1---message: "+ message);
        publishService.send("common-exchange","common-queue_5s", message);
        publishService.send("common-exchange","common-queue_15s", message);
        publishService.send("common-exchange","common-queue_30s", message);
        publishService.send("common-exchange","common-queue_45s", message);
        publishService.send("common-exchange","common-queue_50s", message);
        Thread.sleep(100000);
    }

}  