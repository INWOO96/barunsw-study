package com.barunsw.framework.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptionUtil {
	public static String encryptSHA512(String target) {
		try {
			MessageDigest objSHA = MessageDigest.getInstance("SHA-512");
			byte[] bytSHA = objSHA.digest(target.getBytes());
			BigInteger intNumber = new BigInteger(1, bytSHA);
			String strHashCode = intNumber.toString(16);

			while (strHashCode.length() < 128) {
				strHashCode = "0" + strHashCode;
			}
			return strHashCode;
		} catch (Exception e) {
			return "";
		}
	}
}
