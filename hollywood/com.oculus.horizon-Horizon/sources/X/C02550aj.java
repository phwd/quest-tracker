package X;

import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;

@VisibleForTesting
/* renamed from: X.0aj  reason: invalid class name and case insensitive filesystem */
public final class C02550aj {
    public final C06510nV A00;
    public final AnonymousClass0nN A01;

    @Nullable
    public static C02540ai A00(String str, AnonymousClass0WD r3) {
        String str2;
        try {
            str2 = r3.A4R(str, "");
        } catch (Exception e) {
            AnonymousClass0NO.A0H("RegistrationState", e, "get reg state string failed");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            return C02540ai.A00(str2);
        } catch (JSONException e2) {
            AnonymousClass0NO.A0H("RegistrationState", e2, "Parse failed");
            return null;
        }
    }

    public final List<C02540ai> A03() {
        Map<String, ?> A2x = this.A00.A00(AnonymousClass0WE.REGISTRATIONS).A2x();
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, ?> entry : A2x.entrySet()) {
            try {
                entry.getKey();
                entry.getValue();
                C02540ai A002 = C02540ai.A00(entry.getValue().toString());
                if (!A002.A04) {
                    linkedList.add(A002);
                }
            } catch (JSONException e) {
                AnonymousClass0NO.A0H("RegistrationState", e, "Parse failed");
            }
        }
        return linkedList;
    }

    public final void A04() {
        AnonymousClass0WD A002 = this.A00.A00(AnonymousClass0WE.REGISTRATIONS);
        C06520nY A2L = A002.A2L();
        for (String str : A002.A2x().keySet()) {
            C02540ai A003 = A00(str, A002);
            if (A003 == null) {
                AnonymousClass0NO.A0E("RegistrationState", "invalid value for %s", str);
            } else {
                A003.A03 = "";
                A003.A00 = Long.valueOf(System.currentTimeMillis());
                try {
                    A2L.A00.putString(str, A003.A01());
                } catch (JSONException e) {
                    AnonymousClass0NO.A0H("RegistrationState", e, "RegistrationCacheEntry serialization failed");
                }
            }
        }
        A2L.A00();
    }

    public C02550aj(AnonymousClass0nN r5, C01600Wb r6, C06510nV r7) {
        this.A01 = r5;
        this.A00 = r7;
        AnonymousClass0WD A002 = r7.A00(AnonymousClass0WE.FBNS_STATE);
        String A4R = A002.A4R("mqtt_version", "");
        String str = r6.A00;
        if (!A4R.equals(str)) {
            A04();
            C06520nY A2L = A002.A2L();
            A2L.A00.putString("mqtt_version", str);
            A2L.A00();
        }
    }

    public static boolean A01(String str, C02540ai r4, AnonymousClass0WD r5) {
        try {
            String A012 = r4.A01();
            C06520nY A2L = r5.A2L();
            A2L.A00.putString(str, A012);
            A2L.A00();
            return true;
        } catch (JSONException e) {
            AnonymousClass0NO.A0H("RegistrationState", e, "RegistrationCacheEntry serialization failed");
            return false;
        }
    }

    @Nullable
    public final String A02(@Nonnull String str) {
        AnonymousClass0W9.A00(!TextUtils.isEmpty(str));
        C02540ai A002 = A00(str, this.A00.A00(AnonymousClass0WE.REGISTRATIONS));
        if (A002 != null && !A002.A04) {
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = A002.A00.longValue();
            if (longValue + VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS >= currentTimeMillis && longValue <= currentTimeMillis) {
                return A002.A03;
            }
        }
        return null;
    }
}
