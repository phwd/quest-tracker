package X;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: X.1fQ  reason: invalid class name */
public class AnonymousClass1fQ {
    public static final Pattern A04 = Pattern.compile("[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);
    public static final Pattern A05 = Pattern.compile("[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);
    public static final Pattern A06 = Pattern.compile("[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)", 2);
    public final String A00;
    public final String A01;
    public final String A02;
    public final String A03;

    public AnonymousClass1fQ(String str) {
        this.A02 = str;
        String str2 = "";
        String str3 = null;
        if (str != null) {
            Matcher matcher = A06.matcher(str);
            this.A01 = matcher.find() ? matcher.group(1) : str2;
            String str4 = null;
            Matcher matcher2 = A05.matcher(str);
            this.A03 = matcher2.find() ? matcher2.group(2) : str4;
        } else {
            this.A01 = str2;
            this.A03 = "UTF-8";
        }
        if ("multipart/form-data".equalsIgnoreCase(this.A01)) {
            Matcher matcher3 = A04.matcher(str);
            this.A00 = matcher3.find() ? matcher3.group(2) : str3;
        }
    }
}
