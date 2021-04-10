package X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import com.facebook.annotations.DoNotOptimize;

@TargetApi(26)
@DoNotOptimize
/* renamed from: X.0ht  reason: invalid class name and case insensitive filesystem */
public class C04890ht {
    @SuppressLint({"BadMethodUse-android.content.Context.startForegroundService"})
    public static void A00(Context context, Intent intent) {
        context.startForegroundService(intent);
    }
}
