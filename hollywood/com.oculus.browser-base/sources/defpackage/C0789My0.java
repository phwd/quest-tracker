package defpackage;

import java.util.ArrayList;
import java.util.List;

/* renamed from: My0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0789My0 {

    /* renamed from: a  reason: collision with root package name */
    public final List f8515a = new ArrayList();

    public C0789My0(String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!z && charAt == '\\') {
                z = true;
            } else if (z || charAt != '*') {
                sb.append(charAt);
                z = false;
            } else {
                this.f8515a.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (!z) {
            this.f8515a.add(sb.toString());
            return;
        }
        throw new C0728Ly0("Unmatched escape symbol at the end", str, null);
    }
}
