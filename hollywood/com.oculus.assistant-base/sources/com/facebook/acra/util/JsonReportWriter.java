package com.facebook.acra.util;

import X.C0139Dd;
import android.util.JsonWriter;
import com.facebook.acra.LogCatCollector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class JsonReportWriter {
    public static final String LOG_TAG = "JsonReportWriter";

    public static void writeJsonReport(Map map, Map map2, OutputStream outputStream) {
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream, LogCatCollector.UTF_8_ENCODING));
        jsonWriter.setIndent("  ");
        jsonWriter.beginObject();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == null) {
                C0139Dd.A0O(LOG_TAG, "Ignoring NULL Field %s", entry.getKey());
            } else {
                jsonWriter.name((String) entry.getKey()).value((String) entry.getValue());
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            C0139Dd.A0H(LOG_TAG, "Field %s -> %s", entry2.getKey(), entry2.getValue());
            InputStreamField inputStreamField = (InputStreamField) entry2.getValue();
            InputStream inputStream = inputStreamField.mInputStream;
            if (inputStream instanceof FileInputStream) {
                ((FileInputStream) inputStream).getChannel().position(0L);
            }
            String loadAttachment = AttachmentUtil.loadAttachment(inputStream, (int) inputStreamField.mLength);
            jsonWriter.name((String) entry2.getKey()).value(loadAttachment);
            C0139Dd.A0H(LOG_TAG, "Attachment ori len %d -> encoded len %d", Long.valueOf(inputStreamField.mLength), Integer.valueOf(loadAttachment.length()));
        }
        jsonWriter.endObject();
        jsonWriter.close();
    }

    public static void writeGzipJsonReport(Map map, Map map2, OutputStream outputStream) {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        try {
            writeJsonReport(map, map2, gZIPOutputStream);
            gZIPOutputStream.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    public static boolean writeGzipJsonReport(Map map, Map map2, File file) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                writeGzipJsonReport(map, map2, fileOutputStream);
                fileOutputStream.close();
                C0139Dd.A0H(LOG_TAG, "Gzip %s ms %s bytes", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Long.valueOf(file.length()));
                return true;
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException e) {
            C0139Dd.A0V(LOG_TAG, e, "Could not write report %s", file.getPath());
            return false;
        }
    }
}
