package X;

import android.annotation.TargetApi;
import android.view.View;

/* renamed from: X.1uV  reason: invalid class name */
public class AnonymousClass1uV implements View.OnAttachStateChangeListener {
    public final void onViewDetachedFromWindow(View view) {
    }

    @TargetApi(19)
    public final void onViewAttachedToWindow(View view) {
        AnonymousClass1uW.getBinding(view).mRebindRunnable.run();
        view.removeOnAttachStateChangeListener(this);
    }
}
