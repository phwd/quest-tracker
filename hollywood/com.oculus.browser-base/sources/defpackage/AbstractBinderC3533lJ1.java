package defpackage;

import android.location.Location;
import android.os.Parcel;
import java.util.Objects;

/* renamed from: lJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC3533lJ1 extends AbstractBinderC3518lE1 implements AbstractC2679gJ1 {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f10338a = 0;

    public AbstractBinderC3533lJ1() {
        super("com.google.android.gms.location.ILocationListener");
    }

    @Override // defpackage.AbstractBinderC3518lE1
    public final boolean c(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        Location location = (Location) PE1.a(parcel, Location.CREATOR);
        BinderC2493fE1 fe1 = (BinderC2493fE1) this;
        synchronized (fe1) {
            C5718y90 y90 = fe1.b;
            C2835hE1 he1 = new C2835hE1(location);
            Objects.requireNonNull(y90);
            SE0.i(he1, "Notifier must not be null");
            y90.f11667a.sendMessage(y90.f11667a.obtainMessage(1, he1));
        }
        return true;
    }
}
