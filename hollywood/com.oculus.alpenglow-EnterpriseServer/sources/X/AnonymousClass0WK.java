package X;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

/* renamed from: X.0WK  reason: invalid class name */
public class AnonymousClass0WK extends AnonymousClass0Bd<Locale> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Locale locale) throws IOException {
        String locale2;
        Locale locale3 = locale;
        if (locale3 == null) {
            locale2 = null;
        } else {
            locale2 = locale3.toString();
        }
        r2.A0E(locale2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Locale A02(AnonymousClass0Fo r6) throws IOException {
        String str;
        String str2;
        String str3 = null;
        if (r6.A0D() == AnonymousClass007.A0I) {
            r6.A0L();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(r6.A0F(), "_");
        if (stringTokenizer.hasMoreElements()) {
            str = stringTokenizer.nextToken();
        } else {
            str = null;
        }
        if (stringTokenizer.hasMoreElements()) {
            str2 = stringTokenizer.nextToken();
        } else {
            str2 = null;
        }
        if (stringTokenizer.hasMoreElements()) {
            str3 = stringTokenizer.nextToken();
        }
        if (str2 == null) {
            if (str3 == null) {
                return new Locale(str);
            }
        } else if (str3 == null) {
            return new Locale(str, str2);
        }
        return new Locale(str, str2, str3);
    }
}
