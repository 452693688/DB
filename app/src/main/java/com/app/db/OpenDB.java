package com.app.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.List;

public class OpenDB {
    /***
     * SQL语句
     */
    public static final String SQL_DELETE_TABLE = "drop table if exists ";
    public static final String SQL_CREATE_TABLE = "create table if not exists ";

    private static SQLiteDatabase db = null;

    // 打开数据库

    private static SQLiteDatabase openDB(File file) {
        if (db != null) {
            return db;
        }
        if (file == null || !file.exists()) {
            Log.e("数据库文件不存在", "--------");
            return db;
        }
        if (db == null) {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
        }
        return db;
    }

    public static SQLiteDatabase getDB(File file) {
        if (db == null) {
            db = openDB(file);
        }
        return db;
    }

    // 添加表
    public static void createTab(File file, String sql) {
        SQLiteDatabase dbs = openDB(file);
        if (dbs == null) {
            return;
        }
        dbs.execSQL(sql);
    }

    // 删除表
    public static void deleteTab(File file, String tabName) {
        SQLiteDatabase dbs = openDB(file);
        if (dbs == null) {
            return;
        }
        dbs.execSQL(SQL_DELETE_TABLE + tabName);
    }

    /***
     * 调用SQLiteDatabase对象的query方法进行查询，返回一个Cursor对象：由数据库查询返回的结果集对象
     *
     * @param table
     *            ：表名
     * @param columns
     *            :要查询的列名
     * @param selection
     *            ：查询条件
     * @param selectionArgs
     *            ：查询条件的参数
     * @param groupBy
     *            :对查询的结果进行分组
     * @param having
     *            ：对分组的结果进行限制
     * @param orderBy
     *            ：对查询的结果进行排序
     */
    public static Cursor query(File file, final String table,
                               final String[] columns, final String selection,
                               final String[] selectionArgs, final String groupBy,
                               final String having, final String orderBy) {
        SQLiteDatabase dbs = openDB(file);
        if (dbs == null) {
            return null;
        }
        return dbs.query(table, columns, selection, selectionArgs, groupBy,
                having, orderBy);

    }

    // 添加数据
    public static void insert(File file, String table, String nullColumnHack,
                              ContentValues values) {
        SQLiteDatabase dbs = openDB(file);
        if (dbs == null) {
            return;
        }
        dbs.insert(table, nullColumnHack, values);
    }

    public void inertBatch(File file, List<String> sqls) {
        SQLiteDatabase dbs = openDB(file);
        if (dbs == null) {
            return;
        }
        dbs.beginTransaction();
        try {
            for (String sql : sqls) {
                dbs.execSQL(sql);
            }
            dbs.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbs.endTransaction();
        }
    }

    /**
     * @param file      数据库文件名称
     * @param tableName 表格名称
     * @param list      插入数据库的数据
     */
    public static void inertBatch(File file, String tableName,
                                  List<ContentValues> list) {
        try {
            SQLiteDatabase dbs = openDB(file);
            if (dbs == null) {
                return;
            }
            dbs.beginTransaction();
            for (ContentValues v : list) {
                dbs.insert(tableName, null, v);
            }
            dbs.setTransactionSuccessful();
            dbs.endTransaction();
            Log.e("数据添加完成", "------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 关闭数据库
    public static void closeDB() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
}
