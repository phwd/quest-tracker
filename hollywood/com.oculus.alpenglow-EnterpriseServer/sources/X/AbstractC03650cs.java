package X;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0cs  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03650cs<E> extends AnonymousClass0CX {
    @Nullable
    public final Activity A00;
    @NonNull
    public final Context A01;
    @NonNull
    public final Handler A02;
    public final AnonymousClass0Cj A03 = new C03610cn();

    @Override // X.AnonymousClass0CX
    @Nullable
    public View A00(int i) {
        return null;
    }

    @Override // X.AnonymousClass0CX
    public boolean A01() {
        return true;
    }

    @Nullable
    public abstract E A03();

    public void A04() {
    }

    public AbstractC03650cs(@NonNull FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.A00 = fragmentActivity;
        this.A01 = fragmentActivity;
        this.A02 = handler;
    }

    @NonNull
    public LayoutInflater A02() {
        return LayoutInflater.from(this.A01);
    }
}
