package com.facebook.crudolib.params;

import com.facebook.debug.log.LoggingUtil;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ParamsJsonEncoder implements ParamsEncoder {
    public static final int FLAG_ENCODE_AS_JSON_STRING = 1;
    private static final int FLAG_NONE = 0;
    private static ParamsJsonEncoder sInstance;

    public static synchronized ParamsJsonEncoder getInstance() {
        ParamsJsonEncoder paramsJsonEncoder;
        synchronized (ParamsJsonEncoder.class) {
            if (sInstance == null) {
                sInstance = new ParamsJsonEncoder();
            }
            paramsJsonEncoder = sInstance;
        }
        return paramsJsonEncoder;
    }

    @Override // com.facebook.crudolib.params.ParamsEncoder
    public void encode(Writer writer, ParamsCollection paramsCollection) throws IOException {
        if (paramsCollection instanceof ParamsCollectionMap) {
            encodeMap(writer, (ParamsCollectionMap) paramsCollection);
        } else {
            encodeArray(writer, (ParamsCollectionArray) paramsCollection);
        }
    }

    private void encodeMap(Writer writer, ParamsCollectionMap paramsCollectionMap) throws IOException {
        writer.write(123);
        encodeBodyMap(writer, paramsCollectionMap);
        writer.write(125);
    }

    private void encodeArray(Writer writer, ParamsCollectionArray paramsCollectionArray) throws IOException {
        writer.write(91);
        encodeBodyArray(writer, paramsCollectionArray);
        writer.write(93);
    }

    public void encodeBody(Writer writer, ParamsCollection paramsCollection) throws IOException {
        if (paramsCollection instanceof ParamsCollectionMap) {
            encodeBodyMap(writer, (ParamsCollectionMap) paramsCollection);
        } else {
            encodeBodyArray(writer, (ParamsCollectionArray) paramsCollection);
        }
    }

    private void encodeBodyMap(Writer writer, ParamsCollectionMap paramsCollectionMap) throws IOException {
        int size = paramsCollectionMap.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                writer.write(44);
            }
            String key = paramsCollectionMap.getKey(i);
            writeJsonString(writer, key);
            writer.write(58);
            encodeValue(writer, key, paramsCollectionMap.getValue(i));
        }
    }

    private void encodeBodyArray(Writer writer, ParamsCollectionArray paramsCollectionArray) throws IOException {
        int size = paramsCollectionArray.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                writer.write(44);
            }
            encodeValue(writer, null, paramsCollectionArray.get(i));
        }
    }

    private void encodeValue(Writer writer, @Nullable String str, @Nullable Object obj) throws IOException {
        String str2;
        if (obj == null) {
            writer.write(LoggingUtil.NO_HASHCODE);
        } else if (obj instanceof String) {
            writeJsonString(writer, (String) obj);
        } else if (obj instanceof Number) {
            writeJsonNumber(writer, (Number) obj);
        } else if (obj instanceof Boolean) {
            writer.write(((Boolean) obj).booleanValue() ? "true" : "false");
        } else if (obj instanceof ParamsCollection) {
            writeSubParameters(writer, (ParamsCollection) obj);
        } else {
            if (str != null) {
                str2 = " (found in key '" + str + "')";
            } else {
                str2 = "";
            }
            throw new IllegalArgumentException("The type " + obj.getClass().toString() + " is not supported" + str2);
        }
    }

    /* JADX INFO: finally extract failed */
    private void writeSubParameters(Writer writer, ParamsCollection paramsCollection) throws IOException {
        int parentEncoderFlags = paramsCollection.getParentEncoderFlags(ParamsJsonEncoder.class, 0);
        Class<?> cls = paramsCollection.getEncoder(this).getClass();
        if (cls.equals(ParamsJsonEncoder.class) && parentEncoderFlags == 0) {
            paramsCollection.encode(writer, this);
        } else if ((parentEncoderFlags & 1) != 0) {
            writer.write(34);
            JsonEncodingWriter jsonEncodingWriter = new JsonEncodingWriter(writer);
            try {
                paramsCollection.encode(jsonEncodingWriter, this);
                jsonEncodingWriter.flush();
                writer.write(34);
            } catch (Throwable th) {
                jsonEncodingWriter.flush();
                throw th;
            }
        } else {
            throw new IllegalStateException("Unsupported encoder=" + cls + ", flags=" + parentEncoderFlags + " combination");
        }
    }

    private void writeJsonString(Writer writer, String str) throws IOException {
        writer.write(34);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            JsonEncodingWriter.writeUnencodedChar(writer, str.charAt(i));
        }
        writer.write(34);
    }

    private void writeJsonNumber(Writer writer, Number number) throws IOException {
        NumberFormatHelper.getThreadLocalInstance().writeTo(writer, number);
    }
}
