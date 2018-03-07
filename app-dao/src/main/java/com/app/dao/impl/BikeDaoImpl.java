package com.app.dao.impl;

import com.app.dao.IBikeDao;
import com.app.entity.Bike;
import com.app.mapper.BikeMapper;
import com.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
@Service
public class BikeDaoImpl implements IBikeDao{

    @Autowired
    private BikeMapper bikeMapper;
    @Override
    public List<Bike> getBike(Bike param) {
        return bikeMapper.getBike(param);
    }

    @Override
    public Integer count(Bike param) {
        return bikeMapper.count(param);
    }

    @Override
    public Object add(Bike param) {
        return bikeMapper.add(param);
    }

    @Override
    public Object deleteBike(Bike param) {
        return bikeMapper.deleteBike(param);
    }

    @Override
    public Object updateBike(Bike param) {
        return bikeMapper.updateBike(param);
    }
}
