package com.oculus.browser;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ShellEnvironment {

    /* renamed from: a  reason: collision with root package name */
    public Bundle f9710a;
    public Map b;
    public int c;
    public int d;
    public String e;
    public boolean f;
    public List g;
    public String h;
    public String i;
    public float j;
    public float k;
    public int l;
    public EnumC4488qw0 m;

    public ShellEnvironment(Bundle bundle) {
        List list;
        EnumC4488qw0 qw0;
        float f2;
        float f3;
        this.f9710a = bundle;
        HashMap hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            if (str.startsWith("_oc_gk:")) {
                hashMap.put(str.substring(7), bundle.getString(str));
            }
        }
        this.b = hashMap;
        String[] split = bundle.getString("_oc_shell_version_name").split("\\.");
        boolean z = true;
        int i2 = 0;
        if (split.length >= 2) {
            this.c = Integer.parseInt(split[0]);
            this.d = Integer.parseInt(split[1]);
        }
        StringBuilder i3 = AbstractC2531fV.i("parseVersionCode: ");
        i3.append(this.c);
        i3.append(".");
        i3.append(this.d);
        AbstractC1220Ua0.d("ShellEnvironment", i3.toString(), new Object[0]);
        String string = bundle.getString("_oc_device_type");
        AbstractC1220Ua0.d("ShellEnvironment", AbstractC2531fV.f("parseDeviceType: ", string), new Object[0]);
        this.e = string;
        String string2 = bundle.getString("_oc_enterprise_mode");
        AbstractC1220Ua0.d("ShellEnvironment", AbstractC2531fV.f("parseIsEnterpriseDevice: ", string2), new Object[0]);
        this.f = (string2 == null || !string2.equals("true")) ? false : z;
        String string3 = bundle.getString("_oc_shell_feature_sets");
        if (string3 == null) {
            list = new ArrayList();
        } else {
            list = Arrays.asList(string3.split(","));
        }
        this.g = list;
        this.l = isShellFeatureSupported("multiapp-v1") ? Integer.parseInt(bundle.getString("_oc_shell_panel_id")) : i2;
        if (!isShellFeatureSupported("multiapp-v1") || !bundle.containsKey("initialMonitorId")) {
            qw0 = EnumC4488qw0.CENTER;
        } else {
            qw0 = EnumC4488qw0.a(Integer.parseInt(bundle.getString("initialMonitorId")));
        }
        this.m = qw0;
        this.h = bundle.getString("gk_data");
        this.i = bundle.getString("uri");
        String string4 = bundle.getString("_oc_shell_render_scale");
        if (string4 == null) {
            f2 = 1.0f;
        } else {
            f2 = Float.parseFloat(string4);
        }
        this.j = f2;
        String string5 = bundle.getString("_oc_shell_pixel_density");
        if (string5 == null) {
            f3 = 13.0f;
        } else {
            f3 = Float.parseFloat(string5);
        }
        this.k = f3;
        if (bundle.getString("use_test_resolver_rules", "0").equals("1")) {
            AbstractC1575Zv.e().b("host-resolver-rules", "MAP *:80 127.0.0.1:8080,MAP *:443 127.0.0.1:8081,EXCLUDE localhost");
            AbstractC1575Zv.e().a("ignore-certificate-errors");
        }
    }

    public boolean a() {
        String string = this.f9710a.getString("_oc_shell_automation_enabled");
        AbstractC1220Ua0.d("ShellEnvironment", AbstractC2531fV.f("_oc_shell_automation_enabled=", string), new Object[0]);
        return "true".equals(string);
    }

    public String getDeviceType() {
        return this.e;
    }

    public String getGateKeeper(String str) {
        return (String) this.b.get(str);
    }

    public float getPixelDensity() {
        return this.k;
    }

    public float getRenderScale() {
        return this.j;
    }

    public boolean isEnterpriseDevice() {
        return this.f;
    }

    public boolean isShellFeatureSupported(String str) {
        return this.g.contains(str);
    }

    public boolean meetsMinVrShellVersion(int i2, int i3) {
        int i4 = this.c;
        return i4 > i2 || (i4 == i2 && this.d >= i3);
    }
}
