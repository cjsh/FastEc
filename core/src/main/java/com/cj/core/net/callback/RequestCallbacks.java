package com.cj.core.net.callback;

import android.content.Loader;
import android.os.Handler;

import com.cj.core.ui.LatteLoader;
import com.cj.core.ui.LoaderStyle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String>{

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(
                      IRequest iRequest,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      IError iError,
                      LoaderStyle style) {
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(ISUCCESS != null)
                    ISUCCESS.onSuccess(response.body());
            }else{
                if(IERROR != null)
                    IERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(IFAILURE != null){
            IFAILURE.onFailure();
        }

        if(IREQUEST != null)
            IREQUEST.onRequestEnd();
        stopLoading();
    }

    private void stopLoading(){
        if(LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
