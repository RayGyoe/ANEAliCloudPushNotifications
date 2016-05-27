package com.vsdevelop.air.extension.pushnotifications;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;
import android.util.Log;

public class ANEPushNotifications implements FREExtension
{
	
	private static String TAG = "ANEPushNotifications";

	public static FREContext context;
	
	
	@Override  
    public FREContext createContext(String arg)
	{  
		Log.d(TAG, TAG+".createContext");
        return context = new ANEPushNotificationsContext();  
    }
    @Override  
    public void dispose() 
    {
    	context = null;
    	Log.d(TAG, TAG+".dispose");
    }  
    @Override  
    public void initialize() 
    {
    	Log.d(TAG, TAG+".initialize");
    }  
}
