package X;

import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mF  reason: invalid class name */
public final class AnonymousClass0mF {
    @Nullable
    public static FileOutputStream A00;
    @Nullable
    public static FileChannel A01;

    static {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/sys/kernel/debug/tracing/trace_marker");
            A00 = fileOutputStream;
            A01 = fileOutputStream.getChannel();
        } catch (FileNotFoundException e) {
            Log.e("TraceDirect", "Failed to open trace_marker file.", e);
            A00 = null;
            A01 = null;
        }
    }

    public static void A00(String str) {
        Throwable e;
        String str2;
        String str3;
        int write;
        if (A01 != null) {
            try {
                byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
                int length = bytes.length;
                if (length >= 1) {
                    do {
                        write = A01.write(ByteBuffer.wrap(bytes));
                    } while (write == 0);
                    if (write != length) {
                        Log.e("TraceDirect", "Partial write of systrace line.");
                    }
                }
            } catch (UnsupportedEncodingException e2) {
                e = e2;
                str2 = "TraceDirect";
                str3 = "Failed to encode raw systrace line to UTF-8.";
                Log.e(str2, str3, e);
            } catch (IOException e3) {
                e = e3;
                str2 = "TraceDirect";
                str3 = "Failed to write systrace line.";
                Log.e(str2, str3, e);
            }
        }
    }
}
