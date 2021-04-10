package java.net;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import sun.net.www.ParseUtil;

public abstract class JarURLConnection extends URLConnection {
    private String entryName;
    private URL jarFileURL;

    public abstract JarFile getJarFile();

    protected JarURLConnection(URL url) {
        super(url);
        parseSpecs(url);
    }

    private void parseSpecs(URL url) {
        String file = url.getFile();
        int indexOf = file.indexOf("!/");
        if (indexOf != -1) {
            this.jarFileURL = new URL(file.substring(0, indexOf));
            this.entryName = null;
            int i = indexOf + 1 + 1;
            if (i != file.length()) {
                this.entryName = file.substring(i, file.length());
                this.entryName = ParseUtil.decode(this.entryName);
                return;
            }
            return;
        }
        throw new MalformedURLException("no !/ found in url spec:" + file);
    }

    public URL getJarFileURL() {
        return this.jarFileURL;
    }

    public String getEntryName() {
        return this.entryName;
    }

    public JarEntry getJarEntry() {
        return getJarFile().getJarEntry(this.entryName);
    }
}
