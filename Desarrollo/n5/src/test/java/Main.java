import org.testng.Assert;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.util.List;

public class Main {
    static TestNG testNg;

    public static void main(String[] args) throws Exception {
        List<XmlSuite> suite = null;

        //String fileNameSmoke = System.getenv("fileXML");

        String fileNameSmoke = "smoke.xml";
        try {
            suite = (List<XmlSuite>) (new Parser(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileNameSmoke)).parse());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Doesnt process the file: " + fileNameSmoke);
        }

        testNg = new TestNG();
        testNg.setVerbose(10);
        testNg.setXmlSuites(suite);
        testNg.run();
    }
}
