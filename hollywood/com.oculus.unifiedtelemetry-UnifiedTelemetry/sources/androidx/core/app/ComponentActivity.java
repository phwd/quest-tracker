package androidx.core.app;

import X.AO;
import X.AP;
import X.AR;
import X.Ad;
import X.AnonymousClass2C;
import X.AnonymousClass3K;
import X.AnonymousClass76;
import X.AnonymousClass77;
import X.C00062o;
import X.C00177h;
import X.Zw;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
public class ComponentActivity extends Activity implements AR, AnonymousClass76 {
    public C00062o<Class<? extends AnonymousClass3K>, AnonymousClass3K> mExtraDataMap = new C00062o<>();
    public Zw mLifecycleRegistry = new Zw(this);

    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T extends AnonymousClass3K> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Zw.A04(this.mLifecycleRegistry, AO.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.2o<java.lang.Class<? extends X.3K>, X.3K> */
    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public void putExtraData(AnonymousClass3K r3) {
        this.mExtraDataMap.put(r3.getClass(), r3);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C00177h.A01(decorView, keyEvent)) {
            return AnonymousClass77.A00(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C00177h.A01(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @SuppressLint({"RestrictedApi"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Ad.A00(this);
    }

    @Override // X.AnonymousClass76
    @RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // X.AR
    @NonNull
    public AP getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
