package ac.kr.project_20191121.FoodOrder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ac.kr.project_20191121.R;

final public class Menu_Order extends Activity implements OnItemClick {
    private Activity activity;
    ImageButton btnToMain;
    Button btnReset, btnPay;
    TextView basketlist, baskettotal;
    int sum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_order);
        activity = this;
        btnToMain = (ImageButton) findViewById(R.id.btnTomain);
        btnReset = (Button) findViewById(R.id.menuorder_reset);
        btnPay = (Button) findViewById(R.id.menuorder_pay);
        basketlist = (TextView) findViewById(R.id.menuorder_list);
        baskettotal = (TextView) findViewById(R.id.menuorder_total);
        //adapter -> listview 설정
        ListView listview;
        final MenuListAdapter adapter;
        adapter = new MenuListAdapter(getApplicationContext(), this);
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menuitem_kimbob), "김밥", "2500");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menuitem_ramen), "라면", "3000");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.menuitem_dduck), "떡 사리", "500");


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basketlist.setText("");
                baskettotal.setText("");
                sum = 0;
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MenuBasket -> pay
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu_Order.this);
                if (sum != 0) {
                    builder.setMessage(sum + "원입니다.\n결제하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                /*
                                Pay page로 금액을 받아 엑티비티 시작.
                                 */
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //negative;
                                }
                            })
                            .create().show();
                } else {
                    builder.setMessage("메뉴를 선택 후 결제 버튼을 눌러주세요.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .create().show();
                }


            }
        });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }); //btnToMain OnClicklistener -> 종료

    }

    @Override
    public void onClick(String name, int cost) {
        basketlist.append(name + ", ");
        sum += cost;
        baskettotal.setText("합계 : " + sum);
    }
}

