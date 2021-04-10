package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import javax.annotation.Nullable;

public final class FbJsonUtil {
    public static void throwDeserializationFailure(Class<?> cls, JsonParser jsonParser, Exception exc) throws IOException, JsonParseException {
        String str;
        try {
            str = getJsonParserText(jsonParser);
        } catch (Exception unused) {
            str = "failed to get parser text";
        }
        throw new JsonParseException("Failed to deserialize to instance " + cls.getSimpleName() + "\n" + str, jsonParser.getCurrentLocation(), exc);
    }

    public static String getJsonParserText(JsonParser jsonParser) throws IOException {
        Object inputSource = jsonParser.getInputSource();
        StringBuilder sb = new StringBuilder();
        sb.append("current token: ");
        sb.append(jsonParser.getText());
        sb.append("\n");
        if (inputSource instanceof InputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jsonParser.releaseBuffered(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            char[] charArray = byteArrayOutputStream.toString().toCharArray();
            sb.append(charArray, 0, Math.min(charArray.length, 100 - sb.length()));
            byteArrayOutputStream.close();
            InputStream inputStream = (InputStream) inputSource;
            while (true) {
                int read = inputStream.read();
                if (read == -1 || sb.length() >= 100) {
                    break;
                }
                sb.append((char) read);
            }
        } else if (inputSource instanceof Reader) {
            StringWriter stringWriter = new StringWriter();
            jsonParser.releaseBuffered(stringWriter);
            stringWriter.flush();
            char[] charArray2 = stringWriter.toString().toCharArray();
            sb.append(charArray2, 0, Math.min(charArray2.length, 100 - sb.length()));
            stringWriter.close();
            Reader reader = (Reader) inputSource;
            while (true) {
                int read2 = reader.read();
                if (read2 == -1 || sb.length() >= 100) {
                    reader.close();
                } else {
                    sb.append((char) read2);
                }
            }
            reader.close();
        }
        if (sb.length() == 100) {
            sb.append("...");
        }
        return sb.toString();
    }

    public static byte[] writeValueAsBytes(JsonFactory jsonFactory, @Nullable Object obj) throws IOException {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(jsonFactory._getBufferRecycler());
        jsonFactory.createGenerator(byteArrayBuilder).writeObject(obj);
        byte[] byteArray = byteArrayBuilder.toByteArray();
        byteArrayBuilder.release();
        return byteArray;
    }
}
