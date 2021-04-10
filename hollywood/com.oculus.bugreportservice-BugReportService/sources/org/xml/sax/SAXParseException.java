package org.xml.sax;

public class SAXParseException extends SAXException {
    private int columnNumber;
    private int lineNumber;
    private String publicId;
    private String systemId;
}
