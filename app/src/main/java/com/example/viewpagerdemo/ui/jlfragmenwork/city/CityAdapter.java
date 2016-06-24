package com.example.viewpagerdemo.ui.jlfragmenwork.city;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactItemInterface;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.ContactListAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.List;


public class CityAdapter extends ContactListAdapter {

    Context _context;
    BookCall bcal;
    List<ContactItemInterface> _items;

    public CityAdapter(Context _context, int _resource,
                       List<ContactItemInterface> _items, BookCall bcal) {
        super(_context, _resource, _items);
        this._context = _context;
        this._items = _items;
        this.bcal = bcal;
    }

  /*  @Override
    public int getCount() {
        DD.v("这是我的打印:"+_items.size());
        return _items.size();
    }*/

    public void populateDataForRow(View parentView, ContactItemInterface item, final int position) {
        View infoView = parentView.findViewById(R.id.infoRowContainer);
        TextView nicknameView = (TextView) infoView.findViewById(R.id.cityName);
        CircleImageView infoRowContainer = (CircleImageView) infoView.findViewById(R.id.cityimage);
        TextView city_tag = (TextView) infoView.findViewById(R.id.city_tag);
        final int state = item.getStute();
        if (state == 1) {
            city_tag.setText("开始聊天");
        } else if (state == 2) {
            city_tag.setText("邀请中...");
        } else if (state == 0) {
            city_tag.setText("邀请好友");
        }
        nicknameView.setText(item.getDisplayInfo());
        if (!item.getIcons().equals("")) {
            Picasso.with(_context).load(item.getIcons()).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(infoRowContainer);
        } else {
            Picasso.with(_context).load(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(infoRowContainer);
        }

        city_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.d("点击了==========" + bcal.toString());
                bcal.callbook(position, state);
            }
        });
        infoRowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.d("点击了==========infoRowContainer");
            }
        });

    }


    public interface BookCall {
        void callbook(int pos, int state);
    }

}
