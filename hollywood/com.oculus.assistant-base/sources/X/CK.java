package X;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CK {
    public static int A00(String str) {
        if (str != null) {
            Matcher matcher = Pattern.compile("^type_tag:([0-9a-zA-Z]{8});").matcher(str);
            if (matcher.find() && matcher.groupCount() == 1) {
                return (int) Long.parseLong(matcher.group(1), 16);
            }
        }
        return 0;
    }
}
