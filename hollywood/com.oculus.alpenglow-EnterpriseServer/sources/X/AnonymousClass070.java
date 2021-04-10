package X;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;

/* renamed from: X.070  reason: invalid class name */
public class AnonymousClass070 {
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
    public final AnonymousClass07S[] A09;

    @Nullable
    public final IconCompat A00() {
        int i;
        IconCompat iconCompat = this.A05;
        if (iconCompat != null || (i = this.A00) == 0) {
            return iconCompat;
        }
        IconCompat A062 = IconCompat.A06(null, "", i);
        this.A05 = A062;
        return A062;
    }

    public AnonymousClass070(@Nullable IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, AnonymousClass07S[] r7, boolean z, int i, boolean z2, boolean z3) {
        this.A05 = iconCompat;
        if (iconCompat != null) {
            int i2 = iconCompat.A02;
            if ((i2 == -1 ? IconCompat.A01((Icon) iconCompat.A06) : i2) == 2) {
                this.A00 = iconCompat.A0A();
            }
        }
        this.A02 = AnonymousClass074.A00(charSequence);
        this.A01 = pendingIntent;
        this.A07 = bundle == null ? new Bundle() : bundle;
        this.A09 = r7;
        this.A03 = z;
        this.A06 = i;
        this.A04 = z2;
        this.A08 = z3;
    }
}
