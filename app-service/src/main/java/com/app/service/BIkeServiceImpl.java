package com.app.service;

import com.app.dao.IBikeDao;
import com.app.dao.IUserDao;
import com.app.entity.Bike;
import com.app.iService.BikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
@Service
public class BIkeServiceImpl implements BikeService{


    @Resource
    private IBikeDao bikeDaoImpl;
    @Resource
    private IUserDao userDaoImpl;

    @Override
    public List<Bike> getBike(Bike param) {
        return bikeDaoImpl.getBike(param);
    }

    @Override
    public Integer count(Bike param) {
        return bikeDaoImpl.count(param);
    }

    @Override
    public Object add(Bike param) {
        return bikeDaoImpl.add(param);
    }

    @Override
    public Object deleteBike(Bike param) {
        return bikeDaoImpl.deleteBike(param);
    }

    @Override
    public Object updateBike(Bike param) {
        return bikeDaoImpl.updateBike(param);
    }
}
