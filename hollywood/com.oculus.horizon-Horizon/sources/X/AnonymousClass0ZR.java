package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ZR  reason: invalid class name */
public final class AnonymousClass0ZR {
    public List<AnonymousClass0ZS> A00;
    public final int A01 = 10;
    public final AnonymousClass0WD A02;
    public final String A03;
    public final TreeSet<AnonymousClass0ZS> A04;

    @Nullable
    public final synchronized AnonymousClass0ZS A00(AnonymousClass0ZS r4) {
        Iterator<AnonymousClass0ZS> it = A01().iterator();
        while (it.hasNext()) {
            AnonymousClass0ZS next = it.next();
            if (next.equals(r4)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a3, code lost:
        if (r4.A00().isEmpty() != false) goto L_0x00a5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.TreeSet<X.AnonymousClass0ZS> A01() {
        /*
        // Method dump skipped, instructions count: 189
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0ZR.A01():java.util.TreeSet");
    }

    public final synchronized void A02() {
        this.A00.clear();
        Iterator<AnonymousClass0ZS> it = A01().iterator();
        while (it.hasNext()) {
            this.A00.add(it.next());
        }
        AnonymousClass0WD r0 = this.A02;
        if (r0 != null) {
            try {
                C06520nY A2L = r0.A2L();
                String str = this.A03;
                JSONObject jSONObject = new JSONObject();
                if (this.A00 != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (AnonymousClass0ZS r8 : this.A00) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.putOpt("host_name", r8.A02);
                        jSONObject2.put("priority", r8.A01);
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
                A2L.A00.putString(str, jSONObject.toString());
                A2L.A00();
            } catch (JSONException e) {
                AnonymousClass0NO.A0H("AddressEntries", e, "Failed to save addressEntries");
            }
        }
    }

    public final synchronized void A03(AnonymousClass0ZS r4) {
        TreeSet<AnonymousClass0ZS> treeSet = this.A04;
        if (treeSet.size() >= this.A01) {
            treeSet.pollLast();
        }
        treeSet.add(r4);
    }

    public final synchronized void A04(AnonymousClass0ZS r2, AnonymousClass0ZS r3) {
        this.A04.remove(r2);
        A03(r3);
    }

    public AnonymousClass0ZR(AnonymousClass0WD r3) {
        this.A02 = r3;
        this.A03 = "/settings_mqtt_address";
        this.A04 = new TreeSet<>(new AnonymousClass0ZQ(this));
        this.A00 = new ArrayList();
    }
}
