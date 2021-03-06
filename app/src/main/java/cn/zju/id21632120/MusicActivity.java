package cn.zju.id21632120;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cn.iipc.android.tweetlib.SubmitProgram;
import cn.zju.id21632120.service.UpdateService;

/**
 * 音乐播放器
 * Created by Wangli on 2017/6/12.
 */

public class MusicActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textView);
        ContentResolver provider = getContentResolver();
        Cursor cursor = provider.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{"artist", "title", "duration"}, null, null, null);
        String s = "";
        while(cursor.moveToNext()) {
            s += String .format("作者： %s\n曲名： %s\n时间： %d（秒）\n\n", cursor.getString(0),
                    cursor.getString(1), Integer.parseInt(cursor.getString(2))/1000);
        }

        textView.setText(s);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                new SubmitProgram().doSubmit(this, "F2");
                return true;
            case R.id.action_start:
                startService(new Intent(this, UpdateService.class));
                return true;
            case R.id.action_stop:
                stopService(new Intent(this, UpdateService.class));
                return true;
            case R.id.action_calc:
                startActivity(new Intent(this, CalcActivity.class));
                return true;
            case R.id.action_filetest:
                startActivity(new Intent(this, StorageActivity.class));
                return true;
            case R.id.action_music:
                startActivity(new Intent(this, MusicActivity.class));
                return true;
            case R.id.action_scroll_weibo:
                startActivity(new Intent(this, ScrollViewActivity.class));
                return true;
            case R.id.action_list_weibo:
                startActivity(new Intent(this, ListViewActivity.class));
                return true;
            case R.id.action_finish:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
