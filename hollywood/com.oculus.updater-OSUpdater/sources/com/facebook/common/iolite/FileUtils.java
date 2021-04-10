package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FileUtils {
    public static String readFileToString(File file, Charset charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        try {
            char[] cArr = new char[1024];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read != -1) {
                    sb.append(cArr, 0, read);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (Throwable unused) {
        }
        throw th;
    }
}
