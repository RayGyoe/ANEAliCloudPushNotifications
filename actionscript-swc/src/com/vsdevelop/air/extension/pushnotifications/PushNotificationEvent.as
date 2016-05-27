package com.vsdevelop.air.extension.pushnotifications
{
	import flash.events.Event;
	
	public class PushNotificationEvent extends Event
	{
		
		public static const ACCOUNTADDTAG : String = "AccountAddTag";
		public static const ACCOUNTREMOVETAG : String = "AccountRemovetag";
		public static const INITALIBABASDK : String = "InitAlibabaSDK";
		public static const CLOUDPUSHSERVICE : String = "CloudPushService";
		public static const ONMESSAGE : String = "onMessage";
		public static const ONNOTIFICATION : String = "onNotification";
		public static const ONNOTIFICATIONOPENED : String = "onNotificationOpened";
		public static const ONNOTIFICATIONREMOVED : String = "onNotificationRemoved";
		
//		NO_NETWORK	1101	网络不可用
//		REG_FAIL	1056	注册/鉴权失败(请检查AppSecret配置)
//		INVAILD_APPKEY	1052	AppKey不存在
//		INVAILD_PACKAGENAME	1053	包名与配置的不符
//		INVAILD_APPSECRET	1054	Appsecret不合法
//		NETWORK_UNSTABLE	1105	网络不稳定或连接异常
//		INVAILD_SERVER_RETRUN	1115	不合法的服务端返回（请检查返回是否被篡改）
//		SYSTEM_UNKNOWN_ERROR	1108	系统未知异常
		
		public var data:*;
		
		public function PushNotificationEvent(type:String,DispatchData:*=null) {
			super(type);
			data=DispatchData;
		}
		
		override public function clone():Event 
		{
			return new PushNotificationEvent(type,data);
		}
	}
}