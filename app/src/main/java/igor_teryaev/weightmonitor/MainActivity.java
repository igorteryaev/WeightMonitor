package igor_teryaev.weightmonitor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.logging.Logger;
//implements EditPersonalData.UserNameListener, EditPersonalData.DialogCommitListener

public class MainActivity extends AppCompatActivity implements EditPersonalData.DialogCommitListener {

    private SQLiteDatabase db;
    //private UserData user = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onFinishUserDialog(UserData userData) {
        Toast.makeText(this, "Hello, " + userData.getUserName(), Toast.LENGTH_SHORT).show();
        SaveUserDetails(userData);
    }

   /* @Override
    public void onEnterUserName(String user){
        Toast.makeText(this, "Hello, " + user, Toast.LENGTH_SHORT).show();


    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void CallTrainingSchedule()
    {

    }

    public void OnClickText(View view) {

        switch (view.getId()) {
            case R.id.textView1: Log.i("Switch", "1st text");
                break;
            case R.id.textView2:
                Log.i("Switch", "1st text");
                CallTrainingSchedule();
                break;
        }


        try {
            db = openOrCreateDatabase("wMonitor.db", MODE_PRIVATE, null);
        }
        catch(Exception e)
        {
            Log.i("Error", e.getMessage());

        }

        Log.i("Create Table text", getResources().getText(R.string.users_table_create).toString());

        try {
            db.execSQL(getResources().getText(R.string.users_table_create).toString());
        }
        catch(Exception e)
        {
            Log.i("Error", e.getMessage());

        }

        Cursor cursor = db.rawQuery("select * from users", new String[]{});

        if(cursor.getCount()==0)
        {
            Log.i("курсор", "пусто");
            EnterUserDetails();

        }
        else
        {
            Log.i("курсор", "чот есть");
        }



    }


    private void EnterUserDetails()
    {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }

        EditPersonalData editNameDialog = new EditPersonalData();
        editNameDialog.show(manager, "fragment_edit_name");
    }

    private boolean SaveUserDetails(UserData userData)
    {
        if(db!=null)
        {
            try {
                db.execSQL(getResources().getText(R.string.users_table_create).toString());
            }
            catch(Exception e)
            {
                Log.i("Error", e.getMessage());

            }

        }
        return true;
    }



}
