package com.cj.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.cj.core.dalegates.LatteDelegate;
import com.cj.core.net.RestClient;
import com.cj.core.net.callback.IError;
import com.cj.core.net.callback.IFailure;
import com.cj.core.net.callback.ISuccess;

public class MainDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main_layout;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
