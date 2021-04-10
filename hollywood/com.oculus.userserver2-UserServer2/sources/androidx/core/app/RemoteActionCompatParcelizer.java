package androidx.core.app;

import X.AbstractC0056El;
import X.AbstractC0059Eo;
import X.AnonymousClass2O;
import X.TK;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;

@RestrictTo({AnonymousClass2O.LIBRARY})
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(AbstractC0056El el) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        AbstractC0059Eo eo = remoteActionCompat.A01;
        if (el.A09(1)) {
            eo = el.A04();
        }
        remoteActionCompat.A01 = (IconCompat) eo;
        CharSequence charSequence = remoteActionCompat.A03;
        if (el.A09(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((TK) el).A05);
        }
        remoteActionCompat.A03 = charSequence;
        CharSequence charSequence2 = remoteActionCompat.A02;
        if (el.A09(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((TK) el).A05);
        }
        remoteActionCompat.A02 = charSequence2;
        Parcelable parcelable = remoteActionCompat.A00;
        if (el.A09(4)) {
            parcelable = el.A02();
        }
        remoteActionCompat.A00 = (PendingIntent) parcelable;
        boolean z = remoteActionCompat.A04;
        if (el.A09(5)) {
            z = false;
            if (((TK) el).A05.readInt() != 0) {
                z = true;
            }
        }
        remoteActionCompat.A04 = z;
        boolean z2 = remoteActionCompat.A05;
        if (el.A09(6)) {
            z2 = false;
            if (((TK) el).A05.readInt() != 0) {
                z2 = true;
            }
        }
        remoteActionCompat.A05 = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, AbstractC0056El el) {
        IconCompat iconCompat = remoteActionCompat.A01;
        el.A06(1);
        el.A08(iconCompat);
        CharSequence charSequence = remoteActionCompat.A03;
        el.A06(2);
        Parcel parcel = ((TK) el).A05;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.A02;
        el.A06(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.A00;
        el.A06(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.A04;
        el.A06(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.A05;
        el.A06(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
