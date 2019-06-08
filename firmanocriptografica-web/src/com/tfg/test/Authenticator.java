package com.tfg.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;


public class Authenticator {

	public static void main(String[] args) throws Exception {
		
	    GoogleAuthenticator gAuth = new GoogleAuthenticator();
	    final GoogleAuthenticatorKey key = gAuth.createCredentials();
	    String claveSecreta = key.getKey();
	    
	    System.out.println("Clave secreta: " + key.getKey());
	    
	    String URLGoogleQRCode = GoogleAuthenticatorQRGenerator.getOtpAuthURL("TFG", "75720884p@rus", key);
	    String contenidoQR =  GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("TFG", "75720884p@rus", key);
	    
	    System.out.println("Google QR: " + URLGoogleQRCode);
	    System.out.println("Contenido QR2: " + contenidoQR);
	    
	    
	    System.out.println("IMAGEN QR: " + Base64.getEncoder().encodeToString(new Authenticator().generateQRCodeImage(contenidoQR, 200, 200)));
	    
	    claveSecreta = "LESUBIDXHRPTEERN";
	    boolean isCodeValid = gAuth.authorize(claveSecreta, Integer.parseInt("275363"));
	    System.out.println("Resultado verificacion: " + isCodeValid);
	    
	}
	
	
	private byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
       
        MatrixToImageWriter.writeToStream(matrix, "png", stream);
        stream.flush();

        byte[] data = stream.toByteArray();
        stream.close();
        
        return data;
    }

}
