adt -package -storetype pkcs12 -keystore cert.p12 -storepass fd -target ane ANEPushNotifications.ane extension.xml -swc ANEPushNotifications.swc -platform Android-ARM -C Android-ARM . -platform Android-x86 -C Android-x86 . -platform default -C default .

echo Complete