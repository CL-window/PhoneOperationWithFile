package com.cl.slack.phoneoperation.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.cl.slack.phoneoperation.utils.L;

/**
 * <p>Description: 获取手机操作的服务 </p>
 * Created by slack on 2016/7/30 12:05 .
 */
public class MyPhoneOpreationAccessibleService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
//        L.i("onAccessibilityEvent...");
        int eventType = accessibilityEvent.getEventType();//事件类型
        L.i("packageName:" + accessibilityEvent.getPackageName() + "");//响应事件的包名，也就是哪个应用才响应了这个事件
//        L.i("source:" + accessibilityEvent.getSource() + "");//事件源信息
//        L.i("source class:" + accessibilityEvent.getClassName() + "");//事件源的类名，比如android.widget.TextView
//        L.i("event type(int):" + eventType + "");

        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
                L.i("event type:TYPE_NOTIFICATION_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
                L.i("event type:TYPE_WINDOW_STATE_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
                L.i("event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                L.i("event type:TYPE_VIEW_ACCESSIBILITY_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                L.i("event type:TYPE_GESTURE_DETECTION_END");
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                L.i("event type:TYPE_WINDOW_CONTENT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED://界面点击
                L.i("event type:TYPE_VIEW_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED://界面文字改动
                L.i("event type:TYPE_VIEW_TEXT_CHANGED");
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                L.i("event type:TYPE_VIEW_SCROLLED");
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                L.i("event type:TYPE_VIEW_TEXT_SELECTION_CHANGED");
            break;
        }

        for (CharSequence txt : accessibilityEvent.getText()) {
            L.i("text:" + txt);//输出当前事件包含的文本信息
        }

//        L.i("-------------------------------------------------------------");
    }

    @Override
    public void onInterrupt() {
        L.i("onInterrupt...");
    }
}
