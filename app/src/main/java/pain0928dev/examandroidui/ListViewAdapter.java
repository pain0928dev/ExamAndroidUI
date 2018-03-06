package pain0928dev.examandroidui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pain0928dev.examandroidui.sqlite3.Sqlite3Activity;

/**
 * Created by ljh0928 on 2018. 3. 2..
 */

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount(){
        return listViewItems.size();
    }

    @Override
    public View getView(final int position, View converView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(converView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate((R.layout.custom_listview), parent, false);
        }

        ImageView iconImag = (ImageView)converView.findViewById(R.id.ivIcon);
        final TextView tvTitle = (TextView)converView.findViewById(R.id.tvMsg1);
        final TextView tvDesc = (TextView)converView.findViewById(R.id.tvMsg2);

        ListViewItem listViewItem = listViewItems.get(position);

        iconImag.setImageDrawable(listViewItem.getIcon());
        tvTitle.setText(listViewItem.getTitle());
        tvDesc.setText(listViewItem.getDesc());

        converView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, String.valueOf(position) + " Name:" + tvTitle.getText().toString() + ", " + tvDesc.getText().toString(), Toast.LENGTH_SHORT).show();

                if(position == 0){
                    // SQLite3 Test UI
                    Intent intent = new Intent(view.getContext(), Sqlite3Activity.class);
                    context.startActivity(intent);
                } else if(position == 1){

                } else if(position == 2){
                    Intent intent = new Intent(view.getContext(), DatePickerActivity.class);
                    context.startActivity(intent);
                }


            }
        });

        return converView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int posistion){
        return listViewItems.get(posistion);
    }

    public void addItem(Drawable icon, String title, String desc){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        listViewItems.add(item);
    }
}
