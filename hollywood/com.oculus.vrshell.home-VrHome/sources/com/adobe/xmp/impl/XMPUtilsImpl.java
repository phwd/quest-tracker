package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPError;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import com.oculus.os.Version;
import java.util.Iterator;

public class XMPUtilsImpl implements XMPConst {
    static final /* synthetic */ boolean $assertionsDisabled = (!XMPUtilsImpl.class.desiredAssertionStatus());
    private static final String COMMAS = ",，､﹐﹑、،՝";
    private static final String CONTROLS = "  ";
    private static final String QUOTES = "\"[]«»〝〞〟―‹›";
    private static final String SEMICOLA = ";；﹔؛;";
    private static final String SPACES = " 　〿";
    private static final int UCK_COMMA = 2;
    private static final int UCK_CONTROL = 5;
    private static final int UCK_NORMAL = 0;
    private static final int UCK_QUOTE = 4;
    private static final int UCK_SEMICOLON = 3;
    private static final int UCK_SPACE = 1;

    private XMPUtilsImpl() {
    }

    public static String catenateArrayItems(XMPMeta xmp, String schemaNS, String arrayName, String separator, String quotes, boolean allowCommas) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        ParameterAsserts.assertImplementation(xmp);
        if (separator == null || separator.length() == 0) {
            separator = "; ";
        }
        if (quotes == null || quotes.length() == 0) {
            quotes = "\"";
        }
        XMPNode arrayNode = XMPNodeUtils.findNode(((XMPMetaImpl) xmp).getRoot(), XMPPathParser.expandXPath(schemaNS, arrayName), false, null);
        if (arrayNode == null) {
            return "";
        }
        if (!arrayNode.getOptions().isArray() || arrayNode.getOptions().isArrayAlternate()) {
            throw new XMPException("Named property must be non-alternate array", 4);
        }
        checkSeparator(separator);
        char openQuote = quotes.charAt(0);
        char closeQuote = checkQuotes(quotes, openQuote);
        StringBuffer catinatedString = new StringBuffer();
        Iterator it = arrayNode.iterateChildren();
        while (it.hasNext()) {
            XMPNode currItem = (XMPNode) it.next();
            if (currItem.getOptions().isCompositeProperty()) {
                throw new XMPException("Array items must be simple", 4);
            }
            catinatedString.append(applyQuotes(currItem.getValue(), openQuote, closeQuote, allowCommas));
            if (it.hasNext()) {
                catinatedString.append(separator);
            }
        }
        return catinatedString.toString();
    }

    public static void separateArrayItems(XMPMeta xmp, String schemaNS, String arrayName, String catedStr, PropertyOptions arrayOptions, boolean preserveCommas) throws XMPException {
        String itemValue;
        char nextChar;
        int nextKind;
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        if (catedStr == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        ParameterAsserts.assertImplementation(xmp);
        XMPNode arrayNode = separateFindCreateArray(schemaNS, arrayName, arrayOptions, (XMPMetaImpl) xmp);
        int charKind = 0;
        char ch = 0;
        int itemEnd = 0;
        int endPos = catedStr.length();
        while (itemEnd < endPos) {
            int itemStart = itemEnd;
            while (itemStart < endPos) {
                ch = catedStr.charAt(itemStart);
                charKind = classifyCharacter(ch);
                if (charKind == 0 || charKind == 4) {
                    break;
                }
                itemStart++;
            }
            if (itemStart < endPos) {
                if (charKind != 4) {
                    itemEnd = itemStart;
                    while (itemEnd < endPos) {
                        ch = catedStr.charAt(itemEnd);
                        charKind = classifyCharacter(ch);
                        if (charKind != 0 && charKind != 4 && ((charKind != 2 || !preserveCommas) && (charKind != 1 || itemEnd + 1 >= endPos || ((nextKind = classifyCharacter((ch = catedStr.charAt(itemEnd + 1)))) != 0 && nextKind != 4 && (nextKind != 2 || !preserveCommas))))) {
                            break;
                        }
                        itemEnd++;
                    }
                    itemValue = catedStr.substring(itemStart, itemEnd);
                } else {
                    char closeQuote = getClosingQuote(ch);
                    itemValue = "";
                    itemEnd = itemStart + 1;
                    while (true) {
                        if (itemEnd >= endPos) {
                            break;
                        }
                        ch = catedStr.charAt(itemEnd);
                        charKind = classifyCharacter(ch);
                        if (charKind == 4 && isSurroundingQuote(ch, ch, closeQuote)) {
                            if (itemEnd + 1 < endPos) {
                                nextChar = catedStr.charAt(itemEnd + 1);
                                classifyCharacter(nextChar);
                            } else {
                                nextChar = ';';
                            }
                            if (ch != nextChar) {
                                if (isClosingingQuote(ch, ch, closeQuote)) {
                                    itemEnd++;
                                    break;
                                }
                                itemValue = itemValue + ch;
                            } else {
                                itemValue = itemValue + ch;
                                itemEnd++;
                            }
                        } else {
                            itemValue = itemValue + ch;
                        }
                        itemEnd++;
                    }
                }
                int foundIndex = -1;
                int oldChild = 1;
                while (true) {
                    if (oldChild > arrayNode.getChildrenLength()) {
                        break;
                    } else if (itemValue.equals(arrayNode.getChild(oldChild).getValue())) {
                        foundIndex = oldChild;
                        break;
                    } else {
                        oldChild++;
                    }
                }
                if (foundIndex < 0) {
                    arrayNode.addChild(new XMPNode(XMPConst.ARRAY_ITEM_NAME, itemValue, null));
                }
            } else {
                return;
            }
        }
    }

    private static XMPNode separateFindCreateArray(String schemaNS, String arrayName, PropertyOptions arrayOptions, XMPMetaImpl xmp) throws XMPException {
        PropertyOptions arrayOptions2 = XMPNodeUtils.verifySetOptions(arrayOptions, null);
        if (!arrayOptions2.isOnlyArrayOptions()) {
            throw new XMPException("Options can only provide array form", XMPError.BADOPTIONS);
        }
        XMPPath arrayPath = XMPPathParser.expandXPath(schemaNS, arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(xmp.getRoot(), arrayPath, false, null);
        if (arrayNode != null) {
            PropertyOptions arrayForm = arrayNode.getOptions();
            if (!arrayForm.isArray() || arrayForm.isArrayAlternate()) {
                throw new XMPException("Named property must be non-alternate array", XMPError.BADXPATH);
            } else if (arrayOptions2.equalArrayTypes(arrayForm)) {
                throw new XMPException("Mismatch of specified and existing array form", XMPError.BADXPATH);
            }
        } else {
            arrayNode = XMPNodeUtils.findNode(xmp.getRoot(), arrayPath, true, arrayOptions2.setArray(true));
            if (arrayNode == null) {
                throw new XMPException("Failed to create named array", XMPError.BADXPATH);
            }
        }
        return arrayNode;
    }

    public static void removeProperties(XMPMeta xmp, String schemaNS, String propName, boolean doAllProperties, boolean includeAliases) throws XMPException {
        ParameterAsserts.assertImplementation(xmp);
        XMPMetaImpl xmpImpl = (XMPMetaImpl) xmp;
        if (propName == null || propName.length() <= 0) {
            if (schemaNS == null || schemaNS.length() <= 0) {
                Iterator it = xmpImpl.getRoot().iterateChildren();
                while (it.hasNext()) {
                    if (removeSchemaChildren((XMPNode) it.next(), doAllProperties)) {
                        it.remove();
                    }
                }
                return;
            }
            XMPNode schemaNode = XMPNodeUtils.findSchemaNode(xmpImpl.getRoot(), schemaNS, false);
            if (schemaNode != null && removeSchemaChildren(schemaNode, doAllProperties)) {
                xmpImpl.getRoot().removeChild(schemaNode);
            }
            if (includeAliases) {
                XMPAliasInfo[] aliases = XMPMetaFactory.getSchemaRegistry().findAliases(schemaNS);
                for (XMPAliasInfo info : aliases) {
                    XMPNode actualProp = XMPNodeUtils.findNode(xmpImpl.getRoot(), XMPPathParser.expandXPath(info.getNamespace(), info.getPropName()), false, null);
                    if (actualProp != null) {
                        actualProp.getParent().removeChild(actualProp);
                    }
                }
            }
        } else if (schemaNS == null || schemaNS.length() == 0) {
            throw new XMPException("Property name requires schema namespace", 4);
        } else {
            XMPPath expPath = XMPPathParser.expandXPath(schemaNS, propName);
            XMPNode propNode = XMPNodeUtils.findNode(xmpImpl.getRoot(), expPath, false, null);
            if (propNode == null) {
                return;
            }
            if (doAllProperties || !Utils.isInternalProperty(expPath.getSegment(0).getName(), expPath.getSegment(1).getName())) {
                XMPNode parent = propNode.getParent();
                parent.removeChild(propNode);
                if (parent.getOptions().isSchemaNode() && !parent.hasChildren()) {
                    parent.getParent().removeChild(parent);
                }
            }
        }
    }

    public static void appendProperties(XMPMeta source, XMPMeta destination, boolean doAllProperties, boolean replaceOldValues, boolean deleteEmptyValues) throws XMPException {
        ParameterAsserts.assertImplementation(source);
        ParameterAsserts.assertImplementation(destination);
        XMPMetaImpl dest = (XMPMetaImpl) destination;
        Iterator it = ((XMPMetaImpl) source).getRoot().iterateChildren();
        while (it.hasNext()) {
            XMPNode sourceSchema = (XMPNode) it.next();
            XMPNode destSchema = XMPNodeUtils.findSchemaNode(dest.getRoot(), sourceSchema.getName(), false);
            boolean createdSchema = false;
            if (destSchema == null) {
                destSchema = new XMPNode(sourceSchema.getName(), sourceSchema.getValue(), new PropertyOptions().setSchemaNode(true));
                dest.getRoot().addChild(destSchema);
                createdSchema = true;
            }
            Iterator ic = sourceSchema.iterateChildren();
            while (ic.hasNext()) {
                XMPNode sourceProp = (XMPNode) ic.next();
                if (doAllProperties || !Utils.isInternalProperty(sourceSchema.getName(), sourceProp.getName())) {
                    appendSubtree(dest, sourceProp, destSchema, replaceOldValues, deleteEmptyValues);
                }
            }
            if (!destSchema.hasChildren() && (createdSchema || deleteEmptyValues)) {
                dest.getRoot().removeChild(destSchema);
            }
        }
    }

    private static boolean removeSchemaChildren(XMPNode schemaNode, boolean doAllProperties) {
        Iterator it = schemaNode.iterateChildren();
        while (it.hasNext()) {
            XMPNode currProp = (XMPNode) it.next();
            if (doAllProperties || !Utils.isInternalProperty(schemaNode.getName(), currProp.getName())) {
                it.remove();
            }
        }
        return !schemaNode.hasChildren();
    }

    private static void appendSubtree(XMPMetaImpl destXMP, XMPNode sourceNode, XMPNode destParent, boolean replaceOldValues, boolean deleteEmptyValues) throws XMPException {
        XMPNode destNode = XMPNodeUtils.findChildNode(destParent, sourceNode.getName(), false);
        boolean valueIsEmpty = false;
        if (deleteEmptyValues) {
            if (sourceNode.getOptions().isSimple()) {
                valueIsEmpty = sourceNode.getValue() == null || sourceNode.getValue().length() == 0;
            } else {
                valueIsEmpty = !sourceNode.hasChildren();
            }
        }
        if (!deleteEmptyValues || !valueIsEmpty) {
            if (destNode == null) {
                destParent.addChild((XMPNode) sourceNode.clone());
            } else if (replaceOldValues) {
                destXMP.setNode(destNode, sourceNode.getValue(), sourceNode.getOptions(), true);
                destParent.removeChild(destNode);
                destParent.addChild((XMPNode) sourceNode.clone());
            } else {
                PropertyOptions sourceForm = sourceNode.getOptions();
                if (sourceForm != destNode.getOptions()) {
                    return;
                }
                if (sourceForm.isStruct()) {
                    Iterator it = sourceNode.iterateChildren();
                    while (it.hasNext()) {
                        appendSubtree(destXMP, (XMPNode) it.next(), destNode, replaceOldValues, deleteEmptyValues);
                        if (deleteEmptyValues && !destNode.hasChildren()) {
                            destParent.removeChild(destNode);
                        }
                    }
                } else if (sourceForm.isArrayAltText()) {
                    Iterator it2 = sourceNode.iterateChildren();
                    while (it2.hasNext()) {
                        XMPNode sourceItem = (XMPNode) it2.next();
                        if (sourceItem.hasQualifier() && XMPConst.XML_LANG.equals(sourceItem.getQualifier(1).getName())) {
                            int destIndex = XMPNodeUtils.lookupLanguageItem(destNode, sourceItem.getQualifier(1).getValue());
                            if (!deleteEmptyValues || !(sourceItem.getValue() == null || sourceItem.getValue().length() == 0)) {
                                if (destIndex == -1) {
                                    if (!XMPConst.X_DEFAULT.equals(sourceItem.getQualifier(1).getValue()) || !destNode.hasChildren()) {
                                        sourceItem.cloneSubtree(destNode);
                                    } else {
                                        XMPNode destItem = new XMPNode(sourceItem.getName(), sourceItem.getValue(), sourceItem.getOptions());
                                        sourceItem.cloneSubtree(destItem);
                                        destNode.addChild(1, destItem);
                                    }
                                }
                            } else if (destIndex != -1) {
                                destNode.removeChild(destIndex);
                                if (!destNode.hasChildren()) {
                                    destParent.removeChild(destNode);
                                }
                            }
                        }
                    }
                } else if (sourceForm.isArray()) {
                    Iterator is = sourceNode.iterateChildren();
                    while (is.hasNext()) {
                        XMPNode sourceItem2 = (XMPNode) is.next();
                        boolean match = false;
                        Iterator id = destNode.iterateChildren();
                        while (id.hasNext()) {
                            if (itemValuesMatch(sourceItem2, (XMPNode) id.next())) {
                                match = true;
                            }
                        }
                        if (!match) {
                            destNode = (XMPNode) sourceItem2.clone();
                            destParent.addChild(destNode);
                        }
                    }
                }
            }
        } else if (destNode != null) {
            destParent.removeChild(destNode);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean itemValuesMatch(com.adobe.xmp.impl.XMPNode r14, com.adobe.xmp.impl.XMPNode r15) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.itemValuesMatch(com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.XMPNode):boolean");
    }

    private static void checkSeparator(String separator) throws XMPException {
        boolean haveSemicolon = false;
        for (int i = 0; i < separator.length(); i++) {
            int charKind = classifyCharacter(separator.charAt(i));
            if (charKind == 3) {
                if (haveSemicolon) {
                    throw new XMPException("Separator can have only one semicolon", 4);
                }
                haveSemicolon = true;
            } else if (charKind != 1) {
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
            }
        }
        if (!haveSemicolon) {
            throw new XMPException("Separator must have one semicolon", 4);
        }
    }

    private static char checkQuotes(String quotes, char openQuote) throws XMPException {
        char closeQuote;
        if (classifyCharacter(openQuote) != 4) {
            throw new XMPException("Invalid quoting character", 4);
        }
        if (quotes.length() == 1) {
            closeQuote = openQuote;
        } else {
            closeQuote = quotes.charAt(1);
            if (classifyCharacter(closeQuote) != 4) {
                throw new XMPException("Invalid quoting character", 4);
            }
        }
        if (closeQuote == getClosingQuote(openQuote)) {
            return closeQuote;
        }
        throw new XMPException("Mismatched quote pair", 4);
    }

    private static int classifyCharacter(char ch) {
        if (SPACES.indexOf(ch) >= 0 || (8192 <= ch && ch <= 8203)) {
            return 1;
        }
        if (COMMAS.indexOf(ch) >= 0) {
            return 2;
        }
        if (SEMICOLA.indexOf(ch) >= 0) {
            return 3;
        }
        if (QUOTES.indexOf(ch) >= 0 || ((12296 <= ch && ch <= 12303) || (8216 <= ch && ch <= 8223))) {
            return 4;
        }
        if (ch < ' ' || CONTROLS.indexOf(ch) >= 0) {
            return 5;
        }
        return 0;
    }

    private static char getClosingQuote(char openQuote) {
        switch (openQuote) {
            case Version.VERSION_34 /*{ENCODED_INT: 34}*/:
                return '\"';
            case '[':
                return ']';
            case 171:
                return 187;
            case 187:
                return 171;
            case 8213:
                return 8213;
            case 8216:
                return 8217;
            case 8218:
                return 8219;
            case 8220:
                return 8221;
            case 8222:
                return 8223;
            case 8249:
                return 8250;
            case 8250:
                return 8249;
            case 12296:
                return 12297;
            case 12298:
                return 12299;
            case 12300:
                return 12301;
            case 12302:
                return 12303;
            case 12317:
                return 12319;
            default:
                return 0;
        }
    }

    private static String applyQuotes(String item, char openQuote, char closeQuote, boolean allowCommas) {
        if (item == null) {
            item = "";
        }
        boolean prevSpace = false;
        int i = 0;
        while (i < item.length()) {
            int charKind = classifyCharacter(item.charAt(i));
            if (i == 0 && charKind == 4) {
                break;
            }
            if (charKind != 1) {
                prevSpace = false;
                if (charKind != 3) {
                    if (charKind != 5) {
                        if (charKind == 2 && !allowCommas) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                if (prevSpace) {
                    break;
                }
                prevSpace = true;
            }
            i++;
        }
        if (i >= item.length()) {
            return item;
        }
        StringBuffer newItem = new StringBuffer(item.length() + 2);
        int splitPoint = 0;
        while (splitPoint <= i && classifyCharacter(item.charAt(i)) != 4) {
            splitPoint++;
        }
        newItem.append(openQuote).append(item.substring(0, splitPoint));
        for (int charOffset = splitPoint; charOffset < item.length(); charOffset++) {
            newItem.append(item.charAt(charOffset));
            if (classifyCharacter(item.charAt(charOffset)) == 4 && isSurroundingQuote(item.charAt(charOffset), openQuote, closeQuote)) {
                newItem.append(item.charAt(charOffset));
            }
        }
        newItem.append(closeQuote);
        return newItem.toString();
    }

    private static boolean isSurroundingQuote(char ch, char openQuote, char closeQuote) {
        return ch == openQuote || isClosingingQuote(ch, openQuote, closeQuote);
    }

    private static boolean isClosingingQuote(char ch, char openQuote, char closeQuote) {
        return ch == closeQuote || (openQuote == 12317 && ch == 12318) || ch == 12319;
    }
}
