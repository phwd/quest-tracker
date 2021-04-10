package X;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

/* renamed from: X.074  reason: invalid class name */
public class AnonymousClass074 {
    public String A00;
    public ArrayList<AnonymousClass070> A01 = new ArrayList<>();
    public Notification A02;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public Context A03;
    public Bundle A04;
    public AnonymousClass07C A05;
    public CharSequence A06;
    public CharSequence A07;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ArrayList<AnonymousClass070> A08 = new ArrayList<>();
    @Deprecated
    public ArrayList<String> A09;

    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }

    public AnonymousClass074(@NonNull Context context, @NonNull String str) {
        Notification notification = new Notification();
        this.A02 = notification;
        this.A03 = context;
        this.A00 = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.A09 = new ArrayList<>();
    }
}
