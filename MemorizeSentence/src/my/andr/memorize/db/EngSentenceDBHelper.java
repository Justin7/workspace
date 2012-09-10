package my.andr.memorize.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EngSentenceDBHelper extends SQLiteOpenHelper {


	public EngSentenceDBHelper(Context context) {
		super(context, "sentences.db", null ,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] engs={
			"Once you see all those animals, you should feel better.",
			"I need to fix my car as soon as possible.",
			"I hope it snows all day long tomorrow.",
			"Can I get you anything?",
			"How many apples did you eat today?",
			"If it is possible, I'd like to have a Diet Coke.",
			"Whose cellular phone is this anyway?",
			"Richard, what are you doing up there?",
			"Who is that? To tell the truth, I don't like him that much.",
			"One should keep one's promise; otherwise no one will trust him or her.",
			"Speaking of the tennis match, this is John's last one of the season.",
			"Do you know how to cook this?",
			"I feel sick to my stomach.",
			"Feel free to call me, whenever you want to play tennis.",
			"I have two sisters, and both of them are sick. To make matters worse, they have to take their final exams this week.",
			"How come you are not wearing a yellow uniform today?",
			"I have to make a decision by 10:30.",
			"How often do you play tennis?",
			"Do you mind if I give you some advice on that? It is a very important tip on how to use the court.",
			"You are much better than me.",
			"I can't believe this is the last summer camp that I am attending with you guys.",
			"There's some food on the table. Help yourself, but please don't feed the dog. We are trying to cut down the food cost.",
			"Is there a drug store around here?",
			"Don't tell me you can't go there.",
			"You should call the doctor right away.",
			"Would you bring more batteries for me?",
			"May I ask you where I can find those batteries? I looked around everywhere but I couldn't find them. Where did you put them?",
			"What is going on down there?",
			"I had a hard time controlling the boat.",
			"Please forgive me this time. I will make sure it won't happen again.",
			"On a day like this, I would do anything for a cold drink.",
			"What are you looking at?",
			"I heard you are moving to New York. Is that right?",
			"Is this the ring you were looking for?",
			"How much did you pay for that?",
			"How do you open this door?",
			"I hate this. This kind of thing makes me really angry.",
			"We are very sorry for all the trouble we've caused you, but that's the way it is around here. Take it or leave it.",		
			"Excuse me. How long will it take to fix it?",
			"Oh my gosh! What a mess! What happened here?",
			"John promised to visit Paul's office before he leaves for Egypt.",
			"Let me know when she comes in.",
			"Why don't you give her a call and remind her that I'll stop by around 9.",
			"I wonder if this pearl necklace will make her happy.",
			"Are you surprised at the news?",
			"Do you want me to verify that for you?",
			"It was here on the table this morning but it's gone now.",
			"It's obvious he failed the exam twice before he became a broadcaster.",
			"The reason why he failed the exam was he simply didn't study hard enough.",
			"If he studies hard, he will pass the exam. That's for sure."		
		};
		
		String[] kors={
			"�ϴ� �� �������� ���� �� ����� ������ �ž�.",
			"������ �� ���� �� ���� ���ľ� ��.",
			"���� �Ϸ� ���� ���� ������ ���ھ�.",
			"�� �� ������ �帱���?",
			"�� ���� ����� �� ���� �Ծ���?",
			"�����ϴٸ� ���̾�Ʈ �ݶ�� �԰ڽ��ϴ�.",
			"����ü �̰��� ���� �ڵ����Դϱ�?",
			"��ó��, �� ������ �� �ϼ���?",
			"�� ����� ������? ������ ����, ���� �׸� �״��� �������� �ʾƿ�.",
			"����� ����� ���Ѿ� ��. �׷��� ������ �ƹ��� �� ����� ���� ���� �ž�.",
			"�״Ͻ� ���� �����ڸ�, �̰��� ���� �� ���� ������ ����Դϴ�.",
			"�̰��� ��� �丮�ϴ��� �˰� �ִ�?",
			"�谡 ������.",
			"�״Ͻ� ��⸦ �ϰ� ������ ������� ���� ������ ���� ��ȭ�ؿ�.",
			"�����Դ� ������ �� �� �ִµ� ��� ���Ŀ�. ���󰡻����� �׵��� �̹� �ֿ� �б⸻ ������ �ľ� �մϴ�.",
			"�ʴ� ���� �� ��� �������� ���� �ʾҴ�?",
			"���� 10�� 30�б��� ������ ������ ��.",
			"�󸶳� ���� �״Ͻ��� Ĩ�ϱ�?",
			"���� �ű⿡ ���� ������ �� �ص� �ɱ��? �װ��� �״Ͻ� ��Ʈ�� ����ϴ� �� �ſ� �߿��� ������ �� �̴ϴ�.",
			"����� ������ �ξ� ���Ͻô±���.",
			"�̰��� ���� ������ �Բ� �����ϴ� ������ ���� ķ����� �Ͼ������� �ʴ´�.",
			"��Ź ���� ������ �� �־��. ������ ���, �����Դ� ������ ������. ���� ����� �����Ϸ��� �մϴ�.",
			"�� ��ó�� �౹�� �ֳ���?",
			"�ű⿡ �� �� ���ٴ� ���� ������.",
			"���� �ǻ縦 �θ��� �� ���ھ�.",
			"���͸� �� �� ������ �ٷ���?",
			"��� ���� �׷� ���͸��� ã�� �� �ִ��� �˷��ֽðڽ��ϱ�? ����� �� �����ôµ� ã�� ���߾��. ��� �μ̾��?",
			"�� �ؿ� ���� ���̿���?",
			"�� �踦 �����ϴ� �� �ſ� ���� ������.",
			"�� ���� �뼭���ֽʽÿ�. �ٽô� �׷� ���� ������ �ϰڽ��ϴ�.",
			"���ð��� ���� ����, �� ������� ���ؼ���� ������ �ϰھ�.",
			"�� ���� �ִ�?",
			"�������� �̻��Ѵٰ� ����µ�, �װ� ����̳�?",
			"�̰��� �װ� ã�� �ִ� ������?",
			"�� �װ� �� �ְ� ���?",
			"�� ���� ��� ����?",
			"�� �̷� ���� �� �����̾�. �̷� ���� ���� ���� ȭ���� ����ٱ�.",
			"���� ������ �� ���ļ� ���� �˼�������, �������̶� �� �� �׷�����. ����� ������ �ϼ���.",
			"�Ƿ��մϴ�. �װ��� ��ġ�� �� �ð��� �󸶳� �ɸ����?",
			"����, �����̳�. ���� ���� ���̿���?",
			"���� ����Ʈ�� ������ ���� ���� �繫���� �湮�ϱ�� ����߽��ϴ�.",
			"�׳డ ������ ���� �˷���.",
			"�׳࿡�� ��ȭ�ؼ� ���� 9���� �鸦 �Ŷ�� ������.",
			"�� ���� ����̰� �׳ฦ �ູ�ϰ� ���ٱ� ����.",
			"�� ������ ���̳���?",
			"���� �װ��� Ȯ���ص帱���?",
			"��ħ������ �ص� ���� å�� ���� �־��µ� ������ ��������.",
			"�װ� ������� �Ǳ� ���� �� ���迡 �� ���̳� ������ ���� �и��ؿ�.",
			"�װ� �� ���迡 ������ ���� ���� �װ� ���θ� ������ ���� �ʾұ� �����̿���.",
			"�������� �����Ѵٸ�, �״� �� ���迡 �հ��� �̴ϴ�. �װ� Ȯ���ؿ�."
		};

		db.execSQL("CREATE TABLE " + DBConstants.TABLE_NAME + " ( " +
				"	_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"	no INTEGER UNIQUE NOT NULL , " +
				" 	eng_sentence TEXT, " +
				"	kor_sentence TEXT," +
				"	labtime INTEGER," +
				"	last_accessed_time TEXT" +
				" ) ");
		ContentValues row = new ContentValues();
		for (int i = 0; i < kors.length; i++) {
			row.put("no", i+1);
			row.put("eng_sentence", engs[i]);
			row.put("kor_sentence", kors[i]);
			db.insert(DBConstants.TABLE_NAME, null, row);
		}
	}//end onCreate

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE "+DBConstants.TABLE_NAME);
		onCreate(db);
	}//end onUpgrade

}//end class
