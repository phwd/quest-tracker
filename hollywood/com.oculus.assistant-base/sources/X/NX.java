package X;

public enum NX {
    NOT_AVAILABLE(null),
    START_OBJECT("{"),
    END_OBJECT("}"),
    START_ARRAY("["),
    END_ARRAY("]"),
    FIELD_NAME(null),
    VALUE_EMBEDDED_OBJECT(null),
    VALUE_STRING(null),
    VALUE_NUMBER_INT(null),
    VALUE_NUMBER_FLOAT(null),
    VALUE_TRUE("true"),
    VALUE_FALSE("false"),
    VALUE_NULL("null");
    
    public final String _serialized;
    public final byte[] _serializedBytes;
    public final char[] _serializedChars;

    public boolean isNumeric() {
        if (this == VALUE_NUMBER_INT || this == VALUE_NUMBER_FLOAT) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    NX(String str) {
        if (str == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
            return;
        }
        this._serialized = str;
        char[] charArray = str.toCharArray();
        this._serializedChars = charArray;
        int length = charArray.length;
        byte[] bArr = new byte[length];
        this._serializedBytes = bArr;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) charArray[i];
        }
    }

    public byte[] asByteArray() {
        return this._serializedBytes;
    }

    public char[] asCharArray() {
        return this._serializedChars;
    }

    public String asString() {
        return this._serialized;
    }

    public boolean isScalarValue() {
        if (ordinal() >= VALUE_EMBEDDED_OBJECT.ordinal()) {
            return true;
        }
        return false;
    }
}
