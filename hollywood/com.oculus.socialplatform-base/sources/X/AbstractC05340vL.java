package X;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0vL  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05340vL<E> extends AnonymousClass09P {
    @Nullable
    public final Activity A00;
    @NonNull
    public final Context A01;
    @NonNull
    public final Handler A02;
    public final AnonymousClass09b A03 = new C05300vD();

    @Override // X.AnonymousClass09P
    @Nullable
    public View A00(int i) {
        return null;
    }

    @Override // X.AnonymousClass09P
    public boolean A01() {
        return true;
    }

    @Nullable
    public abstract E A03();

    public void A04() {
    }

    public AbstractC05340vL(@NonNull FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.A00 = fragmentActivity;
        if (fragmentActivity != null) {
            this.A01 = fragmentActivity;
            this.A02 = handler;
            return;
        }
        throw new NullPointerException(String.valueOf("context == null"));
    }

    @NonNull
    public LayoutInflater A02() {
        return LayoutInflater.from(this.A01);
    }
}
