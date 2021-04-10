package X;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* renamed from: X.02m  reason: invalid class name */
public abstract class AnonymousClass02m {
    public static final AnonymousClass061<WeakReference<AnonymousClass02m>> A00 = new AnonymousClass061<>();
    public static final Object A01 = new Object();

    public int A0F() {
        return -100;
    }

    public abstract MenuInflater A0H();

    @Nullable
    public abstract <T extends View> T A0I(@IdRes int i);

    @Nullable
    public abstract AnonymousClass02W A0J();

    @Nullable
    public abstract AnonymousClass02Y A0K();

    @Nullable
    public abstract AnonymousClass03D A0L(@NonNull AnonymousClass03C v);

    public abstract void A0M();

    public abstract void A0N();

    public abstract void A0O();

    public abstract void A0P();

    public abstract void A0Q();

    public abstract void A0R();

    public void A0S(@StyleRes int i) {
    }

    public abstract void A0T(@LayoutRes int i);

    public abstract void A0U(Configuration configuration);

    public abstract void A0V(Bundle bundle);

    public abstract void A0W(Bundle bundle);

    public abstract void A0X(View view);

    public abstract void A0Y(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void A0Z(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void A0a(@Nullable Toolbar toolbar);

    public abstract void A0b(@Nullable CharSequence charSequence);

    public abstract boolean A0c(int i);

    public static void A00(@NonNull AnonymousClass02m r3) {
        synchronized (A01) {
            Iterator<WeakReference<AnonymousClass02m>> it = A00.iterator();
            while (it.hasNext()) {
                AnonymousClass02m r0 = it.next().get();
                if (r0 == r3 || r0 == null) {
                    it.remove();
                }
            }
        }
    }

    @NonNull
    @CallSuper
    public Context A0G(@NonNull Context context) {
        return context;
    }
}
