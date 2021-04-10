package com.facebook.msys.mci;

import X.AnonymousClass006;
import X.AnonymousClass1Kc;
import X.AnonymousClass1Kf;
import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.msys.util.McfReferenceHolder;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
public class NotificationCenter {
    @GuardedBy("this")
    public final Set<String> mMainConfig = new HashSet();
    @DoNotStrip
    public final NativeHolder mNativeHolder = initNativeHolder();
    @GuardedBy("this")
    public final Map<Long, Object> mNativeScopeToJavaScope = new HashMap();
    @GuardedBy("this")
    public final Map<NotificationCallback, AnonymousClass1Kf> mObserverConfigs = new HashMap();

    @DoNotStrip
    public interface NotificationCallback {
        @DoNotStrip
        void onNewNotification(String str, @Nullable Object obj, @Nullable Map<Object, Object> map);
    }

    @DoNotStrip
    @GuardedBy("this")
    private native void addObserverNative(String str);

    @DoNotStrip
    private void dispatchNotificationToCallbacks(String str, @Nullable Long l, @Nullable Object obj) {
        Set<String> set;
        if (obj == null || (obj instanceof Map)) {
            Map map = (Map) obj;
            ArrayList arrayList = new ArrayList();
            Object obj2 = null;
            synchronized (this) {
                if (l != null) {
                    obj2 = this.mNativeScopeToJavaScope.get(l);
                }
                for (Map.Entry<NotificationCallback, AnonymousClass1Kf> entry : this.mObserverConfigs.entrySet()) {
                    AnonymousClass1Kf value = entry.getValue();
                    if (value.A01.contains(str) || ((set = value.A00.get(obj2)) != null && set.contains(str))) {
                        arrayList.add(entry.getKey());
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                Execution.executePossiblySync(new AnonymousClass1Kc(this, arrayList, str, obj2, map), 1);
                return;
            }
            return;
        }
        throw new RuntimeException(AnonymousClass006.A07("Native layer of msys reported a notification whose payload could not be serialized into a Java Map. Instead, it's of type ", obj.getClass().getName()));
    }

    @Nullable
    public static Long extractNativePointerFromMcfReference(Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField("mMcfReference");
            declaredField.setAccessible(true);
            if (declaredField.getType().equals(McfReferenceHolder.class)) {
                return Long.valueOf(((McfReferenceHolder) declaredField.get(obj)).nativeReference);
            }
            throw new RuntimeException("Scope object needs to have an mMcfReference field of type McfReferenceHolder");
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    @DoNotStrip
    private native NativeHolder initNativeHolder();

    @DoNotStrip
    @GuardedBy("this")
    private native void removeObserverNative(String str);

    public synchronized void addObserver(NotificationCallback notificationCallback, String str, @Nullable Object obj) {
        if (notificationCallback == null) {
            throw null;
        } else if (str == null) {
            throw null;
        } else if (!observerHasConfig(notificationCallback, str, obj)) {
            if (obj != null) {
                Long extractNativePointerFromMcfReference = extractNativePointerFromMcfReference(obj);
                if (extractNativePointerFromMcfReference == null) {
                    throw new RuntimeException("Scope object needs to have an mMcfReference field of type McfReferenceHolder");
                }
                this.mNativeScopeToJavaScope.put(extractNativePointerFromMcfReference, obj);
            }
            AnonymousClass1Kf r2 = this.mObserverConfigs.get(notificationCallback);
            if (r2 == null) {
                r2 = new AnonymousClass1Kf();
                this.mObserverConfigs.put(notificationCallback, r2);
            }
            if (obj == null) {
                r2.A01.add(str);
            } else {
                Set<String> set = r2.A00.get(obj);
                if (set == null) {
                    set = new HashSet<>();
                    r2.A00.put(obj, set);
                }
                set.add(str);
            }
            if (this.mMainConfig.add(str)) {
                addObserverNative(str);
            }
        }
    }

    public synchronized void removeObserver(NotificationCallback notificationCallback, String str, @Nullable Object obj) {
        if (notificationCallback == null) {
            throw null;
        } else if (str == null) {
            throw null;
        } else if (observerHasConfig(notificationCallback, str, obj)) {
            AnonymousClass1Kf r1 = this.mObserverConfigs.get(notificationCallback);
            if (r1 != null) {
                if (obj == null) {
                    r1.A01.remove(str);
                } else {
                    Set<String> set = r1.A00.get(obj);
                    if (set != null) {
                        set.remove(str);
                        if (set.isEmpty()) {
                            r1.A00.remove(obj);
                        }
                    }
                }
                if (r1.A01.isEmpty() && r1.A00.isEmpty()) {
                    this.mObserverConfigs.remove(notificationCallback);
                }
            }
            if (obj != null) {
                Iterator<Map.Entry<NotificationCallback, AnonymousClass1Kf>> it = this.mObserverConfigs.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getValue().A00.containsKey(obj)) {
                            break;
                        }
                    } else {
                        this.mNativeScopeToJavaScope.remove(Long.valueOf(extractNativePointerFromMcfReference(obj).longValue()));
                        break;
                    }
                }
            }
            Iterator<Map.Entry<NotificationCallback, AnonymousClass1Kf>> it2 = this.mObserverConfigs.entrySet().iterator();
            loop1:
            while (true) {
                if (!it2.hasNext()) {
                    this.mMainConfig.remove(str);
                    removeObserverNative(str);
                    break;
                }
                AnonymousClass1Kf value = it2.next().getValue();
                if (value.A01.contains(str)) {
                    break;
                }
                Iterator it3 = new HashSet(value.A00.entrySet()).iterator();
                while (true) {
                    if (it3.hasNext()) {
                        if (((Set) ((Map.Entry) it3.next()).getValue()).contains(str)) {
                            break loop1;
                        }
                    }
                }
            }
        }
    }

    @GuardedBy("this")
    private boolean observerHasConfig(NotificationCallback notificationCallback, String str, @Nullable Object obj) {
        Set<String> set;
        AnonymousClass1Kf r0 = this.mObserverConfigs.get(notificationCallback);
        if (r0 == null) {
            return false;
        }
        if (obj == null) {
            set = r0.A01;
        } else {
            set = r0.A00.get(obj);
            if (set == null) {
                return false;
            }
        }
        return set.contains(str);
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
