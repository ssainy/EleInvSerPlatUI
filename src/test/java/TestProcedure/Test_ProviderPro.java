package TestProcedure;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumutils.UtilForWeb;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by cch on 2017/1/11.
 */
public class Test_ProviderPro {
    long sec=1000;
    private static WebDriver chromeDriver;
    private static String chromeClasspath = "/chromedriver.exe";
    private static String chromePath;
    static Properties properties=UtilForWeb.readproper("/provider.properties");
    @BeforeClass
    public static void classSetUp() throws URISyntaxException {
        chromePath= UtilForWeb.getFilePath(chromeClasspath);
        System.setProperty("webdriver.chrome.driver",chromePath);
    }
    @AfterClass
    public static void classTearDown()
    {
        //chromeDriver.quit();
        System.out.println("所有测试都执行完成！！！");
    }
    @AfterMethod
    public static void methodTearDown()
    {
        //chromeDriver.quit();
        System.out.println("所有测试都执行完成！！！");
    }
    public static void proLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String prourl=properties.getProperty("prourl");
        chromeDriver.get(prourl);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    public static void adminLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String adminurl=properties.getProperty("url");
        chromeDriver.get(adminurl);
        chromeDriver.findElement(By.id("username")).sendKeys("admin");
        chromeDriver.findElement(By.id("password")).sendKeys("Mzss2016");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }

    @Test(description="服务商注册")
    public void 服务商注册() throws InterruptedException {
        //服务商注册
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("prourl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/p/a[1]")).click();
        chromeDriver.findElement(By.id("loginName")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("loginPassword")).sendKeys("LLXY0828cch");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("LLXY0828cch");
        chromeDriver.findElement(By.id("telephone")).sendKeys("18335834703");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("sendMsgBtn")).click();
        JOptionPane.showMessageDialog(null, "请输入短信验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("ckbname")).click();
        chromeDriver.findElement(By.xpath("//*[@value=\"注　册\"]")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("//*[text()='马上登录']")).getText();
        Assert.assertEquals("马上登录",result);
    }
    @Test(description="服务商登录")
    public void testProLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("prourl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
        String result=chromeDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/a[1]")).getText();
        Assert.assertEquals("退出登录",result);
    }
    @Test(description="后台登录")
    public void testAdminLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        chromeDriver.get(properties.getProperty("adminurl"));
        chromeDriver.findElement(By.id("username")).sendKeys("admin");
        chromeDriver.findElement(By.id("password")).sendKeys("Mzss2016");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    @Test
    public void 服务商三证不合一认证() throws InterruptedException {
        proLogin();
        //申请服务商认证
        Thread.sleep(1000);
        chromeDriver.findElement(By.id("leftnav1")).click();
        chromeDriver.findElement(By.id("corpName")).sendKeys("测试信息有限公司");
        chromeDriver.findElement(By.id("ratepayersCode")).sendKeys("123658965478967");
        chromeDriver.findElement(By.id("taxRegisterCode")).sendKeys("4545123654789654124");
        chromeDriver.findElement(By.id("taxRegisterPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[5]/p/b")).click();//点击上传图片按钮
        chromeDriver.findElement(By.id("orgCode")).sendKeys("123321364");
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("orgPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\2.jpg");
        WebElement webElement=chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[7]/p/b"));//点击上传图片按钮
        Actions action=new Actions(chromeDriver);
        action.clickAndHold(webElement).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[7]/p/b")).click();
        chromeDriver.findElement(By.id("businessLicence")).sendKeys("1234567891234");
        chromeDriver.findElement(By.id("businessLicencePath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\2.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[9]/p/b")).click();
        JOptionPane.showMessageDialog(null, "请点击上传图片", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("areaId")).click();
        chromeDriver.findElement(By.xpath("//*[text()='山西省']")).click();
        chromeDriver.findElement(By.id("registerAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("operationsAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("contracts")).sendKeys("崔彩虹");
        chromeDriver.findElement(By.id("contractsEmail")).sendKeys("507939109@qq.com");
        chromeDriver.findElement(By.id("contractsCard")).sendKeys("140729199408280046");
        chromeDriver.findElement(By.id("contractsCardPath1")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[20]/p/b")).click();
        chromeDriver.findElement(By.id("contractsCardPath2")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[21]/p/b")).click();
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(5000);
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[3]/span/font")).getText();
        Assert.assertEquals("认证状态：待审核!",result);
    }
    @Test
    public void 服务商三证合一认证() throws InterruptedException {
        proLogin();
        //申请服务商认证
        chromeDriver.findElement(By.id("leftnav1")).click();
        chromeDriver.findElement(By.id("type2")).click();
        chromeDriver.findElement(By.id("corpName")).sendKeys("测试三证合一公司");
        chromeDriver.findElement(By.id("creditCode")).sendKeys("123658965478964");
        chromeDriver.findElement(By.id("creditPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"yszhyBox\"]/ul/li[4]/p/b")).click();//点击上传图片按钮
        chromeDriver.findElement(By.id("areaId")).click();
        chromeDriver.findElement(By.xpath("//*[text()='山西省']")).click();
        chromeDriver.findElement(By.id("registerAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("operationsAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("contracts")).sendKeys("崔彩虹");
        chromeDriver.findElement(By.id("contractsEmail")).sendKeys("507939109@qq.com");
        chromeDriver.findElement(By.id("contractsCard")).sendKeys("140729199408280046");
        chromeDriver.findElement(By.id("contractsCardPath1")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"yszhyBox\"]/ul/li[15]/p/b")).click();
        chromeDriver.findElement(By.id("contractsCardPath2")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"yszhyBox\"]/ul/li[16]/p/b")).click();
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(5000);
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[3]/span/font")).getText();
        Assert.assertEquals("认证状态：待审核!",result);
    }
    @Test
    public void 后台审核服务商_同意() throws InterruptedException {
       // chromeDriver.get(properties.getProperty("url"));
        adminLogin();
        Thread.sleep(1000);
        JavascriptExecutor j=(JavascriptExecutor)chromeDriver;
        j.executeScript("document.getElementById('manage').style.display='block';");
        chromeDriver.findElement(By.xpath("//*[@id=\"leftnav2\"]")).click();//点击服务商认证审核
       // chromeDriver.findElement(By.id("store_pic"));
        //chromeDriver.findElement(By.xpath("//*[@id=\"leftnav2\"]")).click();//点击服务商认证审核
        Thread.sleep(2000);
        chromeDriver.findElement(By.xpath("//*[text()='审核']")).click();
        chromeDriver.findElement(By.id("plantform")).click();
        chromeDriver.findElement(By.xpath("//*[text()='航信测试']")).click();
        chromeDriver.findElement(By.id("signatureCenter")).click();
        chromeDriver.findElement(By.xpath("//*[text()='北京中心']")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='审核通过']")).click();
        //服务商查看认证审核状态
        chromeDriver.get("http://10.1.20.8/provider/index.html");//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
        chromeDriver.findElement(By.id("leftnav1")).click();
        String result=chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[1]/div[2]/span/font")).getText();
        Assert.assertEquals("审核通过!",result);
    }
    @Test
    public void 后台审核服务商认证_驳回() throws InterruptedException {
        adminLogin();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"leftnav2\"]")).click();//点击服务商认证审核
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='审核']")).click();
        chromeDriver.findElement(By.id("plantform")).click();
        chromeDriver.findElement(By.xpath("//*[text()='京东测试']")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='驳回']")).click();
        //服务商查看认证审核状态
        chromeDriver.get("http://192.168.96.8/provider/index.html");//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys("cch123");
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
        chromeDriver.findElement(By.id("leftnav1")).click();
        String result=chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[1]/div[2]/span/font")).getText();
        Assert.assertEquals("审核未通过!",result);
    }
    @Test
    public void 服务商申请报销资质() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();//点击我的资质
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[1]/td[6]/a")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"layui-layer2\"]/div")).getText();
        Assert.assertEquals("操作处理成功!",result);
    }
    @Test
    public void 服务商申请开票资质() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();//点击我的资质
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[6]/a")).click();
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"layui-layer2\"]/div")).getText();
        Assert.assertEquals("操作处理成功!",result);
        Thread.sleep(2000);
        result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[4]")).getText();
        Assert.assertEquals("待审核",result);
        chromeDriver.close();
    }
    @Test
    public void 后台审核服务商开票资质_同意() throws InterruptedException {
        adminLogin();
        //chromeDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]")).click();//点击后台审核管理系统
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("leftnav3")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[9]/a")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='审核通过']")).click();
        chromeDriver.close();
    }
    @Test
    public void 后台审核服务商开票资质_驳回() throws InterruptedException {
        adminLogin();
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("leftnav3")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[1]/td[9]/a")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='驳回']")).click();
    }
    @Test
    public void 后台审核服务商报销资质_同意() throws InterruptedException {
        adminLogin();
        //chromeDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]")).click();//点击后台审核管理系统
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("leftnav3")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[2]/td[9]/a")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='审核通过']")).click();
    }
    @Test
    public void 后台审核服务商报销资质_驳回() throws InterruptedException {
        adminLogin();
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("leftnav3")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"table\"]/tbody/tr[2]/td[9]/a")).click();
        chromeDriver.findElement(By.id("auditOpinion")).sendKeys("同意");
        chromeDriver.findElement(By.xpath("//*[text()='驳回']")).click();
    }

    @Test
    public void 服务商查看审核开票资质状态_成功() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[4]")).getText();
        Assert.assertEquals("已开通",result);
}
    @Test
    public void 服务商查看审核开票资质状态_失败() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[4]")).getText();
        Assert.assertEquals("审核失败",result);
    }
    @Test
    public void 服务商查看审核报销资质状态_成功() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[1]/td[4]")).getText();
        Assert.assertEquals("已开通",result);
    }
    @Test
    public void 服务商查看审核报销资质状态_失败() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.id("leftnav2")).click();
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[1]/td[4]")).getText();
        Assert.assertEquals("审核失败",result);
    }
}
