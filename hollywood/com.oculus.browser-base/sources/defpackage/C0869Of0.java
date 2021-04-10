package defpackage;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: Of0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0869Of0 {

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f8639a;
    public List b;
    public List c;

    public C0869Of0(Bundle bundle) {
        this.f8639a = bundle;
    }

    public static C0869Of0 b(Bundle bundle) {
        if (bundle != null) {
            return new C0869Of0(bundle);
        }
        return null;
    }

    public void a() {
        if (this.c == null) {
            ArrayList parcelableArrayList = this.f8639a.getParcelableArrayList("controlFilters");
            this.c = parcelableArrayList;
            if (parcelableArrayList == null) {
                this.c = Collections.emptyList();
            }
        }
    }

    public int c() {
        return this.f8639a.getInt("connectionState", 0);
    }

    public String d() {
        return this.f8639a.getString("status");
    }

    public int e() {
        return this.f8639a.getInt("deviceType");
    }

    public Bundle f() {
        return this.f8639a.getBundle("extras");
    }

    public List g() {
        if (this.b == null) {
            ArrayList<String> stringArrayList = this.f8639a.getStringArrayList("groupMemberIds");
            this.b = stringArrayList;
            if (stringArrayList == null) {
                this.b = Collections.emptyList();
            }
        }
        return this.b;
    }

    public Uri h() {
        String string = this.f8639a.getString("iconUri");
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public String i() {
        return this.f8639a.getString("id");
    }

    public String j() {
        return this.f8639a.getString("name");
    }

    public int k() {
        return this.f8639a.getInt("playbackStream", -1);
    }

    public int l() {
        return this.f8639a.getInt("playbackType", 1);
    }

    public int m() {
        return this.f8639a.getInt("presentationDisplayId", -1);
    }

    public int n() {
        return this.f8639a.getInt("volume");
    }

    public int o() {
        return this.f8639a.getInt("volumeHandling", 0);
    }

    public int p() {
        return this.f8639a.getInt("volumeMax");
    }

    public boolean q() {
        return this.f8639a.getBoolean("enabled", true);
    }

    public boolean r() {
        a();
        return !TextUtils.isEmpty(i()) && !TextUtils.isEmpty(j()) && !this.c.contains(null);
    }

    public String toString() {
        StringBuilder j = AbstractC2531fV.j("MediaRouteDescriptor{ ", "id=");
        j.append(i());
        j.append(", groupMemberIds=");
        j.append(g());
        j.append(", name=");
        j.append(j());
        j.append(", description=");
        j.append(d());
        j.append(", iconUri=");
        j.append(h());
        j.append(", isEnabled=");
        j.append(q());
        j.append(", connectionState=");
        j.append(c());
        j.append(", controlFilters=");
        a();
        j.append(Arrays.toString(this.c.toArray()));
        j.append(", playbackType=");
        j.append(l());
        j.append(", playbackStream=");
        j.append(k());
        j.append(", deviceType=");
        j.append(e());
        j.append(", volume=");
        j.append(n());
        j.append(", volumeMax=");
        j.append(p());
        j.append(", volumeHandling=");
        j.append(o());
        j.append(", presentationDisplayId=");
        j.append(m());
        j.append(", extras=");
        j.append(f());
        j.append(", isValid=");
        j.append(r());
        j.append(", minClientVersion=");
        j.append(this.f8639a.getInt("minClientVersion", 1));
        j.append(", maxClientVersion=");
        j.append(this.f8639a.getInt("maxClientVersion", Integer.MAX_VALUE));
        j.append(" }");
        return j.toString();
    }
}
