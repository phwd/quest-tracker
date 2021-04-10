package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(" ").omitEmptyStrings();
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>() {
        /* class com.google.common.reflect.ClassPath.AnonymousClass1 */

        public boolean apply(ClassInfo info) {
            return info.className.indexOf(36) == -1;
        }
    };
    private static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private final ImmutableSet<ResourceInfo> resources;

    private ClassPath(ImmutableSet<ResourceInfo> resources2) {
        this.resources = resources2;
    }

    public static ClassPath from(ClassLoader classloader) throws IOException {
        DefaultScanner scanner = new DefaultScanner();
        scanner.scan(classloader);
        return new ClassPath(scanner.getResources());
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(IS_TOP_LEVEL).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String packageName) {
        Preconditions.checkNotNull(packageName);
        ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = it.next();
            if (classInfo.getPackageName().equals(packageName)) {
                builder.add(classInfo);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String packageName) {
        Preconditions.checkNotNull(packageName);
        String packagePrefix = packageName + '.';
        ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = it.next();
            if (classInfo.getName().startsWith(packagePrefix)) {
                builder.add(classInfo);
            }
        }
        return builder.build();
    }

    @Beta
    public static class ResourceInfo {
        final ClassLoader loader;
        private final String resourceName;

        static ResourceInfo of(String resourceName2, ClassLoader loader2) {
            if (resourceName2.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION)) {
                return new ClassInfo(resourceName2, loader2);
            }
            return new ResourceInfo(resourceName2, loader2);
        }

        ResourceInfo(String resourceName2, ClassLoader loader2) {
            this.resourceName = (String) Preconditions.checkNotNull(resourceName2);
            this.loader = (ClassLoader) Preconditions.checkNotNull(loader2);
        }

        public final URL url() {
            URL url = this.loader.getResource(this.resourceName);
            if (url != null) {
                return url;
            }
            throw new NoSuchElementException(this.resourceName);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo that = (ResourceInfo) obj;
            if (!this.resourceName.equals(that.resourceName) || this.loader != that.loader) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.resourceName;
        }
    }

    @Beta
    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        ClassInfo(String resourceName, ClassLoader loader) {
            super(resourceName, loader);
            this.className = ClassPath.getClassName(resourceName);
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastDollarSign = this.className.lastIndexOf(36);
            if (lastDollarSign != -1) {
                return CharMatcher.digit().trimLeadingFrom(this.className.substring(lastDollarSign + 1));
            }
            String packageName = getPackageName();
            if (packageName.isEmpty()) {
                return this.className;
            }
            return this.className.substring(packageName.length() + 1);
        }

        public String getName() {
            return this.className;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.google.common.reflect.ClassPath.ResourceInfo
        public String toString() {
            return this.className;
        }
    }

    static abstract class Scanner {
        private final Set<File> scannedUris = Sets.newHashSet();

        /* access modifiers changed from: protected */
        public abstract void scanDirectory(ClassLoader classLoader, File file) throws IOException;

        /* access modifiers changed from: protected */
        public abstract void scanJarFile(ClassLoader classLoader, JarFile jarFile) throws IOException;

        Scanner() {
        }

        public final void scan(ClassLoader classloader) throws IOException {
            UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = getClassPathEntries(classloader).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<File, ClassLoader> entry = it.next();
                scan(entry.getKey(), entry.getValue());
            }
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public final void scan(File file, ClassLoader classloader) throws IOException {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                scanFrom(file, classloader);
            }
        }

        private void scanFrom(File file, ClassLoader classloader) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        scanDirectory(classloader, file);
                    } else {
                        scanJar(file, classloader);
                    }
                }
            } catch (SecurityException e) {
                ClassPath.logger.warning("Cannot access " + file + ": " + e);
            }
        }

        private void scanJar(File file, ClassLoader classloader) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        scan(it.next(), classloader);
                    }
                    scanJarFile(classloader, jarFile);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
            }
        }

        @VisibleForTesting
        static ImmutableSet<File> getClassPathFromManifest(File jarFile, @NullableDecl Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.of();
            }
            ImmutableSet.Builder<File> builder = ImmutableSet.builder();
            String classpathAttribute = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (classpathAttribute != null) {
                for (String path : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(classpathAttribute)) {
                    try {
                        URL url = getClassPathEntry(jarFile, path);
                        if (url.getProtocol().equals("file")) {
                            builder.add(ClassPath.toFile(url));
                        }
                    } catch (MalformedURLException e) {
                        ClassPath.logger.warning("Invalid Class-Path entry: " + path);
                    }
                }
            }
            return builder.build();
        }

        @VisibleForTesting
        static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classloader) {
            LinkedHashMap<File, ClassLoader> entries = Maps.newLinkedHashMap();
            ClassLoader parent = classloader.getParent();
            if (parent != null) {
                entries.putAll(getClassPathEntries(parent));
            }
            UnmodifiableIterator<URL> it = getClassLoaderUrls(classloader).iterator();
            while (it.hasNext()) {
                URL url = it.next();
                if (url.getProtocol().equals("file")) {
                    File file = ClassPath.toFile(url);
                    if (!entries.containsKey(file)) {
                        entries.put(file, classloader);
                    }
                }
            }
            return ImmutableMap.copyOf(entries);
        }

        private static ImmutableList<URL> getClassLoaderUrls(ClassLoader classloader) {
            if (classloader instanceof URLClassLoader) {
                return ImmutableList.copyOf(((URLClassLoader) classloader).getURLs());
            }
            if (classloader.equals(ClassLoader.getSystemClassLoader())) {
                return parseJavaClassPath();
            }
            return ImmutableList.of();
        }

        @VisibleForTesting
        static ImmutableList<URL> parseJavaClassPath() {
            ImmutableList.Builder<URL> urls = ImmutableList.builder();
            for (String entry : Splitter.on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
                try {
                    urls.add(new File(entry).toURI().toURL());
                } catch (SecurityException e) {
                    try {
                        urls.add(new URL("file", (String) null, new File(entry).getAbsolutePath()));
                    } catch (MalformedURLException e2) {
                        ClassPath.logger.log(Level.WARNING, "malformed classpath entry: " + entry, (Throwable) e2);
                    }
                }
            }
            return urls.build();
        }

        @VisibleForTesting
        static URL getClassPathEntry(File jarFile, String path) throws MalformedURLException {
            return new URL(jarFile.toURI().toURL(), path);
        }
    }

    @VisibleForTesting
    static final class DefaultScanner extends Scanner {
        private final SetMultimap<ClassLoader, String> resources = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        DefaultScanner() {
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<ResourceInfo> getResources() {
            ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
            for (Map.Entry<ClassLoader, String> entry : this.resources.entries()) {
                builder.add(ResourceInfo.of(entry.getValue(), entry.getKey()));
            }
            return builder.build();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.reflect.ClassPath.Scanner
        public void scanJarFile(ClassLoader classloader, JarFile file) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && !entry.getName().equals("META-INF/MANIFEST.MF")) {
                    this.resources.get(classloader).add(entry.getName());
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.reflect.ClassPath.Scanner
        public void scanDirectory(ClassLoader classloader, File directory) throws IOException {
            Set<File> currentPath = new HashSet<>();
            currentPath.add(directory.getCanonicalFile());
            scanDirectory(directory, classloader, "", currentPath);
        }

        private void scanDirectory(File directory, ClassLoader classloader, String packagePrefix, Set<File> currentPath) throws IOException {
            File[] files = directory.listFiles();
            if (files == null) {
                ClassPath.logger.warning("Cannot read directory " + directory);
                return;
            }
            for (File f : files) {
                String name = f.getName();
                if (f.isDirectory()) {
                    File deref = f.getCanonicalFile();
                    if (currentPath.add(deref)) {
                        scanDirectory(deref, classloader, packagePrefix + name + "/", currentPath);
                        currentPath.remove(deref);
                    }
                } else {
                    String resourceName = packagePrefix + name;
                    if (!resourceName.equals("META-INF/MANIFEST.MF")) {
                        this.resources.get(classloader).add(resourceName);
                    }
                }
            }
        }
    }

    @VisibleForTesting
    static String getClassName(String filename) {
        return filename.substring(0, filename.length() - CLASS_FILE_NAME_EXTENSION.length()).replace('/', '.');
    }

    @VisibleForTesting
    static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return new File(url.getPath());
        }
    }
}
