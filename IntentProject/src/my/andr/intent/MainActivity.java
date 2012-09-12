package my.andr.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String r = data.getStringExtra("result");
		Log.i("log", "result = " + r);
		Log.i("log", "requestCode = " + requestCode);
		Log.i("log", "resultCode = " + resultCode);
		Toast.makeText(this, "result = " + r, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, "requestCode = " + requestCode, Toast.LENGTH_SHORT).show();
		Toast.makeText(this, "resultCode = " + resultCode, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button b=(Button)findViewById(R.id.next);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				 * Intent 종류
				 * 1,2 : 명시적 인텐트 - (private)나만 알고있는 액티비티 연결. 더 빠름
				 * 3,4 : public하게 action과 category를 공개하여 사용하는 방법
				 *       dependency injection(DI)를 위함 -- 다음에 연동되는 액티비티를 알 수 없을 때. 
				 */
				
				/*
				 * 1. Local activity만 호출 가능
				 */
//Intent i=new Intent();
//i.setClass(MainActivity.this, NextActivity.class);
//startActivity(i);
				
				/*
				 * 2. 다른 프로세스에 있는 activity까지 연동 가능하지만, package명을 충족한 것만 가능.
				 */
//Intent i=new Intent();
//ComponentName cn = new ComponentName(
//		"my.andr.intent",
//"my.andr.intent.NextActivity");
//
//i.setComponent(cn);
//startActivity(i);
				
				/*
				 * 3. action과 category를 설정하는 방법. 구체적으로 지정할 필요가 있음
				 * Manifest에 명시해야 함.
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_MAIN);
//
//startActivity(i);
				
				/*
				 * 4. 나만의 action을 만들어 공개
				 */
//Intent i=new Intent();
//i.setAction("a.b.c");
//
//startActivity(i);
				
				/*
				 * 5. intent는 "무엇"인가를 의도하기 위해 사용한다
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_VIEW);
//i.setData(Uri.parse("http://www.naver.com"));
//i.addCategory(Intent.CATEGORY_DEFAULT); // 생략가능
//
//startActivity(i);
				
				/*
				 * 6
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_DIAL);
//i.setData(Uri.parse("tel:114"));
//
//startActivity(i);

				/*
				 * 7. permission이 필요한 컴포넌트 
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_CALL);
//i.setData(Uri.parse("tel:114"));
//
//startActivity(i);

				/*
				 * 4. 나만의 action을 만들어 공개
				 */
Intent i=new Intent();
i.setAction("a.b.c");
i.putExtra("name", "홍길동");
i.putExtra("age", 20);
startActivityForResult(i, RESULT_OK);
			}
		});
	}
}