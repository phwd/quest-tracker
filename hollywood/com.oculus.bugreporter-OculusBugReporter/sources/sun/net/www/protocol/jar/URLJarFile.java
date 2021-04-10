package sun.net.www.protocol.jar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import sun.net.www.ParseUtil;

public class URLJarFile extends JarFile {
    private static int BUF_SIZE = 2048;
    private URLJarFileCloseController closeController;
    private Attributes superAttr;
    private Map<String, Attributes> superEntries;
    private Manifest superMan;

    public interface URLJarFileCloseController {
        void close(JarFile jarFile);
    }

    static JarFile getJarFile(URL url) throws IOException {
        return getJarFile(url, null);
    }

    static JarFile getJarFile(URL url, URLJarFileCloseController closeController2) throws IOException {
        if (isFileURL(url)) {
            return new URLJarFile(url, closeController2);
        }
        return retrieve(url, closeController2);
    }

    public URLJarFile(File file) throws IOException {
        this(file, (URLJarFileCloseController) null);
    }

    public URLJarFile(File file, URLJarFileCloseController closeController2) throws IOException {
        super(file, true, 5);
        this.closeController = null;
        this.closeController = closeController2;
    }

    private URLJarFile(URL url, URLJarFileCloseController closeController2) throws IOException {
        super(ParseUtil.decode(url.getFile()));
        this.closeController = null;
        this.closeController = closeController2;
    }

    private static boolean isFileURL(URL url) {
        if (!url.getProtocol().equalsIgnoreCase("file")) {
            return false;
        }
        String host = url.getHost();
        if (host == null || host.equals("") || host.equals("~") || host.equalsIgnoreCase("localhost")) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.zip.ZipFile
    public void finalize() throws IOException {
        close();
    }

    @Override // java.util.jar.JarFile, java.util.zip.ZipFile
    public ZipEntry getEntry(String name) {
        ZipEntry ze = super.getEntry(name);
        if (ze == null) {
            return null;
        }
        if (ze instanceof JarEntry) {
            return new URLJarFileEntry((JarEntry) ze);
        }
        throw new InternalError(((Object) super.getClass()) + " returned unexpected entry type " + ((Object) ze.getClass()));
    }

    @Override // java.util.jar.JarFile
    public Manifest getManifest() throws IOException {
        if (!isSuperMan()) {
            return null;
        }
        Manifest man = new Manifest();
        man.getMainAttributes().putAll((Map) this.superAttr.clone());
        if (this.superEntries != null) {
            Map<String, Attributes> entries = man.getEntries();
            for (String key : this.superEntries.keySet()) {
                entries.put(key, (Attributes) this.superEntries.get(key).clone());
            }
        }
        return man;
    }

    @Override // java.io.Closeable, java.util.zip.ZipFile, java.lang.AutoCloseable
    public void close() throws IOException {
        URLJarFileCloseController uRLJarFileCloseController = this.closeController;
        if (uRLJarFileCloseController != null) {
            uRLJarFileCloseController.close(this);
        }
        super.close();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized boolean isSuperMan() throws IOException {
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

    private static JarFile retrieve(URL url) throws IOException {
        return retrieve(url, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.jar.JarFile retrieve(java.net.URL r5, final sun.net.www.protocol.jar.URLJarFile.URLJarFileCloseController r6) throws java.io.IOException {
        /*
            r0 = 0
            java.net.URLConnection r1 = r5.openConnection()     // Catch:{ PrivilegedActionException -> 0x002a }
            java.io.InputStream r1 = r1.getInputStream()     // Catch:{ PrivilegedActionException -> 0x002a }
            sun.net.www.protocol.jar.URLJarFile$1 r2 = new sun.net.www.protocol.jar.URLJarFile$1     // Catch:{ all -> 0x001c }
            r2.<init>(r6)     // Catch:{ all -> 0x001c }
            java.lang.Object r2 = java.security.AccessController.doPrivileged(r2)     // Catch:{ all -> 0x001c }
            java.util.jar.JarFile r2 = (java.util.jar.JarFile) r2     // Catch:{ all -> 0x001c }
            r0 = r2
            if (r1 == 0) goto L_0x001a
            r1.close()
        L_0x001a:
            return r0
        L_0x001c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001e }
        L_0x001e:
            r3 = move-exception
            if (r1 == 0) goto L_0x0029
            r1.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0029:
            throw r3
        L_0x002a:
            r1 = move-exception
            java.lang.Exception r2 = r1.getException()
            java.io.IOException r2 = (java.io.IOException) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.net.www.protocol.jar.URLJarFile.retrieve(java.net.URL, sun.net.www.protocol.jar.URLJarFile$URLJarFileCloseController):java.util.jar.JarFile");
    }

    private class URLJarFileEntry extends JarEntry {
        private JarEntry je;

        URLJarFileEntry(JarEntry je2) {
            super(je2);
            this.je = je2;
        }

        @Override // java.util.jar.JarEntry
        public Attributes getAttributes() throws IOException {
            Map<String, Attributes> e;
            Attributes a;
            if (!URLJarFile.this.isSuperMan() || (e = URLJarFile.this.superEntries) == null || (a = e.get(getName())) == null) {
                return null;
            }
            return (Attributes) a.clone();
        }

        @Override // java.util.jar.JarEntry
        public Certificate[] getCertificates() {
            Certificate[] certs = this.je.getCertificates();
            if (certs == null) {
                return null;
            }
            return (Certificate[]) certs.clone();
        }

        @Override // java.util.jar.JarEntry
        public CodeSigner[] getCodeSigners() {
            CodeSigner[] csg = this.je.getCodeSigners();
            if (csg == null) {
                return null;
            }
            return (CodeSigner[]) csg.clone();
        }
    }
}
