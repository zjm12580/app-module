package com.app.mapper;

import com.app.entity.Bike;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
@Repository
public interface BikeMapper {

    public List<Bike> getBike(Bike param);

    Integer count(Bike param);

    Object add(Bike param);

    Object deleteBike(Bike param);

    Object updateBike(Bike param);
}
