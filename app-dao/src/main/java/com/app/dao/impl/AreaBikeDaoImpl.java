package com.app.dao.impl;

import com.app.dao.IAreaBikeDao;
import com.app.entity.AreaManager;
import com.app.entity.Bike;
import com.app.mapper.AreaManageMapper;
import com.app.mapper.BikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhujiamin on 2018/4/24.
 */
@Service
public class AreaBikeDaoImpl implements IAreaBikeDao {

    @Autowired
    private AreaManageMapper areaManageMapper;

    @Override
    public List<AreaManager> getList(AreaManager param) {
        return areaManageMapper.getList(param);
    }

    @Override
    public Integer count(AreaManager param) {
        return areaManageMapper.count(param);
    }
}
