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
/* renamed from: X.0xC  reason: invalid class name and case insensitive filesystem */
public final class C08410xC {
    public List<C08440xF> A00;
    public final int A01 = 10;
    public final AnonymousClass0ux A02;
    public final String A03;
    public final TreeSet<C08440xF> A04;

    @Nullable
    public final synchronized C08440xF A00(C08440xF r4) {
        Iterator<C08440xF> it = A01().iterator();
        while (it.hasNext()) {
            C08440xF next = it.next();
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
    public final synchronized java.util.TreeSet<X.C08440xF> A01() {
        /*
        // Method dump skipped, instructions count: 189
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08410xC.A01():java.util.TreeSet");
    }

    public final synchronized void A02() {
        this.A00.clear();
        Iterator<C08440xF> it = A01().iterator();
        while (it.hasNext()) {
            this.A00.add(it.next());
        }
        AnonymousClass0ux r0 = this.A02;
        if (r0 != null) {
            try {
                C07720vq A2E = r0.A2E();
                String str = this.A03;
                JSONObject jSONObject = new JSONObject();
                if (this.A00 != null) {
                    JSONArray jSONArray = new JSONArray();
                    for (C08440xF r8 : this.A00) {
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
                A2E.A00.putString(str, jSONObject.toString());
                A2E.A00();
            } catch (JSONException e) {
                AnonymousClass0NK.A09("AddressEntries", e, "Failed to save addressEntries");
            }
        }
    }

    public final synchronized void A03(C08440xF r4) {
        TreeSet<C08440xF> treeSet = this.A04;
        if (treeSet.size() >= this.A01) {
            treeSet.pollLast();
        }
        treeSet.add(r4);
    }

    public final synchronized void A04(C08440xF r2, C08440xF r3) {
        this.A04.remove(r2);
        A03(r3);
    }

    public C08410xC(AnonymousClass0ux r3) {
        this.A02 = r3;
        this.A03 = "/settings_mqtt_address";
        this.A04 = new TreeSet<>(new C09180yo(this));
        this.A00 = new ArrayList();
    }
}
