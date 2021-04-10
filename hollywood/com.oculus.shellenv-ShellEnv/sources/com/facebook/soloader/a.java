package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import com.facebook.soloader.e;
import com.facebook.soloader.m;
import java.io.File;
import java.util.zip.ZipEntry;

public final class a extends e {
    private final int f;

    /* renamed from: com.facebook.soloader.a$a  reason: collision with other inner class name */
    public class C0001a extends e.b {
        private File a;
        private final int b;

        C0001a(e eVar) {
            super(eVar);
            this.a = new File(a.this.d.getApplicationInfo().nativeLibraryDir);
            this.b = a.this.f;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.soloader.e.b
        public final boolean a(ZipEntry zipEntry, String str) {
            String name = zipEntry.getName();
            if (str.equals(a.this.e)) {
                a.this.e = null;
                String.format("allowing consideration of corrupted lib %s", str);
                return true;
            } else if ((this.b & 1) == 0) {
                StringBuilder sb = new StringBuilder("allowing consideration of ");
                sb.append(name);
                sb.append(": self-extraction preferred");
                return true;
            } else {
                File file = new File(this.a, str);
                if (!file.isFile()) {
                    String.format("allowing considering of %s: %s not in system lib dir", name, str);
                    return true;
                }
                long length = file.length();
                long size = zipEntry.getSize();
                if (length != size) {
                    String.format("allowing consideration of %s: sysdir file length is %s, but the file is %s bytes long in the APK", file, Long.valueOf(length), Long.valueOf(size));
                    return true;
                }
                StringBuilder sb2 = new StringBuilder("not allowing consideration of ");
                sb2.append(name);
                sb2.append(": deferring to libdir");
                return false;
            }
        }
    }

    public a(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.f = i;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m, com.facebook.soloader.e
    public final m.e a() {
        return new C0001a(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.m
    public final byte[] b() {
        File canonicalFile = this.b.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(SysUtil.a(this.d));
            if ((this.f & 1) == 0) {
                obtain.writeByte((byte) 0);
                return obtain.marshall();
            }
            String str = this.d.getApplicationInfo().nativeLibraryDir;
            if (str == null) {
                obtain.writeByte((byte) 1);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                return marshall;
            }
            File canonicalFile2 = new File(str).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                obtain.writeByte((byte) 1);
                byte[] marshall2 = obtain.marshall();
                obtain.recycle();
                return marshall2;
            }
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile2.getPath());
            obtain.writeLong(canonicalFile2.lastModified());
            byte[] marshall3 = obtain.marshall();
            obtain.recycle();
            return marshall3;
        } finally {
            obtain.recycle();
        }
    }
}
