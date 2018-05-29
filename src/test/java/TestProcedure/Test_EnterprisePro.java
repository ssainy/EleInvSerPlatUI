package TestProcedure;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumutils.JDBC;
import seleniumutils.UtilForWeb;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by cch on 2017/1/11.
 */
public class Test_EnterprisePro {
    long sec=1000;
    private static WebDriver chromeDriver;
    private static String chromeClasspath = "/chromedriver.exe";
    private static String chromePath;
    static Properties properties=UtilForWeb.readproper("/enterprise.properties");
    public static void proLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("prourl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("Lihuajian2012");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    public static void entLogin(String username){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("enturl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(username);
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    public static void 企业注册(String username,String phone) throws Exception {
        chromePath= UtilForWeb.getFilePath(chromeClasspath);
        System.setProperty("webdriver.chrome.driver",chromePath);
        //JDBC.update("update ele_user set telephone='18335834704' where user_type='ENTERPRISE'and telephone='18335834703'");
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        //企业注册
        String url=properties.getProperty("enturl");
        chromeDriver.get(url);//打开网页
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/p/a[1]")).click();
        chromeDriver.findElement(By.id("loginName")).sendKeys(username);
        chromeDriver.findElement(By.id("loginPassword")).sendKeys("LLXY0828cch");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("LLXY0828cch");
        chromeDriver.findElement(By.id("telephone")).sendKeys(phone);
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("sendMsgBtn")).click();
        JOptionPane.showMessageDialog(null, "请输入短信验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("ckbname")).click();
        chromeDriver.findElement(By.xpath("//*[@value=\"注　册\"]")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("//*[text()='马上登录']")).getText();
        Assert.assertEquals("马上登录",result);
        chromeDriver.quit();
    }
    @Test
    public static void 企业三证合一认证(String nsrmc,String nsrsbh,String username) throws Exception {
        chromePath= UtilForWeb.getFilePath(chromeClasspath);
        System.setProperty("webdriver.chrome.driver",chromePath);
        entLogin(username);
        //申请企业认证
        chromeDriver.findElement(By.xpath("//*[text()='立即去认证']")).click();
        chromeDriver.findElement(By.id("type1")).click();
        chromeDriver.findElement(By.id("corpName")).sendKeys(nsrmc);
        chromeDriver.findElement(By.id("creditCode")).sendKeys(nsrsbh);
        chromeDriver.findElement(By.id("creditPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[4]/b")).click();//点击上传图片按钮
        chromeDriver.findElement(By.id("areaId")).click();
        chromeDriver.findElement(By.xpath("//*[text()='山西省']")).click();
        chromeDriver.findElement(By.id("registerAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("operationsAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("tax_org_code")).click();
        chromeDriver.findElement(By.xpath("//*[text()='北京市国家税务局']")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.id("city_org_code")).click();
        chromeDriver.findElement(By.id("contracts")).sendKeys("崔彩虹");
        chromeDriver.findElement(By.id("contractsEmail")).sendKeys("507939109@qq.com");
        chromeDriver.findElement(By.id("contractsCard")).sendKeys("140729199408280046");
        chromeDriver.findElement(By.id("contractsCardPath1")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[14]/b")).click();
        chromeDriver.findElement(By.id("contractsCardPath2")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[15]/b")).click();
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(5000);
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div[1]/ul/li[1]/p")).getText();
        Assert.assertEquals("审核通过!",result);
        chromeDriver.quit();
    }
    public static void 企业申请开通开票服务_服务器版(String username) throws InterruptedException, URISyntaxException {
        chromePath= UtilForWeb.getFilePath(chromeClasspath);
        System.setProperty("webdriver.chrome.driver",chromePath);
        entLogin(username);
        Thread.sleep(1000);
        JavascriptExecutor j=(JavascriptExecutor)chromeDriver;
        j.executeScript("document.getElementById('orderManager').style.display='block';");
        chromeDriver.findElement(By.xpath("//*[@id=\"leftnav2\"]")).click();//点击下单申请
        //chromeDriver.findElement(By.xpath("//*[@id=\"providerform\"]/ul/li[1]/div[3]/label")).click();
        chromeDriver.findElement(By.id("provider")).sendKeys("蛋敦的服务商");
       // chromeDriver.findElement(By.xpath("//*[text()='李华健的服务商']")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"productTable\"]/tbody/tr[1]/td[1]/input")).click();
        chromeDriver.findElement(By.id("cert_email")).sendKeys("507939109@qq.com");
        chromeDriver.findElement(By.id("getemailcode")).click();
        JOptionPane.showMessageDialog(null, "请输入邮箱验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("ckbname")).click();
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/span/font[2]")).getText();
        Assert.assertEquals("申请通过！",result);
    }
    public static void 服务商审核企业的开票服务_通过() throws InterruptedException, URISyntaxException {
        chromePath= UtilForWeb.getFilePath(chromeClasspath);
        System.setProperty("webdriver.chrome.driver",chromePath);
        proLogin();
        JavascriptExecutor j=(JavascriptExecutor)chromeDriver;
        j.executeScript("document.getElementById('deliverService').style.display='block';");
        chromeDriver.findElement(By.xpath("//*[@id=\"leftnav6\"]")).click();//点击下单申请
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='审批']")).click();
        chromeDriver.findElement(By.xpath("//*[text()='下一步']")).click();
        chromeDriver.findElement(By.id("uploaders")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\2.jpg");
        JOptionPane.showMessageDialog(null, "请手动点击上传", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.xpath("//*[text()='获取制章制证信息']")).click();
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        chromeDriver.findElement(By.id("send2")).click();
    }
}
