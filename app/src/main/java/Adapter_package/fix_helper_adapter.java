package Adapter_package;

import android.widget.TextView;

import com.example.hs.jiankangli_example1.R;

import java.util.List;

import bean.fix_helper_bean;

/**
 * Created by 李浩 on 2017/6/29.
 */

public class fix_helper_adapter extends myBaseAdapter<fix_helper_bean.BodyBean.DataBean>{
    private List<fix_helper_bean.BodyBean.DataBean> list;
    private int id;

    public fix_helper_adapter(List<fix_helper_bean.BodyBean.DataBean> list, int id) {
        super(list, id);
        this.id=id;
        this.list=list;
    }


    @Override
    public void fillData(int position, MyHolder myHolder) {
        ((TextView)myHolder.findView(R.id.tv_pp_id)).setText(list.get(position).getManufacturerName());
        ((TextView)myHolder.findView(R.id.tv_title_id)).setText(list.get(position).getTitle());
    }
}
