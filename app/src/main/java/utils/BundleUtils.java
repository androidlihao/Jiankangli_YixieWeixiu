package utils;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by 李浩 on 2017/1/13.
 */

public class BundleUtils {
    public static JSONObject bundleToJsonObject(Bundle bundle,JSONObject jsonObject){
        Set<String> set = bundle.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            try {
                jsonObject.put(key,bundle.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.i("TAGS", "bundleToJsonObject: "+jsonObject);
        return jsonObject;
    }
}
