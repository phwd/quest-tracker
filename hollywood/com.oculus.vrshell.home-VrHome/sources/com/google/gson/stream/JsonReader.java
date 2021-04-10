package com.google.gson.stream;

import com.adobe.xmp.XMPError;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import com.oculus.os.Version;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader implements Closeable {
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private final char[] buffer = new char[1024];
    private final Reader in;
    private boolean lenient = false;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int[] pathIndices;
    private String[] pathNames;
    int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos = 0;
    private int[] stack = new int[32];
    private int stackSize = 0;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            /* class com.google.gson.stream.JsonReader.AnonymousClass1 */

            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader reader) throws IOException {
                if (reader instanceof JsonTreeReader) {
                    ((JsonTreeReader) reader).promoteNameToValue();
                    return;
                }
                int p = reader.peeked;
                if (p == 0) {
                    p = reader.doPeek();
                }
                if (p == 13) {
                    reader.peeked = 9;
                } else if (p == 12) {
                    reader.peeked = 8;
                } else if (p == 14) {
                    reader.peeked = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + reader.peek() + reader.locationString());
                }
            }
        };
    }

    public JsonReader(Reader in2) {
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (in2 == null) {
            throw new NullPointerException("in == null");
        }
        this.in = in2;
    }

    public final void setLenient(boolean lenient2) {
        this.lenient = lenient2;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + locationString());
    }

    public void endArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + peek() + locationString());
    }

    public void beginObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + locationString());
    }

    public void endObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + peek() + locationString());
    }

    public boolean hasNext() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        return (p == 2 || p == 4) ? false : true;
    }

    public JsonToken peek() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        switch (p) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return JsonToken.NAME;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
            case 16:
                return JsonToken.NUMBER;
            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: package-private */
    public int doPeek() throws IOException {
        int peekStack = this.stack[this.stackSize - 1];
        if (peekStack == 1) {
            this.stack[this.stackSize - 1] = 2;
        } else if (peekStack == 2) {
            switch (nextNonWhitespace(true)) {
                case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
                    break;
                case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (peekStack == 5) {
                switch (nextNonWhitespace(true)) {
                    case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
                        break;
                    case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            int c = nextNonWhitespace(true);
            switch (c) {
                case Version.VERSION_34 /*{ENCODED_INT: 34}*/:
                    this.peeked = 13;
                    return 13;
                case Version.VERSION_39 /*{ENCODED_INT: 39}*/:
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    this.pos--;
                    if (isLiteral((char) c)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (peekStack == 4) {
            this.stack[this.stackSize - 1] = 5;
            switch (nextNonWhitespace(true)) {
                case Version.VERSION_58 /*{ENCODED_INT: 58}*/:
                    break;
                case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                case Version.VERSION_60 /*{ENCODED_INT: 60}*/:
                default:
                    throw syntaxError("Expected ':'");
                case Version.VERSION_61 /*{ENCODED_INT: 61}*/:
                    checkLenient();
                    if ((this.pos < this.limit || fillBuffer(1)) && this.buffer[this.pos] == '>') {
                        this.pos++;
                        break;
                    }
            }
        } else if (peekStack == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (peekStack == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (peekStack == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (nextNonWhitespace(true)) {
            case Version.VERSION_34 /*{ENCODED_INT: 34}*/:
                this.peeked = 9;
                return 9;
            case Version.VERSION_39 /*{ENCODED_INT: 39}*/:
                checkLenient();
                this.peeked = 8;
                return 8;
            case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
            case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                break;
            case 91:
                this.peeked = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.peeked = 4;
                    return 4;
                }
                break;
            case 123:
                this.peeked = 1;
                return 1;
            default:
                this.pos--;
                int result = peekKeyword();
                if (result != 0) {
                    return result;
                }
                int result2 = peekNumber();
                if (result2 != 0) {
                    return result2;
                }
                if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
        }
        if (peekStack == 1 || peekStack == 2) {
            checkLenient();
            this.pos--;
            this.peeked = 7;
            return 7;
        }
        throw syntaxError("Unexpected value");
    }

    private int peekKeyword() throws IOException {
        int peeking;
        String keywordUpper;
        String keyword;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 'f' || c == 'F') {
            keyword = "false";
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        for (int i = 1; i < length; i++) {
            if (this.pos + i >= this.limit && !fillBuffer(i + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i];
            if (!(c2 == keyword.charAt(i) || c2 == keywordUpper.charAt(i))) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = peeking;
        return peeking;
    }

    private int peekNumber() throws IOException {
        char c;
        char[] buffer2 = this.buffer;
        int p = this.pos;
        int l = this.limit;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            if (p + i == l) {
                if (i == buffer2.length) {
                    return 0;
                }
                if (fillBuffer(i + 1)) {
                    p = this.pos;
                    l = this.limit;
                }
            }
            c = buffer2[p + i];
            switch (c) {
                case Version.VERSION_43 /*{ENCODED_INT: 43}*/:
                    if (last == 5) {
                        last = 6;
                        break;
                    } else {
                        return 0;
                    }
                case Version.VERSION_45 /*{ENCODED_INT: 45}*/:
                    if (last == 0) {
                        negative = true;
                        last = 1;
                        break;
                    } else if (last == 5) {
                        last = 6;
                        break;
                    } else {
                        return 0;
                    }
                case Version.VERSION_46 /*{ENCODED_INT: 46}*/:
                    if (last == 2) {
                        last = 3;
                        break;
                    } else {
                        return 0;
                    }
                case Version.VERSION_69 /*{ENCODED_INT: 69}*/:
                case XMPError.BADSCHEMA:
                    if (last == 2 || last == 4) {
                        last = 5;
                        break;
                    } else {
                        return 0;
                    }
                default:
                    if (c >= '0' && c <= '9') {
                        if (last != 1 && last != 0) {
                            if (last != 2) {
                                if (last != 3) {
                                    if (last != 5 && last != 6) {
                                        break;
                                    } else {
                                        last = 7;
                                        break;
                                    }
                                } else {
                                    last = 4;
                                    break;
                                }
                            } else if (value != 0) {
                                long newValue = (10 * value) - ((long) (c - '0'));
                                fitsInLong &= value > -922337203685477580L || (value == -922337203685477580L && newValue < value);
                                value = newValue;
                                break;
                            } else {
                                return 0;
                            }
                        } else {
                            value = (long) (-(c - '0'));
                            last = 2;
                            break;
                        }
                    } else {
                        break;
                    }
                    break;
            }
            i++;
        }
        if (isLiteral(c)) {
            return 0;
        }
        if (last == 2 && fitsInLong && ((value != Long.MIN_VALUE || negative) && (value != 0 || !negative))) {
            if (!negative) {
                value = -value;
            }
            this.peekedLong = value;
            this.pos += i;
            this.peeked = 15;
            return 15;
        } else if (last != 2 && last != 4 && last != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i;
            this.peeked = 16;
            return 16;
        }
    }

    private boolean isLiteral(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
            case ' ':
            case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
            case Version.VERSION_58 /*{ENCODED_INT: 58}*/:
            case '[':
            case ']':
            case '{':
            case '}':
                break;
            default:
                return true;
            case Version.VERSION_35 /*{ENCODED_INT: 35}*/:
            case Version.VERSION_47 /*{ENCODED_INT: 47}*/:
            case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
            case Version.VERSION_61 /*{ENCODED_INT: 61}*/:
            case '\\':
                checkLenient();
                break;
        }
        return false;
    }

    public String nextName() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 14) {
            result = nextUnquotedValue();
        } else if (p == 12) {
            result = nextQuotedValue('\'');
        } else if (p == 13) {
            result = nextQuotedValue('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + peek() + locationString());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = result;
        return result;
    }

    public String nextString() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 10) {
            result = nextUnquotedValue();
        } else if (p == 8) {
            result = nextQuotedValue('\'');
        } else if (p == 9) {
            result = nextQuotedValue('\"');
        } else if (p == 11) {
            result = this.peekedString;
            this.peekedString = null;
        } else if (p == 15) {
            result = Long.toString(this.peekedLong);
        } else if (p == 16) {
            result = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw new IllegalStateException("Expected a string but was " + peek() + locationString());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean nextBoolean() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + peek() + locationString());
        }
    }

    public void nextNull() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + locationString());
    }

    public double nextDouble() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return (double) this.peekedLong;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9) {
            this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
        } else if (p == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (p != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + locationString());
        }
        this.peeked = 11;
        double result = Double.parseDouble(this.peekedString);
        if (this.lenient || (!Double.isNaN(result) && !Double.isInfinite(result))) {
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return result;
        }
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + result + locationString());
    }

    public long nextLong() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + peek() + locationString());
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        long result = (long) asDouble;
        if (((double) result) != asDouble) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result;
    }

    private String nextQuotedValue(char quote) throws IOException {
        char[] buffer2 = this.buffer;
        StringBuilder builder = null;
        do {
            int p = this.pos;
            int l = this.limit;
            int start = p;
            int p2 = p;
            while (p2 < l) {
                int p3 = p2 + 1;
                char c = buffer2[p2];
                if (c == quote) {
                    this.pos = p3;
                    int len = (p3 - start) - 1;
                    if (builder == null) {
                        return new String(buffer2, start, len);
                    }
                    builder.append(buffer2, start, len);
                    return builder.toString();
                }
                if (c == '\\') {
                    this.pos = p3;
                    int len2 = (p3 - start) - 1;
                    if (builder == null) {
                        builder = new StringBuilder(Math.max((len2 + 1) * 2, 16));
                    }
                    builder.append(buffer2, start, len2);
                    builder.append(readEscapeCharacter());
                    p3 = this.pos;
                    l = this.limit;
                    start = p3;
                } else if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = p3;
                }
                p2 = p3;
            }
            if (builder == null) {
                builder = new StringBuilder(Math.max((p2 - start) * 2, 16));
            }
            builder.append(buffer2, start, p2 - start);
            this.pos = p2;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() throws IOException {
        String result;
        StringBuilder builder = null;
        int i = 0;
        while (true) {
            if (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    case ' ':
                    case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
                    case Version.VERSION_58 /*{ENCODED_INT: 58}*/:
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case Version.VERSION_35 /*{ENCODED_INT: 35}*/:
                    case Version.VERSION_47 /*{ENCODED_INT: 47}*/:
                    case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                    case Version.VERSION_61 /*{ENCODED_INT: 61}*/:
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                }
            } else if (i >= this.buffer.length) {
                if (builder == null) {
                    builder = new StringBuilder(Math.max(i, 16));
                }
                builder.append(this.buffer, this.pos, i);
                this.pos += i;
                i = 0;
                if (!fillBuffer(1)) {
                }
            } else if (fillBuffer(i + 1)) {
            }
        }
        if (builder == null) {
            result = new String(this.buffer, this.pos, i);
        } else {
            result = builder.append(this.buffer, this.pos, i).toString();
        }
        this.pos += i;
        return result;
    }

    private void skipQuotedValue(char quote) throws IOException {
        char[] buffer2 = this.buffer;
        do {
            int p = this.pos;
            int l = this.limit;
            int p2 = p;
            while (p2 < l) {
                int p3 = p2 + 1;
                char c = buffer2[p2];
                if (c == quote) {
                    this.pos = p3;
                    return;
                }
                if (c == '\\') {
                    this.pos = p3;
                    readEscapeCharacter();
                    p3 = this.pos;
                    l = this.limit;
                } else if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = p3;
                }
                p2 = p3;
            }
            this.pos = p2;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    case ' ':
                    case Version.VERSION_44 /*{ENCODED_INT: 44}*/:
                    case Version.VERSION_58 /*{ENCODED_INT: 58}*/:
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        this.pos += i;
                        return;
                    case Version.VERSION_35 /*{ENCODED_INT: 35}*/:
                    case Version.VERSION_47 /*{ENCODED_INT: 47}*/:
                    case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
                    case Version.VERSION_61 /*{ENCODED_INT: 61}*/:
                    case '\\':
                        checkLenient();
                        this.pos += i;
                        return;
                    default:
                        i++;
                }
            }
            this.pos += i;
        } while (fillBuffer(1));
    }

    public int nextInt() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            int result = (int) this.peekedLong;
            if (this.peekedLong != ((long) result)) {
                throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return result;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : '\"');
            }
            try {
                int result2 = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result2;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + peek() + locationString());
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        int result3 = (int) asDouble;
        if (((double) result3) != asDouble) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    public void skipValue() throws IOException {
        int count = 0;
        do {
            int p = this.peeked;
            if (p == 0) {
                p = doPeek();
            }
            if (p == 3) {
                push(1);
                count++;
            } else if (p == 1) {
                push(3);
                count++;
            } else if (p == 4) {
                this.stackSize--;
                count--;
            } else if (p == 2) {
                this.stackSize--;
                count--;
            } else if (p == 14 || p == 10) {
                skipUnquotedValue();
            } else if (p == 8 || p == 12) {
                skipQuotedValue('\'');
            } else if (p == 9 || p == 13) {
                skipQuotedValue('\"');
            } else if (p == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (count != 0);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.pathNames[this.stackSize - 1] = "null";
    }

    private void push(int newTop) {
        if (this.stackSize == this.stack.length) {
            int[] newStack = new int[(this.stackSize * 2)];
            int[] newPathIndices = new int[(this.stackSize * 2)];
            String[] newPathNames = new String[(this.stackSize * 2)];
            System.arraycopy(this.stack, 0, newStack, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, newPathIndices, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, newPathNames, 0, this.stackSize);
            this.stack = newStack;
            this.pathIndices = newPathIndices;
            this.pathNames = newPathNames;
        }
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    private boolean fillBuffer(int minimum) throws IOException {
        char[] buffer2 = this.buffer;
        this.lineStart -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(buffer2, this.pos, buffer2, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int total = this.in.read(buffer2, this.limit, buffer2.length - this.limit);
            if (total == -1) {
                return false;
            }
            this.limit += total;
            if (this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && buffer2[0] == 65279) {
                this.pos++;
                this.lineStart++;
                minimum++;
            }
        } while (this.limit < minimum);
        return true;
    }

    private int nextNonWhitespace(boolean throwOnEof) throws IOException {
        char[] buffer2 = this.buffer;
        int p = this.pos;
        int l = this.limit;
        while (true) {
            if (p == l) {
                this.pos = p;
                if (fillBuffer(1)) {
                    p = this.pos;
                    l = this.limit;
                } else if (!throwOnEof) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + locationString());
                }
            }
            int p2 = p + 1;
            char c = buffer2[p];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = p2;
                p = p2;
            } else if (c == ' ' || c == '\r') {
                p = p2;
            } else if (c == '\t') {
                p = p2;
            } else if (c == '/') {
                this.pos = p2;
                if (p2 == l) {
                    this.pos--;
                    boolean charsLoaded = fillBuffer(2);
                    this.pos++;
                    if (!charsLoaded) {
                        return c;
                    }
                }
                checkLenient();
                switch (buffer2[this.pos]) {
                    case Version.VERSION_42 /*{ENCODED_INT: 42}*/:
                        this.pos++;
                        if (!skipTo("*/")) {
                            throw syntaxError("Unterminated comment");
                        }
                        p = this.pos + 2;
                        l = this.limit;
                        continue;
                    case Version.VERSION_47 /*{ENCODED_INT: 47}*/:
                        this.pos++;
                        skipToEndOfLine();
                        p = this.pos;
                        l = this.limit;
                        continue;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.pos = p2;
                checkLenient();
                skipToEndOfLine();
                p = this.pos;
                l = this.limit;
            } else {
                this.pos = p2;
                return c;
            }
        }
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                this.pos = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean skipTo(String toFind) throws IOException {
        int length = toFind.length();
        while (true) {
            if (this.pos + length > this.limit && !fillBuffer(length)) {
                return false;
            }
            if (this.buffer[this.pos] == '\n') {
                this.lineNumber++;
                this.lineStart = this.pos + 1;
            } else {
                for (int c = 0; c < length; c++) {
                    if (this.buffer[this.pos + c] == toFind.charAt(c)) {
                    }
                }
                return true;
            }
            this.pos++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }

    /* access modifiers changed from: package-private */
    public String locationString() {
        int line = this.lineNumber + 1;
        return " at line " + line + " column " + ((this.pos - this.lineStart) + 1) + " path " + getPath();
    }

    public String getPath() {
        StringBuilder result = new StringBuilder().append('$');
        int size = this.stackSize;
        for (int i = 0; i < size; i++) {
            switch (this.stack[i]) {
                case 1:
                case 2:
                    result.append('[').append(this.pathIndices[i]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    result.append('.');
                    if (this.pathNames[i] != null) {
                        result.append(this.pathNames[i]);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return result.toString();
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private char readEscapeCharacter() throws IOException {
        int i;
        if (this.pos != this.limit || fillBuffer(1)) {
            char[] cArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 + 1;
            char escaped = cArr[i2];
            switch (escaped) {
                case '\n':
                    this.lineNumber++;
                    this.lineStart = this.pos;
                    break;
                case Version.VERSION_34 /*{ENCODED_INT: 34}*/:
                case Version.VERSION_39 /*{ENCODED_INT: 39}*/:
                case Version.VERSION_47 /*{ENCODED_INT: 47}*/:
                case '\\':
                    break;
                case 'b':
                    return '\b';
                case XMPError.BADXPATH:
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.pos + 4 <= this.limit || fillBuffer(4)) {
                        char result = 0;
                        int i3 = this.pos;
                        int end = i3 + 4;
                        while (i3 < end) {
                            char c = this.buffer[i3];
                            char result2 = (char) (result << 4);
                            if (c >= '0' && c <= '9') {
                                i = c - '0';
                            } else if (c >= 'a' && c <= 'f') {
                                i = (c - 'a') + 10;
                            } else if (c < 'A' || c > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                            } else {
                                i = (c - 'A') + 10;
                            }
                            result = (char) (i + result2);
                            i3++;
                        }
                        this.pos += 4;
                        return result;
                    }
                    throw syntaxError("Unterminated escape sequence");
                default:
                    throw syntaxError("Invalid escape sequence");
            }
            return escaped;
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private IOException syntaxError(String message) throws IOException {
        throw new MalformedJsonException(message + locationString());
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        this.pos--;
        if (this.pos + NON_EXECUTE_PREFIX.length <= this.limit || fillBuffer(NON_EXECUTE_PREFIX.length)) {
            for (int i = 0; i < NON_EXECUTE_PREFIX.length; i++) {
                if (this.buffer[this.pos + i] != NON_EXECUTE_PREFIX[i]) {
                    return;
                }
            }
            this.pos += NON_EXECUTE_PREFIX.length;
        }
    }
}
