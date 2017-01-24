package com.app.db.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.app.db.OpenDB;
import com.app.db.geography.ChinaBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */

public class ManagerData {
    private String fileDbName = "china_locality.db";
    private File file;

    public ManagerData(File file) {
        this.file = file;
    }

    /**
     * 将apk里的数据库文件写到sd卡
     */
    public ManagerData(Context context) {
        file = F.getFile(context, fileDbName, "china_locality.db", false);
    }

    // 关闭数据库
    public void close() {
        OpenDB.closeDB();
    }

    //插入省份
    public void insertProvinces(List<ChinaBean> list) {
        List<ContentValues> valuess = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ChinaBean province = list.get(i);
            ContentValues values = new ContentValues();
            values.put(TableProvince.INDEX, String.valueOf(i + 1));
            values.put(TableProvince.province_name, province.provinceName());
            values.put(TableProvince.province_code, province.provinceCode());
            valuess.add(values);
        }
        OpenDB.inertBatch(file, TableProvince.table_name, valuess);
    }

    //插入城市
    public void insertCitys(List<ChinaBean> list) {
        List<ContentValues> valuess = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ChinaBean province = list.get(i);
            ContentValues values = new ContentValues();
            values.put(TableCity.index, String.valueOf(i + 1));
            values.put(TableCity.city_name, province.cityName());
            values.put(TableCity.city_code, province.cityCode());
            values.put(TableCity.province_code, province.provinceCode());
            valuess.add(values);
        }
        OpenDB.inertBatch(file, TableCity.table_name, valuess);
    }

    //插入地区
    public void insertDistrict(List<ChinaBean> list) {
        List<ContentValues> valuess = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ChinaBean province = list.get(i);
            ContentValues values = new ContentValues();
            values.put(TableDistrict.INDEX, String.valueOf(i + 1));
            values.put(TableDistrict.district_name, province.districtName());
            values.put(TableDistrict.district_code, province.districtCode());
            values.put(TableDistrict.city_code, province.cityCode());
            valuess.add(values);
        }
        OpenDB.inertBatch(file, TableDistrict.table_name, valuess);
    }

    //  获取省份
    public List<AddressBean> getProvince() {
        Cursor cursor = OpenDB.query(file, TableProvince.table_name, null,
                null, null, null, null, null);
        ArrayList<AddressBean> list = readCursor(cursor, 1);
        return list;
    }

    //  获取城市
    public List<AddressBean> getCity() {
        Cursor cursor = OpenDB.query(file, TableCity.table_name, null,
                null, null, null, null, null);
        ArrayList<AddressBean> list = readCursor(cursor, 2);
        return list;
    }

    //  获取地区
    public List<AddressBean> getDistrict() {
        Cursor cursor = OpenDB.query(file, TableDistrict.table_name, null,
                null, null, null, null, null);
        ArrayList<AddressBean> list = readCursor(cursor, 3);
        return list;
    }

    //  根据省份行政码获取城市
    public List<AddressBean> getCitys(String provinceCode) {
        Cursor cursor = OpenDB
                .query(file, TableCity.table_name, null,
                        TableCity.province_code + "=?", new String[]{provinceCode}, null,
                        null, null);
        ArrayList<AddressBean> list = readCursor(cursor, 2);
        return list;
    }

    //  根据城市行政码获取地区
    public List<AddressBean> getDistricts(String cityCode) {
        Cursor cursor = OpenDB.query(file, TableDistrict.table_name,
                null, TableDistrict.city_code + "=?", new String[]{cityCode}, null,
                null, null);
        ArrayList<AddressBean> list = readCursor(cursor, 3);
        return list;
    }

    /***
     * @param cursor
     * @param type
     *            1:省份；2:城市；3:地区；
     * @return
     */
    private ArrayList<AddressBean> readCursor(Cursor cursor, int type) {
        ArrayList<AddressBean> list = new ArrayList<AddressBean>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                AddressBean bean = new AddressBean();
                switch (type) {
                    case 1:
                        bean.index = cursor.getString(TableProvince.i_index);
                        bean.provinceCode = cursor.getString(TableProvince.i_province_code);
                        bean.provinceName = cursor.getString(TableProvince.i_province_name);
                        bean.other = cursor.getString(TableProvince.i_other);
                        break;

                    case 2:
                        bean.index = cursor.getString(TableCity.i_index);
                        bean.cityCode = cursor.getString(TableCity.i_city_code);
                        bean.cityNmae = cursor.getString(TableCity.i_city_name);
                        bean.provinceCode = cursor.getString(TableCity.i_province_code);
                        bean.other = cursor.getString(TableCity.i_other);
                        break;
                    case 3:
                        bean.index = cursor.getString(TableDistrict.i_index);
                        bean.districtCode = cursor.getString(TableDistrict.i_district_code);
                        bean.districtName = cursor.getString(TableDistrict.i_district_name);
                        bean.cityCode = cursor.getString(TableDistrict.i_city_code);
                        bean.other = cursor.getString(TableDistrict.i_other);
                        break;
                }
                list.add(bean);
            }
            cursor.close();
        }
        return list;
    }

}
