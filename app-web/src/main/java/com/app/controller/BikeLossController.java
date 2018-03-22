package com.app.controller;

/**
 * Created by zhujiamin on 2017/12/12.
 */

import com.alibaba.druid.support.json.JSONUtils;
import com.app.entity.Bike;
import com.app.entity.BikeLoss;
import com.app.entity.PageDataTable;
import com.app.iService.BikeLossService;
import com.app.iService.BikeService;
import com.app.utils.FileUtils;
import com.app.utils.JsonUtils;
import com.app.utils.ResultMap;
import com.sun.org.apache.regexp.internal.RE;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by zhujiamin on 2017/11/2.
 */
@Controller
public class BikeLossController extends BaseController {

    @Resource
    private BikeLossService bikeLossService;

    @RequestMapping("/bikeLoss/bikeLossManage")
    public String bikeLossManage() {
        return "/bikeLossManage";
    }

    @RequestMapping("/bikeLoss/batchDelete")
    @ResponseBody
    public Object batchDeletes(@RequestBody() String ids) {
        List<Integer> idList = JsonUtils.parseObject(ids, List.class);
        if (idList == null || idList.size() == 0) {
            return ResultMap.failed("所选数据为空");
        }
        try {
            if (bikeLossService.batchDelete(idList) > 0) {
                return ResultMap.success();
            } else {
                return ResultMap.failed("删除失败");
            }
        } catch (Exception e) {
            return ResultMap.failed("删除异常，请重试");
        }
    }

    @RequestMapping("/bikeLoss/repaired")
    @ResponseBody
    public Object repaired(BikeLoss param) {
        param.setStatus(0);
        if (bikeLossService.update(param) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.failed("更新失败");
        }
    }

    @RequestMapping("/bikeLoss/getBikeLoss")
    @ResponseBody
    public PageDataTable getBikeLoss(BikeLoss param) {

        List<BikeLoss> bikeList = bikeLossService.getList(param);
        Integer count = bikeLossService.count(param);
        PageDataTable<BikeLoss> res = new PageDataTable();
        res.setAaData(bikeList);
        res.setsEcho(0);
        res.setiTotalDisplayRecords(count);
        return res;
    }


    @RequestMapping("/bikeLoss/delete")
    @ResponseBody
    public Object deleteBike(BikeLoss param) {
        if (bikeLossService.delete(param) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.failed("删除失败");
        }
    }
}
