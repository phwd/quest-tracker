package com.facebook.acra;

import com.facebook.acra.util.InputStreamField;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class CrashReportData extends LinkedHashMap<String, String> {
    public static final int CONTINUE = 3;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final int IGNORE = 5;
    public static final int KEY_DONE = 4;
    public static final int NONE = 0;
    public static final String PROP_DTD_NAME = "http://java.sun.com/dtd/properties.dtd";
    public static final int SLASH = 1;
    public static final int UNICODE = 2;
    public static final String lineSeparator = "\n";
    @Nullable
    public ArrayList<String> fieldFailures;
    public Throwable generatingIoError;
    public Map<String, InputStreamField> mInputStreamFields = new LinkedHashMap();
    public final boolean throwAwayWrites;

    @Nullable
    public static Writer getWriter(OutputStream outputStream) {
        try {
            return new OutputStreamWriter(outputStream, "ISO8859_1");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private void mergeInputStreamFields(CrashReportData crashReportData, boolean z) {
        for (Map.Entry<String, InputStreamField> entry : crashReportData.mInputStreamFields.entrySet()) {
            if (z || !this.mInputStreamFields.containsKey(entry.getKey())) {
                this.mInputStreamFields.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void storeComment(Writer writer, String str) throws IOException {
        writer.write("#");
        writer.write(str);
        writer.write(lineSeparator);
    }

    public static void storeKeyValuePair(Writer writer, String str, String str2) throws IOException {
        if (str2 == null) {
            str2 = "";
        }
        int length = str.length() + str2.length() + 1;
        StringBuilder sb = new StringBuilder(length + (length / 5));
        dumpString(sb, str, true);
        sb.append('=');
        dumpString(sb, str2, false);
        sb.append(lineSeparator);
        writer.write(sb.toString());
        writer.flush();
    }

    private String substitutePredefinedEntries(String str) {
        return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
    }

    @Nullable
    public String put(String str, String str2, @Nullable Writer writer) throws IOException {
        String str3;
        if (!this.throwAwayWrites) {
            str3 = (String) put(str, str2);
        } else {
            str3 = null;
        }
        if (writer != null) {
            storeKeyValuePair(writer, str, str2);
        }
        return str3;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0020  */
    public static void dumpString(java.lang.Appendable r6, java.lang.String r7, boolean r8) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 138
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.dumpString(java.lang.Appendable, java.lang.String, boolean):void");
    }

    private boolean isEbcdic(BufferedInputStream bufferedInputStream) throws IOException {
        byte read;
        do {
            read = (byte) bufferedInputStream.read();
            if (read == -1 || read == 35 || read == 10 || read == 61) {
                return false;
            }
        } while (read != 21);
        return true;
    }

    public Map<String, InputStreamField> getInputStreamFields() {
        return this.mInputStreamFields;
    }

    public void merge(Map<String, String> map, boolean z, @Nullable Writer writer) throws IOException {
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            if (str2 != null && (z || get(str) == null)) {
                put(str, str2, writer);
            }
        }
        if (map instanceof CrashReportData) {
            mergeInputStreamFields((CrashReportData) map, false);
        }
    }

    public void mergeFieldOverwrite(Map<String, String> map, String str, @Nullable Writer writer) throws IOException {
        String str2 = map.get(str);
        if (str2 != null) {
            put(str, str2, writer);
        }
    }

    public void putAll(Map<String, String> map, @Nullable Writer writer) throws IOException {
        putAll(map);
        if (writer != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                storeKeyValuePair(writer, entry.getKey(), entry.getValue());
            }
            writer.flush();
        }
    }

    public Object setProperty(String str, String str2) {
        return put(str, str2);
    }

    @Deprecated
    public void save(OutputStream outputStream, String str) {
        try {
            store(outputStream, str);
        } catch (IOException unused) {
        }
    }

    public CrashReportData() {
    }

    public CrashReportData(Map<String, String> map) {
        try {
            merge(map, true, (Writer) null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String str) {
        return (String) super.get(str);
    }

    public String getProperty(String str, String str2) {
        String str3 = (String) super.get(str);
        return str3 == null ? str2 : str3;
    }

    public void list(PrintStream printStream) {
        if (printStream != null) {
            StringBuilder sb = new StringBuilder(80);
            for (Map.Entry entry : entrySet()) {
                if (entry.getValue() != null) {
                    sb.append((String) entry.getKey());
                    sb.append('=');
                    int length = ((String) entry.getValue()).length();
                    String str = (String) entry.getValue();
                    if (length > 40) {
                        sb.append(str.substring(0, 37));
                        str = "...";
                    }
                    sb.append(str);
                    printStream.println(sb.toString());
                    sb.setLength(0);
                }
            }
            return;
        }
        throw null;
    }

    public void list(PrintWriter printWriter) {
        if (printWriter != null) {
            StringBuilder sb = new StringBuilder(80);
            for (Map.Entry entry : entrySet()) {
                if (entry.getValue() != null) {
                    sb.append((String) entry.getKey());
                    sb.append('=');
                    int length = ((String) entry.getValue()).length();
                    String str = (String) entry.getValue();
                    if (length > 40) {
                        sb.append(str.substring(0, 37));
                        str = "...";
                    }
                    sb.append(str);
                    printWriter.println(sb.toString());
                    sb.setLength(0);
                }
            }
            return;
        }
        throw null;
    }

    public synchronized void load(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader;
        if (inputStream != null) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.mark(Integer.MAX_VALUE);
            boolean isEbcdic = isEbcdic(bufferedInputStream);
            bufferedInputStream.reset();
            if (!isEbcdic) {
                inputStreamReader = new InputStreamReader(bufferedInputStream, "ISO8859-1");
            } else {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
            }
            load(inputStreamReader);
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0144, code lost:
        if (r5 >= 0) goto L_0x0146;
     */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0155  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void load(java.io.Reader r18) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 355
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.load(java.io.Reader):void");
    }

    public synchronized void store(OutputStream outputStream, @Nullable String str) throws IOException {
        store(getWriter(outputStream), str);
    }

    public synchronized void store(Writer writer, @Nullable String str) throws IOException {
        if (str != null) {
            storeComment(writer, str);
        }
        for (Map.Entry entry : entrySet()) {
            storeKeyValuePair(writer, (String) entry.getKey(), (String) entry.getValue());
        }
        writer.flush();
    }

    public void storeToXML(OutputStream outputStream, String str) throws IOException {
        storeToXML(outputStream, str, "UTF-8");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void storeToXML(java.io.OutputStream r7, java.lang.String r8, java.lang.String r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.storeToXML(java.io.OutputStream, java.lang.String, java.lang.String):void");
    }
}
