package org.puj.ingesoft.controllers.persistencia;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;

public class SeguridadArchivos {

    public static void encryptDecrypt(String key, int cipherMode, String filePathIn, String filePathOut) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException, InvalidKeySpecException {
        File in = new File(filePathIn);
        File out = new File(filePathOut);
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());

        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = skf.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        if(cipherMode == Cipher.ENCRYPT_MODE){
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            write(cis, fos);
            in.delete();
        }
        else if(cipherMode == Cipher.DECRYPT_MODE)
        {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, SecureRandom.getInstance("SHA1PRNG"));
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            write(fis,cos);
            cos.close();
        }
        fos.close();
        fis.close();
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[64];
        int numberOfBytesRead;
        while((numberOfBytesRead = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, numberOfBytesRead);
        }
        out.close();
        in.close();
    }

    public static void main (String[] args)
    {
        String pathP = "./DB_P.db";
        String pathM = "./DB_M.db";
        String decriptado = "./dec.json";

        try {
            //encryptDecrypt("pk163287", Cipher.ENCRYPT_MODE, decriptado, pathP);
            encryptDecrypt("pk163287", Cipher.DECRYPT_MODE, pathP, decriptado);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}
