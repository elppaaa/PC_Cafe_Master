package ac.kr.project_20191121;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.channels.InterruptedByTimeoutException;

import ac.kr.project_20191121.FoodOrder.Menu_Order;

public class Main extends Activity {
    ImageButton btnSeatcheck, btnSeatreservation, btnAddtime, btnMenuorder, btnMakers;
    TextView mainhello;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSeatcheck = (ImageButton) findViewById(R.id.btnSeatCheck);
        btnSeatreservation = (ImageButton) findViewById(R.id.btnSeatreservation);
        btnAddtime = (ImageButton) findViewById(R.id.btnAddtime);
        btnMenuorder = (ImageButton) findViewById(R.id.btnMenuorder);
        btnMakers = (ImageButton) findViewById(R.id.btnMakers);
        mainhello = (TextView) findViewById(R.id.mainhello);
        user = getSharedPreferences("login",MODE_PRIVATE).getString("login_id","");
        mainhello.setText(user + "님 좋은시간 보내세요.");
        btnSeatcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Seatcheck.class);
                startActivity(intent);
            }
        });

        btnAddtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Addtime.class);
                startActivity(intent);
            }
        });
        btnSeatreservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Seatreservation.class);
                startActivity(intent);
            }
        });
        btnMenuorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu_Order.class);
                startActivity(intent);
            }
        });

        //제작자들 더블 클릭하면 로그아웃
        btnMakers.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gd = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                    pref.edit().clear().commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    acfinish();
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    return super.onSingleTapConfirmed(e);
                }
            });
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gd.onTouchEvent(motionEvent);
                return true;
            }
        });


    }
    void acfinish(){
        finish();
    }
}
