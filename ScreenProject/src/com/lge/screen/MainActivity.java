package com.lge.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
