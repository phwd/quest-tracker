package X;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.Tp  reason: case insensitive filesystem */
public abstract class AbstractC0144Tp<E> extends Ai {
    @Nullable
    public final Activity A00;
    @NonNull
    public final Context A01;
    @NonNull
    public final Handler A02;
    public final Au A03 = new C0141Tk();

    public AbstractC0144Tp(@NonNull FragmentActivity fragmentActivity) {
        Handler handler = new Handler();
        this.A00 = fragmentActivity;
        this.A01 = fragmentActivity;
        this.A02 = handler;
    }
}
