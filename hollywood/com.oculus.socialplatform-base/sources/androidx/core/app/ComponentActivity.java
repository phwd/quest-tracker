package androidx.core.app;

import X.AnonymousClass02C;
import X.AnonymousClass03P;
import X.AnonymousClass07B;
import X.AnonymousClass07C;
import X.AnonymousClass07f;
import X.AnonymousClass0AP;
import X.AnonymousClass0AQ;
import X.AnonymousClass0AS;
import X.AnonymousClass0Ae;
import X.AnonymousClass0uv;
import X.C000502v;
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
public class ComponentActivity extends Activity implements AnonymousClass0AS, AnonymousClass07B {
    public C000502v<Class<? extends AnonymousClass03P>, AnonymousClass03P> mExtraDataMap = new C000502v<>();
    public AnonymousClass0uv mLifecycleRegistry = new AnonymousClass0uv(this);

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T extends AnonymousClass03P> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AnonymousClass0uv.A04(this.mLifecycleRegistry, AnonymousClass0AP.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.02v<java.lang.Class<? extends X.03P>, X.03P> */
    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public void putExtraData(AnonymousClass03P r3) {
        this.mExtraDataMap.put(r3.getClass(), r3);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass07f.A09(decorView, keyEvent)) {
            return AnonymousClass07C.A00(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass07f.A09(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // X.AnonymousClass0AS
    @NonNull
    public AnonymousClass0AQ getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @SuppressLint({"RestrictedApi"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass0Ae.A00(this);
    }

    @Override // X.AnonymousClass07B
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
