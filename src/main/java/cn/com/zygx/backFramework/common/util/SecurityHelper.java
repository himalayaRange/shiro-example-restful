/**  
 * @author wangyi
 * @date 2016-6-20 下午3:24:52 
 * @version V1.0   
 */ 
package cn.com.zygx.backFramework.common.util;

import java.net.URLDecoder;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.zygx.backFramework.common.context.Constant;

/**
 * 
 * @author wangyi
 * @date 2016-6-20 下午3:24:52 
 */
public class SecurityHelper {
    protected final static Logger logger = LoggerFactory.getLogger(SecurityHelper.class);  
    //值越大，加密越深，一般例子都以"Java安全性编程指南”这本书中的例子的值为准，设成1000，我们在这边只需要20就够了，原因就是考虑到加解密的速度问题。
    private final static int ITERATIONS = 20;  
  
    /**
     * 加密
     * @Description
     * @param key
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encrypt(String key, String plainText) throws Exception {  
        // String encryptTxt = "";  
        try {  
            byte[] salt = new byte[8];  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(key.getBytes());  
            byte[] digest = md.digest();  
            for (int i = 0; i < 8; i++) {  
                salt[i] = digest[i];  
            }  
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
            SecretKey skey = keyFactory.generateSecret(pbeKeySpec);  
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);  
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");  
            cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);  
            byte[] cipherText = cipher.doFinal(plainText.getBytes());  
            String saltString = new String(Base64.encode(salt)).replaceAll("\r|\n", "");;  
            String ciphertextString = new String(Base64.encode(cipherText)).replaceAll("\r|\n", "");; 
            return new StringBuffer().append(saltString).append(ciphertextString).toString();  
        } catch (Exception e) {  
            throw new Exception("Encrypt Text Error:" + e.getMessage(), e);  
        }  
    }  
  
    /**
     * 解密
     * @Description
     * @param key
     * @param encryptTxt
     * @return
     * @throws Exception
     */
    public static String decrypt(String key, String encryptTxt) throws Exception {  
        int saltLength = 12;  
        try {  
            String salt = encryptTxt.substring(0, saltLength);  
            String ciphertext = encryptTxt.substring(saltLength, encryptTxt.length());  
            byte[] saltarray = Base64.decode(salt.getBytes());  
            byte[] ciphertextArray = Base64.decode(ciphertext.getBytes());  
            PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");  
            SecretKey skey = keyFactory.generateSecret(keySpec);  
            PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);  
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");  
            cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);  
            byte[] plaintextArray = cipher.doFinal(ciphertextArray);  
            return new String(plaintextArray);  
        } catch (Exception e) {  
            throw new Exception(e);  
        }  
    }  
    
    /**
     * 获取Integer参数并解密
     * @Description
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static Integer getIntegerParamter(HttpServletRequest request,String name)  {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return Integer.valueOf(result);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    
    /**
     * 获取String参数并解密
     * @Description
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static String getStringParamter(HttpServletRequest request,String name) throws Exception {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return result;
    	}catch(Exception e){
    		return null;
    	}
    }
    
    
  /**
   * 获取Double参数并解密
   * @Description
   * @param request
   * @param name
   * @return
   * @throws Exception
   */
    public static Double getDoubleParamter(HttpServletRequest request,String name) throws Exception {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return Double.valueOf(result);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    /**
     * 获取boolean类型参数并解密
     * @Description
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static Boolean getBooleanParamter(HttpServletRequest request,String name) throws Exception {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return Boolean.valueOf(result);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    /**
     * 获取Float类型参数并解密
     * @Description
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static Float getFloatParamter(HttpServletRequest request,String name) throws Exception {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return Float.valueOf(result);
    	}catch(Exception e){
    		return null;
    	}
    }
    
    /**
     * 获取Byte类型参数并解密
     * @Description
     * @param request
     * @param name
     * @return
     * @throws Exception
     */
    public static Byte getByteParamter(HttpServletRequest request,String name) throws Exception {
    	try{
	    	String req=request.getParameter(name);
	    	String result=null;
    	if(req!=null){
    		String param= URLDecoder.decode(req, "UTF-8");
    	    result=SecurityHelper.decrypt(Constant.URLSECURITY, param);
    	}else{
    		result=null;
    	}
	    	return Byte.valueOf(result);
    	}catch(Exception e){
    		return null;
    	}
    }
    
}
