package com.app.test;

import org.junit.Test;

import java.io.*;

/**
 *
 * 测试   原型模式（Prototype）
 * com.app.test
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/2
 */
public class DesignPatternsTest {

    @Test
    public void testClone() throws CloneNotSupportedException, IOException, ClassNotFoundException {

        Property property =new Property();
        property.setString("asdss");
        System.out.println(property.getString().hashCode());
        Property p1= (Property) property.clone();
        System.out.println(p1.getString().hashCode());
        p1.getString();
        p1 = (Property) property.deepClone();
        System.out.println(p1.getString().hashCode());
    }


}


class Property implements  Cloneable,Serializable{

    private String string;
    /**
     * 浅复制
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        Property property =(Property)super.clone();
        return property;
    }


    public Object deepClone()throws IOException, ClassNotFoundException{
        /**
         * 写入对象的二进制流
         */
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(this);
        /**
         * 读出二进制流产生的对象
         */
        ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bis);
        return ois.readObject();

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
