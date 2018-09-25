package com.cj.core.net;

import android.content.Context;

import com.cj.core.net.callback.ISuccess;
import com.cj.core.net.callback.IError;
import com.cj.core.net.callback.IFailure;
import com.cj.core.net.callback.IRequest;
import com.cj.core.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBulider {

    private String mUrl = null;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RestClientBulider(){

    }

    public final RestClientBulider url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBulider params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBulider params(String key,Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RestClientBulider raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBulider onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBulider success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBulider error(IError iError){
        this.mIError = iError;
        return this;
    }

    public final RestClientBulider failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBulider loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBulider loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody,mContext,mLoaderStyle);
    }
}
