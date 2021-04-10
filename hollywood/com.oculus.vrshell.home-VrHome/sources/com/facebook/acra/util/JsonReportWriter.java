package com.facebook.acra.util;

import android.util.JsonWriter;
import com.facebook.debug.log.BLog;
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
    public static String LOG_TAG = JsonReportWriter.class.getSimpleName();

    public static boolean writeGzipJsonReport(Map<String, String> textParameters, Map<String, InputStreamField> binParameters, File outFile) {
        long start = System.currentTimeMillis();
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            try {
                writeGzipJsonReport(textParameters, binParameters, fos);
                fos.close();
                BLog.d(LOG_TAG, "Gzip %s ms %s bytes", Long.valueOf(System.currentTimeMillis() - start), Long.valueOf(outFile.length()));
                return true;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException e) {
            BLog.e(LOG_TAG, e, "Could not write report %s", outFile.getPath());
            return false;
        }
    }

    public static void writeGzipJsonReport(Map<String, String> textParameters, Map<String, InputStreamField> binParameters, OutputStream outputStream) throws IOException {
        GZIPOutputStream gzipStream = new GZIPOutputStream(outputStream);
        try {
            writeJsonReport(textParameters, binParameters, gzipStream);
            gzipStream.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void writeJsonReport(Map<String, String> textParameters, Map<String, InputStreamField> binParameters, OutputStream outputStream) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        jsonWriter.setIndent("  ");
        jsonWriter.beginObject();
        for (Map.Entry<String, String> param : textParameters.entrySet()) {
            if (param.getValue() == null) {
                BLog.e(LOG_TAG, "Ignoring NULL Field %s", param.getKey());
            } else {
                jsonWriter.name(param.getKey()).value(param.getValue());
            }
        }
        for (Map.Entry<String, InputStreamField> param2 : binParameters.entrySet()) {
            BLog.d(LOG_TAG, "Field %s -> %s", param2.getKey(), param2.getValue());
            InputStreamField field = param2.getValue();
            InputStream attachmentInputStream = field.getInputStream();
            if (attachmentInputStream instanceof FileInputStream) {
                ((FileInputStream) attachmentInputStream).getChannel().position(0L);
            }
            String attachment = AttachmentUtil.loadAttachment(attachmentInputStream, (int) field.getLength());
            jsonWriter.name(param2.getKey()).value(attachment);
            BLog.d(LOG_TAG, "Attachment ori len %d -> encoded len %d", Long.valueOf(field.getLength()), Integer.valueOf(attachment.length()));
        }
        jsonWriter.endObject();
        jsonWriter.close();
    }
}
