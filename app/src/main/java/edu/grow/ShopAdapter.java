package edu.grow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<Shop> {

    private int resourceLayout;
    private Context mContext;

    public ShopAdapter(Context context, int resource, List<Shop> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Shop m = getItem(position);

        if (m != null) {
            ImageView item_img_view = (ImageView) v.findViewById(R.id.item_img);
            TextView item_name_view = (TextView) v.findViewById(R.id.item_name);
            Button item_price_view = (Button) v.findViewById(R.id.item_price);

            if (item_img_view != null) {
                item_img_view.setImageResource(m.thumbnail);
            }

            if (item_name_view != null) {
                item_name_view.setText(m.name);
            }

            if (item_price_view != null) {
                item_price_view.setText("가격 : "+m.price);
            }
        }

        return v;
    }

}
