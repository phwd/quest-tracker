package com.facebook.acra;

import com.facebook.acra.util.InputStreamField;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.reliability.anr.AnrState;
import com.oculus.common.build.BuildConfig;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CrashReportData extends LinkedHashMap<String, String> {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String lineSeparator = "\n";
    ArrayList<String> fieldFailures;
    Throwable generatingIoError;
    public Map<String, InputStreamField> mInputStreamFields = new LinkedHashMap();
    boolean throwAwayWrites;

    public CrashReportData() {
    }

    public CrashReportData(Map<String, String> map) {
        try {
            merge(map, true, (Writer) null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dumpString(Appendable appendable, String str, boolean z) throws IOException {
        int length = str.length();
        int i = 0;
        if (!z && length > 0 && str.charAt(0) == ' ') {
            appendable.append("\\ ");
            i = 1;
        }
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case AnrState.NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED$65befc1 /*{ENCODED_INT: 9}*/:
                    appendable.append("\\t");
                    break;
                case AnrState.NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED$65befc1 /*{ENCODED_INT: 10}*/:
                    appendable.append("\\n");
                    break;
                case BreakpadManager.SIGSEGV /*{ENCODED_INT: 11}*/:
                default:
                    if ((z && charAt == ' ') || charAt == '\\' || charAt == '#' || charAt == '!' || charAt == ':') {
                        appendable.append('\\');
                    }
                    if (charAt >= ' ' && charAt <= '~') {
                        appendable.append(charAt);
                        break;
                    } else {
                        appendable.append("\\u");
                        appendable.append(HEX_DIGITS[(charAt >>> '\f') & 15]);
                        appendable.append(HEX_DIGITS[(charAt >>> '\b') & 15]);
                        appendable.append(HEX_DIGITS[(charAt >>> 4) & 15]);
                        appendable.append(HEX_DIGITS[(charAt >>> 0) & 15]);
                        break;
                    }
                case '\f':
                    appendable.append("\\f");
                    break;
                case '\r':
                    appendable.append("\\r");
                    break;
            }
            i++;
        }
    }

    public final String getProperty(String str) {
        return (String) super.get(str);
    }

    public final synchronized void load(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(Integer.MAX_VALUE);
        boolean isEbcdic = isEbcdic(bufferedInputStream);
        bufferedInputStream.reset();
        if (!isEbcdic) {
            load(new InputStreamReader(bufferedInputStream, "ISO8859-1"));
        } else {
            load(new InputStreamReader(bufferedInputStream));
        }
    }

    private static boolean isEbcdic(BufferedInputStream bufferedInputStream) throws IOException {
        byte read;
        do {
            read = (byte) bufferedInputStream.read();
            if (read == -1 || read == 35 || read == 10 || read == 61) {
                return false;
            }
        } while (read != 21);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x00ff  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void load(java.io.Reader r17) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 376
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.load(java.io.Reader):void");
    }

    public final String put(String str, String str2, Writer writer) throws IOException {
        String str3 = !this.throwAwayWrites ? (String) put(str, str2) : null;
        if (writer != null) {
            storeKeyValuePair(writer, str, str2);
        }
        return str3;
    }

    public final void putAll(Map<String, String> map, Writer writer) throws IOException {
        putAll(map);
        if (writer != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                storeKeyValuePair(writer, entry.getKey(), entry.getValue());
            }
            writer.flush();
        }
    }

    public final synchronized void store(OutputStream outputStream, String str) throws IOException {
        store(getWriter(outputStream), (String) null);
    }

    public static Writer getWriter(OutputStream outputStream) {
        try {
            return new OutputStreamWriter(outputStream, "ISO8859_1");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private static void storeKeyValuePair(Writer writer, String str, String str2) throws IOException {
        String str3 = str.toString();
        if (str2 == null) {
            str2 = BuildConfig.PROVIDER_SUFFIX;
        }
        int length = str3.length() + str2.length() + 1;
        StringBuilder sb = new StringBuilder(length + (length / 5));
        dumpString(sb, str3, true);
        sb.append('=');
        dumpString(sb, str2, false);
        sb.append(lineSeparator);
        writer.write(sb.toString());
        writer.flush();
    }

    public final void merge(Map<String, String> map, boolean z, Writer writer) throws IOException {
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            if (str2 != null && (z || get(str) == null)) {
                put(str, str2, writer);
            }
        }
        if (map instanceof CrashReportData) {
            for (Map.Entry<String, InputStreamField> entry : ((CrashReportData) map).mInputStreamFields.entrySet()) {
                if (!this.mInputStreamFields.containsKey(entry.getKey())) {
                    this.mInputStreamFields.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public final void mergeFieldOverwrite(Map<String, String> map, String str, Writer writer) throws IOException {
        String str2 = map.get(str);
        if (str2 != null) {
            put(str, str2, writer);
        }
    }

    private synchronized void store(Writer writer, String str) throws IOException {
        if (str != null) {
            writer.write("#");
            writer.write(str);
            writer.write(lineSeparator);
        }
        for (Map.Entry entry : entrySet()) {
            storeKeyValuePair(writer, (String) entry.getKey(), (String) entry.getValue());
        }
        writer.flush();
    }
}
