package com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import com.fasterxml.jackson.dataformat.smile.SmileParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Tool {
    public static final String SUFFIX = ".lzf";
    public final JsonFactory jsonFactory = new JsonFactory();
    public final SmileFactory smileFactory = new SmileFactory();

    public Tool() {
        this.smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_NAMES, true);
        this.smileFactory.configure(SmileGenerator.Feature.CHECK_SHARED_STRING_VALUES, true);
        this.smileFactory.configure(SmileGenerator.Feature.ENCODE_BINARY_AS_7BIT, true);
        this.smileFactory.configure(SmileGenerator.Feature.WRITE_HEADER, true);
        this.smileFactory.configure(SmileGenerator.Feature.WRITE_END_MARKER, false);
        this.smileFactory.configure(SmileParser.Feature.REQUIRE_HEADER, false);
    }

    private void process(String[] strArr) throws IOException {
        String str;
        String str2 = null;
        if (strArr.length == 2) {
            str2 = strArr[0];
            str = strArr[1];
        } else if (strArr.length == 1) {
            str2 = strArr[0];
            str = null;
        } else {
            showUsage();
            str = null;
        }
        if ("-e".equals(str2)) {
            encode(inputStream(str));
        } else if ("-d".equals(str2)) {
            decode(inputStream(str));
        } else if ("-v".equals(str2)) {
            verify(inputStream(str), inputStream(str));
        } else {
            showUsage();
        }
    }

    private InputStream inputStream(String str) throws IOException {
        if (str == null) {
            return System.in;
        }
        File file = new File(str);
        if (!file.exists()) {
            PrintStream printStream = System.err;
            printStream.println("File '" + str + "' does not exist.");
            System.exit(1);
        }
        return new FileInputStream(file);
    }

    private void decode(InputStream inputStream) throws IOException {
        SmileParser createParser = this.smileFactory.createParser(inputStream);
        JsonGenerator createGenerator = this.jsonFactory.createGenerator(System.out, JsonEncoding.UTF8);
        while (true) {
            if (createParser.nextToken() == null && createParser.nextToken() == null) {
                createParser.close();
                createGenerator.close();
                return;
            }
            createGenerator.copyCurrentEvent(createParser);
        }
    }

    private void encode(InputStream inputStream) throws IOException {
        JsonParser createParser = this.jsonFactory.createParser(inputStream);
        SmileGenerator createGenerator = this.smileFactory.createGenerator((OutputStream) System.out, JsonEncoding.UTF8);
        while (createParser.nextToken() != null) {
            createGenerator.copyCurrentEvent(createParser);
        }
        createParser.close();
        createGenerator.close();
    }

    private void verify(InputStream inputStream, InputStream inputStream2) throws IOException {
        String text;
        String text2;
        JsonParser createParser = this.jsonFactory.createParser(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4000);
        SmileGenerator createGenerator = this.smileFactory.createGenerator((OutputStream) byteArrayOutputStream, JsonEncoding.UTF8);
        while (createParser.nextToken() != null) {
            createGenerator.copyCurrentEvent(createParser);
        }
        createParser.close();
        createGenerator.close();
        JsonParser createParser2 = this.jsonFactory.createParser(inputStream2);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        SmileParser createParser3 = this.smileFactory.createParser(byteArray);
        int i = 0;
        do {
            JsonToken nextToken = createParser2.nextToken();
            if (nextToken != null) {
                JsonToken nextToken2 = createParser3.nextToken();
                i++;
                if (nextToken == nextToken2) {
                    text = createParser2.getText();
                    text2 = createParser3.getText();
                } else {
                    throw new IOException("Input and encoded differ, token #" + i + "; expected " + nextToken + ", got " + nextToken2);
                }
            } else {
                PrintStream printStream = System.out;
                printStream.println("OK: verified " + i + " tokens (from " + byteArray.length + " bytes of Smile encoded data), input and encoded contents are identical");
                return;
            }
        } while (text.equals(text2));
        throw new IOException("Input and encoded differ, token #" + i + "; expected text '" + text + "', got '" + text2 + "'");
    }

    /* access modifiers changed from: protected */
    public void showUsage() {
        PrintStream printStream = System.err;
        printStream.println("Usage: java " + getClass().getName() + " -e/-d [file]");
        System.err.println(" (if no file given, reads from stdin -- always writes to stdout)");
        System.err.println(" -d: decode Smile encoded input as JSON");
        System.err.println(" -e: encode JSON (text) input as Smile");
        System.err.println(" -v: encode JSON (text) input as Smile; read back, verify, do not write out");
        System.exit(1);
    }

    public static void main(String[] strArr) throws IOException {
        new Tool().process(strArr);
    }
}
