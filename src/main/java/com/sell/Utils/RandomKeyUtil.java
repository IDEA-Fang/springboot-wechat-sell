package com.sell.Utils;

import java.util.Random;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/7
 * 21:31
 * #
 * * 生成唯一的主键
 * * 格式: 时间+随机数
 */
public class RandomKeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer integer = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(integer);


}
}
