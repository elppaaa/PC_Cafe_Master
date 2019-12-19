package ac.kr.project_20191121;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    CheckBox chkAddCard;
    boolean is_newcard = false;
    DBHelper card_m;
    ArrayAdapter spinnerAdapter;
    ArrayList<Card> spinnerArray;
    int Cost;
    Intent intent;
    HashMap<String, Card> _hash;
    Card now_card;


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
        chkAddCard = findViewById(R.id.chkAddCard);
        edtCardCost.setText("결제 금액 : " + Integer.toString(Cost));
        spinner_card.setAdapter(spinnerAdapter);


        spinner_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                now_card = card_m.GetCard((String)spinner_card.getItemAtPosition(i));
                Toast.makeText(getApplicationContext(),(String)spinner_card.getItemAtPosition(i),Toast.LENGTH_SHORT).show();


                if(((String)spinner_card.getItemAtPosition(i)).equals("새로 추가")){
                    for(EditText ed : new EditText[] {edtCardNum, edtCardDueM, edtCardDueY, edtCardPw, edtCardJm}){
                        ed.setText("");
                        is_newcard = true;
                    }
                }else{
                    is_newcard = false;
                    edtCardNum.setText(now_card.GetNum());
                    edtCardDueM.setText(now_card.GetDue().substring(0,2));
                    edtCardDueY.setText(now_card.GetDue().substring(2,4));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        CardSlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText[] ed = new EditText[]{edtCardNum, edtCardDueM, edtCardDueY, edtCardPw, edtCardJm};
                String Due = edtCardDueM.getText().toString() + edtCardDueY.getText().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                //alertdialog
                //검사
                for (EditText _t :ed) {
                    if (_t.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "결제 정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(is_newcard){
                    //새카드 결제
                    if(chkAddCard.isChecked()) {
                        final EditText newCardName = new EditText(Payment.this);

                        builder.setMessage("카드 닉네임")
                                .setView(newCardName)
                                .setPositiveButton("저장", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).create().show();


                        card_m.InsertCard(new Card(edtCardNum.getText().toString(), newCardName.getText().toString() ,Due
                                , edtCardPw.getText().toString(), edtCardJm.getText().toString()));

                    }
                    //등록 후 이벤트
                    AlertDialog.Builder bd = new AlertDialog.Builder(Payment.this);
                    bd.setMessage("결제되었습니다.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }).show();
                    finish();

                } else{
                    //기존카드 내용 비교
                    if(card_m.chkCard(now_card)){
                        AlertDialog.Builder bd = new AlertDialog.Builder(Payment.this);
                        bd.setMessage("결제되었습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                }).show();

                    }else {
                        Toast.makeText(getApplicationContext(), "카드 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }

}
