/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.eeasa.sendmail.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 *
 * @author Carlos
 */
public class ArchivoUtil {

    public static byte[] convertirArchivoAByteArray(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if (ios.read(buffer) == -1) {
                throw new IOException("EOF reached while trying to read the whole file");
            }
        } catch (Exception ex) {
            buffer = null;
        } finally {
            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (IOException e) {
            }
        }
        return buffer;
    }
    
    public static byte[] getBytesFromInputStream(InputStream is)
    {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
        {
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1;)
                os.write(buffer, 0, len);

            os.flush();

            return os.toByteArray();
        }
        catch (IOException e)
        {
            return null;
        }
    }
    
    public static void stringToFile(String ruta, String contenido) {
        FileOutputStream fop = null;
        File file;
        try {
            file = new File(ruta);
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = contenido.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static byte[] obtenerBytesDeDocument(Document documentoXml) throws Exception {
  	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  	  Source xmlSource = new DOMSource(documentoXml);
  	  Result outputTarget = new StreamResult(outputStream);
  	  TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
  	  
  	  byte[] result = outputStream.toByteArray();	    	  
  	  return result;
   }     
}