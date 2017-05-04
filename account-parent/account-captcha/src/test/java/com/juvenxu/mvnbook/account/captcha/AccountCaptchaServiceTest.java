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
	//����һ������������
	private AccountCaptchaService service;
	//ÿһ����������������֮ǰ����Ҫ��ʼ��AccountCaptchaService���bean����ʵ����һ���������
    @BeforeMethod
    //����׼��
    public void prepare() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext( "account-captcha.xml" );
        service = (AccountCaptchaService) ctx.getBean( "accountCaptchaService" );
    }

    //������֤��ͼƬ������
    @Test
    public void testGenerateCaptcha() throws Exception {
    	//��ʼ��һ����֤������
        String captchaKey = service.generateCaptchaKey();
        //��������Ƿ�Ϊ��
        Assert.assertNotNull(captchaKey);
        //��ȡ��֤��ͼƬ
        byte[] captchaImage = service.generateCaptchaImage( captchaKey );
        //����ȡ�����Ƿ�Ϊ��
        Assert.assertTrue(captchaImage.length > 0);
        //�����������֤��ͼƬ���ݵ��½�jpg�ļ���
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
        //����ļ��Ƿ����
        Assert.assertTrue(image.exists() && image.length() > 0);
    }
    
    //����һ����ȷ��Captcha��֤����
    @Test
    public void testValidateCaptchaCorrect() throws Exception {
    	//��ʼ��List��Ŷ��趨ֵ
        List<String> preDefinedTexts = new ArrayList<String>();
        preDefinedTexts.add( "12345" );
        preDefinedTexts.add( "abcde" );
        service.setPreDefinedTexts( preDefinedTexts );
        //������֤������
        String captchaKey = service.generateCaptchaKey();
        //������֤��ͼƬ
        service.generateCaptchaImage( captchaKey );
        //����������ͼƬ�Ƿ��Ӧ
        Assert.assertTrue( service.validateCaptcha( captchaKey, "12345" ) );
        //��һ���������
        captchaKey = service.generateCaptchaKey();
        service.generateCaptchaImage( captchaKey );
        Assert.assertTrue( service.validateCaptcha( captchaKey, "abcde" ) );
    }
    
    //���Ե��û�����Captchaֵ����ʱ�ĳ���
    @Test
    public void testValidateCaptchaIncorrect() throws Exception {
    	//��ʼ��List��Ŷ��趨ֵ
        List<String> preDefinedTexts = new ArrayList<String>();
        preDefinedTexts.add( "12345" );
        service.setPreDefinedTexts( preDefinedTexts );
        //������֤������
        String captchaKey = service.generateCaptchaKey();
        //������֤��ͼƬ
        service.generateCaptchaImage( captchaKey );
        //����������ͼƬ�Ƿ��Ӧ(ʹ�ô���ֵ)
        Assert.assertFalse( service.validateCaptcha( captchaKey, "67890" ) );
    }
}
