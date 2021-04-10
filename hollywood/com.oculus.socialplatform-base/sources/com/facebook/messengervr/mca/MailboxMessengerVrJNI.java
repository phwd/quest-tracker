package com.facebook.messengervr.mca;

import X.AnonymousClass0l0;
import java.util.Map;

public class MailboxMessengerVrJNI {
    public static final native Object dispatchOIJJOOZZZZ(int i, int i2, long j, long j2, Object obj, Object obj2, boolean z, boolean z2, boolean z3, boolean z4);

    public static final native void dispatchVIJJOOO(int i, int i2, long j, long j2, Object obj, Object obj2, Object obj3);

    public static final native void dispatchVIOOO(int i, int i2, Object obj, Object obj2, Object obj3);

    public static final native void dispatchVIOOOOOOOOOZZ(int i, int i2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, boolean z, boolean z2);

    public static final native void dispatchVJOO(int i, long j, Object obj, Object obj2);

    public static final native void dispatchVOO(int i, Object obj, Object obj2);

    public static final native Map<String, String> getNotificationStrings(int i);

    static {
        AnonymousClass0l0.A06("messengervrmcamailboxmessengervrjni");
    }
}
