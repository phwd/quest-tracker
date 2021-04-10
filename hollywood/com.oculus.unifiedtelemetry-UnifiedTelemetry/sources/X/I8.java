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

@SuppressLint({"NonLiteralLogTagArgument"})
@ThreadSafe
public final class I8 extends YR {
    public static final eJ<ErrorReporter> A0B = new YQ();
    public Boolean A00 = null;
    public final AbstractC0106Kc A01;
    public final Map<String, ArrayList<Long>> A02;
    public final Random A03;
    public final ExecutorService A04;
    public final eJ<ErrorReporter> A05;
    public final eJ<Boolean> A06;
    public final eJ<TriState> A07;
    @Nullable
    public volatile J7 A08;
    @Nullable
    public volatile KF A09;
    @Nullable
    public volatile Iu A0A;

    static {
        AbstractC0459mt.A00();
    }

    public I8(eJ<TriState> eJVar, eJ<Boolean> eJVar2, ExecutorService executorService, AbstractC0106Kc kc, Random random) {
        eJ<ErrorReporter> eJVar3 = A0B;
        this.A07 = eJVar;
        this.A06 = eJVar2;
        this.A04 = executorService;
        this.A01 = kc;
        this.A03 = random;
        this.A05 = eJVar3;
        this.A02 = new HashMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r19.A06.get().booleanValue() != false) goto L_0x002d;
     */
    @Override // X.Ix
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A5K(X.J5 r20) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.I8.A5K(X.J5):void");
    }
}
