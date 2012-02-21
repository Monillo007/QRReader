/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qrreader;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/** 
 * @author Monillo007
 * 
 * Clase desarrollada por Monillo007's y distribuída bajo la licencia 
 * Creative Commons (Atribución-No comercial) 2.5 México http://creativecommons.org/licenses/by-nc/2.5/mx/
 * para Monillo007's Blog http://monillo007.blogspot.com
 * 
 * No olvides pasar a visitar mi blog y dejar tu comentario!
 */
public class QRReader {

    public static void main(String[] args) {
        //Objeto de tipo Reader que realizara la interpretacion del codigo QR
        Reader lector = new MultiFormatReader();

        //El archivo que contiene la imagen con el codigo a interpretar
        File ubicacionImagen = new File(System.getProperty("user.home") + "/barras.jpg");
        
        //Objeto utilizado para leer la imagen
        BufferedImage imagen;
        
        try {
            //Se verifica que la imagen existe
            if(ubicacionImagen.exists()){
                //Decodificamos el archivo para poderlo leer
                imagen = ImageIO.read(ubicacionImagen);
                
                //Se da un formato generico a la imagen para poder ser interpretada
                LuminanceSource fuente = new BufferedImageLuminanceSource(imagen);
                
                //Creamos un mapa de datos binarios a partir de la fuente
                BinaryBitmap mapaBits = new BinaryBitmap(new HybridBinarizer(fuente));

                //Decodificamos los datos para obtener el contenido del codigo
                Result resultado = lector.decode(mapaBits);

                //Mostramos el contenido del codigo
                System.out.println("Contenido del codigo = "+resultado.getText());
            }else{
                System.out.println("No se puede leer el archivo");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }
}
