package com.app.iService;

import com.app.entity.AreaManager;
import com.app.entity.Bike;

import java.util.List;

/**
 * Created by zhujiamin on 2018/4/24.
 */
public interface AreaBikeService {

    public List<AreaManager> getList(AreaManager param);

    Integer count(AreaManager param);
}
