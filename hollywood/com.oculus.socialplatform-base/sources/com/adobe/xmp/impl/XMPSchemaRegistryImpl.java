package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPSchemaRegistry;
import com.adobe.xmp.options.AliasOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public final class XMPSchemaRegistryImpl implements XMPSchemaRegistry, XMPConst {
    public Map aliasMap = new HashMap();
    public Map namespaceToPrefixMap = new HashMap();
    public Pattern p = Pattern.compile("[/*?\\[\\]]");
    public Map prefixToNamespaceMap = new HashMap();

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized void deleteNamespace(String str) {
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix != null) {
            this.namespaceToPrefixMap.remove(str);
            this.prefixToNamespaceMap.remove(namespacePrefix);
        }
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo findAlias(String str) {
        return (XMPAliasInfo) this.aliasMap.get(str);
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo[] findAliases(String str) {
        ArrayList arrayList;
        String namespacePrefix = getNamespacePrefix(str);
        arrayList = new ArrayList();
        if (namespacePrefix != null) {
            for (String str2 : this.aliasMap.keySet()) {
                if (str2.startsWith(namespacePrefix)) {
                    arrayList.add(findAlias(str2));
                }
            }
        }
        return (XMPAliasInfo[]) arrayList.toArray(new XMPAliasInfo[arrayList.size()]);
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized Map getAliases() {
        return Collections.unmodifiableMap(new TreeMap(this.aliasMap));
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized String getNamespacePrefix(String str) {
        return (String) this.namespaceToPrefixMap.get(str);
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized String getNamespaceURI(String str) {
        if (str != null) {
            if (!str.endsWith(":")) {
                str = AnonymousClass006.A07(str, ":");
            }
        }
        return (String) this.prefixToNamespaceMap.get(str);
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized Map getNamespaces() {
        return Collections.unmodifiableMap(new TreeMap(this.namespaceToPrefixMap));
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized Map getPrefixes() {
        return Collections.unmodifiableMap(new TreeMap(this.prefixToNamespaceMap));
    }

    public synchronized void registerAlias(String str, String str2, final String str3, final String str4, AliasOptions aliasOptions) throws XMPException {
        final AliasOptions aliasOptions2;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        ParameterAsserts.assertSchemaNS(str3);
        ParameterAsserts.assertPropName(str4);
        if (aliasOptions != null) {
            aliasOptions2 = new AliasOptions(XMPNodeUtils.verifySetOptions(aliasOptions.toPropertyOptions(), null).options);
        } else {
            aliasOptions2 = new AliasOptions();
        }
        if (this.p.matcher(str2).find() || this.p.matcher(str4).find()) {
            throw new XMPException("Alias and actual property names must be simple", 102);
        }
        String namespacePrefix = getNamespacePrefix(str);
        final String namespacePrefix2 = getNamespacePrefix(str3);
        if (namespacePrefix == null) {
            throw new XMPException("Alias namespace is not registered", 101);
        } else if (namespacePrefix2 != null) {
            String A07 = AnonymousClass006.A07(namespacePrefix, str2);
            if (this.aliasMap.containsKey(A07)) {
                throw new XMPException("Alias is already existing", 4);
            } else if (!this.aliasMap.containsKey(AnonymousClass006.A07(namespacePrefix2, str4))) {
                this.aliasMap.put(A07, new XMPAliasInfo() {
                    /* class com.adobe.xmp.impl.XMPSchemaRegistryImpl.AnonymousClass1 */

                    public String toString() {
                        StringBuilder sb = new StringBuilder();
                        sb.append(namespacePrefix2);
                        sb.append(str4);
                        sb.append(" NS(");
                        sb.append(str3);
                        sb.append("), FORM (");
                        sb.append(getAliasForm());
                        sb.append(")");
                        return sb.toString();
                    }

                    @Override // com.adobe.xmp.properties.XMPAliasInfo
                    public AliasOptions getAliasForm() {
                        return aliasOptions2;
                    }

                    @Override // com.adobe.xmp.properties.XMPAliasInfo
                    public String getNamespace() {
                        return str3;
                    }

                    @Override // com.adobe.xmp.properties.XMPAliasInfo
                    public String getPrefix() {
                        return namespacePrefix2;
                    }

                    @Override // com.adobe.xmp.properties.XMPAliasInfo
                    public String getPropName() {
                        return str4;
                    }
                });
            } else {
                throw new XMPException("Actual property is already an alias, use the base property", 4);
            }
        } else {
            throw new XMPException("Actual namespace is not registered", 101);
        }
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized String registerNamespace(String str, String str2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPrefix(str2);
        int i = 1;
        if (str2.charAt(str2.length() - 1) != ':') {
            str2 = AnonymousClass006.A01(str2, ':');
        }
        int length = str2.length() - 1;
        if (Utils.isXMLNameNS(str2.substring(0, length))) {
            String str3 = (String) this.namespaceToPrefixMap.get(str);
            String str4 = (String) this.prefixToNamespaceMap.get(str2);
            if (str3 != null) {
                return str3;
            }
            if (str4 != null) {
                String str5 = str2;
                while (this.prefixToNamespaceMap.containsKey(str5)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2.substring(0, length));
                    sb.append("_");
                    sb.append(i);
                    sb.append("_:");
                    str5 = sb.toString();
                    i++;
                }
                str2 = str5;
            }
            this.prefixToNamespaceMap.put(str2, str);
            this.namespaceToPrefixMap.put(str, str2);
            return str2;
        }
        throw new XMPException("The prefix is a bad XML name", 201);
    }

    @Override // com.adobe.xmp.XMPSchemaRegistry
    public synchronized XMPAliasInfo resolveAlias(String str, String str2) {
        XMPAliasInfo xMPAliasInfo;
        String namespacePrefix = getNamespacePrefix(str);
        if (namespacePrefix == null) {
            xMPAliasInfo = null;
        } else {
            xMPAliasInfo = (XMPAliasInfo) this.aliasMap.get(AnonymousClass006.A07(namespacePrefix, str2));
        }
        return xMPAliasInfo;
    }

    private void registerStandardAliases() throws XMPException {
        AliasOptions aliasOptions = new AliasOptions();
        aliasOptions.setOption(1536, true);
        AliasOptions aliasOptions2 = new AliasOptions();
        aliasOptions2.setOption(7680, true);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Author", XMPConst.NS_DC, "creator", aliasOptions);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Authors", XMPConst.NS_DC, "creator", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Description", XMPConst.NS_DC, "description", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Format", XMPConst.NS_DC, "format", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Keywords", XMPConst.NS_DC, "subject", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Locale", XMPConst.NS_DC, "language", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Title", XMPConst.NS_DC, "title", null);
        registerAlias(XMPConst.NS_XMP_RIGHTS, "Copyright", XMPConst.NS_DC, "rights", null);
        registerAlias(XMPConst.NS_PDF, "Author", XMPConst.NS_DC, "creator", aliasOptions);
        registerAlias(XMPConst.NS_PDF, "BaseURL", "http://ns.adobe.com/xap/1.0/", "BaseURL", null);
        registerAlias(XMPConst.NS_PDF, "CreationDate", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias(XMPConst.NS_PDF, "Creator", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PDF, "ModDate", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias(XMPConst.NS_PDF, "Subject", XMPConst.NS_DC, "description", aliasOptions2);
        registerAlias(XMPConst.NS_PDF, "Title", XMPConst.NS_DC, "title", aliasOptions2);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Author", XMPConst.NS_DC, "creator", aliasOptions);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Caption", XMPConst.NS_DC, "description", aliasOptions2);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Copyright", XMPConst.NS_DC, "rights", aliasOptions2);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Keywords", XMPConst.NS_DC, "subject", null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Marked", XMPConst.NS_XMP_RIGHTS, "Marked", null);
        registerAlias(XMPConst.NS_PHOTOSHOP, "Title", XMPConst.NS_DC, "title", aliasOptions2);
        registerAlias(XMPConst.NS_PHOTOSHOP, "WebStatement", XMPConst.NS_XMP_RIGHTS, "WebStatement", null);
        registerAlias(XMPConst.NS_TIFF, "Artist", XMPConst.NS_DC, "creator", aliasOptions);
        registerAlias(XMPConst.NS_TIFF, "Copyright", XMPConst.NS_DC, "rights", null);
        registerAlias(XMPConst.NS_TIFF, "DateTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias(XMPConst.NS_TIFF, "ImageDescription", XMPConst.NS_DC, "description", null);
        registerAlias(XMPConst.NS_TIFF, "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PNG, "Author", XMPConst.NS_DC, "creator", aliasOptions);
        registerAlias(XMPConst.NS_PNG, "Copyright", XMPConst.NS_DC, "rights", aliasOptions2);
        registerAlias(XMPConst.NS_PNG, "CreationTime", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias(XMPConst.NS_PNG, "Description", XMPConst.NS_DC, "description", aliasOptions2);
        registerAlias(XMPConst.NS_PNG, "ModificationTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias(XMPConst.NS_PNG, "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias(XMPConst.NS_PNG, "Title", XMPConst.NS_DC, "title", aliasOptions2);
    }

    private void registerStandardNamespaces() throws XMPException {
        registerNamespace(XMPConst.NS_XML, "xml");
        registerNamespace(XMPConst.NS_RDF, "rdf");
        registerNamespace(XMPConst.NS_DC, "dc");
        registerNamespace(XMPConst.NS_IPTCCORE, "Iptc4xmpCore");
        registerNamespace(XMPConst.NS_X, "x");
        registerNamespace(XMPConst.NS_IX, "iX");
        registerNamespace("http://ns.adobe.com/xap/1.0/", "xmp");
        registerNamespace(XMPConst.NS_XMP_RIGHTS, "xmpRights");
        registerNamespace(XMPConst.NS_XMP_MM, "xmpMM");
        registerNamespace(XMPConst.NS_XMP_BJ, "xmpBJ");
        registerNamespace(XMPConst.NS_XMP_NOTE, "xmpNote");
        registerNamespace(XMPConst.NS_PDF, "pdf");
        registerNamespace(XMPConst.NS_PDFX, "pdfx");
        registerNamespace(XMPConst.NS_PDFX_ID, "pdfxid");
        registerNamespace(XMPConst.NS_PDFA_SCHEMA, "pdfaSchema");
        registerNamespace(XMPConst.NS_PDFA_PROPERTY, "pdfaProperty");
        registerNamespace(XMPConst.NS_PDFA_TYPE, "pdfaType");
        registerNamespace(XMPConst.NS_PDFA_FIELD, "pdfaField");
        registerNamespace(XMPConst.NS_PDFA_ID, "pdfaid");
        registerNamespace(XMPConst.NS_PDFA_EXTENSION, "pdfaExtension");
        registerNamespace(XMPConst.NS_PHOTOSHOP, "photoshop");
        registerNamespace(XMPConst.NS_PSALBUM, "album");
        registerNamespace(XMPConst.NS_EXIF, "exif");
        registerNamespace(XMPConst.NS_EXIF_AUX, "aux");
        registerNamespace(XMPConst.NS_TIFF, "tiff");
        registerNamespace(XMPConst.NS_PNG, "png");
        registerNamespace(XMPConst.NS_JPEG, "jpeg");
        registerNamespace(XMPConst.NS_JP2K, "jp2k");
        registerNamespace(XMPConst.NS_CAMERARAW, "crs");
        registerNamespace(XMPConst.NS_ADOBESTOCKPHOTO, "bmsp");
        registerNamespace(XMPConst.NS_CREATOR_ATOM, "creatorAtom");
        registerNamespace(XMPConst.NS_ASF, "asf");
        registerNamespace(XMPConst.NS_WAV, "wav");
        registerNamespace(XMPConst.NS_DM, "xmpDM");
        registerNamespace(XMPConst.NS_TRANSIENT, "xmpx");
        registerNamespace(XMPConst.TYPE_TEXT, "xmpT");
        registerNamespace(XMPConst.TYPE_PAGEDFILE, "xmpTPg");
        registerNamespace(XMPConst.TYPE_GRAPHICS, "xmpG");
        registerNamespace(XMPConst.TYPE_IMAGE, "xmpGImg");
        registerNamespace(XMPConst.TYPE_FONT, "stFNT");
        registerNamespace(XMPConst.TYPE_DIMENSIONS, "stDim");
        registerNamespace(XMPConst.TYPE_RESOURCEEVENT, "stEvt");
        registerNamespace(XMPConst.TYPE_RESOURCEREF, "stRef");
        registerNamespace(XMPConst.TYPE_ST_VERSION, "stVer");
        registerNamespace(XMPConst.TYPE_ST_JOB, "stJob");
        registerNamespace(XMPConst.TYPE_MANIFESTITEM, "stMfs");
        registerNamespace(XMPConst.TYPE_IDENTIFIERQUAL, "xmpidq");
    }

    public XMPSchemaRegistryImpl() {
        try {
            registerStandardNamespaces();
            registerStandardAliases();
        } catch (XMPException unused) {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }
}
