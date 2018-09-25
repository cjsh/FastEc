package com.cj.core.net;

import android.content.Context;

import com.cj.core.net.callback.ISuccess;
import com.cj.core.net.callback.IError;
import com.cj.core.net.callback.IFailure;
import com.cj.core.net.callback.IRequest;
import com.cj.core.net.callback.RequestCallbacks;
import com.cj.core.ui.LatteLoader;
import com.cj.core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                             Map<String, Object> params,
                             IRequest iRequest,
                             ISuccess iSuccess,
                             IFailure iFailure,
                             IError iError,
                             RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.IREQUEST = iRequest;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBulider builder(){
        return new RestClientBulider();
    }

    private void request(HTTPMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if(IREQUEST != null){
            IREQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
                default:
                    break;
        }

        if(call != null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                IREQUEST,
                ISUCCESS,
                IFAILURE,
                IERROR,
                LOADER_STYLE
        );
    }

    public final void get(){
        request(HTTPMethod.GET);
    }

    public final void post(){
        request(HTTPMethod.POST);
    }

    public final void put(){
        request(HTTPMethod.PUT);
    }

    public final void delete(){
        request(HTTPMethod.DELETE);
    }
}
