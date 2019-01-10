package io.github.vipul_08.retrofitupload;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUpload {

    static String resp;

    public static String upload(File file , HashMap<String , Object> valuePairs) {

        JSONObject jsonObject = new JSONObject(valuePairs);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(RetrofitUtils.getURL())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData(RetrofitUtils.getIMAGE_PARAM(), file.getName(), reqFile);
        RequestBody json = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        RetrofitClient client = retrofit.create(RetrofitClient.class);

        Call<ResponseBody> call = client.sendImageAndData(RetrofitUtils.getROUTE(),RetrofitUtils.getJSON_PARAM(),json,image);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resp =  response.body().toString();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resp = "Failed"+t.getMessage();
            }
        });
        return resp;
    }

    public static String upload(HashMap<String , Object> valuePairs) {
        JSONObject jsonObject = new JSONObject(valuePairs);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(RetrofitUtils.getURL())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RequestBody json = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

        RetrofitClient client = retrofit.create(RetrofitClient.class);

        Call<ResponseBody> call = client.sendDataOnly(RetrofitUtils.getROUTE(),RetrofitUtils.getJSON_PARAM(),json);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resp =  response.body().toString();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resp = "Failed"+t.getMessage();
            }
        });
        return resp;
    }

    public static String upload(File file) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(RetrofitUtils.getURL())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData(RetrofitUtils.getIMAGE_PARAM(), file.getName(), reqFile);

        RetrofitClient client = retrofit.create(RetrofitClient.class);

        Call<ResponseBody> call = client.sendImageOnly(RetrofitUtils.getROUTE(),image);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resp =  response.body().toString();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resp = "Failed"+t.getMessage();
            }
        });
        return resp;
    }
}
