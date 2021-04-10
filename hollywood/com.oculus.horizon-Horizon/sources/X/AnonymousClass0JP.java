package X;

import android.annotation.SuppressLint;
import com.facebook.acra.ErrorReporter;
import com.facebook.common.util.TriState;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;
import javax.inject.Provider;

@SuppressLint({"NonLiteralLogTagArgument"})
@ThreadSafe
/* renamed from: X.0JP  reason: invalid class name */
public final class AnonymousClass0JP extends AnonymousClass0px {
    public static final Provider<ErrorReporter> A0B = new C06800pu();
    public Boolean A00 = null;
    public final AnonymousClass0LE A01;
    public final Map<String, ArrayList<Long>> A02;
    public final Random A03;
    public final ExecutorService A04;
    public final Provider<ErrorReporter> A05;
    public final Provider<Boolean> A06;
    public final Provider<TriState> A07;
    @Nullable
    public volatile AnonymousClass0K0 A08;
    @Nullable
    public volatile AbstractC01130Kn A09;
    @Nullable
    public volatile AnonymousClass0Jo A0A;

    static {
        AbstractC04480hn.A00();
    }

    public AnonymousClass0JP(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, AnonymousClass0LE r6, Random random) {
        Provider<ErrorReporter> provider3 = A0B;
        this.A07 = provider;
        this.A06 = provider2;
        this.A04 = executorService;
        this.A01 = r6;
        this.A03 = random;
        this.A05 = provider3;
        this.A02 = new HashMap();
    }

    @Override // X.AbstractC01060Jr
    public final void A7i(String str) {
        this.A05.get().setUserId(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r20.A06.get().booleanValue() != false) goto L_0x002d;
     */
    @Override // X.AbstractC01060Jr
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A9A(X.AnonymousClass0Jy r21) {
        /*
        // Method dump skipped, instructions count: 449
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0JP.A9A(X.0Jy):void");
    }
}
