package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03K;
import X.AnonymousClass074;
import X.AnonymousClass075;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AR;
import X.AnonymousClass0Ad;
import X.C000602o;
import X.C001507f;
import X.C07280rn;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class ComponentActivity extends Activity implements AnonymousClass0AR, AnonymousClass074 {
    public C000602o<Class<? extends AnonymousClass03K>, AnonymousClass03K> mExtraDataMap = new C000602o<>();
    public C07280rn mLifecycleRegistry = new C07280rn(this);

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T extends AnonymousClass03K> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        C07280rn.A04(this.mLifecycleRegistry, AnonymousClass0AO.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.02o<java.lang.Class<? extends X.03K>, X.03K> */
    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public void putExtraData(AnonymousClass03K r3) {
        this.mExtraDataMap.put(r3.getClass(), r3);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C001507f.A01(decorView, keyEvent)) {
            return AnonymousClass075.A00(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C001507f.A01(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // X.AnonymousClass0AR
    @NonNull
    public AnonymousClass0AP getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @SuppressLint({"RestrictedApi"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass0Ad.A00(this);
    }

    @Override // X.AnonymousClass074
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
