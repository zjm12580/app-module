package com.app.service;

import com.app.dao.IBikeLossDao;
import com.app.entity.BikeLoss;
import com.app.iService.BikeLossService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhujiamin on 2018/2/25.
 */
@Service
public class BIkeLossServiceImpl implements BikeLossService{

    @Resource
    private IBikeLossDao bikeLossDaoImpl;

    @Override
    public List<BikeLoss> getList(BikeLoss param) {
        return  bikeLossDaoImpl.getList(param);
    }

    @Override
    public Integer count(BikeLoss param) {
        return  bikeLossDaoImpl.count(param);
    }

    @Override
    public int insert(BikeLoss param) {
        return  bikeLossDaoImpl.insert(param);
    }

    @Override
    public int delete(BikeLoss param) {
        return  bikeLossDaoImpl.delete(param);
    }

    @Override
    public int update(BikeLoss param) {
        return  bikeLossDaoImpl.update(param);
    }

    @Override
    public int batchDelete(List<Integer> param) {
        return  bikeLossDaoImpl.batchDelete(param);
    }
}
