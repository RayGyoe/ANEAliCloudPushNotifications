package com.vsdevelop.air.extension.pushnotifications;

import java.util.Map;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import android.content.Context;
import android.util.Log;

public class PushNotificationsMessageReceiver extends MessageReceiver {
	
	private static final String TAG = "PushNotificationsMessageReceiver";
	
	//消息接收
	protected void onMessage(Context context, CPushMessage message)
	{
		Log.e(TAG, "onMessage : " +message.getTitle()+"   =  "+ message.getContent());
		
		if(ANEPushNotifications.context!=null){
			ANEPushNotifications.context.dispatchStatusEventAsync("onMessage", "title="+message.getTitle()+"||content="+ message.getContent());
		}
	}
	//通知接收
	protected void onNotification(Context context,String title, String summary, Map <String, String > extraMap)
	{
		Log.e(TAG, "onNotification : " + title + "  =  "+summary);
		if(ANEPushNotifications.context!=null){
			ANEPushNotifications.context.dispatchStatusEventAsync("onNotification", "title="+title+"||summary="+ summary);
		}
	}
	//用于在接收通知后，用户需要自定义操作的场景，或者用于获取扩展字段。 title 通知的标题，summary 通知的内容 ，extraMap 通知做携带的扩展字段
	protected void onNotificationOpened(Context context, String title, String summary, String extraMap)
	{
		Log.e(TAG, "onNotificationOpened : " + title + "  =  "+summary);
		if(ANEPushNotifications.context!=null){
			ANEPushNotifications.context.dispatchStatusEventAsync("onNotificationOpened", "title="+title+"||summary="+ summary);
		}
	}
	//在用户打开（某个）notification的时候，会回调该方法。title 通知的标题，summary 通知的内容，extraMap 扩展字段 ，以json字段形式表示
	protected void onNotificationRemoved(Context context, String messageId)
	{
		Log.e(TAG, "onNotificationRemoved : " +messageId);
		if(ANEPushNotifications.context!=null){
			ANEPushNotifications.context.dispatchStatusEventAsync("onNotificationRemoved", "messageid="+messageId);
		}
	}

}
