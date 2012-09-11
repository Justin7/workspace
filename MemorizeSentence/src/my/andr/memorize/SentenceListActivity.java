package my.andr.memorize;

import java.text.SimpleDateFormat;
import java.util.Date;

import my.andr.memorize.db.EngDbAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class SentenceListActivity extends ListActivity {
	private EngDbAdapter dao;
	int selectedCategoryIndex = 0;
	/**
	 * ��ü ������ ȣ��Ǵ� �޼���
	 * ��ü �ʱ�ȭ �۾��� �����Ѵ�.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_alone);		
		dao = EngDbAdapter.getInstance(this);
		addEvents();
	}// end onCreate()
/**
 * ���õ� ������ �ε����� ������ ������ ������ ��ġ�� �ľ��Ͽ� ǥ���Ѵ�.
 */
	@Override
	protected void onResume() {
		super.onResume();
		selectedCategoryIndex = getSharedPreferences("eng_sentence",MODE_WORLD_READABLE).getInt("selectedCategoryIndex",0);
		Spinner spinner = (Spinner) findViewById(R.id.alone_category);
		spinner.setSelection(selectedCategoryIndex);
		sentenceList();
		int lastPosition = getSharedPreferences("eng_sentence",MODE_WORLD_READABLE).getInt("last_position",0);
		getListView().setSelection(lastPosition);// ������ ��ġ�� �̵�
	}
   /** 
    * ListView�� ����� ���÷����Ѵ�.
    */
	@SuppressWarnings("deprecation")
	private void sentenceList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final int today = new Integer(sdf.format(new Date()));

		Cursor cursor = dao.getQuery(selectedCategoryIndex);
		startManagingCursor(cursor);

		SimpleCursorAdapter exerciseSentenceListAdapter = new SimpleCursorAdapter(
				this, R.layout.exercise_alone_item,
				cursor, new String[] { "no", "kor_sentence","labtime", "last_accessed_time" },
				new int[] {
						R.id.exe_alone_list_no, R.id.exe_alone_list_sentence,
						R.id.exe_alone_list_status,
						R.id.exe_alone_list_lastaccesseedtime }, 0) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				Cursor cursor = getCursor();
				int columnIndex = cursor.getColumnIndex("last_accessed_time");
				int lat = cursor.getInt(columnIndex);
				int diff = lat - today;
				String msg = (lat == 0) ? "��Ͼ���" : (diff < 0) ? diff * -1	+ "����" : "����";
				TextView tv=(TextView) view.findViewById(R.id.exe_alone_list_sentence);
				if (diff == 0) {   //���� �۾��� �Ŷ��
						tv.setBackgroundColor(Color.argb(100, 0, 200, 0));
						tv.setTextColor(Color.BLACK);
				} 
				((TextView) view.findViewById(R.id.exe_alone_list_lastaccesseedtime)).setText(msg);
				columnIndex = cursor.getColumnIndex("labtime");
				int labtime = cursor.getInt(columnIndex);
				labtime /= 1000;
				((TextView) view.findViewById(R.id.exe_alone_list_status))
						.setText(labtime + "��");
				return view;
			}
		};
		setListAdapter(exerciseSentenceListAdapter);
	}
    /**
     * ���õ� �������� ���� ó���� �ε��� ������ �����Ѵ�.
     * ������ ���õ� ���� �ε��� ������ �����Ѵ�.
     */
	private void addEvents() {
		// ���ǳ� �׸� ���ý� ó��
		Spinner spinner = (Spinner) findViewById(R.id.alone_category);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedCategoryIndex = position;
				savePreference("selectedCategoryIndex", selectedCategoryIndex);
				sentenceList();						
			}
			public void onNothingSelected(AdapterView<?> parent) {	
			}
		});
		//����Ʈ �׸��� Ŭ���� ó��
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				String noString = ((TextView) arg1.findViewById(R.id.exe_alone_list_no)).getText().toString();
				/** ������ ��ġ�� ���� */ 
				savePreference("last_position", position);
				
				Intent intent = new Intent(SentenceListActivity.this, SentencePlayActivity.class);
				intent.putExtra("item_no", noString);
				startActivity(intent);
			}
		});
	}// end addEvents()
	/**
	 * ���ǳ��� ����������  ����Ʈ �׸� ������ �����Ѵ�.
	 * @param key  ������ �������� ������
	 * @param no   ������ ������
	 */
	protected void savePreference(String key, int no) {
		SharedPreferences pref = getSharedPreferences("eng_sentence",
				MODE_WORLD_READABLE);
		Editor edit = pref.edit();
		edit.putInt(key, no);
		edit.commit();
	}
}// end ExerciseAloneActivity
