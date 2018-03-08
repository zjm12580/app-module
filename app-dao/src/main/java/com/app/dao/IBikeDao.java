package com.app.dao;

import com.app.entity.Bike;

import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
public interface IBikeDao {

    List<Bike> getBike(Bike param);

    Integer count(Bike param);

    int add(Bike param);

    int deleteBike(Bike param);

    int updateBike(Bike param);
}
