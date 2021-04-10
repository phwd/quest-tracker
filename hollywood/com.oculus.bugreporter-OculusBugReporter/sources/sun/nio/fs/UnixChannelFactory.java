package sun.nio.fs;

import java.io.FileDescriptor;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import sun.misc.JavaIOFileDescriptorAccess;
import sun.misc.SharedSecrets;
import sun.nio.ch.FileChannelImpl;
import sun.nio.ch.SimpleAsynchronousFileChannelImpl;
import sun.nio.ch.ThreadPool;

class UnixChannelFactory {
    private static final JavaIOFileDescriptorAccess fdAccess = SharedSecrets.getJavaIOFileDescriptorAccess();

    protected UnixChannelFactory() {
    }

    /* access modifiers changed from: protected */
    public static class Flags {
        boolean append;
        boolean create;
        boolean createNew;
        boolean deleteOnClose;
        boolean dsync;
        boolean noFollowLinks;
        boolean read;
        boolean sync;
        boolean truncateExisting;
        boolean write;

        protected Flags() {
        }

        static Flags toFlags(Set<? extends OpenOption> options) {
            Flags flags = new Flags();
            for (OpenOption option : options) {
                if (option instanceof StandardOpenOption) {
                    switch ((StandardOpenOption) option) {
                        case READ:
                            flags.read = true;
                            continue;
                        case WRITE:
                            flags.write = true;
                            continue;
                        case APPEND:
                            flags.append = true;
                            continue;
                        case TRUNCATE_EXISTING:
                            flags.truncateExisting = true;
                            continue;
                        case CREATE:
                            flags.create = true;
                            continue;
                        case CREATE_NEW:
                            flags.createNew = true;
                            continue;
                        case DELETE_ON_CLOSE:
                            flags.deleteOnClose = true;
                            continue;
                        case SPARSE:
                            break;
                        case SYNC:
                            flags.sync = true;
                            continue;
                        case DSYNC:
                            flags.dsync = true;
                            continue;
                        default:
                            throw new UnsupportedOperationException();
                    }
                } else if (option == LinkOption.NOFOLLOW_LINKS && UnixConstants.O_NOFOLLOW != 0) {
                    flags.noFollowLinks = true;
                } else if (option == null) {
                    throw new NullPointerException();
                } else {
                    throw new UnsupportedOperationException(((Object) option) + " not supported");
                }
            }
            return flags;
        }
    }

    static FileChannel newFileChannel(int fd, String path, boolean reading, boolean writing) {
        FileDescriptor fdObj = new FileDescriptor();
        fdAccess.set(fdObj, fd);
        return FileChannelImpl.open(fdObj, path, reading, writing, null);
    }

    static FileChannel newFileChannel(int dfd, UnixPath path, String pathForPermissionCheck, Set<? extends OpenOption> options, int mode) throws UnixException {
        Flags flags = Flags.toFlags(options);
        if (!flags.read && !flags.write) {
            if (flags.append) {
                flags.write = true;
            } else {
                flags.read = true;
            }
        }
        if (flags.read && flags.append) {
            throw new IllegalArgumentException("READ + APPEND not allowed");
        } else if (!flags.append || !flags.truncateExisting) {
            return FileChannelImpl.open(open(dfd, path, pathForPermissionCheck, flags, mode), path.toString(), flags.read, flags.write, flags.append, null);
        } else {
            throw new IllegalArgumentException("APPEND + TRUNCATE_EXISTING not allowed");
        }
    }

    static FileChannel newFileChannel(UnixPath path, Set<? extends OpenOption> options, int mode) throws UnixException {
        return newFileChannel(-1, path, null, options, mode);
    }

    static AsynchronousFileChannel newAsynchronousFileChannel(UnixPath path, Set<? extends OpenOption> options, int mode, ThreadPool pool) throws UnixException {
        Flags flags = Flags.toFlags(options);
        if (!flags.read && !flags.write) {
            flags.read = true;
        }
        if (!flags.append) {
            return SimpleAsynchronousFileChannelImpl.open(open(-1, path, null, flags, mode), flags.read, flags.write, pool);
        }
        throw new UnsupportedOperationException("APPEND not allowed");
    }

    protected static FileDescriptor open(int dfd, UnixPath path, String pathForPermissionCheck, Flags flags, int mode) throws UnixException {
        int oflags;
        int fd;
        if (!flags.read || !flags.write) {
            oflags = flags.write ? UnixConstants.O_WRONLY : UnixConstants.O_RDONLY;
        } else {
            oflags = UnixConstants.O_RDWR;
        }
        if (flags.write) {
            if (flags.truncateExisting) {
                oflags |= UnixConstants.O_TRUNC;
            }
            if (flags.append) {
                oflags |= UnixConstants.O_APPEND;
            }
            if (flags.createNew) {
                byte[] pathForSysCall = path.asByteArray();
                if (pathForSysCall[pathForSysCall.length - 1] == 46 && (pathForSysCall.length == 1 || pathForSysCall[pathForSysCall.length - 2] == 47)) {
                    throw new UnixException(UnixConstants.EEXIST);
                }
                oflags |= UnixConstants.O_CREAT | UnixConstants.O_EXCL;
            } else if (flags.create) {
                oflags |= UnixConstants.O_CREAT;
            }
        }
        boolean followLinks = true;
        if (!flags.createNew && (flags.noFollowLinks || flags.deleteOnClose)) {
            if (flags.deleteOnClose && UnixConstants.O_NOFOLLOW == 0) {
                try {
                    if (UnixFileAttributes.get(path, false).isSymbolicLink()) {
                        throw new UnixException("DELETE_ON_CLOSE specified and file is a symbolic link");
                    }
                } catch (UnixException x) {
                    if (!flags.create || x.errno() != UnixConstants.ENOENT) {
                        throw x;
                    }
                }
            }
            followLinks = false;
            oflags |= UnixConstants.O_NOFOLLOW;
        }
        if (flags.dsync) {
            oflags |= UnixConstants.O_DSYNC;
        }
        if (flags.sync) {
            oflags |= UnixConstants.O_SYNC;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            if (pathForPermissionCheck == null) {
                pathForPermissionCheck = path.getPathForPermissionCheck();
            }
            if (flags.read) {
                sm.checkRead(pathForPermissionCheck);
            }
            if (flags.write) {
                sm.checkWrite(pathForPermissionCheck);
            }
            if (flags.deleteOnClose) {
                sm.checkDelete(pathForPermissionCheck);
            }
        }
        if (dfd >= 0) {
            try {
                fd = UnixNativeDispatcher.openat(dfd, path.asByteArray(), oflags, mode);
            } catch (UnixException x2) {
                if (flags.createNew && x2.errno() == UnixConstants.EISDIR) {
                    x2.setError(UnixConstants.EEXIST);
                }
                if (followLinks) {
                    throw x2;
                } else if (x2.errno() == UnixConstants.ELOOP) {
                    throw new UnixException(x2.getMessage() + " (NOFOLLOW_LINKS specified)");
                } else {
                    throw x2;
                }
            }
        } else {
            fd = UnixNativeDispatcher.open(path, oflags, mode);
        }
        if (flags.deleteOnClose) {
            if (dfd >= 0) {
                try {
                    UnixNativeDispatcher.unlinkat(dfd, path.asByteArray(), 0);
                } catch (UnixException e) {
                }
            } else {
                UnixNativeDispatcher.unlink(path);
            }
        }
        FileDescriptor fdObj = new FileDescriptor();
        fdAccess.set(fdObj, fd);
        return fdObj;
    }
}
