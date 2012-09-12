package my.andr.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NextActivity extends Activity {
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Intent r = new Intent();
		r.putExtra("result", "오케이");
		setResult(RESULT_OK, r);
		//finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next);
		
		Intent i = getIntent();
		String name = i.getStringExtra("name");
		int age = i.getIntExtra("age", 0);
		
		Log.i("log", name + ", " + age);
		Toast.makeText(this, name + ", " + age, Toast.LENGTH_SHORT).show();
//		
//		Intent r = new Intent();
//		r.putExtra("result", "오케이");
//		setResult(RESULT_OK, r);
//		finish();
	}
}
