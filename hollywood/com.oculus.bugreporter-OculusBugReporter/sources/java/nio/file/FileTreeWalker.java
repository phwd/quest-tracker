package java.nio.file;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import sun.nio.fs.BasicFileAttributesHolder;

class FileTreeWalker implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean closed;
    private final boolean followLinks;
    private final LinkOption[] linkOptions;
    private final int maxDepth;
    private final ArrayDeque<DirectoryNode> stack = new ArrayDeque<>();

    /* access modifiers changed from: package-private */
    public enum EventType {
        START_DIRECTORY,
        END_DIRECTORY,
        ENTRY
    }

    /* access modifiers changed from: private */
    public static class DirectoryNode {
        private final Path dir;
        private final Iterator<Path> iterator;
        private final Object key;
        private boolean skipped;
        private final DirectoryStream<Path> stream;

        DirectoryNode(Path dir2, Object key2, DirectoryStream<Path> stream2) {
            this.dir = dir2;
            this.key = key2;
            this.stream = stream2;
            this.iterator = stream2.iterator();
        }

        /* access modifiers changed from: package-private */
        public Path directory() {
            return this.dir;
        }

        /* access modifiers changed from: package-private */
        public Object key() {
            return this.key;
        }

        /* access modifiers changed from: package-private */
        public DirectoryStream<Path> stream() {
            return this.stream;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Path> iterator() {
            return this.iterator;
        }

        /* access modifiers changed from: package-private */
        public void skip() {
            this.skipped = true;
        }

        /* access modifiers changed from: package-private */
        public boolean skipped() {
            return this.skipped;
        }
    }

    /* access modifiers changed from: package-private */
    public static class Event {
        private final BasicFileAttributes attrs;
        private final Path file;
        private final IOException ioe;
        private final EventType type;

        private Event(EventType type2, Path file2, BasicFileAttributes attrs2, IOException ioe2) {
            this.type = type2;
            this.file = file2;
            this.attrs = attrs2;
            this.ioe = ioe2;
        }

        Event(EventType type2, Path file2, BasicFileAttributes attrs2) {
            this(type2, file2, attrs2, null);
        }

        Event(EventType type2, Path file2, IOException ioe2) {
            this(type2, file2, null, ioe2);
        }

        /* access modifiers changed from: package-private */
        public EventType type() {
            return this.type;
        }

        /* access modifiers changed from: package-private */
        public Path file() {
            return this.file;
        }

        /* access modifiers changed from: package-private */
        public BasicFileAttributes attributes() {
            return this.attrs;
        }

        /* access modifiers changed from: package-private */
        public IOException ioeException() {
            return this.ioe;
        }
    }

    FileTreeWalker(Collection<FileVisitOption> options, int maxDepth2) {
        boolean fl = false;
        for (FileVisitOption option : options) {
            if (AnonymousClass1.$SwitchMap$java$nio$file$FileVisitOption[option.ordinal()] == 1) {
                fl = true;
            } else {
                throw new AssertionError((Object) "Should not get here");
            }
        }
        if (maxDepth2 >= 0) {
            this.followLinks = fl;
            this.linkOptions = fl ? new LinkOption[0] : new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
            this.maxDepth = maxDepth2;
            return;
        }
        throw new IllegalArgumentException("'maxDepth' is negative");
    }

    /* renamed from: java.nio.file.FileTreeWalker$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$FileVisitOption = new int[FileVisitOption.values().length];

        static {
            try {
                $SwitchMap$java$nio$file$FileVisitOption[FileVisitOption.FOLLOW_LINKS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    /* JADX INFO: Multiple debug info for r0v2 java.nio.file.attribute.BasicFileAttributes: [D('attrs' java.nio.file.attribute.BasicFileAttributes), D('ioe' java.io.IOException)] */
    private BasicFileAttributes getAttributes(Path file, boolean canUseCached) throws IOException {
        BasicFileAttributes cached;
        if (canUseCached && (file instanceof BasicFileAttributesHolder) && System.getSecurityManager() == null && (cached = ((BasicFileAttributesHolder) file).get()) != null && (!this.followLinks || !cached.isSymbolicLink())) {
            return cached;
        }
        try {
            return Files.readAttributes(file, BasicFileAttributes.class, this.linkOptions);
        } catch (IOException ioe) {
            if (this.followLinks) {
                return Files.readAttributes(file, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            }
            throw ioe;
        }
    }

    private boolean wouldLoop(Path dir, Object key) {
        Iterator<DirectoryNode> it = this.stack.iterator();
        while (it.hasNext()) {
            DirectoryNode ancestor = it.next();
            Object ancestorKey = ancestor.key();
            if (key == null || ancestorKey == null) {
                try {
                    if (Files.isSameFile(dir, ancestor.directory())) {
                        return true;
                    }
                } catch (IOException | SecurityException e) {
                }
            } else if (key.equals(ancestorKey)) {
                return true;
            }
        }
        return false;
    }

    private Event visit(Path entry, boolean ignoreSecurityException, boolean canUseCached) {
        try {
            BasicFileAttributes attrs = getAttributes(entry, canUseCached);
            if (this.stack.size() >= this.maxDepth || !attrs.isDirectory()) {
                return new Event(EventType.ENTRY, entry, attrs);
            }
            if (this.followLinks && wouldLoop(entry, attrs.fileKey())) {
                return new Event(EventType.ENTRY, entry, new FileSystemLoopException(entry.toString()));
            }
            try {
                this.stack.push(new DirectoryNode(entry, attrs.fileKey(), Files.newDirectoryStream(entry)));
                return new Event(EventType.START_DIRECTORY, entry, attrs);
            } catch (IOException ioe) {
                return new Event(EventType.ENTRY, entry, ioe);
            } catch (SecurityException se) {
                if (ignoreSecurityException) {
                    return null;
                }
                throw se;
            }
        } catch (IOException ioe2) {
            return new Event(EventType.ENTRY, entry, ioe2);
        } catch (SecurityException se2) {
            if (ignoreSecurityException) {
                return null;
            }
            throw se2;
        }
    }

    /* access modifiers changed from: package-private */
    public Event walk(Path file) {
        if (!this.closed) {
            return visit(file, false, false);
        }
        throw new IllegalStateException("Closed");
    }

    /* JADX INFO: Multiple debug info for r1v2 java.nio.file.FileTreeWalker$Event: [D('entry' java.nio.file.Path), D('ev' java.nio.file.FileTreeWalker$Event)] */
    /* access modifiers changed from: package-private */
    public Event next() {
        Event ev;
        DirectoryNode top = this.stack.peek();
        if (top == null) {
            return null;
        }
        do {
            Path entry = null;
            IOException ioe = null;
            if (!top.skipped()) {
                Iterator<Path> iterator = top.iterator();
                try {
                    if (iterator.hasNext()) {
                        entry = iterator.next();
                    }
                } catch (DirectoryIteratorException x) {
                    ioe = x.getCause();
                }
            }
            if (entry == null) {
                try {
                    top.stream().close();
                } catch (IOException e) {
                    if (ioe != null) {
                        ioe = e;
                    } else {
                        ioe.addSuppressed(e);
                    }
                }
                this.stack.pop();
                return new Event(EventType.END_DIRECTORY, top.directory(), ioe);
            }
            ev = visit(entry, true, true);
        } while (ev == null);
        return ev;
    }

    /* access modifiers changed from: package-private */
    public void pop() {
        if (!this.stack.isEmpty()) {
            try {
                this.stack.pop().stream().close();
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void skipRemainingSiblings() {
        if (!this.stack.isEmpty()) {
            this.stack.peek().skip();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            while (!this.stack.isEmpty()) {
                pop();
            }
            this.closed = true;
        }
    }
}
