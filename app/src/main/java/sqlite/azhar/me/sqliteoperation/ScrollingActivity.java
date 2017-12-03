package sqlite.azhar.me.sqliteoperation;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sqlite.azhar.me.sqliteoperation.SQLiteOperation.SqliteOperation;

public class ScrollingActivity extends AppCompatActivity {

    SqliteOperation sqliteOperation;
    EditText etFriendsName,etFriendsEmail,etFriendsPhone,etFriendsID;
    TextView tvDisplayFriends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        sqliteOperation=new SqliteOperation(this);
        initializeToolbarAndFloatingActionButton();
        initializeViewID();

    }

    private void initializeViewID() {
        etFriendsEmail=(EditText)findViewById(R.id.etFriendsEmail);
        etFriendsName=(EditText)findViewById(R.id.etFriendsName);
        etFriendsPhone=(EditText)findViewById(R.id.etFriendsPhoneNumber);
        etFriendsID=(EditText)findViewById(R.id.etFriendsID);
        tvDisplayFriends=(TextView)findViewById(R.id.tvDisplayFriends);
    }


    private void initializeToolbarAndFloatingActionButton() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFriend(View view) {
        Cursor cursor=sqliteOperation.displayMyFriend();
        if(cursor.getCount()==0){
            Toast.makeText(this, "There is No Friend Available", Toast.LENGTH_SHORT).show();

        }else{
            StringBuffer stringBuffer=new StringBuffer();
            while (cursor.moveToNext()){

                stringBuffer.append("Friend ID: "+cursor.getString(0)+"\n");
                stringBuffer.append("Friend Name: "+cursor.getString(1)+"\n");
                stringBuffer.append("Friend Email: "+cursor.getString(2)+"\n");
                stringBuffer.append("Friend Phone: "+cursor.getString(3)+"\n"+"\n");
            }
            tvDisplayFriends.setText(stringBuffer);
        }
    }

    public void updateFriend(View view) {
        if(sqliteOperation.updateFriendInformation(etFriendsID.getText().toString(),etFriendsName.getText().toString(),etFriendsEmail.getText().toString(),etFriendsPhone.getText().toString()))
        {
            Toast.makeText(this, "Friends Information Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Friends Information do not Update ", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteFriend(View view) {
        if (sqliteOperation.deleteFriendsInformation(etFriendsID.getText().toString())>0){
            Toast.makeText(this, "My Friends Information Deletes", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "My Friends Information does not Delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFriend(View view) {
     boolean isInsertedMyFriend =   sqliteOperation.addMyFriend(etFriendsName.getText().toString(),etFriendsEmail.getText().toString(),etFriendsPhone.getText().toString());
    if(isInsertedMyFriend==true){
        Toast.makeText(this, "My friend Added", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(this, "My Friend yet not added", Toast.LENGTH_SHORT).show();
    }
    }
}
