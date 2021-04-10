package X;

import fi.iki.elonen.NanoHTTPD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.1eh  reason: invalid class name */
public class AnonymousClass1eh implements Iterable<String> {
    public final ArrayList<NanoHTTPD.Cookie> A00 = new ArrayList<>();
    public final HashMap<String, String> A01 = new HashMap<>();
    public final /* synthetic */ AnonymousClass1ea A02;

    public AnonymousClass1eh(AnonymousClass1ea r9, Map<String, String> map) {
        this.A02 = r9;
        String str = map.get("cookie");
        if (str != null) {
            for (String str2 : str.split(";")) {
                String[] split = str2.trim().split("=");
                if (split.length == 2) {
                    this.A01.put(split[0], split[1]);
                }
            }
        }
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return this.A01.keySet().iterator();
    }
}
