package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.options.ParseOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMPMetaParser {
    private static final Object XMP_RDF = new Object();
    private static DocumentBuilderFactory factory = createDocumentBuilderFactory();

    public static XMPMeta parse(Object input, ParseOptions options) throws XMPException {
        ParameterAsserts.assertNotNull(input);
        if (options == null) {
            options = new ParseOptions();
        }
        Object[] result = findRootNode(parseXml(input, options), options.getRequireXMPMeta(), new Object[3]);
        if (result == null || result[1] != XMP_RDF) {
            return new XMPMetaImpl();
        }
        XMPMetaImpl xmp = ParseRDF.parse((Node) result[0]);
        xmp.setPacketHeader((String) result[2]);
        if (!options.getOmitNormalization()) {
            return XMPNormalizer.process(xmp, options);
        }
        return xmp;
    }

    private static Document parseXml(Object input, ParseOptions options) throws XMPException {
        if (input instanceof InputStream) {
            return parseXmlFromInputStream((InputStream) input, options);
        }
        if (input instanceof byte[]) {
            return parseXmlFromBytebuffer(new ByteBuffer((byte[]) ((byte[]) input)), options);
        }
        return parseXmlFromString((String) input, options);
    }

    private static Document parseXmlFromInputStream(InputStream stream, ParseOptions options) throws XMPException {
        if (!options.getAcceptLatin1() && !options.getFixControlChars()) {
            return parseInputSource(new InputSource(stream));
        }
        try {
            return parseXmlFromBytebuffer(new ByteBuffer(stream), options);
        } catch (IOException e) {
            throw new XMPException("Error reading the XML-file", 204, e);
        }
    }

    private static Document parseXmlFromBytebuffer(ByteBuffer buffer, ParseOptions options) throws XMPException {
        try {
            return parseInputSource(new InputSource(buffer.getByteStream()));
        } catch (XMPException e) {
            if (e.getErrorCode() == 201 || e.getErrorCode() == 204) {
                if (options.getAcceptLatin1()) {
                    buffer = Latin1Converter.convert(buffer);
                }
                if (!options.getFixControlChars()) {
                    return parseInputSource(new InputSource(buffer.getByteStream()));
                }
                try {
                    return parseInputSource(new InputSource(new FixASCIIControlsReader(new InputStreamReader(buffer.getByteStream(), buffer.getEncoding()))));
                } catch (UnsupportedEncodingException e2) {
                    throw new XMPException("Unsupported Encoding", 9, e);
                }
            } else {
                throw e;
            }
        }
    }

    private static Document parseXmlFromString(String input, ParseOptions options) throws XMPException {
        try {
            return parseInputSource(new InputSource(new StringReader(input)));
        } catch (XMPException e) {
            if (e.getErrorCode() == 201 && options.getFixControlChars()) {
                return parseInputSource(new InputSource(new FixASCIIControlsReader(new StringReader(input))));
            }
            throw e;
        }
    }

    private static Document parseInputSource(InputSource source) throws XMPException {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(null);
            return builder.parse(source);
        } catch (SAXException e) {
            throw new XMPException("XML parsing failure", 201, e);
        } catch (ParserConfigurationException e2) {
            throw new XMPException("XML Parser not correctly configured", 0, e2);
        } catch (IOException e3) {
            throw new XMPException("Error reading the XML-file", 204, e3);
        }
    }

    private static Object[] findRootNode(Node root, boolean xmpmetaRequired, Object[] result) {
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node root2 = children.item(i);
            if (7 == root2.getNodeType() && ((ProcessingInstruction) root2).getTarget() == "xpacket") {
                if (result != null) {
                    result[2] = ((ProcessingInstruction) root2).getData();
                }
            } else if (!(3 == root2.getNodeType() || 7 == root2.getNodeType())) {
                String rootNS = root2.getNamespaceURI();
                String rootLocal = root2.getLocalName();
                if (("xmpmeta".equals(rootLocal) || "xapmeta".equals(rootLocal)) && "adobe:ns:meta/".equals(rootNS)) {
                    return findRootNode(root2, false, result);
                }
                if (xmpmetaRequired || !"RDF".equals(rootLocal) || !"http://www.w3.org/1999/02/22-rdf-syntax-ns#".equals(rootNS)) {
                    Object[] newResult = findRootNode(root2, xmpmetaRequired, result);
                    if (newResult != null) {
                        return newResult;
                    }
                } else if (result == null) {
                    return result;
                } else {
                    result[0] = root2;
                    result[1] = XMP_RDF;
                    return result;
                }
            }
        }
        return null;
    }

    private static DocumentBuilderFactory createDocumentBuilderFactory() {
        DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
        factory2.setNamespaceAware(true);
        factory2.setIgnoringComments(true);
        try {
            factory2.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        } catch (Exception e) {
        }
        return factory2;
    }
}
