package androidx.databinding;

import X.AnonymousClass0AO;
import X.AnonymousClass0AR;
import X.AnonymousClass1uW;
import androidx.lifecycle.OnLifecycleEvent;
import java.lang.ref.WeakReference;

public class ViewDataBinding$OnStartListener implements AnonymousClass0AR {
    public final WeakReference<AnonymousClass1uW> A00;

    @OnLifecycleEvent(AnonymousClass0AO.ON_START)
    public void onStart() {
        AnonymousClass1uW r0 = this.A00.get();
        if (r0 != null) {
            r0.executePendingBindings();
        }
    }

    public ViewDataBinding$OnStartListener(AnonymousClass1uW r2) {
        this.A00 = new WeakReference<>(r2);
    }
}
