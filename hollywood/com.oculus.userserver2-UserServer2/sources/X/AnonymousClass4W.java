package X;

import android.app.Person;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat$CarExtender;
import com.oculus.userserver.api.ExceptionUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.4W  reason: invalid class name */
public final class AnonymousClass4W {
    @Nullable
    public Uri A00;
    public Bundle A01 = new Bundle();
    @Nullable
    public String A02;
    public final long A03;
    @Nullable
    public final C00114k A04;
    public final CharSequence A05;

    @NonNull
    public static List<AnonymousClass4W> A00(Parcelable[] parcelableArr) {
        C00114k r0;
        int length = parcelableArr.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            if (parcelableArr[i] instanceof Bundle) {
                Bundle bundle = (Bundle) parcelableArr[i];
                AnonymousClass4W r3 = null;
                try {
                    if (bundle.containsKey(NotificationCompat$CarExtender.KEY_TEXT) && bundle.containsKey("time")) {
                        if (bundle.containsKey("person")) {
                            r0 = C00114k.A01(bundle.getBundle("person"));
                        } else if (bundle.containsKey("sender_person") && Build.VERSION.SDK_INT >= 28) {
                            r0 = C00114k.A00((Person) bundle.getParcelable("sender_person"));
                        } else if (bundle.containsKey("sender")) {
                            AnonymousClass4j r1 = new AnonymousClass4j();
                            r1.A01 = bundle.getCharSequence("sender");
                            r0 = new C00114k(r1);
                        } else {
                            r0 = null;
                        }
                        AnonymousClass4W r11 = new AnonymousClass4W(bundle.getCharSequence(NotificationCompat$CarExtender.KEY_TEXT), bundle.getLong("time"), r0);
                        if (bundle.containsKey(ExceptionUtils.KEY_TYPE) && bundle.containsKey("uri")) {
                            r11.A02 = bundle.getString(ExceptionUtils.KEY_TYPE);
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

    public AnonymousClass4W(CharSequence charSequence, long j, @Nullable C00114k r5) {
        this.A05 = charSequence;
        this.A03 = j;
        this.A04 = r5;
    }

    @NonNull
    public static Bundle[] A01(List<AnonymousClass4W> list) {
        Bundle[] bundleArr = new Bundle[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass4W r6 = list.get(i);
            Bundle bundle = new Bundle();
            CharSequence charSequence = r6.A05;
            if (charSequence != null) {
                bundle.putCharSequence(NotificationCompat$CarExtender.KEY_TEXT, charSequence);
            }
            bundle.putLong("time", r6.A03);
            C00114k r7 = r6.A04;
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
                bundle.putString(ExceptionUtils.KEY_TYPE, str);
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
