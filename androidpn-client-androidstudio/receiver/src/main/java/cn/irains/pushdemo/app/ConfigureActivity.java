package cn.irains.pushdemo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class ConfigureActivity extends ActionBarActivity {

    private static final String PREFERENCES_NAME = "democonf";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_IP = "IP";
    private static final String KEY_POSITION = "POSITION";

    private EditText editName;
    private EditText editIP;
    private EditText editPosition;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        sp = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        editName = (EditText) findViewById(R.id.conf_name);
        editIP = (EditText) findViewById(R.id.conf_ip);
        editPosition = (EditText) findViewById(R.id.conf_position);
        editName.setText(sp.getString(KEY_NAME, ""));
        editIP.setText(sp.getString(KEY_IP, ""));
        editPosition.setText(sp.getString(KEY_POSITION, ""));

    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NAME, editName.getText().toString());
        editor.putString(KEY_IP, editIP.getText().toString());
        editor.putString(KEY_POSITION, editPosition.getText().toString());
        editor.commit();
        super.onStop();
    }
}
