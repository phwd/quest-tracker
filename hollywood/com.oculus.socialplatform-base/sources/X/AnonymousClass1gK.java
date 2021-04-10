package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import retrofit.client.Defaults;

/* renamed from: X.1gK  reason: invalid class name */
public final class AnonymousClass1gK {
    public static final File A05 = new File("/proc/self/fd");
    public static volatile AnonymousClass1gK A06;
    @GuardedBy("this")
    public int A00;
    @GuardedBy("this")
    public boolean A01 = true;
    public final int A02;
    public final int A03;
    public final boolean A04;

    @TargetApi(26)
    public final boolean A00(int i, int i2, BitmapFactory.Options options, boolean z, boolean z2) {
        int i3;
        boolean z3;
        boolean z4 = false;
        if (z && this.A04 && Build.VERSION.SDK_INT >= 26 && !z2 && i >= (i3 = this.A03) && i2 >= i3) {
            synchronized (this) {
                int i4 = this.A00 + 1;
                this.A00 = i4;
                if (i4 >= 50) {
                    boolean z5 = false;
                    this.A00 = 0;
                    int length = A05.list().length;
                    int i5 = this.A02;
                    if (length < i5) {
                        z5 = true;
                    }
                    this.A01 = z5;
                    if (!z5 && Log.isLoggable("Downsampler", 5)) {
                        Log.w("Downsampler", AnonymousClass006.A05("Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors ", length, ", limit ", i5));
                    }
                }
                z3 = this.A01;
            }
            if (z3) {
                z4 = true;
            }
        }
        if (z4) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return z4;
    }

    @VisibleForTesting
    public AnonymousClass1gK() {
        String str;
        String str2 = Build.MODEL;
        boolean z = true;
        if (str2 != null && str2.length() >= 7) {
            String substring = str2.substring(0, 7);
            switch (substring.hashCode()) {
                case -1398613787:
                    str = "SM-A520";
                    if (substring.equals(str) && Build.VERSION.SDK_INT == 26) {
                        z = false;
                        break;
                    }
                case -1398431166:
                    str = "SM-G930";
                    z = false;
                    break;
                case -1398431161:
                    str = "SM-G935";
                    z = false;
                    break;
                case -1398431073:
                    str = "SM-G960";
                    z = false;
                    break;
                case -1398431068:
                    str = "SM-G965";
                    z = false;
                    break;
                case -1398343746:
                    str = "SM-J720";
                    z = false;
                    break;
                case -1398222624:
                    str = "SM-N935";
                    z = false;
                    break;
            }
        }
        this.A04 = z;
        if (Build.VERSION.SDK_INT >= 28) {
            this.A02 = Defaults.READ_TIMEOUT_MILLIS;
            return;
        }
        this.A02 = 700;
        this.A03 = 128;
    }
}
