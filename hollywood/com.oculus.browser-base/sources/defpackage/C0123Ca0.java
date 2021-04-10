package defpackage;

import android.util.Property;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;

/* renamed from: Ca0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0123Ca0 extends Property {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocationBarTablet f7821a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0123Ca0(LocationBarTablet locationBarTablet, Class cls, String str) {
        super(cls, str);
        this.f7821a = locationBarTablet;
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return Float.valueOf(((LocationBarTablet) obj).b0);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        LocationBarTablet locationBarTablet = (LocationBarTablet) obj;
        LocationBarTablet locationBarTablet2 = this.f7821a;
        float floatValue = ((Float) obj2).floatValue();
        locationBarTablet2.b0 = floatValue;
        locationBarTablet2.L.e().d(floatValue);
    }
}
