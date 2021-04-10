package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;

/* renamed from: Da0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0184Da0 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocationBarTablet f7896a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0184Da0(LocationBarTablet locationBarTablet, Class cls, String str) {
        super(cls, str);
        this.f7896a = locationBarTablet;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((LocationBarTablet) obj).t0);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        LocationBarTablet locationBarTablet = (LocationBarTablet) obj;
        LocationBarTablet locationBarTablet2 = this.f7896a;
        float floatValue = ((Float) obj2).floatValue();
        int i = LocationBarTablet.g0;
        locationBarTablet2.B(floatValue);
    }
}
