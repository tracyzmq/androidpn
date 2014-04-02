package cn.irains.pushdemo.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.androidpn.client.Constants;
import org.androidpn.client.LogUtil;

import cn.irains.pushdemo.app.notify.NotifyIQ;


public class NotifyDetailActivity extends ActionBarActivity {
    private static final String LOG_TAG = LogUtil.makeLogTag(NotifyDetailActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_detail);
        Intent intent = getIntent();
        Object o = intent.getSerializableExtra(Constants.INTENT_EXTRA_IQ);
        if ( o == null || !( o instanceof  NotifyIQ)) {
            Log.e(LOG_TAG, "the extra is null");
            return ;
        }
        NotifyIQ iq = (NotifyIQ) o;
        showTextView((TextView) findViewById(R.id.notify_title), iq.getTitle());
        showTextView((TextView) findViewById(R.id.notify_msg), iq.getMessage());
        showTextView((TextView) findViewById(R.id.notify_uri), iq.getUri());
        showTextView((TextView) findViewById(R.id.notify_time), iq.getTime());
    }

    private void showTextView(TextView view, String text) {
        if(text != null) {
            view.setText(text);
        }
    }
}
