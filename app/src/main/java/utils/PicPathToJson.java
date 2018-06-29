package utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.hs.jiankangli_example1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Created by 李浩 on 2017/1/18.
 */

public class PicPathToJson {
    public static JSONObject getHaveWant(int a, JSONObject jsonObject,LinkedList<LinkedList<String>> list){
        try {
            if(list!=null){
                JSONArray jsonArray;
                if (!(jsonObject.toString().contains("images"))){
                    jsonArray=new JSONArray();
                }else{
                    jsonArray=jsonObject.getJSONArray("images");
                }
                for(int i=0;i<list.size();i++){
                    if (list.get(i).size() == 3 && !list.get(i).get(0).equals("tianjia") &&
                            !list.get(i).get(1).equals("failed") && !list.get(i).get(2).equals("failed")) {
                        JSONObject js=new JSONObject();
                        js.put("imagePath",list.get(i).get(2));
                        js.put("type",a);
                        jsonArray.put(js);
                        Log.i("TAG", "getPicPath: "+jsonArray.length());
                    }
                }
                jsonObject.put("images",jsonArray);
                Log.i("TAG", "getPicPath: "+jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject getPicPath(int a, JSONObject jsonObject,LinkedList<LinkedList<String>> list){
        try {
            if(list!=null){
                JSONArray jsonArray;
                if (!(jsonObject.toString().contains("pictures"))){
                     jsonArray=new JSONArray();
                }else{
                    jsonArray=jsonObject.getJSONArray("pictures");
                }
                for(int i=0;i<list.size();i++){
                    if (list.get(i).size() == 3 && !list.get(i).get(0).equals("tianjia") &&
                            !list.get(i).get(1).equals("failed") && !list.get(i).get(2).equals("failed")) {
                        JSONObject js=new JSONObject();
                        js.put("image_path",list.get(i).get(2));
                        js.put("type",a);
                        js.put("weight",jsonArray.length());
                        jsonArray.put(js);
                        Log.i("TAG", "getPicPath: "+jsonArray.length());
                    }
                }
                jsonObject.put("pictures",jsonArray);
                Log.i("TAG", "getPicPath: "+jsonObject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static ArrayList getPreViewPath(LinkedList<LinkedList<String>> list){
        ArrayList arrayList=new ArrayList();
        if (list!=null){
        for(int i=0;i<list.size();i++){
            if (list.get(i).size() == 3 && !list.get(i).get(0).equals("tianjia") &&
                    !list.get(i).get(1).equals("failed") && !list.get(i).get(2).equals("failed")) {
                    arrayList.add(list.get(i).getFirst());
            }
        }
        }
        return arrayList;
    }
    private  Activity activity;
    private  LinkedList<LinkedList<String>> list;
    private  ArrayList<String> deleteList;
    public PicPathToJson(Activity activity,LinkedList<LinkedList<String>> list,ArrayList<String> deleteList){
        this.activity=activity;
        this.list=list;
        this.deleteList=deleteList;
    }
    public LinkedList<LinkedList<String>> GridViewOnItem(final GridView gv_id, final ninelayout_Adapter adapter) {
        gv_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==gv_id.getAdapter().getCount()-1&&(list.get(i).getFirst()).equals("tianjia")){//为最后一项，并且最后一项为可以添加
                    new Pic_MultiImageSelector_util(list,activity).getMultiImageSelector(2,5-(list.size()-1));
                }
            }
        });
        return list;
    }
    public ArrayList<String> GridViewDelete(final GridView gv_id, final ninelayout_Adapter adapter){
        gv_id.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if(!(list.get(i).getFirst()).equals("tianjia")){//如果当前不为添加
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setVisibility(View.VISIBLE);
                    gv_id.getChildAt(i).findViewById(R.id.iv_delte_id).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //如果当前图片上传成功了，将所有删除的记录下来,用于图片删除优化操作
                            if(list.get(i).size()==3&&!list.get(i).get(1).equals("failed")){
                                deleteList.add(list.get(i).getLast());
                            }
                            Log.i("TAG", "GridViewDelete: "+deleteList);
                            list=new Pic_MultiImageSelector_util(null,null).deleteUrlList(list,i);//去删除数据，然后刷新适配器
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                return false;
            }
        });
        return deleteList;
    }
}
