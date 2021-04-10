package X;

import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.squareup.okhttp.internal.http.HttpEngine;

/* renamed from: X.07S  reason: invalid class name */
public final class AnonymousClass07S {
    public final int A00;
    public final Bundle A01;
    public final CharSequence A02;
    public final String A03;
    public final boolean A04;
    public final CharSequence[] A05;

    @RequiresApi(HttpEngine.MAX_FOLLOW_UPS)
    public static RemoteInput[] A00(AnonymousClass07S[] r7) {
        int length = r7.length;
        RemoteInput[] remoteInputArr = new RemoteInput[length];
        for (int i = 0; i < length; i++) {
            AnonymousClass07S r6 = r7[i];
            RemoteInput.Builder addExtras = new RemoteInput.Builder(r6.A03).setLabel(r6.A02).setChoices(r6.A05).setAllowFreeFormInput(r6.A04).addExtras(r6.A01);
            if (Build.VERSION.SDK_INT >= 29) {
                addExtras.setEditChoicesBeforeSending(r6.A00);
            }
            remoteInputArr[i] = addExtras.build();
        }
        return remoteInputArr;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/lang/CharSequence;[Ljava/lang/CharSequence;ZILandroid/os/Bundle;Ljava/util/Set<Ljava/lang/String;>;)V */
    public AnonymousClass07S(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, int i, Bundle bundle) {
        this.A03 = str;
        this.A02 = charSequence;
        this.A05 = charSequenceArr;
        this.A04 = z;
        this.A00 = i;
        this.A01 = bundle;
        if (i == 2 && !z) {
            throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        }
    }
}
