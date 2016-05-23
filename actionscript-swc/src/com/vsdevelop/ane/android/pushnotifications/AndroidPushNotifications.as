package com.vsdevelop.ane.android.pushnotifications
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;

	public class AndroidPushNotifications  extends EventDispatcher 
	{
		
		private static var _instance:AndroidPushNotifications;
		
		private var _extCtx:ExtensionContext = null;
		
		
		public function AndroidPushNotifications()
		{
			if (!_instance)
			{
				if (this.isPushNotificationSupported)
				{
					
					_extCtx = ExtensionContext.createExtensionContext("com.vsdevelop.ane.android.pushnotifications", null);
					
					if (_extCtx != null)
					{
						_extCtx.addEventListener(StatusEvent.STATUS, onStatus);
					} else
					{
						trace('extCtx is null.');
					}
				}
				_instance = this;
			}
			else
			{
				throw Error( 'This is a singleton, use getInstance, do not call the constructor directly');
			}
		}
		
		public function get getContext():ExtensionContext
		{
			return _extCtx;
		}

		private function onStatus(e:StatusEvent):void 
		{
			if (this.isPushNotificationSupported)
			{
					this.dispatchEvent(new PushNotificationEvent(e.code,e.level));
					dispatchEvent(e.clone());
			}
		}
		
		
		public function init():Boolean
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("init");
			}
			return false;
		}
		public function getDeviceId():String
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("getDeviceId") as String;
			}
			return null;
		}
		
		public function getUtDid():String
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("getUtDid") as String;
			}
			return null;
		}
		public function bindAccount(name:String):Boolean
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("bindAccount",name);
			}
			return false;
		}
		public function unbindAccount(name:String):Boolean
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("unbindAccount");
			}
			return false;
		}
		public function addTag(tag:String):Boolean
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("addTag",tag);
			}
			return false;
		}
		public function removeTag(tag:String):Boolean
		{
			if (this.isPushNotificationSupported)
			{
				return	_extCtx.call("removeTag",tag);
			}
			return false;
		}
		
//		functionMap.put("init", new initAlibabaSDK());
//		functionMap.put("getDeviceId", new getDeviceId());
//		functionMap.put("getUtDid", new getUtDid());
//		functionMap.put("bindAccount", new bindAccount());
//		functionMap.put("unbindAccount", new unbindAccount());
//		functionMap.put("addTag", new addTag());
//		functionMap.put("removeTag", new removeTag());
		
		public function get isPushNotificationSupported():Boolean
		{
			var result:Boolean = (Capabilities.manufacturer.search('Android') > -1);
			trace('Android Push notification is'+(result ? ' ' : ' not ')+'supported');
			return result;
		}
		public static function getInstance() : AndroidPushNotifications
		{
			return _instance ? _instance : new AndroidPushNotifications();
		}
	}
}