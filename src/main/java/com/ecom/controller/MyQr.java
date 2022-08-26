package com.ecom.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class MyQr {

	@SuppressWarnings("deprecation")
	public static byte[] createQR(String data, String path, String charset, Map hashMap, int height, int width)
			throws WriterException, IOException {

		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "JPEG", byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}

}
