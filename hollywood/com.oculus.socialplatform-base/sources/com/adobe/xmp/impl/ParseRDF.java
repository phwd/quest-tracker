package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.PropertyOptions;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ParseRDF implements XMPConst, XMPError {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String DEFAULT_PREFIX = "_dflt";
    public static final int RDFTERM_ABOUT = 3;
    public static final int RDFTERM_ABOUT_EACH = 10;
    public static final int RDFTERM_ABOUT_EACH_PREFIX = 11;
    public static final int RDFTERM_BAG_ID = 12;
    public static final int RDFTERM_DATATYPE = 7;
    public static final int RDFTERM_DESCRIPTION = 8;
    public static final int RDFTERM_FIRST_CORE = 1;
    public static final int RDFTERM_FIRST_OLD = 10;
    public static final int RDFTERM_FIRST_SYNTAX = 1;
    public static final int RDFTERM_ID = 2;
    public static final int RDFTERM_LAST_CORE = 7;
    public static final int RDFTERM_LAST_OLD = 12;
    public static final int RDFTERM_LAST_SYNTAX = 9;
    public static final int RDFTERM_LI = 9;
    public static final int RDFTERM_NODE_ID = 6;
    public static final int RDFTERM_OTHER = 0;
    public static final int RDFTERM_PARSE_TYPE = 4;
    public static final int RDFTERM_RDF = 1;
    public static final int RDFTERM_RESOURCE = 5;

    public static void fixupQualifiedNode(XMPNode xMPNode) throws XMPException {
        XMPNode child = xMPNode.getChild(1);
        if (child.getOptions().getOption(64)) {
            if (!xMPNode.getOptions().getOption(64)) {
                XMPNode qualifier = child.getQualifier(1);
                child.removeQualifier(qualifier);
                xMPNode.addQualifier(qualifier);
            } else {
                throw new XMPException("Redundant xml:lang for rdf:value element", 203);
            }
        }
        for (int i = 1; i <= child.getQualifierLength(); i++) {
            xMPNode.addQualifier(child.getQualifier(i));
        }
        for (int i2 = 2; i2 <= xMPNode.getChildrenLength(); i2++) {
            xMPNode.addQualifier(xMPNode.getChild(i2));
        }
        xMPNode.hasValueChild = false;
        xMPNode.getOptions().setOption(256, false);
        xMPNode.getOptions().mergeWith(child.getOptions());
        xMPNode.value = child.value;
        xMPNode.children = null;
        Iterator iterateChildren = child.iterateChildren();
        while (iterateChildren.hasNext()) {
            xMPNode.addChild((XMPNode) iterateChildren.next());
        }
    }

    public static boolean isCoreSyntaxTerm(int i) {
        return 1 <= i && i <= 7;
    }

    public static boolean isOldTerm(int i) {
        return 10 <= i && i <= 12;
    }

    public static void rdf_LiteralPropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, null, z);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node item = node.getAttributes().item(i);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String namespaceURI = item.getNamespaceURI();
                String localName = item.getLocalName();
                if (XMPConst.XML_LANG.equals(item.getNodeName())) {
                    addQualifierNode(addChildNode, XMPConst.XML_LANG, item.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"datatype".equals(localName))) {
                    throw new XMPException("Invalid attribute for literal property element", 202);
                }
            }
        }
        String str = "";
        for (int i2 = 0; i2 < node.getChildNodes().getLength(); i2++) {
            Node item2 = node.getChildNodes().item(i2);
            if (item2.getNodeType() == 3) {
                str = AnonymousClass006.A07(str, item2.getNodeValue());
            } else {
                throw new XMPException("Invalid child of literal property element", 202);
            }
        }
        addChildNode.value = str;
    }

    public static void rdf_NodeElementAttrs(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        int i = 0;
        for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
            Node item = node.getAttributes().item(i2);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                int rDFTermKind = getRDFTermKind(item);
                if (rDFTermKind == 0) {
                    addChildNode(xMPMetaImpl, xMPNode, item, item.getNodeValue(), z);
                } else if (rDFTermKind != 6 && rDFTermKind != 2 && rDFTermKind != 3) {
                    throw new XMPException("Invalid nodeElement attribute", 202);
                } else if (i <= 0) {
                    i++;
                    if (z && rDFTermKind == 3) {
                        String str = xMPNode.name;
                        if (str == null || str.length() <= 0) {
                            xMPNode.name = item.getNodeValue();
                        } else if (!str.equals(item.getNodeValue())) {
                            throw new XMPException("Mismatched top level rdf:about values", 203);
                        }
                    }
                } else {
                    throw new XMPException("Mutally exclusive about, ID, nodeID attributes", 202);
                }
            }
        }
    }

    public static void rdf_NodeElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node) throws XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node item = node.getChildNodes().item(i);
            if (!isWhitespaceNode(item)) {
                rdf_NodeElement(xMPMetaImpl, xMPNode, item, true);
            }
        }
    }

    public static void rdf_PropertyElementList(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node item = node.getChildNodes().item(i);
            if (!isWhitespaceNode(item)) {
                if (item.getNodeType() == 1) {
                    rdf_PropertyElement(xMPMetaImpl, xMPNode, item, z);
                } else {
                    throw new XMPException("Expected property element node not found", 202);
                }
            }
        }
    }

    public static XMPNode addChildNode(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, String str, boolean z) throws XMPException {
        XMPSchemaRegistry xMPSchemaRegistry = XMPMetaFactory.schema;
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null) {
            if (XMPConst.NS_DC_DEPRECATED.equals(namespaceURI)) {
                namespaceURI = XMPConst.NS_DC;
            }
            String namespacePrefix = xMPSchemaRegistry.getNamespacePrefix(namespaceURI);
            if (namespacePrefix == null) {
                namespacePrefix = xMPSchemaRegistry.registerNamespace(namespaceURI, node.getPrefix() != null ? node.getPrefix() : DEFAULT_PREFIX);
            }
            String A07 = AnonymousClass006.A07(namespacePrefix, node.getLocalName());
            PropertyOptions propertyOptions = new PropertyOptions();
            boolean z2 = false;
            if (z) {
                xMPNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.tree, namespaceURI, DEFAULT_PREFIX, true);
                xMPNode.implicit = false;
                if (xMPSchemaRegistry.findAlias(A07) != null) {
                    xMPMetaImpl.tree.hasAliases = true;
                    xMPNode.hasAliases = true;
                    z2 = true;
                }
            }
            boolean equals = "rdf:li".equals(A07);
            boolean equals2 = "rdf:value".equals(A07);
            XMPNode xMPNode2 = new XMPNode(A07, str, propertyOptions);
            xMPNode2.alias = z2;
            if (!equals2) {
                xMPNode.addChild(xMPNode2);
            } else {
                xMPNode.addChild(1, xMPNode2);
                if (z || !xMPNode.getOptions().getOption(256)) {
                    throw new XMPException("Misplaced rdf:value element", 202);
                }
                xMPNode.hasValueChild = true;
            }
            if (equals) {
                if (xMPNode.getOptions().getOption(512)) {
                    xMPNode2.name = "[]";
                } else {
                    throw new XMPException("Misplaced rdf:li element", 202);
                }
            }
            return xMPNode2;
        }
        throw new XMPException("XML namespace required for all elements and attributes", 202);
    }

    public static XMPNode addQualifierNode(XMPNode xMPNode, String str, String str2) throws XMPException {
        if (XMPConst.XML_LANG.equals(str)) {
            str2 = Utils.normalizeLangValue(str2);
        }
        XMPNode xMPNode2 = new XMPNode(str, str2, null);
        xMPNode.addQualifier(xMPNode2);
        return xMPNode2;
    }

    public static boolean isPropertyElementName(int i) {
        if (i == 8 || isOldTerm(i)) {
            return false;
        }
        return !isCoreSyntaxTerm(i);
    }

    public static XMPMetaImpl parse(Node node) throws XMPException {
        XMPMetaImpl xMPMetaImpl = new XMPMetaImpl();
        rdf_RDF(xMPMetaImpl, node);
        return xMPMetaImpl;
    }

    public static void rdf_EmptyPropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        boolean z2;
        String str;
        String str2;
        if (!node.hasChildNodes()) {
            Node node2 = null;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node item = node.getAttributes().item(i);
                if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                    int rDFTermKind = getRDFTermKind(item);
                    if (rDFTermKind != 0) {
                        if (rDFTermKind == 2) {
                            continue;
                        } else if (rDFTermKind != 5) {
                            if (rDFTermKind != 6) {
                                throw new XMPException("Unrecognized attribute of empty property element", 202);
                            } else if (!z4) {
                                z6 = true;
                            } else {
                                throw new XMPException("Empty property element can't have both rdf:resource and rdf:nodeID", 202);
                            }
                        } else if (z6) {
                            throw new XMPException("Empty property element can't have both rdf:resource and rdf:nodeID", 202);
                        } else if (!z3) {
                            node2 = item;
                            z4 = true;
                        } else {
                            throw new XMPException("Empty property element can't have both rdf:value and rdf:resource", 203);
                        }
                    } else if (!"value".equals(item.getLocalName()) || !XMPConst.NS_RDF.equals(item.getNamespaceURI())) {
                        if (!XMPConst.XML_LANG.equals(item.getNodeName())) {
                            z5 = true;
                        }
                    } else if (!z4) {
                        node2 = item;
                        z3 = true;
                    } else {
                        throw new XMPException("Empty property element can't have both rdf:value and rdf:resource", 203);
                    }
                }
            }
            String str3 = "";
            XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, str3, z);
            if (z3 || z4) {
                if (node2 != null) {
                    str3 = node2.getNodeValue();
                }
                addChildNode.value = str3;
                if (!z3) {
                    addChildNode.getOptions().setOption(2, true);
                }
                z2 = false;
            } else {
                if (z5) {
                    z2 = true;
                    addChildNode.getOptions().setOption(256, true);
                }
                z2 = false;
            }
            for (int i2 = 0; i2 < node.getAttributes().getLength(); i2++) {
                Node item2 = node.getAttributes().item(i2);
                if (item2 != node2 && !"xmlns".equals(item2.getPrefix()) && (item2.getPrefix() != null || !"xmlns".equals(item2.getNodeName()))) {
                    int rDFTermKind2 = getRDFTermKind(item2);
                    if (rDFTermKind2 == 0) {
                        str2 = item2.getNodeName();
                        if (!z2) {
                            str = item2.getNodeValue();
                        } else if (XMPConst.XML_LANG.equals(str2)) {
                            addQualifierNode(addChildNode, XMPConst.XML_LANG, item2.getNodeValue());
                        } else {
                            addChildNode(xMPMetaImpl, addChildNode, item2, item2.getNodeValue(), false);
                        }
                    } else if (rDFTermKind2 == 2) {
                        continue;
                    } else if (rDFTermKind2 == 5) {
                        str = item2.getNodeValue();
                        str2 = "rdf:resource";
                    } else if (rDFTermKind2 != 6) {
                        throw new XMPException("Unrecognized attribute of empty property element", 202);
                    }
                    addQualifierNode(addChildNode, str2, str);
                }
            }
            return;
        }
        throw new XMPException("Nested content not allowed with rdf:resource or property attributes", 202);
    }

    public static void rdf_ParseTypeCollectionPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeCollection property element not allowed", 203);
    }

    public static void rdf_ParseTypeLiteralPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeLiteral property element not allowed", 203);
    }

    public static void rdf_ParseTypeOtherPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeOther property element not allowed", 203);
    }

    public static void rdf_ParseTypeResourcePropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        XMPNode addChildNode = addChildNode(xMPMetaImpl, xMPNode, node, "", z);
        addChildNode.getOptions().setOption(256, true);
        for (int i = 0; i < node.getAttributes().getLength(); i++) {
            Node item = node.getAttributes().item(i);
            if (!"xmlns".equals(item.getPrefix()) && (item.getPrefix() != null || !"xmlns".equals(item.getNodeName()))) {
                String localName = item.getLocalName();
                String namespaceURI = item.getNamespaceURI();
                if (XMPConst.XML_LANG.equals(item.getNodeName())) {
                    addQualifierNode(addChildNode, XMPConst.XML_LANG, item.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(namespaceURI) || (!"ID".equals(localName) && !"parseType".equals(localName))) {
                    throw new XMPException("Invalid attribute for ParseTypeResource property element", 202);
                }
            }
        }
        rdf_PropertyElementList(xMPMetaImpl, addChildNode, node, false);
        if (addChildNode.hasValueChild) {
            fixupQualifiedNode(addChildNode);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void rdf_ResourcePropertyElement(com.adobe.xmp.impl.XMPMetaImpl r11, com.adobe.xmp.impl.XMPNode r12, org.w3c.dom.Node r13, boolean r14) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 343
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ParseRDF.rdf_ResourcePropertyElement(com.adobe.xmp.impl.XMPMetaImpl, com.adobe.xmp.impl.XMPNode, org.w3c.dom.Node, boolean):void");
    }

    public static int getRDFTermKind(Node node) {
        String localName = node.getLocalName();
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null && (("about".equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && XMPConst.NS_RDF.equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespaceURI = XMPConst.NS_RDF;
        }
        if (!XMPConst.NS_RDF.equals(namespaceURI)) {
            return 0;
        }
        if ("li".equals(localName)) {
            return 9;
        }
        if ("parseType".equals(localName)) {
            return 4;
        }
        if ("Description".equals(localName)) {
            return 8;
        }
        if ("about".equals(localName)) {
            return 3;
        }
        if ("resource".equals(localName)) {
            return 5;
        }
        if ("RDF".equals(localName)) {
            return 1;
        }
        if ("ID".equals(localName)) {
            return 2;
        }
        if ("nodeID".equals(localName)) {
            return 6;
        }
        if ("datatype".equals(localName)) {
            return 7;
        }
        if ("aboutEach".equals(localName)) {
            return 10;
        }
        if ("aboutEachPrefix".equals(localName)) {
            return 11;
        }
        if (!"bagID".equals(localName)) {
            return 0;
        }
        return 12;
    }

    public static boolean isWhitespaceNode(Node node) {
        if (node.getNodeType() == 3) {
            String nodeValue = node.getNodeValue();
            for (int i = 0; i < nodeValue.length(); i++) {
                if (Character.isWhitespace(nodeValue.charAt(i))) {
                }
            }
            return true;
        }
        return false;
    }

    public static void rdf_NodeElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        int rDFTermKind = getRDFTermKind(node);
        if (rDFTermKind != 8 && rDFTermKind != 0) {
            throw new XMPException("Node element must be rdf:Description or typed node", 202);
        } else if (!z || rDFTermKind != 0) {
            rdf_NodeElementAttrs(xMPMetaImpl, xMPNode, node, z);
            rdf_PropertyElementList(xMPMetaImpl, xMPNode, node, z);
        } else {
            throw new XMPException("Top level typed node not allowed", 203);
        }
    }

    public static void rdf_PropertyElement(XMPMetaImpl xMPMetaImpl, XMPNode xMPNode, Node node, boolean z) throws XMPException {
        if (isPropertyElementName(getRDFTermKind(node))) {
            NamedNodeMap attributes = node.getAttributes();
            ArrayList arrayList = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                if ("xmlns".equals(item.getPrefix()) || (item.getPrefix() == null && "xmlns".equals(item.getNodeName()))) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(item.getNodeName());
                }
            }
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    attributes.removeNamedItem((String) it.next());
                }
            }
            if (attributes.getLength() <= 3) {
                for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                    Node item2 = attributes.item(i2);
                    String localName = item2.getLocalName();
                    String namespaceURI = item2.getNamespaceURI();
                    String nodeValue = item2.getNodeValue();
                    if (!XMPConst.XML_LANG.equals(item2.getNodeName()) || ("ID".equals(localName) && XMPConst.NS_RDF.equals(namespaceURI))) {
                        if (!"datatype".equals(localName) || !XMPConst.NS_RDF.equals(namespaceURI)) {
                            if ("parseType".equals(localName) && XMPConst.NS_RDF.equals(namespaceURI)) {
                                if ("Literal".equals(nodeValue)) {
                                    rdf_ParseTypeLiteralPropertyElement();
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } else if ("Resource".equals(nodeValue)) {
                                    rdf_ParseTypeResourcePropertyElement(xMPMetaImpl, xMPNode, node, z);
                                    return;
                                } else if ("Collection".equals(nodeValue)) {
                                    rdf_ParseTypeCollectionPropertyElement();
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                } else {
                                    rdf_ParseTypeOtherPropertyElement();
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        }
                        rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
                        return;
                    }
                }
                if (node.hasChildNodes()) {
                    for (int i3 = 0; i3 < node.getChildNodes().getLength(); i3++) {
                        if (node.getChildNodes().item(i3).getNodeType() != 3) {
                            rdf_ResourcePropertyElement(xMPMetaImpl, xMPNode, node, z);
                            return;
                        }
                    }
                    rdf_LiteralPropertyElement(xMPMetaImpl, xMPNode, node, z);
                    return;
                }
            }
            rdf_EmptyPropertyElement(xMPMetaImpl, xMPNode, node, z);
            return;
        }
        throw new XMPException("Invalid property element name", 202);
    }

    public static void rdf_RDF(XMPMetaImpl xMPMetaImpl, Node node) throws XMPException {
        if (node.hasAttributes()) {
            rdf_NodeElementList(xMPMetaImpl, xMPMetaImpl.tree, node);
            return;
        }
        throw new XMPException("Invalid attributes of rdf:RDF element", 202);
    }
}
