package ac.kr.project_20191121;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Payment extends Activity {
    Spinner spinner_card;
    ImageButton CardSlash;
    EditText edtCardNum, edtCardDueM, edtCardDueY, edtCardPw, edtCardJm;
    TextView edtCardCost;
    boolean is_newcard = false;
    DBHelper card_m;
    ArrayAdapter spinnerAdapter;
    ArrayList<Card> spinnerArray;
    int Cost;
    Intent intent;
    HashMap<String, Card> _hash;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        //onCreate
        intent = getIntent();
        Cost = intent.getIntExtra("cost",0);
        card_m = new DBHelper(this);
        _hash = card_m.GetAllCardMap();
        final ArrayList<String> nicklist = new ArrayList<>();
        /*
        for(String _nick :_hash.keySet()){
            nicklist.add(_nick);
        }
        */
        nicklist.addAll(_hash.keySet());
        nicklist.add("새로 추가");




        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, nicklist);
        edtCardCost = findViewById(R.id.edtCardCost);

        CardSlash = findViewById(R.id.btnCardSlash);
        spinner_card = findViewById(R.id.spinnerCard);
        edtCardNum = findViewById(R.id.edtCardNum);
        edtCardDueM = findViewById(R.id.edtCardDueM);
        edtCardDueY = findViewById(R.id.edtCardDueY);
        edtCardPw = findViewById(R.id.edtCardPw);
        edtCardJm = findViewById(R.id.edtCardJm);
        edtCardCost.setText("결제 금액 : " + Integer.toString(Cost));
        spinner_card.setAdapter(spinnerAdapter);


        spinner_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CardSlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText[] ed = new EditText[]{edtCardNum, edtCardDueM, edtCardDueY, edtCardPw, edtCardJm};
                //검사
                for (EditText _t :ed) {
                    if (_t.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "결제 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }




            }
        });
    }

}
