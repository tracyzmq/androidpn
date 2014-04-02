package cn.irains.pushdemo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.androidpn.client.NotificationDetailsActivity;
import org.androidpn.client.ServiceManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.ic_launcher);
        serviceManager.startService();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_configure:
                startActivity(new Intent(this, ConfigureActivity.class));
                break;
            case R.id.bt_notify_all_msgs:
                startActivity(new Intent(this, NotifyListActivity.class));
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, NotifySettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
