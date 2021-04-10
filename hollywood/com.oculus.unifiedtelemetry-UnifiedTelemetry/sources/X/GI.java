package X;

import android.content.Context;
import com.facebook.analytics2.logger.HandlerThreadFactory;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class GI {
    public final int A00;
    public final int A01;
    public final long A02;
    public final Context A03;
    public final Fg A04;
    public final Fx A05;
    public final G6 A06;
    public final C0256Ye A07;
    public final C0255Yd A08;
    public final MG A09;
    public final Class<? extends HandlerThreadFactory> A0A;
    public final String A0B;

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;ILjava/lang/String;Lcom/facebook/analytics2/logger/MaxEventsPerBatchProvider;LX/Fx;LX/MG;LX/G6;LX/Fg;Ljava/lang/Class<+Lcom/facebook/analytics2/logger/HandlerThreadFactory;>;Lcom/facebook/analytics2/logger/UploadSchedulerParamsProvider;ZJI)V */
    public GI(Context context, int i, String str, C0256Ye ye, Fx fx, MG mg, G6 g6, Fg fg, Class cls, C0255Yd yd, long j, int i2) {
        this.A03 = context;
        this.A07 = ye;
        this.A05 = fx;
        this.A09 = mg;
        this.A04 = fg;
        this.A0A = cls;
        this.A06 = g6;
        this.A00 = i;
        this.A0B = str;
        this.A08 = yd;
        this.A02 = j;
        this.A01 = i2;
    }
}
