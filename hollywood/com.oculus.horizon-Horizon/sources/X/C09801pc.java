package X;

import com.facebook.common.memory.PooledByteBuffer;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1pc  reason: invalid class name and case insensitive filesystem */
public final class C09801pc {
    public static final CancellationException A0D = new CancellationException("Prefetching is not enabled");
    public AtomicLong A00 = new AtomicLong();
    public final C10331rw A01;
    public final AbstractC10301rt<AnonymousClass1kC, AnonymousClass1q1> A02;
    public final C10061qu A03;
    public final C09821pe A04;
    public final AnonymousClass1t0 A05;
    public final AnonymousClass1tF A06;
    public final AnonymousClass0KW<Boolean> A07;
    public final AnonymousClass0KW<Boolean> A08;
    public final AnonymousClass1oQ A09;
    public final AnonymousClass1oQ A0A;
    public final AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> A0B;
    public final AnonymousClass1uO A0C;

    /* JADX WARN: Incorrect args count in method signature: (LX/1pe;Ljava/util/Set<LX/1tF;>;Ljava/util/Set<LX/1t0;>;LX/0KW<Ljava/lang/Boolean;>;LX/1rt<LX/1kC;LX/1q1;>;LX/1rt<LX/1kC;Lcom/facebook/common/memory/PooledByteBuffer;>;LX/1oQ;LX/1oQ;Lcom/facebook/imagepipeline/cache/CacheKeyFactory;LX/1uO;LX/0KW<Ljava/lang/Boolean;>;LX/0KW<Ljava/lang/Boolean;>;Lcom/facebook/callercontext/CallerContextVerifier;LX/1qu;)V */
    public C09801pc(C09821pe r2, Set set, Set set2, AnonymousClass0KW r5, AbstractC10301rt r6, AbstractC10301rt r7, AnonymousClass1oQ r8, AnonymousClass1oQ r9, C10331rw r10, AnonymousClass1uO r11, AnonymousClass0KW r12, C10061qu r13) {
        this.A04 = r2;
        this.A06 = new C10101qy(set);
        this.A05 = new C10091qx(set2);
        this.A07 = r5;
        this.A02 = r6;
        this.A0B = r7;
        this.A09 = r8;
        this.A0A = r9;
        this.A01 = r10;
        this.A0C = r11;
        this.A08 = r12;
        this.A03 = r13;
    }
}
