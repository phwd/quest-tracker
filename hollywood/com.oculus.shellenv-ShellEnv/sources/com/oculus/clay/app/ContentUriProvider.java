package com.oculus.clay.app;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class ContentUriProvider {
    private final Context a;

    public ContentUriProvider(Context context) {
        this.a = context;
    }

    public final boolean canAccessContentUri(String str) {
        try {
            InputStream openInputStream = this.a.getContentResolver().openInputStream(Uri.parse(str));
            boolean z = openInputStream != null;
            if (openInputStream != null) {
                openInputStream.close();
            }
            return z;
        } catch (Exception unused) {
            String.format("Cannot access content URI %s", str);
            return false;
        }
    }

    public final byte[] readContentUriContents(String str) {
        String str2;
        try {
            InputStream openInputStream = this.a.getContentResolver().openInputStream(Uri.parse(str));
            try {
                byte[] bArr = new byte[4096];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        openInputStream.close();
                        return byteArray;
                    }
                }
            } catch (Throwable unused) {
            }
            throw th;
        } catch (FileNotFoundException unused2) {
            str2 = String.format("Application did not have appropriate permissions to retrieve content URI %s", str);
            Log.w("Clay", str2);
            return null;
        } catch (IOException unused3) {
            str2 = String.format("Failed to read InputStream when retrieving content URI %s", str);
            Log.w("Clay", str2);
            return null;
        } catch (Exception unused4) {
            str2 = String.format("Unknown exception when retrieving content URI %s", str);
            Log.w("Clay", str2);
            return null;
        }
    }
}
