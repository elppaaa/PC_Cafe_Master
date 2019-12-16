package ac.kr.project_20191121;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ac.kr.project_20191121.FoodOrder.Menu_Order;

public class Main extends Activity {
    ImageButton btnSeatcheck, btnSeatreservation, btnAddtime, btnMenuorder, btnMakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnSeatcheck = (ImageButton) findViewById(R.id.btnSeatCheck);
        btnSeatreservation = (ImageButton) findViewById(R.id.btnSeatreservation);
        btnAddtime = (ImageButton) findViewById(R.id.btnAddtime);
        btnMenuorder = (ImageButton) findViewById(R.id.btnMenuorder);
        btnMakers = (ImageButton) findViewById(R.id.btnMakers);

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
    }
}
