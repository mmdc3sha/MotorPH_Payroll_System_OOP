package AdminView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFPrinter {

    public static void printPanelToPDF(JPanel panel, String filePath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            BufferedImage bufferedImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            panel.print(g2d);
            g2d.dispose();

            // Convert BufferedImage to iText Image
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(writer, bufferedImage, 1.0f);
            document.add(image);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}