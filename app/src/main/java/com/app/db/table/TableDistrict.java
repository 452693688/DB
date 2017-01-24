package com.app.db.table;

import com.app.db.OpenDB;

/**
 * 地区
 * Created by Administrator on 2017/1/24.
 */

public class TableDistrict {
    public static String INDEX = "orders";
    // 其他
    public static String OTHER = "other";
    public static String table_name = "districts";
    // 地区行政代码
    public static String district_code = "district_code";
    // 地区名称
    public static String district_name = "district_name";
    // 城市行政代码
    public static String city_code = "city_code";

    public static final String SQL_CREATE_TABLE = OpenDB.SQL_CREATE_TABLE
            + table_name
            + "( "
            + INDEX
            + " TEXT,"
            + district_code
            + " primary key,"
            + district_name
            + " TEXT,"
            + city_code
            + " TEXT,"
            + OTHER
            + " TEXT " + ")";
    public static int i_index = 0;
    public static int i_district_code = i_index + 1;
    public static int i_district_name = i_district_code + 1;
    public static int i_city_code = i_district_name + 1;
    public static int i_other = i_city_code + 1;
}
