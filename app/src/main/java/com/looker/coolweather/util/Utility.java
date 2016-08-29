package com.looker.coolweather.util;

import android.text.TextUtils;

import com.looker.coolweather.model.City;
import com.looker.coolweather.model.CoolWeatherDB;
import com.looker.coolweather.model.County;
import com.looker.coolweather.model.Province;

/**
 * Created by looker on 2016/8/29.
 */
public class Utility {

    /*
    * 由于省市县数据都是“代号|城市， 代号|城市”，所以需要一个工具类来解析此类数据
    *
    * 解析和处理雾浮起返回的省级数据
    * */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces !=null && allProvinces.length > 0){
                for (String p : allProvinces
                     ) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /*
    * 解析和处理服务器返回的市级数据
    * */
    public static boolean handleCitiesResponse(CoolWeatherDB coolweatherDB, String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities
                     ) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    coolweatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /*
    * 解析和处理服务器返回的县级数据
    * */
    public static boolean handleCountiesResponse(CoolWeatherDB coolweatherDB, String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties
                     ) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到County表
                    coolweatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
