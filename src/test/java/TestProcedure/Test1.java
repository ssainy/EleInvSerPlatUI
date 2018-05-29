package TestProcedure;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumutils.ExcelData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cch on 2017/5/25.
 */
public class Test1 {
    @DataProvider(name = "data")
    public Iterator<Object[]> telright() throws Exception {
        return (Iterator<Object[]>) new ExcelData("服务商品台批量注册申请", "Sheet2");
    }
    @Test(dataProvider = "data")
    public void test(Map<String, String> data) throws Exception {
        String NSRSBH = data.get("纳税人识别号");
        String NSRMC = data.get("纳税人名称");
        String username=data.get("企业用户名");
        String phone=data.get("手机号");
        Test_EnterprisePro.企业注册(username,phone);
        Test_EnterprisePro.企业三证合一认证(NSRMC,NSRSBH,username);
        Test_EnterprisePro.企业申请开通开票服务_服务器版(username);
      //  Test_EnterprisePro.服务商审核企业的开票服务_通过();

    }
}
