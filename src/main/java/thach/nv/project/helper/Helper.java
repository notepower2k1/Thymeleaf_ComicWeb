package thach.nv.project.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {

	static public String getNewID(String oldid, int num, int zeroQtt, String first) {
		int id = Integer.parseInt(oldid.substring(num)) + 1;
		String temp = (zeroQtt == 2 ? "00" : "0").concat(String.valueOf(id));
		return first.concat(temp.substring(String.valueOf(id).length() - 1));
	}
	
	static public String getEncryptedMD5(String strInput) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(strInput.getBytes());
			BigInteger b = new BigInteger(1, messageDigest);
			
			String hashText = b.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			return hashText;
		} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
		}
	}

}
