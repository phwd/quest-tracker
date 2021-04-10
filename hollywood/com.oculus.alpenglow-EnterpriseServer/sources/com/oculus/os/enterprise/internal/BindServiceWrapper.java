package com.oculus.os.enterprise.internal;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.UserHandle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindServiceWrapper {
    public static Class cContextImpl;
    public static Method cContext_bindServiceAsUser;
    public static Method cContext_getUserId;

    static {
        try {
            cContextImpl = Class.forName("android.app.ContextImpl");
            cContext_getUserId = Context.class.getMethod("getUserId", new Class[0]);
            cContext_bindServiceAsUser = Context.class.getMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Context getContextImpl(Context context) {
        while (context != null) {
            if (!cContextImpl.isAssignableFrom(context.getClass())) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                return context;
            }
        }
        throw new RuntimeException("Failed to find ContextImpl for context");
    }

    public static boolean bindServiceWithHandler(Context context, Intent intent, ServiceConnection serviceConnection, int i, Handler handler) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Context contextImpl = getContextImpl(context);
        return ((Boolean) cContext_bindServiceAsUser.invoke(contextImpl, intent, serviceConnection, Integer.valueOf(i), handler, UserHandle.getUserHandleForUid(((Integer) cContext_getUserId.invoke(contextImpl, new Object[0])).intValue()))).booleanValue();
    }
}
