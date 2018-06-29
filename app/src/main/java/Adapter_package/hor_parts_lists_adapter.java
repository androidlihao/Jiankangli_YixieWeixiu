package Adapter_package;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hs.jiankangli_example1.R;

import java.util.List;

import bean.Recommend;

/**
 * Created by 李浩 on 2017/7/26.
 */

public class hor_parts_lists_adapter extends myBaseAdapter{

        private List list;
        private int id;

        public hor_parts_lists_adapter(List list, int id) {
            super(list, id);
            this.id=id;
            this.list=list;
        }


        @Override
        public void fillData(int position, MyHolder myHolder) {
            Recommend.BodyBean.DataBean recommend= (Recommend.BodyBean.DataBean) list.get(position);
            Glide.with(myHolder.getmConvertView().getContext()).
                    load(recommend.getShowImage())
                    .placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_mapmode).into(((ImageView) myHolder.findView(R.id.imageView)));
            ((TextView)myHolder.findView(R.id.tv_model_id)).setText(recommend.getManufacturerName()+" "+recommend.getName());
            ((TextView)myHolder.findView(R.id.tv_price_id)).setText("¥  "+recommend.getPrice());
        }

}
