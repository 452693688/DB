package com.app.db.geography;

import android.content.Context;
import android.database.Cursor;

import com.app.db.OpenDB;
import com.app.db.table.F;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChinaDao {
    private String fileDbName = "china_locality.db";
    private File file;

    public ChinaDao(File file) {
        this.file = file;
    }

    /**
     * 将apk里的数据库文件写到sd卡
     */
    public ChinaDao(Context context) {
        file = F.getFile(context, fileDbName, "china_locality.db", false);
    }

    /**
     * 获取省份
     */
    public List<ChinaBean> getProvince() {
        Cursor cursor = OpenDB.query(file, ChinaTab.TABLE_NAME_CHINA_P, null,
                null, null, null, null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 1);
        return list;
    }

    public List<ChinaBean> getCity() {
        Cursor cursor = OpenDB.query(file, ChinaTab.TABLE_NAME_CHINA_P_C, null,
                null, null, null, null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 2);
        return list;
    }
    public List<ChinaBean> getDistrict() {
        Cursor cursor = OpenDB.query(file, ChinaTab.TABLE_NAME_CHINA_P_C_D, null,
                null, null, null, null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 3);
        return list;
    }
    /**
     * @param p_a_c 省份行政码
     * @return
     */
    public List<ChinaBean> getProvinceByAC(String p_a_c) {
        Cursor cursor = OpenDB.query(file, ChinaTab.TABLE_NAME_CHINA_P, null,
                ChinaTab.P_A_C + "=?", new String[]{p_a_c}, null, null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 1);
        return list;
    }

    /**
     * 根据省份行政码获取城市
     */
    public List<ChinaBean> getCity(String p_a_c) {
        Cursor cursor = OpenDB
                .query(file, ChinaTab.TABLE_NAME_CHINA_P_C, null,
                        ChinaTab.P_A_C + "=?", new String[]{p_a_c}, null,
                        null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 2);
        return list;
    }

    /**
     * @param p_a_c 城市行政编码
     * @return
     */
    public List<ChinaBean> getCityByAC(String p_c_a_c) {
        Cursor cursor = OpenDB
                .query(file, ChinaTab.TABLE_NAME_CHINA_P_C, null,
                        ChinaTab.P_C_A_C + "=?", new String[]{p_c_a_c}, null,
                        null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 2);
        return list;
    }

    /**
     * 获取城市名称获取城市
     */
    public List<ChinaBean> getCityByName(String cityName) {
        Cursor cursor = OpenDB
                .query(file, ChinaTab.TABLE_NAME_CHINA_P_C, null,
                        ChinaTab.P_C_N + " like ? ", new String[]{"%" + cityName + "%"}, null,
                        null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 2);
        return list;
    }

    /**
     * 获取区
     */
    public List<ChinaBean> getCounty(String p_c_a_c) {
        Cursor cursor = OpenDB.query(file, ChinaTab.TABLE_NAME_CHINA_P_C_D,
                null, ChinaTab.P_C_A_C + "=?", new String[]{p_c_a_c}, null,
                null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 3);
        return list;
    }

    /**
     * 获取地区名称获取地区
     */
    public List<ChinaBean> getCountyByName(String districtName) {
        Cursor cursor = OpenDB
                .query(file, ChinaTab.TABLE_NAME_CHINA_P_C_D, null,
                        ChinaTab.P_C_D_N + " like ? ", new String[]{"%" + districtName + "%"}, null,
                        null, null);
        ArrayList<ChinaBean> list = readCursor(cursor, 3);
        return list;
    }

    /***
     * @param cursor
     * @param type
     *            1:省份；2:城市；3:地区；
     * @return
     */
    private ArrayList<ChinaBean> readCursor(Cursor cursor, int type) {
        ArrayList<ChinaBean> list = new ArrayList<ChinaBean>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ChinaBean bean = new ChinaBean();
                switch (type) {
                    case 1:
                        bean.orders = cursor.getString(ChinaTab.I1_INDEX);
                        bean.p_a_c = cursor.getString(ChinaTab.I1_P_A_C);
                        bean.p_n = cursor.getString(ChinaTab.I1_P_N);
                        bean.other = cursor.getString(ChinaTab.I1_OTHER);
                        break;

                    case 2:
                        bean.orders = cursor.getString(ChinaTab.I2_INDEX);
                        bean.p_c_a_c = cursor.getString(ChinaTab.I2_P_C_A_C);
                        bean.p_c_n = cursor.getString(ChinaTab.I2_P_C_N);
                        bean.p_a_c = cursor.getString(ChinaTab.I2_P_A_C);
                        bean.other = cursor.getString(ChinaTab.I2_OTHER);
                        break;
                    case 3:
                        bean.orders = cursor.getString(ChinaTab.I3_INDEX);
                        bean.p_c_d_a_c = cursor.getString(ChinaTab.I3_P_C_D_A_C);
                        bean.p_c_d_n = cursor.getString(ChinaTab.I3_P_C_D_N);
                        bean.p_c_a_c = cursor.getString(ChinaTab.I3_P_C_A_C);
                        bean.other = cursor.getString(ChinaTab.I3_OTHER);
                        break;
                }
                list.add(bean);
            }
            cursor.close();
        }
        return list;
    }

    // 关闭数据库
    public void close() {
        OpenDB.closeDB();
    }
}
