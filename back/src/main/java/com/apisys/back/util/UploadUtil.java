package com.apisys.back.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadUtil {

    public Boolean fazerUploadImage(MultipartFile imagem){

        boolean sussecedUpload = false;

        if (!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try{
                //create a diretory for put the archive.

                String pastaUploadImage = "C:\\Users\\Cldianedev\\Desktop\\auth-system-user\\back\\resources";
                File dir = new File(pastaUploadImage);
                if (!dir.exists()){
                    // se o diretorio não existir ele vai criar
                    dir.mkdirs();
                }

                // criar o arquivo no diretorio
                File imageFile = new File(dir.getAbsoluteFile() + File.separator + nomeArquivo);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageFile));
                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Armazenando em :" + imageFile.getAbsolutePath());
            } catch (Exception e){

            }
        }
        return null;
    }
}
