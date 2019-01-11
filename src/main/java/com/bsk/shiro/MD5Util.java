package com.bsk.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 基于shiro的MD5加密
 * @author Lenovo
 *
 */
public class MD5Util {
	public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString() ;
    }
    public static void main(String[] args) {
        String md5 = md5("abc123","bsk") ;
        System.out.println(md5);
    }
}
