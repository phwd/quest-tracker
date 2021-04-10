package com.facebook.common.iolite;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FileUtils {
    public static void copyToFileOrThrow(InputStream inputStream, File file) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read >= 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        return;
                    } catch (IOException unused) {
                        throw new IOException("IOException when getting file from URI:\" + contentURIFilePath");
                    }
                }
            }
        } finally {
            fileOutputStream.flush();
            try {
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
            } catch (IOException unused2) {
                throw new IOException("IOException when getting file from URI:\" + contentURIFilePath");
            }
        }
    }

    public static File getInternalCacheDirectory(Context context) throws IOException {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            return cacheDir;
        }
        throw new IOException("Internal cache directory not available");
    }

    public static void writeLines(Collection<String> collection, File file) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        for (String str : collection) {
            printWriter.println(str);
        }
        printWriter.close();
    }

    public static List<String> readLines(File file) throws FileNotFoundException {
        ArrayList arrayList = new ArrayList();
        Scanner scanner = new Scanner(new FileReader(file));
        while (scanner.hasNextLine()) {
            arrayList.add(scanner.nextLine());
        }
        scanner.close();
        return arrayList;
    }

    public static void writeStringToFile(String str, File file, Charset charset) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

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
