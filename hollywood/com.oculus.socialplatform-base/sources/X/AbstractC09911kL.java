package X;

import android.content.Context;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1kL  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09911kL<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> {
    public static final AnonymousClass1l9<Object> A05 = new AnonymousClass1mH();
    public static final NullPointerException A06 = new NullPointerException("No image request was specified!");
    public static final AtomicLong A07 = new AtomicLong();
    @Nullable
    public AnonymousClass1l9<? super INFO> A00 = null;
    @Nullable
    public AnonymousClass1m0 A01 = null;
    @Nullable
    public Object A02 = null;
    @Nullable
    public REQUEST A03 = null;
    public final Context A04;

    /* JADX INFO: finally extract failed */
    public final AbstractC09891kF A00() {
        AnonymousClass1k9 r7;
        AbstractC00750Ik<AnonymousClass0M8<AbstractC00820Ju<AnonymousClass0VM>>> r5;
        C10101kp r2;
        C00740Ii.A05(true, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        C00740Ii.A05(true, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
        C01060Pq.A00();
        AnonymousClass1k8 r6 = (AnonymousClass1k8) this;
        C01060Pq.A00();
        try {
            AnonymousClass1m0 r72 = ((AbstractC09911kL) r6).A01;
            String valueOf = String.valueOf(A07.getAndIncrement());
            if (r72 instanceof AnonymousClass1k9) {
                r7 = (AnonymousClass1k9) r72;
            } else {
                C06231Ww r4 = r6.A00;
                r7 = new AnonymousClass1k9(r4.A00, r4.A02, r4.A04, r4.A03);
                AbstractC00750Ik<Boolean> r0 = r4.A01;
                if (r0 != null) {
                    r7.A05 = r0.get().booleanValue();
                }
            }
            REQUEST request = r6.A03;
            if (request != null) {
                r5 = new AnonymousClass1l7(r6, r7, valueOf, request, r6.A02, AnonymousClass007.A00);
            } else {
                r5 = new C03810ob(A06);
            }
            REQUEST request2 = request;
            if (r6.A01.A01 == null || request2 == null) {
                r2 = null;
            } else {
                r2 = new C10101kp(request2.A03.toString(), request2.A07, request2.A05, r6.A02);
            }
            Object obj = r6.A02;
            C01060Pq.A00();
            AbstractC09891kF.A07(r7, valueOf, obj);
            r7.A0A = false;
            r7.A01 = r5;
            AnonymousClass1k9.A01(r7, null);
            r7.A00 = r2;
            synchronized (r7) {
                r7.A03 = null;
            }
            AnonymousClass1k9.A01(r7, null);
            r7.A0D(null);
            C01060Pq.A00();
            synchronized (r7) {
                r7.A04 = r6.A03;
            }
            C01060Pq.A00();
            AnonymousClass1l9<? super INFO> r02 = this.A00;
            if (r02 != null) {
                r7.A0B(r02);
            }
            C01060Pq.A00();
            return r7;
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;Ljava/util/Set<LX/1l9;>;Ljava/util/Set<Lcom/facebook/fresco/ui/common/ControllerListener2;>;)V */
    public AbstractC09911kL(Context context) {
        this.A04 = context;
    }
}
