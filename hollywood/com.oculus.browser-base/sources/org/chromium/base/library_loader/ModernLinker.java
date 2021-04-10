package org.chromium.base.library_loader;

import android.os.ParcelFileDescriptor;
import org.chromium.base.library_loader.Linker;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ModernLinker extends Linker {
    public static native int nativeGetRelroSharingResult();

    public static native boolean nativeLoadLibrary(String str, long j, Linker.LibInfo libInfo, boolean z);

    public static native boolean nativeUseRelros(Linker.LibInfo libInfo);

    @Override // org.chromium.base.library_loader.Linker
    public void a(boolean z) {
        Linker.LibInfo libInfo = this.c;
        if (libInfo.mRelroFd != -1) {
            long j = libInfo.mLoadSize;
            nativeUseRelros(libInfo);
            Linker.LibInfo libInfo2 = this.c;
            int i = libInfo2.mRelroFd;
            if (i >= 0) {
                O21.a(ParcelFileDescriptor.adoptFd(i));
                libInfo2.mRelroFd = -1;
            }
            AbstractC3100ip1.f10165a.a("ChromiumAndroidLinker.RelroAvailableImmediately", z);
            AbstractC3364kK0.g("ChromiumAndroidLinker.RelroSharingStatus", nativeGetRelroSharingResult(), 3);
        }
    }

    @Override // org.chromium.base.library_loader.Linker
    public void d(String str, boolean z) {
        boolean z2 = true;
        if (!"monochrome".equals(str)) {
            AbstractC1220Ua0.d("ModernLinker", "loadLibraryImpl: %s, %b", str, Boolean.valueOf(z));
        }
        String mapLibraryName = System.mapLibraryName(str);
        boolean z3 = !z;
        boolean z4 = z && this.d;
        long j = z ? this.e : 0;
        if (z3) {
            this.g = 3;
        } else if (z4) {
            Linker.LibInfo libInfo = new Linker.LibInfo();
            libInfo.F = mapLibraryName;
            if (!nativeLoadLibrary(mapLibraryName, j, libInfo, true)) {
                AbstractC1220Ua0.a("ModernLinker", "Unable to load with ModernLinker, using the system linker instead", new Object[0]);
                libInfo.mRelroFd = -1;
            }
            this.c = libInfo;
            if (libInfo.mRelroFd == -1) {
                z2 = false;
            }
            AbstractC3100ip1.f10165a.a("ChromiumAndroidLinker.RelroProvidedSuccessfully", z2);
            this.g = 2;
        } else if (nativeLoadLibrary(mapLibraryName, j, new Linker.LibInfo(), false)) {
            this.g = 3;
        } else {
            f(String.format("Unable to load library: %s", mapLibraryName));
            throw null;
        }
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError unused) {
            if (z3 || z4) {
                f("Cannot load without relro sharing");
                throw null;
            } else {
                f("Unable to load the library a second time with the system linker");
                throw null;
            }
        }
    }

    public final void f(String str) {
        this.g = 1;
        AbstractC1220Ua0.a("ModernLinker", str, new Object[0]);
        throw new UnsatisfiedLinkError(str);
    }
}
