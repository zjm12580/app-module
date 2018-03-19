package com.app.controller;

/**
 * Created by zhujiamin on 2017/12/12.
 */

import com.alibaba.druid.support.json.JSONUtils;
import com.app.entity.Bike;
import com.app.entity.PageDataTable;
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
public class BikeController extends BaseController {
    @Resource
    private BikeService bikeService;

    @RequestMapping("/app/bikeLossManage")
    public String bikeLossManage() {
        return "/bikeLossManage";
    }

    @RequestMapping("/app/bikeManage")
    public String bikeManage() {
        return "/bikeManage";
    }


    @RequestMapping("/app/updateBikeIndex")
    public String updateBikeIndex(Long id, Model model) {
        Bike param = new Bike();
        param.setId(id);
        List<Bike> bikeList = bikeService.getBike(param);
        if (bikeList != null && bikeList.size() > 0) {
            model.addAttribute("bike", bikeList.get(0));
        }
        return "/updateBikeIndex";
    }

    @RequestMapping("/app/addBike")
    public String addBike() {
        return "/addBike";
    }

    @RequestMapping("/app/getBikes")
    @ResponseBody
    public PageDataTable getBikes(Bike param) {

        List<Bike> bikeList = bikeService.getBike(param);
        Integer count = bikeService.count(param);
        PageDataTable<Bike> res = new PageDataTable();
        res.setAaData(bikeList);
        res.setsEcho(0);
        res.setiTotalDisplayRecords(count);
        return res;
    }

    @RequestMapping("/app/add")
    @ResponseBody
    public Object add(Bike param, @RequestParam("file") MultipartFile file) throws Exception {
        try {
            if (param == null) {
                throw new Exception("param为空");
            }
            if (file == null || file.getSize() == 0) {
                throw new Exception("上传文件为空");
            }
            param.setPictureUrl(file.getOriginalFilename());
            param.setIsdel(0);
            param.setCtime(new Date());
            param.setMtime(new Date());
            bikeService.add(param);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }

        return ResultMap.success("新增成功");
    }

    @RequestMapping("/app/delete")
    @ResponseBody
    public Object deleteBike(Bike param) {
        if (bikeService.deleteBike(param) > 0) {
            return ResultMap.success();
        } else {
            return ResultMap.failed("删除失败");
        }
    }


    @RequestMapping("/app/batchDelete")
    @ResponseBody
    public Object batchDeletes(@RequestBody() String ids) {
        List<Integer> idList = JsonUtils.parseObject(ids, List.class);
        if (idList == null || idList.size() == 0) {
            return ResultMap.failed("所选数据为空");
        }
        try {
            if (bikeService.batchDelete(idList) > 0) {
                return ResultMap.success();
            } else {
                return ResultMap.failed("删除失败");
            }
        } catch (Exception e) {
            return ResultMap.failed("删除异常，请重试");
        }
    }

    @RequestMapping("/app/updateBike")
    @ResponseBody
    public Object updateBike(Bike param) {
        try {
            bikeService.updateBike(param);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("更新成功");
    }

    @RequestMapping("/app/downloadPhoto")
    public void downloadPhoto(String fileId, HttpServletResponse response) throws IOException {
        FileUtils.readFile(fileId, response);
    }
}
