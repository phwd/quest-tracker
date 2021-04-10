package org.json;

public class JSONTokener {
    private final String in;
    private int pos;

    public JSONTokener(String str) {
        if (str != null && str.startsWith("﻿")) {
            str = str.substring(1);
        }
        this.in = str;
    }

    public Object nextValue() {
        int nextCleanInternal = nextCleanInternal();
        if (nextCleanInternal == -1) {
            throw syntaxError("End of input");
        } else if (nextCleanInternal == 34 || nextCleanInternal == 39) {
            return nextString((char) nextCleanInternal);
        } else {
            if (nextCleanInternal == 91) {
                return readArray();
            }
            if (nextCleanInternal == 123) {
                return readObject();
            }
            this.pos--;
            return readLiteral();
        }
    }

    private int nextCleanInternal() {
        while (this.pos < this.in.length()) {
            String str = this.in;
            int i = this.pos;
            this.pos = i + 1;
            char charAt = str.charAt(i);
            if (!(charAt == '\t' || charAt == '\n' || charAt == '\r' || charAt == ' ')) {
                if (charAt == '#') {
                    skipToEndOfLine();
                } else if (charAt != '/' || this.pos == this.in.length()) {
                    return charAt;
                } else {
                    char charAt2 = this.in.charAt(this.pos);
                    if (charAt2 == '*') {
                        this.pos++;
                        int indexOf = this.in.indexOf("*/", this.pos);
                        if (indexOf != -1) {
                            this.pos = indexOf + 2;
                        } else {
                            throw syntaxError("Unterminated comment");
                        }
                    } else if (charAt2 != '/') {
                        return charAt;
                    } else {
                        this.pos++;
                        skipToEndOfLine();
                    }
                }
            }
        }
        return -1;
    }

    private void skipToEndOfLine() {
        while (this.pos < this.in.length()) {
            char charAt = this.in.charAt(this.pos);
            if (charAt == '\r' || charAt == '\n') {
                this.pos++;
                return;
            }
            this.pos++;
        }
    }

    public String nextString(char c) {
        int i = this.pos;
        StringBuilder sb = null;
        while (this.pos < this.in.length()) {
            String str = this.in;
            int i2 = this.pos;
            this.pos = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt == c) {
                if (sb != null) {
                    sb.append((CharSequence) this.in, i, this.pos - 1);
                    return sb.toString();
                }
                new String(this.in.substring(i, this.pos - 1));
                throw null;
            } else if (charAt == '\\') {
                if (this.pos != this.in.length()) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append((CharSequence) this.in, i, this.pos - 1);
                    sb.append(readEscapeCharacter());
                    i = this.pos;
                } else {
                    throw syntaxError("Unterminated escape sequence");
                }
            }
        }
        throw syntaxError("Unterminated string");
    }

    private char readEscapeCharacter() {
        String str = this.in;
        int i = this.pos;
        this.pos = i + 1;
        char charAt = str.charAt(i);
        if (charAt == 'b') {
            return '\b';
        }
        if (charAt == 'f') {
            return '\f';
        }
        if (charAt == 'n') {
            return '\n';
        }
        if (charAt == 'r') {
            return '\r';
        }
        if (charAt == 't') {
            return '\t';
        }
        if (charAt != 'u') {
            return charAt;
        }
        if (this.pos + 4 <= this.in.length()) {
            String str2 = this.in;
            int i2 = this.pos;
            String substring = str2.substring(i2, i2 + 4);
            this.pos += 4;
            try {
                return (char) Integer.parseInt(substring, 16);
            } catch (NumberFormatException unused) {
                throw this.syntaxError("Invalid escape sequence: " + substring);
            }
        } else {
            throw syntaxError("Unterminated escape sequence");
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0086 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object readLiteral() {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.readLiteral():java.lang.Object");
    }

    private String nextToInternal(String str) {
        int i = this.pos;
        while (this.pos < this.in.length()) {
            char charAt = this.in.charAt(this.pos);
            if (charAt == '\r' || charAt == '\n' || str.indexOf(charAt) != -1) {
                return this.in.substring(i, this.pos);
            }
            this.pos++;
        }
        return this.in.substring(i);
    }

    private JSONObject readObject() {
        JSONObject jSONObject = new JSONObject();
        int nextCleanInternal = nextCleanInternal();
        if (nextCleanInternal == 125) {
            return jSONObject;
        }
        if (nextCleanInternal != -1) {
            this.pos--;
        }
        while (true) {
            Object nextValue = nextValue();
            if (nextValue instanceof String) {
                int nextCleanInternal2 = nextCleanInternal();
                if (nextCleanInternal2 == 58 || nextCleanInternal2 == 61) {
                    if (this.pos < this.in.length() && this.in.charAt(this.pos) == '>') {
                        this.pos++;
                    }
                    jSONObject.put((String) nextValue, nextValue());
                    int nextCleanInternal3 = nextCleanInternal();
                    if (nextCleanInternal3 != 44 && nextCleanInternal3 != 59) {
                        if (nextCleanInternal3 == 125) {
                            return jSONObject;
                        }
                        throw syntaxError("Unterminated object");
                    }
                } else {
                    throw syntaxError("Expected ':' after " + nextValue);
                }
            } else if (nextValue == null) {
                throw syntaxError("Names cannot be null");
            } else {
                throw syntaxError("Names must be strings, but " + nextValue + " is of type " + nextValue.getClass().getName());
            }
        }
    }

    private JSONArray readArray() {
        JSONArray jSONArray = new JSONArray();
        boolean z = false;
        while (true) {
            int nextCleanInternal = nextCleanInternal();
            if (nextCleanInternal != -1) {
                if (nextCleanInternal == 44 || nextCleanInternal == 59) {
                    jSONArray.put(null);
                } else if (nextCleanInternal != 93) {
                    this.pos--;
                    jSONArray.put(nextValue());
                    int nextCleanInternal2 = nextCleanInternal();
                    if (!(nextCleanInternal2 == 44 || nextCleanInternal2 == 59)) {
                        if (nextCleanInternal2 == 93) {
                            return jSONArray;
                        }
                        throw syntaxError("Unterminated array");
                    }
                } else {
                    if (z) {
                        jSONArray.put(null);
                    }
                    return jSONArray;
                }
                z = true;
            } else {
                throw syntaxError("Unterminated array");
            }
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + this);
    }

    public String toString() {
        return " at character " + this.pos + " of " + this.in;
    }
}
