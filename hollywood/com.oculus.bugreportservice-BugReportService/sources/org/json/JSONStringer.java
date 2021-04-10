package org.json;

import java.util.ArrayList;
import java.util.List;

public class JSONStringer {
    private final String indent = null;
    final StringBuilder out = new StringBuilder();
    private final List stack = new ArrayList();

    /* access modifiers changed from: package-private */
    public enum Scope {
        EMPTY_ARRAY,
        NONEMPTY_ARRAY,
        EMPTY_OBJECT,
        DANGLING_KEY,
        NONEMPTY_OBJECT,
        NULL
    }

    public JSONStringer array() {
        open(Scope.EMPTY_ARRAY, "[");
        return this;
    }

    public JSONStringer endArray() {
        close(Scope.EMPTY_ARRAY, Scope.NONEMPTY_ARRAY, "]");
        return this;
    }

    public JSONStringer object() {
        open(Scope.EMPTY_OBJECT, "{");
        return this;
    }

    public JSONStringer endObject() {
        close(Scope.EMPTY_OBJECT, Scope.NONEMPTY_OBJECT, "}");
        return this;
    }

    /* access modifiers changed from: package-private */
    public JSONStringer open(Scope scope, String str) {
        if (!this.stack.isEmpty() || this.out.length() <= 0) {
            beforeValue();
            this.stack.add(scope);
            this.out.append(str);
            return this;
        }
        throw new JSONException("Nesting problem: multiple top-level roots");
    }

    /* access modifiers changed from: package-private */
    public JSONStringer close(Scope scope, Scope scope2, String str) {
        Scope peek = peek();
        if (peek == scope2 || peek == scope) {
            List list = this.stack;
            list.remove(list.size() - 1);
            if (peek == scope2) {
                newline();
            }
            this.out.append(str);
            return this;
        }
        throw new JSONException("Nesting problem");
    }

    private Scope peek() {
        if (!this.stack.isEmpty()) {
            List list = this.stack;
            return (Scope) list.get(list.size() - 1);
        }
        throw new JSONException("Nesting problem");
    }

    private void replaceTop(Scope scope) {
        List list = this.stack;
        list.set(list.size() - 1, scope);
    }

    public JSONStringer value(Object obj) {
        if (this.stack.isEmpty()) {
            throw new JSONException("Nesting problem");
        } else if (obj instanceof JSONArray) {
            ((JSONArray) obj).writeTo(this);
            return this;
        } else if (obj instanceof JSONObject) {
            ((JSONObject) obj).writeTo(this);
            return this;
        } else {
            beforeValue();
            if (obj == null || (obj instanceof Boolean) || obj == JSONObject.NULL) {
                this.out.append(obj);
            } else if (obj instanceof Number) {
                this.out.append(JSONObject.numberToString((Number) obj));
            } else {
                string(obj.toString());
            }
            return this;
        }
    }

    private void string(String str) {
        this.out.append("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\f') {
                this.out.append("\\f");
            } else if (charAt == '\r') {
                this.out.append("\\r");
            } else if (charAt == '\"' || charAt == '/' || charAt == '\\') {
                StringBuilder sb = this.out;
                sb.append('\\');
                sb.append(charAt);
            } else {
                switch (charAt) {
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
                        if (charAt > 31) {
                            this.out.append(charAt);
                            break;
                        } else {
                            this.out.append(String.format("\\u%04x", Integer.valueOf(charAt)));
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

    public JSONStringer key(String str) {
        if (str != null) {
            beforeKey();
            string(str);
            return this;
        }
        throw new JSONException("Names must be non-null");
    }

    private void beforeKey() {
        Scope peek = peek();
        if (peek == Scope.NONEMPTY_OBJECT) {
            this.out.append(',');
        } else if (peek != Scope.EMPTY_OBJECT) {
            throw new JSONException("Nesting problem");
        }
        newline();
        replaceTop(Scope.DANGLING_KEY);
    }

    private void beforeValue() {
        if (!this.stack.isEmpty()) {
            Scope peek = peek();
            if (peek == Scope.EMPTY_ARRAY) {
                replaceTop(Scope.NONEMPTY_ARRAY);
                newline();
            } else if (peek == Scope.NONEMPTY_ARRAY) {
                this.out.append(',');
                newline();
            } else if (peek == Scope.DANGLING_KEY) {
                this.out.append(this.indent == null ? ":" : ": ");
                replaceTop(Scope.NONEMPTY_OBJECT);
            } else if (peek != Scope.NULL) {
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
