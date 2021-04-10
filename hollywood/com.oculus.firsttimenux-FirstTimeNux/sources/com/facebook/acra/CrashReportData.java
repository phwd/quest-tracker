package com.facebook.acra;

import com.facebook.acra.util.InputStreamField;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.zstd.AbstractZstdOutputStream;
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
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
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
                case ExceptionHandlerManager.PRIORITY_EARLY /*{ENCODED_INT: 10}*/:
                    sb.append("\\n");
                    break;
                case BreakpadManager.SIGSEGV /*{ENCODED_INT: 11}*/:
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
                case AbstractZstdOutputStream.DEFAULT_COMPRESSION_LEVEL /*{ENCODED_INT: 13}*/:
                    sb.append("\\r");
                    break;
            }
            i++;
        }
    }

    public String getProperty(String key) {
        return (String) super.get(key);
    }

    public String getProperty(String key, String defaultValue) {
        String property = (String) super.get(key);
        return property == null ? defaultValue : property;
    }

    public void list(PrintStream out) {
        if (out == null) {
            throw new NullPointerException();
        }
        StringBuilder buffer = new StringBuilder(80);
        for (Map.Entry<String, String> entry : entrySet()) {
            if (entry.getValue() != null) {
                buffer.append(entry.getKey());
                buffer.append('=');
                if (entry.getValue().length() > 40) {
                    buffer.append(entry.getValue().substring(0, 37));
                    buffer.append("...");
                } else {
                    buffer.append(entry.getValue());
                }
                out.println(buffer.toString());
                buffer.setLength(0);
            }
        }
    }

    public void list(PrintWriter writer) {
        if (writer == null) {
            throw new NullPointerException();
        }
        StringBuilder buffer = new StringBuilder(80);
        for (Map.Entry<String, String> entry : entrySet()) {
            if (entry.getValue() != null) {
                buffer.append(entry.getKey());
                buffer.append('=');
                if (entry.getValue().length() > 40) {
                    buffer.append(entry.getValue().substring(0, 37));
                    buffer.append("...");
                } else {
                    buffer.append(entry.getValue());
                }
                writer.println(buffer.toString());
                buffer.setLength(0);
            }
        }
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

    @Nullable
    public String put(String key, String value, @Nullable Writer writer) throws IOException {
        String previousValue = null;
        if (!this.throwAwayWrites) {
            previousValue = (String) put(key, value);
        }
        if (writer != null) {
            storeKeyValuePair(writer, key, value);
        }
        return previousValue;
    }

    public void putAll(Map<String, String> properties, @Nullable Writer writer) throws IOException {
        putAll(properties);
        if (writer != null) {
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                storeKeyValuePair(writer, entry.getKey(), entry.getValue());
            }
            writer.flush();
        }
    }

    @Deprecated
    public void save(OutputStream out, String comment) {
        try {
            store(out, comment);
        } catch (IOException e) {
        }
    }

    public Object setProperty(String key, String value) {
        return put(key, value);
    }

    public synchronized void store(OutputStream out, @Nullable String comment) throws IOException {
        store(getWriter(out), comment);
    }

    @Nullable
    public static Writer getWriter(OutputStream out) {
        try {
            return new OutputStreamWriter(out, "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public synchronized void store(Writer writer, @Nullable String comment) throws IOException {
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

    public void storeToXML(OutputStream os, String comment) throws IOException {
        storeToXML(os, comment, "UTF-8");
    }

    public void merge(Map<String, String> other, boolean overwriteExisting, @Nullable Writer writer) throws IOException {
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

    public void mergeFieldOverwrite(Map<String, String> other, String fieldName, @Nullable Writer writer) throws IOException {
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

    public synchronized void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
        String encodingCanonicalName;
        if (os == null || encoding == null) {
            throw new NullPointerException();
        }
        try {
            encodingCanonicalName = Charset.forName(encoding).name();
        } catch (IllegalCharsetNameException e) {
            System.out.println("Warning: encoding name " + encoding + " is illegal, using UTF-8 as default encoding");
            encodingCanonicalName = "UTF-8";
        } catch (UnsupportedCharsetException e2) {
            System.out.println("Warning: encoding " + encoding + " is not supported, using UTF-8 as default encoding");
            encodingCanonicalName = "UTF-8";
        }
        PrintStream printStream = new PrintStream(os, false, encodingCanonicalName);
        printStream.print("<?xml version=\"1.0\" encoding=\"");
        printStream.print(encodingCanonicalName);
        printStream.println("\"?>");
        printStream.print("<!DOCTYPE properties SYSTEM \"");
        printStream.print(PROP_DTD_NAME);
        printStream.println("\">");
        printStream.println("<properties>");
        if (comment != null) {
            printStream.print("<comment>");
            printStream.print(substitutePredefinedEntries(comment));
            printStream.println("</comment>");
        }
        for (Map.Entry<String, String> entry : entrySet()) {
            String keyValue = entry.getKey().toString();
            printStream.print("<entry key=\"");
            printStream.print(substitutePredefinedEntries(keyValue));
            printStream.print("\">");
            printStream.print(substitutePredefinedEntries(entry.getValue()));
            printStream.println("</entry>");
        }
        printStream.println("</properties>");
        printStream.flush();
    }

    private String substitutePredefinedEntries(String s) {
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
    }

    public Map<String, InputStreamField> getInputStreamFields() {
        return this.mInputStreamFields;
    }
}
