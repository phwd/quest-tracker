package defpackage;

import java.lang.reflect.InvocationTargetException;

/* renamed from: yS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5765yS {

    /* renamed from: a  reason: collision with root package name */
    public static final BW0 f11681a = new BW0();

    public static Class b(ClassLoader classLoader, String str) {
        BW0 bw0 = f11681a;
        Class cls = (Class) bw0.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        bw0.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassNotFoundException e) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e2) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        }
    }

    public AbstractComponentCallbacksC3550lS a(ClassLoader classLoader, String str) {
        try {
            return (AbstractComponentCallbacksC3550lS) c(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new C3208jS(AbstractC2531fV.g("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
