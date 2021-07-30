package com.example.tkmticketunion.model.api;

import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.model.domain.HttpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络请求接口
 */
public interface API {

    /**
     * 获取首页分类
     * @return
     */
    @GET("discovery/categories")
    Call<HttpResponse<List<Category>>> getHomeCategories();

    @GET("discovery/{materialId}/{page}")
    Call<HttpResponse<List<Content>>> getCategoryContents(@Path("materialId") int categoryId, @Path("page") int page);
}