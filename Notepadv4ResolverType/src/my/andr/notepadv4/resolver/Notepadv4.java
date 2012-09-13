package my.andr.notepadv4.resolver;

import my.andr.notepadv4.provider.Notes;
import android.app.ListActivity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Notepadv4 extends ListActivity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;

	Cursor notesCursor = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notepad_list);
		fillData();
		registerForContextMenu(getListView());
	}

	/*
	 * cursor가 갖고 있는 데이터 관리를 LoadManager에 위임하지 않으면
	 * DB 조작을 할 때마다  fillData()와 같은 method를 직접 호출하여
	 * cursor를 requery해주어야 함
	 * -> 비표준
	 */
	private void fillData() {
		CursorLoader cl = new CursorLoader(this, Notes.CONTENT_URI,
				new String[] { Notes._ID, Notes.TITLE, Notes.BODY }, null,
				null, null);
		notesCursor = cl.loadInBackground();

		String[] from = new String[] { Notes._ID, Notes.TITLE, Notes.BODY };
		int[] to = new int[] { R.id.text1, R.id.text2, R.id.text3 };
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
			R.layout.notes_row, notesCursor, from, to, 0);
		setListAdapter(notes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createNote();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			getContentResolver().delete(Notes.CONTENT_URI,
					Notes._ID + "=" + info.id, null);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	private void createNote() {
		Intent i = new Intent(this, NoteEdit.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, NoteEdit.class);
		/*
		 * DB를 사용하는 앱에서는 intent에 데이터를 담지 않도록 함.
		 * --> DB에 있는 데이터만 가치 있는 것. 그래서 primary key만 담음.
		 */
		i.putExtra(Notes._ID, id);
		startActivityForResult(i, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		fillData();
	}
}
