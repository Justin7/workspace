package com.lge.screen;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("log", "onBind()");
		Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("log", "onCreate()");
		Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("log", "onStartCommand()");
		Toast.makeText(this, "onStartCommand()", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("log", "onDestroy()");
		Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}

}
