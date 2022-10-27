package cerec.biblio.demo.config;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToImageConvert {

    private static String fileName = "";

//    private static String getFileName(){return fileName;}
//    private void setFileName(String fileName){fileName = fileName;}

    public static void convert(String fileUrl) throws IOException {

        PDDocument document = PDDocument.loadNonSeq(new File(fileUrl),null);

        PDPage page = (PDPage) document.getDocumentCatalog().getAllPages().get(0);

        BufferedImage bim = page.convertToImage(BufferedImage.TYPE_INT_RGB,300);
        PDFToImageConvert.fileName = fileUrl.substring(fileUrl.lastIndexOf("\\")+1,fileUrl.lastIndexOf("."));
        ImageIOUtil.writeImage(bim,fileUrl+".png",300);



    }

//    public static void SupFile(){
//        File supF = new File(PDFToImageConvert.fileName);
//        supF.delete();
//    }
}
