package com.vsdevelop.air.extension.pushnotifications;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.Environment;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;

import android.util.Log;


public class ANEPushNotificationsContext extends FREContext 
{
	private static String TAG = "ANEPushNotificationsContext";
	
	public static  CloudPushService PushService = null;
	/**
	 * 
	 */
	public ANEPushNotificationsContext() {
		Log.d(ANEPushNotificationsContext.TAG, ANEPushNotificationsContext.TAG+".ANEPushNotificationsContext");
	}
	
	
	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		
		Log.d(ANEPushNotificationsContext.TAG, ANEPushNotificationsContext.TAG+".getFunctions");
		
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();
		
		//init function
		functionMap.put("init", new initAlibabaSDK());
		functionMap.put("getDeviceId", new getDeviceId());
		functionMap.put("getUtDid", new getUtDid());
		functionMap.put("bindAccount", new bindAccount());
		functionMap.put("unbindAccount", new unbindAccount());
		functionMap.put("addTag", new addTag());
		functionMap.put("removeTag", new removeTag());
		
		
		return functionMap;
	}
	

	@Override
	public void dispose() 
	{
		
	}
	// 获取 deviceId
	class getDeviceId implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null)return null;
			String deviceid = ANEPushNotificationsContext.PushService.getDeviceId();
			try {
				return FREObject.newObject(deviceid);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	class getUtDid implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null)return null;
			String UTDeviceId = ANEPushNotificationsContext.PushService.getUTDeviceId();
			try {
				return FREObject.newObject(UTDeviceId);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	class getAppkey implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null)return null;
			String Appkey = AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY);
			try {
				return FREObject.newObject(Appkey);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
	class bindAccount implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null){
				
				try {
					return FREObject.newObject(false);
				} catch (FREWrongThreadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			try {
				String alias = args[0].getAsString();
				ANEPushNotificationsContext.PushService.bindAccount(alias);
				return FREObject.newObject(true);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FRETypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREInvalidObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				return FREObject.newObject(false);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	class unbindAccount implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null){
				
				try {
					return FREObject.newObject(false);
				} catch (FREWrongThreadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			try {
				ANEPushNotificationsContext.PushService.unbindAccount();
				return FREObject.newObject(true);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
	class addTag implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null){
				
				try {
					return FREObject.newObject(false);
				} catch (FREWrongThreadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			
			try {
				String tagString = args[0].getAsString();
				ANEPushNotificationsContext.PushService.addTag(tagString, new CommonCallback() {	                 		
					@Override
				    public void onSuccess() {
						Log.d(ANEPushNotificationsContext.TAG, "@用户增加标签成功");
						
						ANEPushNotifications.context.dispatchStatusEventAsync("AccountAddTag", "Success");
				    }
					@Override
				    public void onFailed(String errorCode, String errorMessage) {
				        Log.d(ANEPushNotificationsContext.TAG, "@用户增加标签失败，原因：" + errorMessage);
				        
				        ANEPushNotifications.context.dispatchStatusEventAsync("AccountAddTag", "Failed:errorCode="+errorCode);
				    }
				});
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FRETypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREInvalidObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				return FREObject.newObject(true);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}


	class removeTag implements FREFunction
	{
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			if(ANEPushNotificationsContext.PushService==null){
				
				try {
					return FREObject.newObject(false);
				} catch (FREWrongThreadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			
			try {
				String tagString = args[0].getAsString();
				ANEPushNotificationsContext.PushService.removeTag(tagString, new CommonCallback() {	                 		
					@Override
				    public void onSuccess() {
				        //remove tag
						Log.d(ANEPushNotificationsContext.TAG, "@用户删除标签成功");
						
						ANEPushNotifications.context.dispatchStatusEventAsync("AccountRemovetag", "Success");
				    }
					@Override
				    public void onFailed(String errorCode, String errorMessage) {
				        Log.d(ANEPushNotificationsContext.TAG, "@用户删除标签失败，原因：" + errorMessage);
				        
				        ANEPushNotifications.context.dispatchStatusEventAsync("AccountRemovetag", "Failed:errorCode="+errorCode);
				    }
				});
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FRETypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREInvalidObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				return FREObject.newObject(true);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
	/////
	class initAlibabaSDK implements FREFunction
	{
		
		public FREObject call(FREContext ctx, FREObject[] args) 
		{
			Log.d(ANEPushNotificationsContext.TAG, "AlibabaPushService init");
			
			
//			if(ANEPushNotificationsContext.PushService==null){
//				
//				try {
//					
//					//AlibabaSDK.setEnvironment(Environment.ONLINE);
//					AlibabaSDK.asyncInit(ANEPushNotifications.context.getActivity().getApplicationContext(), new InitResultCallback() {
//						
//					    public void onSuccess() {
//					        Log.d(ANEPushNotificationsContext.TAG, "init onesdk success");
//					        //alibabaSDK
//					        ANEPushNotifications.context.dispatchStatusEventAsync("InitAlibabaSDK", "Success");
//					        ANEPushNotificationsContext.PushService =  AlibabaSDK.getService(CloudPushService.class);
//					        if(ANEPushNotificationsContext.PushService==null)
//					        {
//					        	ANEPushNotifications.context.dispatchStatusEventAsync("CloudPushService", "Failed Error");
//					        }else{
//					        	//Log.d(ANEPushNotificationsContext.TAG, "getDeviceId:"+AlibabaSDK.getService(CloudPushService.class).getDeviceId());
//					        }
//					        ///register
//					        ANEPushNotificationsContext.PushService.setLogLevel(3);
//					    	ANEPushNotificationsContext.PushService.register(ANEPushNotifications.context.getActivity().getApplicationContext(), new CommonCallback() {
//					            public void onSuccess() {
//					            	final String pushInfo = "deviceid:"+ANEPushNotificationsContext.PushService.getDeviceId()+"||UtDid:"+ANEPushNotificationsContext.PushService.getUTDeviceId()+"||Appkey:"+AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY);
//					            	ANEPushNotifications.context.dispatchStatusEventAsync("CloudPushService", pushInfo);
//					                Log.i(ANEPushNotificationsContext.TAG, pushInfo);
//					            }
//					            public void onFailed(String errorCode, String errorMessage) {
//					            	ANEPushNotifications.context.dispatchStatusEventAsync("CloudPushService", "Failed||errorcode:"+errorCode);
//					                Log.d(ANEPushNotificationsContext.TAG, "init cloudchannel failed. errorcode:" + errorCode + ", errorMessage:" + errorMessage);
//					            }
//					        });
//					    }
//
//					    public void onFailure(int code, String message) {
//					        Log.e(ANEPushNotificationsContext.TAG, "init onesdk failed : " + message);
//					        
//					        ANEPushNotifications.context.dispatchStatusEventAsync("InitAlibabaSDK", "Failure");
//					    }
//					});
//					try {
//						return FREObject.newObject(true);
//					} catch (FREWrongThreadException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} catch (Error er) {
//					ANEPushNotifications.context.dispatchStatusEventAsync("debug", "AlibabaSDK.asyncInit error");
//					// TODO Auto-generated catch block
//					try {
//						return FREObject.newObject(false);
//					} catch (FREWrongThreadException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}

			Log.d(ANEPushNotificationsContext.TAG, "AlibabaPushService end");
			
			try {				
				return FREObject.newObject(ANEPushNotificationsContext.PushService==null?false:true);
			} catch (FREWrongThreadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}

	}
}
