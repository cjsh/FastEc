package com.cj.core.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

public class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getIntance();
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getIntance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
