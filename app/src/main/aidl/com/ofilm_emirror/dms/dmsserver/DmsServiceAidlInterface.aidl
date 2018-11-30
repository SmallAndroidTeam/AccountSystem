// DmsServiceAidlInterface.aidl
package com.ofilm_emirror.dms.dmsserver;

// Declare any non-default types here with import statements

interface DmsServiceAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void sayHelloWorld();
    int getDmsMode();
    void setDmsIdle();
    void setDmsNormal();
    void setDmsRegister();
    int getRegisterResult();
    int getRegisterFaceID();
    void setDmsLogin();
    int getLoginFaceID();
}
