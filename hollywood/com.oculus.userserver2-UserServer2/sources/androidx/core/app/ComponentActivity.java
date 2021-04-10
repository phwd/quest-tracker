package androidx.core.app;

import X.AbstractC0041Bq;
import X.AnonymousClass2O;
import X.AnonymousClass3C;
import X.AnonymousClass44;
import X.AnonymousClass7q;
import X.AnonymousClass7r;
import X.AnonymousClass8Q;
import X.Bs;
import X.C5;
import X.EnumC0040Bp;
import X.Tc;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public class ComponentActivity extends Activity implements Bs, AnonymousClass7q {
    public AnonymousClass3C<Class<? extends AnonymousClass44>, AnonymousClass44> mExtraDataMap = new AnonymousClass3C<>();
    public Tc mLifecycleRegistry = new Tc(this);

    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T extends AnonymousClass44> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        Tc.A04(this.mLifecycleRegistry, EnumC0040Bp.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.3C<java.lang.Class<? extends X.44>, X.44> */
    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public void putExtraData(AnonymousClass44 r3) {
        this.mExtraDataMap.put(r3.getClass(), r3);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass8Q.A01(decorView, keyEvent)) {
            return AnonymousClass7r.A00(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass8Q.A01(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // X.Bs
    @NonNull
    public AbstractC0041Bq getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @SuppressLint({"RestrictedApi"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        C5.A00(this);
    }

    @Override // X.AnonymousClass7q
    @RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
