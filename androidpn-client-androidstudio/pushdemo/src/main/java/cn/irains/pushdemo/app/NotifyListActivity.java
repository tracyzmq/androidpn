package cn.irains.pushdemo.app;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.androidpn.client.Constants;
import org.androidpn.client.LogUtil;

import java.util.List;

import cn.irains.pushdemo.app.db.DBConsts;
import cn.irains.pushdemo.app.db.DBOperator;
import cn.irains.pushdemo.app.notify.NotifyIQ;


public class NotifyListActivity extends ActionBarActivity {
    private static final String LOG_TAG = LogUtil.makeLogTag(NotifyListActivity.class);

    private DBOperator operator;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_list);
        ListView listView = (ListView) findViewById(R.id.notify_list);
        operator = new DBOperator(this);
//        ListAdapter adapter = new ArrayAdapter<NotifyIQ>(this, R.layout.activity_notify_item,)
        cursor = operator.queryAll();
        CursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.activity_notify_item, cursor,
                new String[]{DBConsts.COL_TITLE, DBConsts.COL_MSG, DBConsts.COL_TIME},
                new int[] {R.id.notify_list_title, R.id.notify_list_msg, R.id.notify_list_time} );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                NotifyIQ iq = operator.queryIQBy_Id(id);
                startActivity(new Intent(NotifyListActivity.this, NotifyDetailActivity.class).putExtra(Constants.INTENT_EXTRA_IQ, iq));
            }
        });
    }

    @Override
    protected void onDestroy() {
        cursor.close();
        operator.closeDB();
        super.onDestroy();
    }
}
