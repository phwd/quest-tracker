package sun.nio.fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.security.AccessController;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public abstract class UnixFileSystem extends FileSystem {
    private static final String GLOB_SYNTAX = "glob";
    private static final String REGEX_SYNTAX = "regex";
    private final byte[] defaultDirectory;
    private final boolean needToResolveAgainstDefaultDirectory;
    private final UnixFileSystemProvider provider;
    private final UnixPath rootDirectory;

    /* access modifiers changed from: package-private */
    public abstract FileStore getFileStore(UnixMountEntry unixMountEntry) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract Iterable<UnixMountEntry> getMountEntries();

    UnixFileSystem(UnixFileSystemProvider provider2, String dir) {
        this.provider = provider2;
        this.defaultDirectory = Util.toBytes(UnixPath.normalizeAndCheck(dir));
        boolean z = false;
        if (this.defaultDirectory[0] == 47) {
            String propValue = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.fs.chdirAllowed", "false"));
            if (propValue.length() == 0 ? true : Boolean.valueOf(propValue).booleanValue()) {
                this.needToResolveAgainstDefaultDirectory = true;
            } else {
                byte[] cwd = UnixNativeDispatcher.getcwd();
                boolean defaultIsCwd = cwd.length == this.defaultDirectory.length;
                if (defaultIsCwd) {
                    int i = 0;
                    while (true) {
                        if (i >= cwd.length) {
                            break;
                        } else if (cwd[i] != this.defaultDirectory[i]) {
                            defaultIsCwd = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                this.needToResolveAgainstDefaultDirectory = !defaultIsCwd ? true : z;
            }
            this.rootDirectory = new UnixPath(this, "/");
            return;
        }
        throw new RuntimeException("default directory must be absolute");
    }

    /* access modifiers changed from: package-private */
    public byte[] defaultDirectory() {
        return this.defaultDirectory;
    }

    /* access modifiers changed from: package-private */
    public boolean needToResolveAgainstDefaultDirectory() {
        return this.needToResolveAgainstDefaultDirectory;
    }

    /* access modifiers changed from: package-private */
    public UnixPath rootDirectory() {
        return this.rootDirectory;
    }

    /* access modifiers changed from: package-private */
    public boolean isSolaris() {
        return false;
    }

    static List<String> standardFileAttributeViews() {
        return Arrays.asList("basic", "posix", "unix", "owner");
    }

    @Override // java.nio.file.FileSystem
    public final FileSystemProvider provider() {
        return this.provider;
    }

    @Override // java.nio.file.FileSystem
    public final String getSeparator() {
        return "/";
    }

    @Override // java.nio.file.FileSystem
    public final boolean isOpen() {
        return true;
    }

    @Override // java.nio.file.FileSystem
    public final boolean isReadOnly() {
        return false;
    }

    @Override // java.io.Closeable, java.nio.file.FileSystem, java.lang.AutoCloseable
    public final void close() throws IOException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void copyNonPosixAttributes(int sfd, int tfd) {
    }

    @Override // java.nio.file.FileSystem
    public final Iterable<Path> getRootDirectories() {
        final List<Path> allowedList = Collections.unmodifiableList(Arrays.asList(this.rootDirectory));
        return new Iterable<Path>() {
            /* class sun.nio.fs.UnixFileSystem.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<Path> iterator() {
                try {
                    SecurityManager sm = System.getSecurityManager();
                    if (sm != null) {
                        sm.checkRead(UnixFileSystem.this.rootDirectory.toString());
                    }
                    return allowedList.iterator();
                } catch (SecurityException e) {
                    return Collections.emptyList().iterator();
                }
            }
        };
    }

    private class FileStoreIterator implements Iterator<FileStore> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Iterator<UnixMountEntry> entries;
        private FileStore next;

        FileStoreIterator() {
            this.entries = UnixFileSystem.this.getMountEntries().iterator();
        }

        private FileStore readNext() {
            while (this.entries.hasNext()) {
                UnixMountEntry entry = this.entries.next();
                if (!entry.isIgnored()) {
                    SecurityManager sm = System.getSecurityManager();
                    if (sm != null) {
                        try {
                            sm.checkRead(Util.toString(entry.dir()));
                        } catch (SecurityException e) {
                        }
                    }
                    try {
                        return UnixFileSystem.this.getFileStore(entry);
                    } catch (IOException e2) {
                    }
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public synchronized boolean hasNext() {
            boolean z = true;
            if (this.next != null) {
                return true;
            }
            this.next = readNext();
            if (this.next == null) {
                z = false;
            }
            return z;
        }

        @Override // java.util.Iterator
        public synchronized FileStore next() {
            FileStore result;
            if (this.next == null) {
                this.next = readNext();
            }
            if (this.next != null) {
                result = this.next;
                this.next = null;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.nio.file.FileSystem
    public final Iterable<FileStore> getFileStores() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkPermission(new RuntimePermission("getFileStoreAttributes"));
            } catch (SecurityException e) {
                return Collections.emptyList();
            }
        }
        return new Iterable<FileStore>() {
            /* class sun.nio.fs.UnixFileSystem.AnonymousClass2 */

            @Override // java.lang.Iterable
            public Iterator<FileStore> iterator() {
                return new FileStoreIterator();
            }
        };
    }

    @Override // java.nio.file.FileSystem
    public final Path getPath(String first, String... more) {
        String path;
        if (more.length == 0) {
            path = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            for (String segment : more) {
                if (segment.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append('/');
                    }
                    sb.append(segment);
                }
            }
            path = sb.toString();
        }
        return new UnixPath(this, path);
    }

    @Override // java.nio.file.FileSystem
    public PathMatcher getPathMatcher(String syntaxAndInput) {
        String expr;
        int pos = syntaxAndInput.indexOf(58);
        if (pos <= 0 || pos == syntaxAndInput.length()) {
            throw new IllegalArgumentException();
        }
        String syntax = syntaxAndInput.substring(0, pos);
        String input = syntaxAndInput.substring(pos + 1);
        if (syntax.equals(GLOB_SYNTAX)) {
            expr = Globs.toUnixRegexPattern(input);
        } else if (syntax.equals(REGEX_SYNTAX)) {
            expr = input;
        } else {
            throw new UnsupportedOperationException("Syntax '" + syntax + "' not recognized");
        }
        final Pattern pattern = compilePathMatchPattern(expr);
        return new PathMatcher() {
            /* class sun.nio.fs.UnixFileSystem.AnonymousClass3 */

            @Override // java.nio.file.PathMatcher
            public boolean matches(Path path) {
                return pattern.matcher(path.toString()).matches();
            }
        };
    }

    @Override // java.nio.file.FileSystem
    public final UserPrincipalLookupService getUserPrincipalLookupService() {
        return LookupService.instance;
    }

    private static class LookupService {
        static final UserPrincipalLookupService instance = new UserPrincipalLookupService() {
            /* class sun.nio.fs.UnixFileSystem.LookupService.AnonymousClass1 */

            @Override // java.nio.file.attribute.UserPrincipalLookupService
            public UserPrincipal lookupPrincipalByName(String name) throws IOException {
                return UnixUserPrincipals.lookupUser(name);
            }

            @Override // java.nio.file.attribute.UserPrincipalLookupService
            public GroupPrincipal lookupPrincipalByGroupName(String group) throws IOException {
                return UnixUserPrincipals.lookupGroup(group);
            }
        };

        private LookupService() {
        }
    }

    /* access modifiers changed from: package-private */
    public Pattern compilePathMatchPattern(String expr) {
        return Pattern.compile(expr);
    }

    /* access modifiers changed from: package-private */
    public char[] normalizeNativePath(char[] path) {
        return path;
    }

    /* access modifiers changed from: package-private */
    public String normalizeJavaPath(String path) {
        return path;
    }
}
