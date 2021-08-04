package com.example.tkmticketunion.model.api;

import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.model.domain.GetTicketParam;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.model.domain.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 获取首页分类内容列表
     * @param categoryId
     * @param page
     * @return
     */
    @GET("discovery/{materialId}/{page}")
    Call<HttpResponse<List<Content>>> getCategoryContents(@Path("materialId") int categoryId, @Path("page") int page);

    /**
     * 获取淘口令
     * @param param
     * @return
     */
    @POST("tpwd")
    Call<HttpResponse<Ticket>> getTicket(@Body GetTicketParam param);
}
