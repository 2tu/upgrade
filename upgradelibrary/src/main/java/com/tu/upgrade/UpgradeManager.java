package com.tu.upgrade;

import java.io.File;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

/**
 * 版本升级
 * 
 * @author Tu
 * @email enum@foxmail.com
 * @time 2015-3-2 下午2:55:04
 */
public class UpgradeManager {
	/**
	 * 版本升级，需要注册DownloadCompleteReceiver registerReceiver(receiver, new
	 * IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	 * 
	 * @param context
	 * @param uri
	 * @param title
	 * @param description
	 */
	public static long updgrade(Context context, Uri uri, String title, String description) {
		if(DownloadManagerResolver.resolve(context)) {
			DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
			DownloadManager.Request request = new DownloadManager.Request(uri);
			request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.toString().substring(uri.toString().lastIndexOf("/") + 1));
			File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			if (!folder.exists() || !folder.isDirectory()) {
				folder.mkdirs();
			}
			request.setTitle(title);
			request.setDescription(description);
			if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT)
				request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			request.setMimeType("application/vnd.android.package-archive");
			return downloadManager.enqueue(request);
		}
		return 0;
	}
	/**
	 * 安装APK
	 *
	 * @param context
	 * @param fileName
	 */
	public static void installAPK(Context context, String fileName) {
		File apkfile = new File(fileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
}
