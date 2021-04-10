package com.oculus.vrshell.home;

import android.util.Log;
import com.facebook.common.procread.ProcReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoMethods {
    private static final String TAG = CryptoMethods.class.getSimpleName();

    public static String getFileMD5(File file) {
        String output = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            try {
                InputStream is = new FileInputStream(file);
                byte[] buffer = new byte[ProcReader.PROC_OUT_LONG];
                while (true) {
                    try {
                        int read = is.read(buffer);
                        if (read <= 0) {
                            break;
                        }
                        digest.update(buffer, 0, read);
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            is.close();
                        } catch (IOException e2) {
                            Log.e(TAG, String.format("Exception on closing MD5 input stream: %s", e2.getMessage()));
                        }
                        throw th;
                    }
                }
                output = String.format("%32s", new BigInteger(1, digest.digest()).toString(16)).replace(' ', '0');
                try {
                    is.close();
                } catch (IOException e3) {
                    Log.e(TAG, String.format("Exception on closing MD5 input stream: %s", e3.getMessage()));
                }
            } catch (FileNotFoundException e4) {
                Log.e(TAG, String.format("Exception while getting FileInputStream: %s", e4.getMessage()));
            }
        } catch (NoSuchAlgorithmException e5) {
            Log.e(TAG, String.format("Exception while getting digest: %s", e5.getMessage()));
        }
        return output;
    }
}
