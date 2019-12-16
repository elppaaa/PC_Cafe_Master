package ac.kr.project_20191121.FoodOrder;

import android.graphics.drawable.Drawable;

public class MenuItemList {
    private Drawable itemImage;
    private String itemName;
    private String itemEx;

    public void setImage(Drawable img) {
        itemImage = img;
    }
    public void setName(String title) {
        itemName = title;
    }
    public void setEx(String ex) {
        itemEx = ex;
    }

    public Drawable getImage() {
        return this.itemImage;
    }
    public String getName() {
        return this.itemName;
    }
    public String  getEx() {
        return this.itemEx;
    }

}
