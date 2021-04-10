package androidx.core.app;

import X.AnonymousClass2C;
import X.CW;
import X.CZ;
import android.app.PendingIntent;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

@RestrictTo({AnonymousClass2C.LIBRARY})
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(CW cw) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        CZ cz = remoteActionCompat.A01;
        if (cw.A0I(1)) {
            cz = cw.A05();
        }
        remoteActionCompat.A01 = (IconCompat) cz;
        CharSequence charSequence = remoteActionCompat.A03;
        if (cw.A0I(2)) {
            charSequence = cw.A06();
        }
        remoteActionCompat.A03 = charSequence;
        CharSequence charSequence2 = remoteActionCompat.A02;
        if (cw.A0I(3)) {
            charSequence2 = cw.A06();
        }
        remoteActionCompat.A02 = charSequence2;
        Parcelable parcelable = remoteActionCompat.A00;
        if (cw.A0I(4)) {
            parcelable = cw.A03();
        }
        remoteActionCompat.A00 = (PendingIntent) parcelable;
        boolean z = remoteActionCompat.A04;
        if (cw.A0I(5)) {
            z = cw.A0H();
        }
        remoteActionCompat.A04 = z;
        boolean z2 = remoteActionCompat.A05;
        if (cw.A0I(6)) {
            z2 = cw.A0H();
        }
        remoteActionCompat.A05 = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, CW cw) {
        IconCompat iconCompat = remoteActionCompat.A01;
        cw.A09(1);
        cw.A0C(iconCompat);
        CharSequence charSequence = remoteActionCompat.A03;
        cw.A09(2);
        cw.A0D(charSequence);
        CharSequence charSequence2 = remoteActionCompat.A02;
        cw.A09(3);
        cw.A0D(charSequence2);
        PendingIntent pendingIntent = remoteActionCompat.A00;
        cw.A09(4);
        cw.A0B(pendingIntent);
        boolean z = remoteActionCompat.A04;
        cw.A09(5);
        cw.A0F(z);
        boolean z2 = remoteActionCompat.A05;
        cw.A09(6);
        cw.A0F(z2);
    }
}
