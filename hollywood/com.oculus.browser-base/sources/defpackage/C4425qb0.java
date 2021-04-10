package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.base.SysUtils;

/* renamed from: qb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4425qb0 extends AbstractC5079uP {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f11149a;

    public C4425qb0() {
        HashMap hashMap = new HashMap(1);
        this.f11149a = hashMap;
        hashMap.put("lowmem", Boolean.toString(SysUtils.isLowEndDevice()));
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        return this.f11149a;
    }
}
