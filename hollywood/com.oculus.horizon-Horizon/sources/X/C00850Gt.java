package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.analytics2.logger.HighPriUploadRetryReceiver;

/* renamed from: X.0Gt  reason: invalid class name and case insensitive filesystem */
public class C00850Gt extends Thread {
    public static final String __redex_internal_original_name = "com.facebook.analytics2.logger.HighPriUploadRetryReceiver$1";
    public final /* synthetic */ BroadcastReceiver.PendingResult A00;
    public final /* synthetic */ Context A01;
    public final /* synthetic */ Intent A02;
    public final /* synthetic */ HighPriUploadRetryReceiver A03;

    public C00850Gt(HighPriUploadRetryReceiver highPriUploadRetryReceiver, Intent intent, Context context, BroadcastReceiver.PendingResult pendingResult) {
        this.A03 = highPriUploadRetryReceiver;
        this.A02 = intent;
        this.A01 = context;
        this.A00 = pendingResult;
    }

    public final void run() {
        try {
            Bundle extras = this.A02.getExtras();
            Context context = this.A01;
            C00970Hv A002 = C00970Hv.A00(extras, context);
            C00990Hx.A01(C00990Hx.A00(context), "com.facebook.analytics2.logger.UPLOAD_NOW", new C00970Hv(null, null, "com.facebook.analytics2.logger.UPLOAD_NOW", A002.A06, A002.A02, new AnonymousClass0Hr(0, 0, "com.facebook.analytics2.logger.UPLOAD_NOW"), context), null);
            this.A00.finish();
        } catch (C00860Gv e) {
            AnonymousClass0NO.A0D("HighPriUploadRetryReceiver", "Exception when scheduling high pri upload via alarm", e);
        }
    }
}
