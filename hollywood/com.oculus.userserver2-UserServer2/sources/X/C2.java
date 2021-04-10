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
public final class C2 extends AbstractC0140Sw {
    public static final AbstractC0192Xx<ErrorReporter> A0B = new C0139Sv();
    public Boolean A00 = null;
    public final Kt A01;
    public final Map<String, ArrayList<Long>> A02;
    public final Random A03;
    public final ExecutorService A04;
    public final AbstractC0192Xx<ErrorReporter> A05;
    public final AbstractC0192Xx<Boolean> A06;
    public final AbstractC0192Xx<TriState> A07;
    @Nullable
    public volatile JR A08;
    @Nullable
    public volatile KV A09;
    @Nullable
    public volatile JE A0A;

    static {
        PB.A00();
    }

    public C2(AbstractC0192Xx<TriState> xx, AbstractC0192Xx<Boolean> xx2, ExecutorService executorService, Kt kt, Random random) {
        AbstractC0192Xx<ErrorReporter> xx3 = A0B;
        this.A07 = xx;
        this.A06 = xx2;
        this.A04 = executorService;
        this.A01 = kt;
        this.A03 = random;
        this.A05 = xx3;
        this.A02 = new HashMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r19.A06.get().booleanValue() != false) goto L_0x002d;
     */
    @Override // X.JH
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A3m(X.JP r20) {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C2.A3m(X.JP):void");
    }
}
