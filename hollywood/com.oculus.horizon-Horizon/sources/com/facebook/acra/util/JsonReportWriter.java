package com.facebook.acra.util;

import X.AnonymousClass0NO;
import android.util.JsonWriter;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonReportWriter {
    public static final String LOG_TAG = "JsonReportWriter";

    public static void writeJsonReport(Map<String, String> map, Map<String, InputStreamField> map2, OutputStream outputStream) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        jsonWriter.setIndent("  ");
        jsonWriter.beginObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                AnonymousClass0NO.A0E(LOG_TAG, "Ignoring NULL Field %s", entry.getKey());
            } else {
                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
        }
        for (Map.Entry<String, InputStreamField> entry2 : map2.entrySet()) {
            entry2.getKey();
            entry2.getValue();
            InputStreamField value = entry2.getValue();
            InputStream inputStream = value.mInputStream;
            if (inputStream instanceof FileInputStream) {
                ((FileInputStream) inputStream).getChannel().position(0L);
            }
            jsonWriter.name(entry2.getKey()).value(AttachmentUtil.loadAttachment(inputStream, (int) value.mLength));
        }
        jsonWriter.endObject();
        jsonWriter.close();
    }

    public static void writeGzipJsonReport(Map<String, String> map, Map<String, InputStreamField> map2, OutputStream outputStream) throws IOException {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        try {
            writeJsonReport(map, map2, gZIPOutputStream);
            gZIPOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public static boolean writeGzipJsonReport(Map<String, String> map, Map<String, InputStreamField> map2, File file) {
        System.currentTimeMillis();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                writeGzipJsonReport(map, map2, fileOutputStream);
                fileOutputStream.close();
                System.currentTimeMillis();
                file.length();
                return true;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            AnonymousClass0NO.A0K(LOG_TAG, e, "Could not write report %s", file.getPath());
            return false;
        }
    }
}
