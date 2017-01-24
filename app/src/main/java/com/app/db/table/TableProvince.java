package com.app.db.table;

import com.app.db.OpenDB;

/**
 * 省分
 * Created by Administrator on 2017/1/24.
 */

public class TableProvince {
    public static String INDEX = "orders";
    // 其他
    public static String OTHER = "other";

    public static String table_name = "provinces";
    // 省份行政代码
    public static String province_code = "province_code";
    // 省份名称
    public static String province_name = "province_name";
    public static final String SQL_CREATE_TABLE = OpenDB.SQL_CREATE_TABLE
            + table_name
            + "( "
            + INDEX
            + " TEXT,"
            + province_code
            + " primary key," + province_name + " TEXT," + OTHER + " TEXT " + ")";
    public static int i_index = 0;
    public static int i_province_code = i_index + 1;
    public static int i_province_name = i_province_code + 1;
    public static int i_other = i_province_name + 1;
}
