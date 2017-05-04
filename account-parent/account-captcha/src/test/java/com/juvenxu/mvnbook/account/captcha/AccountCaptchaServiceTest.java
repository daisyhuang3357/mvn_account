package com.juvenxu.mvnbook.account.captcha;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountCaptchaServiceTest {
	//声明一个被测服务对象
	private AccountCaptchaService service;
	//每一个测试用例在运行之前都需要初始化AccountCaptchaService这个bean，即实例化一个被测对象
    @BeforeMethod
    //测试准备
    public void prepare() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "account-captcha.xml" );
        service = (AccountCaptchaService) ctx.getBean( "accountCaptchaService" );
    }

    //测试验证码图片的生成
    @Test
    public void testGenerateCaptcha() throws Exception {
    	//初始化一个验证码主键
        String captchaKey = service.generateCaptchaKey();
        //检查主键是否为空
        Assert.assertNotNull(captchaKey);
        //获取验证码图片
        byte[] captchaImage = service.generateCaptchaImage( captchaKey );
        //检查获取内容是否为空
        Assert.assertTrue(captchaImage.length > 0);
        //创建并存放验证码图片内容到新建jpg文件中
        File image = new File( "target/" + captchaKey + ".jpg" );
        OutputStream output = null;
        try {
            output = new FileOutputStream( image );
            output.write( captchaImage );
        } finally {
            if ( output != null ) {
                output.close();
            }
        }
        //检查文件是否存在
        Assert.assertTrue(image.exists() && image.length() > 0);
    }
    
    //测试一个正确的Captcha验证流程
    @Test
    public void testValidateCaptchaCorrect() throws Exception {
    	//初始化List存放定设定值
        List<String> preDefinedTexts = new ArrayList<String>();
        preDefinedTexts.add( "12345" );
        preDefinedTexts.add( "abcde" );
        service.setPreDefinedTexts( preDefinedTexts );
        //生成验证码主键
        String captchaKey = service.generateCaptchaKey();
        //生成验证码图片
        service.generateCaptchaImage( captchaKey );
        //检验主键和图片是否对应
        Assert.assertTrue( service.validateCaptcha( captchaKey, "12345" ) );
        //另一组测试数据
        captchaKey = service.generateCaptchaKey();
        service.generateCaptchaImage( captchaKey );
        Assert.assertTrue( service.validateCaptcha( captchaKey, "abcde" ) );
    }
    
    //测试当用户反馈Captcha值错误时的场景
    @Test
    public void testValidateCaptchaIncorrect() throws Exception {
    	//初始化List存放定设定值
        List<String> preDefinedTexts = new ArrayList<String>();
        preDefinedTexts.add( "12345" );
        service.setPreDefinedTexts( preDefinedTexts );
        //生成验证码主键
        String captchaKey = service.generateCaptchaKey();
        //生成验证码图片
        service.generateCaptchaImage( captchaKey );
        //检验主键和图片是否对应(使用错误值)
        Assert.assertFalse( service.validateCaptcha( captchaKey, "67890" ) );
    }
}
