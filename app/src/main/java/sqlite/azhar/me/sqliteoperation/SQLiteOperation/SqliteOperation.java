package sqlite.azhar.me.sqliteoperation.SQLiteOperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azhar on 12/2/2017.
 */

public class SqliteOperation extends SQLiteOpenHelper {
    private static final String MY_DATABASE_NAME = "friend.db";
    private static final String MY_TABLE_NAME = "friend_table";
    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "NAME";
    private static final String EMAIL_COLUMN = "EMAIL";
    private static final String PHONE_COLUMN = "PHONE";

    public SqliteOperation(Context context) {
        super(context, MY_DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //  sqLiteDatabase=this.getWritableDatabase();
        // sqLiteDatabase.execSQL("CREATE TABLE " +MY_TABLE_NAME +" (contact_id integer PRIMARY KEY, first_name text NOT NULL, last_name text NOT NULL, email text NOT NULL UNIQUE, phone text NOT NULL UNIQUE)");
        /*sqLiteDatabase.execSQL("create table " + MY_TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT,PHONE TEXT)");
*/

      /*  db.execSQL("create table " + TABLE_NAME + " ( " + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_2  + " TEXT, " + COL_3 + " TEXT)");*/
        sqLiteDatabase.execSQL("create table " + MY_TABLE_NAME +

                " (" +ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT," +NAME_COLUMN + " TEXT,"  +
               EMAIL_COLUMN+ " TEXT,"+PHONE_COLUMN+ " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addMyFriend(String name, String email, String phone) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COLUMN, name);
        contentValues.put(EMAIL_COLUMN, email);
        contentValues.put(PHONE_COLUMN, phone);
        long addFriends = sqLiteDatabase.insert(MY_TABLE_NAME, null, contentValues);
        if (addFriends == -1)
            return false;
        else
            return true;
    }

    public Cursor displayMyFriend() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MY_TABLE_NAME, null);
        return cursor;
    }

    public  boolean updateFriendInformation(String id,String name,String email,String phone){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_COLUMN,id);
        contentValues.put(NAME_COLUMN,name);
        contentValues.put(EMAIL_COLUMN,email);
        contentValues.put(PHONE_COLUMN,phone);
        sqLiteDatabase.update(MY_TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }

    public Integer deleteFriendsInformation(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
     return    sqLiteDatabase.delete(MY_TABLE_NAME,"ID=?",new String[]{id});

    }
}
