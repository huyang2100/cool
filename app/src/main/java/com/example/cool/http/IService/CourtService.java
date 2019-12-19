package com.example.cool.http.IService;

import com.example.cool.http.response.CourtResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourtService {
    @GET("ydbg/organ.do?action=getCorp")
    Call<CourtResponse> getCourts();
}
