package android.icu.text;

import java.text.ParsePosition;

public interface SymbolTable {
    char[] lookup(String str);

    UnicodeMatcher lookupMatcher(int i);

    String parseReference(String str, ParsePosition parsePosition, int i);
}
