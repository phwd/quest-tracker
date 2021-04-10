package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Dn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dn1 {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f7910a;
    public static Dn1 b;

    public static Dn1 a() {
        if (b == null) {
            b = new Dn1();
        }
        return b;
    }

    public static SharedPreferences b() {
        if (f7910a == null) {
            f7910a = ContextUtils.getApplicationContext().getSharedPreferences("trendy_terms", 0);
        }
        return f7910a;
    }

    public static void c(Profile profile) {
        if (AbstractC2793h01.i.c()) {
            PostTask.b(C3070if1.b, new Bn1(profile, new An1()), 0);
        }
    }

    public static List d(Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            String trim = readLine.trim();
            if (trim.startsWith("<title>")) {
                String trim2 = trim.replace("<title>", "").replace("</title>", "").trim();
                if (!TextUtils.isEmpty(trim2)) {
                    arrayList.add(trim2);
                }
            }
        }
        bufferedReader.close();
        if (arrayList.size() <= 1) {
            return null;
        }
        arrayList.remove(0);
        return arrayList;
    }

    public static void e(List list) {
        SharedPreferences.Editor edit = b().edit();
        edit.putInt("count", list.size());
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            edit.putString("term_" + i, (String) it.next());
            i++;
        }
        edit.apply();
        list.toString();
    }
}
