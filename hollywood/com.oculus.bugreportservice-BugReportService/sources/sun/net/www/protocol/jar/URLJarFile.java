package sun.net.www.protocol.jar;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import sun.net.www.ParseUtil;

public class URLJarFile extends JarFile {
    private URLJarFileCloseController closeController = null;
    private Attributes superAttr;
    private Map superEntries;
    private Manifest superMan;

    public interface URLJarFileCloseController {
        void close(JarFile jarFile);
    }

    static JarFile getJarFile(URL url, URLJarFileCloseController uRLJarFileCloseController) {
        if (isFileURL(url)) {
            return new URLJarFile(url, uRLJarFileCloseController);
        }
        return retrieve(url, uRLJarFileCloseController);
    }

    public URLJarFile(File file, URLJarFileCloseController uRLJarFileCloseController) {
        super(file, true, 5);
        this.closeController = uRLJarFileCloseController;
    }

    private URLJarFile(URL url, URLJarFileCloseController uRLJarFileCloseController) {
        super(ParseUtil.decode(url.getFile()));
        this.closeController = uRLJarFileCloseController;
    }

    private static boolean isFileURL(URL url) {
        if (!url.getProtocol().equalsIgnoreCase("file")) {
            return false;
        }
        String host = url.getHost();
        return host == null || host.equals("") || host.equals("~") || host.equalsIgnoreCase("localhost");
    }

    @Override // java.util.jar.JarFile, java.util.zip.ZipFile
    public ZipEntry getEntry(String str) {
        ZipEntry entry = super.getEntry(str);
        if (entry == null) {
            return null;
        }
        if (entry instanceof JarEntry) {
            return new URLJarFileEntry((JarEntry) entry);
        }
        throw new InternalError(super.getClass() + " returned unexpected entry type " + entry.getClass());
    }

    @Override // java.util.jar.JarFile
    public Manifest getManifest() {
        if (!isSuperMan()) {
            return null;
        }
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().putAll((Map) this.superAttr.clone());
        if (this.superEntries != null) {
            Map entries = manifest.getEntries();
            for (String str : this.superEntries.keySet()) {
                entries.put(str, (Attributes) ((Attributes) this.superEntries.get(str)).clone());
            }
        }
        return manifest;
    }

    @Override // java.io.Closeable, java.util.zip.ZipFile, java.lang.AutoCloseable
    public void close() {
        URLJarFileCloseController uRLJarFileCloseController = this.closeController;
        if (uRLJarFileCloseController != null) {
            uRLJarFileCloseController.close(this);
        }
        super.close();
    }

    private synchronized boolean isSuperMan() {
        if (this.superMan == null) {
            this.superMan = super.getManifest();
        }
        if (this.superMan == null) {
            return false;
        }
        this.superAttr = this.superMan.getMainAttributes();
        this.superEntries = this.superMan.getEntries();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r1 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.jar.JarFile retrieve(java.net.URL r1, final sun.net.www.protocol.jar.URLJarFile.URLJarFileCloseController r2) {
        /*
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ PrivilegedActionException -> 0x0027 }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ PrivilegedActionException -> 0x0027 }
            sun.net.www.protocol.jar.URLJarFile$1 r0 = new sun.net.www.protocol.jar.URLJarFile$1     // Catch:{ all -> 0x0019 }
            r0.<init>(r2)     // Catch:{ all -> 0x0019 }
            java.lang.Object r2 = java.security.AccessController.doPrivileged(r0)     // Catch:{ all -> 0x0019 }
            java.util.jar.JarFile r2 = (java.util.jar.JarFile) r2     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x0018
            r1.close()
        L_0x0018:
            return r2
        L_0x0019:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001b }
        L_0x001b:
            r0 = move-exception
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ all -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x0026:
            throw r0
        L_0x0027:
            r1 = move-exception
            java.lang.Exception r1 = r1.getException()
            java.io.IOException r1 = (java.io.IOException) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.www.protocol.jar.URLJarFile.retrieve(java.net.URL, sun.net.www.protocol.jar.URLJarFile$URLJarFileCloseController):java.util.jar.JarFile");
    }

    private class URLJarFileEntry extends JarEntry {
        private JarEntry je;

        URLJarFileEntry(JarEntry jarEntry) {
            super(jarEntry);
            this.je = jarEntry;
        }
    }
}
