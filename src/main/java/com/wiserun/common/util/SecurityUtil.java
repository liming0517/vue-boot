package com.wiserun.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 安全处理工具
 * 
 * @author chenKeYu
 * 
 * @date 2014年4月11日
 */
public class SecurityUtil {

	/*private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e',
		'2', '3', '4', '5', '6', '7', '8', 'g', 'f', 'y', 'n', 'm', 'n',
		'p', 'w', 'x' };*/
	
	private static char[] captchars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private static Random generator = new Random();
	
	//private static String md5Key = "8vzqtymeopjqacb8arwbmc6khdg32b7";
    /**
     * 生成MD5验签名
     * @param xml
     * @return
     */
    public static String buildMD5Sign(String xml,String md5Key){
        String content = "xml="+ xml + md5Key;
        String encryText;
        try {
            encryText = MD5Encrypt(content, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }catch(UnsupportedEncodingException e){
            return null;
        }
        return encryText;
    }
    private static String MD5Encrypt(String plainText, String encode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encText = StringUtils.EMPTY;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes(encode));
        byte b[] = md.digest();
        StringBuilder buf = new StringBuilder();
        for (int offset = 0; offset < b.length; offset++) {
            int i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        encText = buf.toString();
        return encText;
    }
	/**
	 * MD5加密方法
	 */
	public static String md5(String str) {
		byte[] resultByte = DigestUtils.md5(str.getBytes());
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < resultByte.length; ++i) {
			result.append(Integer.toHexString(0xFF & resultByte[i]));
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateKey());
		
	}
	
	/**
	 * 生产8位字母数字秘钥
	 * @return
	 */
	public static String generateKey(){
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(captchars[generator.nextInt(captchars.length - 1) + 1]);
		}
		return sb.toString();
	}
	
	
	

}
