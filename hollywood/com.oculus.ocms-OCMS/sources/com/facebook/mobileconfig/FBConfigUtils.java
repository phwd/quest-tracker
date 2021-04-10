package com.facebook.mobileconfig;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.stetho.common.Utf8Charset;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBConfigUtils {
    private static final String TAG = "FBConfigUtils";

    @Nullable
    public static String getSchemaHashInternal(@Nullable ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            BLog.w(TAG, "Invalid ByteBuffer passed. Forcing C++ manager creation.");
            return null;
        }
        Charset forName = Charset.forName(Utf8Charset.NAME);
        byte[] bArr = new byte[(byteBuffer.limit() - byteBuffer.position())];
        byteBuffer.get(bArr);
        if (isValidSchemaHash(bArr)) {
            return new String(bArr, 0, bArr.length, forName);
        }
        BLog.w(TAG, "Invalid schema hash in flatbuffer. Forcing C++ manager creation.");
        return null;
    }

    public static boolean isValidSchemaHash(byte[] bArr) {
        if (!(bArr.length == 32 || bArr.length == 65)) {
            return false;
        }
        for (byte b : bArr) {
            if ((b < 97 || b > 102) && ((b < 48 || b > 57) && b != 58)) {
                return false;
            }
        }
        return true;
    }
}
