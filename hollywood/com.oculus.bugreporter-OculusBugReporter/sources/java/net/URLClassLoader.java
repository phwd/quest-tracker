package java.net;

import java.io.Closeable;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import sun.misc.Resource;
import sun.misc.URLClassPath;
import sun.net.www.ParseUtil;
import sun.net.www.protocol.file.FileURLConnection;
import sun.security.util.SecurityConstants;

public class URLClassLoader extends SecureClassLoader implements Closeable {
    private final AccessControlContext acc;
    private WeakHashMap<Closeable, Void> closeables = new WeakHashMap<>();
    private final URLClassPath ucp;

    public URLClassLoader(URL[] urls, ClassLoader parent) {
        super(parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, this.acc);
    }

    URLClassLoader(URL[] urls, ClassLoader parent, AccessControlContext acc2) {
        super(parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = acc2;
        this.ucp = new URLClassPath(urls, acc2);
    }

    public URLClassLoader(URL[] urls) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, this.acc);
    }

    URLClassLoader(URL[] urls, AccessControlContext acc2) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = acc2;
        this.ucp = new URLClassPath(urls, acc2);
    }

    public URLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(parent);
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkCreateClassLoader();
        }
        this.acc = AccessController.getContext();
        this.ucp = new URLClassPath(urls, factory, this.acc);
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String name) {
        URL url = getResource(name);
        if (url == null) {
            return null;
        }
        try {
            URLConnection urlc = url.openConnection();
            InputStream is = urlc.getInputStream();
            if (urlc instanceof JarURLConnection) {
                JarFile jar = ((JarURLConnection) urlc).getJarFile();
                synchronized (this.closeables) {
                    if (!this.closeables.containsKey(jar)) {
                        this.closeables.put(jar, null);
                    }
                }
            } else if (urlc instanceof FileURLConnection) {
                synchronized (this.closeables) {
                    this.closeables.put(is, null);
                }
            }
            return is;
        } catch (IOException e) {
            return null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(new RuntimePermission("closeClassLoader"));
        }
        List<IOException> errors = this.ucp.closeLoaders();
        synchronized (this.closeables) {
            for (Closeable c : this.closeables.keySet()) {
                try {
                    c.close();
                } catch (IOException ioex) {
                    errors.add(ioex);
                }
            }
            this.closeables.clear();
        }
        if (!errors.isEmpty()) {
            IOException firstex = errors.remove(0);
            for (IOException error : errors) {
                firstex.addSuppressed(error);
            }
            throw firstex;
        }
    }

    /* access modifiers changed from: protected */
    public void addURL(URL url) {
        this.ucp.addURL(url);
    }

    public URL[] getURLs() {
        return this.ucp.getURLs();
    }

    /* access modifiers changed from: protected */
    @Override // java.lang.ClassLoader
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        try {
            Class<?> result = (Class) AccessController.doPrivileged(new PrivilegedExceptionAction<Class<?>>() {
                /* class java.net.URLClassLoader.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Class<?> run() throws ClassNotFoundException {
                    Resource res = URLClassLoader.this.ucp.getResource(name.replace('.', '/').concat(".class"), false);
                    if (res == null) {
                        return null;
                    }
                    try {
                        return URLClassLoader.this.defineClass(name, res);
                    } catch (IOException e) {
                        throw new ClassNotFoundException(name, e);
                    }
                }
            }, this.acc);
            if (result != null) {
                return result;
            }
            throw new ClassNotFoundException(name);
        } catch (PrivilegedActionException pae) {
            throw ((ClassNotFoundException) pae.getException());
        }
    }

    private Package getAndVerifyPackage(String pkgname, Manifest man, URL url) {
        Package pkg = getPackage(pkgname);
        if (pkg != null) {
            if (pkg.isSealed()) {
                if (!pkg.isSealed(url)) {
                    throw new SecurityException("sealing violation: package " + pkgname + " is sealed");
                }
            } else if (man != null && isSealed(pkgname, man)) {
                throw new SecurityException("sealing violation: can't seal package " + pkgname + ": already loaded");
            }
        }
        return pkg;
    }

    private void definePackageInternal(String pkgname, Manifest man, URL url) {
        if (getAndVerifyPackage(pkgname, man, url) != null) {
            return;
        }
        if (man != null) {
            try {
                definePackage(pkgname, man, url);
            } catch (IllegalArgumentException e) {
                if (getAndVerifyPackage(pkgname, man, url) == null) {
                    throw new AssertionError((Object) ("Cannot find package " + pkgname));
                }
            }
        } else {
            definePackage(pkgname, null, null, null, null, null, null, null);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Class<?> defineClass(String name, Resource res) throws IOException {
        System.nanoTime();
        int i = name.lastIndexOf(46);
        URL url = res.getCodeSourceURL();
        if (i != -1) {
            definePackageInternal(name.substring(0, i), res.getManifest(), url);
        }
        ByteBuffer bb = res.getByteBuffer();
        if (bb != null) {
            return defineClass(name, bb, new CodeSource(url, res.getCodeSigners()));
        }
        byte[] b = res.getBytes();
        return defineClass(name, b, 0, b.length, new CodeSource(url, res.getCodeSigners()));
    }

    /* access modifiers changed from: protected */
    public Package definePackage(String name, Manifest man, URL url) throws IllegalArgumentException {
        String implVendor;
        String implVersion;
        String implTitle;
        String specVendor;
        String specVersion;
        String specTitle;
        String specTitle2;
        URL sealBase;
        String specTitle3 = null;
        String specVersion2 = null;
        String specVendor2 = null;
        String implTitle2 = null;
        String implVersion2 = null;
        String implVendor2 = null;
        String sealed = null;
        Attributes attr = man.getAttributes(name.replace('.', '/').concat("/"));
        if (attr != null) {
            specTitle3 = attr.getValue(Attributes.Name.SPECIFICATION_TITLE);
            specVersion2 = attr.getValue(Attributes.Name.SPECIFICATION_VERSION);
            specVendor2 = attr.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            implTitle2 = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            implVersion2 = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            implVendor2 = attr.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            sealed = attr.getValue(Attributes.Name.SEALED);
        }
        Attributes attr2 = man.getMainAttributes();
        if (attr2 != null) {
            if (specTitle3 == null) {
                specTitle3 = attr2.getValue(Attributes.Name.SPECIFICATION_TITLE);
            }
            if (specVersion2 == null) {
                specVersion2 = attr2.getValue(Attributes.Name.SPECIFICATION_VERSION);
            }
            if (specVendor2 == null) {
                specVendor2 = attr2.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            }
            if (implTitle2 == null) {
                implTitle2 = attr2.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            }
            if (implVersion2 == null) {
                implVersion2 = attr2.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            }
            if (implVendor2 == null) {
                implVendor2 = attr2.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            }
            if (sealed == null) {
                specTitle = specTitle3;
                specVersion = specVersion2;
                specVendor = specVendor2;
                implTitle = implTitle2;
                implVersion = implVersion2;
                implVendor = implVendor2;
                specTitle2 = attr2.getValue(Attributes.Name.SEALED);
            } else {
                specTitle = specTitle3;
                specVersion = specVersion2;
                specVendor = specVendor2;
                implTitle = implTitle2;
                implVersion = implVersion2;
                implVendor = implVendor2;
                specTitle2 = sealed;
            }
        } else {
            specTitle = specTitle3;
            specVersion = specVersion2;
            specVendor = specVendor2;
            implTitle = implTitle2;
            implVersion = implVersion2;
            implVendor = implVendor2;
            specTitle2 = sealed;
        }
        if ("true".equalsIgnoreCase(specTitle2)) {
            sealBase = url;
        } else {
            sealBase = null;
        }
        return definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
    }

    private boolean isSealed(String name, Manifest man) {
        Attributes attr;
        Attributes attr2 = man.getAttributes(name.replace('.', '/').concat("/"));
        String sealed = null;
        if (attr2 != null) {
            sealed = attr2.getValue(Attributes.Name.SEALED);
        }
        if (sealed == null && (attr = man.getMainAttributes()) != null) {
            sealed = attr.getValue(Attributes.Name.SEALED);
        }
        return "true".equalsIgnoreCase(sealed);
    }

    @Override // java.lang.ClassLoader
    public URL findResource(final String name) {
        URL url = (URL) AccessController.doPrivileged(new PrivilegedAction<URL>() {
            /* class java.net.URLClassLoader.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public URL run() {
                return URLClassLoader.this.ucp.findResource(name, true);
            }
        }, this.acc);
        if (url != null) {
            return this.ucp.checkURL(url);
        }
        return null;
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> findResources(String name) throws IOException {
        final Enumeration<URL> e = this.ucp.findResources(name, true);
        return new Enumeration<URL>() {
            /* class java.net.URLClassLoader.AnonymousClass3 */
            private URL url = null;

            private boolean next() {
                if (this.url != null) {
                    return true;
                }
                do {
                    URL u = (URL) AccessController.doPrivileged(new PrivilegedAction<URL>() {
                        /* class java.net.URLClassLoader.AnonymousClass3.AnonymousClass1 */

                        @Override // java.security.PrivilegedAction
                        public URL run() {
                            if (!e.hasMoreElements()) {
                                return null;
                            }
                            return (URL) e.nextElement();
                        }
                    }, URLClassLoader.this.acc);
                    if (u == null) {
                        break;
                    }
                    this.url = URLClassLoader.this.ucp.checkURL(u);
                } while (this.url == null);
                if (this.url != null) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Enumeration
            public URL nextElement() {
                if (next()) {
                    URL u = this.url;
                    this.url = null;
                    return u;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return next();
            }
        };
    }

    /* access modifiers changed from: protected */
    @Override // java.security.SecureClassLoader
    public PermissionCollection getPermissions(CodeSource codesource) {
        final Permission p;
        URLConnection urlConnection;
        PermissionCollection perms = super.getPermissions(codesource);
        URL url = codesource.getLocation();
        try {
            urlConnection = url.openConnection();
            p = urlConnection.getPermission();
        } catch (IOException e) {
            p = null;
            urlConnection = null;
        }
        if (p instanceof FilePermission) {
            String path = p.getName();
            if (path.endsWith(File.separator)) {
                p = new FilePermission(path + "-", "read");
            }
        } else if (p != null || !url.getProtocol().equals("file")) {
            URL locUrl = url;
            if (urlConnection instanceof JarURLConnection) {
                locUrl = ((JarURLConnection) urlConnection).getJarFileURL();
            }
            String host = locUrl.getHost();
            if (host != null && host.length() > 0) {
                p = new SocketPermission(host, SecurityConstants.SOCKET_CONNECT_ACCEPT_ACTION);
            }
        } else {
            String path2 = ParseUtil.decode(url.getFile().replace('/', File.separatorChar));
            if (path2.endsWith(File.separator)) {
                path2 = path2 + "-";
            }
            p = new FilePermission(path2, "read");
        }
        if (p != null) {
            final SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    /* class java.net.URLClassLoader.AnonymousClass4 */

                    @Override // java.security.PrivilegedAction
                    public Void run() throws SecurityException {
                        sm.checkPermission(p);
                        return null;
                    }
                }, this.acc);
            }
            perms.add(p);
        }
        return perms;
    }

    public static URLClassLoader newInstance(final URL[] urls, final ClassLoader parent) {
        final AccessControlContext acc2 = AccessController.getContext();
        return (URLClassLoader) AccessController.doPrivileged(new PrivilegedAction<URLClassLoader>() {
            /* class java.net.URLClassLoader.AnonymousClass5 */

            @Override // java.security.PrivilegedAction
            public URLClassLoader run() {
                return new FactoryURLClassLoader(urls, parent, acc2);
            }
        });
    }

    public static URLClassLoader newInstance(final URL[] urls) {
        final AccessControlContext acc2 = AccessController.getContext();
        return (URLClassLoader) AccessController.doPrivileged(new PrivilegedAction<URLClassLoader>() {
            /* class java.net.URLClassLoader.AnonymousClass6 */

            @Override // java.security.PrivilegedAction
            public URLClassLoader run() {
                return new FactoryURLClassLoader(urls, acc2);
            }
        });
    }

    static {
        ClassLoader.registerAsParallelCapable();
    }
}
