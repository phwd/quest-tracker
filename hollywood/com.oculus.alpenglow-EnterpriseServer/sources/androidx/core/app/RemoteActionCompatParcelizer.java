package androidx.core.app;

import X.AnonymousClass02D;
import X.AnonymousClass0HI;
import X.AnonymousClass0HL;
import android.app.PendingIntent;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

@RestrictTo({AnonymousClass02D.LIBRARY})
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AnonymousClass0HI r3) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        AnonymousClass0HL r1 = remoteActionCompat.A01;
        if (r3.A0I(1)) {
            r1 = r3.A05();
        }
        remoteActionCompat.A01 = (IconCompat) r1;
        CharSequence charSequence = remoteActionCompat.A03;
        if (r3.A0I(2)) {
            charSequence = r3.A06();
        }
        remoteActionCompat.A03 = charSequence;
        CharSequence charSequence2 = remoteActionCompat.A02;
        if (r3.A0I(3)) {
            charSequence2 = r3.A06();
        }
        remoteActionCompat.A02 = charSequence2;
        Parcelable parcelable = remoteActionCompat.A00;
        if (r3.A0I(4)) {
            parcelable = r3.A03();
        }
        remoteActionCompat.A00 = (PendingIntent) parcelable;
        boolean z = remoteActionCompat.A04;
        if (r3.A0I(5)) {
            z = r3.A0H();
        }
        remoteActionCompat.A04 = z;
        boolean z2 = remoteActionCompat.A05;
        if (r3.A0I(6)) {
            z2 = r3.A0H();
        }
        remoteActionCompat.A05 = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, AnonymousClass0HI r3) {
        IconCompat iconCompat = remoteActionCompat.A01;
        r3.A09(1);
        r3.A0C(iconCompat);
        CharSequence charSequence = remoteActionCompat.A03;
        r3.A09(2);
        r3.A0D(charSequence);
        CharSequence charSequence2 = remoteActionCompat.A02;
        r3.A09(3);
        r3.A0D(charSequence2);
        PendingIntent pendingIntent = remoteActionCompat.A00;
        r3.A09(4);
        r3.A0B(pendingIntent);
        boolean z = remoteActionCompat.A04;
        r3.A09(5);
        r3.A0F(z);
        boolean z2 = remoteActionCompat.A05;
        r3.A09(6);
        r3.A0F(z2);
    }
}
