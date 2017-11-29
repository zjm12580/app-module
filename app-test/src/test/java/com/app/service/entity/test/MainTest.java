package com.app.service.entity.test;


import com.app.entity.PageParameter;
import com.app.entity.Property;
import com.app.iService.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * com.app.test
 *
 * @author zhujiamin
 * @date 2017/7/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:ApplicationContext.xml"})
public class MainTest {

    @Resource
    private IUserService iUser;


    @Test
    public void testPageparameter(){
        PageParameter pageParameter=new PageParameter();
        Property property=new Property();
        List<Property> properties=new ArrayList<>();
        property.setName("user_name");
        property.setType("String");
        property.setValue("zjm");
        property.setCompareType("equals");
        properties.add(property);
        pageParameter.setProperties(properties);
        pageParameter.setPageSize(10);
        pageParameter.setPageNumber(0);
        pageParameter.setStart(0);
        System.out.println(pageParameter.getPageSize());
        System.out.printf(iUser.getUser(pageParameter).toString());
        System.out.printf(pageParameter.getCount()+"");

    }

    @Test
    public void say() throws InterruptedException {
//        while(true){
//            Thread.sleep(1000);
//            System.out.println("1243");
//        }
    }
}
