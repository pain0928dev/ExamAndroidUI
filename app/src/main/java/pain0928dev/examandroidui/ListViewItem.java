package pain0928dev.examandroidui;

import android.graphics.drawable.Drawable;

/**
 * Created by ljh0928 on 2018. 3. 2..
 */

public class ListViewItem {
    private Drawable icon;
    private String title;
    private String desc;

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public Drawable getIcon(){
        return icon;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc(){
        return desc;
    }
}
