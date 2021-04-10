package X;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* renamed from: X.0s9  reason: invalid class name */
public abstract class AnonymousClass0s9<E> extends AnonymousClass09N {
    @Nullable
    public final Activity A00;
    @NonNull
    public final Context A01;
    @NonNull
    public final Handler A02;
    public final AbstractC003209a A03 = new C07390s3();

    @Override // X.AnonymousClass09N
    @Nullable
    public View A00(int i) {
        return null;
    }

    @Override // X.AnonymousClass09N
    public boolean A01() {
        return true;
    }

    @Nullable
    public abstract E A03();

    public void A04() {
    }

    public void A05(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        if (i == -1) {
            this.A01.startActivity(intent);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void A06(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        if (i == -1) {
            this.A00.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
    }

    public void A07(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
    }

    public boolean A08(@NonNull Fragment fragment) {
        return true;
    }

    public boolean A09(@NonNull String str) {
        return false;
    }

    public AnonymousClass0s9(@NonNull FragmentActivity fragmentActivity) {
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
