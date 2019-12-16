package ac.kr.project_20191121.FoodOrder;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ac.kr.project_20191121.R;

public class MenuListAdapter extends BaseAdapter {
    private ArrayList<MenuItemList> listItemList = new ArrayList<MenuItemList>();
    public View.OnClickListener conClickListener;
    Context context;
    public MenuListAdapter(Context context) {
        this.context = context;
    }


    int resourceId;

    public MenuListAdapter() {

    }
    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*
        View에 대한 부분을 설정
         */
        final int pos = i;
        final Context context = viewGroup.getContext();

        // "listview_item" Layout을 inflate하여 view 참조 획득.
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_order_listitem, viewGroup, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView iconImageView = (ImageView) view.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) view.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) view.findViewById(R.id.textView2) ;
        Button btnAdd = (Button) view.findViewById(R.id.btn_Menu_Add);

        // Data Set(listItemList)에서 i에 위치한 데이터 참조 획득
        MenuItemList listItem = listItemList.get(i);

        // 아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listItem.getImage());
        titleTextView.setText(listItem.getName());
        descTextView.setText(listItem.getEx());


        iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog imgdialog = new Dialog(context);
                imgdialog.setContentView(R.layout.image_dialog);
                ImageView im = (ImageView) imgdialog.findViewById(R.id.imgdialog_img);
                im.setImageDrawable(iconImageView.getDrawable());
                imgdialog.show();
            }
        });





        return view;
    }

    public void addItem(Drawable img, String Name, String ex) {
        MenuItemList item = new MenuItemList();

        item.setImage(img);
        item.setName(Name);
        item.setEx(ex);

        listItemList.add(item);
    }
}
