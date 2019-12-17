package ac.kr.project_20191121.FoodOrder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageButton;
import android.widget.ListView;

import ac.kr.project_20191121.R;

public class Menu_Order extends Activity {
    private Activity activity;
    ImageButton btnToMain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_order);
        activity = this;
        btnToMain = (ImageButton) findViewById(R.id.btnTomain);
        //adapter -> listview 설정
        ListView listview;
        final MenuListAdapter adapter;
        adapter = new MenuListAdapter();
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), "box", "boxboxboxbox");
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round), "야", "너가 그렇게 공부를 잘해?");

/*
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               MenuItemList item = (MenuItemList) adapterView.getItemAtPosition(i);
               Drawable img = item.getImage();

                Dialog imgdialog = new Dialog(activity);
                imgdialog.setContentView(R.layout.image_dialog);
                ImageView im = (ImageView) imgdialog.findViewById(R.id.imgdialog_img);
                im.setImageDrawable(img);
                imgdialog.show();
            }
        }); //listview.setOnItemClicklistener

 */
        



    }


}
