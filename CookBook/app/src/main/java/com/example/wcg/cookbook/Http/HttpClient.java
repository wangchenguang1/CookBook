package com.example.wcg.cookbook.Http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wcg.cookbook.CKApplication;
import com.example.wcg.cookbook.CKNetStatus;
import com.example.wcg.cookbook.Model.Classfy.CKClassifyData;
import com.example.wcg.cookbook.Model.Classfy.ErrorModel;
import com.example.wcg.cookbook.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wcg on 16/3/7.
 */


// 单例类
public class HttpClient  {

    // 网络请求url
    public static final String api_key = "09b836726e8224a7abfe31b82129ddbf";
    public static final String name_url = "http://apis.baidu.com/tngou/cook/name";
    public static final String detail_url = "http://apis.baidu.com/tngou/cook/show";
    public static final String list_url = "http://apis.baidu.com/tngou/cook/list";
    public static final String classify_url = "http://apis.baidu.com/tngou/cook/classify";

    public static final String pre_image_url = "http://tnfs.tngou.net/image";

    // 网络请求回调接口
    public interface CallBack {
        //执行回调操作的方法
        void success(Object object);
        void fail(ErrorModel errorModel);
    }

    public static  CKNetStatus netStatus;    // 当前网络状态
    private static HttpClient instance;      // 当前实例
    private static Context mCtx;             // Application的context
    private static RequestQueue queue;       // 请求队列

    //构造方法，传入Application的context
    private HttpClient(Context context){
        mCtx = context;
        queue = Volley.newRequestQueue(mCtx);
    }

    //单例
    public static synchronized HttpClient getInstance(Context context) {
        if (instance == null){
            instance = new HttpClient(context);
        }
        return instance;
    }

    // get方法
    public static HttpClient getInstance() {
        return instance;
    }

    public static RequestQueue getQueue() {
        return queue;
    }

    //请求数据
    public void requestData(String baseUrl, HashMap parameterMap  , final Class ResponseClass ,final CallBack callBack){

        if (!isNetworkAvailable()){
            Toast.makeText(mCtx, "网络未连接", Toast.LENGTH_SHORT).show();
            ErrorModel errorModel1 = ErrorModel.requestFail("无网络");
            callBack.fail(errorModel1);
            return;
        }

        String url = generateUrl(parameterMap, baseUrl);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("json:", jsonObject.toString());
                String errNum = null;
                Gson gson = new Gson();
                try {
                    errNum =  jsonObject.getString("errNum");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (errNum != null){
                    ErrorModel errorModel = gson.fromJson(jsonObject.toString(), ErrorModel.class);
                    callBack.fail(errorModel);
                } else {
                    Object responseObject = gson.fromJson(jsonObject.toString(), ResponseClass);
                    callBack.success(responseObject);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                String str = volleyError.getMessage();
                if (str == null){
                    str = "请求失败";
                }
                ErrorModel errorModel = ErrorModel.requestFail(str);
                callBack.fail(errorModel);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("apikey", HttpClient.api_key);
                return map;
            }
        };
        jsonObjectRequest.setTag(baseUrl);  //设置tag
        queue.add(jsonObjectRequest);
        queue.start();
    }

    public String generateUrl(HashMap hashMap, String baseUrl){

        Iterator iterator = hashMap.entrySet().iterator();
        baseUrl = baseUrl + "?";
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();

            String parameters =  key + "=" + val + "&";
            baseUrl = baseUrl + parameters;
        }

        baseUrl  = baseUrl.substring(0,baseUrl.length()-1);

        return baseUrl;
    }

    // 判断当前网络是否连接
    public boolean isNetworkAvailable()
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void cancleRequest(Object tag){
        queue.cancelAll(tag);
    }

}
