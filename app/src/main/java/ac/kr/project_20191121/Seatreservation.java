package ac.kr.project_20191121;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Seatreservation extends Activity {
    ImageButton btnTomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seatreservation);

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        btnTomain=(ImageButton)findViewById(R.id.btnTomain);

        btnTomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Main.class);
                startActivity(intent);
            }
        });
    }
    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }
        public int getCount(){
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

        Integer[] posterID = {R.drawable.seaticon1, R.drawable.seaticon2, R.drawable.seaticon3, R.drawable.seaticon4, R.drawable.seaticon5,
                R.drawable.seaticon6, R.drawable.seaticon7, R.drawable.seaticon8, R.drawable.seaticon9, R.drawable.seaticon10,
                R.drawable.seaticon11, R.drawable.seaticon12, R.drawable.seaticon13, R.drawable.seaticon14, R.drawable.seaticon15};

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(250,220));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterID[position]);

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(Seatreservation.this, R.layout.seat_dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Seatreservation.this);
                    ImageView ivSeat = (ImageView)dialogView.findViewById(R.id.ivSeat);
                    ivSeat.setImageResource(posterID[pos]);
                    dlg.setIcon(R.drawable.seaticon1);
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("예약", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(), "예약되셨습니다.", Toast.LENGTH_SHORT).show();
                                    imageView.setImageResource(R.drawable.seaticonbook);
                                }
                            });
                            dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });
            return imageView;
        }
    }

}
