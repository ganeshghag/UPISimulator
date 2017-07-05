package com.javapapers.java.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Hex;

public class EncryptionDecryptionAES {
	static Cipher cipher;
	
	//http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#impl
	//AES/CBC/NoPadding (128)
	//AES/CBC/PKCS5Padding (128)
	//AES/ECB/NoPadding (128)
	//AES/ECB/PKCS5Padding (128)
	
	static public String AES_CIPHER = "AES/CBC/PKCS5Padding";
	//static public String AES_CIPHER = "AES/CBC/NoPadding";  //does not work with random data
	//ECB mode cannot use IV
	//If you use a block-chaining mode like CBC, you need to provide an IvParameterSpec to the Cipher as well.
	
	//The default modes for symmetric algorithms can be found in the SymmetricAlgorithm Properties page on the MSDN. 
	//In this case, PaddingMode.PKCS7 is the default padding mode used.
	//Be aware, however, that this specific Cryptographic Exception can be thrown in different circumstances as well. If your Key and/or IV aren't the exact same ones that were used in the encryption of your data then this exception will be thrown.
	
	//https://stackoverflow.com/questions/29232705/encrypt-text-to-aes-cbc-pkcs7padding
	//Java only provides PKCS#5 padding, but it is the same as PKCS#7 padding.
	
	
	static public String INIT_VECTOR = "RandomInitVector";  //required for padding
	
	//AES keysize = 128 or 256
	//AES cipher algo with padding
	//AES with IV
	
	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		cipher = Cipher.getInstance(AES_CIPHER);

		String plainText = "TEST TEXTDATA for AES Symmetric Encryption Decryption";
		System.out.println("Plain Text Before Encryption: " + plainText);

		String encryptedText = encrypt(plainText, secretKey);
		System.out.println("Encrypted Text After Encryption: " + encryptedText);

		String decryptedText = decrypt(encryptedText, secretKey);
		System.out.println("Decrypted Text After Decryption: " + decryptedText);
	}

	public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
		
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		//Base64.Encoder encoder = Base64.getEncoder();
		//String encryptedText = encoder.encodeToString(encryptedByte);
		String encryptedText = Hex.encodeHexString(encryptedByte);
		return encryptedText;
	}

	public static String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
		
		//Base64.Decoder decoder = Base64.getDecoder();
		//byte[] encryptedTextByte = decoder.decode(encryptedText);
		byte[] encryptedTextByte = Hex.decodeHex(encryptedText.toCharArray());
		cipher.init(Cipher.DECRYPT_MODE, secretKey,iv);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
}
