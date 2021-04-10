package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.analytics2.logger.HighPriUploadRetryReceiver;

/* renamed from: X.7B  reason: invalid class name */
public final class AnonymousClass7B extends Thread {
    public static final String __redex_internal_original_name = "com.facebook.analytics2.logger.HighPriUploadRetryReceiver$1";
    public final /* synthetic */ BroadcastReceiver.PendingResult A00;
    public final /* synthetic */ Context A01;
    public final /* synthetic */ Intent A02;
    public final /* synthetic */ HighPriUploadRetryReceiver A03;

    public AnonymousClass7B(HighPriUploadRetryReceiver highPriUploadRetryReceiver, Intent intent, Context context, BroadcastReceiver.PendingResult pendingResult) {
        this.A03 = highPriUploadRetryReceiver;
        this.A02 = intent;
        this.A01 = context;
        this.A00 = pendingResult;
    }

    public final void run() {
        try {
            Bundle extras = this.A02.getExtras();
            Context context = this.A01;
            AnonymousClass81 A002 = AnonymousClass81.A00(extras, context);
            AnonymousClass83.A01(AnonymousClass83.A00(context), "com.facebook.analytics2.logger.UPLOAD_NOW", new AnonymousClass81(null, null, "com.facebook.analytics2.logger.UPLOAD_NOW", A002.A06, A002.A02, new AnonymousClass7y(0, 0, "com.facebook.analytics2.logger.UPLOAD_NOW"), context), null);
            this.A00.finish();
        } catch (AnonymousClass7D e) {
            C0139Dd.A0N("HighPriUploadRetryReceiver", "Exception when scheduling high pri upload via alarm", e);
        }
    }
}
