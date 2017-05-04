package com.juvenxu.mvnbook.account.captcha;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomGeneratorTest
{
	@Test
	public void testGetRandomString() throws Exception {
		//��ʼ����Ϊ100�ļ���randoms
		Set<String> randoms = new HashSet<String>( 100 );
		//ѭ��һ�ٴ��ж�ÿ�����ɵ�ֵ�Ƿ�Ϊ���ֵ
		for ( int i = 0; i < 100; i++ ) {
	            String random = RandomGenerator.getRandomString();
	            //�ж����ɵ�����ַ����ڼ������Ƿ����
	            Assert.assertFalse( randoms.contains( random ));
	            randoms.add( random );
	        }
	}
//    @Test
//    public void testGetRandomString()
//        throws Exception
//    {
//        Set<String> randoms = new HashSet<String>( 100 );
//
//        for ( int i = 0; i < 100; i++ )
//        {
//            String random = RandomGenerator.getRandomString();
//
//            assertFalse( randoms.contains( random ) );
//
//            randoms.add( random );
//        }
//    }
}
