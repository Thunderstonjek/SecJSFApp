package ch.gibm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;

public class Hashers {
	public static String md5(String message){
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			//converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2*hash.length);
			for(byte b : hash){
				sb.append(String.format("%02x", b&0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger("MD5Hash_Error_Logger").log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger("MD5Hash_Error_Logger").log(Level.SEVERE, null, ex);
		}
		return digest;
	}
	
	public static String sha256(String message){
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(message.getBytes("UTF-8"));
			//converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2*hash.length);
			for(byte b : hash){
				sb.append(String.format("%02x", b&0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger("MD5Hash_Error_Logger").log(Level.SEVERE, null, ex);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger("MD5Hash_Error_Logger").log(Level.SEVERE, null, ex);
		}
		return digest;
	}
	
	// 80 char long string (salt + hash(salt + message))
	public static String sha256WithSalt(String message, String salt){
		return salt + sha256(salt + message);
	}
	
	// 16 char long salt
	public static String generateSalt() {
		return sha256(new Random().toString()).substring(0, 16);
	}
}
