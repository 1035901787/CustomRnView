package com.customrnview.customview;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/**
 * Created by sujialong on 2017/9/1.
 */
public class TextViewManager extends SimpleViewManager<TextView> {

    //该方法用于js端导出时，使用的控件名称
    @Override
    public String getName() {
        return "CustomTextView";
    }

    //该方法用于创建ui控件实例
    @Override
    protected TextView createViewInstance(ThemedReactContext reactContext) {
        final TextView textView = new TextView(reactContext);
//        final ThemedReactContext myContext = reactContext;
        //注册点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "MyMessage哈哈哈--自定义");
                ReactContext reactContext = (ReactContext) textView.getContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                        textView.getId(),
                        "topChange",
                        event);
            }
        });
        return textView;
    }

    @ReactProp(name="text")
    public void setText(TextView textView,String text){
        textView.setText(text);
    }

    @ReactProp(name="textSize")
    public void setTextSize(TextView view,float fontSize){
        view.setTextSize(fontSize);
    }

    @ReactProp(name = "textColor",defaultInt = Color.BLACK)
    public void setTextColor(TextView view,int textColor){
        view.setTextColor(textColor);
    }
}
