package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandleController;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class TY extends C9 implements C8 {
    @NonNull
    public final <T extends C7> T A00(@NonNull String str, @NonNull Class<T> cls) {
        Constructor<?> constructor;
        C6 c6;
        T t;
        Object obj;
        Throwable e;
        String str2;
        CQ cq = (CQ) this;
        boolean isAssignableFrom = Tf.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            Class<?>[] clsArr = CQ.A05;
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            for (int i = 0; i < length; i++) {
                constructor = constructors[i];
                if (!Arrays.equals(clsArr, constructor.getParameterTypes())) {
                }
            }
            return (T) cq.A03.A1E(cls);
        }
        Class<?>[] clsArr2 = CQ.A06;
        Constructor<?>[] constructors2 = cls.getConstructors();
        int length2 = constructors2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            constructor = constructors2[i2];
            if (!Arrays.equals(clsArr2, constructor.getParameterTypes())) {
            }
        }
        return (T) cq.A03.A1E(cls);
        Ds ds = cq.A04;
        AbstractC0041Bq bq = cq.A02;
        Bundle bundle = cq.A01;
        Bundle A00 = ds.A00(str);
        if (A00 == null && bundle == null) {
            c6 = new C6();
        } else {
            HashMap hashMap = new HashMap();
            if (bundle != null) {
                for (String str3 : bundle.keySet()) {
                    hashMap.put(str3, bundle.get(str3));
                }
            }
            if (A00 != null) {
                ArrayList parcelableArrayList = A00.getParcelableArrayList("keys");
                ArrayList parcelableArrayList2 = A00.getParcelableArrayList("values");
                if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                    throw new IllegalStateException("Invalid bundle passed as restored state");
                }
                for (int i3 = 0; i3 < parcelableArrayList.size(); i3++) {
                    hashMap.put(parcelableArrayList.get(i3), parcelableArrayList2.get(i3));
                }
            }
            c6 = new C6(hashMap);
        }
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, c6);
        SavedStateHandleController.A00(savedStateHandleController, ds, bq);
        SavedStateHandleController.A02(ds, bq);
        if (isAssignableFrom) {
            try {
                t = (T) ((C7) constructor.newInstance(cq.A00, savedStateHandleController.A01));
            } catch (IllegalAccessException e2) {
                e = e2;
                StringBuilder sb = new StringBuilder("Failed to access ");
                sb.append(cls);
                str2 = sb.toString();
                throw new RuntimeException(str2, e);
            } catch (InstantiationException e3) {
                e = e3;
                StringBuilder sb2 = new StringBuilder("A ");
                sb2.append(cls);
                sb2.append(" cannot be instantiated.");
                str2 = sb2.toString();
                throw new RuntimeException(str2, e);
            } catch (InvocationTargetException e4) {
                StringBuilder sb3 = new StringBuilder("An exception happened in constructor of ");
                sb3.append(cls);
                str2 = sb3.toString();
                e = e4.getCause();
                throw new RuntimeException(str2, e);
            }
        } else {
            t = (T) ((C7) constructor.newInstance(savedStateHandleController.A01));
        }
        Map<String, Object> map = t.A00;
        synchronized (map) {
            obj = map.get("androidx.lifecycle.savedstate.vm.tag");
            if (obj == null) {
                map.put("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController);
            }
        }
        if (obj != null) {
            savedStateHandleController = obj;
        }
        if (!t.A01 || !(savedStateHandleController instanceof Closeable)) {
            return t;
        }
        try {
            ((Closeable) savedStateHandleController).close();
            return t;
        } catch (IOException e5) {
            throw new RuntimeException(e5);
        }
    }

    @Override // X.C8
    @NonNull
    public final <T extends C7> T A1E(@NonNull Class<T> cls) {
        if (!(this instanceof CQ)) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) A00(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
