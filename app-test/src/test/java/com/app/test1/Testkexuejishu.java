package com.app.test1;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * com.app.test1
 *
 * @author zhujiamin
 * @date 2017/9/12
 */
public class Testkexuejishu {

    @Test
    public void test(){
        BigDecimal bd = new BigDecimal("2.34234E+19");
        System.out.println(bd.toPlainString());

    }
}
