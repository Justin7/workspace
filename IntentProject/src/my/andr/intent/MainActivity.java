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
				 * Intent ����
				 * 1,2 : ����� ����Ʈ - (private)���� �˰��ִ� ��Ƽ��Ƽ ����. �� ����
				 * 3,4 : public�ϰ� action�� category�� �����Ͽ� ����ϴ� ���
				 *       dependency injection(DI)�� ���� -- ������ �����Ǵ� ��Ƽ��Ƽ�� �� �� ���� ��. 
				 */
				
				/*
				 * 1. Local activity�� ȣ�� ����
				 */
//Intent i=new Intent();
//i.setClass(MainActivity.this, NextActivity.class);
//startActivity(i);
				
				/*
				 * 2. �ٸ� ���μ����� �ִ� activity���� ���� ����������, package���� ������ �͸� ����.
				 */
//Intent i=new Intent();
//ComponentName cn = new ComponentName(
//		"my.andr.intent",
//"my.andr.intent.NextActivity");
//
//i.setComponent(cn);
//startActivity(i);
				
				/*
				 * 3. action�� category�� �����ϴ� ���. ��ü������ ������ �ʿ䰡 ����
				 * Manifest�� ����ؾ� ��.
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_MAIN);
//
//startActivity(i);
				
				/*
				 * 4. ������ action�� ����� ����
				 */
//Intent i=new Intent();
//i.setAction("a.b.c");
//
//startActivity(i);
				
				/*
				 * 5. intent�� "����"�ΰ��� �ǵ��ϱ� ���� ����Ѵ�
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_VIEW);
//i.setData(Uri.parse("http://www.naver.com"));
//i.addCategory(Intent.CATEGORY_DEFAULT); // ��������
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
				 * 7. permission�� �ʿ��� ������Ʈ 
				 */
//Intent i=new Intent();
//i.setAction(Intent.ACTION_CALL);
//i.setData(Uri.parse("tel:114"));
//
//startActivity(i);

				/*
				 * 4. ������ action�� ����� ����
				 */
Intent i=new Intent();
i.setAction("a.b.c");
i.putExtra("name", "ȫ�浿");
i.putExtra("age", 20);
startActivityForResult(i, RESULT_OK);
			}
		});
	}
}