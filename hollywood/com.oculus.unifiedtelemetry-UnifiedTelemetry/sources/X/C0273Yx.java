package X;

import android.content.Context;
import android.os.HandlerThread;
import com.facebook.analytics2.logger.DefaultHandlerThreadFactory;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import com.oculus.unifiedtelemetry.R;
import com.squareup.okhttp.ConnectionPool;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.Yx  reason: case insensitive filesystem */
public final class C0273Yx implements Fc {
    @Nullable
    public G0 A00;
    @GuardedBy("this")
    public GS A01;
    @GuardedBy("this")
    public GS A02;
    public final int A03;
    public final long A04;
    public final Context A05;
    public final Fg A06;
    public final G5 A07;
    @Nullable
    public final AbstractC0274Yy A08;
    @Nullable
    public final AbstractC0274Yy A09;
    public final C0256Ye A0A;
    public final C0256Ye A0B;
    public final C0255Yd A0C;
    public final C0255Yd A0D;
    public final MG A0E;
    public final Class<? extends HandlerThreadFactory> A0F = DefaultHandlerThreadFactory.class;
    public final Class<? extends AbstractC0090Hb> A0G;
    public final Z4 A0H;

    @Override // X.Fc
    public final synchronized void A3Y(@Nullable C0260Yj yj) {
        String str;
        String str2;
        GS gs = this.A01;
        if (gs != null) {
            GR gr = gs.A04;
            if (yj != null) {
                str2 = yj.userId;
            } else {
                str2 = null;
            }
            gr.sendMessage(gr.obtainMessage(4, str2));
        }
        GS gs2 = this.A02;
        if (gs2 != null) {
            GR gr2 = gs2.A04;
            if (yj != null) {
                str = yj.userId;
            } else {
                str = null;
            }
            gr2.sendMessage(gr2.obtainMessage(4, str));
        }
    }

    @Override // X.Fc
    public final synchronized void A5O(G0 g0) {
        this.A00 = g0;
        GS gs = this.A01;
        if (gs != null) {
            GR gr = gs.A04;
            gr.sendMessage(gr.obtainMessage(3, g0));
        }
        GS gs2 = this.A02;
        if (gs2 != null) {
            GR gr2 = gs2.A04;
            gr2.sendMessage(gr2.obtainMessage(3, g0));
        }
    }

    /* JADX WARN: Failed to parse method signature: (Landroid/content/Context;Ljava/lang/Class<+LX/Hb;>;Lcom/facebook/analytics2/logger/EventListener;Lcom/facebook/analytics2/logger/EventListener;Ljava/lang/Class<+Lcom/facebook/flexiblesampling/SamplingPolicyConfig;>;Ljava/lang/Class<+Lcom/facebook/analytics2/logger/PrivacyPolicy;>;Ljava/lang/Class<+Lcom/facebook/analytics2/logger/HandlerThreadFactory;>;LX/G5;Lcom/facebook/analytics2/logger/PigeonHealthDataProvider;Lcom/facebook/analytics2/logger/PigeonHealthDataProvider;LX/H0;LX/MG;LX/Fg;Lcom/facebook/analytics2/logger/UploadSchedulerParamsProvider;Lcom/facebook/analytics2/logger/UploadSchedulerParamsProvider;Lcom/facebook/analytics2/logger/MaxEventsPerBatchProvider;Lcom/facebook/analytics2/logger/MaxEventsPerBatchProvider;Lcom/facebook/analytics2/logger/MicroBatchConfigProvider;Lcom/facebook/analytics2/logger/BeginWritingBlock;Ljava/lang/Class<+Lcom/facebook/analytics2/logger/UploadJobInstrumentation;>;ZJLjava/lang/Class<+Lcom/facebook/analytics2/logger/BatchPayloadIteratorFactory;>;ZILcom/facebook/analytics2/logger/EventBatchStoreProvider;)V */
    public C0273Yx(Context context, Class cls, @Nullable AbstractC0274Yy yy, @Nullable AbstractC0274Yy yy2, @Nullable G5 g5, @Nullable MG mg, Fg fg, C0255Yd yd, @Nullable C0255Yd yd2, @Nullable C0256Ye ye, C0256Ye ye2, Z4 z4) {
        this.A05 = context;
        this.A0G = cls;
        this.A09 = yy;
        this.A08 = yy2;
        this.A07 = g5;
        this.A0E = mg;
        this.A06 = fg;
        this.A0D = yd;
        this.A0C = yd2;
        this.A0B = ye;
        this.A0A = ye2;
        this.A0H = z4;
        this.A04 = ConnectionPool.DEFAULT_KEEP_ALIVE_DURATION_MS;
        this.A03 = 3;
    }

    @Override // X.Fc
    public final synchronized void A4L(String str, YE ye, long j) {
        GS gs;
        if (j == -2) {
            if (this.A01 == null) {
                Context context = this.A05;
                G7 A002 = G7.A00(context);
                Class<? extends HandlerThreadFactory> cls = this.A0F;
                HandlerThread A1f = A002.A03(cls.getName()).A1f("Analytics-HighPri-Proc", 0);
                Integer num = AnonymousClass07.A00;
                AbstractC0274Yy yy = this.A08;
                C0256Ye ye2 = this.A0A;
                GS gs2 = new GS(A1f, yy, new GI(context, R.id.jobscheduler_analytics2_high_pri, "high", ye2, new Fx(this.A07), this.A0E, new G6(this.A0G, cls, num, "ads"), this.A06, cls, this.A0C, this.A04, this.A03), ye2);
                this.A01 = gs2;
                G0 g0 = this.A00;
                GR gr = gs2.A04;
                gr.sendMessage(gr.obtainMessage(3, g0));
            }
            gs = this.A01;
        } else {
            if (this.A02 == null) {
                Context context2 = this.A05;
                G7 A003 = G7.A00(context2);
                Class<? extends HandlerThreadFactory> cls2 = this.A0F;
                HandlerThread A1f2 = A003.A03(cls2.getName()).A1f("Analytics-NormalPri-Proc", 10);
                AbstractC0274Yy yy2 = this.A09;
                C0256Ye ye3 = this.A0B;
                GS gs3 = new GS(A1f2, yy2, new GI(context2, R.id.jobscheduler_analytics2_normal_pri, "normal", ye3, new Fx(this.A07), this.A0E, new G6(this.A0G, cls2, AnonymousClass07.A00, "regular"), this.A06, cls2, this.A0D, this.A04, this.A03), ye3);
                this.A02 = gs3;
                G0 g02 = this.A00;
                GR gr2 = gs3.A04;
                gr2.sendMessage(gr2.obtainMessage(3, g02));
            }
            gs = this.A02;
        }
        GR gr3 = gs.A04;
        gr3.sendMessage(gr3.obtainMessage(1, 1, 0, ye));
    }
}
