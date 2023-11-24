package DriverManager;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    private WebDriver driver;

    public Utils(WebDriver driver){
        this.driver = driver;
    }
    public Utils(){

    }

    public String getDate(){
        LocalDateTime localTime = LocalDateTime.now();
        String temp = localTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd___HH-mm-SS"));

        return temp;
    }

    public String TakeScreenshot(String fileName){
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String file = fileName + '_'+ getDate() +".PNG";
        String name = "src\\test\\resources\\reportes\\" + fileName + '_'+ getDate() +".PNG";
        // Save the screenshot to a file
        try {
            FileUtils.copyFile(screenshotFile, new File(name));
            System.out.println("Screenshot saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save the screenshot: " + e.getMessage());
        }

        return file;
    }

    public String TakeScreenshot(String fileName, boolean createFile){
        String name = "";
        String file = fileName + '_'+ getDate() +".PNG";

        if(createFile)
            name = fileName;
        else
            name = "src\\test\\resources\\reportes\\" + fileName + '_'+ getDate() +".PNG";
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot to a file
        try {
            FileUtils.copyFile(screenshotFile, new File(name));
            System.out.println("Screenshot saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save the screenshot: " + e.getMessage());
        }

        return file;
    }
    public void createFileWithEvidenceScreenShot(String nameScreenshot, String nameDocx, String fec,  String titulo) throws IOException, InvalidFormatException{

        String rutaImagen = "src/test/resources/" + nameScreenshot + '_'+ getDate() +".png";
        String rutaDocumento = "src/test/resources/" + nameDocx + fec + ".docx";
        TakeScreenshot(rutaImagen, true);
        File fichero = new File(rutaDocumento);

        XWPFDocument docx;

        //Niega el fichero para preguntar si no existe
        if (!fichero.exists()) {
            docx = new XWPFDocument();
        } else {
            FileInputStream ficheroStream = new FileInputStream(fichero);
            docx = new XWPFDocument(ficheroStream);
        }

        //Crea parrafo necesita utilizar tipo dato parrafo setea titulo y font size
        XWPFParagraph paragraph = docx.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(titulo);
        run.setFontSize(13);
        run.setBold(true);
        run.setTextPosition(50);

        //Sube la captura de pantalla de la ruta solicitada al archivo solicitado
        InputStream pic = new FileInputStream(rutaImagen);
        run.addPicture(pic, Document.PICTURE_TYPE_PNG, rutaImagen,
                Units.toEMU(1000), Units.toEMU(1000));
        pic.close();

        File file = new File(rutaImagen);

        try {
            if (file.exists()) {
                file.delete();
            }
        }catch (Exception e){
            System.out.println("No se pudo eliminar el archivo");
        }

        FileOutputStream out = new FileOutputStream(rutaDocumento);
        docx.write(out);
        out.flush();
        out.close();
        docx.close();
    }

}
