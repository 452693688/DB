package com.app.db.geography;


import com.app.db.OpenDB;

/**
 * P:province(省)； C:city(城市)； D:district(地区)； N：name(名称)；
 * a_c：administrative_code(行政代码)
 *
 */
public class ChinaTab {
	public static String INDEX = "orders";
	// 其他
	public static String OTHER = "other";
	/**
	 * 省分
	 */
	public static String TABLE_NAME_CHINA_P = "province_all";
	// 省份行政代码
	public static String P_A_C = "p_a_c";
	// 省份名称
	public static String P_N = "p_n";
	public static final String SQL_CREATE_TABLE_CHINA_P = OpenDB.SQL_CREATE_TABLE
			+ TABLE_NAME_CHINA_P
			+ "( "
			+ INDEX
			+ " TEXT,"
			+ P_A_C
			+ " primary key," + P_N + " TEXT," + OTHER + " TEXT " + ")";
	/**
	 * 城市
	 */
	public static String TABLE_NAME_CHINA_P_C = "province_city";
	// 城市行政代码
	public static String P_C_A_C = "p_c_a_c";
	// 城市名称
	public static String P_C_N = "p_c_n";
	public static final String SQL_CREATE_TABLE_CHINA_P_C = OpenDB.SQL_CREATE_TABLE
			+ TABLE_NAME_CHINA_P_C
			+ "( "
			+ INDEX
			+ " TEXT,"
			+ P_C_A_C
			+ " primary key,"
			+ P_C_N
			+ " TEXT,"
			+ P_A_C
			+ " TEXT,"
			+ OTHER
			+ " TEXT " + ")";
	/**
	 * 地区
	 */
	public static String TABLE_NAME_CHINA_P_C_D = "province_city_district";
	// 地区行政代码
	public static String P_C_D_A_C = "p_c_d_a_c";
	// 地区名称
	public static String P_C_D_N = "p_c_d_n";
	public static final String SQL_CREATE_TABLE_CHINA_P_C_D = OpenDB.SQL_CREATE_TABLE
			+ TABLE_NAME_CHINA_P_C_D
			+ "( "
			+ INDEX
			+ " TEXT,"
			+ P_C_D_A_C
			+ " primary key,"
			+ P_C_D_N
			+ " TEXT,"
			+ P_C_A_C
			+ " TEXT,"
			+ OTHER
			+ " TEXT " + ")";
	public static int I1_INDEX = 0;
	public static int I1_P_A_C = I1_INDEX + 1;
	public static int I1_P_N = I1_P_A_C + 1;
	public static int I1_OTHER = I1_P_N + 1;
	//
	public static int I2_INDEX = 0;
	public static int I2_P_C_A_C = I2_INDEX + 1;
	public static int I2_P_C_N = I2_P_C_A_C + 1;
	public static int I2_P_A_C = I2_P_C_N + 1;
	public static int I2_OTHER = I2_P_A_C + 1;
	//
	public static int I3_INDEX = 0;
	public static int I3_P_C_D_A_C = I2_INDEX + 1;
	public static int I3_P_C_D_N = I3_P_C_D_A_C + 1;
	public static int I3_P_C_A_C = I3_P_C_D_N + 1;
	public static int I3_OTHER = I3_P_C_A_C + 1;
}
