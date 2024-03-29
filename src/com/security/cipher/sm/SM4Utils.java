package com.security.cipher.sm;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import com.security.cipher.util.EncryptUtil;

public class SM4Utils
{
	private String secretKey = "";
	
	private String iv = "";
	
	private boolean hexString = true;
	
	public SM4Utils()
	{
	}

	public void setSecretKey(String Key)
	{
		this.secretKey = Key;
	}

	public void setIv(String iv)
	{
		this.iv = iv;
	}

	public void setHexString(boolean hexString)
	{
		this.hexString = hexString;
	}

	public static void main(String[] args) throws IOException
	{
		String plainText = "abcd000000";

		SM4Utils sm4 = new SM4Utils();
		sm4.secretKey = "JeF8U9wHFOMfs2Y8";
		sm4.hexString = false;

		System.out.println("ECB模式");
		String cipherText = sm4.encryptData_ECB(plainText);
		System.out.println("密文: " + cipherText);
		System.out.println("");

		plainText = sm4.decryptData_ECB(cipherText);

		System.out.println("明文: " + plainText);
		System.out.println("");

		//System.out.println("CBC模式");
		//sm4.iv = "UISwD9fW6cFh9SNS";
		//cipherText = sm4.encryptData_CBC(plainText);
		//System.out.println("密文: " + cipherText);
		//System.out.println("");
		//
		//plainText = sm4.decryptData_CBC(cipherText);
		//System.out.println("明文: " + plainText);
	}
	public String encryptData_ECB(String plainText)
	{
		try 
		{
			SM4Context ctx = new SM4Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_ENCRYPT;
			
			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = EncryptUtil.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx, EncryptUtil.hexStringToBytes(plainText));

			String cipherText = EncryptUtil.encodeHexString(encrypted);
			//String cipherText = new String(encrypted);
			//Log.e("test1",cipherText);
			/*
			String cipherText = new BASE64Encoder().encode(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0)
			{
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}*/
			return cipherText;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String decryptData_ECB(String cipherText)
	{
		try 
		{
			SM4Context ctx = new SM4Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_DECRYPT;
			
			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = EncryptUtil.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx, EncryptUtil.hexStringToBytes(cipherText));
			return EncryptUtil.encodeHexString(decrypted);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String encryptData_CBC(String plainText)
	{
		try 
		{
			SM4Context ctx = new SM4Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_ENCRYPT;
			
			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString)
			{
				keyBytes = EncryptUtil.hexStringToBytes(secretKey);
				ivBytes = EncryptUtil.hexStringToBytes(iv);
			}
			else
			{
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			//byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("GBK"));
			byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("GBK"));
			String cipherText = new BASE64Encoder().encode(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0)
			{
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String decryptData_CBC(String cipherText)
	{
		try 
		{
			SM4Context ctx = new SM4Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_DECRYPT;
			
			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString)
			{
				keyBytes = EncryptUtil.hexStringToBytes(secretKey);
				ivBytes = EncryptUtil.hexStringToBytes(iv);
			}
			else
			{
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}
			
			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
			//return new String(decrypted, "GBK");
			return new String(decrypted, "GBK");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void SM4_ECB_TEST() throws IOException {

		String plainText = "11223344556677888877665544332211";
		String sm4_Key = "00000000000000008888888888888888";
		SM4Utils sm4Utils = new SM4Utils();
		sm4Utils.setSecretKey(sm4_Key);
		String encryptdata = sm4Utils.encryptData_ECB(plainText);
		//txt.setText(encryptdata);
		System.out.println("密文: " + encryptdata);
		String decryptdata = sm4Utils.decryptData_ECB(encryptdata);
		System.out.println("密文: " + decryptdata);
	}
}

