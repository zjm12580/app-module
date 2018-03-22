package com.app.task;

import com.app.dao.IBikeLossDao;
import com.app.emum.BikeLossPositionEnum;
import com.app.emum.BikeNameEnum;
import com.app.emum.BikeTypeEnum;
import com.app.emum.ExtentEnum;
import com.app.entity.BikeLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhujiamin on 2018/3/21.
 */
public class BikeLossTask {

    @Autowired
    private IBikeLossDao bikeLossDaoImpl;

    private static final String PIX = "APP";

    /**
     * 每分钟生成一个单子
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void execute() {
        try {
            java.util.Random random = new java.util.Random();// 定义随机类
            int result = random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
            int result1 = random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
            int result3 = random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10
            int result4 = random.nextInt(10);// 返回[0,10)集合中的整数，注意不包括10

            int i = (int) ((Math.random() * 9 + 1) * 100000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            BikeLoss bikeLoss = new BikeLoss();
            bikeLoss.setBikeNumber(PIX + sdf.format(new Date()) + i);
            bikeLoss.setStatus(1);
            bikeLoss.setActualCost((double) 20);
            bikeLoss.setName(BikeNameEnum.get(result3% 7));
            bikeLoss.setType(BikeTypeEnum.get(result4 % 7).getValue());
            bikeLoss.setCtime(new Date());
            bikeLoss.setExpectCost((double) 5);
            bikeLoss.setLossPosition(BikeLossPositionEnum.get(result % 4));
            bikeLoss.setLossSituation(ExtentEnum.get(result4%4).getValue());
            bikeLoss.setModifer(1L);
//            bikeLoss.setStatus(ExtentEnum.get(result1 % 3).getValue());
            bikeLossDaoImpl.insert(bikeLoss);
            //如果有先后顺序或依赖关系，可以在这里执行多个
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
