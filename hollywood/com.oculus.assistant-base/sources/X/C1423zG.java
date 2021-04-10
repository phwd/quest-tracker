package X;

import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.flexiblesampling.SamplingResult;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.zG  reason: case insensitive filesystem */
public final class C1423zG implements IT {
    public UnifiedTelemetryLogger A00;
    public C0462Zt A01 = new C0462Zt();
    public final C0935pA A02;

    @Override // X.IT
    public final void A3l(Ii ii) {
        String str;
        String str2;
        StringBuilder sb;
        C0848js jsVar;
        JSONObject jSONObject;
        C0935pA pAVar = this.A02;
        C00446t r3 = null;
        try {
            AnonymousClass6J r7 = pAVar.A00;
            EnumC00486y r5 = EnumC00486y.CLIENT_EVENT;
            SamplingResult samplingResult = SamplingResult.A02;
            if (samplingResult == null) {
                E9 e9 = new E9();
                e9.A01 = true;
                e9.A00 = 1;
                samplingResult = new SamplingResult(e9);
                SamplingResult.A02 = samplingResult;
            }
            C00446t A002 = AnonymousClass6J.A00(r7, null, "perf", r5);
            A002.A05();
            if (samplingResult.A01) {
                A002.A02 = AnonymousClass75.HAS_DOWNLOADED_SAMPLING_POLICY.getTag() | A002.A02;
            }
            A002.A02 = AnonymousClass75.IS_EVENT_LOGGED_UNSAMPLED.getTag() | A002.A02;
            if (!(A002 instanceof C0685fM)) {
                C00446t.A00(A002);
            }
            C0847jr A04 = A002.A04();
            pAVar.A00(A04, "marker_id", ii.getMarkerId());
            pAVar.A00(A04, "instance_id", ii.A35());
            pAVar.A00(A04, ErrorReportingConstants.SOFT_ERROR_OCCURRENCE_COUNT, ii.A2q());
            pAVar.A01(A04, "time_since_boot_ms", ii.A2i());
            pAVar.A01(A04, "duration_ns", ii.A2O());
            pAVar.A00(A04, "action_id", ii.A2G());
            pAVar.A00(A04, "marker_type", ii.A2d());
            String A36 = ii.A36();
            if (A36 != null) {
                C0847jr.A01(A04, "unique_marker_id_debug_only", A36);
            }
            if (ii.A3P() && ii.A3V()) {
                C0847jr.A01(A04, "app_started_in_bg", Boolean.valueOf(ii.A2T()));
            }
            boolean A3Z = ii.A3Z();
            boolean A3O = ii.A3O();
            if (A3Z) {
                str = "missing_config";
            } else if (A3O) {
                str = "always_on";
            } else {
                str = "random_sampling";
            }
            C0847jr.A01(A04, "method", str);
            int A2Z = ii.A2Z();
            if (A2Z != 0) {
                pAVar.A00(A04, "da_level", A2Z);
            }
            String A2z = ii.A2z();
            if (A2z != null) {
                C0847jr.A01(A04, "da_type", A2z);
            }
            List A2R = ii.A2R();
            List A2S = ii.A2S();
            int size = A2R.size() - 1;
            C0847jr jrVar = null;
            C0847jr jrVar2 = null;
            C0847jr jrVar3 = null;
            C0847jr jrVar4 = null;
            C0847jr jrVar5 = null;
            C0847jr jrVar6 = null;
            C0847jr jrVar7 = null;
            C0847jr jrVar8 = null;
            for (int i = 0; i < size; i += 2) {
                String str3 = (String) A2R.get(i);
                String str4 = (String) A2R.get(i + 1);
                switch (((Integer) A2S.get(i >> 1)).intValue()) {
                    case 1:
                        if (jrVar == null) {
                            jrVar = A04.A05("annotations");
                        }
                        C0847jr.A01(jrVar, str3, str4);
                        break;
                    case 2:
                    case 3:
                        if (jrVar2 == null) {
                            jrVar2 = A04.A05("annotations_int");
                        }
                        pAVar.A01(jrVar2, str3, Long.parseLong(str4));
                        break;
                    case 4:
                        if (jrVar3 == null) {
                            jrVar3 = A04.A05("annotations_string_array");
                        }
                        pAVar.A05(jrVar3, str3, Ih.A00(str4));
                        break;
                    case 5:
                    case 10:
                        if (jrVar4 == null) {
                            jrVar4 = A04.A05("annotations_int_array");
                        }
                        pAVar.A04(jrVar4, str3, Ih.A00(str4));
                        break;
                    case 6:
                        if (jrVar5 == null) {
                            jrVar5 = A04.A05("annotations_double");
                        }
                        C0847jr.A01(jrVar5, str3, Double.valueOf(Double.parseDouble(str4)));
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                        if (jrVar6 == null) {
                            jrVar6 = A04.A05("annotations_double_array");
                        }
                        pAVar.A03(jrVar6, str3, Ih.A00(str4));
                        break;
                    case 8:
                        if (jrVar7 == null) {
                            jrVar7 = A04.A05("annotations_bool");
                        }
                        C0847jr.A01(jrVar7, str3, Boolean.valueOf(Boolean.parseBoolean(str4)));
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                        if (jrVar8 == null) {
                            jrVar8 = A04.A05("annotations_bool_array");
                        }
                        pAVar.A02(jrVar8, str3, Ih.A00(str4));
                        break;
                }
            }
            String A31 = ii.A31();
            if (!A31.isEmpty()) {
                C0847jr.A01(A04, "trace_tags", A31);
            }
            short A2G = ii.A2G();
            if (A2G == 3) {
                str2 = "client_fail";
            } else if (A2G == 4) {
                str2 = "client_cancel";
            } else {
                str2 = "client_tti";
            }
            C0847jr.A01(A04, "marker", str2);
            long A33 = ii.A33();
            if (A33 != -1) {
                pAVar.A01(A04, "ttl_ms", A33);
            }
            String str5 = Ih.A00;
            if (str5 != null) {
                C0847jr.A01(A04, "scenario", str5);
            }
            if (ii.A3c()) {
                C0847jr.A01(A04, "tracked_for_loss", true);
            }
            if (ii.A2p() != 0) {
                sb = new StringBuilder("markerStart called multiple times without end or cancel");
            } else {
                sb = null;
            }
            IW A2k = ii.A2k();
            if (A2k != null) {
                A2k.A00(new C0926p1(pAVar, A04.A04("points")));
            }
            if (sb != null) {
                C0847jr.A01(A04, "error", sb.toString());
            }
            if (ii.A3A()) {
                Ic A2f = ii.A2f();
                if (!A2f.A00.isEmpty()) {
                    A2f.A01(new C0927p2(pAVar, A04.A05("metadata")));
                }
            }
            if (ii.getMarkerId() != 196678) {
                r3 = A002;
            }
            C0462Zt zt = this.A01;
            zt.A00 = "perf_utl";
            C0847jr A042 = r3.A04();
            StringWriter stringWriter = new StringWriter();
            synchronized (C0848js.class) {
                jsVar = C0848js.A00;
                if (jsVar == null) {
                    jsVar = new C0848js();
                    C0848js.A00 = jsVar;
                }
            }
            A042.A02 = jsVar;
            try {
                jsVar.A02(stringWriter, A042);
                jSONObject = new JSONObject(stringWriter.toString());
            } catch (IOException | JSONException e) {
                C0139Dd.A0O("UTLHoneyClientLoggerImpl", "Unable to encode extras as JSON. Error:%s", e.getMessage(), e);
                jSONObject = new JSONObject();
            }
            zt.A01.putString("perf_payload_json", jSONObject.toString());
            this.A00.reportEvent(zt.A00(), false);
        } catch (Exception e2) {
            throw e2;
        }
    }

    public C1423zG(UnifiedTelemetryLogger unifiedTelemetryLogger, AnonymousClass6J r3) {
        this.A00 = unifiedTelemetryLogger;
        this.A02 = new C0935pA(r3);
    }
}
