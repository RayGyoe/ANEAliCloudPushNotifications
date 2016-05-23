##ANEAliCloudPushNotifications##

Adobe AIR For Android Aliyun PushNotifications

### com.vsdevelop.ane.android.pushnotifications ###


    <android>
    <manifestAdditions><![CDATA[<manifest android:installLocation="auto">
			<uses-feature android:required="true" android:name="android.hardware.touchscreen.multitouch" />
			<!--<uses-sdk android:minSdkVersion="14" android:targetSdkVersion="21" />-->
			<!-- 访问网络 -->
			<uses-permission android:name="android.permission.INTERNET" />
			<!-- 允许写入外部存储卡 -->
			<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
			<!-- 允许通话时静音 -->
			<uses-permission android:name="android.permission.READ_PHONE_STATE" />
			<!-- 禁止睡眠 -->
			<uses-permission android:name="android.permission.WAKE_LOCK" />
			<!-- 禁用键盘锁 -->
			<uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
			<!-- 拍照权限 -->
			<uses-permission android:name="android.permission.CAMERA"/>
			<!-- 录音 -->
			<uses-permission android:name="android.permission.RECORD_AUDIO"/>
			<!-- 获取网络状态 -->
			<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
			<!-- 获取WiFi状态 -->
			<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
			<!-- 状态栏控制 -->
			<uses-permission android:name="android.permission.STATUS_BAR" />
			<!-- 使用蓝牙 -->
			<!-- <uses-permission android:name="android.permission.BLUETOOTH"/> -->
			<!-- 蓝牙管理 -->
			<!-- <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/> -->
			<!-- 使用振动 -->
			<uses-permission android:name="android.permission.VIBRATE" />
			<!-- 使用SIP视频 -->
			<!-- <uses-permission android:name="android.permission.USE_SIP" /> -->
			<!-- 访问GPS -->
			<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
			<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
			
			
			<!--推送辅助权限-->
			<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
			<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
			<!--Android 6.0版本可去除，用于选举信息（通道复用）的同步-->
			<uses-permission android:name="android.permission.WRITE_SETTINGS" />
			<!--选举使用，当应用有删除或者更新时需要重新选举，复用推送通道-->
			<uses-permission android:name="android.permission.BROADCAST_PACKAGE_CHANGED" />
			<uses-permission android:name="android.permission.BROADCAST_PACKAGE_REPLACED" />
			<uses-permission android:name="android.permission.RESTART_PACKAGES" />
			<!--补偿通道小米PUSH使用，不用可去除-->
			<uses-permission android:name="android.permission.GET_TASKS" />
			<!--补偿通道GCM使用，不使用可去除-->
			<uses-permission android:name="android.permission.GET_ACCOUNTS" />
			<!--允许监听启动完成事件-->
			<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
			
			<!-- android:name="com.vsdevelop.ane.android.pushnotifications.ANEPushNotificationsApplication"-->
			<application>
				<!--sandbox-->
				<meta-data android:name="com.alibaba.app.appkey" android:value="xxxxxx" />
				<meta-data android:name="com.alibaba.app.appsecret" android:value="xxxxxx" />
				
				<!--release-->
				<!--<meta-data android:name="com.alibaba.app.appkey" android:value="xxxx" />
				<meta-data android:name="com.alibaba.app.appsecret" android:value="xxxx" />-->
				
				<!-- 通道保持服务 -->
				<service android:name="com.alibaba.sdk.android.push.ChannelService"
					android:exported="true" android:process=":channel">
					<intent-filter>
						<action android:name="com.taobao.accs.intent.action.SERVICE"/>
					</intent-filter>
					<intent-filter>
						<action android:name="org.agoo.android.intent.action.PING_V4" />
						<category android:name="taobao" />
					</intent-filter>
				</service>
	
				<!-- 消息接收服务 -->
				<service android:name="com.alibaba.sdk.android.push.MsgService"
					android:exported="false">
					<intent-filter>
						<action android:name="com.taobao.accs.intent.action.RECEIVE" />
					</intent-filter>
				</service>
				
				<!-- 连接心跳保持监听器 -->
				<receiver android:name="anet.channel.heartbeat.HeartbeatManager$Receiver" >
					<intent-filter>
						<action android:name="anetwork.channel.intent.action.COMMAND" />
					</intent-filter>
				</receiver>
	
				<!-- 消息接收监听器 -->
				<receiver android:exported="true" android:name="com.vsdevelop.ane.android.pushnotifications.PushNotificationsMessageReceiver">
					<intent-filter>
						<action android:name="com.taobao.accs.intent.action.COMMAND" />
					</intent-filter>
					<intent-filter>
						<action android:name="com.taobao.taobao.intent.action.COMMAND" />
					</intent-filter>
					<intent-filter>
						<action android:name="org.agoo.android.intent.action.RECEIVE" />
					</intent-filter>
					<intent-filter>
						<action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
					</intent-filter>
					<intent-filter>
						<action android:name="android.intent.action.USER_PRESENT" />
					</intent-filter>
					<intent-filter>
						<action android:name="android.intent.action.BOOT_COMPLETED" />
					</intent-filter>
					<intent-filter>
						<action android:name="android.intent.action.PACKAGE_REMOVED" />
						<data android:scheme="package" />
					</intent-filter>
				</receiver>
			</application>
			</manifest>]]></manifestAdditions> 
		</android>

阿里云DEMO：
[https://github.com/aliyun/alicloud-android-demo](https://github.com/aliyun/alicloud-android-demo)