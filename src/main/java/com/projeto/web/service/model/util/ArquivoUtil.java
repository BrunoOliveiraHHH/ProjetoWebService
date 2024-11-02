package com.projeto.web.service.model.util;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class ArquivoUtil {

	/** Converte String base64 diretamente para blob */
	public static Blob stringBase64ToBlob(String arquivo) {
		return byteToBlob(stringTobyte(arquivo));
	}

	/** Converte blob diretamente para String base64 */
	public static String blobToStringBase64(Blob arquivo) {
		return byteToString(blobToByte(arquivo));
	}

	public static Blob byteToBlob(byte[] arquivo) {

		Blob convert = null;

		try {
			convert = new SerialBlob(arquivo);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convert;
	}

	public static byte[] blobToByte(Blob arquivo) {
		byte[] convert = null;

		try {
			convert = arquivo.getBytes(1l, (int) arquivo.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convert;
	}

	public static byte[] stringTobyte(String arquivo) {
		return Base64.getDecoder().decode(arquivo);
	}

	public static String byteToString(byte[] arquivo) {
		return Base64.getEncoder().withoutPadding().encodeToString(arquivo);
	}
}