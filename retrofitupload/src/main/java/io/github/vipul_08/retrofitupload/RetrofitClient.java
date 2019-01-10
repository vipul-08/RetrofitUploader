package io.github.vipul_08.retrofitupload;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitClient {

    @Multipart
    @POST("{route}")
    Call<ResponseBody> sendImageAndData(
            @Path("route") String route,
            @Path("paramJson") String paramJson,
            @Part("{paramJson}") RequestBody json,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("{route}")
    Call<ResponseBody> sendImageOnly(
            @Path("route") String route,
            @Part MultipartBody.Part photo
    );

    @POST("{route")
    Call<ResponseBody> sendDataOnly(
            @Path("route") String route,
            @Path("paramJson") String paramJson,
            @Part("{paramJson}") RequestBody json
    );

}
