package X;

import android.text.TextUtils;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;

@VisibleForTesting
/* renamed from: X.0ug  reason: invalid class name */
public final class AnonymousClass0ug {
    public final C07710vp A00;
    public final C07640vh A01;

    @Nullable
    public static AnonymousClass0un A00(String str, AnonymousClass0ux r3) {
        String str2;
        try {
            str2 = r3.A4Z(str, "");
        } catch (Exception e) {
            AnonymousClass0NK.A09("RegistrationState", e, "get reg state string failed");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            return AnonymousClass0un.A00(str2);
        } catch (JSONException e2) {
            AnonymousClass0NK.A09("RegistrationState", e2, "Parse failed");
            return null;
        }
    }

    public final List<AnonymousClass0un> A03() {
        Map<String, ?> A2u = this.A00.A00(EnumC07690vn.REGISTRATIONS).A2u();
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, ?> entry : A2u.entrySet()) {
            try {
                entry.getKey();
                entry.getValue();
                AnonymousClass0un A002 = AnonymousClass0un.A00(entry.getValue().toString());
                if (!A002.A04) {
                    linkedList.add(A002);
                }
            } catch (JSONException e) {
                AnonymousClass0NK.A09("RegistrationState", e, "Parse failed");
            }
        }
        return linkedList;
    }

    public final void A04() {
        AnonymousClass0ux A002 = this.A00.A00(EnumC07690vn.REGISTRATIONS);
        C07720vq A2E = A002.A2E();
        for (String str : A002.A2u().keySet()) {
            AnonymousClass0un A003 = A00(str, A002);
            if (A003 == null) {
                AnonymousClass0NK.A06("RegistrationState", "invalid value for %s", str);
            } else {
                A003.A03 = "";
                A003.A00 = Long.valueOf(System.currentTimeMillis());
                try {
                    A2E.A00.putString(str, A003.A01());
                } catch (JSONException e) {
                    AnonymousClass0NK.A09("RegistrationState", e, "RegistrationCacheEntry serialization failed");
                }
            }
        }
        A2E.A00();
    }

    public AnonymousClass0ug(C07640vh r5, C08090wY r6, C07710vp r7) {
        this.A01 = r5;
        this.A00 = r7;
        AnonymousClass0ux A002 = r7.A00(EnumC07690vn.FBNS_STATE);
        String A4Z = A002.A4Z("mqtt_version", "");
        String str = r6.A00;
        if (!A4Z.equals(str)) {
            A04();
            C07720vq A2E = A002.A2E();
            A2E.A00.putString("mqtt_version", str);
            A2E.A00();
        }
    }

    public static boolean A01(String str, AnonymousClass0un r4, AnonymousClass0ux r5) {
        try {
            String A012 = r4.A01();
            C07720vq A2E = r5.A2E();
            A2E.A00.putString(str, A012);
            A2E.A00();
            return true;
        } catch (JSONException e) {
            AnonymousClass0NK.A09("RegistrationState", e, "RegistrationCacheEntry serialization failed");
            return false;
        }
    }

    @Nullable
    public final String A02(@Nonnull String str) {
        C08170wh.A00(!TextUtils.isEmpty(str));
        AnonymousClass0un A002 = A00(str, this.A00.A00(EnumC07690vn.REGISTRATIONS));
        if (A002 != null && !A002.A04) {
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = A002.A00.longValue();
            if (longValue + 86400000 >= currentTimeMillis && longValue <= currentTimeMillis) {
                return A002.A03;
            }
        }
        return null;
    }
}
