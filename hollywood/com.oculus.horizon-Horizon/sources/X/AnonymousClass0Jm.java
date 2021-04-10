package X;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.SavedStateHandleController;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0Jm  reason: invalid class name */
public final class AnonymousClass0Jm extends AbstractC07250rj {
    public static final Class<?>[] A05 = {Application.class, AnonymousClass0Ae.class};
    public static final Class<?>[] A06 = {AnonymousClass0Ae.class};
    public final Application A00;
    public final Bundle A01;
    public final AnonymousClass0AP A02;
    public final C01050Jk A03;
    public final AnonymousClass0C0 A04;

    @Override // X.C00500Ah
    public final void A00(@NonNull AnonymousClass0Af r3) {
        SavedStateHandleController.A01(r3, this.A04, this.A02);
    }

    @Override // X.AbstractC07250rj
    @NonNull
    public final <T extends AnonymousClass0Af> T A01(@NonNull String str, @NonNull Class<T> cls) {
        Constructor<?> constructor;
        AnonymousClass0Ae r0;
        T t;
        Object obj;
        Throwable e;
        String str2;
        boolean isAssignableFrom = AnonymousClass0rq.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            Class<?>[] clsArr = A05;
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            for (int i = 0; i < length; i++) {
                constructor = constructors[i];
                if (!Arrays.equals(clsArr, constructor.getParameterTypes())) {
                }
            }
            return (T) this.A03.A1w(cls);
        }
        Class<?>[] clsArr2 = A06;
        Constructor<?>[] constructors2 = cls.getConstructors();
        int length2 = constructors2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            constructor = constructors2[i2];
            if (!Arrays.equals(clsArr2, constructor.getParameterTypes())) {
            }
        }
        return (T) this.A03.A1w(cls);
        AnonymousClass0C0 r7 = this.A04;
        AnonymousClass0AP r6 = this.A02;
        Bundle bundle = this.A01;
        Bundle A002 = r7.A00(str);
        if (A002 == null && bundle == null) {
            r0 = new AnonymousClass0Ae();
        } else {
            HashMap hashMap = new HashMap();
            if (bundle != null) {
                for (String str3 : bundle.keySet()) {
                    hashMap.put(str3, bundle.get(str3));
                }
            }
            if (A002 != null) {
                ArrayList parcelableArrayList = A002.getParcelableArrayList("keys");
                ArrayList parcelableArrayList2 = A002.getParcelableArrayList(ConfigStorageCache.VALUES_JSON_KEY);
                if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                    throw new IllegalStateException("Invalid bundle passed as restored state");
                }
                for (int i3 = 0; i3 < parcelableArrayList.size(); i3++) {
                    hashMap.put(parcelableArrayList.get(i3), parcelableArrayList2.get(i3));
                }
            }
            r0 = new AnonymousClass0Ae(hashMap);
        }
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, r0);
        SavedStateHandleController.A00(savedStateHandleController, r7, r6);
        SavedStateHandleController.A02(r7, r6);
        if (isAssignableFrom) {
            t = (T) ((AnonymousClass0Af) constructor.newInstance(this.A00, savedStateHandleController.A01));
        } else {
            try {
                t = (T) ((AnonymousClass0Af) constructor.newInstance(savedStateHandleController.A01));
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
    public AnonymousClass0Jm(@NonNull Application application, @NonNull AbstractC07170rP r3, @Nullable Bundle bundle) {
        this.A04 = r3.getSavedStateRegistry();
        this.A02 = r3.getLifecycle();
        this.A01 = bundle;
        this.A00 = application;
        C01050Jk r0 = C01050Jk.A01;
        if (r0 == null) {
            r0 = new C01050Jk(application);
            C01050Jk.A01 = r0;
        }
        this.A03 = r0;
    }

    @Override // X.AnonymousClass0Ag, X.AbstractC07250rj
    @NonNull
    public final <T extends AnonymousClass0Af> T A1w(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) A01(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
