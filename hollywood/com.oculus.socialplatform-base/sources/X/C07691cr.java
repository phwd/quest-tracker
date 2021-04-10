package X;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.oculus.appmanager.info.ApkUpdateInfoContract;

/* renamed from: X.1cr  reason: invalid class name and case insensitive filesystem */
public final class C07691cr {
    public final int A00;
    public final int A01;
    public final int A02;
    public final Context A03;

    public C07691cr(C07701cs r7) {
        this.A03 = r7.A03;
        this.A00 = r7.A01.isLowRamDevice() ? ApkUpdateInfoContract.UPDATE_TYPE_PATCH : 4194304;
        ActivityManager activityManager = r7.A01;
        float f = 0.4f;
        int round = Math.round(((float) ((activityManager.getMemoryClass() << 10) << 10)) * (activityManager.isLowRamDevice() ? 0.33f : f));
        DisplayMetrics displayMetrics = r7.A02.A00;
        float f2 = (float) ((displayMetrics.widthPixels * displayMetrics.heightPixels) << 2);
        float f3 = r7.A00;
        int round2 = Math.round(f3 * f2);
        int round3 = Math.round(f2 * 2.0f);
        int i = round - this.A00;
        if (round3 + round2 <= i) {
            this.A02 = round3;
            this.A01 = round2;
        } else {
            float f4 = ((float) i) / (f3 + 2.0f);
            round3 = Math.round(2.0f * f4);
            this.A02 = round3;
            this.A01 = Math.round(f4 * f3);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            Formatter.formatFileSize(this.A03, (long) round3);
            Formatter.formatFileSize(this.A03, (long) this.A01);
            Formatter.formatFileSize(this.A03, (long) this.A00);
            Formatter.formatFileSize(this.A03, (long) round);
            r7.A01.getMemoryClass();
            r7.A01.isLowRamDevice();
        }
    }
}
