package com.app.mapper;

import com.app.entity.Bike;
import com.app.entity.BikeLoss;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhujiamin on 2018/3/20.
 */
@Repository
public interface BikeLossMapper {
    public List<BikeLoss> getList(BikeLoss param);

    Integer count(BikeLoss param);

    int insert(BikeLoss param);

    int delete(BikeLoss param);

    int update(BikeLoss param);

    int batchDelete(List<Integer> param);
}
