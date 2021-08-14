package com.example.tkmticketunion.model.api;

import com.example.tkmticketunion.model.domain.ChoiceCategory;
import com.example.tkmticketunion.model.domain.ChoiceContents;
import com.example.tkmticketunion.model.domain.HomeCategory;
import com.example.tkmticketunion.model.domain.HomeContent;
import com.example.tkmticketunion.model.domain.GetTicketParam;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.model.domain.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<HttpResponse<List<HomeCategory>>> getHomeCategories();

    /**
     * 获取首页分类内容列表
     * @param categoryId
     * @param page
     * @return
     */
    @GET("discovery/{materialId}/{page}")
    Call<HttpResponse<List<HomeContent>>> getHomeCategoryContents(@Path("materialId") int categoryId, @Path("page") int page);

    /**
     * 获取淘口令
     * @param param
     * @return
     */
    @POST("tpwd")
    Call<HttpResponse<Ticket>> getTicket(@Body GetTicketParam param);

    /**
     * 获取精选分类
     * @return
     */
    @GET("recommend/categories")
    Call<HttpResponse<List<ChoiceCategory>>> getChoiceCategories();

    /**
     * 获取精选分类内容列表
     * @param categoryId
     * @return
     */
    @GET("recommend/{categoryId}")
    Call<HttpResponse<ChoiceContents>> getChoiceCategoryContents(@Path("categoryId") String categoryId);
}
