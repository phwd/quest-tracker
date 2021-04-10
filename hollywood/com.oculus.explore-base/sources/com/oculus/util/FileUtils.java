package com.oculus.util;

import android.content.Context;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static void deleteFromInternalStorage(Context context, String fileName) {
        File file = getPrivateFile(context, fileName);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static void writeToInternalStorage(Context context, String fileName, String data) {
        writeBytesToInternalStorage(context, fileName, data.getBytes());
    }

    public static void writeBytesToInternalStorage(Context context, String fileName, byte[] data) {
        try {
            FileOutputStream outputStream = new FileOutputStream(getPrivateFile(context, fileName));
            outputStream.write(data);
            outputStream.close();
            Log.d(TAG, String.format("Saved %s to %s", data.toString(), fileName));
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - failed to save %s because %s", fileName, e.getMessage()));
        }
    }

    public static String readFromInternalStorage(Context context, String fileName) {
        File fileInput = getPrivateFile(context, fileName);
        if (fileInput == null || !fileInput.exists()) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(fileInput);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\n");
            }
            inputStream.close();
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - failed to read from %s because %s", fileName, e.getMessage()));
        }
        return sb.toString();
    }

    public static String getPrivateFilePath(Context context, String fileName) {
        return context.getFilesDir() + File.separator + fileName;
    }

    public static File getPrivateFile(Context context, String fileName) {
        try {
            return new File(getPrivateFilePath(context, fileName));
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - Failed to create File instance from %s because %s", fileName, e.getMessage()));
            return null;
        }
    }
}
