package com.facebook.rti.push.service;

import X.AbstractC08430xE;
import X.AbstractC09550ze;
import X.AbstractServiceC08280wx;
import X.AnonymousClass006;
import X.AnonymousClass007;
import X.AnonymousClass0NK;
import X.AnonymousClass0uf;
import X.AnonymousClass0ug;
import X.AnonymousClass0uj;
import X.AnonymousClass0ul;
import X.AnonymousClass0um;
import X.AnonymousClass0un;
import X.AnonymousClass0uo;
import X.AnonymousClass0up;
import X.AnonymousClass0us;
import X.AnonymousClass0ut;
import X.AnonymousClass0uu;
import X.AnonymousClass0ux;
import X.AnonymousClass0v0;
import X.AnonymousClass0v1;
import X.AnonymousClass0v2;
import X.AnonymousClass0v3;
import X.AnonymousClass0v8;
import X.AnonymousClass0vB;
import X.AnonymousClass0vD;
import X.AnonymousClass0vF;
import X.AnonymousClass0vJ;
import X.AnonymousClass0vM;
import X.AnonymousClass0vO;
import X.AnonymousClass0yD;
import X.AnonymousClass0yX;
import X.AnonymousClass151;
import X.AnonymousClass153;
import X.C04980iE;
import X.C07510vP;
import X.C07710vp;
import X.C07720vq;
import X.C07770vz;
import X.C07800w2;
import X.C07850w7;
import X.C07860w8;
import X.C08100wZ;
import X.C08110wa;
import X.C08130wc;
import X.C08170wh;
import X.C08310x0;
import X.C08340x4;
import X.C08350x5;
import X.C08560xS;
import X.C09120yh;
import X.C09450zT;
import X.C09540zd;
import X.EnumC07690vn;
import X.EnumC08570xT;
import X.EnumC08820xs;
import X.EnumC08980yG;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.facebook.push.fbns.ipc.FbnsAIDLRequest;
import com.facebook.push.fbns.ipc.FbnsAIDLResult;
import com.facebook.push.fbns.ipc.IFbnsAIDLService;
import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class FbnsService extends AbstractServiceC08280wx {
    public static FbnsService A09;
    public static final List<SubscribeTopic> A0A = new AnonymousClass0v8();
    public static final List<SubscribeTopic> A0B = new AnonymousClass0v2();
    public C07800w2 A00;
    public AnonymousClass0uj A01;
    public C08340x4 A02;
    public AnonymousClass0uf A03;
    public C07770vz A04;
    @VisibleForTesting
    public AnonymousClass0vD A05;
    @VisibleForTesting
    public AnonymousClass0ug A06;
    public String A07;
    public final IFbnsAIDLService.Stub A08 = new IFbnsAIDLService.Stub() {
        /* class com.facebook.rti.push.service.FbnsService.AnonymousClass3 */
        public final Map<EnumC08820xs, AbstractC09550ze> A00;

        @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
        public final FbnsAIDLResult A75(FbnsAIDLRequest fbnsAIDLRequest) {
            AbstractC09550ze A002 = A00(fbnsAIDLRequest, true);
            FbnsService fbnsService = FbnsService.this;
            Bundle bundle = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            return new FbnsAIDLResult(A002.A2A(fbnsService, bundle));
        }

        @Override // com.facebook.push.fbns.ipc.IFbnsAIDLService
        public final void A8h(FbnsAIDLRequest fbnsAIDLRequest) {
            AbstractC09550ze A002 = A00(fbnsAIDLRequest, false);
            FbnsService fbnsService = FbnsService.this;
            Bundle bundle = ((FbnsAIDLResult) fbnsAIDLRequest).A00;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            A002.A2B(fbnsService, bundle);
        }

        {
            HashMap hashMap = new HashMap();
            this.A00 = hashMap;
            EnumC08820xs r0 = EnumC08820xs.GET_PREF_BASED_CONFIG;
            AbstractC09550ze r2 = C07860w8.A02;
            hashMap.put(r0, r2);
            this.A00.put(EnumC08820xs.SET_PREF_BASED_CONFIG, r2);
            Map<EnumC08820xs, AbstractC09550ze> map = this.A00;
            EnumC08820xs r1 = EnumC08820xs.GET_APPS_STATISTICS;
            map.put(r1, new C07850w7());
            Map<EnumC08820xs, AbstractC09550ze> map2 = this.A00;
            AbstractC09550ze r22 = C07860w8.A01;
            map2.put(r1, r22);
            this.A00.put(EnumC08820xs.GET_ANALYTICS_CONFIG, r22);
            this.A00.put(EnumC08820xs.SET_ANALYTICS_CONFIG, r22);
            this.A00.put(EnumC08820xs.GET_FLYTRAP_REPORT, new C08350x5());
            Map<EnumC08820xs, AbstractC09550ze> map3 = this.A00;
            EnumC08820xs r02 = EnumC08820xs.GET_PREF_IDS;
            AbstractC09550ze r23 = C07860w8.A03;
            map3.put(r02, r23);
            this.A00.put(EnumC08820xs.SET_PREF_IDS, r23);
        }

        private AbstractC09550ze A00(FbnsAIDLRequest fbnsAIDLRequest, boolean z) {
            String str;
            int i;
            if (fbnsAIDLRequest == null || (i = fbnsAIDLRequest.A00) < 0) {
                AnonymousClass0NK.A01("FbnsService", "Invalid FbnsAIDLRequest");
                str = "FbnsService received invalid FbnsAIDLRequest";
            } else {
                EnumC08820xs fromOperationType = EnumC08820xs.fromOperationType(i);
                if (fromOperationType == EnumC08820xs.NOT_EXIST) {
                    str = "FbnsService operation not found";
                } else if (fromOperationType.hasReturn() == z) {
                    AbstractC09550ze r0 = this.A00.get(fromOperationType);
                    if (r0 != null) {
                        return r0;
                    }
                    str = "FbnsService does not implement operation" + fromOperationType;
                } else {
                    AnonymousClass0NK.A01("FbnsService", "FbnsAIDLOperation incorrect return type");
                    str = "FbnsService operation incorrect return type";
                }
            }
            throw new IllegalArgumentException(str);
        }
    };

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x01ee, code lost:
        if ((!r10.A06.A02) != false) goto L_0x01f0;
     */
    @Override // X.AbstractServiceC08280wx
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C07490ue A0F() {
        /*
        // Method dump skipped, instructions count: 1445
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A0F():X.0ue");
    }

    public final synchronized void A0Q(ArrayList<String> arrayList) {
        for (AnonymousClass0un r0 : this.A06.A03()) {
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
    public static final void A03(com.facebook.rti.push.service.FbnsService r8, android.content.Intent r9) {
        /*
        // Method dump skipped, instructions count: 371
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A03(com.facebook.rti.push.service.FbnsService, android.content.Intent):void");
    }

    private void A04(Integer num, AnonymousClass0us r16, String str) {
        AnonymousClass0uj r4 = this.A01;
        String str2 = r16.A02;
        String str3 = r16.A04;
        long j = ((AbstractServiceC08280wx) this).A00;
        boolean A002 = super.A07.A00();
        long j2 = super.A07.A03.get();
        Map<String, String> A003 = C09120yh.A00("event_type", AnonymousClass0vJ.A00(num));
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
        A003.put("s_net_ms", String.valueOf(elapsedRealtime - r4.A01.A06.get()));
        if (j2 > 0) {
            A003.put("is_scr_on", String.valueOf(A002));
            A003.put("s_scr_ms", String.valueOf(elapsedRealtime - j2));
        }
        AnonymousClass0uj.A01(r4, "fbns_message_event", A003);
    }

    private void A05(String str, String str2, String str3) {
        C07510vP r4 = new C07510vP(str, str2, str3);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("tk", r4.A02);
            jSONObject.putOpt("pn", r4.A01);
            jSONObject.putOpt("aid", r4.A00);
            String jSONObject2 = jSONObject.toString();
            AnonymousClass0v3 r6 = new AnonymousClass0v3(this);
            try {
                try {
                    if (this.A09.A05("/fbns_unreg_req", jSONObject2.getBytes("UTF-8"), EnumC08980yG.ACKNOWLEDGED_DELIVERY, r6) != -1) {
                        return;
                    }
                    this.A01.A02(AnonymousClass007.A08, null);
                } catch (UnsupportedEncodingException unused) {
                    throw new RuntimeException("UTF-8 not supported");
                }
            } catch (C09540zd unused2) {
            }
        } catch (JSONException e) {
            AnonymousClass0NK.A09("FbnsService", e, "service/unregister/serialization_exception");
            this.A01.A02(AnonymousClass007.A0H, null);
        }
    }

    @Override // X.AnonymousClass11R, X.AbstractServiceC08280wx
    public final void A0E(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        try {
            printWriter.println(AnonymousClass006.A07("[ ", "FbnsService", " ]"));
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            A0R(arrayList, arrayList2);
            ArrayList<String> arrayList3 = new ArrayList<>();
            A0Q(arrayList3);
            printWriter.println("validCompatibleApps=" + arrayList);
            printWriter.println("enabledCompatibleApps=" + arrayList2);
            printWriter.println("registeredApps=" + arrayList3);
            printWriter.println("notificationCounter=" + super.A05.A01);
        } catch (Exception unused) {
        }
        super.A0E(fileDescriptor, printWriter, strArr);
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0M(AnonymousClass0yD r18) {
        Intent intent;
        super.A0M(r18);
        AnonymousClass0uf r4 = this.A03;
        ArrayList<AnonymousClass0vM> arrayList = new ArrayList();
        ArrayList<AnonymousClass0vM> arrayList2 = new ArrayList();
        AnonymousClass0um A012 = r4.A01();
        synchronized (A012) {
            arrayList.clear();
            arrayList2.clear();
            SharedPreferences sharedPreferences = A012.A01;
            Map<String, ?> all = sharedPreferences.getAll();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            boolean z = false;
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                AnonymousClass0up A002 = AnonymousClass0up.A00(entry.getValue());
                if (A002 != null) {
                    long j = A002.A01;
                    if (j + 86400000 >= System.currentTimeMillis() && j <= System.currentTimeMillis()) {
                        if (A002.A00 + A012.A00.get() < System.currentTimeMillis()) {
                            A002.A00 = System.currentTimeMillis();
                            arrayList.add(A002);
                            edit.putString(((AnonymousClass0vM) A002).A01, A002.A01());
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
        for (AnonymousClass0vM r1 : arrayList2) {
            if (!(r1 == null || (intent = r1.A00) == null)) {
                r4.A02(r1.A01, intent.getPackage(), AnonymousClass0ut.DATA_EXPIRED);
            }
        }
        int i = 0;
        for (AnonymousClass0vM r0 : arrayList) {
            String str = r0.A01;
            Intent intent2 = r0.A00;
            FbnsService fbnsService = r4.A00;
            String str2 = intent2.getPackage();
            AnonymousClass0uj r2 = fbnsService.A01;
            Map<String, String> A003 = C09120yh.A00("event_type", AnonymousClass0vJ.A00(AnonymousClass007.A0J));
            if (!TextUtils.isEmpty(str)) {
                A003.put("event_extra_info", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                A003.put("dpn", str2);
            }
            AnonymousClass0uj.A01(r2, "fbns_message_event", A003);
            fbnsService.A02.log(AnonymousClass006.A08("Redeliver Notif: notifId = ", str, "; target = ", str2));
            AnonymousClass0ut A004 = AnonymousClass0uf.A00(r4, intent2);
            if (A004.isPermanentFailure()) {
                r4.A01().A00(str);
                r4.A02(str, intent2.getPackage(), A004);
            } else if (!A004.isSucceeded()) {
                intent2.getPackage();
            }
            if (A004.isSucceeded()) {
                i++;
            }
        }
        ((AtomicLong) ((AbstractC08430xE) super.A05.A06(C08560xS.class)).A00(EnumC08570xT.FbnsLiteNotificationDeliveryRetried)).addAndGet((long) i);
    }

    public final void A0R(List<String> list, List<String> list2) {
        for (String str : new ArrayList(((AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00)).A06)) {
            C08130wc A002 = C08100wZ.A00(getApplicationContext(), str, 64);
            if (A002.A02 == AnonymousClass007.A0E || A002.A02 == AnonymousClass007.A0C || A002.A02 == AnonymousClass007.A0G) {
                list.add(A002.A00);
            }
            if (A002.A02 == AnonymousClass007.A0G) {
                list2.add(A002.A00);
            }
        }
    }

    public static String A01(String str) {
        if (AnonymousClass0vB.A00(str)) {
            return ((AnonymousClass153) ((AnonymousClass0v1) AnonymousClass151.A00)).A00;
        }
        return FbnsService.class.getName();
    }

    private final void A02(Intent intent) {
        String str;
        String str2 = intent.getPackage();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (str2.equals(getPackageName()) || this.A00.A03(str2)) {
            C07800w2 r1 = this.A00;
            if (r1.A03(str2)) {
                r1.A01(intent, str2);
                return;
            }
            return;
        }
        String A022 = this.A06.A02(str2);
        AnonymousClass0ug r12 = this.A06;
        C08170wh.A00(!TextUtils.isEmpty(str2));
        AnonymousClass0un A002 = AnonymousClass0ug.A00(str2, r12.A00.A00(EnumC07690vn.REGISTRATIONS));
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
            C07770vz r6 = this.A04;
            Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER_RETRY");
            intent.setClassName(r6.A01, str3);
            intent.putExtra("pkg_name", str);
            intent.putExtra("appid", str2);
            r6.A04.A02(intent);
            C04980iE r8 = new C04980iE();
            r8.A04(intent, r6.A01.getClassLoader());
            r8.A01 |= 1;
            r8.A08 = r6.A07;
            Context context = r6.A01;
            PendingIntent service = PendingIntent.getService(context, 0, C04980iE.A01(r8, context), C04980iE.A00(r8, 134217728));
            r6.A05.put(str, service);
            long A3x = r6.A02.A3x(str, 120000);
            r6.A03.A02(r6.A00, SystemClock.elapsedRealtime() + A3x, service);
            long j = A3x * 2;
            if (j > 86400000) {
                j = 86400000;
            }
            C07720vq A2E = r6.A02.A2E();
            A2E.A00.putLong(str, j);
            A2E.A00();
            AnonymousClass0ug r3 = this.A06;
            C08170wh.A00(!TextUtils.isEmpty(str));
            C08170wh.A00(!TextUtils.isEmpty(str2));
            AnonymousClass0un r2 = new AnonymousClass0un();
            r2.A02 = str;
            r2.A01 = str2;
            r2.A00 = Long.valueOf(System.currentTimeMillis());
            AnonymousClass0ug.A01(str, r2, r3.A00.A00(EnumC07690vn.REGISTRATIONS));
            if (AnonymousClass0vF.A00(getApplicationContext())) {
                str4 = AnonymousClass0yX.A01(this.A0A.A0U.A02.A00.A00());
                if (TextUtils.isEmpty(str4)) {
                    AnonymousClass0NK.A01("FbnsService", "service/register/pubKey_empty");
                    return;
                }
            } else {
                str4 = null;
            }
            AnonymousClass0vO r1 = new AnonymousClass0vO(str, str2, str4);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.putOpt("pkg_name", r1.A01);
                jSONObject.putOpt("appid", r1.A00);
                jSONObject.putOpt("pub_key", r1.A02);
                String jSONObject2 = jSONObject.toString();
                try {
                    try {
                        if (this.A09.A05("/fbns_reg_req", jSONObject2.getBytes("UTF-8"), EnumC08980yG.ACKNOWLEDGED_DELIVERY, new AnonymousClass0uu(this)) != -1) {
                            return;
                        }
                    } catch (UnsupportedEncodingException unused) {
                        throw new RuntimeException("UTF-8 not supported");
                    }
                } catch (C09540zd unused2) {
                }
                this.A01.A02(AnonymousClass007.A0G, null);
            } catch (JSONException e) {
                AnonymousClass0NK.A09("FbnsService", e, "service/register/serialize_exception");
                this.A01.A02(AnonymousClass007.A0H, null);
            }
        }
    }

    @Override // X.AnonymousClass11R, X.AbstractServiceC08280wx
    public final void A0D() {
        super.A0D();
        if (A09 == this) {
            A09 = null;
        }
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0G() {
        super.A0G();
        C08310x0 r1 = super.A05;
        this.A03.A01();
        r1.A0G = "S";
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0H() {
        super.A0H();
        AnonymousClass0v0 r0 = (AnonymousClass0v0) this.A0A;
        AnonymousClass0ug r5 = r0.A03;
        AnonymousClass0uj r4 = r0.A01;
        C07770vz r3 = r0.A02;
        C07800w2 r2 = r0.A00;
        AnonymousClass0uf r1 = new AnonymousClass0uf(this, r2, r0.A05);
        this.A06 = r5;
        this.A01 = r4;
        this.A04 = r3;
        this.A05 = new AnonymousClass0vD();
        this.A00 = r2;
        this.A03 = r1;
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0I() {
        super.A0I();
        AnonymousClass0uf r1 = this.A03;
        if (((AnonymousClass0ul) r1).A00 == null) {
            AnonymousClass0uo r4 = new AnonymousClass0uo(r1);
            ((AnonymousClass0ul) r1).A00 = r4;
            C08110wa.A00.A06(r1.A02, r4, new IntentFilter("com.facebook.rti.intent.ACTION_NOTIFICATION_ACK"), null);
        }
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0J() {
        super.A0J();
        AnonymousClass0uf r3 = this.A03;
        BroadcastReceiver broadcastReceiver = ((AnonymousClass0ul) r3).A00;
        if (broadcastReceiver != null) {
            C08110wa.A00.A05(r3.A02, broadcastReceiver);
            ((AnonymousClass0ul) r3).A00 = null;
        }
    }

    @Override // X.AbstractServiceC08280wx
    public final void A0L(Intent intent, C09450zT r10) {
        String str;
        AnonymousClass0uj r2;
        Integer num;
        super.A0L(intent, r10);
        String action = intent.getAction();
        if ("com.facebook.rti.fbns.intent.REGISTER".equals(action) || "com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action) || "com.facebook.rti.fbns.intent.UNREGISTER".equals(action)) {
            String stringExtra = intent.getStringExtra("pkg_name");
            String A002 = C07800w2.A00(intent);
            if (TextUtils.isEmpty(stringExtra)) {
                AnonymousClass0NK.A07("FbnsService", "Empty package name for %s from %s", action, A002);
                r2 = this.A01;
                num = AnonymousClass007.A04;
            } else {
                if ("com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action)) {
                    stringExtra = getPackageName();
                }
                if (!stringExtra.equals(A002)) {
                    AnonymousClass0NK.A07("FbnsService", "Package mismatch for %s from %s: packageName %s", action, A002, stringExtra);
                    r2 = this.A01;
                    num = AnonymousClass007.A03;
                }
            }
            Map<String, String> A003 = C09120yh.A00("event_type", AnonymousClass0uj.A00(num));
            if (!TextUtils.isEmpty(action)) {
                A003.put("event_extra_info", action);
            }
            if (!TextUtils.isEmpty(A002)) {
                A003.put("spn", A002);
            }
            if (!TextUtils.isEmpty(stringExtra)) {
                A003.put("dpn", stringExtra);
            }
            AnonymousClass0uj.A01(r2, "fbns_registration_event", A003);
            return;
        }
        String action2 = intent.getAction();
        if ("com.facebook.rti.fbns.intent.REGISTER".equals(action2)) {
            A0N(AnonymousClass007.A02, r10);
            A03(this, intent);
        } else if ("com.facebook.rti.fbns.intent.REGISTER_RETRY".equals(action2)) {
            A0N(AnonymousClass007.A03, r10);
            A06(intent.getStringExtra("pkg_name"), intent.getStringExtra("appid"), intent.getComponent().getClassName());
        } else if ("com.facebook.rti.fbns.intent.UNREGISTER".equals(action2)) {
            A0N(AnonymousClass007.A04, r10);
            String stringExtra2 = intent.getStringExtra("pkg_name");
            String A022 = this.A06.A02(stringExtra2);
            AnonymousClass0ug r1 = this.A06;
            C08170wh.A00(!TextUtils.isEmpty(stringExtra2));
            C07710vp r0 = r1.A00;
            EnumC07690vn r22 = EnumC07690vn.REGISTRATIONS;
            AnonymousClass0un A004 = AnonymousClass0ug.A00(stringExtra2, r0.A00(r22));
            if (A004 == null) {
                str = null;
            } else {
                str = A004.A01;
            }
            AnonymousClass0ug r12 = this.A06;
            C08170wh.A00(!TextUtils.isEmpty(stringExtra2));
            AnonymousClass0ux A005 = r12.A00.A00(r22);
            AnonymousClass0un A006 = AnonymousClass0ug.A00(stringExtra2, A005);
            if (A006 != null && !A006.A04) {
                A006.A04 = true;
                AnonymousClass0ug.A01(stringExtra2, A006, A005);
            }
            A02(A00(stringExtra2, "unregistered", null));
            this.A01.A02(AnonymousClass007.A05, null);
            if (!(A022 == null || str == null)) {
                A05(A022, stringExtra2, str);
            }
        } else {
            AnonymousClass0NK.A01("FbnsService", "service/doIntent/unrecognized_action");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        A0R(arrayList, arrayList2);
        ArrayList<String> arrayList3 = new ArrayList<>();
        A0Q(arrayList3);
        C08310x0 r13 = super.A05;
        r13.A0I = C08310x0.A02(arrayList);
        r13.A0F = C08310x0.A02(arrayList2);
        r13.A0H = C08310x0.A02(arrayList3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e5  */
    @Override // X.AbstractServiceC08280wx
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.IBinder onBind(android.content.Intent r10) {
        /*
        // Method dump skipped, instructions count: 238
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.onBind(android.content.Intent):android.os.IBinder");
    }

    @Override // X.AnonymousClass11R
    public final void onCreate() {
        super.onCreate();
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractServiceC08280wx
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0O(java.lang.String r21, byte[] r22, int r23, long r24, X.C09440zS r26, @javax.annotation.Nullable java.lang.Long r27) {
        /*
        // Method dump skipped, instructions count: 866
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.rti.push.service.FbnsService.A0O(java.lang.String, byte[], int, long, X.0zS, java.lang.Long):void");
    }
}
