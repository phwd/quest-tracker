package com.facebook.mobileconfig.metadata;

import android.content.Context;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.iolite.Closeables;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class LocatorMapFactory {
    public static final String LOCATOR_MAP_PATH = "qe_lookup.bin";

    public static ByteBuffer newLocatorMapBufferFromContext(Context context) {
        ByteBuffer byteBuffer;
        try {
            InputStream open = context.getAssets().open(LOCATOR_MAP_PATH);
            long j = (long) MobileConfigAssetMeta.LOCATOR_MAP_SIZE;
            if (j > 0) {
                byteBuffer = ByteBuffer.wrap(ByteStreams.toByteArray(open, (int) j));
            } else {
                byteBuffer = ByteBuffer.wrap(ByteStreams.toByteArray(open));
            }
            Closeables.closeQuietly(open);
            return byteBuffer;
        } catch (FileNotFoundException unused) {
            ByteBuffer newLocatorMapBufferFromClassPath = newLocatorMapBufferFromClassPath();
            Closeables.closeQuietly(null);
            return newLocatorMapBufferFromClassPath;
        } catch (Exception e) {
            throw new RuntimeException("IOException encountered while reading asset", e);
        } catch (Throwable th) {
            Closeables.closeQuietly(null);
            throw th;
        }
    }

    public static ByteBuffer newLocatorMapBufferFromClassPath() {
        InputStream inputStream = null;
        try {
            inputStream = LocatorMapFactory.class.getResourceAsStream("/assets/qe_lookup.bin");
            ByteBuffer wrap = ByteBuffer.wrap(ByteStreams.toByteArray(inputStream));
            Closeables.closeQuietly(inputStream);
            return wrap;
        } catch (Exception e) {
            throw new RuntimeException("IOException encountered while reading resource", e);
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            throw th;
        }
    }
}
