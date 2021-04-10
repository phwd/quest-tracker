package X;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: X.1gF  reason: invalid class name */
public final class AnonymousClass1gF {
    public static final Paint A00;
    public static final Paint A01 = new Paint(7);
    public static final Lock A02;
    public static final Paint A03 = new Paint(6);
    public static final Set<String> A04;

    static {
        Lock r0;
        String[] strArr = new String[29];
        System.arraycopy(new String[]{"XT1085", "XT1092", "XT1093", "XT1094", "XT1095", "XT1096", "XT1097", "XT1098", "XT1031", "XT1028", "XT937C", "XT1032", "XT1008", "XT1033", "XT1035", "XT1034", "XT939G", "XT1039", "XT1040", "XT1042", "XT1045", "XT1063", "XT1064", "XT1068", "XT1069", "XT1072", "XT1077"}, 0, strArr, 0, 27);
        System.arraycopy(new String[]{"XT1078", "XT1079"}, 0, strArr, 27, 2);
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        A04 = hashSet;
        if (hashSet.contains(Build.MODEL)) {
            r0 = new ReentrantLock();
        } else {
            r0 = new locks.LockC09201gv();
        }
        A02 = r0;
        Paint paint = new Paint(7);
        A00 = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @NonNull
    public static Bitmap.Config A00(@NonNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 26) {
            Bitmap.Config config = Bitmap.Config.RGBA_F16;
            if (config.equals(bitmap.getConfig())) {
                return config;
            }
        }
        return Bitmap.Config.ARGB_8888;
    }

    public static void A03(@NonNull Bitmap bitmap, @NonNull Bitmap bitmap2, Matrix matrix) {
        Lock lock = A02;
        lock.lock();
        try {
            Canvas canvas = new Canvas(bitmap2);
            canvas.drawBitmap(bitmap, matrix, A03);
            canvas.setBitmap(null);
        } finally {
            lock.unlock();
        }
    }

    public static Bitmap A01(@NonNull AbstractC07941di r3, @NonNull Bitmap bitmap) {
        Bitmap.Config A002 = A00(bitmap);
        if (A002.equals(bitmap.getConfig())) {
            return bitmap;
        }
        Bitmap A3J = r3.A3J(bitmap.getWidth(), bitmap.getHeight(), A002);
        new Canvas(A3J).drawBitmap(bitmap, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (Paint) null);
        return A3J;
    }

    public static Bitmap A02(@NonNull AbstractC07941di r6, @NonNull Bitmap bitmap, int i, int i2) {
        Bitmap.Config config;
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        float min = Math.min(((float) i) / ((float) bitmap.getWidth()), ((float) i2) / ((float) bitmap.getHeight()));
        int round = Math.round(((float) bitmap.getWidth()) * min);
        int round2 = Math.round(((float) bitmap.getHeight()) * min);
        if (bitmap.getWidth() == round && bitmap.getHeight() == round2) {
            return bitmap;
        }
        int width = (int) (((float) bitmap.getWidth()) * min);
        int height = (int) (((float) bitmap.getHeight()) * min);
        if (bitmap.getConfig() != null) {
            config = bitmap.getConfig();
        } else {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap A3J = r6.A3J(width, height, config);
        A3J.setHasAlpha(bitmap.hasAlpha());
        if (Log.isLoggable("TransformationUtils", 2)) {
            bitmap.getWidth();
            bitmap.getHeight();
            A3J.getWidth();
            A3J.getHeight();
        }
        Matrix matrix = new Matrix();
        matrix.setScale(min, min);
        A03(bitmap, A3J, matrix);
        return A3J;
    }
}
