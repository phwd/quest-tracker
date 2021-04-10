package oculus.internal;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static void closeQuietly(FileOutputStream fs) {
        if (fs != null) {
            try {
                fs.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(FileInputStream fs) {
        if (fs != null) {
            try {
                fs.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(ParcelFileDescriptor fd) {
        if (fd != null) {
            try {
                fd.close();
            } catch (IOException e) {
            }
        }
    }

    public static String readAllText(File file) {
        FileInputStream inputStream = null;
        try {
            byte[] bytes = new byte[((int) file.length())];
            inputStream = new FileInputStream(file);
            if (inputStream.read(bytes) == bytes.length) {
                String str = new String(bytes);
                closeQuietly(inputStream);
                return str;
            }
        } catch (IOException e) {
            Log.e(TAG, "Couldn't read file", e);
        } catch (Throwable th) {
            closeQuietly((FileInputStream) null);
            throw th;
        }
        closeQuietly(inputStream);
        return null;
    }

    public static boolean writeAllText(File file, String text) {
        try {
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(text.getBytes());
            closeQuietly(outputStream);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Couldn't write to file", e);
            return false;
        } finally {
            closeQuietly((FileOutputStream) null);
        }
    }
}
