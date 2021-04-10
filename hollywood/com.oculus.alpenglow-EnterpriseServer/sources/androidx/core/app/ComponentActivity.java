package androidx.core.app;

import X.AbstractC01030Da;
import X.AnonymousClass02D;
import X.AnonymousClass06D;
import X.AnonymousClass06h;
import X.AnonymousClass0AR;
import X.AnonymousClass0AS;
import X.AnonymousClass0Aw;
import X.AnonymousClass0DX;
import X.AnonymousClass0DY;
import X.AnonymousClass0Dm;
import X.C03540cc;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public class ComponentActivity extends Activity implements AbstractC01030Da, AnonymousClass0AR {
    public AnonymousClass06D<Class<? extends AnonymousClass06h>, AnonymousClass06h> mExtraDataMap = new AnonymousClass06D<>();
    public C03540cc mLifecycleRegistry = new C03540cc(this);

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public <T extends AnonymousClass06h> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    @Override // X.AbstractC01030Da
    @NonNull
    public AnonymousClass0DY getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        C03540cc.A04(this.mLifecycleRegistry, AnonymousClass0DX.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.06D<java.lang.Class<? extends X.06h>, X.06h> */
    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public void putExtraData(AnonymousClass06h r3) {
        this.mExtraDataMap.put(r3.getClass(), r3);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass0Aw.A05(decorView, keyEvent)) {
            return AnonymousClass0AS.A00(this, decorView, this, keyEvent);
        }
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AnonymousClass0Aw.A05(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @SuppressLint({"RestrictedApi"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass0Dm.A00(this);
    }

    @Override // X.AnonymousClass0AR
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
