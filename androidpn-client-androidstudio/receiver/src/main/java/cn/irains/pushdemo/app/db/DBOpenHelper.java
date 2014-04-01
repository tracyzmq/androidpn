package cn.irains.pushdemo.app.db;/**
 * Created by msdx on 2014/3/26.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.androidpn.client.LogUtil;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-03-26
 * Time: 13:06
 * FIXME
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = LogUtil.makeLogTag(DBOpenHelper.class);

    public DBOpenHelper(Context context) {
        super(context, DBConsts.DB_NAME, null, DBConsts.DB_VERSION);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(LOG_TAG, "create database");
        Log.i(LOG_TAG, DBConsts.CREATE_TABLE_IQ);
        db.execSQL(DBConsts.CREATE_TABLE_IQ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
