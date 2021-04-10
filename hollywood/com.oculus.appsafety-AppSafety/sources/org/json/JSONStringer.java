package org.json;

import android.icu.impl.PatternTokenizer;
import android.icu.text.PluralRules;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONStringer {
    private final String indent;
    final StringBuilder out;
    private final List<Scope> stack;

    /* access modifiers changed from: package-private */
    public enum Scope {
        EMPTY_ARRAY,
        NONEMPTY_ARRAY,
        EMPTY_OBJECT,
        DANGLING_KEY,
        NONEMPTY_OBJECT,
        NULL
    }

    public JSONStringer() {
        this.out = new StringBuilder();
        this.stack = new ArrayList();
        this.indent = null;
    }

    JSONStringer(int indentSpaces) {
        this.out = new StringBuilder();
        this.stack = new ArrayList();
        char[] indentChars = new char[indentSpaces];
        Arrays.fill(indentChars, ' ');
        this.indent = new String(indentChars);
    }

    public JSONStringer array() throws JSONException {
        return open(Scope.EMPTY_ARRAY, "[");
    }

    public JSONStringer endArray() throws JSONException {
        return close(Scope.EMPTY_ARRAY, Scope.NONEMPTY_ARRAY, "]");
    }

    public JSONStringer object() throws JSONException {
        return open(Scope.EMPTY_OBJECT, "{");
    }

    public JSONStringer endObject() throws JSONException {
        return close(Scope.EMPTY_OBJECT, Scope.NONEMPTY_OBJECT, "}");
    }

    /* access modifiers changed from: package-private */
    public JSONStringer open(Scope empty, String openBracket) throws JSONException {
        if (!this.stack.isEmpty() || this.out.length() <= 0) {
            beforeValue();
            this.stack.add(empty);
            this.out.append(openBracket);
            return this;
        }
        throw new JSONException("Nesting problem: multiple top-level roots");
    }

    /* access modifiers changed from: package-private */
    public JSONStringer close(Scope empty, Scope nonempty, String closeBracket) throws JSONException {
        Scope context = peek();
        if (context == nonempty || context == empty) {
            List<Scope> list = this.stack;
            list.remove(list.size() - 1);
            if (context == nonempty) {
                newline();
            }
            this.out.append(closeBracket);
            return this;
        }
        throw new JSONException("Nesting problem");
    }

    private Scope peek() throws JSONException {
        if (!this.stack.isEmpty()) {
            List<Scope> list = this.stack;
            return list.get(list.size() - 1);
        }
        throw new JSONException("Nesting problem");
    }

    private void replaceTop(Scope topOfStack) {
        List<Scope> list = this.stack;
        list.set(list.size() - 1, topOfStack);
    }

    public JSONStringer value(Object value) throws JSONException {
        if (this.stack.isEmpty()) {
            throw new JSONException("Nesting problem");
        } else if (value instanceof JSONArray) {
            ((JSONArray) value).writeTo(this);
            return this;
        } else if (value instanceof JSONObject) {
            ((JSONObject) value).writeTo(this);
            return this;
        } else {
            beforeValue();
            if (value == null || (value instanceof Boolean) || value == JSONObject.NULL) {
                this.out.append(value);
            } else if (value instanceof Number) {
                this.out.append(JSONObject.numberToString((Number) value));
            } else {
                string(value.toString());
            }
            return this;
        }
    }

    public JSONStringer value(boolean value) throws JSONException {
        if (!this.stack.isEmpty()) {
            beforeValue();
            this.out.append(value);
            return this;
        }
        throw new JSONException("Nesting problem");
    }

    public JSONStringer value(double value) throws JSONException {
        if (!this.stack.isEmpty()) {
            beforeValue();
            this.out.append(JSONObject.numberToString(Double.valueOf(value)));
            return this;
        }
        throw new JSONException("Nesting problem");
    }

    public JSONStringer value(long value) throws JSONException {
        if (!this.stack.isEmpty()) {
            beforeValue();
            this.out.append(value);
            return this;
        }
        throw new JSONException("Nesting problem");
    }

    private void string(String value) {
        this.out.append("\"");
        int length = value.length();
        for (int i = 0; i < length; i++) {
            char c = value.charAt(i);
            if (c == '\f') {
                this.out.append("\\f");
            } else if (c == '\r') {
                this.out.append("\\r");
            } else if (c == '\"' || c == '/' || c == '\\') {
                StringBuilder sb = this.out;
                sb.append(PatternTokenizer.BACK_SLASH);
                sb.append(c);
            } else {
                switch (c) {
                    case '\b':
                        this.out.append("\\b");
                        continue;
                    case '\t':
                        this.out.append("\\t");
                        continue;
                    case '\n':
                        this.out.append("\\n");
                        continue;
                    default:
                        if (c > 31) {
                            this.out.append(c);
                            break;
                        } else {
                            this.out.append(String.format("\\u%04x", Integer.valueOf(c)));
                            continue;
                        }
                }
            }
        }
        this.out.append("\"");
    }

    private void newline() {
        if (this.indent != null) {
            this.out.append("\n");
            for (int i = 0; i < this.stack.size(); i++) {
                this.out.append(this.indent);
            }
        }
    }

    public JSONStringer key(String name) throws JSONException {
        if (name != null) {
            beforeKey();
            string(name);
            return this;
        }
        throw new JSONException("Names must be non-null");
    }

    private void beforeKey() throws JSONException {
        Scope context = peek();
        if (context == Scope.NONEMPTY_OBJECT) {
            this.out.append(',');
        } else if (context != Scope.EMPTY_OBJECT) {
            throw new JSONException("Nesting problem");
        }
        newline();
        replaceTop(Scope.DANGLING_KEY);
    }

    private void beforeValue() throws JSONException {
        if (!this.stack.isEmpty()) {
            Scope context = peek();
            if (context == Scope.EMPTY_ARRAY) {
                replaceTop(Scope.NONEMPTY_ARRAY);
                newline();
            } else if (context == Scope.NONEMPTY_ARRAY) {
                this.out.append(',');
                newline();
            } else if (context == Scope.DANGLING_KEY) {
                this.out.append(this.indent == null ? ":" : PluralRules.KEYWORD_RULE_SEPARATOR);
                replaceTop(Scope.NONEMPTY_OBJECT);
            } else if (context != Scope.NULL) {
                throw new JSONException("Nesting problem");
            }
        }
    }

    public String toString() {
        if (this.out.length() == 0) {
            return null;
        }
        return this.out.toString();
    }
}
