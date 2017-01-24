package com.app.db.geography;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/2.
 */

public class Place implements Serializable {
    //省份
    public String provinceName;
    public String provinceCode;
    //城市
    public String cityName;
    public String cityCode;
    //区
    public String districtName;
    public String districtCode;
    //xx省-xx市-xx区
    public String getPlaceName() {
        String tag = provinceName;
        if (!TextUtils.isEmpty(cityName) && !provinceName.equals(cityName)) {
            tag += "-" + cityName;
        }
        if (!TextUtils.isEmpty(districtName)) {
            tag += "-" + districtName;
        }
        tag.replaceAll(" ", "");
        return tag;
    }
    //xx省xx市xx区
    public String getNames() {
        String tag = provinceName;
        if (!TextUtils.isEmpty(cityName) && !provinceName.equals(cityName)) {
            tag += cityName;
        }
        if (!TextUtils.isEmpty(districtName)) {
            tag += districtName;
        }
        tag.replaceAll(" ", "");
        tag = tag.replaceAll("\u0000", "");
        return tag;
    }
}
