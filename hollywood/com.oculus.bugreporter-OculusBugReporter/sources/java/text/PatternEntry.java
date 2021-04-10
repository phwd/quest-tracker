package java.text;

import android.icu.impl.PatternTokenizer;

class PatternEntry {
    static final int RESET = -2;
    static final int UNSET = -1;
    String chars;
    String extension;
    int strength = -1;

    public void appendQuotedExtension(StringBuffer toAddTo) {
        appendQuoted(this.extension, toAddTo);
    }

    public void appendQuotedChars(StringBuffer toAddTo) {
        appendQuoted(this.chars, toAddTo);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.chars.equals(((PatternEntry) obj).chars);
    }

    public int hashCode() {
        return this.chars.hashCode();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        addToBuffer(result, true, false, null);
        return result.toString();
    }

    /* access modifiers changed from: package-private */
    public final int getStrength() {
        return this.strength;
    }

    /* access modifiers changed from: package-private */
    public final String getExtension() {
        return this.extension;
    }

    /* access modifiers changed from: package-private */
    public final String getChars() {
        return this.chars;
    }

    /* access modifiers changed from: package-private */
    public void addToBuffer(StringBuffer toAddTo, boolean showExtension, boolean showWhiteSpace, PatternEntry lastEntry) {
        if (showWhiteSpace && toAddTo.length() > 0) {
            if (this.strength == 0 || lastEntry != null) {
                toAddTo.append('\n');
            } else {
                toAddTo.append(' ');
            }
        }
        if (lastEntry != null) {
            toAddTo.append('&');
            if (showWhiteSpace) {
                toAddTo.append(' ');
            }
            lastEntry.appendQuotedChars(toAddTo);
            appendQuotedExtension(toAddTo);
            if (showWhiteSpace) {
                toAddTo.append(' ');
            }
        }
        int i = this.strength;
        if (i == -2) {
            toAddTo.append('&');
        } else if (i == -1) {
            toAddTo.append('?');
        } else if (i == 0) {
            toAddTo.append('<');
        } else if (i == 1) {
            toAddTo.append(';');
        } else if (i == 2) {
            toAddTo.append(',');
        } else if (i == 3) {
            toAddTo.append('=');
        }
        if (showWhiteSpace) {
            toAddTo.append(' ');
        }
        appendQuoted(this.chars, toAddTo);
        if (showExtension && this.extension.length() != 0) {
            toAddTo.append('/');
            appendQuoted(this.extension, toAddTo);
        }
    }

    static void appendQuoted(String chars2, StringBuffer toAddTo) {
        boolean inQuote = false;
        char ch = chars2.charAt(0);
        if (Character.isSpaceChar(ch)) {
            inQuote = true;
            toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
        } else if (isSpecialChar(ch)) {
            inQuote = true;
            toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
        } else {
            if (!(ch == '\t' || ch == '\n' || ch == '\f' || ch == '\r' || ch == 16)) {
                if (ch == '\'') {
                    inQuote = true;
                    toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
                } else if (ch != '@') {
                    if (0 != 0) {
                        inQuote = false;
                        toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
                    }
                }
            }
            inQuote = true;
            toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
        }
        toAddTo.append(chars2);
        if (inQuote) {
            toAddTo.append(PatternTokenizer.SINGLE_QUOTE);
        }
    }

    PatternEntry(int strength2, StringBuffer chars2, StringBuffer extension2) {
        String str = "";
        this.chars = str;
        this.extension = str;
        this.strength = strength2;
        this.chars = chars2.toString();
        this.extension = extension2.length() > 0 ? extension2.toString() : str;
    }

    static class Parser {
        private int i;
        private StringBuffer newChars = new StringBuffer();
        private StringBuffer newExtension = new StringBuffer();
        private String pattern;

        public Parser(String pattern2) {
            this.pattern = pattern2;
            this.i = 0;
        }

        /* JADX WARNING: Removed duplicated region for block: B:64:0x0128 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x012a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.text.PatternEntry next() throws java.text.ParseException {
            /*
            // Method dump skipped, instructions count: 378
            */
            throw new UnsupportedOperationException("Method not decompiled: java.text.PatternEntry.Parser.next():java.text.PatternEntry");
        }
    }

    static boolean isSpecialChar(char ch) {
        return ch == ' ' || (ch <= '/' && ch >= '\"') || ((ch <= '?' && ch >= ':') || ((ch <= '`' && ch >= '[') || (ch <= '~' && ch >= '{')));
    }
}
