package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.m;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public final class d extends m {

    final class a extends m.e {
        private final b[] a;

        /* renamed from: com.facebook.soloader.d$a$a  reason: collision with other inner class name */
        final class C0002a extends m.d {
            private int a;

            private C0002a() {
            }

            /* synthetic */ C0002a(a aVar, byte b2) {
                this();
            }

            @Override // com.facebook.soloader.m.d
            public final boolean a() {
                return this.a < a.this.a.length;
            }

            @Override // com.facebook.soloader.m.d
            public final m.c b() {
                b[] bVarArr = a.this.a;
                int i = this.a;
                this.a = i + 1;
                b bVar = bVarArr[i];
                FileInputStream fileInputStream = new FileInputStream(bVar.a);
                try {
                    return new m.c(bVar, fileInputStream);
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        }

        a(d dVar, m mVar) {
            boolean z;
            Context context = dVar.d;
            File file = new File("/data/local/tmp/exopackage/" + context.getPackageName() + "/native-libs/");
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            String[] a2 = SysUtil.a();
            for (String str : a2) {
                File file2 = new File(file, str);
                if (file2.isDirectory()) {
                    linkedHashSet.add(str);
                    File file3 = new File(file2, "metadata.txt");
                    if (file3.isFile()) {
                        FileReader fileReader = new FileReader(file3);
                        try {
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        bufferedReader.close();
                                        fileReader.close();
                                        break;
                                    } else if (readLine.length() != 0) {
                                        int indexOf = readLine.indexOf(32);
                                        if (indexOf != -1) {
                                            String str2 = readLine.substring(0, indexOf) + ".so";
                                            int size = arrayList.size();
                                            int i = 0;
                                            while (true) {
                                                if (i >= size) {
                                                    z = false;
                                                    break;
                                                } else if (((b) arrayList.get(i)).c.equals(str2)) {
                                                    z = true;
                                                    break;
                                                } else {
                                                    i++;
                                                }
                                            }
                                            if (!z) {
                                                String substring = readLine.substring(indexOf + 1);
                                                arrayList.add(new b(str2, substring, new File(file2, substring)));
                                            }
                                        } else {
                                            throw new RuntimeException("illegal line in exopackage metadata: [" + readLine + "]");
                                        }
                                    }
                                } catch (Throwable unused) {
                                }
                            }
                        } catch (Throwable unused2) {
                        }
                    } else {
                        continue;
                    }
                }
            }
            linkedHashSet.toArray(new String[linkedHashSet.size()]);
            this.a = (b[]) arrayList.toArray(new b[arrayList.size()]);
            return;
            throw th;
            throw th;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.b a() {
            return new m.b(this.a);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.m.e
        public final m.d b() {
            return new C0002a(this, (byte) 0);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b extends m.a {
        final File a;

        b(String str, String str2, File file) {
            super(str, str2);
            this.a = file;
        }
    }

    public d(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public final m.e a() {
        return new a(this, this);
    }
}
