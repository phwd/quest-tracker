package com.facebook.secure.fileprovider.common;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import com.facebook.annotations.DoNotOptimize;
import java.io.IOException;
import java.lang.reflect.Field;

public class FileStatHelperBase {
    public static StatInfo statOpenFile(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        throw new IOException("Not Implemented");
    }

    public static int getFDFromPFD(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 12) {
            return getFDFromPFDWithAPIAbove12(parcelFileDescriptor);
        }
        try {
            Field field = parcelFileDescriptor.getClass().getField("descriptor");
            field.setAccessible(true);
            return field.getInt(parcelFileDescriptor);
        } catch (NoSuchFieldException e) {
            throw new IOException(e);
        } catch (IllegalAccessException e2) {
            throw new IOException(e2);
        } catch (IllegalArgumentException e3) {
            throw new IOException(e3);
        }
    }

    @TargetApi(12)
    @DoNotOptimize
    private static int getFDFromPFDWithAPIAbove12(ParcelFileDescriptor parcelFileDescriptor) {
        return parcelFileDescriptor.getFd();
    }
}
