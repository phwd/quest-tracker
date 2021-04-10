package com.facebook.soloader;

import android.os.StrictMode;
import android.os.Trace;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class c extends k {
    protected final File a;
    private int b;

    public c(File file, int i) {
        this.a = file;
        this.b = i;
    }

    private static void a(File file, int i, StrictMode.ThreadPolicy threadPolicy) {
        String[] a2 = a(file);
        new StringBuilder("Loading lib dependencies: ").append(Arrays.toString(a2));
        for (String str : a2) {
            if (!str.startsWith("/")) {
                i.a(str, i | 1, threadPolicy);
            }
        }
    }

    private static String[] a(File file) {
        if (i.a) {
            g.a("SoLoader.getElfDependencies[", file.getName(), "]");
        }
        try {
            return g.a(file);
        } finally {
            if (i.a) {
                Trace.endSection();
            }
        }
    }

    @Override // com.facebook.soloader.k
    public int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return a(str, i, this.a, threadPolicy);
    }

    /* access modifiers changed from: protected */
    public final int a(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" not found on ");
            sb.append(file.getCanonicalPath());
            return 0;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(" found on ");
        sb2.append(file.getCanonicalPath());
        if ((i & 1) == 0 || (this.b & 2) == 0) {
            if ((this.b & 1) != 0) {
                a(file2, i, threadPolicy);
            } else {
                new StringBuilder("Not resolving dependencies for ").append(str);
            }
            try {
                i.b.a(file2.getAbsolutePath(), i);
                return 1;
            } catch (UnsatisfiedLinkError e) {
                if (e.getMessage().contains("bad ELF magic")) {
                    return 3;
                }
                throw e;
            }
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(" loaded implicitly");
            return 2;
        }
    }

    @Override // com.facebook.soloader.k
    public String toString() {
        String str;
        try {
            str = String.valueOf(this.a.getCanonicalPath());
        } catch (IOException unused) {
            str = this.a.getName();
        }
        return getClass().getName() + "[root = " + str + " flags = " + this.b + ']';
    }
}
