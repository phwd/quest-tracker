package X;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;

/* renamed from: X.03d  reason: invalid class name */
public class AnonymousClass03d {
    @Deprecated
    public int A00;
    public PendingIntent A01;
    public CharSequence A02;
    public boolean A03;
    public boolean A04 = true;
    @Nullable
    public IconCompat A05;
    public final int A06;
    public final Bundle A07;
    public final boolean A08;
    public final AnonymousClass045[] A09;

    @Nullable
    public final IconCompat A00() {
        int i;
        IconCompat iconCompat = this.A05;
        if (iconCompat != null || (i = this.A00) == 0) {
            return iconCompat;
        }
        IconCompat A072 = IconCompat.A07(null, "", i);
        this.A05 = A072;
        return A072;
    }

    public AnonymousClass03d(@Nullable IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, AnonymousClass045[] r7, boolean z, int i, boolean z2, boolean z3) {
        this.A05 = iconCompat;
        if (iconCompat != null && iconCompat.A0E() == 2) {
            this.A00 = iconCompat.A0D();
        }
        this.A02 = AnonymousClass03h.A00(charSequence);
        this.A01 = pendingIntent;
        this.A07 = bundle == null ? new Bundle() : bundle;
        this.A09 = r7;
        this.A03 = z;
        this.A06 = i;
        this.A04 = z2;
        this.A08 = z3;
    }
}
