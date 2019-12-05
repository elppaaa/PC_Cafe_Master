package ac.kr.project_20191121;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Seatreservation extends Activity {
    ImageButton btnTomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.seatreservation);
        btnTomain=(ImageButton)findViewById(R.id.btnTomain);

        btnTomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Main.class);
                startActivity(intent);
            }
        });
    }
}
