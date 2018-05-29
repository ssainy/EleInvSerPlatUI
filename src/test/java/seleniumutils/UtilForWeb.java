package seleniumutils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Created by Administrator on 2016/10/30.
 */
public class UtilForWeb {
    private static Log logger= LogFactory.getLog(UtilForWeb.class);
    public static String getFilePath(String filePath) throws URISyntaxException {
        return new File(sun.reflect.Reflection.getCallerClass(2).getResource(filePath).toURI()).getAbsolutePath();
    }

    public static WebDriver switchToCurrentWindow(WebDriver webDriver) {
        String handle = "";
        ArrayList<String> handles = new ArrayList<String>(webDriver.getWindowHandles());
        handle = handles.get(handles.size() - 1);
        return webDriver.switchTo().window(handle);
    }

    public static WebDriver switchToOneWindow(WebDriver webDriver) {
        String handle = "";
        ArrayList<String> handles = new ArrayList<String>(webDriver.getWindowHandles());
        handle = handles.get(0);
        return webDriver.switchTo().window(handle);
    }

    public static void windowMaximize(WebDriver webDriver) {

        webDriver.manage().window().maximize();
    }

    public static WebElement locateElementByXpath(WebDriver webDriver, String locator) {
        WebElement element = webDriver.findElement(By.xpath(locator));
        return element;
    }
    public static WebElement locateElementById(WebDriver webDriver, String locator) {
        WebElement element = webDriver.findElement(By.id(locator));
        return element;
    }

    public static void taskesScreenshot(WebDriver webDriver, String filePath, String fileName) throws IOException {
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(fileName));
    }

    public static void scroll(WebDriver webDriver, int height) {
        try {
            //垂直滚动条
            String setscroll = "documentElement.scrollTop=" + height;
            //水平滚动条
            //String setscroll = "documentElement.scrollTop=" + height;
            JavascriptExecutor jse = (JavascriptExecutor) webDriver;
            jse.executeScript(setscroll);
        } catch (Exception e) {
            //logger.error("fail to set the scroll");
            e.printStackTrace();
        }
    }

    public static Properties readproper(String filepath) {
        InputStream resourceAsStream = Class.class.getResourceAsStream(filepath);
        if (resourceAsStream == null) {
            return null;
        }
        Properties properties = new Properties();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, "GBK"));
            properties.load(reader);
        } catch (IOException e) {
        }
        return properties;
    }

    public static String readTxtFile(String filepath) {
        try {
            StringBuffer strBuffer= new StringBuffer();
            String ecoding = "GBK";
            File file = new File(filepath);
            if (file.isFile() && file.exists()) {
                InputStreamReader inputstr = new InputStreamReader(new FileInputStream(filepath), ecoding);
                BufferedReader bufferedReader = new BufferedReader(inputstr);
                String LinkTxt = null;
                while ((LinkTxt = bufferedReader.readLine()) != null) {
                    strBuffer.append(LinkTxt).append("\n");
                    //System.out.println("util print==========>>>>>>>>>>>" + strBuffer);
                    //System.out.println("print==========>>>>>>>>>>>" +LinkTxt);
                    logger.info("util print===>>>"+strBuffer);
                    logger.warn("the log message is warn");
                    logger.error("the log message is error");
                    logger.debug("the log message is debug");
                }
                inputstr.close();
                return strBuffer.toString();
            } else {
                System.out.println("系统找不到指定文件");
                return null;
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return null;
        }
    }
    public static void proLogin(WebDriver webDriver){
        Properties properties=UtilForWeb.readproper("/provider.properties");
        webDriver = new ChromeDriver();//启动chrome浏览器
        String url="http://10.1.20.8/provider/index.html";
        webDriver.get(url);//打开网页
        webDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        webDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        webDriver.findElement(By.id("loginButton")).click();
    }
    public static void adminLogin(WebDriver webDriver){
        webDriver = new ChromeDriver();//启动chrome浏览器
        webDriver.get("http://10.1.20.8/management/index.html");
        webDriver.findElement(By.id("username")).sendKeys("admin");
        webDriver.findElement(By.id("password")).sendKeys("admin");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        webDriver.findElement(By.id("loginButton")).click();
    }
    public static void entLogin(WebDriver webDriver){
        Properties properties=UtilForWeb.readproper("/provider.properties");
        String url="http://10.1.20.8/enterprise/index.html";
        webDriver.get(url);//打开网页
        webDriver.findElement(By.id("username")).sendKeys(properties.getProperty("EntUser"));
        webDriver.findElement(By.id("password")).sendKeys("LLXY888a");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        webDriver.findElement(By.id("loginButton")).click();
        String result=webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/a[1]")).getText();
        Assert.assertEquals("退出登录",result);
    }
}
