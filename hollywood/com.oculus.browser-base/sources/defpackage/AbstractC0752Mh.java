package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Pair;
import com.oculus.os.Version;
import java.io.FileDescriptor;
import java.io.IOException;

/* renamed from: Mh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0752Mh {
    public static Pair a(FileDescriptor fileDescriptor, int i, boolean z) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        if (i3 > i && i4 > i) {
            i2 = Math.min(i3, i4) / i;
        }
        options.inSampleSize = i2;
        options.inJustDecodeBounds = false;
        Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        if (decodeFileDescriptor == null) {
            return null;
        }
        return new Pair(e(decodeFileDescriptor, i, z, fileDescriptor), Float.valueOf(((float) decodeFileDescriptor.getHeight()) / ((float) decodeFileDescriptor.getWidth())));
    }

    public static Matrix b(FileDescriptor fileDescriptor) {
        Matrix matrix = new Matrix();
        try {
            switch (new ExifInterface(fileDescriptor).getAttributeInt("Orientation", 0)) {
                case 0:
                    c(8);
                    break;
                case 1:
                    c(0);
                    break;
                case 2:
                    matrix.setScale(-1.0f, 1.0f);
                    c(6);
                    break;
                case 3:
                    matrix.postRotate(180.0f);
                    c(2);
                    break;
                case 4:
                    matrix.setScale(1.0f, -1.0f);
                    c(7);
                    break;
                case 5:
                    matrix.setRotate(90.0f);
                    matrix.postScale(-1.0f, 1.0f);
                    c(4);
                    break;
                case 6:
                    matrix.postRotate(90.0f);
                    c(1);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    matrix.setRotate(-90.0f);
                    matrix.postScale(-1.0f, 1.0f);
                    c(5);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    matrix.postRotate(-90.0f);
                    c(3);
                    break;
            }
        } catch (IOException unused) {
        }
        return matrix;
    }

    public static void c(int i) {
        AbstractC3364kK0.g("Android.PhotoPicker.ExifOrientation", i, 9);
    }

    public static Bitmap d(Bitmap bitmap, float f, boolean z) {
        float min = Math.min(f / ((float) bitmap.getWidth()), f / ((float) bitmap.getHeight()));
        return Bitmap.createScaledBitmap(bitmap, Math.round(min * ((float) bitmap.getWidth())), Math.round(((float) bitmap.getHeight()) * min), z);
    }

    public static Bitmap e(Bitmap bitmap, int i, boolean z, FileDescriptor fileDescriptor) {
        if (!z) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (!(width == i && height == i)) {
                if (width <= i || height <= i) {
                    if (width < i) {
                        height = (int) (((float) height) * (((float) i) / ((float) width)));
                        width = i;
                    }
                    if (height < i) {
                        width = (int) (((float) width) * (((float) i) / ((float) height)));
                        height = i;
                    }
                    bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                } else {
                    float f = (width < height ? (float) width : (float) height) / ((float) i);
                    bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(((float) width) / f), Math.round(((float) height) / f), true);
                }
            }
            int width2 = bitmap.getWidth();
            int height2 = bitmap.getHeight();
            if (width2 == i && height2 == i) {
                return bitmap;
            }
            return Bitmap.createBitmap(bitmap, width2 > i ? (width2 - i) / 2 : 0, height2 > i ? (height2 - i) / 2 : 0, i, i, b(fileDescriptor), true);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), b(fileDescriptor), true);
        return Bitmap.createScaledBitmap(createBitmap, i, (int) (((float) createBitmap.getHeight()) * (((float) i) / ((float) createBitmap.getWidth()))), true);
    }
}
