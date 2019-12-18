package ac.kr.project_20191121;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class Join2 extends Activity {
    // myDBHelper myHelper;//객체참조변수, 인스턴스생성
    private EditText jId, jPw, jName, jPhone, jBirth;
    private ImageButton btnRegistration;
    private DBHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join2);//디자인 화면에 배치

        //Button, ImageButton, Button
        jId = (EditText) findViewById(R.id.jId);
        jPw = (EditText) findViewById(R.id.jPw);
        jName = (EditText) findViewById(R.id.jName);
        jPhone = (EditText) findViewById(R.id.jPhone);
        jBirth = (EditText) findViewById(R.id.jBirth);
        btnRegistration = (ImageButton) findViewById(R.id.jregistration);
        //DB
        mDB = new DBHelper(this);
        //회원가입화면에서 ID, PW EditText에 입력하고 회원가입버튼을 누르면 1건 insert 레코드 등록


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빈칸 검증.
                for(EditText e : new EditText[]{jId, jPw, jName, jPhone, jBirth}){
                    if (e.getText().toString().isEmpty())  {
                        Toast.makeText(getApplicationContext(), "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                mDB.InsertData(jId.getText().toString(), jPw.getText().toString(),jName.getText().toString(), jPhone.getText().toString(), jBirth.getText().toString());

                //회원1건 가입완료 사용자는 아무런 메세지가 없으니 "가입됨"토스트메세지 줌
                Toast.makeText(getApplicationContext(), "가입됨", Toast.LENGTH_SHORT).show();
                //화면이동 로그인화면으로 이동
                finish();

            }
        });

    }//onCreate






    /*
    public class myDBHelper extends SQLiteOpenHelper {
        //생성자 클래스명과 동일 메소드
        public  myDBHelper(Context context){//import class
            super(context,"LoginDB",null,1);//DB생성
        }//클래스에 에러 onCreate(테이블생성) onUpgrade(테이블 있으면 삭제 테이블 다시 생성필수 메소드
        //커서위치 code-override
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Joininfo(uId TEXT,uPassword TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Joininfo");//테이블 있으면 삭제 다시 생성
            onCreate(db);
        }
    }//myDBHelper
*/


}//MainActivity
