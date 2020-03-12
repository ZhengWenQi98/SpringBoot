package cn.zwq;

import cn.zwq.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEmailApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendSimpleEmail(){
        String content = "你好，恭喜你...";
        emailService.sendSimpleMail("XXX@qq.com","祝福邮件",content);
    }

    @Test
    public void sendMimeEmail(){
        String content = "<a href='https://blog.csdn.net/'>你好，欢迎注册网站，请点击链接激活</a>";
        emailService.sendHtmlMail("XXX@163.com","激活邮件",content);
    }

    @Test
    public void sendAttachment(){
        emailService.sendAttachmentsMail("XXX@qq.com","发送附件","爱旅行","F:\\图片\\爱旅行.jpg");
    }
}