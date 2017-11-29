package com.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie的工具类
 * zhujiamin
 */
public class CookieUtils {

    Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * 获取cookie
     * @param request
     * @return
     */
    public Cookie[] getCookies(HttpServletRequest request){
        return request.getCookies();
    }


    /**
     * 设置cookie时间
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public void addCookie(HttpServletResponse response,String cookieName,String cookieValue){
        if (response==null||StringUtils.isEmpty(cookieName)||StringUtils.isEmpty(cookieValue)){
        throw new NullPointerException("response,cookieName,cookieValue不能为空");

    }
        Cookie cookie = new Cookie(cookieName,cookieValue);//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
    }

    //修改cookie，可以根据某个cookie的name修改它（不只是name要与被修改cookie一致，path、domain必须也要与被修改cookie一致）
    public void editCookie(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookies");
        } else {
            for(Cookie cookie : cookies){
                //迭代时如果发现与指定cookieName相同的cookie，就修改相关数据
                if(cookie.getName().equals("name_test")){
                    cookie.setValue("new_value");//修改value
                    cookie.setPath("/");
                    cookie.setMaxAge(10 * 60);// 修改存活时间
                    response.addCookie(cookie);//将修改过的cookie存入response，替换掉旧的同名cookie
                    break;
                }
            }
        }
    }

    //删除cookie
    public void delCookie(HttpServletRequest request,HttpServletResponse response,String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("没有cookie");
        } else {
            for (Cookie cookie : cookies) {
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if (cookie.getName().equals(name)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
