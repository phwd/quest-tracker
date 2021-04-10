package android.icu.number;

import android.icu.text.PluralRules;

public class SkeletonSyntaxException extends IllegalArgumentException {
    private static final long serialVersionUID = 7733971331648360554L;

    public SkeletonSyntaxException(String message, CharSequence token) {
        super("Syntax error in skeleton string: " + message + PluralRules.KEYWORD_RULE_SEPARATOR + ((Object) token));
    }

    public SkeletonSyntaxException(String message, CharSequence token, Throwable cause) {
        super("Syntax error in skeleton string: " + message + PluralRules.KEYWORD_RULE_SEPARATOR + ((Object) token), cause);
    }
}
