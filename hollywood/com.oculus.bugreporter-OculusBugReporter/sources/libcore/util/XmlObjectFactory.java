package libcore.util;

import com.android.org.kxml2.io.KXmlParser;
import com.android.org.kxml2.io.KXmlSerializer;
import org.apache.harmony.xml.ExpatReader;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public class XmlObjectFactory {
    private XmlObjectFactory() {
    }

    public static XmlSerializer newXmlSerializer() {
        return new KXmlSerializer();
    }

    public static XmlPullParser newXmlPullParser() {
        return new KXmlParser();
    }

    public static XMLReader newXMLReader() {
        return new ExpatReader();
    }
}
