package com.facebook.acra.util;

import android.util.JsonWriter;
import com.facebook.debug.log.BLog;
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
    public static String LOG_TAG = "JsonReportWriter";

    public static boolean writeGzipJsonReport(Map<String, String> map, Map<String, InputStreamField> map2, File file) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                writeGzipJsonReport(map, map2, fileOutputStream);
                fileOutputStream.close();
                BLog.d(LOG_TAG, "Gzip %s ms %s bytes", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Long.valueOf(file.length()));
                return true;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "Could not write report %s", file.getPath());
            return false;
        }
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

    public static void writeJsonReport(Map<String, String> map, Map<String, InputStreamField> map2, OutputStream outputStream) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        jsonWriter.setIndent("  ");
        jsonWriter.beginObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                BLog.e(LOG_TAG, "Ignoring NULL Field %s", entry.getKey());
            } else {
                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
        }
        for (Map.Entry<String, InputStreamField> entry2 : map2.entrySet()) {
            BLog.d(LOG_TAG, "Field %s -> %s", entry2.getKey(), entry2.getValue());
            InputStreamField value = entry2.getValue();
            InputStream inputStream = value.getInputStream();
            if (inputStream instanceof FileInputStream) {
                ((FileInputStream) inputStream).getChannel().position(0L);
            }
            String loadAttachment = AttachmentUtil.loadAttachment(inputStream, (int) value.getLength());
            jsonWriter.name(entry2.getKey()).value(loadAttachment);
            BLog.d(LOG_TAG, "Attachment ori len %d -> encoded len %d", Long.valueOf(value.getLength()), Integer.valueOf(loadAttachment.length()));
        }
        jsonWriter.endObject();
        jsonWriter.close();
    }
}
