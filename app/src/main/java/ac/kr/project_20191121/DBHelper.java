package ac.kr.project_20191121;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private String id, pass, name, phone, date;
    private String Card_num, Card_cvc, Card_my, Card_pw, Card_jm;

    /*
    [*] USER_INFO TABLE
        _ID TEXT, 0
        _PASS TEXT, 1
        NAME TEXT, 2
        PHONE TEXT, 3
        BIRTH TEXT, 4
        TIME INTEGER 5
     */


    /*
    [*] CARD_INFO TABLE
        NUM TEXT, 0
        NICK TEXT, 1
        DUE TEXT, 2
        PW TEXT, 3
        JM TEXT 4
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
        sb.append("CREATE TABLE USER_INFO( _ID TEXT PRIMARY KEY , _PASS TEXT, NAME TEXT, PHONE TEXT, BIRTH TEXT , TIME INTEGER) ");
        sqLiteDatabase.execSQL(sb.toString());
        sb = new StringBuffer();
        sb.append("CREATE TABLE CARD_INFO( NUM TEXT PRIMARY KEY, NICK TEXT, DUE TEXT, PW TEXT, JM TEXT );");
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
        sb.append("_ID, _PASS, NAME, PHONE, BIRTH, TIME) ");
        sb.append(" VALUES (?, ?, ?, ?, ?, 0 ); ");

        write_db.execSQL(sb.toString(), new Object[]{
                _id, _pass, _name, _phone, _date });

    }

    public void insertCard(Card _c){
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO CARD_INFO ( ");
        sb.append("NUM, NICK, DUE, PW, JM )");
        sb.append(" VALUES (?, ?, ?, ?, ? );");
        write_db.execSQL(sb.toString(), new Object[]{
                _c.GetNum(), _c.GetNick(), _c.GetDue(), _c.GetPw(), _c.GetJm() });
    }

    //nick으로 Card Object 찾기
    public Card GetCardInfo(String _nick){
        for(Card _c : (List<Card>) GetAllCard()){
           if(_c.GetNick().equals(_nick))
               return _c;
        }
        return null;
    }

    public List GetAllCard(){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT NUM, NICK, DUE, PW, JM, FROM CARD_INFO; ");
        Cursor c = read_db.rawQuery(sb.toString(), null);
        List card_list = new ArrayList<Card>();
        Card card = null;
        while(c.moveToNext()){
            card = new Card(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
            card_list.add(card);
        }
        return card_list;

    }

    public List GetAllData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, _PASS, NAME, PHONE, BIRTH, TIME FROM USER_INFO; ");
        Cursor c = read_db.rawQuery(sb.toString(), null);
        List people = new ArrayList<Person>();
        Person person = null;

        while (c.moveToNext()) {
            person = new Person(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getInt(5));
            people.add(person);
        }
        return people;
    }


    public Person login(String _id, String _pw){
      List<Person> data  = GetAllData();
      for(Person p : data){
          if (_id.equals(p.getID()) && _pw.equals(p.getPASS())) {
              return p;
          }
      }
      return null;
    }
    //ID가 있으면 true
    public boolean checkID(String _id){
        for(Person p : (List<Person>) GetAllData()){
            if(p.getID().equals(_id))
                return true;
        }
        return false;
    }



}
