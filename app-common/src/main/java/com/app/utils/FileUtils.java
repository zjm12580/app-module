package com.app.utils;

import java.io.*;

/**
 * Created by zhujiamin on 2018/3/7.
 */
public class FileUtils {

    public static String savePic(InputStream inputStream, String fileName) {
        String path = System.getProperty( "user.dir" );
        System.out.println(path);
        //获取Tomcat服务器所在路径的最后一个文件目录
        String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("\\")+1,tomcat_path.length());
        //若最后一个文件目录为bin目录，则服务器为手动启动
        if(("bin").equals(bin_path)){//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin
            //获取保存上传图片的文件路径
            pic_path = tomcat_path.substring(0,System.getProperty( "user.dir" ).lastIndexOf("\\")) +"\\webapps"+"\\pic_file\\";
        }else{//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5
            pic_path = tomcat_path+"\\webapps"+"\\pic_file\\";
        }
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            path=tempFile.getPath() + File.separator + fileName;
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return path;
    }

}
