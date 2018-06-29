package Adapter_package;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hs.jiankangli_example1.R;

import java.util.List;

import bean.PartsDetailsList;

/**
 * Created by 李浩 on 2017/7/26.
 */

public class parts_lists_adapter extends myBaseAdapter{

        private List list;
        private int id;

        public parts_lists_adapter(List list, int id) {
            super(list, id);
            this.id=id;
            this.list=list;
        }


        @Override
        public void fillData(int position, MyHolder myHolder) {
            PartsDetailsList.BodyBean.DataBean partsDetails= (PartsDetailsList.BodyBean.DataBean) list.get(position);
            Glide.with(myHolder.getmConvertView().getContext()).
                    load(partsDetails.getShowImage())
              .placeholder(android.R.drawable.ic_menu_gallery).error(android.R.drawable.ic_menu_mapmode).into(((ImageView) myHolder.findView(R.id.iv_parts_id)));
            ((TextView)myHolder.findView(R.id.tv_model_id)).setText(partsDetails.getManufacturerName()+" "+partsDetails.getName());
            ((TextView)myHolder.findView(R.id.tv_price_id)).setText(partsDetails.getMoneyUnit()+"  "+partsDetails.getPrice());
            ((TextView)myHolder.findView(R.id.tv_storage_id)).setText("库存量  "+partsDetails.getInventory());
        }

}
