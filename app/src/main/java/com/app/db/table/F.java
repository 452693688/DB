package com.app.db.table;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/1/24.
 */

public class F {
    /***
     * SD卡根目录
     */
    public final static String CFG_PATH_SDCARD_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    //-----------------------------------------------------//
    /***
     *  dbs 的 SD卡根目录
     */
    public final static String CFG_PATH_SDCARD_DIR = CFG_PATH_SDCARD_ROOT
            + File.separator + "dbs";

    //测试文件路径
    private final static String CFG_PATH_SDCARD_TEXT = CFG_PATH_SDCARD_DIR + File.separator + "db";

    /***
     * @param fileName 写入sd卡的数据库名称
     * @param source 数据库文件源文件
     * @param isChange 数据库文件是否有修改
     * @return 写入sd卡的数据库文件
     */
    public static File getFile(Context context, String fileName, String source, boolean isChange) {
        String path = getLoactionBd(fileName);
        File file = new File(path);
        boolean exists = file.exists();
        if (isChange && exists) {
            file.delete();
        }
        if (exists) {
            return file;
        }
        try {
            // 读取文件 写入到sd卡
            AssetManager manager = context.getAssets();
            InputStream inputStream = manager.open(source);
            writeDB(inputStream, fileName);
        } catch (IOException e) {
             Log.e("写入失败", "-----------");
            e.printStackTrace();
            return null;
        }
        path = getLoactionBd(fileName);
        file = new File(path);
        exists = file.exists();
        if (exists) {
            return file;
        } else {
            return null;
        }
    }

    /** 写入数据库文件 */
    public static void writeDB(InputStream inputStream, String fileName) {
        String path = getLoactionBd(fileName);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int szie = 0;
            while ((szie = inputStream.read(buffer)) > 0) {
                fout.write(buffer);
            }
            fout.flush();
            inputStream.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream = null;
            fout = null;
        }
    }

    /** 数据库写入sd卡，获取写入地址 */
    public static String getLoactionBd(String fileName) {
        File file = new File(CFG_PATH_SDCARD_TEXT);
        if (!file.exists()) {
            file.mkdirs();
        }
        String path = CFG_PATH_SDCARD_TEXT + File.separator + fileName;
        return path;
    }
}
