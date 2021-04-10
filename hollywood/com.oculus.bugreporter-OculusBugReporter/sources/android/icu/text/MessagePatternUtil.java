package android.icu.text;

import android.icu.text.MessagePattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MessagePatternUtil {
    private MessagePatternUtil() {
    }

    public static MessageNode buildMessageNode(String patternString) {
        return buildMessageNode(new MessagePattern(patternString));
    }

    public static MessageNode buildMessageNode(MessagePattern pattern) {
        int limit = pattern.countParts() - 1;
        if (limit < 0) {
            throw new IllegalArgumentException("The MessagePattern is empty");
        } else if (pattern.getPartType(0) == MessagePattern.Part.Type.MSG_START) {
            return buildMessageNode(pattern, 0, limit);
        } else {
            throw new IllegalArgumentException("The MessagePattern does not represent a MessageFormat pattern");
        }
    }

    public static class Node {
        /* synthetic */ Node(AnonymousClass1 x0) {
            this();
        }

        private Node() {
        }
    }

    public static class MessageNode extends Node {
        private volatile List<MessageContentsNode> list;

        /* synthetic */ MessageNode(AnonymousClass1 x0) {
            this();
        }

        public List<MessageContentsNode> getContents() {
            return this.list;
        }

        public String toString() {
            return this.list.toString();
        }

        private MessageNode() {
            super(null);
            this.list = new ArrayList();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addContentsNode(MessageContentsNode node) {
            if ((node instanceof TextNode) && !this.list.isEmpty()) {
                MessageContentsNode lastNode = this.list.get(this.list.size() - 1);
                if (lastNode instanceof TextNode) {
                    TextNode textNode = (TextNode) lastNode;
                    textNode.text += ((TextNode) node).text;
                    return;
                }
            }
            this.list.add(node);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private MessageNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    public static class MessageContentsNode extends Node {
        private Type type;

        public enum Type {
            TEXT,
            ARG,
            REPLACE_NUMBER
        }

        /* synthetic */ MessageContentsNode(Type x0, AnonymousClass1 x1) {
            this(x0);
        }

        public Type getType() {
            return this.type;
        }

        public String toString() {
            return "{REPLACE_NUMBER}";
        }

        private MessageContentsNode(Type type2) {
            super(null);
            this.type = type2;
        }

        /* access modifiers changed from: private */
        public static MessageContentsNode createReplaceNumberNode() {
            return new MessageContentsNode(Type.REPLACE_NUMBER);
        }
    }

    public static class TextNode extends MessageContentsNode {
        private String text;

        /* synthetic */ TextNode(String x0, AnonymousClass1 x1) {
            this(x0);
        }

        public String getText() {
            return this.text;
        }

        @Override // android.icu.text.MessagePatternUtil.MessageContentsNode
        public String toString() {
            return "«" + this.text + "»";
        }

        private TextNode(String text2) {
            super(MessageContentsNode.Type.TEXT, null);
            this.text = text2;
        }
    }

    public static class ArgNode extends MessageContentsNode {
        private MessagePattern.ArgType argType;
        private ComplexArgStyleNode complexStyle;
        private String name;
        private int number = -1;
        private String style;
        private String typeName;

        public MessagePattern.ArgType getArgType() {
            return this.argType;
        }

        public String getName() {
            return this.name;
        }

        public int getNumber() {
            return this.number;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public String getSimpleStyle() {
            return this.style;
        }

        public ComplexArgStyleNode getComplexStyle() {
            return this.complexStyle;
        }

        @Override // android.icu.text.MessagePatternUtil.MessageContentsNode
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            sb.append(this.name);
            if (this.argType != MessagePattern.ArgType.NONE) {
                sb.append(',');
                sb.append(this.typeName);
                if (this.argType != MessagePattern.ArgType.SIMPLE) {
                    sb.append(',');
                    sb.append(this.complexStyle.toString());
                } else if (this.style != null) {
                    sb.append(',');
                    sb.append(this.style);
                }
            }
            sb.append('}');
            return sb.toString();
        }

        private ArgNode() {
            super(MessageContentsNode.Type.ARG, null);
        }

        /* access modifiers changed from: private */
        public static ArgNode createArgNode() {
            return new ArgNode();
        }
    }

    public static class ComplexArgStyleNode extends Node {
        private MessagePattern.ArgType argType;
        private boolean explicitOffset;
        private volatile List<VariantNode> list;
        private double offset;

        /* synthetic */ ComplexArgStyleNode(MessagePattern.ArgType x0, AnonymousClass1 x1) {
            this(x0);
        }

        public MessagePattern.ArgType getArgType() {
            return this.argType;
        }

        public boolean hasExplicitOffset() {
            return this.explicitOffset;
        }

        public double getOffset() {
            return this.offset;
        }

        public List<VariantNode> getVariants() {
            return this.list;
        }

        public VariantNode getVariantsByType(List<VariantNode> numericVariants, List<VariantNode> keywordVariants) {
            if (numericVariants != null) {
                numericVariants.clear();
            }
            keywordVariants.clear();
            VariantNode other = null;
            for (VariantNode variant : this.list) {
                if (variant.isSelectorNumeric()) {
                    numericVariants.add(variant);
                } else if (!PluralRules.KEYWORD_OTHER.equals(variant.getSelector())) {
                    keywordVariants.add(variant);
                } else if (other == null) {
                    other = variant;
                }
            }
            return other;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(this.argType.toString());
            sb.append(" style) ");
            if (hasExplicitOffset()) {
                sb.append("offset:");
                sb.append(this.offset);
                sb.append(' ');
            }
            sb.append(this.list.toString());
            return sb.toString();
        }

        private ComplexArgStyleNode(MessagePattern.ArgType argType2) {
            super(null);
            this.list = new ArrayList();
            this.argType = argType2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addVariant(VariantNode variant) {
            this.list.add(variant);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private ComplexArgStyleNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    public static class VariantNode extends Node {
        private MessageNode msgNode;
        private double numericValue;
        private String selector;

        /* synthetic */ VariantNode(AnonymousClass1 x0) {
            this();
        }

        public String getSelector() {
            return this.selector;
        }

        public boolean isSelectorNumeric() {
            return this.numericValue != -1.23456789E8d;
        }

        public double getSelectorValue() {
            return this.numericValue;
        }

        public MessageNode getMessage() {
            return this.msgNode;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isSelectorNumeric()) {
                sb.append(this.numericValue);
                sb.append(" (");
                sb.append(this.selector);
                sb.append(") {");
            } else {
                sb.append(this.selector);
                sb.append(" {");
            }
            sb.append(this.msgNode.toString());
            sb.append('}');
            return sb.toString();
        }

        private VariantNode() {
            super(null);
            this.numericValue = -1.23456789E8d;
        }
    }

    private static MessageNode buildMessageNode(MessagePattern pattern, int start, int limit) {
        int prevPatternIndex = pattern.getPart(start).getLimit();
        MessageNode node = new MessageNode(null);
        int i = start + 1;
        while (true) {
            MessagePattern.Part part = pattern.getPart(i);
            int patternIndex = part.getIndex();
            if (prevPatternIndex < patternIndex) {
                node.addContentsNode(new TextNode(pattern.getPatternString().substring(prevPatternIndex, patternIndex), null));
            }
            if (i == limit) {
                return node.freeze();
            }
            MessagePattern.Part.Type partType = part.getType();
            if (partType == MessagePattern.Part.Type.ARG_START) {
                int argLimit = pattern.getLimitPartIndex(i);
                node.addContentsNode(buildArgNode(pattern, i, argLimit));
                i = argLimit;
                part = pattern.getPart(i);
            } else if (partType == MessagePattern.Part.Type.REPLACE_NUMBER) {
                node.addContentsNode(MessageContentsNode.createReplaceNumberNode());
            }
            prevPatternIndex = part.getLimit();
            i++;
        }
    }

    private static ArgNode buildArgNode(MessagePattern pattern, int start, int limit) {
        ArgNode node = ArgNode.createArgNode();
        MessagePattern.ArgType argType = node.argType = pattern.getPart(start).getArgType();
        int start2 = start + 1;
        MessagePattern.Part part = pattern.getPart(start2);
        node.name = pattern.getSubstring(part);
        if (part.getType() == MessagePattern.Part.Type.ARG_NUMBER) {
            node.number = part.getValue();
        }
        int start3 = start2 + 1;
        int i = AnonymousClass1.$SwitchMap$android$icu$text$MessagePattern$ArgType[argType.ordinal()];
        if (i == 1) {
            int start4 = start3 + 1;
            node.typeName = pattern.getSubstring(pattern.getPart(start3));
            if (start4 < limit) {
                node.style = pattern.getSubstring(pattern.getPart(start4));
            }
        } else if (i == 2) {
            node.typeName = "choice";
            node.complexStyle = buildChoiceStyleNode(pattern, start3, limit);
        } else if (i == 3) {
            node.typeName = "plural";
            node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
        } else if (i == 4) {
            node.typeName = "select";
            node.complexStyle = buildSelectStyleNode(pattern, start3, limit);
        } else if (i == 5) {
            node.typeName = "selectordinal";
            node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
        }
        return node;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.text.MessagePatternUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$text$MessagePattern$ArgType = new int[MessagePattern.ArgType.values().length];

        static {
            try {
                $SwitchMap$android$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.CHOICE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.PLURAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.SELECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.SELECTORDINAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private static ComplexArgStyleNode buildChoiceStyleNode(MessagePattern pattern, int start, int limit) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.CHOICE, null);
        while (start < limit) {
            double value = pattern.getNumericValue(pattern.getPart(start));
            int start2 = start + 2;
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(pattern.getPart(start + 1));
            variant.numericValue = value;
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildPluralStyleNode(MessagePattern pattern, int start, int limit, MessagePattern.ArgType argType) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(argType, null);
        MessagePattern.Part offset = pattern.getPart(start);
        if (offset.getType().hasNumericValue()) {
            node.explicitOffset = true;
            node.offset = pattern.getNumericValue(offset);
            start++;
        }
        while (start < limit) {
            int start2 = start + 1;
            MessagePattern.Part selector = pattern.getPart(start);
            double value = -1.23456789E8d;
            MessagePattern.Part part = pattern.getPart(start2);
            if (part.getType().hasNumericValue()) {
                value = pattern.getNumericValue(part);
                start2++;
            }
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(selector);
            variant.numericValue = value;
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildSelectStyleNode(MessagePattern pattern, int start, int limit) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.SELECT, null);
        while (start < limit) {
            int start2 = start + 1;
            MessagePattern.Part selector = pattern.getPart(start);
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(selector);
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }
}
