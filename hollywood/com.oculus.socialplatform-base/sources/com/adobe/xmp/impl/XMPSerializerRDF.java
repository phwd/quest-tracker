package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.options.SerializeOptions;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class XMPSerializerRDF {
    public static final int DEFAULT_PAD = 2048;
    public static final String PACKET_HEADER = "<?xpacket begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"?>";
    public static final String PACKET_TRAILER = "<?xpacket end=\"";
    public static final String PACKET_TRAILER2 = "\"?>";
    public static final Set RDF_ATTR_QUALIFIER = new HashSet(Arrays.asList(XMPConst.XML_LANG, "rdf:resource", "rdf:ID", "rdf:bagID", "rdf:nodeID"));
    public static final String RDF_RDF_END = "</rdf:RDF>";
    public static final String RDF_RDF_START = "<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">";
    public static final String RDF_SCHEMA_END = "</rdf:Description>";
    public static final String RDF_SCHEMA_START = "<rdf:Description rdf:about=";
    public static final String RDF_STRUCT_END = "</rdf:Description>";
    public static final String RDF_STRUCT_START = "<rdf:Description";
    public static final String RDF_XMPMETA_END = "</x:xmpmeta>";
    public static final String RDF_XMPMETA_START = "<x:xmpmeta xmlns:x=\"adobe:ns:meta/\" x:xmptk=\"";
    public SerializeOptions options;
    public CountOutputStream outputStream;
    public int padding;
    public int unicodeSize = 1;
    public OutputStreamWriter writer;
    public XMPMetaImpl xmp;

    private void appendNodeValue(String str, boolean z) throws IOException {
        write(Utils.escapeXML(str, z, true));
    }

    private void serializeCompactRDFSchemas() throws IOException, XMPException {
        String str;
        writeIndent(2);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        Iterator iterateChildren = this.xmp.tree.iterateChildren();
        while (iterateChildren.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren.next(), hashSet, 4);
        }
        boolean z = true;
        Iterator iterateChildren2 = this.xmp.tree.iterateChildren();
        while (iterateChildren2.hasNext()) {
            z &= serializeCompactRDFAttrProps((XMPNode) iterateChildren2.next(), 3);
        }
        if (!z) {
            write(62);
            writeNewline();
            Iterator iterateChildren3 = this.xmp.tree.iterateChildren();
            while (iterateChildren3.hasNext()) {
                serializeCompactRDFElementProps((XMPNode) iterateChildren3.next(), 3);
            }
            writeIndent(2);
            str = "</rdf:Description>";
        } else {
            str = "/>";
        }
        write(str);
        writeNewline();
    }

    private void serializePrettyRDFSchema(XMPNode xMPNode) throws IOException, XMPException {
        writeIndent(2);
        write(RDF_SCHEMA_START);
        writeTreeName();
        HashSet hashSet = new HashSet();
        hashSet.add("xml");
        hashSet.add("rdf");
        declareUsedNamespaces(xMPNode, hashSet, 4);
        write(62);
        writeNewline();
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            serializePrettyRDFProperty((XMPNode) iterateChildren.next(), false, 3);
        }
        writeIndent(2);
        write("</rdf:Description>");
        writeNewline();
    }

    private void addPadding(int i) throws XMPException, IOException {
        SerializeOptions serializeOptions = this.options;
        if (serializeOptions.getOption(512)) {
            int i2 = this.outputStream.bytesWritten + (i * this.unicodeSize);
            int i3 = this.padding;
            if (i2 <= i3) {
                this.padding = i3 - i2;
            } else {
                throw new XMPException("Can't fit into specified packet size", 107);
            }
        }
        int i4 = this.padding / this.unicodeSize;
        this.padding = i4;
        int length = serializeOptions.newline.length();
        if (i4 >= length) {
            int i5 = i4 - length;
            while (true) {
                this.padding = i5;
                int i6 = length + 100;
                if (i5 >= i6) {
                    writeChars(100, ' ');
                    writeNewline();
                    i5 = this.padding - i6;
                } else {
                    writeChars(i5, ' ');
                    writeNewline();
                    return;
                }
            }
        } else {
            writeChars(i4, ' ');
        }
    }

    private void declareNamespace(String str, String str2, Set set, int i) throws IOException {
        if (str2 == null) {
            QName qName = new QName(str);
            if (qName.hasPrefix()) {
                str = qName.prefix;
                str2 = XMPMetaFactory.schema.getNamespaceURI(AnonymousClass006.A07(str, ":"));
                declareNamespace(str, str2, set, i);
            } else {
                return;
            }
        }
        if (!set.contains(str)) {
            writeNewline();
            writeIndent(i);
            write("xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write(34);
            set.add(str);
        }
    }

    private void emitRDFArrayTag(XMPNode xMPNode, boolean z, int i) throws IOException {
        String str;
        String str2;
        String str3;
        if (z || xMPNode.hasChildren()) {
            writeIndent(i);
            if (z) {
                str = "<rdf:";
            } else {
                str = "</rdf:";
            }
            write(str);
            if (xMPNode.getOptions().getOption(2048)) {
                str2 = "Alt";
            } else if (xMPNode.getOptions().getOption(1024)) {
                str2 = "Seq";
            } else {
                str2 = "Bag";
            }
            write(str2);
            if (!z || xMPNode.hasChildren()) {
                str3 = ">";
            } else {
                str3 = "/>";
            }
            write(str3);
            writeNewline();
        }
    }

    private String serializeAsRDF() throws IOException, XMPException {
        if (!this.options.getOption(16)) {
            writeIndent(0);
            write(PACKET_HEADER);
            writeNewline();
        }
        writeIndent(0);
        write(RDF_XMPMETA_START);
        if (!this.options.omitVersionAttribute) {
            write(XMPMetaFactory.getVersionInfo().getMessage());
        }
        write("\">");
        writeNewline();
        writeIndent(1);
        write(RDF_RDF_START);
        writeNewline();
        if (this.options.getOption(64)) {
            serializeCompactRDFSchemas();
        } else {
            serializePrettyRDFSchemas();
        }
        writeIndent(1);
        write(RDF_RDF_END);
        writeNewline();
        writeIndent(0);
        write(RDF_XMPMETA_END);
        writeNewline();
        String str = "";
        SerializeOptions serializeOptions = this.options;
        if (serializeOptions.getOption(16)) {
            return str;
        }
        for (int i = serializeOptions.baseIndent; i > 0; i--) {
            str = AnonymousClass006.A07(str, serializeOptions.indent);
        }
        String A07 = AnonymousClass006.A07(str, PACKET_TRAILER);
        char c = 'w';
        if (serializeOptions.getOption(32)) {
            c = 'r';
        }
        return AnonymousClass006.A07(AnonymousClass006.A01(A07, c), PACKET_TRAILER2);
    }

    private void serializeCompactRDFArrayProp(XMPNode xMPNode, int i) throws IOException, XMPException {
        write(62);
        writeNewline();
        int i2 = i + 1;
        emitRDFArrayTag(xMPNode, true, i2);
        if (xMPNode.getOptions().getOption(4096)) {
            XMPNodeUtils.normalizeLangArray(xMPNode);
        }
        serializeCompactRDFElementProps(xMPNode, i + 2);
        emitRDFArrayTag(xMPNode, false, i2);
    }

    private void serializeCompactRDFGeneralQualifier(int i, XMPNode xMPNode) throws IOException, XMPException {
        write(" rdf:parseType=\"Resource\">");
        writeNewline();
        int i2 = i + 1;
        serializePrettyRDFProperty(xMPNode, true, i2);
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            serializePrettyRDFProperty((XMPNode) iterateQualifier.next(), false, i2);
        }
    }

    private Object[] serializeCompactRDFSimpleProp(XMPNode xMPNode) throws IOException {
        String str;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = bool;
        if (xMPNode.getOptions().getOption(2)) {
            write(" rdf:resource=\"");
            appendNodeValue(xMPNode.value, true);
            str = "\"/>";
        } else {
            String str2 = xMPNode.value;
            if (str2 == null || str2.length() == 0) {
                str = "/>";
            } else {
                write(62);
                appendNodeValue(xMPNode.value, false);
                bool2 = Boolean.FALSE;
                return new Object[]{bool, bool2};
            }
        }
        write(str);
        writeNewline();
        bool = Boolean.FALSE;
        return new Object[]{bool, bool2};
    }

    private void serializePrettyRDFProperty(XMPNode xMPNode, boolean z, int i) throws IOException, XMPException {
        String str;
        String str2 = xMPNode.name;
        if (z) {
            str2 = "rdf:value";
        } else if ("[]".equals(str2)) {
            str2 = "rdf:li";
        }
        writeIndent(i);
        write(60);
        write(str2);
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        boolean z2 = false;
        boolean z3 = false;
        while (iterateQualifier.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateQualifier.next();
            if (!RDF_ATTR_QUALIFIER.contains(xMPNode2.name)) {
                z2 = true;
            } else {
                z3 = "rdf:resource".equals(xMPNode2.name);
                if (!z) {
                    write(32);
                    write(xMPNode2.name);
                    write("=\"");
                    appendNodeValue(xMPNode2.value, true);
                    write(34);
                }
            }
        }
        if (!z2 || z) {
            if (xMPNode.getOptions().isCompositeProperty()) {
                if (xMPNode.getOptions().getOption(512)) {
                    write(62);
                    writeNewline();
                    int i2 = i + 1;
                    emitRDFArrayTag(xMPNode, true, i2);
                    if (xMPNode.getOptions().getOption(4096)) {
                        XMPNodeUtils.normalizeLangArray(xMPNode);
                    }
                    Iterator iterateChildren = xMPNode.iterateChildren();
                    while (iterateChildren.hasNext()) {
                        serializePrettyRDFProperty((XMPNode) iterateChildren.next(), false, i + 2);
                    }
                    emitRDFArrayTag(xMPNode, false, i2);
                } else if (z3) {
                    Iterator iterateChildren2 = xMPNode.iterateChildren();
                    while (iterateChildren2.hasNext()) {
                        XMPNode xMPNode3 = (XMPNode) iterateChildren2.next();
                        if (canBeRDFAttrProp(xMPNode3)) {
                            writeNewline();
                            writeIndent(i + 1);
                            write(32);
                            write(xMPNode3.name);
                            write("=\"");
                            appendNodeValue(xMPNode3.value, true);
                            write(34);
                        } else {
                            throw new XMPException("Can't mix rdf:resource and complex fields", 202);
                        }
                    }
                    write("/>");
                } else if (!xMPNode.hasChildren()) {
                    str = " rdf:parseType=\"Resource\"/>";
                } else {
                    write(" rdf:parseType=\"Resource\">");
                    writeNewline();
                    Iterator iterateChildren3 = xMPNode.iterateChildren();
                    while (iterateChildren3.hasNext()) {
                        serializePrettyRDFProperty((XMPNode) iterateChildren3.next(), false, i + 1);
                    }
                }
                writeIndent(i);
                write("</");
                write(str2);
                write(62);
            } else if (xMPNode.getOptions().getOption(2)) {
                write(" rdf:resource=\"");
                appendNodeValue(xMPNode.value, true);
                str = "\"/>";
            } else {
                String str3 = xMPNode.value;
                if (str3 != null && !"".equals(str3)) {
                    write(62);
                    appendNodeValue(xMPNode.value, false);
                    write("</");
                    write(str2);
                    write(62);
                }
                write("/>");
            }
            write(str);
        } else if (!z3) {
            write(" rdf:parseType=\"Resource\">");
            writeNewline();
            int i3 = i + 1;
            serializePrettyRDFProperty(xMPNode, true, i3);
            Iterator iterateQualifier2 = xMPNode.iterateQualifier();
            while (iterateQualifier2.hasNext()) {
                XMPNode xMPNode4 = (XMPNode) iterateQualifier2.next();
                if (!RDF_ATTR_QUALIFIER.contains(xMPNode4.name)) {
                    serializePrettyRDFProperty(xMPNode4, false, i3);
                }
            }
            writeIndent(i);
            write("</");
            write(str2);
            write(62);
        } else {
            throw new XMPException("Can't mix rdf:resource and general qualifiers", 202);
        }
        writeNewline();
    }

    private void serializePrettyRDFSchemas() throws IOException, XMPException {
        if (this.xmp.tree.getChildrenLength() > 0) {
            Iterator iterateChildren = this.xmp.tree.iterateChildren();
            while (iterateChildren.hasNext()) {
                serializePrettyRDFSchema((XMPNode) iterateChildren.next());
            }
            return;
        }
        writeIndent(2);
        write(RDF_SCHEMA_START);
        writeTreeName();
        write("/>");
        writeNewline();
    }

    private void writeChars(int i, char c) throws IOException {
        while (i > 0) {
            this.writer.write(c);
            i--;
        }
    }

    private void writeIndent(int i) throws IOException {
        for (int i2 = this.options.baseIndent + i; i2 > 0; i2--) {
            this.writer.write(this.options.indent);
        }
    }

    private void writeNewline() throws IOException {
        this.writer.write(this.options.newline);
    }

    private void writeTreeName() throws IOException {
        write(34);
        String str = this.xmp.tree.name;
        if (str != null) {
            appendNodeValue(str, true);
        }
        write(34);
    }

    public void checkOptionsConsistence() throws XMPException {
        SerializeOptions serializeOptions = this.options;
        if (serializeOptions.getEncodeUTF16BE() || serializeOptions.getEncodeUTF16LE()) {
            this.unicodeSize = 2;
        }
        if (!serializeOptions.getOption(512)) {
            if (serializeOptions.getOption(32)) {
                if (serializeOptions.getOption(16) || serializeOptions.getOption(256)) {
                    throw new XMPException("Inconsistent options for read-only packet", 103);
                }
            } else if (!serializeOptions.getOption(16)) {
                if (this.padding == 0) {
                    this.padding = this.unicodeSize << 11;
                }
                if (serializeOptions.getOption(256) && !this.xmp.doesPropertyExist("http://ns.adobe.com/xap/1.0/", "Thumbnails")) {
                    this.padding += this.unicodeSize * 10000;
                    return;
                }
                return;
            } else if (serializeOptions.getOption(256)) {
                throw new XMPException("Inconsistent options for non-packet serialize", 103);
            }
            this.padding = 0;
        } else if (serializeOptions.getOption(16) || serializeOptions.getOption(256)) {
            throw new XMPException("Inconsistent options for exact size serialize", 103);
        } else if ((serializeOptions.padding & (this.unicodeSize - 1)) != 0) {
            throw new XMPException("Exact size must be a multiple of the Unicode element", 103);
        }
    }

    public void serialize(XMPMeta xMPMeta, OutputStream outputStream2, SerializeOptions serializeOptions) throws XMPException {
        try {
            CountOutputStream countOutputStream = new CountOutputStream(outputStream2);
            this.outputStream = countOutputStream;
            this.writer = new OutputStreamWriter(countOutputStream, serializeOptions.getEncoding());
            this.xmp = (XMPMetaImpl) xMPMeta;
            this.options = serializeOptions;
            this.padding = serializeOptions.padding;
            this.writer = new OutputStreamWriter(this.outputStream, serializeOptions.getEncoding());
            checkOptionsConsistence();
            String serializeAsRDF = serializeAsRDF();
            this.writer.flush();
            addPadding(serializeAsRDF.length());
            write(serializeAsRDF);
            this.writer.flush();
            this.outputStream.close();
        } catch (IOException unused) {
            throw new XMPException("Error writing to the OutputStream", 0);
        }
    }

    private boolean canBeRDFAttrProp(XMPNode xMPNode) {
        if (xMPNode.hasQualifier() || xMPNode.getOptions().getOption(2) || xMPNode.getOptions().isCompositeProperty() || "[]".equals(xMPNode.name)) {
            return false;
        }
        return true;
    }

    private void declareUsedNamespaces(XMPNode xMPNode, Set set, int i) throws IOException {
        if (xMPNode.getOptions().isSchemaNode()) {
            String str = xMPNode.value;
            declareNamespace(str.substring(0, str.length() - 1), xMPNode.name, set, i);
        } else if (xMPNode.getOptions().getOption(256)) {
            Iterator iterateChildren = xMPNode.iterateChildren();
            while (iterateChildren.hasNext()) {
                declareNamespace(((XMPNode) iterateChildren.next()).name, null, set, i);
            }
        }
        Iterator iterateChildren2 = xMPNode.iterateChildren();
        while (iterateChildren2.hasNext()) {
            declareUsedNamespaces((XMPNode) iterateChildren2.next(), set, i);
        }
        Iterator iterateQualifier = xMPNode.iterateQualifier();
        while (iterateQualifier.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateQualifier.next();
            declareNamespace(xMPNode2.name, null, set, i);
            declareUsedNamespaces(xMPNode2, set, i);
        }
    }

    private boolean serializeCompactRDFAttrProps(XMPNode xMPNode, int i) throws IOException {
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z = true;
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (canBeRDFAttrProp(xMPNode2)) {
                writeNewline();
                writeIndent(i);
                write(xMPNode2.name);
                write("=\"");
                appendNodeValue(xMPNode2.value, true);
                write(34);
            } else {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a9, code lost:
        if (r4 != false) goto L_0x0074;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void serializeCompactRDFElementProps(com.adobe.xmp.impl.XMPNode r12, int r13) throws java.io.IOException, com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 194
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPSerializerRDF.serializeCompactRDFElementProps(com.adobe.xmp.impl.XMPNode, int):void");
    }

    private boolean serializeCompactRDFStructProp(XMPNode xMPNode, int i, boolean z) throws XMPException, IOException {
        String str;
        Iterator iterateChildren = xMPNode.iterateChildren();
        boolean z2 = false;
        boolean z3 = false;
        while (iterateChildren.hasNext()) {
            if (canBeRDFAttrProp((XMPNode) iterateChildren.next())) {
                z2 = true;
                if (z3) {
                    break;
                }
            } else {
                z3 = true;
                if (z2) {
                    break;
                }
            }
        }
        if (!z || !z3) {
            if (!xMPNode.hasChildren()) {
                str = " rdf:parseType=\"Resource\"/>";
            } else if (!z3) {
                serializeCompactRDFAttrProps(xMPNode, i + 1);
                str = "/>";
            } else if (!z2) {
                write(" rdf:parseType=\"Resource\">");
                writeNewline();
                serializeCompactRDFElementProps(xMPNode, i + 1);
                return true;
            } else {
                write(62);
                writeNewline();
                int i2 = i + 1;
                writeIndent(i2);
                write(RDF_STRUCT_START);
                serializeCompactRDFAttrProps(xMPNode, i + 2);
                write(">");
                writeNewline();
                serializeCompactRDFElementProps(xMPNode, i2);
                writeIndent(i2);
                write("</rdf:Description>");
                writeNewline();
                return true;
            }
            write(str);
            writeNewline();
            return false;
        }
        throw new XMPException("Can't mix rdf:resource qualifier and element fields", 202);
    }

    private void write(int i) throws IOException {
        this.writer.write(i);
    }

    private void write(String str) throws IOException {
        this.writer.write(str);
    }
}
