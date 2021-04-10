package com.oculus.vrshell.util;

import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.common.primitives.UnsignedBytes;
import com.oculus.common.logutilities.LoggingUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageConverterUtil {
    private static final String TAG = LoggingUtil.tag(ImageConverterUtil.class);

    @Nullable
    public static Bitmap convertImageToBitmap(int i, int i2, Image.Plane[] planeArr) {
        if (planeArr[0].getBuffer() == null) {
            Log.e(TAG, "No image data in buffer.");
            return null;
        }
        ByteBuffer buffer = planeArr[0].getBuffer();
        int[] iArr = new int[buffer.order(ByteOrder.BIG_ENDIAN).asIntBuffer().remaining()];
        int pixelStride = planeArr[0].getPixelStride();
        int rowStride = planeArr[0].getRowStride() - (pixelStride * i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2 * i; i4++) {
            iArr[i4] = ((buffer.get(i3) & UnsignedBytes.MAX_VALUE) << 16) | 0 | ((buffer.get(i3 + 1) & UnsignedBytes.MAX_VALUE) << 8) | (buffer.get(i3 + 2) & UnsignedBytes.MAX_VALUE) | ((buffer.get(i3 + 3) & UnsignedBytes.MAX_VALUE) << 24);
            i3 += pixelStride;
            if (i4 % i == 0 && i4 != 0) {
                i3 += rowStride;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setHasAlpha(false);
        return createBitmap;
    }
}
