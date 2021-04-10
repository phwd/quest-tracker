package defpackage;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/* renamed from: aB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1619aB {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f9416a = Pattern.compile("\\.(dmp|forced)([0-9]*)(\\.try([0-9]+))\\z");
    public static final Comparator b = new YA();
    public final File c;

    static {
        Pattern.compile("^[^.]+-([^-,]+?)\\.");
        Pattern.compile("\\.dmp([0-9]*)\\z");
        Pattern.compile("\\.(dmp)([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.up([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.forced([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.skipped([0-9]*)(\\.try([0-9]+))\\z");
        Pattern.compile("\\.tmp\\z");
    }

    public C1619aB(File file) {
        Objects.requireNonNull(file, "Specified context cannot be null.");
        if (file.isDirectory()) {
            this.c = file;
            return;
        }
        throw new IllegalArgumentException(file.getAbsolutePath() + " is not a directory.");
    }

    public static int d(String str) {
        int e = e(str);
        if (e >= 0) {
            return e;
        }
        return 0;
    }

    public static int e(String str) {
        int lastIndexOf = str.lastIndexOf(".try");
        if (lastIndexOf < 0) {
            return -1;
        }
        String substring = str.substring(lastIndexOf + 4);
        try {
            int nextInt = new Scanner(substring).useDelimiter("[^0-9]+").nextInt();
            if (substring.indexOf(Integer.toString(nextInt)) == 0) {
                return nextInt;
            }
            return -1;
        } catch (NoSuchElementException unused) {
            return -1;
        }
    }

    public File a(String str) {
        File file = new File(b(), str);
        if (!file.exists()) {
            return file;
        }
        if (file.delete()) {
            return new File(b(), str);
        }
        StringBuilder i = AbstractC2531fV.i("Unable to delete previous logfile");
        i.append(file.getAbsolutePath());
        AbstractC1220Ua0.f("CrashFileManager", i.toString(), new Object[0]);
        return file;
    }

    public File b() {
        return new File(this.c, "Crash Reports");
    }

    public File[] c(Pattern pattern) {
        File b2 = b();
        File[] listFiles = b2.listFiles(pattern != null ? new ZA(this, pattern) : null);
        if (listFiles == null) {
            AbstractC1220Ua0.f("CrashFileManager", b2.getAbsolutePath() + " does not exist or is not a directory", new Object[0]);
            return new File[0];
        }
        Arrays.sort(listFiles, b);
        return listFiles;
    }
}
