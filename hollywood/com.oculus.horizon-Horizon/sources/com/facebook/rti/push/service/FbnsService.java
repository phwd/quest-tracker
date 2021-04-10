package com.facebook.rti.push.service;

import X.AbstractC01570Vx;
import X.AbstractC02010Yp;
import X.AbstractC02020Yq;
import X.AbstractC02430aW;
import X.AbstractServiceC06020mN;
import X.AnonymousClass006;
import X.AnonymousClass007;
import X.AnonymousClass0IW;
import X.AnonymousClass0NO;
import X.AnonymousClass0VY;
import X.AnonymousClass0W2;
import X.AnonymousClass0W4;
import X.AnonymousClass0W9;
import X.AnonymousClass0WD;
import X.AnonymousClass0WE;
import X.AnonymousClass0WT;
import X.AnonymousClass0WZ;
import X.AnonymousClass0Wc;
import X.AnonymousClass0Wu;
import X.AnonymousClass0Yy;
import X.AnonymousClass0ZK;
import X.AnonymousClass0aS;
import X.AnonymousClass0mJ;
import X.AnonymousClass0nF;
import X.AnonymousClass0nJ;
import X.C01880Xv;
import X.C01890Xx;
import X.C01910Xz;
import X.C01990Ym;
import X.C02000Yn;
import X.C02150Zl;
import X.C02420aV;
import X.C02460aZ;
import X.C02470aa;
import X.C02490ac;
import X.C02500ad;
import X.C02510ae;
import X.C02520af;
import X.C02530ag;
import X.C02540ai;
import X.C02550aj;
import X.C04600iE;
import X.C04630id;
import X.C04650ig;
import X.C04800jZ;
import X.C04880jp;
import X.C04900jr;
import X.C04930ju;
import X.C04940jv;
import X.C05990mI;
import X.C06510nV;
import X.C06520nY;
import X.EnumC01870Xu;
import X.EnumC02170Zn;
import X.EnumC02330aI;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.push.fbns.ipc.FbnsAIDLRequest;
import com.facebook.push.fbns.ipc.FbnsAIDLResult;
import com.facebook.push.fbns.ipc.IFbnsAIDLService;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.oculus.horizon.abuse_prevention.AudioCapture;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class FbnsService extends AbstractServiceC06020mN {
    public static FbnsService A09;
    public static final List<SubscribeTopic> A0A = new C02500ad();
    public static final List<SubscribeTopic> A0B = new C02510ae();
    public C01890Xx A00;
    public C02420aV A01;
    public C04900jr A02;
    public C04880jp A03;
    public C02490ac A04;
    @VisibleForTesting
    public C02520af A05;
    @VisibleForTesting
    public C02550aj A06;
    public String A07;
    public final IFbnsAIDLService.Stub A08 = new IFbnsAIDLService.Stub() {
        /* class com.facebook.rti.push.service.FbnsService.AnonymousClass3 */
        public final Map<EnumC02330aI, AbstractC02430aW> A00;

        @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
        public final FbnsAIDLResult A7l(FbnsAIDLRequest fbnsAIDLRequest) {
            AbstractC02430aW A002 = A00(fbnsAIDLRequest, true);
            FbnsService fbnsService = FbnsService.this;
            Bundle bundle = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            return new FbnsAIDLResult(A002.A2G(fbnsService, bundle));
        }

        @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
        public final void A9i(FbnsAIDLRequest fbnsAIDLRequest) {
            AbstractC02430aW A002 = A00(fbnsAIDLRequest, false);
            FbnsService fbnsService = FbnsService.this;
            Bundle bundle = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            A002.A2H(fbnsService, bundle);
        }

        {
            HashMap hashMap = new HashMap();
            this.A00 = hashMap;
            EnumC02330aI r0 = EnumC02330aI.GET_PREF_BASED_CONFIG;
            AbstractC02430aW r2 = C04600iE.A02;
            hashMap.put(r0, r2);
            this.A00.put(EnumC02330aI.SET_PREF_BASED_CONFIG, r2);
            Map<EnumC02330aI, AbstractC02430aW> map = this.A00;
            EnumC02330aI r1 = EnumC02330aI.GET_APPS_STATISTICS;
            map.put(r1, new C04940jv());
            Map<EnumC02330aI, AbstractC02430aW> map2 = this.A00;
            AbstractC02430aW r22 = C04600iE.A01;
            map2.put(r1, r22);
            this.A00.put(EnumC02330aI.GET_ANALYTICS_CONFIG, r22);
            this.A00.put(EnumC02330aI.SET_ANALYTICS_CONFIG, r22);
            this.A00.put(EnumC02330aI.GET_FLYTRAP_REPORT, new C04630id());
            Map<EnumC02330aI, AbstractC02430aW> map3 = this.A00;
            EnumC02330aI r02 = EnumC02330aI.GET_PREF_IDS;
            AbstractC02430aW r23 = C04600iE.A03;
            map3.put(r02, r23);
            this.A00.put(EnumC02330aI.SET_PREF_IDS, r23);
        }

        private AbstractC02430aW A00(FbnsAIDLRequest fbnsAIDLRequest, boolean z) {
            String str;
            int i;
            if (fbnsAIDLRequest == null || (i = fbnsAIDLRequest.A00) < 0) {
                AnonymousClass0NO.A08("FbnsService", "Invalid FbnsAIDLRequest");
                str = "FbnsService received invalid FbnsAIDLRequest";
            } else {
                EnumC02330aI fromOperationType = EnumC02330aI.fromOperationType(i);
                if (fromOperationType == EnumC02330aI.NOT_EXIST) {
                    str = "FbnsService operation not found";
                } else if (fromOperationType.hasReturn() == z) {
                    AbstractC02430aW r0 = this.A00.get(fromOperationType);
                    if (r0 != null) {
                        return r0;
                    }
                    StringBuilder sb = new StringBuilder("FbnsService does not implement operation");
                    sb.append(fromOperationType);
                    str = sb.toString();
                } else {
                    AnonymousClass0NO.A08("FbnsService", "FbnsAIDLOperation incorrect return type");
                    str = "FbnsService operation incorrect return type";
                }
            }
            throw new IllegalArgumentException(str);
        }
    };

    @Override // X.AbstractServiceC06020mN
    public final String A0J() {
        return "FBNS_ALWAYS";
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0K() {
        Integer num = null;
        List<C02540ai> A032 = this.A06.A03();
        this.A06.A04();
        this.A01.A02(AnonymousClass007.A0B, String.valueOf(A032.size()));
        AnonymousClass0WD A002 = this.A0A.A03.A00(AnonymousClass0WE.RUNTIME_PARAMS);
        if (A002.A1r("DELIVERY_RETRY_INTERVAL")) {
            num = Integer.valueOf(A002.A3e("DELIVERY_RETRY_INTERVAL", AudioCapture.AUDIO_RECORDER_INTERVAL_MS));
        }
        A0U(AnonymousClass007.A05, new C01990Ym(num));
        for (C02540ai r3 : A032) {
            Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
            intent.putExtra("pkg_name", r3.A02);
            intent.putExtra("appid", r3.A01);
            intent.setClassName(getPackageName(), getClass().getName());
            A02(intent);
        }
    }

    @Override // X.AbstractServiceC06020mN
    public final boolean A0X(Intent intent) {
        if (intent != null) {
            if (!getApplicationContext().getPackageName().equals(C01890Xx.A00(intent))) {
                this.A01.A05(intent.toString());
                return false;
            }
        }
        return true;
    }

    public final synchronized void A0Y(ArrayList<String> arrayList) {
        for (C02540ai r0 : this.A06.A03()) {
            arrayList.add(r0.A02);
        }
    }

    @VisibleForTesting
    public static final Intent A00(String str, String str2, @Nullable String str3) {
        Intent intent = new Intent("com.facebook.rti.fbns.intent.RECEIVE");
        intent.setPackage(str);
        intent.addCategory(str);
        intent.putExtra("receive_type", str2);
        if (str3 != null) {
            intent.putExtra("data", str3);
        }
        return intent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x014d, code lost:
        if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x0150;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0165  */
    @com.facebook.rti.common.guavalite.annotations.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A02(android.content.Intent r9) {
        /*
        // Method dump skipped, instructions count: 371
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A02(android.content.Intent):void");
    }

    private void A04(Integer num, C02460aZ r16, String str) {
        C02420aV r4 = this.A01;
        String str2 = r16.A02;
        String str3 = r16.A04;
        long j = ((AbstractServiceC06020mN) this).A00;
        boolean A002 = super.A07.A00();
        long j2 = super.A07.A03.get();
        Map<String, String> A003 = AnonymousClass0VY.A00("event_type", AnonymousClass0aS.A00(num));
        if (!TextUtils.isEmpty(str)) {
            A003.put("event_extra_info", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            A003.put("is_buffered", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            A003.put("dpn", str3);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        A003.put("s_boot_ms", String.valueOf(elapsedRealtime));
        A003.put("s_svc_ms", String.valueOf(elapsedRealtime - r4.A00));
        A003.put("s_mqtt_ms", String.valueOf(elapsedRealtime - j));
        A003.put("s_net_ms", String.valueOf(elapsedRealtime - r4.A01.A05.get()));
        if (j2 > 0) {
            A003.put("is_scr_on", String.valueOf(A002));
            A003.put("s_scr_ms", String.valueOf(elapsedRealtime - j2));
        }
        C02420aV.A01(r4, "fbns_message_event", A003);
    }

    private void A05(String str, String str2, String str3) {
        C02530ag r4 = new C02530ag(str, str2, str3);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("tk", r4.A02);
            jSONObject.putOpt("pn", r4.A01);
            jSONObject.putOpt(AttributionIdentifiers.ATTRIBUTION_ID_COLUMN_NAME, r4.A00);
            String obj = jSONObject.toString();
            C04800jZ r6 = new C04800jZ(this);
            try {
                try {
                    if (this.A09.A05("/fbns_unreg_req", obj.getBytes("UTF-8"), EnumC02170Zn.ACKNOWLEDGED_DELIVERY, r6) != -1) {
                        return;
                    }
                    this.A01.A02(AnonymousClass007.A08, null);
                } catch (UnsupportedEncodingException unused) {
                    throw new RuntimeException("UTF-8 not supported");
                }
            } catch (AnonymousClass0ZK unused2) {
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0H("FbnsService", e, "service/unregister/serialization_exception");
            this.A01.A02(AnonymousClass007.A0H, null);
        }
    }

    @Override // X.AbstractServiceC06020mN, X.AbstractServiceC01930Yb
    public final void A0G(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        try {
            printWriter.println(AnonymousClass006.A07("[ ", "FbnsService", " ]"));
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            A0Z(arrayList, arrayList2);
            ArrayList<String> arrayList3 = new ArrayList<>();
            A0Y(arrayList3);
            StringBuilder sb = new StringBuilder();
            sb.append("validCompatibleApps=");
            sb.append(arrayList);
            printWriter.println(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("enabledCompatibleApps=");
            sb2.append(arrayList2);
            printWriter.println(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("registeredApps=");
            sb3.append(arrayList3);
            printWriter.println(sb3.toString());
            StringBuilder sb4 = new StringBuilder();
            sb4.append("notificationCounter=");
            sb4.append(super.A05.A01);
            printWriter.println(sb4.toString());
        } catch (Exception unused) {
        }
        super.A0G(fileDescriptor, printWriter, strArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x025d, code lost:
        if ((!r5.A06.A02) != false) goto L_0x025f;
     */
    @Override // X.AbstractServiceC06020mN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C01940Yd A0H() {
        /*
        // Method dump skipped, instructions count: 1545
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A0H():X.0Yd");
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0Q(int i) {
        this.A03.A01().A00.set(((long) i) * 1000);
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0S(AnonymousClass0Yy r7) {
        if (AnonymousClass0Yy.FAILED_CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD.equals(r7)) {
            if (System.currentTimeMillis() - this.A06.A00.A00(AnonymousClass0WE.FBNS_STATE).A3l("auto_reg_retry", 0) > VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS) {
                C06520nY A2L = this.A06.A00.A00(AnonymousClass0WE.FBNS_STATE).A2L();
                A2L.A00.putLong("auto_reg_retry", System.currentTimeMillis());
                A2L.A00();
                List<C02540ai> A032 = this.A06.A03();
                this.A06.A04();
                this.A01.A02(AnonymousClass007.A06, String.valueOf(A032.size()));
                for (C02540ai r3 : A032) {
                    Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
                    intent.putExtra("pkg_name", r3.A02);
                    intent.putExtra("appid", r3.A01);
                    intent.setClassName(getPackageName(), getClass().getName());
                    A02(intent);
                }
            }
        }
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0T(C02150Zl r18) {
        Intent intent;
        super.A0T(r18);
        C04880jp r5 = this.A03;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        C05990mI A012 = r5.A01();
        synchronized (A012) {
            arrayList.clear();
            arrayList2.clear();
            SharedPreferences sharedPreferences = A012.A01;
            Map<String, ?> all = sharedPreferences.getAll();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            boolean z = false;
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                AnonymousClass0mJ A002 = AnonymousClass0mJ.A00(entry.getValue());
                if (A002 != null) {
                    long j = A002.A01;
                    if (j + VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS >= System.currentTimeMillis() && j <= System.currentTimeMillis()) {
                        if (A002.A00 + A012.A00.get() < System.currentTimeMillis()) {
                            A002.A00 = System.currentTimeMillis();
                            arrayList.add(A002);
                            edit.putString(((AbstractC02020Yq) A002).A01, A002.A01());
                            z = true;
                        }
                    }
                }
                edit.remove(entry.getKey());
                if (A002 != null) {
                    arrayList2.add(A002);
                }
                z = true;
            }
            if (z) {
                edit.apply();
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            AbstractC02020Yq r1 = (AbstractC02020Yq) it.next();
            if (!(r1 == null || (intent = r1.A00) == null)) {
                r5.A04(r1.A01, intent.getPackage(), EnumC01870Xu.DATA_EXPIRED);
            }
        }
        int i = 0;
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AbstractC02020Yq r2 = (AbstractC02020Yq) it2.next();
            r5.A03(r2.A01, r2.A00);
            if (r5.A05(r2)) {
                i++;
            }
        }
        ((AtomicLong) ((AnonymousClass0nJ) super.A05.A06(AnonymousClass0IW.class)).A00(AnonymousClass0nF.FbnsLiteNotificationDeliveryRetried)).addAndGet((long) i);
    }

    public final void A0Z(List<String> list, List<String> list2) {
        for (String str : new ArrayList(((AbstractC01570Vx) AnonymousClass0W2.A00).A06())) {
            AnonymousClass0WT A002 = AnonymousClass0WZ.A00(getApplicationContext(), str, 64);
            if (A002.A02 == AnonymousClass007.A0E || A002.A02 == AnonymousClass007.A0C || A002.A02 == AnonymousClass007.A0G) {
                list.add(A002.A00);
            }
            if (A002.A02 == AnonymousClass007.A0G) {
                list2.add(A002.A00);
            }
        }
    }

    public static String A01(String str) {
        if (C01880Xv.A00(str)) {
            return ((AbstractC01570Vx) AnonymousClass0W2.A00).A00();
        }
        return FbnsService.class.getName();
    }

    private final void A03(Intent intent) {
        String str;
        String str2 = intent.getPackage();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.equals(getPackageName()) || AnonymousClass0WZ.A01(this.A00.A00, str2)) {
            this.A00.A04(intent, str2);
            return;
        }
        String A022 = this.A06.A02(str2);
        C02550aj r1 = this.A06;
        AnonymousClass0W9.A00(!TextUtils.isEmpty(str2));
        C02540ai A002 = C02550aj.A00(str2, r1.A00.A00(AnonymousClass0WE.REGISTRATIONS));
        if (A002 == null) {
            str = null;
        } else {
            str = A002.A01;
        }
        if (A022 != null && str != null) {
            A05(A022, str2, str);
        }
    }

    @VisibleForTesting
    private final void A06(String str, String str2, String str3) {
        String str4;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.A04.A01(str, str2, str3);
            C02550aj r3 = this.A06;
            AnonymousClass0W9.A00(!TextUtils.isEmpty(str));
            AnonymousClass0W9.A00(!TextUtils.isEmpty(str2));
            C02540ai r2 = new C02540ai();
            r2.A02 = str;
            r2.A01 = str2;
            r2.A00 = Long.valueOf(System.currentTimeMillis());
            C02550aj.A01(str, r2, r3.A00.A00(AnonymousClass0WE.REGISTRATIONS));
            if (AnonymousClass0W4.A00(getApplicationContext())) {
                str4 = C01910Xz.A01(this.A0A.A0U.A02.A00.A02());
                if (TextUtils.isEmpty(str4)) {
                    AnonymousClass0NO.A08("FbnsService", "service/register/pubKey_empty");
                    return;
                }
            } else {
                str4 = null;
            }
            C02470aa r4 = new C02470aa(str, str2, str4);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.putOpt("pkg_name", r4.A01);
                jSONObject.putOpt("appid", r4.A00);
                jSONObject.putOpt("pub_key", r4.A02);
                String obj = jSONObject.toString();
                try {
                    try {
                        if (this.A09.A05("/fbns_reg_req", obj.getBytes("UTF-8"), EnumC02170Zn.ACKNOWLEDGED_DELIVERY, new C04650ig(this)) != -1) {
                            return;
                        }
                        this.A01.A02(AnonymousClass007.A0G, null);
                    } catch (UnsupportedEncodingException unused) {
                        throw new RuntimeException("UTF-8 not supported");
                    }
                } catch (AnonymousClass0ZK unused2) {
                }
            } catch (JSONException e) {
                AnonymousClass0NO.A0H("FbnsService", e, "service/register/serialize_exception");
                this.A01.A02(AnonymousClass007.A0H, null);
            }
        }
    }

    @Override // X.AbstractServiceC06020mN, X.AbstractServiceC01930Yb
    public final void A0E() {
        super.A0E();
        if (A09 == this) {
            A09 = null;
        }
    }

    @Override // X.AbstractServiceC06020mN
    public final Integer A0I() {
        return AnonymousClass007.A01;
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0L() {
        super.A0L();
        AnonymousClass0Wu r1 = super.A05;
        this.A03.A01();
        r1.A0G = "S";
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0M() {
        super.A0M();
        C04930ju r0 = (C04930ju) this.A0A;
        C02550aj r5 = r0.A03;
        C02420aV r4 = r0.A01;
        C02490ac r3 = r0.A02;
        C01890Xx r2 = r0.A00;
        C04880jp r1 = new C04880jp(this, r2, r0.A05);
        this.A06 = r5;
        this.A01 = r4;
        this.A04 = r3;
        this.A05 = new C02520af();
        this.A00 = r2;
        this.A03 = r1;
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0N() {
        super.A0N();
        C04880jp r1 = this.A03;
        if (((AbstractC02010Yp) r1).A00 == null) {
            C02000Yn r4 = new C02000Yn(r1);
            ((AbstractC02010Yp) r1).A00 = r4;
            AnonymousClass0Wc.A00.A07(r1.A02, r4, new IntentFilter("com.facebook.rti.intent.ACTION_NOTIFICATION_ACK"), null);
        }
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0O() {
        super.A0O();
        C04880jp r3 = this.A03;
        BroadcastReceiver broadcastReceiver = ((AbstractC02010Yp) r3).A00;
        if (broadcastReceiver != null) {
            AnonymousClass0Wc.A00.A06(r3.A02, broadcastReceiver);
            ((AbstractC02010Yp) r3).A00 = null;
        }
    }

    @Override // X.AbstractServiceC06020mN
    public final void A0R(Intent intent, C01990Ym r12) {
        String str;
        C02420aV r2;
        Integer num;
        super.A0R(intent, r12);
        String action = intent.getAction();
        if ("com.facebook.rti.fbns.intent.REGISTER".equals(action) || "com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action) || "com.facebook.rti.fbns.intent.UNREGISTER".equals(action)) {
            String stringExtra = intent.getStringExtra("pkg_name");
            String A002 = C01890Xx.A00(intent);
            if (TextUtils.isEmpty(stringExtra)) {
                AnonymousClass0NO.A0F("FbnsService", "Empty package name for %s from %s", action, A002);
                r2 = this.A01;
                num = AnonymousClass007.A04;
            } else {
                if ("com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action)) {
                    stringExtra = getPackageName();
                }
                if (!stringExtra.equals(A002)) {
                    AnonymousClass0NO.A0F("FbnsService", "Package mismatch for %s from %s: packageName %s", action, A002, stringExtra);
                    r2 = this.A01;
                    num = AnonymousClass007.A03;
                }
            }
            Map<String, String> A003 = AnonymousClass0VY.A00("event_type", C02420aV.A00(num));
            if (!TextUtils.isEmpty(action)) {
                A003.put("event_extra_info", action);
            }
            if (!TextUtils.isEmpty(A002)) {
                A003.put("spn", A002);
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                A003.put("dpn", stringExtra);
            }
            C02420aV.A01(r2, "fbns_registration_event", A003);
            return;
        }
        String action2 = intent.getAction();
        if ("com.facebook.rti.fbns.intent.REGISTER".equals(action2)) {
            A0U(AnonymousClass007.A02, r12);
            A02(intent);
        } else if ("com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action2)) {
            A0U(AnonymousClass007.A03, r12);
            A06(intent.getStringExtra("pkg_name"), intent.getStringExtra("appid"), intent.getComponent().getClassName());
        } else if ("com.facebook.rti.fbns.intent.UNREGISTER".equals(action2)) {
            A0U(AnonymousClass007.A04, r12);
            String stringExtra2 = intent.getStringExtra("pkg_name");
            String A022 = this.A06.A02(stringExtra2);
            C02550aj r1 = this.A06;
            AnonymousClass0W9.A00(!TextUtils.isEmpty(stringExtra2));
            C06510nV r0 = r1.A00;
            AnonymousClass0WE r22 = AnonymousClass0WE.REGISTRATIONS;
            C02540ai A004 = C02550aj.A00(stringExtra2, r0.A00(r22));
            if (A004 == null) {
                str = null;
            } else {
                str = A004.A01;
            }
            C02550aj r13 = this.A06;
            AnonymousClass0W9.A00(!TextUtils.isEmpty(stringExtra2));
            AnonymousClass0WD A005 = r13.A00.A00(r22);
            C02540ai A006 = C02550aj.A00(stringExtra2, A005);
            if (A006 != null && !A006.A04) {
                A006.A04 = true;
                C02550aj.A01(stringExtra2, A006, A005);
            }
            A03(A00(stringExtra2, "unregistered", null));
            this.A01.A02(AnonymousClass007.A05, null);
            if (!(A022 == null || str == null)) {
                A05(A022, stringExtra2, str);
            }
        } else {
            AnonymousClass0NO.A08("FbnsService", "service/doIntent/unrecognized_action");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        A0Z(arrayList, arrayList2);
        ArrayList<String> arrayList3 = new ArrayList<>();
        A0Y(arrayList3);
        AnonymousClass0Wu r14 = super.A05;
        r14.A0I = AnonymousClass0Wu.A02(arrayList);
        r14.A0F = AnonymousClass0Wu.A02(arrayList2);
        r14.A0H = AnonymousClass0Wu.A02(arrayList3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bd  */
    @Override // X.AbstractServiceC06020mN
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.IBinder onBind(android.content.Intent r10) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.onBind(android.content.Intent):android.os.IBinder");
    }

    @Override // X.AbstractServiceC01930Yb
    public final void onCreate() {
        super.onCreate();
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractServiceC06020mN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0V(java.lang.String r23, byte[] r24, int r25, long r26, X.C01610Wd r28, @javax.annotation.Nullable java.lang.Long r29) {
        /*
        // Method dump skipped, instructions count: 865
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A0V(java.lang.String, byte[], int, long, X.0Wd, java.lang.Long):void");
    }
}
