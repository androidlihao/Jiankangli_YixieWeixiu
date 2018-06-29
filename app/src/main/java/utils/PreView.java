package utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.hs.jiankangli_example1.My_activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bean.Pic_bean;

/**
 * Created by 李浩 on 2016/10/25.
 */
public class PreView {
    private Context context;
    private Bundle bundle;
    public PreView(Context context,Bundle bundle){
        this.context=context;
        this.bundle=bundle;
    }
    //将数据传输过来
    public void SendPreview(){
                //开启预览界面,调用js的方法，将值传过去
                Intent intent=new Intent(context,My_activity.class);
                JSONObject jsonObject=new JSONObject();
               String tag= bundle.getString("tag");
                try {
                    jsonObject.put("contentCategoriesId",bundle.getString("content_categories_id"));
                    JSONObject object1=new JSONObject();
                    object1.put("name",bundle.getString("manufacturer_name"));
                    object1.put("manufacturerId",bundle.getString("manufacturer_id"));
                    jsonObject.put("manufacturerModel",object1);
                    JSONObject object2=new JSONObject();
                    object2.put("name",bundle.getString("product_categories_name"));
                    object2.put("productCategoriesId",bundle.getString("product_categories_id"));
                    jsonObject.put("productCategoriesModel",object2);
                    JSONObject object3=new JSONObject();
                    object3.put("name",bundle.getString("content_categories_name"));
                    object3.put("contentCategoriesId",bundle.getString("content_categories_id"));
                    jsonObject.put("contentcategoriesModels",object3);
                    jsonObject.put("models",bundle.getString("models"));
                    jsonObject.put("keyWord",bundle.getString("key_word"));
                    JSONObject jsonObject2=new JSONObject();
                    jsonObject2.put("memberId",new JavaScriptObject(context).getMemberid(null));
                    jsonObject2.put("headPath",new JavaScriptObject(context).getHeadPath());//头像地址
                    jsonObject2.put("name",new Common_utils(context).getName());//名字
                    jsonObject2.put("nickname",new Common_utils(context).getNickName());//头像地址
                    jsonObject2.put("levelName",new Common_utils(context).getlv_name());
                    jsonObject.put("member",jsonObject2);
                    jsonObject.put("subsystem",bundle.getString("subsystem"));
                    jsonObject.put("summayContent",bundle.getString("summay_content"));//摘要
                    jsonObject.put("errCode",bundle.getString("err_code"));
                    jsonObject.put("faultDescription",bundle.getString("fault_description"));//错误描述
                    jsonObject.put("stepsResolve",bundle.getString("steps_resolve"));//解决步骤
                    jsonObject.put("possibleCauses",bundle.getString("possible_causes"));//设置可能的原因
                    jsonObject.put("explanation",bundle.getString("explanation"));//设置解释
                    jsonObject.put("result",bundle.getString("result"));//设置
                    jsonObject.put("workingPrinciple",bundle.getString("workingPrinciple"));//设置
                    jsonObject.put("title",bundle.getString("title"));//设置标题
                    jsonObject.put("solvingGuide",bundle.getString("solvingGuide"));//设置解决指导
                    jsonObject.put("singlename",bundle.get("singlename"));
                    jsonObject.put("commonly",bundle.get("commonly"));
                    jsonObject.put("abbreviation",bundle.get("abbreviation"));
                    JSONArray jsonArray = new JSONArray();
                    ArrayList PriviewList1=bundle.getStringArrayList("PriviewList1");//预览界面1
                    ArrayList PriviewList2=bundle.getStringArrayList("PriviewList2");//预览界面2
                    ArrayList PriviewList3=bundle.getStringArrayList("PriviewList3");//预览界面2
                    if(PriviewList1!=null&&!PriviewList1.isEmpty()){
                        for(int i=0;i<PriviewList1.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("imagePath",PriviewList1.get(i));
                            if(tag.equals("gongzuo")){
                                jsonObject1.put("type",4);
                            }else if(tag.equals("miji")){
                                jsonObject1.put("type",8);//如果当前页面为解决步骤，那么第一个就是描述
                            }else if(tag.equals("cuowu")){
                                jsonObject1.put("type",6);
                            }else if(tag.equals("guzhang")){
                                jsonObject1.put("type",1);
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                    }
                    if(PriviewList2!=null&&!PriviewList2.isEmpty()){
                        for(int i=0;i<PriviewList2.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("imagePath",PriviewList2.get(i));
                            if(tag.equals("miji")){
                                jsonObject1.put("type",9);//如果当前页面为解决步骤，解决步骤
                            }else if(tag.equals("cuowu")){
                                jsonObject1.put("type",5);//如果当前页面为解决步骤，解决步骤
                            }else if(tag.equals("guzhang")){
                                jsonObject1.put("type",2);
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                    }
                    if(PriviewList3!=null&&!PriviewList3.isEmpty()){
                        for(int i=0;i<PriviewList3.size();i++){
                            JSONObject jsonObject1=new JSONObject();
                            jsonObject1.put("imagePath",PriviewList3.get(i));
                            if(tag.equals("cuowu")){
                                jsonObject1.put("type",7);//如果当前页面为解决步骤，解决步骤
                            }else if(tag.equals("guzhang")){
                                jsonObject1.put("type",3);//如果当前页面为解决步骤，解决步骤
                            }
                            jsonObject1.put("weight",i);
                            jsonArray.put(jsonObject1);
                        }
                    }
                    if(Pic_bean.answer_frist_list!=null&&tag.equals("nonu")){
                        for(int i=0;i<Pic_bean.answer_frist_list.size();i++){
                            if (Pic_bean.answer_frist_list.get(i).size() == 3 && !Pic_bean.answer_frist_list.get(i).get(0).equals("tianjia") &&
                                    !Pic_bean.answer_frist_list.get(i).get(1).equals("failed") && !Pic_bean.answer_frist_list.get(i).get(2).equals("failed")) {
                                JSONObject js=new JSONObject();
                                js.put("imagePath",Pic_bean.answer_frist_list.get(i).get(1));
                                js.put("type",5);
                                js.put("weight",i);
                                jsonArray.put(js);
                            }
                        }
                    }
                    jsonObject.put("contentImages",jsonArray);
                    JSONObject js=new JSONObject();
                    js.put("getPreview_data",jsonObject);
                    Log.i("TAG", "SendPreview: "+js);
                    intent.putExtra("my","preview");
                    intent.putExtra("yulan",js.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                context.startActivity(intent);

    }
}
