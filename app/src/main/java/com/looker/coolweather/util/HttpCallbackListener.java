package com.looker.coolweather.util;

/**
 * Created by looker on 2016/8/29.
 */
public interface HttpCallbackListener {
    void onFinish(String s);

    void onError(Exception e);
}
