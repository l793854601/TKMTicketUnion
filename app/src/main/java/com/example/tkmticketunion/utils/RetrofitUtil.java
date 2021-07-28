package com.example.tkmticketunion.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class RetrofitUtil {

    private static RetrofitUtil sInstance = null;

    private Retrofit mRetrofit;

    /**
     * 私有化构造方法
     */
    private RetrofitUtil() {
        initRetrofit();
    }

    /**
     * 单例
     * @return
     */
    public static RetrofitUtil getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitUtil();
                }
            }
        }

        return sInstance;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {

        //  创建OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        //  创建Retrofit
        mRetrofit = new Retrofit.Builder()
                //  使Retrofit使用OkHttpClient请求
                .client(client)
                //  设置请求地址
                .baseUrl(Constants.BASE_URL)
                //  添加Gson：json转model
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
