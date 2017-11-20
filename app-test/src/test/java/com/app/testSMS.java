package com.app;

import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;
import org.junit.Test;

/**
 * Created by zhujiamin on 2017/10/30.
 */
public class testSMS {

    /**
     *
     */
    @Test
    public void testSend() {
        //创建全局参数
        GlobalParams globalParams = new GlobalParams();
        //设置请求路径
        globalParams.setRequestPath("/sms/v2/std/");
        //设置是否需要日志 1:需要日志;0:不需要日志
        globalParams.setNeedLog(1);
        //设置全局参数
        ConfigManager.setGlobalParams(globalParams);
        setAccountInfo();
        // 是否保持长连接 false:否;true:是
        boolean isKeepAlive = false;
        // 实例化短信处理对象
        SmsSendConn smsSendConn = new SmsSendConn(isKeepAlive);
        singleSend(smsSendConn, null);

    }


    public static void setAccountInfo() {

//        sms.mont.username=J02602
//        sms.mont.password=251005
        // 设置用户账号信息
        // 用户账号
        String userid = "J02602";
        // 密码
        String password = "251005";
        // 发送优先级
        int priority = 1;
        // 主IP信息，请前往您的控制台获取请求域名(IP)或联系梦网客服进行获取
        String ipAddress1 = "api01.monyun.cn:7901";
        // 备用IP1信息
        String ipAddress2 = "192.169.1.189:8086";
        // 备用IP2信息
        String ipAddress3 = null;
        // 备用IP3信息
        String ipAddress4 = null;
        // 返回值
        int result = -310007;
        try {
            // 设置用户账号信息
            result = ConfigManager.setAccountInfo(userid, password, priority,
                    ipAddress1, ipAddress2, ipAddress3, ipAddress4);
            // 判断返回结果，0设置成功，否则失败
            if (result == 0) {
                System.out.println("设置用户账号信息成功！");
            } else {
                System.out.println("设置用户账号信息失败，错误码：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }


    /**
     * @param smsSendConn 短信处理对象,在这个方法中调用发送短信功能
     * @param userid      用户账号
     * @description 单条发送
     */
    public static void singleSend(SmsSendConn smsSendConn, String userid) {
        try {
            // 参数类
            Message message = new Message();
            // 设置用户账号 指定用户账号发送，需要填写用户账号，不指定用户账号发送，无需填写用户账号
            message.setUserid(userid);
            // 设置手机号码 此处只能设置一个手机号码
            message.setMobile("13758221369");
            // 设置内容
            message.setContent("验证码：6666，打死都不要告诉别人哦！");
            // 设置扩展号
            message.setExno("");
            // 用户自定义流水编号
            message.setCustid("");
            // 自定义扩展数据
            message.setExdata("");
            // 业务类型
            message.setSvrtype("");
            // 返回的流水号
            StringBuffer returnValue = new StringBuffer();
            // 返回值
            int result = -310099;
            // 发送短信
            result = smsSendConn.singleSend(message, returnValue);
            // result为0:成功
            if (result == 0) {
                System.out.println("单条发送提交成功！");
                System.out.println(returnValue.toString());
            }
            // result为非0：失败
            else {
                System.out.println("单条发送提交失败,错误码：" + result);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
        }
    }
}
