package com.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.app.db.OpenDB;
import com.app.db.geography.ChinaBean;
import com.app.db.geography.ChinaDao;
import com.app.db.geography.ChinaTab;
import com.app.db.table.F;
import com.app.db.table.ManagerData;
import com.app.db.table.TableCity;
import com.app.db.table.TableDistrict;
import com.app.db.table.TableProvince;
import com.example.administrator.db.R;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String fileDbName = "china_locality.db";
    private File file;
    private ChinaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.p_btn).setOnClickListener(this);
        findViewById(R.id.c_btn).setOnClickListener(this);
        findViewById(R.id.d_btn).setOnClickListener(this);
        findViewById(R.id.delet_btn).setOnClickListener(this);
        file = F.getFile(this, fileDbName, "china_locality.db", false);
        dao = new ChinaDao(file);
    }

    private ManagerData managerData;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.p_btn:
                //修改省份列表
                List<ChinaBean> provinces = dao.getProvince();
                OpenDB.createTab(file, TableProvince.SQL_CREATE_TABLE);
                if (managerData == null) {
                    managerData = new ManagerData(file);
                }
                // managerData.insertProvinces(provinces);
                Log.e("====", "数据库省份写入完成");
                break;
            case R.id.c_btn:
                //修改城市列表
                provinces = dao.getCity();
                OpenDB.createTab(file, TableCity.SQL_CREATE_TABLE);
                if (managerData == null) {
                    managerData = new ManagerData(file);
                }
                // managerData.insertCitys(provinces);
                Log.e("====", "数据库城市写入完成");
                break;
            case R.id.d_btn:
                //修改地区列表
                provinces = dao.getDistrict();
                OpenDB.createTab(file, TableDistrict.SQL_CREATE_TABLE);
                if (managerData == null) {
                    managerData = new ManagerData(file);
                }
              //  managerData.insertDistrict(provinces);
                Log.e("====", "数据库地区写入完成");
                break;
            case R.id.delet_btn:
                //删除旧表
                OpenDB.deleteTab(file, ChinaTab.TABLE_NAME_CHINA_P);
                OpenDB.deleteTab(file, ChinaTab.TABLE_NAME_CHINA_P_C);
                OpenDB.deleteTab(file, ChinaTab.TABLE_NAME_CHINA_P_C_D);
                Log.e("====", "数据库删除旧表完成");
                break;
        }
    }
}
