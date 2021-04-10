package java.lang;

import dalvik.system.VMRuntime;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import sun.net.www.ParseUtil;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class Package implements AnnotatedElement {
    private static Map<String, Manifest> mans = new HashMap(10);
    private static Map<String, Package> pkgs = new HashMap(31);
    private static Map<String, URL> urls = new HashMap(10);
    private final String implTitle;
    private final String implVendor;
    private final String implVersion;
    private final transient ClassLoader loader;
    private transient Class<?> packageInfo;
    private final String pkgName;
    private final URL sealBase;
    private final String specTitle;
    private final String specVendor;
    private final String specVersion;

    private static native String getSystemPackage0(String str);

    private static native String[] getSystemPackages0();

    public String getName() {
        return this.pkgName;
    }

    public String getSpecificationTitle() {
        return this.specTitle;
    }

    public String getSpecificationVersion() {
        return this.specVersion;
    }

    public String getSpecificationVendor() {
        return this.specVendor;
    }

    public String getImplementationTitle() {
        return this.implTitle;
    }

    public String getImplementationVersion() {
        return this.implVersion;
    }

    public String getImplementationVendor() {
        return this.implVendor;
    }

    public boolean isSealed() {
        return this.sealBase != null;
    }

    public boolean isSealed(URL url) {
        return url.equals(this.sealBase);
    }

    public boolean isCompatibleWith(String desired) throws NumberFormatException {
        String str = this.specVersion;
        if (str == null || str.length() < 1) {
            throw new NumberFormatException("Empty version string");
        }
        String[] sa = this.specVersion.split("\\.", -1);
        int[] si = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            si[i] = Integer.parseInt(sa[i]);
            if (si[i] < 0) {
                throw NumberFormatException.forInputString("" + si[i]);
            }
        }
        String[] da = desired.split("\\.", -1);
        int[] di = new int[da.length];
        for (int i2 = 0; i2 < da.length; i2++) {
            di[i2] = Integer.parseInt(da[i2]);
            if (di[i2] < 0) {
                throw NumberFormatException.forInputString("" + di[i2]);
            }
        }
        int len = Math.max(di.length, si.length);
        int i3 = 0;
        while (i3 < len) {
            int d = i3 < di.length ? di[i3] : 0;
            int s = i3 < si.length ? si[i3] : 0;
            if (s < d) {
                return false;
            }
            if (s > d) {
                return true;
            }
            i3++;
        }
        return true;
    }

    @CallerSensitive
    public static Package getPackage(String name) {
        ClassLoader l = ClassLoader.getClassLoader(Reflection.getCallerClass());
        if (l != null) {
            return l.getPackage(name);
        }
        return getSystemPackage(name);
    }

    @CallerSensitive
    public static Package[] getPackages() {
        ClassLoader l = ClassLoader.getClassLoader(Reflection.getCallerClass());
        if (l != null) {
            return l.getPackages();
        }
        return getSystemPackages();
    }

    static Package getPackage(Class<?> c) {
        String name = c.getName();
        int i = name.lastIndexOf(46);
        if (i == -1) {
            return null;
        }
        String name2 = name.substring(0, i);
        ClassLoader cl = c.getClassLoader();
        if (cl != null) {
            return cl.getPackage(name2);
        }
        return getSystemPackage(name2);
    }

    public int hashCode() {
        return this.pkgName.hashCode();
    }

    public String toString() {
        String spec;
        String ver;
        int targetSdkVersion = VMRuntime.getRuntime().getTargetSdkVersion();
        if (targetSdkVersion <= 0 || targetSdkVersion > 24) {
            String spec2 = this.specTitle;
            String ver2 = this.specVersion;
            if (spec2 == null || spec2.length() <= 0) {
                spec = "";
            } else {
                spec = ", " + spec2;
            }
            if (ver2 == null || ver2.length() <= 0) {
                ver = "";
            } else {
                ver = ", version " + ver2;
            }
            return "package " + this.pkgName + spec + ver;
        }
        return "package " + this.pkgName;
    }

    private Class<?> getPackageInfo() {
        if (this.packageInfo == null) {
            try {
                this.packageInfo = Class.forName(this.pkgName + ".package-info", false, this.loader);
            } catch (ClassNotFoundException e) {
                this.packageInfo = AnonymousClass1PackageInfoProxy.class;
            }
        }
        return this.packageInfo;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return (A) getPackageInfo().getAnnotation(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return super.isAnnotationPresent(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass) {
        return (A[]) getPackageInfo().getAnnotationsByType(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        return getPackageInfo().getAnnotations();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass) {
        return (A) getPackageInfo().getDeclaredAnnotation(annotationClass);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Class<A extends java.lang.annotation.Annotation> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass) {
        return (A[]) getPackageInfo().getDeclaredAnnotationsByType(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return getPackageInfo().getDeclaredAnnotations();
    }

    Package(String name, String spectitle, String specversion, String specvendor, String impltitle, String implversion, String implvendor, URL sealbase, ClassLoader loader2) {
        this.pkgName = name;
        this.implTitle = impltitle;
        this.implVersion = implversion;
        this.implVendor = implvendor;
        this.specTitle = spectitle;
        this.specVersion = specversion;
        this.specVendor = specvendor;
        this.sealBase = sealbase;
        this.loader = loader2;
    }

    private Package(String name, Manifest man, URL url, ClassLoader loader2) {
        String sealed = null;
        String specTitle2 = null;
        String specVersion2 = null;
        String specVendor2 = null;
        String implTitle2 = null;
        String implVersion2 = null;
        String implVendor2 = null;
        URL sealBase2 = null;
        Attributes attr = man.getAttributes(name.replace('.', '/').concat("/"));
        if (attr != null) {
            specTitle2 = attr.getValue(Attributes.Name.SPECIFICATION_TITLE);
            specVersion2 = attr.getValue(Attributes.Name.SPECIFICATION_VERSION);
            specVendor2 = attr.getValue(Attributes.Name.SPECIFICATION_VENDOR);
            implTitle2 = attr.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
            implVersion2 = attr.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            implVendor2 = attr.getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
            sealed = attr.getValue(Attributes.Name.SEALED);
        }
        Attributes attr2 = man.getMainAttributes();
        if (attr2 != null) {
            specTitle2 = specTitle2 == null ? attr2.getValue(Attributes.Name.SPECIFICATION_TITLE) : specTitle2;
            specVersion2 = specVersion2 == null ? attr2.getValue(Attributes.Name.SPECIFICATION_VERSION) : specVersion2;
            specVendor2 = specVendor2 == null ? attr2.getValue(Attributes.Name.SPECIFICATION_VENDOR) : specVendor2;
            implTitle2 = implTitle2 == null ? attr2.getValue(Attributes.Name.IMPLEMENTATION_TITLE) : implTitle2;
            implVersion2 = implVersion2 == null ? attr2.getValue(Attributes.Name.IMPLEMENTATION_VERSION) : implVersion2;
            implVendor2 = implVendor2 == null ? attr2.getValue(Attributes.Name.IMPLEMENTATION_VENDOR) : implVendor2;
            if (sealed == null) {
                sealed = attr2.getValue(Attributes.Name.SEALED);
            }
        }
        sealBase2 = "true".equalsIgnoreCase(sealed) ? url : sealBase2;
        this.pkgName = name;
        this.specTitle = specTitle2;
        this.specVersion = specVersion2;
        this.specVendor = specVendor2;
        this.implTitle = implTitle2;
        this.implVersion = implVersion2;
        this.implVendor = implVendor2;
        this.sealBase = sealBase2;
        this.loader = loader2;
    }

    static Package getSystemPackage(String name) {
        Package pkg;
        String name2;
        String fn;
        synchronized (pkgs) {
            pkg = pkgs.get(name);
            if (pkg == null && (fn = getSystemPackage0((name2 = name.replace('.', '/').concat("/")))) != null) {
                pkg = defineSystemPackage(name2, fn);
            }
        }
        return pkg;
    }

    static Package[] getSystemPackages() {
        Package[] packageArr;
        String[] names = getSystemPackages0();
        synchronized (pkgs) {
            for (int i = 0; i < names.length; i++) {
                defineSystemPackage(names[i], getSystemPackage0(names[i]));
            }
            packageArr = (Package[]) pkgs.values().toArray(new Package[pkgs.size()]);
        }
        return packageArr;
    }

    private static Package defineSystemPackage(final String iname, final String fn) {
        return (Package) AccessController.doPrivileged(new PrivilegedAction<Package>() {
            /* class java.lang.Package.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Package run() {
                Package pkg;
                String name = String.this;
                URL url = (URL) Package.urls.get(fn);
                if (url == null) {
                    File file = new File(fn);
                    try {
                        url = ParseUtil.fileToEncodedURL(file);
                    } catch (MalformedURLException e) {
                    }
                    if (url != null) {
                        Package.urls.put(fn, url);
                        if (file.isFile()) {
                            Map map = Package.mans;
                            String str = fn;
                            map.put(str, Package.loadManifest(str));
                        }
                    }
                }
                String name2 = name.substring(0, name.length() - 1).replace('/', '.');
                Manifest man = (Manifest) Package.mans.get(fn);
                if (man != null) {
                    pkg = new Package(name2, man, url, null);
                } else {
                    pkg = new Package(name2, null, null, null, null, null, null, null, null);
                }
                Package.pkgs.put(name2, pkg);
                return pkg;
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0022, code lost:
        $closeResource(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0025, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.jar.Manifest loadManifest(java.lang.String r5) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0026 }
            r1.<init>(r5)     // Catch:{ IOException -> 0x0026 }
            java.util.jar.JarInputStream r2 = new java.util.jar.JarInputStream     // Catch:{ all -> 0x001f }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x001f }
            java.util.jar.Manifest r3 = r2.getManifest()     // Catch:{ all -> 0x0018 }
            $closeResource(r0, r2)
            $closeResource(r0, r1)
            return r3
        L_0x0018:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001a }
        L_0x001a:
            r4 = move-exception
            $closeResource(r3, r2)
            throw r4
        L_0x001f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0021 }
        L_0x0021:
            r3 = move-exception
            $closeResource(r2, r1)
            throw r3
        L_0x0026:
            r1 = move-exception
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.Package.loadManifest(java.lang.String):java.util.jar.Manifest");
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }
}
