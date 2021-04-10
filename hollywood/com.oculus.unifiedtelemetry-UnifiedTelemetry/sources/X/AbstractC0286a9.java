package X;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.a9  reason: case insensitive filesystem */
public abstract class AbstractC0286a9<E> extends AnonymousClass9O {
    @Nullable
    public final Activity A00;
    @NonNull
    public final Context A01;
    @NonNull
    public final Handler A02;
    public final AbstractC00279a A03 = new C0283a4();

    @Override // X.AnonymousClass9O
    @Nullable
    public View A00(int i) {
        return null;
    }

    @Override // X.AnonymousClass9O
    public boolean A01() {
        return true;
    }

    @Nullable
    public abstract E A03();

    public void A04() {
    }

    public AbstractC0286a9(@NonNull FragmentActivity fragmentActivity) {
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
