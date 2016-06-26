package com.example.viewpagerdemo.ui.jlfragmenwork.city;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.units.StringUtils;
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

    //重写
    public void populateDataForRow(View parentView, ContactItemInterface item, final int position) {

        View infoView = parentView.findViewById(R.id.infoRowContainer);
        TextView nicknameView = (TextView) infoView.findViewById(R.id.cityName);
        CircleImageView infoRowContainer = (CircleImageView) infoView.findViewById(R.id.cityimage);
        TextView city_tag = (TextView) infoView.findViewById(R.id.city_tag);
        infoRowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.v("===================================0");
            }
        });
        final int state = item.getStute();
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
        Log.e("x","---------Item:"+item.getDisplayInfo() + item.getThinkesId());
        if(StringUtils.isEmpty(item.getDisplayInfo())){
            nicknameView.setText(item.getThinkesId());
        }else {
            nicknameView.setText(item.getDisplayInfo());
        }
        if (!item.getIcons().equals("")) {
            Picasso.with(_context).load(item.getIcons()).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(infoRowContainer);
        } else {
            infoRowContainer.setImageResource(R.drawable.aliwx_default_photo_right);
        }

        /*city_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.d("点击了==========" + bcal.toString());
                if (state != 2) {
                    bcal.callbook(position, state);
                }
            }
        });
        infoRowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DD.d("点击了==========infoRowContainer");
            }
        });*/

    }


    public interface BookCall {
        void callbook(int pos, int state);
    }




}
