package X;

import android.app.Person;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bolts.AppLinks;
import java.util.ArrayList;
import java.util.List;
import libraries.marauder.analytics.AnalyticsEventBase;

/* renamed from: X.03r  reason: invalid class name */
public final class AnonymousClass03r {
    @Nullable
    public Uri A00;
    public Bundle A01 = new Bundle();
    @Nullable
    public String A02;
    public final long A03;
    @Nullable
    public final AnonymousClass045 A04;
    public final CharSequence A05;

    @NonNull
    public static List<AnonymousClass03r> A00(Parcelable[] parcelableArr) {
        AnonymousClass045 r0;
        int length = parcelableArr.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            if (parcelableArr[i] instanceof Bundle) {
                Bundle bundle = (Bundle) parcelableArr[i];
                AnonymousClass03r r3 = null;
                try {
                    if (bundle.containsKey("text") && bundle.containsKey(AnalyticsEventBase.TIME_KEY)) {
                        if (bundle.containsKey("person")) {
                            r0 = AnonymousClass045.A01(bundle.getBundle("person"));
                        } else if (bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            r0 = AnonymousClass045.A00((Person) bundle.getParcelable("sender_person"));
                        } else if (bundle.containsKey("sender")) {
                            AnonymousClass044 r1 = new AnonymousClass044();
                            r1.A01 = bundle.getCharSequence("sender");
                            r0 = new AnonymousClass045(r1);
                        } else {
                            r0 = null;
                        }
                        AnonymousClass03r r11 = new AnonymousClass03r(bundle.getCharSequence("text"), bundle.getLong(AnalyticsEventBase.TIME_KEY), r0);
                        if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                            r11.A02 = bundle.getString("type");
                            r11.A00 = (Uri) bundle.getParcelable("uri");
                        }
                        if (bundle.containsKey(AppLinks.KEY_NAME_EXTRAS)) {
                            r11.A01.putAll(bundle.getBundle(AppLinks.KEY_NAME_EXTRAS));
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

    public AnonymousClass03r(CharSequence charSequence, long j, @Nullable AnonymousClass045 r5) {
        this.A05 = charSequence;
        this.A03 = j;
        this.A04 = r5;
    }

    @NonNull
    public static Bundle[] A01(List<AnonymousClass03r> list) {
        Bundle[] bundleArr = new Bundle[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass03r r6 = list.get(i);
            Bundle bundle = new Bundle();
            CharSequence charSequence = r6.A05;
            if (charSequence != null) {
                bundle.putCharSequence("text", charSequence);
            }
            bundle.putLong(AnalyticsEventBase.TIME_KEY, r6.A03);
            AnonymousClass045 r7 = r6.A04;
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
                bundle.putBundle(AppLinks.KEY_NAME_EXTRAS, bundle2);
            }
            bundleArr[i] = bundle;
        }
        return bundleArr;
    }
}
