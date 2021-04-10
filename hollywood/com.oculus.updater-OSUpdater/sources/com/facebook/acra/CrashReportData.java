package com.facebook.acra;

import com.facebook.acra.util.InputStreamField;
import com.facebook.ultralight.UL;
import com.oculus.common.build.BuildConfig;
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
    private static final int CONTINUE = 3;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int IGNORE = 5;
    private static final int KEY_DONE = 4;
    private static final int NONE = 0;
    private static final String PROP_DTD_NAME = "http://java.sun.com/dtd/properties.dtd";
    private static final int SLASH = 1;
    private static final int UNICODE = 2;
    private static String lineSeparator = "\n";
    @Nullable
    ArrayList<String> fieldFailures;
    Throwable generatingIoError;
    Map<String, InputStreamField> mInputStreamFields = new LinkedHashMap();
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
                case '\t':
                    appendable.append("\\t");
                    break;
                case '\n':
                    appendable.append("\\n");
                    break;
                case 11:
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
                case UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 12}*/:
                    appendable.append("\\f");
                    break;
                case UL.id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 13}*/:
                    appendable.append("\\r");
                    break;
            }
            i++;
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
                    if (((String) entry.getValue()).length() > 40) {
                        sb.append(((String) entry.getValue()).substring(0, 37));
                        sb.append("...");
                    } else {
                        sb.append((String) entry.getValue());
                    }
                    printStream.println(sb.toString());
                    sb.setLength(0);
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public void list(PrintWriter printWriter) {
        if (printWriter != null) {
            StringBuilder sb = new StringBuilder(80);
            for (Map.Entry entry : entrySet()) {
                if (entry.getValue() != null) {
                    sb.append((String) entry.getKey());
                    sb.append('=');
                    if (((String) entry.getValue()).length() > 40) {
                        sb.append(((String) entry.getValue()).substring(0, 37));
                        sb.append("...");
                    } else {
                        sb.append((String) entry.getValue());
                    }
                    printWriter.println(sb.toString());
                    sb.setLength(0);
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public synchronized void load(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.mark(Integer.MAX_VALUE);
            boolean isEbcdic = isEbcdic(bufferedInputStream);
            bufferedInputStream.reset();
            if (!isEbcdic) {
                load(new InputStreamReader(bufferedInputStream, "ISO8859-1"));
            } else {
                load(new InputStreamReader(bufferedInputStream));
            }
        } else {
            throw new NullPointerException();
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:109:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void load(java.io.Reader r17) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 398
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.load(java.io.Reader):void");
    }

    @Nullable
    public String put(String str, String str2, @Nullable Writer writer) throws IOException {
        String str3 = !this.throwAwayWrites ? (String) put(str, str2) : null;
        if (writer != null) {
            storeKeyValuePair(writer, str, str2);
        }
        return str3;
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

    @Deprecated
    public void save(OutputStream outputStream, String str) {
        try {
            store(outputStream, str);
        } catch (IOException unused) {
        }
    }

    public Object setProperty(String str, String str2) {
        return put(str, str2);
    }

    public synchronized void store(OutputStream outputStream, @Nullable String str) throws IOException {
        store(getWriter(outputStream), str);
    }

    @Nullable
    public static Writer getWriter(OutputStream outputStream) {
        try {
            return new OutputStreamWriter(outputStream, "ISO8859_1");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
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

    private static void storeComment(Writer writer, String str) throws IOException {
        writer.write("#");
        writer.write(str);
        writer.write(lineSeparator);
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

    public void storeToXML(OutputStream outputStream, String str) throws IOException {
        storeToXML(outputStream, str, "UTF-8");
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

    private void mergeInputStreamFields(CrashReportData crashReportData, boolean z) {
        for (Map.Entry<String, InputStreamField> entry : crashReportData.mInputStreamFields.entrySet()) {
            if (z || !this.mInputStreamFields.containsKey(entry.getKey())) {
                this.mInputStreamFields.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void storeToXML(java.io.OutputStream r4, java.lang.String r5, java.lang.String r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.storeToXML(java.io.OutputStream, java.lang.String, java.lang.String):void");
    }

    private String substitutePredefinedEntries(String str) {
        return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
    }

    public Map<String, InputStreamField> getInputStreamFields() {
        return this.mInputStreamFields;
    }
}
