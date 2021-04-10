package X;

import android.media.ExifInterface;
import androidx.annotation.RequiresApi;
import com.facebook.soloader.DoNotOptimize;
import java.io.IOException;
import java.io.InputStream;

@DoNotOptimize
/* renamed from: X.1t7  reason: invalid class name */
public class AnonymousClass1t7 {
    @RequiresApi(api = 24)
    public static int A00(InputStream inputStream) {
        try {
            return new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        } catch (IOException unused) {
            return 0;
        }
    }
}
