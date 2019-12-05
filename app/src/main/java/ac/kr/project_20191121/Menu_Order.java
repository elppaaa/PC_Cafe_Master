package ac.kr.project_20191121;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

public class Menu_Order extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_order);

        //adapter -> listview 설정
        ListView listview;
        MenuListAdapter adapter;
        adapter = new MenuListAdapter();
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.mipmap.ic_launcher), "box", "boxboxboxbox");



    }
}
