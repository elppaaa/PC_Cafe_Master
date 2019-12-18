package ac.kr.project_20191121;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private String id, pass, name, phone, date;

    /*
    [*] USER_INFO TABLE
        _ID TEXT,
        _PASS TEXT,
        NAME TEXT,
        PHONE TEXT
        BIRTH DATE
     */


    /*
    To-do
    [*] change var date
        get date string from datepicker
     */

    private Context context;
    private SQLiteDatabase write_db ;
    private SQLiteDatabase read_db;

    public DBHelper(Context context) {
        super(context, "DB", null, 1);
        this.context = context;
        write_db = getWritableDatabase();
        read_db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE USER_INFO( _ID TEXT PRIMARY KEY , _PASS TEXT, NAME TEXT, PHONE TEXT, BIRTH TEXT ) ");
        sqLiteDatabase.execSQL(sb.toString());
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();

    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }


    public void InsertData(String _id, String _pass, String _name, String _phone, String _date) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO USER_INFO (");
        sb.append("_ID, _PASS, NAME, PHONE, BIRTH) ");
        sb.append(" VALUES (?, ?, ?, ?, ? ); ");

        write_db.execSQL(sb.toString(), new Object[]{
                _id, _pass, _name, _phone, _date});

    }

    public List GetAllData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, _PASS, NAME, PHONE, BIRTH FROM USER_INFO; ");
        Cursor c = read_db.rawQuery(sb.toString(), null);
        List people = new ArrayList<Person>();
        Person person = null;
        Date d = new Date();

        while (c.moveToNext()) {
            person = new Person(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
            people.add(person);
        }
        return people;
    }


    public boolean login(String _id, String _pw){
      List<Person> data  = GetAllData();
      for(Person p : data){
          if (_id.equals(p.getID()) && _pw.equals(p.getPASS()))
              return true;
      }
      return false;
    }




}
