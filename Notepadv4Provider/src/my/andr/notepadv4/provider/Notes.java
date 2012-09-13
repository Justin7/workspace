package my.andr.notepadv4.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 * DB 설정값은 항상 상수로 사용해야함 
 */
public  final class Notes implements BaseColumns {
	public static final String AUTHORITY="my.andr.notepadv4.provider";
	public static final Uri    CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/notes");

	public static final String DEFAULT_SORT_ORDER="title";

	public static final String TITLE="title";
	public static final String BODY ="body";
}  
