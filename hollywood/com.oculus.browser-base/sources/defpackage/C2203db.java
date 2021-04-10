package defpackage;

import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: db  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2203db {

    /* renamed from: a  reason: collision with root package name */
    public final File f9791a;
    public final File b;
    public final File c;

    public C2203db(File file) {
        this.f9791a = file;
        this.b = new File(file.getPath() + ".new");
        this.c = new File(file.getPath() + ".bak");
    }

    public static void c(File file, File file2) {
        if (file2.isDirectory() && !file2.delete()) {
            Log.e("AtomicFile", "Failed to delete file which is a directory " + file2);
        }
        if (!file.renameTo(file2)) {
            Log.e("AtomicFile", "Failed to rename " + file + " to " + file2);
        }
    }

    public void a(FileOutputStream fileOutputStream) {
        boolean z;
        try {
            fileOutputStream.getFD().sync();
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        if (!z) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        if (!this.b.delete()) {
            StringBuilder i = AbstractC2531fV.i("Failed to delete new file ");
            i.append(this.b);
            Log.e("AtomicFile", i.toString());
        }
    }

    public void b(FileOutputStream fileOutputStream) {
        boolean z;
        try {
            fileOutputStream.getFD().sync();
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        if (!z) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        c(this.b, this.f9791a);
    }

    public FileOutputStream d() {
        if (this.c.exists()) {
            c(this.c, this.f9791a);
        }
        try {
            return new FileOutputStream(this.b);
        } catch (FileNotFoundException unused) {
            if (this.b.getParentFile().mkdirs()) {
                try {
                    return new FileOutputStream(this.b);
                } catch (FileNotFoundException e) {
                    StringBuilder i = AbstractC2531fV.i("Failed to create new file ");
                    i.append(this.b);
                    throw new IOException(i.toString(), e);
                }
            } else {
                StringBuilder i2 = AbstractC2531fV.i("Failed to create directory for ");
                i2.append(this.b);
                throw new IOException(i2.toString());
            }
        }
    }
}
