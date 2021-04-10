package defpackage;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* renamed from: ZA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZA implements FilenameFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Pattern f9324a;

    public ZA(C1619aB aBVar, Pattern pattern) {
        this.f9324a = pattern;
    }

    public boolean accept(File file, String str) {
        return this.f9324a.matcher(str).find();
    }
}
