package Adapter_package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hs.jiankangli_example1.R;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class skill_or_brand_adapter extends BaseAdapter {
    // 填充数据的list
    private LinkedList<String> list;
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer,Boolean> isSelected;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    private LinkedList<LinkedList<String>> skill_or_brand_List;
    // 构造器
    public skill_or_brand_adapter(LinkedList<String> list, Context context, LinkedList<LinkedList<String>> skill_or_brand_List) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer,Boolean>();//初始化一个布局
        this.skill_or_brand_List=skill_or_brand_List;
        initDate();
    }
    // 初始化isSelected的数据
    private void initDate() {
        ArrayList arrayList=new ArrayList();
        for(int i=0;i<skill_or_brand_List.size();i++){
            arrayList.add(skill_or_brand_List.get(i).getFirst());
        }
        for (int i = 0; i < list.size(); i++) {//将数据源遍历，然后根据数据源的长度来将所有的变为
            if(arrayList.contains(list.get(i))){
                getIsSelected().put(i,true);//将已经有的改为选择状态
            }else {
                getIsSelected().put(i,false);//将所有的都变为未选择的状况
            }
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.listviewitem, null);
            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (SwitchButton) convertView.findViewById(R.id.item_cb);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        // 设置list中TextView的显示
        holder.tv.setText(list.get(position));
        //根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        skill_or_brand_adapter.isSelected = isSelected;
    }
    public static class ViewHolder {
        TextView tv;
        public SwitchButton cb;
    }
}