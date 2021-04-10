package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.m;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class e extends m {
    protected final File b;
    protected final String c;

    /* access modifiers changed from: package-private */
    public static final class a extends m.a implements Comparable {
        final ZipEntry a;
        final int b;

        a(String str, ZipEntry zipEntry, int i) {
            super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
            this.a = zipEntry;
            this.b = i;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            return this.c.compareTo(((a) obj).c);
        }
    }

    public class b extends m.e {
        private a[] a;
        private final ZipFile b;
        private final m c;

        final class a extends m.d {
            private int a;

            private a() {
            }

            /* synthetic */ a(b bVar, byte b2) {
                this();
            }

            @Override // com.facebook.soloader.m.d
            public final boolean a() {
                b.this.c();
                return this.a < b.this.a.length;
            }

            @Override // com.facebook.soloader.m.d
            public final m.c b() {
                b.this.c();
                a[] aVarArr = b.this.a;
                int i = this.a;
                this.a = i + 1;
                a aVar = aVarArr[i];
                InputStream inputStream = b.this.b.getInputStream(aVar.a);
                try {
                    return new m.c(aVar, inputStream);
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
        }

        b(m mVar) {
            this.b = new ZipFile(e.this.b);
            this.c = mVar;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.b a() {
            return new m.b(c());
        }

        /* access modifiers changed from: protected */
        public boolean a(ZipEntry zipEntry, String str) {
            return true;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.d b() {
            return new a(this, (byte) 0);
        }

        /* access modifiers changed from: package-private */
        public final a[] c() {
            if (this.a == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                HashMap hashMap = new HashMap();
                Pattern compile = Pattern.compile(e.this.c);
                String[] a2 = SysUtil.a();
                Enumeration<? extends ZipEntry> entries = this.b.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                    Matcher matcher = compile.matcher(zipEntry.getName());
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        int a3 = SysUtil.a(a2, group);
                        if (a3 >= 0) {
                            linkedHashSet.add(group);
                            a aVar = (a) hashMap.get(group2);
                            if (aVar == null || a3 < aVar.b) {
                                hashMap.put(group2, new a(group2, zipEntry, a3));
                            }
                        }
                    }
                }
                linkedHashSet.toArray(new String[linkedHashSet.size()]);
                a[] aVarArr = (a[]) hashMap.values().toArray(new a[hashMap.size()]);
                Arrays.sort(aVarArr);
                int i = 0;
                for (int i2 = 0; i2 < aVarArr.length; i2++) {
                    a aVar2 = aVarArr[i2];
                    if (a(aVar2.a, aVar2.c)) {
                        i++;
                    } else {
                        aVarArr[i2] = null;
                    }
                }
                a[] aVarArr2 = new a[i];
                int i3 = 0;
                for (a aVar3 : aVarArr) {
                    if (aVar3 != null) {
                        aVarArr2[i3] = aVar3;
                        i3++;
                    }
                }
                this.a = aVarArr2;
            }
            return this.a;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, com.facebook.soloader.m.e
        public void close() {
            this.b.close();
        }
    }

    public e(Context context, String str, File file, String str2) {
        super(context, str);
        this.b = file;
        this.c = str2;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public m.e a() {
        return new b(this);
    }
}
