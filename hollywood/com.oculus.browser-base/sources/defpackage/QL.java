package defpackage;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: QL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QL implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String[] f8755a;

    public QL(UL ul, String[] strArr) {
        this.f8755a = strArr;
    }

    public boolean accept(File file, String str) {
        for (String str2 : this.f8755a) {
            if (str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }
}
