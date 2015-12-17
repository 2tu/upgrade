package com.tu.upgradeapplication;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tu.upgrade.DownloadCompleteReceiver;
import com.tu.upgrade.UpgradeManager;

public class MainActivity extends AppCompatActivity {
    DownloadCompleteReceiver mDownloadCompleteReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long downloadId = UpgradeManager.updgrade(getApplicationContext(), Uri.parse("https://raw.githubusercontent.com/2tu/upgrade/master/upgrade1.1.apk"), "更新", "测试下载");
                mDownloadCompleteReceiver = new DownloadCompleteReceiver(downloadId);
                registerReceiver(mDownloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDownloadCompleteReceiver != null)
            unregisterReceiver(mDownloadCompleteReceiver);
    }
}
