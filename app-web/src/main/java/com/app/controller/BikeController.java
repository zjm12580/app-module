package com.app.controller;

/**
 * Created by zhujiamin on 2017/12/12.
 */

import com.alibaba.druid.support.json.JSONUtils;
import com.app.entity.Bike;
import com.app.entity.PageDataTable;
import com.app.iService.BikeService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2017/11/2.
 */
@Controller
public class BikeController extends BaseController {
      @Resource
    private BikeService bikeService;
    @RequestMapping("/app/bikeManage")
    public String bikeManage() {
        return "/bikeManage";
    }

    @RequestMapping("/app/addBike")
    public String addBike() {
        return "/addBike";
    }

    @RequestMapping("/app/getBikes")
    @ResponseBody
    public PageDataTable getBikes(Bike param) {

        List<Bike> bikeList=bikeService.getBike(param);
        Integer count=bikeService.count(param);
        PageDataTable<Bike> res=new PageDataTable();
        res.setAaData(bikeList);
        res.setsEcho(1);
        res.setiTotalDisplayRecords(count);
        return res;
    }

    @RequestMapping("/app/add")
    @ResponseBody
    public Object add(Bike param) {
       return  bikeService.add(param);
    }

    @RequestMapping("/app/delete")
    @ResponseBody
    public Object deleteBike(Bike param){
        return bikeService.deleteBike(param);
    }

    @RequestMapping("/app/delete")
    @ResponseBody
    public Object updateBike(Bike param){
        return bikeService.updateBike(param);
    }
}
