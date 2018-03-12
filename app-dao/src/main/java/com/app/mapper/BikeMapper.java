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

    int add(Bike param);

    int deleteBike(Bike param);

    int updateBike(Bike param);

    int batchDelete(List<Integer> param);
}
