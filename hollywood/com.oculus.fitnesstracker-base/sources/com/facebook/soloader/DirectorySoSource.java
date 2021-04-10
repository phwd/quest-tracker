package com.facebook.soloader;

import android.os.StrictMode;
import android.os.Trace;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DirectorySoSource extends SoSource {
    protected final int flags;
    protected final File soDirectory;

    public DirectorySoSource(File file, int i) {
        this.soDirectory = file;
        this.flags = i;
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
    }

    /* access modifiers changed from: protected */
    public final int loadLibraryFrom(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader != null) {
            File file2 = new File(this.soDirectory, str);
            ElfFileChannel elfFileChannel = null;
            if (!file2.exists()) {
                file2 = null;
            }
            boolean z = false;
            if (file2 == null) {
                Log.d("SoLoader", str + " not found on " + file.getCanonicalPath());
                return 0;
            }
            Log.d("SoLoader", str + " found on " + file.getCanonicalPath());
            if ((i & 1) == 0 || (this.flags & 2) == 0) {
                if ((this.flags & 1) != 0) {
                    z = true;
                }
                boolean equals = file2.getName().equals(str);
                if (z || !equals) {
                    try {
                        elfFileChannel = new ElfFileChannel(file2);
                    } catch (Throwable th) {
                        if (0 != 0) {
                            elfFileChannel.close();
                        }
                        throw th;
                    }
                }
                if (z) {
                    loadDependencies(str, elfFileChannel, i, threadPolicy);
                } else {
                    Log.d("SoLoader", "Not resolving dependencies for " + str);
                }
                if (equals) {
                    try {
                        SoLoader.sSoFileLoader.load(file2.getAbsolutePath(), i);
                    } catch (UnsatisfiedLinkError e) {
                        if (e.getMessage().contains("bad ELF magic")) {
                            Log.d("SoLoader", "Corrupted lib file detected");
                            if (elfFileChannel == null) {
                                return 3;
                            }
                            elfFileChannel.close();
                            return 3;
                        }
                        throw e;
                    }
                } else {
                    SoFileLoader soFileLoader = SoLoader.sSoFileLoader;
                    file2.getAbsolutePath();
                    soFileLoader.loadBytes$32a67ea();
                }
                if (elfFileChannel != null) {
                    elfFileChannel.close();
                }
                return 1;
            }
            Log.d("SoLoader", str + " loaded implicitly");
            return 2;
        }
        throw new IllegalStateException("SoLoader.init() not yet called");
    }

    private void loadDependencies(String str, ElfByteChannel elfByteChannel, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        String[] dependencies = getDependencies(str, elfByteChannel);
        Log.d("SoLoader", "Loading lib dependencies: " + Arrays.toString(dependencies));
        for (String str2 : dependencies) {
            if (!str2.startsWith("/")) {
                SoLoader.loadLibraryBySoName(str2, i | 1, threadPolicy);
            }
        }
    }

    private static String[] getDependencies(String str, ElfByteChannel elfByteChannel) throws IOException {
        String[] strArr;
        if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
            Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", str, "]");
        }
        try {
            if (elfByteChannel instanceof ElfFileChannel) {
                strArr = MinElf.extract_DT_NEEDED_with_retries((ElfFileChannel) elfByteChannel);
            } else {
                strArr = MinElf.extract_DT_NEEDED_no_retries(elfByteChannel);
            }
            return strArr;
        } finally {
            if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                Trace.endSection();
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        String str;
        try {
            str = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            str = this.soDirectory.getName();
        }
        return getClass().getName() + "[root = " + str + " flags = " + this.flags + ']';
    }
}
