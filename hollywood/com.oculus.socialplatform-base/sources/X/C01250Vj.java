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
/* renamed from: X.0Vj  reason: invalid class name and case insensitive filesystem */
public final class C01250Vj extends AbstractC05060sK {
    public static final Provider<ErrorReporter> A0B = new AnonymousClass0sJ();
    public Boolean A00 = null;
    public final AnonymousClass0K8 A01;
    public final Map<String, ArrayList<Long>> A02;
    public final Random A03;
    public final ExecutorService A04;
    public final Provider<ErrorReporter> A05;
    public final Provider<Boolean> A06;
    public final Provider<TriState> A07;
    @Nullable
    public volatile AnonymousClass0I8 A08;
    @Nullable
    public volatile AnonymousClass0Ja A09;
    @Nullable
    public volatile AbstractC00630Hv A0A;

    static {
        AbstractC03380mI.A00();
    }

    public C01250Vj(Provider<TriState> provider, Provider<Boolean> provider2, ExecutorService executorService, AnonymousClass0K8 r6, Random random) {
        Provider<ErrorReporter> provider3 = A0B;
        this.A07 = provider;
        this.A06 = provider2;
        this.A04 = executorService;
        this.A01 = r6;
        this.A03 = random;
        this.A05 = provider3;
        this.A02 = new HashMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r19.A06.get().booleanValue() != false) goto L_0x002d;
     */
    @Override // X.AbstractC00650Hy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAT(X.AnonymousClass0I6 r20) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01250Vj.AAT(X.0I6):void");
    }

    @Override // X.AbstractC00650Hy
    public final void putCurrentUserId(String str) {
        this.A05.get().setUserId(str);
    }
}
