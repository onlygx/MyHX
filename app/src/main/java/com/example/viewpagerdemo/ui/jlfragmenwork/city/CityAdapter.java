package com.example.viewpagerdemo.ui.jlfragmenwork.city;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.List;


public class CityAdapter extends ContactListAdapter {

    Context _context;
    List<ContactItemInterface> _items;

    public CityAdapter(Context _context, int _resource,
                       List<ContactItemInterface> _items) {
        super(_context, _resource, _items);
        this._context = _context;
        this._items = _items;
    }

    //重写
    public void populateDataForRow(View parentView, ContactItemInterface item, final int position) {

        View infoView = parentView.findViewById(R.id.infoRowContainer);
        TextView nicknameView = (TextView) infoView.findViewById(R.id.cityName);
        CircleImageView infoRowContainer = (CircleImageView) infoView.findViewById(R.id.cityimage);
        TextView city_tag = (TextView) infoView.findViewById(R.id.city_tag);
        //String phone=item.getPhone();
        final int state = item.getStute();

        //DD.v(item.getDisplayInfo() + "==" + state+"==="+phone);
        if (state == 1) {
            city_tag.setText("开始聊天");
            city_tag.setBackgroundResource(R.drawable.shape_bt_grenn_corners);
        } else if (state == 2) {
            city_tag.setBackgroundResource(R.drawable.shape_enbel_false);
            city_tag.setText("邀请中...");
        } else if (state == 0) {
            city_tag.setBackgroundResource(R.drawable.shape_bt_grenn_corners);
            city_tag.setText("邀请好友");
        }


        if (item.getDisplayInfo() != null && !item.getDisplayInfo().equals("")) {
            nicknameView.setText(item.getDisplayInfo());
        } else {
            nicknameView.setText(item.getThinkeSId());
        }


        if (!item.getIcons().equals("") && item.getIcons()!=null) {
            Picasso.with(_context).load(item.getIcons()).placeholder(R.drawable.touxiang03)
                    .error(R.drawable.touxiang03).into(infoRowContainer);
        } else {
            infoRowContainer.setImageResource(R.drawable.touxiang03);
        }

    }

}
