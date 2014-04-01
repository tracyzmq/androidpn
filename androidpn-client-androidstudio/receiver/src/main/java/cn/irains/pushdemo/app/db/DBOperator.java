package cn.irains.pushdemo.app.db;/**
 * Created by msdx on 2014/3/26.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.irains.pushdemo.app.notify.NotifyIQ;

/**
 * User: Geek_Soledad(msdx.android@qq.com)
 * Date: 2014-03-26
 * Time: 13:25
 * FIXME
 */
public class DBOperator {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase dbReader;
    public DBOperator(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void saveIQ(NotifyIQ iq) {
        ContentValues cv = new ContentValues();
        cv.put(DBConsts.COL_ID, iq.getId());
        cv.put(DBConsts.COL_APIKEY, iq.getApiKey());
        cv.put(DBConsts.COL_TITLE, iq.getTitle());
        cv.put(DBConsts.COL_MSG, iq.getMessage());
        cv.put(DBConsts.COL_URI, iq.getUri());
        SQLiteDatabase dbWriter = dbOpenHelper.getWritableDatabase();
        dbWriter.insert(DBConsts.TABLE_IQ, null, cv);
        dbWriter.close();;
    }

    public Cursor queryAll() {
        dbReader = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(DBConsts.QUERY_ALL_IQ, null);
        cursor.moveToFirst();
        return cursor;
    }

    public List<NotifyIQ> queryAllIQS() {
        SQLiteDatabase dbReader = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(DBConsts.QUERY_ALL_IQ, null);
        ArrayList<NotifyIQ> notifyIQs = new ArrayList<NotifyIQ>();
        final int idIndex = cursor.getColumnIndex(DBConsts.COL_ID);
        final int keyIndex = cursor.getColumnIndex(DBConsts.COL_APIKEY);
        final int msgIndex = cursor.getColumnIndex(DBConsts.COL_MSG);
        final int titleIndex = cursor.getColumnIndex(DBConsts.COL_TITLE);
        final int uriIndex = cursor.getColumnIndex(DBConsts.COL_URI);
        while(cursor.moveToNext()){
            NotifyIQ iq = new NotifyIQ();
            iq.setId(cursor.getString(idIndex));
            iq.setApiKey(cursor.getString(keyIndex));
            iq.setMessage(cursor.getString(msgIndex));
            iq.setTitle(cursor.getString(titleIndex));
            iq.setUri(cursor.getString(uriIndex));
            notifyIQs.add(iq);
        }
        cursor.close();
        dbReader.close();;
        return notifyIQs;
    }

    public NotifyIQ queryIQBy_Id(long _id) {
        SQLiteDatabase dbReader = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(DBConsts.QUERY_IQ_BY_TABLE_ID + _id, null);
        NotifyIQ iq = new NotifyIQ();
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            iq.setId(CursorUtil.getString(cursor, DBConsts.COL_ID));
            iq.setApiKey(CursorUtil.getString(cursor, DBConsts.COL_APIKEY));
            iq.setMessage(CursorUtil.getString(cursor, DBConsts.COL_MSG));
            iq.setTitle(CursorUtil.getString(cursor, DBConsts.COL_TITLE));
            iq.setUri(CursorUtil.getString(cursor, DBConsts.COL_URI));
        }
        cursor.close();
        dbReader.close();
        return iq;
    }

    public void closeDB() {
        if ( dbReader != null && dbReader.isOpen()){
            dbReader.close();
        }
    }
}
