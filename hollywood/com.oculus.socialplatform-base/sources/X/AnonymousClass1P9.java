package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.notifications.NotificationConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1P9  reason: invalid class name */
public final class AnonymousClass1P9 {
    public List<AnonymousClass1P5> A00;
    public final int A01 = 10;
    public final AnonymousClass1PC A02;
    public final String A03;
    public final TreeSet<AnonymousClass1P5> A04;

    @Nullable
    public final synchronized AnonymousClass1P5 A00(AnonymousClass1P5 r4) {
        Iterator<AnonymousClass1P5> it = A01().iterator();
        while (it.hasNext()) {
            AnonymousClass1P5 next = it.next();
            if (next.equals(r4)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a5, code lost:
        if (r4.A00().isEmpty() != false) goto L_0x00a7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.TreeSet<X.AnonymousClass1P5> A01() {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1P9.A01():java.util.TreeSet");
    }

    public final synchronized void A02() {
        this.A00.clear();
        Iterator<AnonymousClass1P5> it = A01().iterator();
        while (it.hasNext()) {
            this.A00.add(it.next());
        }
        AnonymousClass1PC r0 = this.A02;
        if (r0 != null) {
            try {
                AnonymousClass1PB A002 = r0.A00();
                String str = this.A03;
                JSONObject jSONObject = new JSONObject();
                if (this.A00 != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (AnonymousClass1P5 r8 : this.A00) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.putOpt("host_name", r8.A02);
                        jSONObject2.put(NotificationConstants.KEY_PRIORITY, r8.A01);
                        jSONObject2.put("fail_count", r8.A00);
                        if (r8.A04 != null) {
                            JSONArray jSONArray2 = new JSONArray();
                            for (String str2 : r8.A04) {
                                jSONArray2.put(str2);
                            }
                            jSONObject2.put("address_list_data", jSONArray2);
                        }
                        jSONArray.put(jSONObject2.toString());
                    }
                    jSONObject.put("address_entries", jSONArray);
                }
                A002.A00.putString(str, jSONObject.toString());
                A002.A00.apply();
            } catch (JSONException e) {
                AnonymousClass0MD.A0C("AddressEntries", e, "Failed to save addressEntries");
            }
        }
    }

    public final synchronized void A03(AnonymousClass1P5 r4) {
        TreeSet<AnonymousClass1P5> treeSet = this.A04;
        if (treeSet.size() >= this.A01) {
            treeSet.pollLast();
        }
        treeSet.add(r4);
    }

    public final synchronized void A04(AnonymousClass1P5 r2, AnonymousClass1P5 r3) {
        this.A04.remove(r2);
        A03(r3);
    }

    public AnonymousClass1P9(AnonymousClass1PC r3) {
        this.A02 = r3;
        this.A03 = "/settings_mqtt_address";
        this.A04 = new TreeSet<>(new AnonymousClass1P8(this));
        this.A00 = new ArrayList();
    }
}
