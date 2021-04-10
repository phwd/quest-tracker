package X;

import android.os.Bundle;
import android.util.Base64;
import com.facebook.acra.AppComponentStats;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.yU  reason: case insensitive filesystem */
public final class C1393yU implements AnonymousClass8U {
    public final /* synthetic */ AbstractC0106Ak A00;
    public final /* synthetic */ C1394yV A01;

    public C1393yU(C1394yV yVVar, AbstractC0106Ak ak) {
        this.A01 = yVVar;
        this.A00 = ak;
    }

    @Override // X.AnonymousClass8U
    public final void A1I(AnonymousClass8F r14, AnonymousClass8H r15) {
        C0514bB.A02(r15, "response");
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("intentConfidence", r15.A00);
            jSONObject.put("utterance", r15.A06);
            jSONObject.put("phraseMatched", r15.A04);
            jSONObject.put("triggerKey", r15.A05);
            jSONObject.put("nluIntentMatched", r15.A02);
            byte[] bArr = r15.A08;
            if (bArr != null) {
                jSONObject.put("customOutput", Base64.encodeToString(bArr, 0));
            }
            jSONObject.put(AppComponentStats.ATTRIBUTE_NAME, r15.A03);
            JSONArray jSONArray = new JSONArray();
            Map map = r15.A07;
            if (map != null) {
                for (Object obj : map.keySet()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (AnonymousClass8Y r2 : (List) map.get(obj)) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("value", r2.A01);
                        jSONObject2.put("confidence", r2.A00);
                        jSONArray2.put(jSONObject2);
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(AppComponentStats.ATTRIBUTE_NAME, obj);
                    jSONObject3.put("possibleSlotValues", jSONArray2);
                    jSONArray.put(jSONObject3);
                }
            }
            jSONObject.put("matchedSlots", jSONArray);
            jSONObject.put("interactionId", r15.A01);
            str = jSONObject.toString();
        } catch (JSONException e) {
            C0139Dd.A0A("VoiceSDKManager", "Unable to serialize response");
            C00799i.A00.logException("sdk_response_deserialization", e);
        }
        Bundle bundle = new Bundle();
        Map map2 = r15.A07;
        if (map2 != null) {
            for (Map.Entry entry : map2.entrySet()) {
                bundle.putString((String) entry.getKey(), ((AnonymousClass8Y) ((List) entry.getValue()).get(0)).A01);
            }
        }
        String str2 = ((C1223vd) this.A00.A2L()).A01;
        C0514bB.A00(str2);
        String str3 = r15.A05;
        C0514bB.A01(str3, "response.triggerKey");
        C1221vb vbVar = new C1221vb(str2, str3, r15.A08, bundle, str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("appId", vbVar.A02);
        bundle2.putString("actionId", vbVar.A01);
        bundle2.putByteArray("commandOutput", vbVar.A04);
        bundle2.putBundle("slots", vbVar.A00);
        bundle2.putString("jsonResponse", vbVar.A03);
        YM ym = this.A01.A00;
        ym.A07.A01(new C1256wC("VoiceSdkAssistantCommandCallback", bundle2, ym.A08));
    }
}
