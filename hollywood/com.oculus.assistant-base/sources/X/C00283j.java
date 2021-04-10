package X;

import android.os.Bundle;
import java.util.LinkedList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.3j  reason: invalid class name and case insensitive filesystem */
public final class C00283j extends C1300wu {
    public final /* synthetic */ C00263e A00;
    public final /* synthetic */ LinkedList A01;
    public final /* synthetic */ JSONObject A02;
    public final /* synthetic */ JSONObject A03;

    public C00283j(C00263e r1, JSONObject jSONObject, LinkedList linkedList, JSONObject jSONObject2) {
        this.A00 = r1;
        this.A03 = jSONObject;
        this.A01 = linkedList;
        this.A02 = jSONObject2;
    }

    @Override // X.C1300wu
    public final String A03() {
        try {
            JSONObject jSONObject = this.A02;
            return jSONObject.has("type") ? jSONObject.getString("type") : "dialog";
        } catch (JSONException unused) {
            return "dialog";
        }
    }

    @Override // X.C1300wu
    public final JSONObject A04() {
        return this.A02.getJSONObject("data");
    }

    @Override // X.X1, X.C1300wu
    public final boolean A42(String str, String str2, Bundle bundle) {
        C0139Dd.A0H("AssistantShowPanelUICallback", "ID: %s, Action: %s", str, str2);
        if (super.A01.getString("id").equals(str)) {
            JSONObject jSONObject = this.A03;
            if (jSONObject.has(str2)) {
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                    HandlerC0422Wz.A06(jSONObject2);
                    this.A01.add(jSONObject2);
                    return true;
                } catch (JSONException e) {
                    C0139Dd.A0L("AssistantShowPanelUICallback", "Unable to load sub dialog", e);
                }
            } else if ("back".equals(str2)) {
                LinkedList linkedList = this.A01;
                if (linkedList.removeLast() != null && !linkedList.isEmpty()) {
                    HandlerC0422Wz.A06((JSONObject) linkedList.peekLast());
                    return true;
                }
            }
        }
        C0139Dd.A0P("AssistantShowPanelUICallback", "Dialog with Id %s not found", str);
        return false;
    }
}
