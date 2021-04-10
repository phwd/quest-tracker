package sun.nio.fs;

import java.nio.file.spi.FileSystemProvider;
import java.security.AccessController;
import sun.security.action.GetPropertyAction;

public class DefaultFileSystemProvider {
    private static FileSystemProvider createProvider(String str) {
        try {
            try {
                return (FileSystemProvider) Class.forName(str).newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                throw new AssertionError(e);
            }
        } catch (ClassNotFoundException e2) {
            throw new AssertionError(e2);
        }
    }

    public static FileSystemProvider create() {
        String str = (String) AccessController.doPrivileged(new GetPropertyAction("os.name"));
        if (str.equals("SunOS")) {
            return createProvider("sun.nio.fs.SolarisFileSystemProvider");
        }
        if (str.equals("Linux") || str.equals("Fuchsia")) {
            return createProvider("sun.nio.fs.LinuxFileSystemProvider");
        }
        if (str.contains("OS X")) {
            return createProvider("sun.nio.fs.MacOSXFileSystemProvider");
        }
        if (str.equals("AIX")) {
            return createProvider("sun.nio.fs.AixFileSystemProvider");
        }
        throw new AssertionError((Object) "Platform not recognized");
    }
}
