package my.andr.task.flag1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageOne extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		
		Button l=(Button)findViewById(R.id.launch);
		l.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				TaskViewer.show(PageOne.this);
			}
		});	
		Button b2=(Button)findViewById(R.id.two);
		b2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i=new Intent("Two");
				startActivity(i);
			}
		});
		Button b3=(Button)findViewById(R.id.three);
		b3.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i=new Intent("Three");
				
				//1.ȣ��� ��ü�� ���� ��ü�� stack�� �ִٸ� ������ ��ü�� Top���� �ű��.
				//i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //1-2-3-4-1 >> 1-2-4-1-3 onNewIntent()
				
				//2.stack�� �����ϴ� ��ü�� �ٽ� ȣ��� �����ϴ� ��ü���� stack����� ȣ��
				//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //1-2-3-4-1 >> 1-2-new3
				
				//3.stack�� �����ϴ� ��ü�� �ٽ� ȣ��� �����ϴ� ��ü������ stack����� SINGLE_TOP���� ������
				//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP); //1-2-3-4-1 >> 1-2-old3 onNewIntent()
				
				//4.Service �Ǵ� BroadcastReceiver���� Activity ȣ��� ����ؾ� ��
				//i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				
				//5.Third �������� history�� ������ ����
				i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				
				startActivity(i);
			}
		});
	}
}