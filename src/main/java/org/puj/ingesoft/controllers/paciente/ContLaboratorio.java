package org.puj.ingesoft.controllers.paciente;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import java.io.File;
import java.io.IOException;

public class ContLaboratorio {


    public void PonerContraseña() throws IOException {
        {

            File f = new File("D:\\.pdf");  //se selecciona el pdf a ponerle la contraseña
            PDDocument pdd = PDDocument.load(f);

            AccessPermission ap = new AccessPermission();

            StandardProtectionPolicy stpp
                    = new StandardProtectionPolicy("admin", "----", ap);  //se le asigna las contraseñas - el user password sería la cedula del paciente

            stpp.setEncryptionKeyLength(128);

            stpp.setPermissions(ap);

            pdd.protect(stpp);

            pdd.save("D:\\.pdf");  //se remplaza el archivo inicial por el archivo con contraseña
            pdd.close();

            System.out.println("PDF Encrypted successfully...");
        }
    }
}