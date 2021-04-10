package org.apache.harmony.xml.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

public final class DOMImplementationImpl implements DOMImplementation {
    private static DOMImplementationImpl instance;

    DOMImplementationImpl() {
    }

    @Override // org.w3c.dom.DOMImplementation
    public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
        return new DocumentImpl(this, namespaceURI, qualifiedName, doctype, null);
    }

    @Override // org.w3c.dom.DOMImplementation
    public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
        return new DocumentTypeImpl(null, qualifiedName, publicId, systemId);
    }

    @Override // org.w3c.dom.DOMImplementation
    public boolean hasFeature(String feature, String version) {
        boolean anyVersion = version == null || version.length() == 0;
        if (feature.startsWith("+")) {
            feature = feature.substring(1);
        }
        if (feature.equalsIgnoreCase("Core")) {
            return anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0");
        }
        if (feature.equalsIgnoreCase("XML")) {
            return anyVersion || version.equals("1.0") || version.equals("2.0") || version.equals("3.0");
        }
        if (feature.equalsIgnoreCase("XMLVersion")) {
            return anyVersion || version.equals("1.0") || version.equals("1.1");
        }
        return false;
    }

    public static DOMImplementationImpl getInstance() {
        if (instance == null) {
            instance = new DOMImplementationImpl();
        }
        return instance;
    }

    @Override // org.w3c.dom.DOMImplementation
    public Object getFeature(String feature, String version) {
        if (hasFeature(feature, version)) {
            return this;
        }
        return null;
    }
}
