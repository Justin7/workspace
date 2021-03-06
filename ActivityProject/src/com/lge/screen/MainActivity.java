package com.lge.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b = (Button)findViewById(R.id.nextBt);
        //OnClickListener ocl = new OnClickListener() {
        b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
    			// TODO Auto-generated method stub
    			Intent i = new Intent();
    			i.setClass(
    					MainActivity.this, 
    					NextActivity.class
    			);
    			startActivity(i);
    		}		
    	});
        //b.setOnClickListener(ocl);
        Log.i("log", "onCreate()");
        Toast.makeText(this, "OnCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
//		Log.i("log", "onRestoreInstanceState()");
//      Toast.makeText(this, "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("log", "onStart()");
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("log", "onRestart()");
        Toast.makeText(this, "onRestart()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("log", "onResume()");
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
//		Log.i("log", "onSaveInstanceState()");
//      Toast.makeText(this, "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("log", "onPause()");
        Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("log", "onStop()");
        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("log", "onDestroy()");
        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        Log.i("log", "onCreateOptionsMenu()");
        Toast.makeText(this, "onCreateOptionsMenu()", Toast.LENGTH_SHORT).show();
        return true;
    }
}
