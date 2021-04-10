package X;

import android.app.PendingIntent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;

/* renamed from: X.4N  reason: invalid class name */
public class AnonymousClass4N {
    @Deprecated
    public int A00;
    public PendingIntent A01;
    @Nullable
    public IconCompat A02;
    public CharSequence A03;
    public boolean A04;
    public final Bundle A05;
    public final boolean A06;
    public final AnonymousClass4p[] A07;

    public AnonymousClass4N(@Nullable IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, AnonymousClass4p[] r7, boolean z, boolean z2) {
        this.A02 = iconCompat;
        if (iconCompat != null) {
            int i = iconCompat.A02;
            if ((i == -1 ? IconCompat.A01((Icon) iconCompat.A06) : i) == 2) {
                this.A00 = iconCompat.A07();
            }
        }
        this.A03 = AnonymousClass4R.A00(charSequence);
        this.A01 = pendingIntent;
        this.A05 = bundle == null ? new Bundle() : bundle;
        this.A07 = r7;
        this.A04 = z;
        this.A06 = z2;
    }
}
