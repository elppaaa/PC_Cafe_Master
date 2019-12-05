package ac.kr.project_20191121;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Join2 {

    EditText edtId, edtPass;
    ImageButton btnJoin, btnLogin;
    int IDflag = 0;
    int Pwflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJoin=(ImageButton)findViewById(R.id.joinBtn);
        btnLogin=(ImageButton) findViewById(R.id.loginBtn);
        edtId=(EditText)findViewById(R.id.edtId);
        edtPass=(EditText)findViewById(R.id.edtPw);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Join2.class);
                startActivity(intent);

            }
        }); //btnjoin2
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Select문으로 Cursor저장 읽기전용으로 DB열어서 읽어옴
                //데이터베이스 Join.java에서 회원가입과 비슷하고 DB사용되는 화면임
                sqlDB=myHelper.getReadableDatabase();//읽기전용을 DB열기
                Cursor cursor;//인스턴스
                cursor=sqlDB.rawQuery("SELECT * FROM Joininfo;",null);
                String edt1=null;//임시변수 화면상에 EditText입력한 ID 임시저장, PW임시저장
                String pass1=null;
                String edt2=null;
                String pass2=null; //DB에서 가져온 ID, PW를 임시저장할 변수 선언
                //모든 레코드에 ID, 비밀번호가 있는 지 확인함
                while(cursor.moveToNext()){
                    edt1=edtId.getText().toString();//화면상에 사용자가 입력한 ID PW
                    pass1=edtPass.getText().toString();
                    edt2=cursor.getString(0);//DB 레코드의 ID저장
                    pass2=cursor.getString(1);//DB레코드의 DB저장

                    if(edt2.equals(edt1)){//아이디 일치
                        IDflag=1;
                        if(pass2.equals(pass1)){//아이디 비밀번호 둘다 일치
                            //정상회원인증 회원인증 확인 완료, 다시 메인화면 열기
                            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            Pwflag=1;//패스워드 일치 1
                            Intent intent1 =new Intent(getApplicationContext(),Main.class);
                            startActivity(intent1);
                            break;//while
                        }
                        else//아이디는 일치 비번은 불일치
                        {
                            Toast.makeText(getApplicationContext(),"비밀번호를 확인하세요",
                                    Toast.LENGTH_SHORT).show();
                            break;//while종료
                        }
                    }
                }//while
                if(IDflag==0&& Pwflag==0){
                    Toast.makeText(getApplicationContext(),"등록된 ID가 없습니다"
                            ,Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                sqlDB.close();
            }
        });
    }
}
