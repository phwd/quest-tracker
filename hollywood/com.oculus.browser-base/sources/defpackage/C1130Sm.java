package defpackage;

import android.os.Bundle;
import com.google.android.gms.cast.CastDevice;

/* renamed from: Sm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1130Sm {

    /* renamed from: a  reason: collision with root package name */
    public CastDevice f8915a;
    public AbstractC1252Um b;
    public Bundle c;

    public C1130Sm(CastDevice castDevice, AbstractC1252Um um) {
        SE0.i(castDevice, "CastDevice parameter cannot be null");
        SE0.i(um, "CastListener parameter cannot be null");
        this.f8915a = castDevice;
        this.b = um;
    }
}
