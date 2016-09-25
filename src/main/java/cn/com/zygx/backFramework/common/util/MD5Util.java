package cn.com.zygx.backFramework.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
/**
 * 
 * @author 
 *
 */
public class MD5Util {
	/**
	 *  对原始的字符串origStr按照md5加密算法
	 *  进行加密，返回一个加密之后的字符串(摘要)。
	 *  摘要有两个特点：
	 *  	不可逆性：通过摘要，不能够反推出原始的字符串。
	 *  	唯一性：原始的字符串生成的摘要是唯一的。
	  */
	public static String encrypt(String origStr) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			//digest方法会依据之前指定的算法
			//生成一个摘要。
			byte[] buf = md.digest(origStr.getBytes());
			//将字节数组转换成一个字符串
			BASE64Encoder encoder = new BASE64Encoder();
			String str = encoder.encode(buf);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
