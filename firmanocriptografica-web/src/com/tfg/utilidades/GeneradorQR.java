package com.tfg.utilidades;

import java.io.ByteArrayOutputStream;
import javax.inject.Named;
import org.omnifaces.cdi.GraphicImageBean;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Named("generadorQR")
@GraphicImageBean
public class GeneradorQR {


	public byte[] getContenido(String textoContenidoQR) {
		return generateQRCodeImage(textoContenidoQR, 200, 200);
	}

	private byte[] generateQRCodeImage(String text, int width, int height){
		byte[] data = null;
		
        try {
    		//1.- Generar el codigo QR
    		QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            
            //2.- Convertir el codigo en byte[]
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "png", stream);
            stream.flush();
            data = stream.toByteArray();
            stream.close();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return data;
    }



}


