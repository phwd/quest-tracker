package defpackage;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: CR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CR implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    public Object f7808a;

    public CR(Object obj) {
        this.f7808a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Bundle bundle;
        try {
            Object invoke = method.invoke(this.f7808a, objArr);
            if (method.getReturnType() == ApplicationInfo.class && (bundle = ((ApplicationInfo) invoke).metaData) != null) {
                bundle.remove("preloaded_fonts");
            }
            return invoke;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Reflection failed when proxying IPackageManager", e2);
        }
    }
}
