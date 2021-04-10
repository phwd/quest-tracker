package X;

import android.app.Person;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat$CarExtender;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.03m  reason: invalid class name */
public final class AnonymousClass03m {
    @Nullable
    public Uri A00;
    public Bundle A01 = new Bundle();
    @Nullable
    public String A02;
    public final long A03;
    @Nullable
    public final AnonymousClass040 A04;
    public final CharSequence A05;

    @NonNull
    public static List<AnonymousClass03m> A00(Parcelable[] parcelableArr) {
        AnonymousClass040 r0;
        int length = parcelableArr.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            if (parcelableArr[i] instanceof Bundle) {
                Bundle bundle = (Bundle) parcelableArr[i];
                AnonymousClass03m r3 = null;
                try {
                    if (bundle.containsKey(NotificationCompat$CarExtender.KEY_TEXT) && bundle.containsKey("time")) {
                        if (bundle.containsKey("person")) {
                            r0 = AnonymousClass040.A01(bundle.getBundle("person"));
                        } else if (bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            r0 = AnonymousClass040.A00((Person) bundle.getParcelable("sender_person"));
                        } else if (bundle.containsKey("sender")) {
                            AnonymousClass03z r1 = new AnonymousClass03z();
                            r1.A01 = bundle.getCharSequence("sender");
                            r0 = new AnonymousClass040(r1);
                        } else {
                            r0 = null;
                        }
                        AnonymousClass03m r11 = new AnonymousClass03m(bundle.getCharSequence(NotificationCompat$CarExtender.KEY_TEXT), bundle.getLong("time"), r0);
                        if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                            r11.A02 = bundle.getString("type");
                            r11.A00 = (Uri) bundle.getParcelable("uri");
                        }
                        if (bundle.containsKey("extras")) {
                            r11.A01.putAll(bundle.getBundle("extras"));
                        }
                        r3 = r11;
                    }
                } catch (ClassCastException unused) {
                }
                if (r3 != null) {
                    arrayList.add(r3);
                }
            }
        }
        return arrayList;
    }

    public AnonymousClass03m(CharSequence charSequence, long j, @Nullable AnonymousClass040 r5) {
        this.A05 = charSequence;
        this.A03 = j;
        this.A04 = r5;
    }

    @NonNull
    public static Bundle[] A01(List<AnonymousClass03m> list) {
        Bundle[] bundleArr = new Bundle[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass03m r6 = list.get(i);
            Bundle bundle = new Bundle();
            CharSequence charSequence = r6.A05;
            if (charSequence != null) {
                bundle.putCharSequence(NotificationCompat$CarExtender.KEY_TEXT, charSequence);
            }
            bundle.putLong("time", r6.A03);
            AnonymousClass040 r7 = r6.A04;
            if (r7 != null) {
                bundle.putCharSequence("sender", r7.A01);
                if (Build.VERSION.SDK_INT >= 28) {
                    bundle.putParcelable("sender_person", r7.A02());
                } else {
                    bundle.putBundle("person", r7.A03());
                }
            }
            String str = r6.A02;
            if (str != null) {
                bundle.putString("type", str);
            }
            Uri uri = r6.A00;
            if (uri != null) {
                bundle.putParcelable("uri", uri);
            }
            Bundle bundle2 = r6.A01;
            if (bundle2 != null) {
                bundle.putBundle("extras", bundle2);
            }
            bundleArr[i] = bundle;
        }
        return bundleArr;
    }
}
