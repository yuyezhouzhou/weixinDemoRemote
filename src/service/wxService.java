package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class wxService {
	
	private static final String TOKEN = "yyzz";
	/**
	 * 校验微信接入是否成功
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	public static boolean check(String timestamp, String nonce, String signature) {
		//1）将token、timestamp、nonce三个参数进行字典序排序 
		String[] strs = new String[] {TOKEN,timestamp,nonce};
		Arrays.sort(strs);
		//2）将三个参数字符串拼接成一个字符串进行sha1加密 
		String src = strs[0]+strs[1]+strs[2];
		String mySign = sha1(src);
		System.out.println(mySign);
		System.out.println(signature);
		//3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return mySign.equalsIgnoreCase(signature);
	}
	
	/**
	 * sha1加密
	 * @param strs
	 * @return
	 */
	private static String sha1(String src) {
		char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("sha1");
			byte[] digest = md.digest(src.getBytes());
			for (byte b : digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
