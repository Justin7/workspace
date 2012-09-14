package my.andr.searchable.dict;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SearchableDictionary extends Activity {
    private static final int MENU_SEARCH = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = getIntent();
        Toast.makeText(this, intent.getAction()+"~~~", 1).show();
        Log.i("log", "SearchableDictionary ::::"+intent.getAction());
        
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            // from click on search results
            Dictionary.getInstance().ensureLoaded(getResources());
            String word = intent.getDataString();
            Dictionary.Word theWord = Dictionary.getInstance().getMatches(word).get(0);
            launchWord(theWord);
            finish();
        } 
        Log.d("log", intent.toString());
        if (intent.getExtras() != null) {
            Log.d("log", intent.getExtras().keySet().toString());
        }
    }
    /** �޴�Ű ������ �� ������ �޴� ����*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_SEARCH, 0, R.string.menu_search)
                .setIcon(android.R.drawable.ic_search_category_default)
                .setAlphabeticShortcut(SearchManager.MENU_KEY);
        return super.onCreateOptionsMenu(menu);
    }
    /** �޴��� ���� ���� �� ȣ��*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SEARCH:
                onSearchRequested();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /** �ܾ �������� ���, 
     * �ܾ�� �� ���Ǹ� Intent�� ���
     * ����� ������ WordActivity Ŭ���� ȣ��   */
    private void launchWord(Dictionary.Word theWord) {
        Intent next = new Intent();
        next.setClass(this, WordActivity.class);
        next.putExtra("word", theWord.word);
        next.putExtra("definition", theWord.definition);
        startActivity(next);
    }  
}
