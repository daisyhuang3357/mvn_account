package com.juvenxu.mvnbook.account.email;

import javax.mail.Message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountEmailServiceTest
{
//    private GreenMail greenMail;
//
//    @BeforeMethod
//    public void startMailServer() throws Exception {
//        greenMail = new GreenMail( ServerSetup.SMTP );
//        greenMail.setUser( "test@daisy.com", "123456" );
//        greenMail.start();
//    }
//
//    @Test
//    public void testSendMail() throws Exception {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext( "account-email.xml" );
//        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean( "accountEmailService" );
//        String subject = "Test Subject";
//        String htmlText = "<h3>Test</h3>";
//        accountEmailService.sendMail( "test2@daisy.com", subject, htmlText );
//        greenMail.waitForIncomingEmail( 2000, 1 );
//        Message[] msgs = greenMail.getReceivedMessages();
//        Assert.assertEquals( 1, msgs.length );
//        Assert.assertEquals( "admin@daisy.com", msgs[0].getFrom()[0].toString() );
//        Assert.assertEquals( subject, msgs[0].getSubject() );
//        Assert.assertEquals( htmlText, GreenMailUtil.getBody( msgs[0] ).trim() );
//    }
//
//    @AfterMethod
//    public void stopMailServer() throws Exception {
//        greenMail.stop();
//    }
}
