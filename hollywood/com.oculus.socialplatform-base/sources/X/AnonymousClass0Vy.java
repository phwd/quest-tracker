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

/* renamed from: X.0Vy  reason: invalid class name */
public final class AnonymousClass0Vy extends AnonymousClass0ur {
    public static final Class<?>[] A05 = {Application.class, AnonymousClass0Af.class};
    public static final Class<?>[] A06 = {AnonymousClass0Af.class};
    public final Application A00;
    public final Bundle A01;
    public final AnonymousClass0AQ A02;
    public final AnonymousClass0Vw A03;
    public final AnonymousClass0C4 A04;

    @Override // X.AnonymousClass0Ai
    public final void A00(@NonNull AnonymousClass0Ag r3) {
        SavedStateHandleController.A01(r3, this.A04, this.A02);
    }

    @Override // X.AnonymousClass0ur
    @NonNull
    public final <T extends AnonymousClass0Ag> T A01(@NonNull String str, @NonNull Class<T> cls) {
        Constructor<?> constructor;
        AnonymousClass0Af r0;
        T t;
        Object obj;
        boolean isAssignableFrom = C05240uy.class.isAssignableFrom(cls);
        if (isAssignableFrom) {
            Class<?>[] clsArr = A05;
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            for (int i = 0; i < length; i++) {
                constructor = constructors[i];
                if (!Arrays.equals(clsArr, constructor.getParameterTypes())) {
                }
            }
            return (T) this.A03.A2L(cls);
        }
        Class<?>[] clsArr2 = A06;
        Constructor<?>[] constructors2 = cls.getConstructors();
        int length2 = constructors2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            constructor = constructors2[i2];
            if (!Arrays.equals(clsArr2, constructor.getParameterTypes())) {
            }
        }
        return (T) this.A03.A2L(cls);
        AnonymousClass0C4 r7 = this.A04;
        AnonymousClass0AQ r6 = this.A02;
        Bundle bundle = this.A01;
        Bundle A002 = r7.A00(str);
        if (A002 == null && bundle == null) {
            r0 = new AnonymousClass0Af();
        } else {
            HashMap hashMap = new HashMap();
            if (bundle != null) {
                for (String str2 : bundle.keySet()) {
                    hashMap.put(str2, bundle.get(str2));
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
            r0 = new AnonymousClass0Af(hashMap);
        }
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, r0);
        SavedStateHandleController.A00(savedStateHandleController, r7, r6);
        SavedStateHandleController.A02(r7, r6);
        if (isAssignableFrom) {
            t = (T) ((AnonymousClass0Ag) constructor.newInstance(this.A00, savedStateHandleController.A01));
        } else {
            try {
                t = (T) ((AnonymousClass0Ag) constructor.newInstance(savedStateHandleController.A01));
            } catch (IllegalAccessException e) {
                StringBuilder sb = new StringBuilder("Failed to access ");
                sb.append(cls);
                throw new RuntimeException(sb.toString(), e);
            } catch (InstantiationException e2) {
                StringBuilder sb2 = new StringBuilder("A ");
                sb2.append(cls);
                sb2.append(" cannot be instantiated.");
                throw new RuntimeException(sb2.toString(), e2);
            } catch (InvocationTargetException e3) {
                StringBuilder sb3 = new StringBuilder("An exception happened in constructor of ");
                sb3.append(cls);
                throw new RuntimeException(sb3.toString(), e3.getCause());
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
        if (!t.A01 || !(savedStateHandleController instanceof Closeable)) {
            return t;
        }
        try {
            ((Closeable) savedStateHandleController).close();
            return t;
        } catch (IOException e4) {
            throw new RuntimeException(e4);
        }
    }

    @SuppressLint({"LambdaLast"})
    public AnonymousClass0Vy(@NonNull Application application, @NonNull AbstractC05180ub r3, @Nullable Bundle bundle) {
        this.A04 = r3.getSavedStateRegistry();
        this.A02 = r3.getLifecycle();
        this.A01 = bundle;
        this.A00 = application;
        AnonymousClass0Vw r0 = AnonymousClass0Vw.A01;
        if (r0 == null) {
            r0 = new AnonymousClass0Vw(application);
            AnonymousClass0Vw.A01 = r0;
        }
        this.A03 = r0;
    }

    @Override // X.AnonymousClass0ur, X.AnonymousClass0Ah
    @NonNull
    public final <T extends AnonymousClass0Ag> T A2L(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) A01(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
