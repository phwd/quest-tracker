package com.oculus.browser;

import J.N;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import com.oculus.browser.components.OculusCrypto;
import com.oculus.browser.components.OculusUser;
import com.oculus.vrapi.SystemProps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.ApplicationLifetime;
import org.chromium.components.crash.browser.ChildProcessCrashObserver;
import org.chromium.components.safe_browsing.SafeBrowsingApiBridge;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PanelService extends com.oculus.vrshell.panelservice.PanelService {
    public long H;
    public long I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9705J;
    public String K;
    public Timer L;
    public C4582rV0 M;
    public HydraApplication N;

    @Override // com.oculus.vrshell.panelservice.PanelService
    public long a(Surface surface, Map map, Bundle bundle) {
        String str;
        HashMap hashMap;
        if (this.L == null) {
            int i = 0;
            if (!SystemProps.a("debug.oc_shell.dbg_watchdog", false)) {
                Thread currentThread = Thread.currentThread();
                Timer timer = new Timer("PanelService Watchdog timer");
                this.L = timer;
                timer.scheduleAtFixedRate(new C5168uw0(this, currentThread), 3000, 500);
            }
            this.H = System.currentTimeMillis();
            this.I = 0;
            this.f9705J = false;
            d();
            String string = bundle.getString("_oc_userid");
            OculusUser oculusUser = OculusUser.f9719a;
            if (string != null && OculusUser.f9719a == null) {
                OculusUser.f9719a = new OculusUser(string);
            }
            AbstractC1220Ua0.d("PanelService", "createNativePanelAppInstance()", new Object[0]);
            d();
            ShellEnvironment shellEnvironment = new ShellEnvironment(bundle);
            d();
            HydraApplication hydraApplication = this.N;
            if (!hydraApplication.f9702J) {
                TraceEvent.Y("HydraApplication.initialize", null);
                Log.i("HydraApplication", "initialize-begin");
                hydraApplication.f9702J = true;
                hydraApplication.Q = new HashMap();
                hydraApplication.R = new HashMap();
                hydraApplication.S = new ArrayList();
                d();
                hydraApplication.M = new VrShellDelegate(hydraApplication);
                d();
                AbstractC1575Zv.e().a("disable-notifications");
                d();
                AbstractC0281Eo.a(ContextUtils.getApplicationContext(), true);
                Log.i("HydraApplication", "Child processes launched");
                try {
                    d();
                    C1321Vq.b().f(false, this);
                } catch (C2840hG0 e) {
                    Log.i("HydraApplication", "ChromeBrowserInitializer failed", e);
                    System.exit(-1);
                }
                Log.i("HydraApplication", "ChromeBrowserInitializer succeeded");
                d();
                Gatekeeper g = Gatekeeper.g();
                Objects.requireNonNull(g);
                if (shellEnvironment.isEnterpriseDevice()) {
                    g.L = new HashMap();
                    HashMap hashMap2 = new HashMap();
                    TU[] values = TU.values();
                    int length = values.length;
                    while (i < length) {
                        TU tu = values[i];
                        hashMap2.put(tu.A0, Boolean.valueOf(tu.C0));
                        i++;
                    }
                    g.f9701J = hashMap2;
                    if (shellEnvironment.isShellFeatureSupported("multiapp-v2")) {
                        g.f9701J.put("oculus_browser_multi_panel", Boolean.TRUE);
                    }
                } else {
                    HashMap hashMap3 = g.f9701J;
                    if (hashMap3 == null) {
                        if (hashMap3 == null && g.O.a()) {
                            try {
                                hashMap = new HashMap();
                                String string2 = g.O.getString("GK_CACHE", null);
                                String string3 = g.O.getString("PROJECT_CACHE", null);
                                if (string2 != null && string3 != null) {
                                    JSONArray jSONArray = new JSONArray(string3);
                                    JSONArray jSONArray2 = new JSONObject(string2).getJSONArray("data");
                                    while (true) {
                                        if (i >= jSONArray2.length()) {
                                            break;
                                        }
                                        JSONArray jSONArray3 = jSONArray2.getJSONObject(i).getJSONArray("results");
                                        if (jSONArray3.length() != jSONArray.length()) {
                                            Log.e("Gatekeeper", "Failed to load gatekeeper data");
                                            break;
                                        }
                                        int i2 = 0;
                                        while (i2 < jSONArray3.length()) {
                                            hashMap.put(jSONArray.getString(i2), Boolean.valueOf(jSONArray3.getBoolean(i2)));
                                            i2++;
                                            jSONArray3 = jSONArray3;
                                        }
                                        i++;
                                        jSONArray2 = jSONArray2;
                                    }
                                    g.f9701J = hashMap;
                                }
                            } catch (JSONException e2) {
                                StringBuilder i3 = AbstractC2531fV.i("JSON failed to parse: ");
                                i3.append(e2.toString());
                                Log.e("Gatekeeper", i3.toString());
                            }
                        }
                        hashMap = null;
                        g.f9701J = hashMap;
                    }
                    HashMap f = g.f();
                    if (g.f9701J == null) {
                        g.f9701J = f;
                    } else {
                        for (String str2 : f.keySet()) {
                            if (g.f9701J.get(str2) == null) {
                                g.f9701J.put(str2, (Boolean) f.get(str2));
                            }
                        }
                    }
                    if (shellEnvironment.isShellFeatureSupported("multiapp-v2")) {
                        g.f9701J.put("oculus_browser_multi_panel", Boolean.TRUE);
                    } else if (f.containsKey("oculus_browser_multi_panel")) {
                        g.f9701J.put("oculus_browser_multi_panel", Boolean.FALSE);
                    }
                    if (g.L == null) {
                        HashMap hashMap4 = new HashMap();
                        if (g.O.a()) {
                            try {
                                String string4 = g.O.getString("OVERRIDE_PROJECT_NAMES", null);
                                String string5 = g.O.getString("OVERRIDE_VALUES", null);
                                if (!(string4 == null || string5 == null)) {
                                    JSONArray jSONArray4 = new JSONArray(string4);
                                    JSONArray jSONArray5 = new JSONArray(string5);
                                    if (jSONArray4.length() != 0) {
                                        if (jSONArray4.length() == jSONArray5.length()) {
                                            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                                                hashMap4.put(jSONArray4.getString(i4), Boolean.valueOf("1".equals(jSONArray5.getString(i4))));
                                            }
                                        }
                                    }
                                }
                            } catch (JSONException e3) {
                                StringBuilder i5 = AbstractC2531fV.i("JSON failed to parse: ");
                                i5.append(e3.toString());
                                Log.e("Gatekeeper", i5.toString());
                            }
                        }
                        g.L = hashMap4;
                    }
                    boolean a2 = g.Q.a();
                    String str3 = shellEnvironment.h;
                    if (a2 && str3 != null) {
                        for (String str4 : str3.split(",")) {
                            String[] split = str4.split("=");
                            g.L.put(split[0], Boolean.valueOf(split[1].equals("1")));
                        }
                    }
                    for (String str5 : g.L.keySet()) {
                        g.f9701J.put(str5, (Boolean) g.L.get(str5));
                    }
                }
                Set keySet = g.f9701J.keySet();
                String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
                g.H = strArr;
                Arrays.sort(strArr);
                StringBuilder sb = new StringBuilder("");
                StringBuilder sb2 = new StringBuilder("");
                String[] strArr2 = g.H;
                for (String str6 : strArr2) {
                    sb.append(str6);
                    sb.append(",");
                    if (((Boolean) g.f9701J.get(str6)).booleanValue()) {
                        str = "1";
                    } else {
                        str = "0";
                    }
                    sb2.append(str);
                    sb2.append(",");
                }
                sb.toString();
                sb2.toString();
                N.MruLOY9q(sb.toString(), sb2.toString());
                if (!shellEnvironment.isEnterpriseDevice()) {
                    g.k();
                }
                synchronized (Dx1.class) {
                    if (Dx1.F == null) {
                        Dx1.F = new Dx1(Preferences.getInstance(), new QN());
                        String str7 = "default";
                        Class cls = SystemProps.f9720a;
                        try {
                            str7 = (String) SystemProps.f9720a.getMethod("get", String.class, String.class).invoke(null, "ro.build.type", str7);
                        } catch (Exception unused) {
                        }
                        N.MozpYpJ6(str7.equals("userdev") || str7.equals("userdebug"));
                    }
                }
                Dx1 dx1 = Dx1.F;
                String string6 = dx1.K.getString("WOL_JSON_STRING_CACHE", null);
                if (string6 != null) {
                    dx1.g(string6);
                }
                dx1.h();
                d();
                new FacebookLoginManager(hydraApplication);
                Log.i("HydraApplication", "initialize-end");
                TraceEvent.f0("HydraApplication.initialize");
            }
            AbstractC1220Ua0.d("PanelService", "prePanelAppInitialize complete", new Object[0]);
            OculusCrypto.nativeInit();
            AbstractC1220Ua0.d("PanelService", "creating PanelApp", new Object[0]);
            d();
            PanelApp panelApp = new PanelApp(this.N, surface, map, shellEnvironment, this);
            AbstractC1220Ua0.d("PanelService", "PanelApp created", new Object[0]);
            d();
            HydraApplication hydraApplication2 = this.N;
            if (!hydraApplication2.K) {
                TraceEvent.Y("HydraApplication.initialize2", null);
                Log.i("HydraApplication", "initialize2-begin");
                hydraApplication2.K = true;
                d();
                VrShellDelegate vrShellDelegate = hydraApplication2.M;
                if (vrShellDelegate != null) {
                    VrApi.a(vrShellDelegate.f9714J);
                    VrShellDelegate.nativeOnLibraryAvailable();
                    vrShellDelegate.G = vrShellDelegate.nativeInit();
                    StringBuilder i6 = AbstractC2531fV.i("mNativeVrShellDelegate = ");
                    i6.append(vrShellDelegate.G);
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", i6.toString(), new Object[0]);
                }
                d();
                ApplicationLifetime.f10600a.b(hydraApplication2);
                d();
                IntentFilter intentFilter = new IntentFilter();
                d();
                intentFilter.addAction("com.oculus.vrshell.panel.vr_permission.RESULT");
                d();
                U90 a3 = U90.a(hydraApplication2);
                BroadcastReceiver broadcastReceiver = hydraApplication2.U;
                synchronized (a3.d) {
                    T90 t90 = new T90(intentFilter, broadcastReceiver);
                    ArrayList arrayList = (ArrayList) a3.d.get(broadcastReceiver);
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                        a3.d.put(broadcastReceiver, arrayList);
                    }
                    arrayList.add(t90);
                    for (int i7 = 0; i7 < intentFilter.countActions(); i7++) {
                        String action = intentFilter.getAction(i7);
                        ArrayList arrayList2 = (ArrayList) a3.e.get(action);
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList(1);
                            a3.e.put(action, arrayList2);
                        }
                        arrayList2.add(t90);
                    }
                }
                d();
                IntentFilter intentFilter2 = new IntentFilter();
                d();
                intentFilter2.addAction("android.intent.action.SCREEN_OFF");
                ContextUtils.getApplicationContext().registerReceiver(hydraApplication2.V, intentFilter2);
                d();
                hydraApplication2.P = new C1505Yq0(new C1739ar0(hydraApplication2), Uri.parse("https://www.facebook.com/mobile/generic_android_crash_logs/").buildUpon().appendPath("1562231310457908").build().toString(), false, OculusUser.getUserID());
                d();
                C3904nY nYVar = new C3904nY(hydraApplication2);
                Object obj = ThreadUtils.f10596a;
                ChildProcessCrashObserver.f10835a = nYVar;
                d();
                C0591Jq0.a(hydraApplication2);
                d();
                C0591Jq0.b().f(hydraApplication2);
                d();
                C4075oY oYVar = new C4075oY(hydraApplication2);
                Executor executor = AbstractC2032cb.f9616a;
                oYVar.f();
                ((ExecutorC1463Ya) executor).execute(oYVar.e);
                if (Gatekeeper.g().h(TU.SAFE_BROWSING)) {
                    SafeBrowsingApiBridge.f10885a = Qx1.class;
                }
                Log.i("HydraApplication", "initialize2-end");
                TraceEvent.f0("HydraApplication.initialize2");
            }
            AbstractC1220Ua0.d("PanelService", "Application initialized", new Object[0]);
            d();
            HydraApplication hydraApplication3 = this.N;
            if (!hydraApplication3.L && !panelApp.f0.Q && panelApp.Y.isShellFeatureSupported("recoverInstances-v1")) {
                hydraApplication3.L = true;
                int i8 = Preferences.getInstance().getInt("PANEL_RECOVERY", 0);
                PanelApp.F = i8 & -3;
                N.M66e4W7Y(panelApp.f9704J, i8);
            }
            this.I = System.currentTimeMillis();
            d();
            long j = panelApp.f9704J;
            Timer timer2 = this.L;
            if (timer2 != null) {
                timer2.cancel();
                this.L = null;
            }
            return j;
        }
        throw new IllegalStateException("Should not start the watchdog if it's already running");
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public String c(int i) {
        C4412qV0 a2 = this.M.a(i);
        if (a2.f11144a) {
            if (Boolean.valueOf(!Collections.disjoint(AbstractC0212Dk.p, a2.c)).booleanValue()) {
                return null;
            }
        }
        return super.c(i);
    }

    public void d() {
        if (System.currentTimeMillis() - this.H < 8000) {
            this.K = Thread.currentThread().getStackTrace()[3].toString();
        } else {
            this.f9705J = true;
        }
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onCreate() {
        AbstractC1220Ua0.d("PanelService", "onCreate", new Object[0]);
        this.N = (HydraApplication) getApplication();
        Boolean bool = AbstractC0212Dk.f7906a;
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractC0212Dk.b, new HashSet());
        hashMap.put(AbstractC0212Dk.c, new HashSet());
        if (AbstractC0212Dk.f7906a.booleanValue()) {
            hashMap.put(AbstractC0212Dk.d, new HashSet());
        }
        AbstractC0212Dk.a(hashMap, "com.facebook.together.workrooms.dev", AbstractC0212Dk.e);
        AbstractC0212Dk.a(hashMap, "com.facebook.workrooms.dev", AbstractC0212Dk.f);
        AbstractC0212Dk.a(hashMap, "com.facebook.together.workrooms.stable", AbstractC0212Dk.g);
        AbstractC0212Dk.a(hashMap, "com.facebook.workrooms.continuous", AbstractC0212Dk.h);
        AbstractC0212Dk.a(hashMap, "com.facebook.together.workrooms", AbstractC0212Dk.i);
        AbstractC0212Dk.a(hashMap, "com.facebook.workrooms.stable", AbstractC0212Dk.j);
        AbstractC0212Dk.a(hashMap, "com.facebook.together.workrooms.staging", AbstractC0212Dk.k);
        AbstractC0212Dk.a(hashMap, "com.facebook.workrooms.staging", AbstractC0212Dk.l);
        AbstractC0212Dk.a(hashMap, "com.facebook.together.workrooms.release", AbstractC0212Dk.m);
        AbstractC0212Dk.a(hashMap, "com.facebook.workrooms", AbstractC0212Dk.n);
        AbstractC0212Dk.a(hashMap, "com.facebook.together.oculus_browser", AbstractC0212Dk.o);
        Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
        HashSet hashSet = new HashSet();
        hashSet.add(Nn1.h);
        this.M = new C4582rV0(unmodifiableMap, Collections.unmodifiableSet(hashSet), getPackageManager());
        Preferences.f9707a = new Preferences(this.N.getSharedPreferences("OculusBrowserPreferences", 0));
        SystemSettings.setBoolean("browser_crash_reports_enabled", !Preferences.getInstance().getBoolean("telemetryOptedOut", false));
        super.onCreate();
    }

    @Override // com.oculus.vrshell.panelservice.PanelService
    public void onDestroy() {
        long j = this.I - this.H;
        AbstractC1220Ua0.d("PanelService", "onDestroy", new Object[0]);
        if ((this.H != 0 && this.I == 0) || this.f9705J) {
            StringBuilder i = AbstractC2531fV.i("Init Stage: ");
            i.append(this.K);
            throw new C5508ww0(this, i.toString());
        } else if (j > 8000) {
            StringBuilder i2 = AbstractC2531fV.i("Creation time: ");
            i2.append(this.H);
            i2.append(" Init time: ");
            i2.append(this.I);
            throw new C5508ww0(this, i2.toString());
        }
    }
}
