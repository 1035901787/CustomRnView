package com.customrnview.customview;

import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by sujialong on 2017/9/1.
 */
public class ToastViewManager extends ReactContextBaseJavaModule{

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String TestEventName = "TestEventName";
    private static ReactApplicationContext reactContext_;

    public ToastViewManager(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext_ = reactContext;
    }

    @Override
    public String getName() {
        return "CustomToastView";
    }

    //返回常量给js端使用
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_SHORT);
        constants.put(TestEventName, TestEventName);
        return constants;
    }

    //创建show方法给js端调用
    @ReactMethod
    public void show(String message, int duration){
        Toast.makeText(getReactApplicationContext(), message, duration).show();
        //发送事件
        this.setEvent();
    }

    //使用回调函数
    @ReactMethod
    public void getNativeClass(Callback callback){
        callback.invoke("使用回调函数");
    }

    //使用promise回调
    @ReactMethod
    public void getArguments(Boolean isResolve,Promise promise){
        WritableMap map = Arguments.createMap();
        map.putString("name", "Arno");
        map.putString("age", "25");

        if(isResolve){
            promise.resolve(map);
        }else{
            promise.reject(map.toString());
        }
    }

    //发送事件,js端使用事件监听接收
    public void setEvent(){
        WritableMap params = Arguments.createMap();
        params.putString("name", "Jack");
        reactContext_
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(TestEventName, params);
    }
}
