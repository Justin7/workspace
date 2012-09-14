package my.andr.calc;

import android.app.Service;

import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;
import my.andr.notepadv4.provider.Notes;

public class CalcService extends Service {
	IBinder ib = new ICalcService.Stub() {
		Calc c = new Calc();
		@Override
		public String calc(String ssu, String sop) throws RemoteException {
			// TODO Auto-generated method stub
			String r = c.calc(ssu, sop);
			
			ContentValues cv = new ContentValues();
			cv.put(Notes.TITLE, new java.util.Date().toString());
			cv.put(Notes.BODY, ssu + " " + sop + " = " + r);
			
			getContentResolver()
			.insert(Notes.CONTENT_URI, cv);
			return r;
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "onBing()", Toast.LENGTH_SHORT).show();
		return ib;
	}

}
