package X;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.SavedStateHandleController;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0MD  reason: invalid class name */
public final class AnonymousClass0MD extends AbstractC03500cY {
    public static final Class<?>[] A05 = {Application.class, C01110Dn.class};
    public static final Class<?>[] A06 = {C01110Dn.class};
    public final Application A00;
    public final Bundle A01;
    public final AnonymousClass0DY A02;
    public final AnonymousClass0MB A03;
    public final AnonymousClass0GJ A04;

    @Override // X.C01130Dq
    public final void A00(@NonNull AnonymousClass0Do r3) {
        SavedStateHandleController.A01(r3, this.A04, this.A02);
    }

    @Override // X.AbstractC03500cY
    @NonNull
    public final <T extends AnonymousClass0Do> T A01(@NonNull String str, @NonNull Class<T> cls) {
        Constructor<?> constructor;
        C01110Dn r0;
        T t;
        Object obj;
        Throwable e;
        String str2;
        boolean isAssignableFrom = C03560cf.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            Class<?>[] clsArr = A05;
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            for (int i = 0; i < length; i++) {
                constructor = constructors[i];
                if (!Arrays.equals(clsArr, constructor.getParameterTypes())) {
                }
            }
            return (T) this.A03.A1t(cls);
        }
        Class<?>[] clsArr2 = A06;
        Constructor<?>[] constructors2 = cls.getConstructors();
        int length2 = constructors2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            constructor = constructors2[i2];
            if (!Arrays.equals(clsArr2, constructor.getParameterTypes())) {
            }
        }
        return (T) this.A03.A1t(cls);
        AnonymousClass0GJ r7 = this.A04;
        AnonymousClass0DY r6 = this.A02;
        Bundle bundle = this.A01;
        Bundle A002 = r7.A00(str);
        if (A002 == null && bundle == null) {
            r0 = new C01110Dn();
        } else {
            HashMap hashMap = new HashMap();
            if (bundle != null) {
                for (String str3 : bundle.keySet()) {
                    hashMap.put(str3, bundle.get(str3));
                }
            }
            if (A002 != null) {
                ArrayList parcelableArrayList = A002.getParcelableArrayList("keys");
                ArrayList parcelableArrayList2 = A002.getParcelableArrayList("values");
                if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                    throw new IllegalStateException("Invalid bundle passed as restored state");
                }
                for (int i3 = 0; i3 < parcelableArrayList.size(); i3++) {
                    hashMap.put(parcelableArrayList.get(i3), parcelableArrayList2.get(i3));
                }
            }
            r0 = new C01110Dn(hashMap);
        }
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, r0);
        SavedStateHandleController.A00(savedStateHandleController, r7, r6);
        SavedStateHandleController.A02(r7, r6);
        if (isAssignableFrom) {
            t = (T) ((AnonymousClass0Do) constructor.newInstance(this.A00, savedStateHandleController.A01));
        } else {
            try {
                t = (T) ((AnonymousClass0Do) constructor.newInstance(savedStateHandleController.A01));
            } catch (IllegalAccessException e2) {
                e = e2;
                str2 = "Failed to access " + cls;
                throw new RuntimeException(str2, e);
            } catch (InstantiationException e3) {
                e = e3;
                str2 = "A " + cls + " cannot be instantiated.";
                throw new RuntimeException(str2, e);
            } catch (InvocationTargetException e4) {
                str2 = "An exception happened in constructor of " + cls;
                e = e4.getCause();
                throw new RuntimeException(str2, e);
            }
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
        if (t.A01 && (savedStateHandleController instanceof Closeable)) {
            try {
                ((Closeable) savedStateHandleController).close();
            } catch (IOException e5) {
                throw new RuntimeException(e5);
            }
        }
        return t;
    }

    @SuppressLint({"LambdaLast"})
    public AnonymousClass0MD(@NonNull Application application, @NonNull AbstractC03380cC r3, @Nullable Bundle bundle) {
        this.A04 = r3.getSavedStateRegistry();
        this.A02 = r3.getLifecycle();
        this.A01 = bundle;
        this.A00 = application;
        AnonymousClass0MB r0 = AnonymousClass0MB.A01;
        if (r0 == null) {
            r0 = new AnonymousClass0MB(application);
            AnonymousClass0MB.A01 = r0;
        }
        this.A03 = r0;
    }

    @Override // X.AbstractC01120Dp, X.AbstractC03500cY
    @NonNull
    public final <T extends AnonymousClass0Do> T A1t(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) A01(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
