package my.andr.memorize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import my.andr.memorize.db.EngDbAdapter;
import my.andr.memorize.db.SentenceVO;
import my.andr.memorize.layout.AutoLinearLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SentencePlayActivity extends Activity  {
	private final  Context cxt=this;
	final static int SUCCESS_SENTENCE = 0;
	final static int ERROR_SENTENCE = 1;
	final static int SHOW_HINT = 2;
	private SentenceVO sentence;
	private TextView clickedSentence;
	private String[] splitSentence;
	private Button tryagainBtn;
	private Button memorizeBtn;
	private int sentenceClickCount;
	private long startTime;// ���۽ð�
	private long endTime;// ����ð�
	private long labTime;// �ɸ��ð�

	private Animation playAnim;
	private EngDbAdapter dao=EngDbAdapter.getInstance(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_alone_play);

		clickedSentence = (TextView) findViewById(R.id.play_sentence_clicked);
		clickedSentence.setText("");
		addEvent();
		String item_no = getIntent().getStringExtra("item_no");
		sentence = dao.getSentence(item_no);
		((TextView) findViewById(R.id.play_sentence1)).setText(sentence.getKorSentence());
		((TextView) findViewById(R.id.play_sentence2)).setText(sentence.getEngSentence());
		//
		int lastAccTime = new Integer(sentence.getLabtime());
		lastAccTime /= 1000;
		((TextView) findViewById(R.id.play_last_accessed_time)).setText(lastAccTime + "��");
		//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		final int today = new Integer(sdf.format(new Date()));
		int lat = 0;
		try {
			lat = new Integer(sentence.getLastAccessedTime());
		} catch (Exception e) {
		}
		int diff = lat - today;
		// System.out.println("DIFF="+diff);
		String msg = (lat == 0) ? "��Ͼ���" : (diff < 0) ? diff * -1 + "����" : "����";
		((TextView) findViewById(R.id.play_labtime)).setText(msg);
		setExerciseTitle("NO." + item_no);// ����
		playAnim = AnimationUtils.loadAnimation(cxt,R.anim.play);
	}// end onCreate()
	
	/**
	 * �ܾ� Ŭ���� ���忡 �´��� Ȯ���Ͽ� ó���ϴ� �޼���
	 */
	private OnClickListener sentenceListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (sentenceClickCount == splitSentence.length)
				return;
			String text = ((TextView) v).getText().toString();
			if (splitSentence[sentenceClickCount].equals(text)) {
				clickedSentence.append(" " + text);
				sentenceClickCount++;
				playAnim = AnimationUtils.loadAnimation(cxt, R.anim.play);
				v.setAnimation(playAnim);// �°� �������Ϳ� ���� �ִϸ��̼� ����
				v.setVisibility(View.INVISIBLE);// �°Դ������� �Ⱥ��̰� ��.
				if (sentenceClickCount == splitSentence.length) {// ������ �� �����
					endTime = new Date().getTime();
					labTime = endTime - startTime;
					handler.sendEmptyMessage(SUCCESS_SENTENCE);
					// ���� ����� ó���̰ų� ��ϰ����̸� ����
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String todayDateFormat = sdf.format(new Date());
					if (sentence.getLabtime() == 0 || // ó������ϴ°��
							sentence.getLastAccessedTime() == null || // ó������ϴ°��
							!sentence.getLastAccessedTime().equals(
									todayDateFormat) || // ��������¥�� ������ �ƴϰų�
							labTime < sentence.getLabtime()) { // ��� ���ŵǰų�
						sentence.setLabtime((int) labTime);
						sentence.setLastAccessedTime(todayDateFormat);
						dao.updateLabtime(labTime, todayDateFormat,sentence.getNo());
					}
				}
			} else {
				handler.sendEmptyMessage(ERROR_SENTENCE);
			}
		}// end onClick
	};
	/**
	 * ��� �޼����� �޾� ó���ϴ� �ڵ鷯 Ŭ����
	 * �ܾ� Ŭ���� ���忡 �´��� Ȯ���Ͽ� ó���ϴ� �޼����� ��� �޼���
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(cxt);
			switch (msg.what) {
			case ERROR_SENTENCE:
				vibrate(100);
				vibrate(100);
				dialog.setMessage("���� ������ Ʋ�Ƚ��ϴ�.")
						.setPositiveButton("Ȯ��", null).show();
				break;
			case SUCCESS_SENTENCE:
				String message = "";
				message += sentence.getLabtime() > labTime ? "��ϰ���~~ " : "";
				message += "�Է½ð��� " + (labTime / 1000) + "���Դϴ�.\n";
				boolean lastSentence = sentence.getNo() % 10 == 0;
				if (lastSentence) {// �ܰ躰 ������ ������ ���
					message += "���� �ܰ��� ������ �����Դϴ�";//
				} else {
					message += "���� �������� �Ѿ�ϴ�.";
				}// end if
				
				if (sentence.getNo() != 50)
					dialog.setPositiveButton(lastSentence ? "���� �ܰ�" : "���� ����",
							onClickNextListener);
				else
					dialog.setPositiveButton("��Ϻ���", onClickCloseListener);
					dialog.setMessage(message)
						.setNegativeButton("���� �ٽ�", retryOnClickListener)
						.show();
				break;
			case SHOW_HINT:
				showHint();
				break;
			}// end switch
		}// end handleMessage

		private void vibrate(long interval) {
			Vibrator vibrator = (Vibrator) getApplicationContext()
					.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(interval);
		}
	};


	private void setExerciseTitle(String title) {
		((TextView) findViewById(R.id.play_title)).setText(title);
	}

	/**
	 * �ٽ� ��õ�
	 */
	DialogInterface.OnClickListener retryOnClickListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			resetSentence();
		}// end onC��ick
	};	
	private void resetSentence() {
		// Ŭ���� ����
		sentenceClickCount = 0;
		startTime = Calendar.getInstance().getTimeInMillis();// ���۽ð�����
		// �Էµ� ���� ����
		clickedSentence.setText("");
		// �ܾ�������
		AutoLinearLayout ll = (AutoLinearLayout) findViewById(R.id.play_random_sentences);
		ll.removeAllViews();
		splitSentence();
	}
	
	DialogInterface.OnClickListener onClickNextListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(cxt,SentencePlayActivity.class);
			intent.putExtra("item_no", sentence.getNo() + 1 + "");
			startActivity(intent);
			finish();// �˾�����
		}// end onC��ick
	};
	DialogInterface.OnClickListener onClickCloseListener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			finish();// �˾�����
		}// end onC��ick
	};

	private void addEvent() {
		findViewById(R.id.play_title).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handler.sendEmptyMessage(SHOW_HINT);
			}
		});
		tryagainBtn = (Button) findViewById(R.id.play_btn_tryagain);
		tryagainBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				resetSentence();
			}

		});

		memorizeBtn = (Button) findViewById(R.id.play_btn_memorize);
		memorizeBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				findViewById(R.id.play_sentence1).setVisibility(View.GONE);
				findViewById(R.id.play_sentence2).setVisibility(View.GONE);
				tryagainBtn.setVisibility(View.VISIBLE);
				memorizeBtn.setVisibility(View.GONE);
				sentenceClickCount = 0;
				splitSentence();
				startTime = new Date().getTime();// ���۽ð�
			}
		});
		findViewById(R.id.play_left_arrow).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if (sentence.getNo() == 1)
							return;// ó�� ����
						Intent intent = new Intent(cxt,
								SentencePlayActivity.class);
						intent.putExtra("item_no", sentence.getNo() - 1 + "");
						startActivity(intent);
						finish();//
					}
				});
		findViewById(R.id.play_right_arrow).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if (sentence.getNo() == 50)
							return;// ������ ����
						Intent intent = new Intent(cxt,
								SentencePlayActivity.class);
						intent.putExtra("item_no", sentence.getNo() + 1 + "");
						startActivity(intent);
						finish();//
					}
				});
	}
	/**
	 *  ������� ��ĭ�� �������� �߶� �ܾ��ϳ��� TextView�� ��� �߰���Ŵ.
	 */
	protected void splitSentence() {
		AutoLinearLayout ll = (AutoLinearLayout) findViewById(R.id.play_random_sentences);
		// ll.setBackgroundColor(Color.GRAY);
		splitSentence = sentence.getEngSentence().split(" ");
		ArrayList<String> al = new ArrayList<String>();
		Collections.addAll(al, splitSentence);

		while (al.size() != 0) {
			String text = al.remove(new Random().nextInt(al.size()));// �迭���� ���Ƿ� �ϳ��� �����鼭 �����.
			TextView tv = new TextView(cxt);
			tv.setText(text);
			tv.setTextSize(24.0f);
			tv.setTextColor(Color.BLACK);
			tv.setBackgroundResource(R.drawable.my_rounded_corner);
			if (text.length() == 1) {
				tv.setPadding(15, 0, 15, 0);
			} else if (text.length() == 2) {
				tv.setPadding(10, 0, 10, 0);
			}
			tv.setOnClickListener(sentenceListener);
			AutoLinearLayout.LayoutParams layoutParams = new AutoLinearLayout.LayoutParams(	15, 15);
			ll.addView(tv, layoutParams);
		}// end for
	}// end splitSentence()

	private void debug(Object obj) {
		String string = (obj == null) ? "null" : obj.toString();
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	private void showHint() {
		new AlertDialog.Builder(SentencePlayActivity.this)
				.setMessage(
						sentence.getKorSentence() + "\n"
								+ sentence.getEngSentence())
				.setPositiveButton("Ȯ��", null).show();
	}

}// end SentencePlayActivity