package X;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

/* renamed from: X.Sc  reason: case insensitive filesystem */
public class C0144Sc extends AbstractC0131Ob<Locale> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Locale locale) throws IOException {
        String obj;
        if (locale == null) {
            obj = null;
        } else {
            obj = locale.toString();
        }
        mmVar.A0G(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Locale A02(lk lkVar) throws IOException {
        String str;
        String str2;
        String str3 = null;
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(lkVar.A0J(), "_");
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
