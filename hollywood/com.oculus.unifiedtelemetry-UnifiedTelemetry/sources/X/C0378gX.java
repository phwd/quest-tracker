package X;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import com.facebook.annotations.DoNotOptimize;

@TargetApi(16)
@DoNotOptimize
/* renamed from: X.gX  reason: case insensitive filesystem */
public class C0378gX {
    public static ClipData A00(Intent intent) {
        return intent.getClipData();
    }
}
