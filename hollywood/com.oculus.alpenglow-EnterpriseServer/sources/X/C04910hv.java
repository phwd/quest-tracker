package X;

import android.annotation.SuppressLint;
import com.facebook.annotations.OkToExtend;
import com.facebook.secure.context.IntentLaunchingPlugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@OkToExtend
@SuppressLint({"InstanceMethodCanBeStatic"})
/* renamed from: X.0hv  reason: invalid class name and case insensitive filesystem */
public final class C04910hv {
    @Nullable
    public static C04910hv A0B;
    public static final C02850av A0C = new C02850av();
    public static final AnonymousClass0i0 A0D = new AnonymousClass0i0();
    public static final C04960iA A0E = new C04960iA();
    @Nullable
    public C01710Lb A00 = null;
    @Nullable
    public AnonymousClass0GO A01 = null;
    @Nullable
    public AnonymousClass0LX A02 = null;
    @Nullable
    public AnonymousClass0GN A03 = null;
    @Nullable
    public C01710Lb A04 = null;
    @Nullable
    public C01710Lb A05 = null;
    public Map<C05280ix, AnonymousClass0GM> A06 = new HashMap();
    public Map<C05280ix, AnonymousClass0GM> A07 = new HashMap();
    public final List<IntentLaunchingPlugin> A08 = AbstractC04860hq.A00;
    public final Map<C05280ix, C01710Lb> A09 = new HashMap();
    public final Map<C05280ix, C01710Lb> A0A = new HashMap();

    public static synchronized AnonymousClass0GM A01(C04910hv r5, C05280ix r6) {
        AnonymousClass0GM r0;
        synchronized (r5) {
            if (!r5.A06.containsKey(r6)) {
                r5.A06.put(r6, new AnonymousClass0GM(r6, A0D, A0C, A0E));
            }
            r0 = r5.A06.get(r6);
        }
        return r0;
    }

    public final synchronized C01710Lb A02() {
        C01710Lb r1;
        r1 = this.A04;
        if (r1 == null) {
            AnonymousClass0LX r3 = this.A02;
            if (r3 == null) {
                r3 = new AnonymousClass0LX(A0D, A0C, A0E);
                this.A02 = r3;
            }
            r1 = new C01710Lb(r3, this.A08);
            this.A04 = r1;
        }
        return r1;
    }

    public final synchronized C01710Lb A03() {
        C01710Lb r1;
        r1 = this.A05;
        if (r1 == null) {
            AnonymousClass0GN r3 = this.A03;
            if (r3 == null) {
                r3 = new AnonymousClass0GN(A0D, A0C, A0E);
                this.A03 = r3;
            }
            r1 = new C01710Lb(r3, this.A08);
            this.A05 = r1;
        }
        return r1;
    }

    public static synchronized C04910hv A00() {
        C04910hv r0;
        synchronized (C04910hv.class) {
            r0 = A0B;
            if (r0 == null) {
                r0 = new C04910hv();
                A0B = r0;
            }
        }
        return r0;
    }
}
