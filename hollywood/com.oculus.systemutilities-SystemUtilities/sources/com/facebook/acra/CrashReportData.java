package com.facebook.acra;

import com.facebook.acra.util.InputStreamField;
import com.oculus.systemutilities.R;
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

public class CrashReportData extends LinkedHashMap<String, String> {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String lineSeparator = "\n";
    ArrayList<String> fieldFailures;
    Throwable generatingIoError;
    Map<String, InputStreamField> mInputStreamFields = new LinkedHashMap();
    boolean throwAwayWrites;

    public CrashReportData() {
    }

    public CrashReportData(Map<String, String> extras) {
        try {
            merge(extras, true, (Writer) null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dumpString(Appendable sb, String string, boolean key) throws IOException {
        int i = 0;
        int length = string.length();
        if (!key && 0 < length && string.charAt(0) == ' ') {
            sb.append("\\ ");
            i = 0 + 1;
        }
        while (i < length) {
            char ch = string.charAt(i);
            switch (ch) {
                case '\t':
                    sb.append("\\t");
                    break;
                case R.styleable.GradientColor_android_endX /*{ENCODED_INT: 10}*/:
                    sb.append("\\n");
                    break;
                case 11:
                default:
                    if ((key && ch == ' ') || ch == '\\' || ch == '#' || ch == '!' || ch == ':') {
                        sb.append('\\');
                    }
                    if (ch >= ' ' && ch <= '~') {
                        sb.append(ch);
                        break;
                    } else {
                        sb.append("\\u");
                        sb.append(HEX_DIGITS[(ch >>> '\f') & 15]);
                        sb.append(HEX_DIGITS[(ch >>> '\b') & 15]);
                        sb.append(HEX_DIGITS[(ch >>> 4) & 15]);
                        sb.append(HEX_DIGITS[(ch >>> 0) & 15]);
                        break;
                    }
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
            }
            i++;
        }
    }

    public String getProperty(String key) {
        return (String) super.get(key);
    }

    public synchronized void load(InputStream in) throws IOException {
        if (in == null) {
            throw new NullPointerException();
        }
        BufferedInputStream bis = new BufferedInputStream(in);
        bis.mark(Integer.MAX_VALUE);
        boolean isEbcdic = isEbcdic(bis);
        bis.reset();
        if (!isEbcdic) {
            load(new InputStreamReader(bis, "ISO8859-1"));
        } else {
            load(new InputStreamReader(bis));
        }
    }

    private boolean isEbcdic(BufferedInputStream in) throws IOException {
        byte b;
        do {
            b = (byte) in.read();
            if (b == -1 || b == 35 || b == 10 || b == 61) {
                return false;
            }
        } while (b != 21);
        return true;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void load(java.io.Reader r22) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 522
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.CrashReportData.load(java.io.Reader):void");
    }

    public String put(String key, String value, Writer writer) throws IOException {
        String previousValue = null;
        if (!this.throwAwayWrites) {
            previousValue = (String) put(key, value);
        }
        if (writer != null) {
            storeKeyValuePair(writer, key, value);
        }
        return previousValue;
    }

    public void putAll(Map<String, String> properties, Writer writer) throws IOException {
        putAll(properties);
        if (writer != null) {
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                storeKeyValuePair(writer, entry.getKey(), entry.getValue());
            }
            writer.flush();
        }
    }

    public synchronized void store(OutputStream out, String comment) throws IOException {
        store(getWriter(out), comment);
    }

    public static Writer getWriter(OutputStream out) {
        try {
            return new OutputStreamWriter(out, "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public synchronized void store(Writer writer, String comment) throws IOException {
        if (comment != null) {
            storeComment(writer, comment);
        }
        for (Map.Entry<String, String> entry : entrySet()) {
            storeKeyValuePair(writer, entry.getKey(), entry.getValue());
        }
        writer.flush();
    }

    private static void storeComment(Writer writer, String comment) throws IOException {
        writer.write("#");
        writer.write(comment);
        writer.write(lineSeparator);
    }

    private static void storeKeyValuePair(Writer writer, String key, String value) throws IOException {
        String valueString;
        String keyString = key.toString();
        if (value == null) {
            valueString = "";
        } else {
            valueString = value;
        }
        int totalLength = keyString.length() + valueString.length() + 1;
        StringBuilder sb = new StringBuilder((totalLength / 5) + totalLength);
        dumpString(sb, keyString, true);
        sb.append('=');
        dumpString(sb, valueString, false);
        sb.append(lineSeparator);
        writer.write(sb.toString());
        writer.flush();
    }

    public void merge(Map<String, String> other, boolean overwriteExisting, Writer writer) throws IOException {
        for (String key : other.keySet()) {
            String value = other.get(key);
            if (value != null && (overwriteExisting || get(key) == null)) {
                put(key, value, writer);
            }
        }
        if (other instanceof CrashReportData) {
            mergeInputStreamFields((CrashReportData) other, false);
        }
    }

    public void mergeFieldOverwrite(Map<String, String> other, String fieldName, Writer writer) throws IOException {
        String value = other.get(fieldName);
        if (value != null) {
            put(fieldName, value, writer);
        }
    }

    private void mergeInputStreamFields(CrashReportData other, boolean overwriteExisting) {
        for (Map.Entry<String, InputStreamField> entry : other.mInputStreamFields.entrySet()) {
            if (overwriteExisting || !this.mInputStreamFields.containsKey(entry.getKey())) {
                this.mInputStreamFields.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, InputStreamField> getInputStreamFields() {
        return this.mInputStreamFields;
    }
}
