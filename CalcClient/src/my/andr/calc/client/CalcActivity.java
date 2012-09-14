package my.andr.calc.client;

import my.andr.calc.ICalcService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends Activity {
	boolean 첫숫자냐;
	TextView tv;
	ICalcService c;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        첫숫자냐=true;
        tv=(TextView)findViewById(R.id.tv);
        //c=new Calc();
      
        Intent i = new Intent();
        ComponentName cn = new ComponentName(
        	"my.andr.calc",
        	"my.andr.calc.CalcService");
        i.setComponent(cn);
        
        ServiceConnection sc = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Toast.makeText(CalcActivity.this, "onServiceDisconnected()", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder ib) {
				// TODO Auto-generated method stub
				c = ICalcService.Stub.asInterface(ib);
				Toast.makeText(CalcActivity.this, "onServiceConnected()", Toast.LENGTH_SHORT).show();
			}
		};
        
        bindService(i, sc, BIND_AUTO_CREATE);
        
        OnClickListener su=new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b=(Button)v;
				CharSequence cs=b.getText();
				if(첫숫자냐){
					tv.setText(cs);
					첫숫자냐=false;
				}else{
					tv.append(cs);
				}
			}
		};
		((Button)findViewById(R.id.b0)).setOnClickListener(su);
		((Button)findViewById(R.id.b1)).setOnClickListener(su);
		((Button)findViewById(R.id.b2)).setOnClickListener(su);
		((Button)findViewById(R.id.b3)).setOnClickListener(su);
		((Button)findViewById(R.id.b4)).setOnClickListener(su);
		((Button)findViewById(R.id.b5)).setOnClickListener(su);
		((Button)findViewById(R.id.b6)).setOnClickListener(su);
		((Button)findViewById(R.id.b7)).setOnClickListener(su);
		((Button)findViewById(R.id.b8)).setOnClickListener(su);
		((Button)findViewById(R.id.b9)).setOnClickListener(su);
		((Button)findViewById(R.id.bdot)).setOnClickListener(su);
		
		OnClickListener op=new OnClickListener() {
			@Override
			public void onClick(View v) {
				CharSequence scs=tv.getText();
				String ssu=scs.toString();
				
				Button b=(Button)v;
				CharSequence cs=b.getText();
				String sop=cs.toString();
				
				String r = null;
				try {
					r = c.calc(ssu, sop);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tv.setText(r);
				첫숫자냐=true;
			}
		};
		((Button)findViewById(R.id.badd)).setOnClickListener(op);
		((Button)findViewById(R.id.bsub)).setOnClickListener(op);
		((Button)findViewById(R.id.bmul)).setOnClickListener(op);
		((Button)findViewById(R.id.bdiv)).setOnClickListener(op);
		((Button)findViewById(R.id.bequ)).setOnClickListener(op);
    }
}