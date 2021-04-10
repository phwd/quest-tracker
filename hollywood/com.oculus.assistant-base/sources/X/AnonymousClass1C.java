package X;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

/* renamed from: X.1C  reason: invalid class name */
public final class AnonymousClass1C {
    public int A00;
    public Notification A01;
    public PendingIntent A02;
    public Context A03;
    public Bundle A04;
    public AnonymousClass1D A05;
    public CharSequence A06;
    public CharSequence A07;
    public String A08;
    public ArrayList A09 = new ArrayList();
    public ArrayList A0A = new ArrayList();
    public ArrayList A0B;
    public boolean A0C;
    public boolean A0D = false;
    public boolean A0E = true;

    public AnonymousClass1C(Context context) {
        Notification notification = new Notification();
        this.A01 = notification;
        this.A03 = context;
        this.A08 = null;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.A00 = 0;
        this.A0B = new ArrayList();
        this.A0C = true;
    }

    public static CharSequence A00(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 5120) {
            return charSequence;
        }
        return charSequence.subSequence(0, 5120);
    }

    public final void A01(AnonymousClass1D r2) {
        if (this.A05 != r2) {
            this.A05 = r2;
            if (r2.A00 != this) {
                r2.A00 = this;
                A01(r2);
            }
        }
    }
}
