package com.cj.fastec;

import android.app.Activity;
import android.os.Bundle;

import com.cj.core.activitys.ProxyActivity;
import com.cj.core.dalegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainDelegate();
    }

}
