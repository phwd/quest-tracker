package org.chromium.base.library_loader;

import android.os.ParcelFileDescriptor;
import org.chromium.base.library_loader.Linker;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LegacyLinker extends Linker {
    public static void f(Linker.LibInfo libInfo) {
        String str = libInfo.F;
        if (!nativeUseSharedRelro(str, libInfo)) {
            AbstractC1220Ua0.f("LegacyLinker", "Could not use shared RELRO section for %s", str);
        }
    }

    public static native boolean nativeAddZipArchivePath(String str);

    public static native boolean nativeCreateSharedRelro(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo);

    public static native boolean nativeUseSharedRelro(String str, Linker.LibInfo libInfo);

    @Override // org.chromium.base.library_loader.Linker
    public void d(String str, boolean z) {
        Linker.LibInfo libInfo;
        boolean z2 = this.d;
        long j = z ? this.e : 0;
        String mapLibraryName = System.mapLibraryName(str);
        Linker.LibInfo libInfo2 = new Linker.LibInfo();
        if (nativeLoadLibrary(mapLibraryName, j, libInfo2)) {
            libInfo2.F = mapLibraryName;
            if (z2) {
                if (!nativeCreateSharedRelro(mapLibraryName, this.e, libInfo2)) {
                    AbstractC1220Ua0.f("LegacyLinker", "Could not create shared RELRO for %s at %x", mapLibraryName, Long.valueOf(this.e));
                    libInfo2.mRelroFd = -1;
                }
                this.c = libInfo2;
                f(libInfo2);
                this.g = 2;
                return;
            }
            this.f = true;
            while (true) {
                libInfo = this.c;
                if (libInfo != null) {
                    break;
                }
                try {
                    this.b.wait();
                } catch (InterruptedException unused) {
                }
            }
            f(libInfo);
            Linker.LibInfo libInfo3 = this.c;
            int i = libInfo3.mRelroFd;
            if (i >= 0) {
                O21.a(ParcelFileDescriptor.adoptFd(i));
                libInfo3.mRelroFd = -1;
            }
            this.c = null;
            this.g = 3;
            return;
        }
        String f = AbstractC2531fV.f("Unable to load library: ", mapLibraryName);
        AbstractC1220Ua0.a("LegacyLinker", f, new Object[0]);
        throw new UnsatisfiedLinkError(f);
    }

    @Override // org.chromium.base.library_loader.Linker
    public void e(String str) {
        synchronized (this.b) {
            b();
            nativeAddZipArchivePath(str);
        }
    }
}
