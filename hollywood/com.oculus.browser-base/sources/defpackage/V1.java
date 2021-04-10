package defpackage;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* renamed from: V1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class V1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f9061a = Pattern.compile("@");

    public static String a(String str) {
        String[] split = f9061a.split(str);
        if (split.length != 2) {
            return str;
        }
        if ("googlemail.com".equalsIgnoreCase(split[1])) {
            split[1] = "gmail.com";
        }
        if ("gmail.com".equalsIgnoreCase(split[1])) {
            split[0] = split[0].replace(".", "");
        }
        return (split[0] + "@" + split[1]).toLowerCase(Locale.US);
    }

    public static Account b(String str) {
        return new Account(str, "com.google");
    }

    public static Account c(List list, String str) {
        String a2 = a(str);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Account account = (Account) it.next();
            if (a(account.name).equals(a2)) {
                return account;
            }
        }
        return null;
    }

    public static List d(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Account) it.next()).name);
        }
        return arrayList;
    }
}
