package com.app.service;

import com.app.dao.IAreaBikeDao;
import com.app.dao.IBikeLossDao;
import com.app.entity.AreaManager;
import com.app.iService.AreaBikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2018/4/24.
 */
@Service
public class AreaBikeServiceImpl implements AreaBikeService {


    @Resource
    private IAreaBikeDao areaBikeDaoImpl;
    @Override
    public List<AreaManager> getList(AreaManager param) {
        return areaBikeDaoImpl.getList(param);
    }

    @Override
    public Integer count(AreaManager param) {
        return areaBikeDaoImpl.count(param);
    }
}
