package com.facebook.secure.fileprovider.common;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class FileStatHelper extends FileStatHelperBase {
    public static StatInfo statOpenFile(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            return Api21Utils.getStatInfo(parcelFileDescriptor);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            return Api14Utils.getStatInfo(parcelFileDescriptor);
        }
        throw new IOException("Below API 14 is not possible to get FD stat info");
    }

    @TargetApi(21)
    private static class Api21Utils {
        private Api21Utils() {
        }

        public static StatInfo getStatInfo(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            try {
                StructStat fstat = Os.fstat(parcelFileDescriptor.getFileDescriptor());
                return StatInfo.newInstance(fstat.st_uid, fstat.st_gid, fstat.st_ino, fstat.st_dev);
            } catch (ErrnoException e) {
                throw new IOException(e);
            }
        }
    }

    @TargetApi(14)
    private static class Api14Utils {
        private Api14Utils() {
        }

        public static StatInfo getStatInfo(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            try {
                Class<?> cls = Class.forName("libcore.io.Libcore");
                Class<?> cls2 = Class.forName("libcore.io.Os");
                Object obj = cls.getField("os").get(null);
                Object invoke = cls2.getMethod("fstat", FileDescriptor.class).invoke(obj, parcelFileDescriptor.getFileDescriptor());
                Class<?> cls3 = Class.forName("libcore.io.StructStat");
                return StatInfo.newInstance(cls3.getField("st_uid").getInt(invoke), cls3.getField("st_gid").getInt(invoke), cls3.getField("st_dev").getLong(invoke), cls3.getField("st_ino").getLong(invoke));
            } catch (ClassNotFoundException e) {
                throw new IOException(e);
            } catch (NoSuchMethodException e2) {
                throw new IOException(e2);
            } catch (NoSuchFieldException e3) {
                throw new IOException(e3);
            } catch (InvocationTargetException e4) {
                throw new IOException(e4);
            } catch (IllegalAccessException e5) {
                throw new IOException(e5);
            } catch (IllegalArgumentException e6) {
                throw new IOException(e6);
            }
        }
    }
}
