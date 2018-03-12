package com.app.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 保存文件
 * Created by zhujiamin on 2018/3/7.
 */
public class FileUtils {

    private static final String PIC_FILE_NAME = "\\pic_file\\";
    public static String path="";
    public static String pic_path="";

    static{
        path = System.getProperty("user.dir");
        System.out.println(path);
        String pic_path = "";
        //获取Tomcat服务器所在路径的最后一个文件目录
        String bin_path = path.substring(path.lastIndexOf("\\") + 1, path.length());
        //若最后一个文件目录为bin目录，则服务器为手动启动
        if (("bin").equals(bin_path)) {//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin
            //获取保存上传图片的文件路径
            pic_path = path.substring(0, System.getProperty("user.dir").lastIndexOf("\\")) + "\\webapps" + PIC_FILE_NAME;
        } else {//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5
            pic_path = path + "\\webapps" + "\\pic_file\\";
        }
        System.out.println(pic_path);
    }

    /**
     * 上传文件
     * @param inputStream
     * @param fileName
     * @return
     */
    public static String savePic(InputStream inputStream, String fileName) {
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(pic_path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            path = tempFile.getPath() + File.separator + fileName;
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
        return PIC_FILE_NAME + fileName;
    }

    /**
     * 读取文件
     * @param fileId
     * @param response
     * @throws IOException
     */
    public static void readFile(String fileId, HttpServletResponse response) throws IOException {
        FileInputStream hFile = new FileInputStream(pic_path+fileId); // 以byte流的方式打开文件
        int i = hFile.available(); // 得到文件大小
        byte data[] = new byte[i];
        hFile.read(data); // 读数据
        hFile.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        toClient.write(data); // 输出数据
        toClient.close();
    }
}
