package sun.net.www.protocol.jar;

import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.HashMap;
import java.util.jar.JarFile;
import sun.net.util.URLUtil;
import sun.net.www.protocol.jar.URLJarFile;

/* access modifiers changed from: package-private */
public class JarFileFactory implements URLJarFile.URLJarFileCloseController {
    private static final HashMap fileCache = new HashMap();
    private static final JarFileFactory instance = new JarFileFactory();
    private static final HashMap urlCache = new HashMap();

    private JarFileFactory() {
    }

    public static JarFileFactory getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public URLConnection getConnection(JarFile jarFile) {
        URL url;
        synchronized (instance) {
            url = (URL) urlCache.get(jarFile);
        }
        if (url != null) {
            return url.openConnection();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public JarFile get(URL url, boolean z) {
        JarFile jarFile;
        if (z) {
            synchronized (instance) {
                jarFile = getCachedJarFile(url);
            }
            if (jarFile == null) {
                JarFile jarFile2 = URLJarFile.getJarFile(url, this);
                synchronized (instance) {
                    JarFile cachedJarFile = getCachedJarFile(url);
                    if (cachedJarFile == null) {
                        fileCache.put(URLUtil.urlNoFragString(url), jarFile2);
                        urlCache.put(jarFile2, url);
                        jarFile = jarFile2;
                    } else {
                        if (jarFile2 != null) {
                            jarFile2.close();
                        }
                        jarFile = cachedJarFile;
                    }
                }
            }
        } else {
            jarFile = URLJarFile.getJarFile(url, this);
        }
        if (jarFile != null) {
            return jarFile;
        }
        throw new FileNotFoundException(url.toString());
    }

    @Override // sun.net.www.protocol.jar.URLJarFile.URLJarFileCloseController
    public void close(JarFile jarFile) {
        synchronized (instance) {
            URL url = (URL) urlCache.remove(jarFile);
            if (url != null) {
                fileCache.remove(URLUtil.urlNoFragString(url));
            }
        }
    }

    private JarFile getCachedJarFile(URL url) {
        Permission permission;
        SecurityManager securityManager;
        JarFile jarFile = (JarFile) fileCache.get(URLUtil.urlNoFragString(url));
        if (jarFile == null || (permission = getPermission(jarFile)) == null || (securityManager = System.getSecurityManager()) == null) {
            return jarFile;
        }
        try {
            securityManager.checkPermission(permission);
            throw null;
        } catch (SecurityException e) {
            if ((permission instanceof FilePermission) && permission.getActions().indexOf("read") != -1) {
                securityManager.checkRead(permission.getName());
                throw null;
            } else if (!(permission instanceof SocketPermission) || permission.getActions().indexOf("connect") == -1) {
                throw e;
            } else {
                securityManager.checkConnect(url.getHost(), url.getPort());
                throw null;
            }
        }
    }

    private Permission getPermission(JarFile jarFile) {
        try {
            URLConnection connection = getConnection(jarFile);
            if (connection != null) {
                return connection.getPermission();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }
}
