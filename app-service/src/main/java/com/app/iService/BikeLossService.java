package com.app.iService;

import com.app.entity.Bike;
import com.app.entity.BikeLoss;

import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
public interface BikeLossService {

    List<BikeLoss> getList(BikeLoss param);

    Integer count(BikeLoss param);

    int insert(BikeLoss param);

    int delete(BikeLoss param);

    int update(BikeLoss param);

    int batchDelete(List<Integer> param);
}
