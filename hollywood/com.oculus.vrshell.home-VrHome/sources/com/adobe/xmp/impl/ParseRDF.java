package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.PropertyOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ParseRDF implements XMPConst, XMPError {
    static final /* synthetic */ boolean $assertionsDisabled = (!ParseRDF.class.desiredAssertionStatus());
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

    static XMPMetaImpl parse(Node xmlRoot) throws XMPException {
        XMPMetaImpl xmp = new XMPMetaImpl();
        rdf_RDF(xmp, xmlRoot);
        return xmp;
    }

    static void rdf_RDF(XMPMetaImpl xmp, Node rdfRdfNode) throws XMPException {
        if (rdfRdfNode.hasAttributes()) {
            rdf_NodeElementList(xmp, xmp.getRoot(), rdfRdfNode);
            return;
        }
        throw new XMPException("Invalid attributes of rdf:RDF element", XMPError.BADRDF);
    }

    private static void rdf_NodeElementList(XMPMetaImpl xmp, XMPNode xmpParent, Node rdfRdfNode) throws XMPException {
        for (int i = 0; i < rdfRdfNode.getChildNodes().getLength(); i++) {
            Node child = rdfRdfNode.getChildNodes().item(i);
            if (!isWhitespaceNode(child)) {
                rdf_NodeElement(xmp, xmpParent, child, true);
            }
        }
    }

    private static void rdf_NodeElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        int nodeTerm = getRDFTermKind(xmlNode);
        if (nodeTerm != 8 && nodeTerm != 0) {
            throw new XMPException("Node element must be rdf:Description or typed node", XMPError.BADRDF);
        } else if (!isTopLevel || nodeTerm != 0) {
            rdf_NodeElementAttrs(xmp, xmpParent, xmlNode, isTopLevel);
            rdf_PropertyElementList(xmp, xmpParent, xmlNode, isTopLevel);
        } else {
            throw new XMPException("Top level typed node not allowed", XMPError.BADXMP);
        }
    }

    private static void rdf_NodeElementAttrs(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        int exclusiveAttrs = 0;
        for (int i = 0; i < xmlNode.getAttributes().getLength(); i++) {
            Node attribute = xmlNode.getAttributes().item(i);
            if (!"xmlns".equals(attribute.getPrefix()) && (attribute.getPrefix() != null || !"xmlns".equals(attribute.getNodeName()))) {
                int attrTerm = getRDFTermKind(attribute);
                switch (attrTerm) {
                    case 0:
                        addChildNode(xmp, xmpParent, attribute, attribute.getNodeValue(), isTopLevel);
                        continue;
                    case 1:
                    case 4:
                    case 5:
                    default:
                        throw new XMPException("Invalid nodeElement attribute", XMPError.BADRDF);
                    case 2:
                    case 3:
                    case 6:
                        if (exclusiveAttrs <= 0) {
                            exclusiveAttrs++;
                            if (isTopLevel && attrTerm == 3) {
                                if (xmpParent.getName() == null || xmpParent.getName().length() <= 0) {
                                    xmpParent.setName(attribute.getNodeValue());
                                    break;
                                } else if (xmpParent.getName().equals(attribute.getNodeValue())) {
                                    break;
                                } else {
                                    throw new XMPException("Mismatched top level rdf:about values", XMPError.BADXMP);
                                }
                            }
                        } else {
                            throw new XMPException("Mutally exclusive about, ID, nodeID attributes", XMPError.BADRDF);
                        }
                        break;
                }
            }
        }
    }

    private static void rdf_PropertyElementList(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlParent, boolean isTopLevel) throws XMPException {
        for (int i = 0; i < xmlParent.getChildNodes().getLength(); i++) {
            Node currChild = xmlParent.getChildNodes().item(i);
            if (!isWhitespaceNode(currChild)) {
                if (currChild.getNodeType() != 1) {
                    throw new XMPException("Expected property element node not found", XMPError.BADRDF);
                }
                rdf_PropertyElement(xmp, xmpParent, currChild, isTopLevel);
            }
        }
    }

    private static void rdf_PropertyElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        if (!isPropertyElementName(getRDFTermKind(xmlNode))) {
            throw new XMPException("Invalid property element name", XMPError.BADRDF);
        }
        NamedNodeMap attributes = xmlNode.getAttributes();
        List<String> nsAttrs = null;
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            if ("xmlns".equals(attribute.getPrefix()) || (attribute.getPrefix() == null && "xmlns".equals(attribute.getNodeName()))) {
                if (nsAttrs == null) {
                    nsAttrs = new ArrayList();
                }
                nsAttrs.add(attribute.getNodeName());
            }
        }
        if (nsAttrs != null) {
            for (String ns : nsAttrs) {
                attributes.removeNamedItem(ns);
            }
        }
        if (attributes.getLength() > 3) {
            rdf_EmptyPropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
            return;
        }
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            Node attribute2 = attributes.item(i2);
            String attrLocal = attribute2.getLocalName();
            String attrNS = attribute2.getNamespaceURI();
            String attrValue = attribute2.getNodeValue();
            if (!XMPConst.XML_LANG.equals(attribute2.getNodeName()) || ("ID".equals(attrLocal) && XMPConst.NS_RDF.equals(attrNS))) {
                if ("datatype".equals(attrLocal) && XMPConst.NS_RDF.equals(attrNS)) {
                    rdf_LiteralPropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
                    return;
                } else if (!"parseType".equals(attrLocal) || !XMPConst.NS_RDF.equals(attrNS)) {
                    rdf_EmptyPropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
                    return;
                } else if ("Literal".equals(attrValue)) {
                    rdf_ParseTypeLiteralPropertyElement();
                    return;
                } else if ("Resource".equals(attrValue)) {
                    rdf_ParseTypeResourcePropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
                    return;
                } else if ("Collection".equals(attrValue)) {
                    rdf_ParseTypeCollectionPropertyElement();
                    return;
                } else {
                    rdf_ParseTypeOtherPropertyElement();
                    return;
                }
            }
        }
        if (xmlNode.hasChildNodes()) {
            for (int i3 = 0; i3 < xmlNode.getChildNodes().getLength(); i3++) {
                if (xmlNode.getChildNodes().item(i3).getNodeType() != 3) {
                    rdf_ResourcePropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
                    return;
                }
            }
            rdf_LiteralPropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
            return;
        }
        rdf_EmptyPropertyElement(xmp, xmpParent, xmlNode, isTopLevel);
    }

    private static void rdf_ResourcePropertyElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        if (!isTopLevel || !"iX:changes".equals(xmlNode.getNodeName())) {
            XMPNode newCompound = addChildNode(xmp, xmpParent, xmlNode, "", isTopLevel);
            for (int i = 0; i < xmlNode.getAttributes().getLength(); i++) {
                Node attribute = xmlNode.getAttributes().item(i);
                if (!"xmlns".equals(attribute.getPrefix()) && (attribute.getPrefix() != null || !"xmlns".equals(attribute.getNodeName()))) {
                    String attrLocal = attribute.getLocalName();
                    String attrNS = attribute.getNamespaceURI();
                    if (XMPConst.XML_LANG.equals(attribute.getNodeName())) {
                        addQualifierNode(newCompound, XMPConst.XML_LANG, attribute.getNodeValue());
                    } else if (!"ID".equals(attrLocal) || !XMPConst.NS_RDF.equals(attrNS)) {
                        throw new XMPException("Invalid attribute for resource property element", XMPError.BADRDF);
                    }
                }
            }
            boolean found = false;
            for (int i2 = 0; i2 < xmlNode.getChildNodes().getLength(); i2++) {
                Node currChild = xmlNode.getChildNodes().item(i2);
                if (!isWhitespaceNode(currChild)) {
                    if (currChild.getNodeType() == 1 && !found) {
                        boolean isRDF = XMPConst.NS_RDF.equals(currChild.getNamespaceURI());
                        String childLocal = currChild.getLocalName();
                        if (isRDF && "Bag".equals(childLocal)) {
                            newCompound.getOptions().setArray(true);
                        } else if (isRDF && "Seq".equals(childLocal)) {
                            newCompound.getOptions().setArray(true).setArrayOrdered(true);
                        } else if (!isRDF || !"Alt".equals(childLocal)) {
                            newCompound.getOptions().setStruct(true);
                            if (!isRDF && !"Description".equals(childLocal)) {
                                String typeName = currChild.getNamespaceURI();
                                if (typeName == null) {
                                    throw new XMPException("All XML elements must be in a namespace", XMPError.BADXMP);
                                }
                                addQualifierNode(newCompound, XMPConst.RDF_TYPE, typeName + ':' + childLocal);
                            }
                        } else {
                            newCompound.getOptions().setArray(true).setArrayOrdered(true).setArrayAlternate(true);
                        }
                        rdf_NodeElement(xmp, newCompound, currChild, false);
                        if (newCompound.getHasValueChild()) {
                            fixupQualifiedNode(newCompound);
                        } else if (newCompound.getOptions().isArrayAlternate()) {
                            XMPNodeUtils.detectAltText(newCompound);
                        }
                        found = true;
                    } else if (found) {
                        throw new XMPException("Invalid child of resource property element", XMPError.BADRDF);
                    } else {
                        throw new XMPException("Children of resource property element must be XML elements", XMPError.BADRDF);
                    }
                }
            }
            if (!found) {
                throw new XMPException("Missing child of resource property element", XMPError.BADRDF);
            }
        }
    }

    private static void rdf_LiteralPropertyElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        XMPNode newChild = addChildNode(xmp, xmpParent, xmlNode, null, isTopLevel);
        for (int i = 0; i < xmlNode.getAttributes().getLength(); i++) {
            Node attribute = xmlNode.getAttributes().item(i);
            if (!"xmlns".equals(attribute.getPrefix()) && (attribute.getPrefix() != null || !"xmlns".equals(attribute.getNodeName()))) {
                String attrNS = attribute.getNamespaceURI();
                String attrLocal = attribute.getLocalName();
                if (XMPConst.XML_LANG.equals(attribute.getNodeName())) {
                    addQualifierNode(newChild, XMPConst.XML_LANG, attribute.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(attrNS) || (!"ID".equals(attrLocal) && !"datatype".equals(attrLocal))) {
                    throw new XMPException("Invalid attribute for literal property element", XMPError.BADRDF);
                }
            }
        }
        String textValue = "";
        for (int i2 = 0; i2 < xmlNode.getChildNodes().getLength(); i2++) {
            Node child = xmlNode.getChildNodes().item(i2);
            if (child.getNodeType() == 3) {
                textValue = textValue + child.getNodeValue();
            } else {
                throw new XMPException("Invalid child of literal property element", XMPError.BADRDF);
            }
        }
        newChild.setValue(textValue);
    }

    private static void rdf_ParseTypeLiteralPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeLiteral property element not allowed", XMPError.BADXMP);
    }

    private static void rdf_ParseTypeResourcePropertyElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        XMPNode newStruct = addChildNode(xmp, xmpParent, xmlNode, "", isTopLevel);
        newStruct.getOptions().setStruct(true);
        for (int i = 0; i < xmlNode.getAttributes().getLength(); i++) {
            Node attribute = xmlNode.getAttributes().item(i);
            if (!"xmlns".equals(attribute.getPrefix()) && (attribute.getPrefix() != null || !"xmlns".equals(attribute.getNodeName()))) {
                String attrLocal = attribute.getLocalName();
                String attrNS = attribute.getNamespaceURI();
                if (XMPConst.XML_LANG.equals(attribute.getNodeName())) {
                    addQualifierNode(newStruct, XMPConst.XML_LANG, attribute.getNodeValue());
                } else if (!XMPConst.NS_RDF.equals(attrNS) || (!"ID".equals(attrLocal) && !"parseType".equals(attrLocal))) {
                    throw new XMPException("Invalid attribute for ParseTypeResource property element", XMPError.BADRDF);
                }
            }
        }
        rdf_PropertyElementList(xmp, newStruct, xmlNode, false);
        if (newStruct.getHasValueChild()) {
            fixupQualifiedNode(newStruct);
        }
    }

    private static void rdf_ParseTypeCollectionPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeCollection property element not allowed", XMPError.BADXMP);
    }

    private static void rdf_ParseTypeOtherPropertyElement() throws XMPException {
        throw new XMPException("ParseTypeOther property element not allowed", XMPError.BADXMP);
    }

    private static void rdf_EmptyPropertyElement(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, boolean isTopLevel) throws XMPException {
        boolean hasPropertyAttrs = false;
        boolean hasResourceAttr = false;
        boolean hasNodeIDAttr = false;
        boolean hasValueAttr = false;
        Node valueNode = null;
        if (xmlNode.hasChildNodes()) {
            throw new XMPException("Nested content not allowed with rdf:resource or property attributes", XMPError.BADRDF);
        }
        for (int i = 0; i < xmlNode.getAttributes().getLength(); i++) {
            Node attribute = xmlNode.getAttributes().item(i);
            if (!"xmlns".equals(attribute.getPrefix()) && (attribute.getPrefix() != null || !"xmlns".equals(attribute.getNodeName()))) {
                switch (getRDFTermKind(attribute)) {
                    case 0:
                        if (!"value".equals(attribute.getLocalName()) || !XMPConst.NS_RDF.equals(attribute.getNamespaceURI())) {
                            if (!XMPConst.XML_LANG.equals(attribute.getNodeName())) {
                                hasPropertyAttrs = true;
                                break;
                            } else {
                                break;
                            }
                        } else if (hasResourceAttr) {
                            throw new XMPException("Empty property element can't have both rdf:value and rdf:resource", XMPError.BADXMP);
                        } else {
                            hasValueAttr = true;
                            valueNode = attribute;
                            continue;
                        }
                    case 1:
                    case 3:
                    case 4:
                    default:
                        throw new XMPException("Unrecognized attribute of empty property element", XMPError.BADRDF);
                    case 2:
                        break;
                    case 5:
                        if (hasNodeIDAttr) {
                            throw new XMPException("Empty property element can't have both rdf:resource and rdf:nodeID", XMPError.BADRDF);
                        } else if (hasValueAttr) {
                            throw new XMPException("Empty property element can't have both rdf:value and rdf:resource", XMPError.BADXMP);
                        } else {
                            hasResourceAttr = true;
                            if (!hasValueAttr) {
                                valueNode = attribute;
                                break;
                            } else {
                                continue;
                            }
                        }
                    case 6:
                        if (hasResourceAttr) {
                            throw new XMPException("Empty property element can't have both rdf:resource and rdf:nodeID", XMPError.BADRDF);
                        }
                        hasNodeIDAttr = true;
                        continue;
                }
            }
        }
        XMPNode childNode = addChildNode(xmp, xmpParent, xmlNode, "", isTopLevel);
        boolean childIsStruct = false;
        if (hasValueAttr || hasResourceAttr) {
            childNode.setValue(valueNode != null ? valueNode.getNodeValue() : "");
            if (!hasValueAttr) {
                childNode.getOptions().setURI(true);
            }
        } else if (hasPropertyAttrs) {
            childNode.getOptions().setStruct(true);
            childIsStruct = true;
        }
        for (int i2 = 0; i2 < xmlNode.getAttributes().getLength(); i2++) {
            Node attribute2 = xmlNode.getAttributes().item(i2);
            if (attribute2 != valueNode && !"xmlns".equals(attribute2.getPrefix()) && (attribute2.getPrefix() != null || !"xmlns".equals(attribute2.getNodeName()))) {
                switch (getRDFTermKind(attribute2)) {
                    case 0:
                        if (childIsStruct) {
                            if (XMPConst.XML_LANG.equals(attribute2.getNodeName())) {
                                addQualifierNode(childNode, XMPConst.XML_LANG, attribute2.getNodeValue());
                                break;
                            } else {
                                addChildNode(xmp, childNode, attribute2, attribute2.getNodeValue(), false);
                                break;
                            }
                        } else {
                            addQualifierNode(childNode, attribute2.getNodeName(), attribute2.getNodeValue());
                            continue;
                        }
                    case 1:
                    case 3:
                    case 4:
                    default:
                        throw new XMPException("Unrecognized attribute of empty property element", XMPError.BADRDF);
                    case 2:
                    case 6:
                        break;
                    case 5:
                        addQualifierNode(childNode, "rdf:resource", attribute2.getNodeValue());
                        continue;
                }
            }
        }
    }

    private static XMPNode addChildNode(XMPMetaImpl xmp, XMPNode xmpParent, Node xmlNode, String value, boolean isTopLevel) throws XMPException {
        XMPSchemaRegistry registry = XMPMetaFactory.getSchemaRegistry();
        String namespace = xmlNode.getNamespaceURI();
        if (namespace != null) {
            if (XMPConst.NS_DC_DEPRECATED.equals(namespace)) {
                namespace = XMPConst.NS_DC;
            }
            String prefix = registry.getNamespacePrefix(namespace);
            if (prefix == null) {
                prefix = registry.registerNamespace(namespace, xmlNode.getPrefix() != null ? xmlNode.getPrefix() : DEFAULT_PREFIX);
            }
            String childName = prefix + xmlNode.getLocalName();
            PropertyOptions childOptions = new PropertyOptions();
            boolean isAlias = false;
            if (isTopLevel) {
                XMPNode schemaNode = XMPNodeUtils.findSchemaNode(xmp.getRoot(), namespace, DEFAULT_PREFIX, true);
                schemaNode.setImplicit(false);
                xmpParent = schemaNode;
                if (registry.findAlias(childName) != null) {
                    isAlias = true;
                    xmp.getRoot().setHasAliases(true);
                    schemaNode.setHasAliases(true);
                }
            }
            boolean isArrayItem = "rdf:li".equals(childName);
            boolean isValueNode = "rdf:value".equals(childName);
            XMPNode newChild = new XMPNode(childName, value, childOptions);
            newChild.setAlias(isAlias);
            if (!isValueNode) {
                xmpParent.addChild(newChild);
            } else {
                xmpParent.addChild(1, newChild);
            }
            if (isValueNode) {
                if (isTopLevel || !xmpParent.getOptions().isStruct()) {
                    throw new XMPException("Misplaced rdf:value element", XMPError.BADRDF);
                }
                xmpParent.setHasValueChild(true);
            }
            if (isArrayItem) {
                if (!xmpParent.getOptions().isArray()) {
                    throw new XMPException("Misplaced rdf:li element", XMPError.BADRDF);
                }
                newChild.setName(XMPConst.ARRAY_ITEM_NAME);
            }
            return newChild;
        }
        throw new XMPException("XML namespace required for all elements and attributes", XMPError.BADRDF);
    }

    private static XMPNode addQualifierNode(XMPNode xmpParent, String name, String value) throws XMPException {
        if (XMPConst.XML_LANG.equals(name)) {
            value = Utils.normalizeLangValue(value);
        }
        XMPNode newQual = new XMPNode(name, value, null);
        xmpParent.addQualifier(newQual);
        return newQual;
    }

    private static void fixupQualifiedNode(XMPNode xmpParent) throws XMPException {
        if ($assertionsDisabled || (xmpParent.getOptions().isStruct() && xmpParent.hasChildren())) {
            XMPNode valueNode = xmpParent.getChild(1);
            if ($assertionsDisabled || "rdf:value".equals(valueNode.getName())) {
                if (valueNode.getOptions().getHasLanguage()) {
                    if (xmpParent.getOptions().getHasLanguage()) {
                        throw new XMPException("Redundant xml:lang for rdf:value element", XMPError.BADXMP);
                    }
                    XMPNode langQual = valueNode.getQualifier(1);
                    valueNode.removeQualifier(langQual);
                    xmpParent.addQualifier(langQual);
                }
                for (int i = 1; i <= valueNode.getQualifierLength(); i++) {
                    xmpParent.addQualifier(valueNode.getQualifier(i));
                }
                for (int i2 = 2; i2 <= xmpParent.getChildrenLength(); i2++) {
                    xmpParent.addQualifier(xmpParent.getChild(i2));
                }
                if ($assertionsDisabled || xmpParent.getOptions().isStruct() || xmpParent.getHasValueChild()) {
                    xmpParent.setHasValueChild(false);
                    xmpParent.getOptions().setStruct(false);
                    xmpParent.getOptions().mergeWith(valueNode.getOptions());
                    xmpParent.setValue(valueNode.getValue());
                    xmpParent.removeChildren();
                    Iterator it = valueNode.iterateChildren();
                    while (it.hasNext()) {
                        xmpParent.addChild((XMPNode) it.next());
                    }
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private static boolean isWhitespaceNode(Node node) {
        if (node.getNodeType() != 3) {
            return false;
        }
        String value = node.getNodeValue();
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPropertyElementName(int term) {
        if (term == 8 || isOldTerm(term) || isCoreSyntaxTerm(term)) {
            return false;
        }
        return true;
    }

    private static boolean isOldTerm(int term) {
        return 10 <= term && term <= 12;
    }

    private static boolean isCoreSyntaxTerm(int term) {
        return 1 <= term && term <= 7;
    }

    private static int getRDFTermKind(Node node) {
        String localName = node.getLocalName();
        String namespace = node.getNamespaceURI();
        if (namespace == null && (("about".equals(localName) || "ID".equals(localName)) && (node instanceof Attr) && XMPConst.NS_RDF.equals(((Attr) node).getOwnerElement().getNamespaceURI()))) {
            namespace = XMPConst.NS_RDF;
        }
        if (XMPConst.NS_RDF.equals(namespace)) {
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
            if ("bagID".equals(localName)) {
                return 12;
            }
        }
        return 0;
    }
}
