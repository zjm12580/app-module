package com.app.dao.impl;

import com.app.dao.IBikeLossDao;
import com.app.entity.BikeLoss;
import com.app.mapper.BikeLossMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
@Service
public class BikeLossDaoImpl implements IBikeLossDao {
    @Autowired
    private BikeLossMapper bikeLossMapper;

    @Override
    public List<BikeLoss> getList(BikeLoss param) {
        return bikeLossMapper.getList(param);
    }

    @Override
    public Integer count(BikeLoss param) {
        return bikeLossMapper.count(param);
    }

    @Override
    public int insert(BikeLoss param) {
        return bikeLossMapper.insert(param);
    }

    @Override
    public int delete(BikeLoss param) {
        return bikeLossMapper.delete(param);
    }

    @Override
    public int update(BikeLoss param) {
        return bikeLossMapper.update(param);
    }

    @Override
    public int batchDelete(List<Integer> param) {
        return bikeLossMapper.batchDelete(param);
    }
}
