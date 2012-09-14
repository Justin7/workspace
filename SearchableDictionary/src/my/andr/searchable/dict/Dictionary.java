package my.andr.searchable.dict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.res.Resources;

public class Dictionary {
	/** �ܾ�� ������ ������ �����ϴ� Ŭ����*/
    public static class Word {
        public final String word;
        public final String definition;

        public Word(String word, String definition) {
            this.word = word;
            this.definition = definition;
        }
    }

    private static final Dictionary sInstance = new Dictionary();
    /** singleton pattern ������ ����  ������ ��ü ���� */
    public static Dictionary getInstance() {
        return sInstance;
    }
    /**  ���� ���ڿ��� �� ���� ���ڿ��� ���۵Ǵ� �ܾ  ������ Map ����*/
    private final Map<String, List<Word>> mDict = new ConcurrentHashMap<String, List<Word>>();

    private Dictionary() {
    }

    private boolean mLoaded = false;

    /** ���Ϸ� ���� �����Ͱ� �о����� �ʾҴٸ�  Thread �� �����Ͽ� ������ �е��� loadWords()�� ȣ���Ѵ� <br/>
     * Loads the words and definitions if they haven't been loaded already.
     *
     * @param resources Used to load the file containing the words and definitions.
     */
    public synchronized void ensureLoaded(final Resources resources) {
        if (mLoaded) return;
        new Thread() {
            public void run() {
                try {
                    loadWords(resources);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    /** ���Ϸ� ���� �����͸� �о�  addWord()�� ȣ���Ͽ� �����Ѵ�.*/
    private synchronized void loadWords(Resources resources) throws IOException {
        if (mLoaded) return;

        InputStream inputStream = resources.openRawResource(R.raw.definitions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while((line = reader.readLine()) != null) {
                String[] strings = line.split("-");
                if (strings.length < 2) continue;
                addWord(strings[0].trim(), strings[1].trim());
            }
        } finally {
            reader.close();
        }
        mLoaded = true;
    }

    /** ���޵� �Ķ������ ���ڿ��� ���� ���ڿ��� �ִٸ� ����� list�� �����ϰ�, ������ null ����.*/
    @SuppressWarnings("unchecked")
	public List<Word> getMatches(String query) {
        List<Word> list = mDict.get(query);
        return list == null ? Collections.EMPTY_LIST : list;
    }
    
    /** �ܾ��� ���� ���ڿ��� ��ġ�ϴ� ���ڿ��� �ִ��� addMatch()�� ȣ���Ѵ� .*/
    private void addWord(String word, String definition) {
        final Word theWord = new Word(word, definition);

        final int len = word.length();
        for (int i = 0; i < len; i++) {
            final String prefix = word.substring(0, len - i);
            addMatch(prefix, theWord);
        }
    }
    /** �����ϴ� �ܾ��忡 ��ġ�ϴ� ���� ���ڿ��� �ִٸ� ���ڿ��� List�� �߰��ϰ�, 
     * ���ٸ� ���ο� List�� ����� Map�� �߰��Ѵ�  .*/
    private void addMatch(String prefix, Word word) {
        List<Word> matches = mDict.get(prefix);
        if (matches == null) {
            matches = new ArrayList<Word>();
            mDict.put(prefix, matches);
        }
        matches.add(word);
    }
}
