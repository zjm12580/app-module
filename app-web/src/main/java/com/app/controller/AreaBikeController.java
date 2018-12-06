package com.app.controller;

import com.app.entity.AreaManager;
import com.app.entity.Bike;
import com.app.entity.PageDataTable;
import com.app.iService.AreaBikeService;
import com.app.iService.BikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2018/4/23.
 */
@Controller
public class AreaBikeController {

    @Resource
    private AreaBikeService areaBikeService;


    @RequestMapping("/app/areaManager")
    public String areaManager() {
        return "/AreaManage";
    }


    @RequestMapping("/app/getAreaManagerList")
    @ResponseBody
    public PageDataTable getAreaManagerList(AreaManager param) {

        List<AreaManager> bikeList = areaBikeService.getList(param);
        Integer count = areaBikeService.count(param);
        PageDataTable<AreaManager> res = new PageDataTable();
        res.setAaData(bikeList);
        res.setsEcho(0);
        res.setiTotalDisplayRecords(count);
        return res;
    }


}
