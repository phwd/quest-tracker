package defpackage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: n61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3836n61 {

    /* renamed from: a  reason: collision with root package name */
    public final List f10472a;
    public final List b;

    public C3836n61(List list, List list2) {
        this.f10472a = Collections.unmodifiableList(list);
        this.b = Collections.unmodifiableList(list2);
    }

    public static List a(String str) {
        JSONArray jSONArray = new JSONArray(str);
        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            linkedList.add(new C3665m61(Integer.parseInt(jSONObject.getString("id")), jSONObject.getString("title"), jSONObject.getString("url"), null, jSONObject.getString("referrer"), Long.parseLong(jSONObject.getString("timestamp")), null, false));
        }
        return linkedList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C3836n61)) {
            return false;
        }
        C3836n61 n61 = (C3836n61) obj;
        List list = this.b;
        if (list != null ? list.equals(n61.b) : n61.b == null) {
            List list2 = this.f10472a;
            if (list2 == null) {
                if (n61.f10472a == null) {
                    return true;
                }
            } else if (list2.equals(n61.f10472a)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        List list = this.b;
        int i = 0;
        int hashCode = (527 + (list == null ? 0 : list.hashCode())) * 31;
        List list2 = this.f10472a;
        if (list2 != null) {
            i = list2.hashCode();
        }
        return hashCode + i;
    }
}
