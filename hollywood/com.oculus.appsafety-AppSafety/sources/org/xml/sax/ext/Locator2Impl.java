package org.xml.sax.ext;

import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

public class Locator2Impl extends LocatorImpl implements Locator2 {
    private String encoding;
    private String version;

    public Locator2Impl() {
    }

    public Locator2Impl(Locator locator) {
        super(locator);
        if (locator instanceof Locator2) {
            Locator2 l2 = (Locator2) locator;
            this.version = l2.getXMLVersion();
            this.encoding = l2.getEncoding();
        }
    }

    @Override // org.xml.sax.ext.Locator2
    public String getXMLVersion() {
        return this.version;
    }

    @Override // org.xml.sax.ext.Locator2
    public String getEncoding() {
        return this.encoding;
    }

    public void setXMLVersion(String version2) {
        this.version = version2;
    }

    public void setEncoding(String encoding2) {
        this.encoding = encoding2;
    }
}
