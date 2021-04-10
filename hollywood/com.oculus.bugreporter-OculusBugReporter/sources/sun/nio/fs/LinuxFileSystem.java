package sun.nio.fs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class LinuxFileSystem extends UnixFileSystem {
    LinuxFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    @Override // java.nio.file.FileSystem
    public WatchService newWatchService() throws IOException {
        return new LinuxWatchService(this);
    }

    private static class SupportedFileFileAttributeViewsHolder {
        static final Set<String> supportedFileAttributeViews = supportedFileAttributeViews();

        private SupportedFileFileAttributeViewsHolder() {
        }

        private static Set<String> supportedFileAttributeViews() {
            Set<String> result = new HashSet<>();
            result.addAll(UnixFileSystem.standardFileAttributeViews());
            result.add("dos");
            result.add("user");
            return Collections.unmodifiableSet(result);
        }
    }

    @Override // java.nio.file.FileSystem
    public Set<String> supportedFileAttributeViews() {
        return SupportedFileFileAttributeViewsHolder.supportedFileAttributeViews;
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystem
    public void copyNonPosixAttributes(int ofd, int nfd) {
        LinuxUserDefinedFileAttributeView.copyExtendedAttributes(ofd, nfd);
    }

    /* access modifiers changed from: package-private */
    public Iterable<UnixMountEntry> getMountEntries(String fstab) {
        ArrayList<UnixMountEntry> entries = new ArrayList<>();
        try {
            long fp = LinuxNativeDispatcher.setmntent(Util.toBytes(fstab), Util.toBytes("r"));
            while (true) {
                try {
                    UnixMountEntry entry = new UnixMountEntry();
                    if (LinuxNativeDispatcher.getmntent(fp, entry) < 0) {
                        break;
                    }
                    entries.add(entry);
                } finally {
                    LinuxNativeDispatcher.endmntent(fp);
                }
            }
        } catch (UnixException e) {
        }
        return entries;
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystem
    public Iterable<UnixMountEntry> getMountEntries() {
        return getMountEntries("/proc/mounts");
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.fs.UnixFileSystem
    public FileStore getFileStore(UnixMountEntry entry) throws IOException {
        return new LinuxFileStore(this, entry);
    }
}
