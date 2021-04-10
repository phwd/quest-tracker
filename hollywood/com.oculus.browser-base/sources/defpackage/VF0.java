package defpackage;

import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.printing.Printable;

/* renamed from: VF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VF0 implements UF0, OF0 {

    /* renamed from: a  reason: collision with root package name */
    public static UF0 f9072a;
    public String b;
    public int c;
    public int d;
    public ParcelFileDescriptor e;
    public int f;
    public PrintAttributes.MediaSize g;
    public int[] h;
    public PF0 i;
    public MF0 j;
    public Printable k;
    public RF0 l;
    public int m = 0;
    public boolean n;
    public SF0 o;

    public VF0() {
        RF0 rf0 = new RF0();
        this.l = rf0;
        rf0.f8822a = this;
    }

    public static UF0 b() {
        Object obj = ThreadUtils.f10596a;
        if (f9072a == null) {
            f9072a = new VF0();
        }
        return f9072a;
    }

    public final void a() {
        ParcelFileDescriptor parcelFileDescriptor = this.e;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.e = null;
                throw th;
            }
            this.e = null;
        }
    }

    public final void c() {
        this.i = null;
        this.j = null;
    }

    public void d(Printable printable, SF0 sf0, int i2, int i3) {
        if (!this.n) {
            this.k = printable;
            this.b = printable.a();
            this.o = sf0;
            this.c = i2;
            this.d = i3;
        }
    }

    public void e() {
        if (!this.n && this.o != null && this.k.c()) {
            this.n = true;
            RF0 rf0 = this.l;
            SF0 sf0 = this.o;
            String title = this.k.getTitle();
            Objects.requireNonNull(rf0);
            sf0.f8886a.print(title, rf0, null);
            this.o = null;
        }
    }
}
