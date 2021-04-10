package java.util.prefs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import sun.security.x509.PolicyMappingsExtension;

class XmlSupport {
    private static final String EXTERNAL_XML_VERSION = "1.0";
    private static final String MAP_XML_VERSION = "1.0";
    private static final String PREFS_DTD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!-- DTD for preferences --><!ELEMENT preferences (root) ><!ATTLIST preferences EXTERNAL_XML_VERSION CDATA \"0.0\"  ><!ELEMENT root (map, node*) ><!ATTLIST root          type (system|user) #REQUIRED ><!ELEMENT node (map, node*) ><!ATTLIST node          name CDATA #REQUIRED ><!ELEMENT map (entry*) ><!ATTLIST map  MAP_XML_VERSION CDATA \"0.0\"  ><!ELEMENT entry EMPTY ><!ATTLIST entry          key CDATA #REQUIRED          value CDATA #REQUIRED >";
    private static final String PREFS_DTD_URI = "http://java.sun.com/dtd/preferences.dtd";

    XmlSupport() {
    }

    static void export(OutputStream os, Preferences p, boolean subTree) throws IOException, BackingStoreException {
        if (!((AbstractPreferences) p).isRemoved()) {
            Document doc = createPrefsDoc("preferences");
            Element preferences = doc.getDocumentElement();
            preferences.setAttribute("EXTERNAL_XML_VERSION", "1.0");
            Element xmlRoot = (Element) preferences.appendChild(doc.createElement("root"));
            xmlRoot.setAttribute("type", p.isUserNode() ? "user" : "system");
            List<Preferences> ancestors = new ArrayList<>();
            Preferences kid = p;
            Preferences dad = kid.parent();
            while (dad != null) {
                ancestors.add(kid);
                kid = dad;
                dad = kid.parent();
            }
            Element e = xmlRoot;
            for (int i = ancestors.size() - 1; i >= 0; i--) {
                e.appendChild(doc.createElement(PolicyMappingsExtension.MAP));
                e = (Element) e.appendChild(doc.createElement("node"));
                e.setAttribute("name", ancestors.get(i).name());
            }
            putPreferencesInXml(e, doc, p, subTree);
            writeDoc(doc, os);
            return;
        }
        throw new IllegalStateException("Node has been removed");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        if (r14 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        if (r2 >= r1.length) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
        r3 = (org.w3c.dom.Element) r11.appendChild(r12.createElement("node"));
        r3.setAttribute("name", r1[r2]);
        putPreferencesInXml(r3, r12, r0[r2], r14);
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void putPreferencesInXml(org.w3c.dom.Element r11, org.w3c.dom.Document r12, java.util.prefs.Preferences r13, boolean r14) throws java.util.prefs.BackingStoreException {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.XmlSupport.putPreferencesInXml(org.w3c.dom.Element, org.w3c.dom.Document, java.util.prefs.Preferences, boolean):void");
    }

    static void importPreferences(InputStream is) throws IOException, InvalidPreferencesFormatException {
        try {
            Document doc = loadPrefsDoc(is);
            String xmlVersion = doc.getDocumentElement().getAttribute("EXTERNAL_XML_VERSION");
            if (xmlVersion.compareTo("1.0") <= 0) {
                NodeList elements = doc.getDocumentElement().getElementsByTagName("root");
                if (elements == null || elements.getLength() != 1) {
                    throw new InvalidPreferencesFormatException("invalid root node");
                }
                Element xmlRoot = (Element) elements.item(0);
                ImportSubtree(xmlRoot.getAttribute("type").equals("user") ? Preferences.userRoot() : Preferences.systemRoot(), xmlRoot);
                return;
            }
            throw new InvalidPreferencesFormatException("Exported preferences file format version " + xmlVersion + " is not supported. This java installation can read versions " + "1.0" + " or older. You may need to install a newer version of JDK.");
        } catch (SAXException e) {
            throw new InvalidPreferencesFormatException(e);
        }
    }

    private static Document createPrefsDoc(String qname) {
        try {
            DOMImplementation di = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation();
            return di.createDocument(null, qname, di.createDocumentType(qname, null, PREFS_DTD_URI));
        } catch (ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    private static Document loadPrefsDoc(InputStream in) throws SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringComments(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setEntityResolver(new Resolver());
            db.setErrorHandler(new EH());
            return db.parse(new InputSource(in));
        } catch (ParserConfigurationException e) {
            throw new AssertionError(e);
        }
    }

    private static final void writeDoc(Document doc, OutputStream out) throws IOException {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            try {
                tf.setAttribute("indent-number", new Integer(2));
            } catch (IllegalArgumentException e) {
            }
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new BufferedWriter(new OutputStreamWriter(out, "UTF-8"))));
        } catch (TransformerException e2) {
            throw new AssertionError(e2);
        }
    }

    /* access modifiers changed from: package-private */
    public static class NodeListAdapter implements NodeList {
        private final List<? extends Node> delegate;

        public NodeListAdapter(List<? extends Node> delegate2) {
            this.delegate = (List) Objects.requireNonNull(delegate2);
        }

        @Override // org.w3c.dom.NodeList
        public Node item(int index) {
            if (index < 0 || index >= this.delegate.size()) {
                return null;
            }
            return (Node) this.delegate.get(index);
        }

        @Override // org.w3c.dom.NodeList
        public int getLength() {
            return this.delegate.size();
        }
    }

    private static NodeList elementNodesOf(NodeList xmlKids) {
        List<Element> elements = new ArrayList<>(xmlKids.getLength());
        for (int i = 0; i < xmlKids.getLength(); i++) {
            Node node = xmlKids.item(i);
            if (node instanceof Element) {
                elements.add((Element) node);
            }
        }
        return new NodeListAdapter(elements);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r2 >= r1) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        ImportSubtree(r4[r2 - 1], (org.w3c.dom.Element) r0.item(r2));
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0059, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void ImportSubtree(java.util.prefs.Preferences r9, org.w3c.dom.Element r10) {
        /*
            org.w3c.dom.NodeList r0 = r10.getChildNodes()
            org.w3c.dom.NodeList r0 = elementNodesOf(r0)
            int r1 = r0.getLength()
            r2 = r9
            java.util.prefs.AbstractPreferences r2 = (java.util.prefs.AbstractPreferences) r2
            java.lang.Object r2 = r2.lock
            monitor-enter(r2)
            r3 = r9
            java.util.prefs.AbstractPreferences r3 = (java.util.prefs.AbstractPreferences) r3     // Catch:{ all -> 0x005a }
            boolean r3 = r3.isRemoved()     // Catch:{ all -> 0x005a }
            if (r3 == 0) goto L_0x001d
            monitor-exit(r2)     // Catch:{ all -> 0x005a }
            return
        L_0x001d:
            r3 = 0
            org.w3c.dom.Node r3 = r0.item(r3)     // Catch:{ all -> 0x005a }
            org.w3c.dom.Element r3 = (org.w3c.dom.Element) r3     // Catch:{ all -> 0x005a }
            ImportPrefs(r9, r3)     // Catch:{ all -> 0x005a }
            int r4 = r1 + -1
            java.util.prefs.Preferences[] r4 = new java.util.prefs.Preferences[r4]     // Catch:{ all -> 0x005a }
            r5 = 1
        L_0x002c:
            if (r5 >= r1) goto L_0x0045
            org.w3c.dom.Node r6 = r0.item(r5)     // Catch:{ all -> 0x005a }
            org.w3c.dom.Element r6 = (org.w3c.dom.Element) r6     // Catch:{ all -> 0x005a }
            int r7 = r5 + -1
            java.lang.String r8 = "name"
            java.lang.String r8 = r6.getAttribute(r8)     // Catch:{ all -> 0x005a }
            java.util.prefs.Preferences r8 = r9.node(r8)     // Catch:{ all -> 0x005a }
            r4[r7] = r8     // Catch:{ all -> 0x005a }
            int r5 = r5 + 1
            goto L_0x002c
        L_0x0045:
            monitor-exit(r2)     // Catch:{ all -> 0x005a }
            r2 = 1
        L_0x0047:
            if (r2 >= r1) goto L_0x0059
            int r3 = r2 + -1
            r3 = r4[r3]
            org.w3c.dom.Node r5 = r0.item(r2)
            org.w3c.dom.Element r5 = (org.w3c.dom.Element) r5
            ImportSubtree(r3, r5)
            int r2 = r2 + 1
            goto L_0x0047
        L_0x0059:
            return
        L_0x005a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.XmlSupport.ImportSubtree(java.util.prefs.Preferences, org.w3c.dom.Element):void");
    }

    private static void ImportPrefs(Preferences prefsNode, Element map) {
        NodeList entries = elementNodesOf(map.getChildNodes());
        int numEntries = entries.getLength();
        for (int i = 0; i < numEntries; i++) {
            Element entry = (Element) entries.item(i);
            prefsNode.put(entry.getAttribute("key"), entry.getAttribute("value"));
        }
    }

    static void exportMap(OutputStream os, Map<String, String> map) throws IOException {
        Document doc = createPrefsDoc(PolicyMappingsExtension.MAP);
        Element xmlMap = doc.getDocumentElement();
        xmlMap.setAttribute("MAP_XML_VERSION", "1.0");
        for (Map.Entry<String, String> e : map.entrySet()) {
            Element xe = (Element) xmlMap.appendChild(doc.createElement("entry"));
            xe.setAttribute("key", e.getKey());
            xe.setAttribute("value", e.getValue());
        }
        writeDoc(doc, os);
    }

    static void importMap(InputStream is, Map<String, String> m) throws IOException, InvalidPreferencesFormatException {
        try {
            Element xmlMap = loadPrefsDoc(is).getDocumentElement();
            String mapVersion = xmlMap.getAttribute("MAP_XML_VERSION");
            if (mapVersion.compareTo("1.0") <= 0) {
                NodeList entries = xmlMap.getChildNodes();
                int numEntries = entries.getLength();
                for (int i = 0; i < numEntries; i++) {
                    if (entries.item(i) instanceof Element) {
                        Element entry = (Element) entries.item(i);
                        m.put(entry.getAttribute("key"), entry.getAttribute("value"));
                    }
                }
                return;
            }
            throw new InvalidPreferencesFormatException("Preferences map file format version " + mapVersion + " is not supported. This java installation can read versions " + "1.0" + " or older. You may need to install a newer version of JDK.");
        } catch (SAXException e) {
            throw new InvalidPreferencesFormatException(e);
        }
    }

    /* access modifiers changed from: private */
    public static class Resolver implements EntityResolver {
        private Resolver() {
        }

        @Override // org.xml.sax.EntityResolver
        public InputSource resolveEntity(String pid, String sid) throws SAXException {
            if (sid.equals(XmlSupport.PREFS_DTD_URI)) {
                InputSource is = new InputSource(new StringReader(XmlSupport.PREFS_DTD));
                is.setSystemId(XmlSupport.PREFS_DTD_URI);
                return is;
            }
            throw new SAXException("Invalid system identifier: " + sid);
        }
    }

    /* access modifiers changed from: private */
    public static class EH implements ErrorHandler {
        private EH() {
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException x) throws SAXException {
            throw x;
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException x) throws SAXException {
            throw x;
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException x) throws SAXException {
            throw x;
        }
    }
}
