package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.Mu;
import X.QC;
import android.content.Context;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class RtcClock {
    public static final long SYSTEM_UID = 1000;
    public static final String TAG = "RtcClock";
    public static final long ZERO_EPOCH = 0;
    public QC _UL_mInjectionContext;

    public final long A00() {
        if (((long) ((Context) AbstractC0096Hu.A03(0, 80, this._UL_mInjectionContext)).getApplicationInfo().uid) != 1000) {
            return 0;
        }
        File file = new File("/sys/class/rtc/rtc0/since_epoch");
        if (!file.exists()) {
            return 0;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                long nextLong = new Scanner(fileInputStream).nextLong();
                fileInputStream.close();
                return nextLong;
            } catch (Throwable unused) {
            }
        } catch (IOException e) {
            Mu.A02(TAG, "Failed to read rtc time", e);
            return 0;
        }
        throw th;
    }

    @Inject
    public RtcClock(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(1, xu);
    }
}
