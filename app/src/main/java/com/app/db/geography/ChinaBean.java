package com.app.db.geography;

import android.text.TextUtils;

public class ChinaBean {
    /**
     * 省份行政代码
     */
    public String p_a_c;
    /**
     * 省份名称
     */
    public String p_n;
    /**
     * 城市行政代码
     */
    public String p_c_a_c;
    /**
     * 城市名称
     */
    public String p_c_n;
    /**
     * 地区行政代码
     */
    public String p_c_d_a_c;
    /**
     * 地区名称
     */
    public String p_c_d_n;
    public String orders;
    public String other;

    //获取名称
    public String getName() {

        return p_c_d_n;
    }

    //获取行政代码
    public String getCode() {
        if (!TextUtils.isEmpty(p_a_c)) {
            return p_a_c;
        }
        if (!TextUtils.isEmpty(p_c_a_c)) {
            return p_c_a_c;
        }
        return p_c_d_a_c;
    }

    public String provinceName() {
        p_n = p_n.replaceAll(" ", "");
        p_n = p_n.replaceAll("\u0000", "");
        return p_n;
    }
    public String provinceCode() {
        p_a_c = p_a_c.replaceAll(" ", "");
        p_a_c = p_a_c.replaceAll("\u0000", "");
        return p_a_c;
    }
    public String cityName() {
        p_c_n = p_c_n.replaceAll(" ", "");
        p_c_n = p_c_n.replaceAll("\u0000", "");
        return p_c_n;
    }
    public String cityCode() {
        p_c_a_c = p_c_a_c.replaceAll(" ", "");
        p_c_a_c = p_c_a_c.replaceAll("\u0000", "");
        return p_c_a_c;
    }
    public String districtName() {
        p_c_d_n = p_c_d_n.replaceAll(" ", "");
        p_c_d_n = p_c_d_n.replaceAll("\u0000", "");
        return p_c_d_n;
    }
    public String districtCode() {
        p_c_d_a_c = p_c_d_a_c.replaceAll(" ", "");
        p_c_d_a_c = p_c_d_a_c.replaceAll("\u0000", "");
        return p_c_d_a_c;
    }
}
