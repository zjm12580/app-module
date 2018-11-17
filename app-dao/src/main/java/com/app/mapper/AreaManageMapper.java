package com.app.mapper;

import com.app.entity.AreaManager;
import com.app.entity.BikeLoss;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhujiamin on 2018/3/20.
 */
@Repository
public interface AreaManageMapper {
    public List<AreaManager> getList(AreaManager param);

    Integer count(AreaManager param);

    int insert(AreaManager param);

    int delete(AreaManager param);

    int update(AreaManager param);

    int batchDelete(List<Integer> param);
}
