package TestProcedure;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumutils.UtilForWeb;

import javax.swing.*;
import java.util.Properties;

/**
 * Created by cch on 2017/5/24.
 */
public class bak {
    long sec=1000;
    private static WebDriver chromeDriver;
    private static String chromeClasspath = "/chromedriver.exe";
    private static String chromePath;
    static Properties properties= UtilForWeb.readproper("/enterprise.properties");
    public void proLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("prourl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("ProUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    public void entLogin(){
        chromeDriver = new ChromeDriver();//启动chrome浏览器
        String url=properties.getProperty("enturl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("EntUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
    }
    @Test(description="企业登录")
    public void testEntLogin(){
        String url=properties.getProperty("enturl");
        chromeDriver.get(url);//打开网页
        chromeDriver.findElement(By.id("username")).sendKeys(properties.getProperty("EntUser"));
        chromeDriver.findElement(By.id("password")).sendKeys("LLXY0828cch");
        JOptionPane.showMessageDialog(null, "请输入图片验证码", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("loginButton")).click();
        String result=chromeDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/a[1]")).getText();
        Assert.assertEquals("退出登录",result);
    }
    @Test
    public void 企业三证不合一认证() throws InterruptedException {
        entLogin();
        //申请企业认证
        chromeDriver.findElement(By.xpath("//*[text()='立即去认证']")).click();
        chromeDriver.findElement(By.id("corpName")).sendKeys("测试公司");
        chromeDriver.findElement(By.id("ratepayersCode")).sendKeys("123658965478965");
        chromeDriver.findElement(By.id("taxRegisterCode")).sendKeys("4545123654789654123");
        chromeDriver.findElement(By.id("taxRegisterPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[5]/b")).click();//点击上传图片按钮
        chromeDriver.findElement(By.id("orgCode")).sendKeys("123321365");
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("orgPath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\2.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[7]/b")).click();//点击上传图片按钮
        chromeDriver.findElement(By.id("businessLicence")).sendKeys("1234567891234");
        JOptionPane.showMessageDialog(null, "请点击确定", "提示信息", JOptionPane.ERROR_MESSAGE);
        chromeDriver.findElement(By.id("businessLicencePath")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\2.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[9]/b")).click();
        chromeDriver.findElement(By.id("areaId")).click();
        chromeDriver.findElement(By.xpath("//*[text()='山西省']")).click();
        chromeDriver.findElement(By.id("registerAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("operationsAdd")).sendKeys("山西省晋中市");
        chromeDriver.findElement(By.id("contracts")).sendKeys("崔彩虹");
        chromeDriver.findElement(By.id("contractsEmail")).sendKeys("507939109@qq.com");
        chromeDriver.findElement(By.id("contractsCard")).sendKeys("140729199408280046");
        chromeDriver.findElement(By.id("contractsCardPath1")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[19]/b")).click();
        chromeDriver.findElement(By.id("contractsCardPath2")).sendKeys("D:\\workspaces\\EleInvSerPlat\\img\\1.jpg");
        chromeDriver.findElement(By.xpath("//*[@id=\"wszhyBox\"]/ul/li[20]/b")).click();
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(5000);
        chromeDriver.findElement(By.xpath("//*[text()='确定']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div[1]/ul/li[1]/p")).getText();
        Assert.assertEquals("审核通过!",result);
    }
    @Test
    public void 企业申请开通开票服务_单机版() throws InterruptedException {
        entLogin();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"leftnav5\"]")).click();
        chromeDriver.findElement(By.id("tax_org_code")).click();//选择省级税务机关
        chromeDriver.findElement(By.xpath("//*[text()='山西省国家税务局']")).click();
        chromeDriver.findElement(By.id("city_org_code")).click();//选择市级税务机关
        chromeDriver.findElement(By.xpath("//*[text()='晋中市国家税务局']")).click();
        chromeDriver.findElement(By.id("provider")).click();
        chromeDriver.findElement(By.xpath("//*[text()='测试信息有限公司']")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"providerform\"]/ul/li[8]/input[1]")).click();//选择单机版
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='提交']")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='确认提交']")).click();
        Thread.sleep(1000);
        String result=chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/span/font[2]")).getText();
        Assert.assertEquals("申请审核中，请耐心等待！",result);
    }
    @Test
    public void 服务商审核企业的开票服务_失败() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/span")).click();
        chromeDriver.findElement(By.id("leftnav6")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='审批']")).click();
        chromeDriver.findElement(By.xpath("//*[text()='退回']")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"layui-layer2\"]/div[2]/textarea")).sendKeys("不符合");
        chromeDriver.findElement(By.xpath("//*[text()='退回']")).click();
    }
    @Test
    public void 企业查看开票服务状态_通过() throws InterruptedException {
        entLogin();
        chromeDriver.findElement(By.id("leftnav3")).click();//点击我的服务
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr/td[2]")).getText();
        Assert.assertEquals("审核通过",result);

    }
    @Test
    public void 企业查看开票服务状态_失败() throws InterruptedException {
        entLogin();
        chromeDriver.findElement(By.id("leftnav3")).click();//点击我的服务
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr/td[2]")).getText();
        Assert.assertEquals("审核未通过",result);

    }
    @Test
    public void 企业申请开通报销服务(){
        entLogin();
        //chromeDriver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]")).click();//点击服务申请
        chromeDriver.findElement(By.id("leftnav7")).click();//点击报销服务申请
        chromeDriver.findElement(By.id("provider")).click();
        chromeDriver.findElement(By.xpath("//*[text()='测试信息有限公司']")).click();
        chromeDriver.findElement(By.xpath("//*[@id=\"providerform\"]/ul/li[6]/div/a")).click();
        chromeDriver.findElement(By.xpath("//*[text()='确认提交']")).click();
        String result=chromeDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/span/font[2]")).getText();
        Assert.assertEquals("申请审核中，请耐心等待！",result);
    }
    @Test
    public void 服务商审核企业的报销服务_通过() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/span")).click();
        chromeDriver.findElement(By.id("leftnav6")).click();
        chromeDriver.findElement(By.xpath("//*[text()='审批']")).click();
        chromeDriver.findElement(By.xpath("//*[text()='审核通过']")).click();
    }
    @Test
    public void 服务商审核企业的报销服务_失败() throws InterruptedException {
        proLogin();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/span")).click();
        chromeDriver.findElement(By.id("leftnav6")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[text()='审批']")).click();
        chromeDriver.findElement(By.xpath("//*[text()='退回']")).click();
        Thread.sleep(1000);
        chromeDriver.findElement(By.xpath("//*[@id=\"layui-layer1\"]/div[2]/textarea")).sendKeys("不符合");
        chromeDriver.findElement(By.xpath("//*[text()='退回服务']")).click();
    }
    @Test
    public void 企业查看报销服务状态_通过() throws InterruptedException {
        entLogin();
        //企业查看报销服务的审核状态
        chromeDriver.findElement(By.id("leftnav3")).click();//点击我的服务
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[2]")).getText();
        Assert.assertEquals("审核通过",result);
    }
    @Test
    public void 企业查看报销服务状态_失败() throws InterruptedException {
        entLogin();
        //企业查看报销服务的审核状态
        chromeDriver.findElement(By.id("leftnav3")).click();//点击我的服务
        String result=chromeDriver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[2]")).getText();
        Assert.assertEquals("审核未通过",result);
    }
}
