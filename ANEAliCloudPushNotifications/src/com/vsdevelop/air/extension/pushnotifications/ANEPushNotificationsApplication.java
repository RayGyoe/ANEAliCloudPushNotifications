package com.vsdevelop.air.extension.pushnotifications;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;

import android.app.Application;
import android.content.Context;
import android.util.Log;


public class ANEPushNotificationsApplication extends Application{
	
	
	 private static final String TAG = "ANEPushNotificationsApplication";

	   @Override
	  public void onCreate() {
	        super.onCreate();
	        initOneSDK(this);
	    }
	   
	    /**
	     *  
	     *
	     * @param applicationContext
	     */
	    private void initOneSDK(final Context applicationContext) {
	        Log.w(TAG, "get App Package Name: " + applicationContext.getPackageName());
	        //AlibabaSDK.setEnvironment(Environment.ONLINE);
	        AlibabaSDK.asyncInit(applicationContext, new InitResultCallback() {

	            public void onSuccess() {
	                Log.d(TAG, "init onesdk success");
	                //alibabaSDK初始化成功后，初始化移动推送通道
	                initCloudChannel(applicationContext);
	            }

	            public void onFailure(int code, String message) {
	                Log.e(TAG, "init onesdk failed : " + message);
	            }
	        });
	    }

	    /**
	     *  
	     *
	     * @param applicationContext
	     */
	    private void initCloudChannel(Context applicationContext) {
	    	ANEPushNotificationsContext.PushService = AlibabaSDK.getService(CloudPushService.class);
	    	
	    	ANEPushNotificationsContext.PushService.setLogLevel(2);
	    	ANEPushNotificationsContext.PushService.register(applicationContext, new CommonCallback() {
	            public void onSuccess() {
	                Log.i(TAG, "init CloudPushService success, device id: " + ANEPushNotificationsContext.PushService.getDeviceId() +
	                        ", UtDid: " + ANEPushNotificationsContext.PushService.getUTDeviceId() +
	                        ", Appkey: " + AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY));
	            }
	            public void onFailed(String errorCode, String errorMessage) {
	                Log.d(TAG, "init cloudchannel failed. errorcode:" + errorCode + ", errorMessage:" + errorMessage);
	            }
	        });


	    }

}
