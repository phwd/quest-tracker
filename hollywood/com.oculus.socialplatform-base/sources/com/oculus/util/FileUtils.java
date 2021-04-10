package com.oculus.util;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileUtils {
    public static final String TAG = "FileUtils";

    public static String getPrivateFilePath(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir());
        sb.append(File.separator);
        sb.append(str);
        return sb.toString();
    }

    public static void deleteFromInternalStorage(Context context, String str) {
        File privateFile = getPrivateFile(context, str);
        if (privateFile != null && privateFile.exists()) {
            privateFile.delete();
        }
    }

    public static File getPrivateFile(Context context, String str) {
        try {
            return new File(getPrivateFilePath(context, str));
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - Failed to create File instance from %s because %s", str, e.getMessage()));
            return null;
        }
    }

    public static String readFromInternalStorage(Context context, String str) {
        File privateFile = getPrivateFile(context, str);
        if (privateFile == null || !privateFile.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(privateFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            fileInputStream.close();
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - failed to read from %s because %s", str, e.getMessage()));
        }
        return sb.toString();
    }

    public static void writeBytesToInternalStorage(Context context, String str, byte[] bArr) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getPrivateFile(context, str));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            bArr.toString();
        } catch (Exception e) {
            Log.e(TAG, String.format("Error - failed to save %s because %s", str, e.getMessage()));
        }
    }

    public static void writeToInternalStorage(Context context, String str, String str2) {
        writeBytesToInternalStorage(context, str, str2.getBytes());
    }
}
