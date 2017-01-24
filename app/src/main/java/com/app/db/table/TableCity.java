package com.app.db.table;

import com.app.db.OpenDB;

/**城市
 * Created by Administrator on 2017/1/24.
 */

public class TableCity {
    public static String index = "orders";
    // 其他
    public static String other = "other";

    public static String table_name = "citys";
    // 城市行政代码
    public static String city_code = "city_code";
    // 城市名称
    public static String city_name = "city_name";
    // 省份行政代码
    public static String province_code = "province_code";
    public static final String SQL_CREATE_TABLE = OpenDB.SQL_CREATE_TABLE
            + table_name
            + "( "
            + index
            + " TEXT,"
            + city_code
            + " primary key,"
            + city_name
            + " TEXT,"
            + province_code
            + " TEXT,"
            + other
            + " TEXT " + ")";
    public static int i_index = 0;
    public static int i_city_code = i_index + 1;
    public static int i_city_name = i_city_code + 1;
    public static int i_province_code = i_city_name + 1;
    public static int i_other = i_province_code + 1;
}
