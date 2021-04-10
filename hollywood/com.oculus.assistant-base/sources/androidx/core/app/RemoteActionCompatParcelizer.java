package androidx.core.app;

import X.AbstractC00293k;
import X.AbstractC00303l;
import X.C0665eH;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;

public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AbstractC00293k r3) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        AbstractC00303l r1 = remoteActionCompat.A01;
        if (r3.A09(1)) {
            r1 = r3.A04();
        }
        remoteActionCompat.A01 = (IconCompat) r1;
        CharSequence charSequence = remoteActionCompat.A03;
        if (r3.A09(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((C0665eH) r3).A05);
        }
        remoteActionCompat.A03 = charSequence;
        CharSequence charSequence2 = remoteActionCompat.A02;
        if (r3.A09(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((C0665eH) r3).A05);
        }
        remoteActionCompat.A02 = charSequence2;
        Parcelable parcelable = remoteActionCompat.A00;
        if (r3.A09(4)) {
            parcelable = r3.A02();
        }
        remoteActionCompat.A00 = (PendingIntent) parcelable;
        boolean z = remoteActionCompat.A04;
        if (r3.A09(5)) {
            z = false;
            if (((C0665eH) r3).A05.readInt() != 0) {
                z = true;
            }
        }
        remoteActionCompat.A04 = z;
        boolean z2 = remoteActionCompat.A05;
        if (r3.A09(6)) {
            z2 = false;
            if (((C0665eH) r3).A05.readInt() != 0) {
                z2 = true;
            }
        }
        remoteActionCompat.A05 = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, AbstractC00293k r5) {
        IconCompat iconCompat = remoteActionCompat.A01;
        r5.A06(1);
        r5.A08(iconCompat);
        CharSequence charSequence = remoteActionCompat.A03;
        r5.A06(2);
        Parcel parcel = ((C0665eH) r5).A05;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.A02;
        r5.A06(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.A00;
        r5.A06(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.A04;
        r5.A06(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.A05;
        r5.A06(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
