package com.juvenxu.mvnbook.account.captcha;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomGeneratorTest
{
	@Test
	public void testGetRandomString() throws Exception {
		//初始容量为100的集合randoms
		Set<String> randoms = new HashSet<String>( 100 );
		//循环一百次判断每次生成的值是否为随机值
		for ( int i = 0; i < 100; i++ ) {
	            String random = RandomGenerator.getRandomString();
	            //判断生成的随机字符串在集合中是否存在
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
