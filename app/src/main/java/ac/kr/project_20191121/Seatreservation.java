package ac.kr.project_20191121;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Seatreservation extends Activity {
    ImageButton btnTomain;
    SharedPreferences pref;
    SharedPreferences.Editor is_r;
    final static Integer[] origianl_posterID =  {R.drawable.seaticon1, R.drawable.seaticon2, R.drawable.seaticon3, R.drawable.seaticon4, R.drawable.seaticon5,
            R.drawable.seaticon6, R.drawable.seaticon7, R.drawable.seaticon8, R.drawable.seaticon9, R.drawable.seaticon10,
            R.drawable.seaticon11, R.drawable.seaticon12, R.drawable.seaticon13, R.drawable.seaticon14, R.drawable.seaticon15};
    final Integer[] posterID = {R.drawable.seaticon1, R.drawable.seaticon2, R.drawable.seaticon3, R.drawable.seaticon4, R.drawable.seaticon5,
            R.drawable.seaticon6, R.drawable.seaticon7, R.drawable.seaticon8, R.drawable.seaticon9, R.drawable.seaticon10,
            R.drawable.seaticon11, R.drawable.seaticon12, R.drawable.seaticon13, R.drawable.seaticon14, R.drawable.seaticon15};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seatreservation);
        pref = getSharedPreferences("is_r", MODE_PRIVATE);
        is_r = pref.edit();

                    /*
            SharedPreference에 데이터를 저장함
            is_r 이름으로 저장되며
            1은 예약됨 0은 사용 끝이다.
             */
        for (int i = 0; i < posterID.length; i++) {
            if (pref.getInt(Integer.toString(i), 0) == 1)
                posterID[i] = R.drawable.seaticonbook;
        }

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this, posterID);
        gv.setAdapter(gAdapter);

        btnTomain = (ImageButton) findViewById(R.id.btnTomain);

        btnTomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        Integer[] posterID;

        public MyGridAdapter(Context c, Integer[] posterID) {
            this.posterID = posterID;
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public long getItemID(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 220));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);



            imageView.setImageResource(posterID[position]);

            final int pos = position;
            //한번 클릭 예약화면, 두번 클릭 예약 해제.
            imageView.setOnTouchListener(new View.OnTouchListener() {
                GestureDetector dg = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        posterID[pos] = origianl_posterID[pos];
                        imageView.setImageResource(posterID[pos]);
                        is_r.putInt(Integer.toString(pos), 0).commit();
                        return true;
                    }

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        if (!(imageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.seaticonbook).getConstantState())) {
                            View dialogView = (View) View.inflate(Seatreservation.this, R.layout.seat_dialog, null);
                            AlertDialog.Builder dlg = new AlertDialog.Builder(Seatreservation.this);
                            ImageView ivSeat = (ImageView) dialogView.findViewById(R.id.ivSeat);
                            ivSeat.setImageResource(posterID[pos]);
                            dlg.setIcon(R.drawable.seaticon1);
                            dlg.setView(dialogView);
                            dlg.setPositiveButton("예약", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(), "예약되셨습니다.", Toast.LENGTH_SHORT).show();
                                    imageView.setImageResource(R.drawable.seaticonbook);
                                    is_r.putInt(Integer.toString(pos), 1).commit();
                                }
                            });
                            dlg.setNegativeButton("닫기", null);
                            dlg.show();
                        } else {
                            Toast.makeText(context, "이미 예약된 좌석입니다.", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    dg.onTouchEvent(motionEvent);
                    return true;
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            return imageView;
        }
    }

}
