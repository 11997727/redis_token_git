package com.example.redis_token.utils;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;

import java.io.IOException;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: redis_token
 * @Package com.example.redis_token.utils
 * @Description:
 * @date 2020/1/5 星期日 09:55
 */
public class UserAgentUtil {
    public static UASparser uaSparser=null;
    static {
        try {
            uaSparser= new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
