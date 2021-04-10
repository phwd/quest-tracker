package X;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

/* renamed from: X.0dL  reason: invalid class name */
public class AnonymousClass0dL extends AnonymousClass13Y<Locale> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Locale locale) throws IOException {
        String obj;
        if (locale == null) {
            obj = null;
        } else {
            obj = locale.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Locale A02(AnonymousClass14I r6) throws IOException {
        String str;
        String str2;
        String str3 = null;
        if (r6.A0G() == AnonymousClass007.A09) {
            r6.A0P();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(r6.A0J(), "_");
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
