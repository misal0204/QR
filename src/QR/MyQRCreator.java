/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Misael
 */
public class MyQRCreator {

    public MyQRCreator() {
    }

    public BufferedImage createQR(String data, int size) {
        BitMatrix matrix;
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            EnumMap<EncodeHintType, String> hints = new EnumMap<EncodeHintType, String>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            matrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size, hints);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", output);
            byte[] data_array = output.toByteArray();
            ByteArrayInputStream input = new ByteArrayInputStream(data_array);
            return ImageIO.read(input);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (WriterException ex) {
            Logger.getLogger(MyQRCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
