package android.icu.util;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class StringTrieBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ValueNode lookupFinalValueNode = new ValueNode();
    private HashMap<Node, Node> nodes = new HashMap<>();
    private Node root;
    private State state = State.ADDING;
    @Deprecated
    protected StringBuilder strings = new StringBuilder();

    public enum Option {
        FAST,
        SMALL
    }

    /* access modifiers changed from: private */
    public enum State {
        ADDING,
        BUILDING_FAST,
        BUILDING_SMALL,
        BUILT
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int getMaxBranchLinearSubNodeLength();

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int getMaxLinearMatchLength();

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int getMinLinearMatch();

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract boolean matchNodesCanHaveValues();

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int write(int i);

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int write(int i, int i2);

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int writeDeltaTo(int i);

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int writeValueAndFinal(int i, boolean z);

    /* access modifiers changed from: protected */
    @Deprecated
    public abstract int writeValueAndType(boolean z, int i, int i2);

    @Deprecated
    protected StringTrieBuilder() {
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void addImpl(CharSequence s, int value) {
        if (this.state != State.ADDING) {
            throw new IllegalStateException("Cannot add (string, value) pairs after build().");
        } else if (s.length() <= 65535) {
            Node node = this.root;
            if (node == null) {
                this.root = createSuffixNode(s, 0, value);
            } else {
                this.root = node.add(this, s, 0, value);
            }
        } else {
            throw new IndexOutOfBoundsException("The maximum string length is 0xffff.");
        }
    }

    /* renamed from: android.icu.util.StringTrieBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$StringTrieBuilder$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$android$icu$util$StringTrieBuilder$State[State.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$util$StringTrieBuilder$State[State.BUILDING_FAST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$util$StringTrieBuilder$State[State.BUILDING_SMALL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$util$StringTrieBuilder$State[State.BUILT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final void buildImpl(Option buildOption) {
        int i = AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State[this.state.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                throw new IllegalStateException("Builder failed and must be clear()ed.");
            } else if (i == 4) {
                return;
            }
        } else if (this.root == null) {
            throw new IndexOutOfBoundsException("No (string, value) pairs were added.");
        } else if (buildOption == Option.FAST) {
            this.state = State.BUILDING_FAST;
        } else {
            this.state = State.BUILDING_SMALL;
        }
        this.root = this.root.register(this);
        this.root.markRightEdgesFirst(-1);
        this.root.write(this);
        this.state = State.BUILT;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void clearImpl() {
        this.strings.setLength(0);
        this.nodes.clear();
        this.root = null;
        this.state = State.ADDING;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final Node registerNode(Node newNode) {
        if (this.state == State.BUILDING_FAST) {
            return newNode;
        }
        Node oldNode = this.nodes.get(newNode);
        if (oldNode != null) {
            return oldNode;
        }
        this.nodes.put(newNode, newNode);
        return newNode;
    }

    private final ValueNode registerFinalValue(int value) {
        this.lookupFinalValueNode.setFinalValue(value);
        Node oldNode = this.nodes.get(this.lookupFinalValueNode);
        if (oldNode != null) {
            return (ValueNode) oldNode;
        }
        ValueNode newNode = new ValueNode(value);
        this.nodes.put(newNode, newNode);
        return newNode;
    }

    /* access modifiers changed from: private */
    public static abstract class Node {
        protected int offset = 0;

        public abstract int hashCode();

        public abstract void write(StringTrieBuilder stringTrieBuilder);

        public boolean equals(Object other) {
            return this == other || getClass() == other.getClass();
        }

        public Node add(StringTrieBuilder builder, CharSequence s, int start, int sValue) {
            return this;
        }

        public Node register(StringTrieBuilder builder) {
            return this;
        }

        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset == 0) {
                this.offset = edgeNumber;
            }
            return edgeNumber;
        }

        public final void writeUnlessInsideRightEdge(int firstRight, int lastRight, StringTrieBuilder builder) {
            int i = this.offset;
            if (i >= 0) {
                return;
            }
            if (i < lastRight || firstRight < i) {
                write(builder);
            }
        }

        public final int getOffset() {
            return this.offset;
        }
    }

    /* access modifiers changed from: private */
    public static class ValueNode extends Node {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected boolean hasValue;
        protected int value;

        public ValueNode() {
        }

        public ValueNode(int v) {
            this.hasValue = true;
            this.value = v;
        }

        public final void setValue(int v) {
            this.hasValue = true;
            this.value = v;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFinalValue(int v) {
            this.hasValue = true;
            this.value = v;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int hashCode() {
            if (this.hasValue) {
                return (1118481 * 37) + this.value;
            }
            return 1118481;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            ValueNode o = (ValueNode) other;
            boolean z = this.hasValue;
            if (z != o.hasValue || (z && this.value != o.value)) {
                return false;
            }
            return true;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public Node add(StringTrieBuilder builder, CharSequence s, int start, int sValue) {
            if (start != s.length()) {
                ValueNode node = builder.createSuffixNode(s, start, sValue);
                node.setValue(this.value);
                return node;
            }
            throw new IllegalArgumentException("Duplicate string.");
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder builder) {
            this.offset = builder.writeValueAndFinal(this.value, true);
        }
    }

    private static final class IntermediateValueNode extends ValueNode {
        private Node next;

        public IntermediateValueNode(int v, Node nextNode) {
            this.next = nextNode;
            setValue(v);
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return ((this.value + 82767594) * 37) + this.next.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            if (this.next == ((IntermediateValueNode) other).next) {
                return true;
            }
            return false;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset != 0) {
                return edgeNumber;
            }
            int edgeNumber2 = this.next.markRightEdgesFirst(edgeNumber);
            this.offset = edgeNumber2;
            return edgeNumber2;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder builder) {
            this.next.write(builder);
            this.offset = builder.writeValueAndFinal(this.value, false);
        }
    }

    /* access modifiers changed from: private */
    public static final class LinearMatchNode extends ValueNode {
        private int hash;
        private int length;
        private Node next;
        private int stringOffset;
        private CharSequence strings;

        public LinearMatchNode(CharSequence builderStrings, int sOffset, int len, Node nextNode) {
            this.strings = builderStrings;
            this.stringOffset = sOffset;
            this.length = len;
            this.next = nextNode;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return this.hash;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            LinearMatchNode o = (LinearMatchNode) other;
            int i = this.length;
            if (i != o.length || this.next != o.next) {
                return false;
            }
            int i2 = this.stringOffset;
            int j = o.stringOffset;
            int limit = this.stringOffset + i;
            while (i2 < limit) {
                if (this.strings.charAt(i2) != this.strings.charAt(j)) {
                    return false;
                }
                i2++;
                j++;
            }
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: android.icu.util.StringTrieBuilder$DynamicBranchNode */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: Multiple debug info for r5v4 int: [D('thisSuffixNode' android.icu.util.StringTrieBuilder$Node), D('prefixLength' int)] */
        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public Node add(StringTrieBuilder builder, CharSequence s, int start, int sValue) {
            Node thisSuffixNode;
            Node thisSuffixNode2;
            if (start != s.length()) {
                int limit = this.stringOffset + this.length;
                int i = this.stringOffset;
                while (i < limit) {
                    if (start == s.length()) {
                        int prefixLength = i - this.stringOffset;
                        LinearMatchNode suffixNode = new LinearMatchNode(this.strings, i, this.length - prefixLength, this.next);
                        suffixNode.setValue(sValue);
                        this.length = prefixLength;
                        this.next = suffixNode;
                        return this;
                    }
                    char thisChar = this.strings.charAt(i);
                    char newChar = s.charAt(start);
                    if (thisChar != newChar) {
                        DynamicBranchNode branchNode = new DynamicBranchNode();
                        int i2 = this.stringOffset;
                        if (i == i2) {
                            if (this.hasValue) {
                                branchNode.setValue(this.value);
                                this.value = 0;
                                this.hasValue = false;
                            }
                            this.stringOffset++;
                            this.length--;
                            thisSuffixNode2 = this.length > 0 ? this : this.next;
                            thisSuffixNode = branchNode;
                        } else if (i == limit - 1) {
                            this.length--;
                            thisSuffixNode2 = this.next;
                            this.next = branchNode;
                            thisSuffixNode = this;
                        } else {
                            int prefixLength2 = i - i2;
                            Node thisSuffixNode3 = new LinearMatchNode(this.strings, i + 1, this.length - (prefixLength2 + 1), this.next);
                            this.length = prefixLength2;
                            this.next = branchNode;
                            thisSuffixNode = this;
                            thisSuffixNode2 = thisSuffixNode3;
                        }
                        ValueNode newSuffixNode = builder.createSuffixNode(s, start + 1, sValue);
                        branchNode.add(thisChar, thisSuffixNode2);
                        branchNode.add(newChar, newSuffixNode);
                        return thisSuffixNode;
                    }
                    i++;
                    start++;
                }
                this.next = this.next.add(builder, s, start, sValue);
                return this;
            } else if (!this.hasValue) {
                setValue(sValue);
                return this;
            } else {
                throw new IllegalArgumentException("Duplicate string.");
            }
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public Node register(StringTrieBuilder builder) {
            Node result;
            this.next = this.next.register(builder);
            int maxLinearMatchLength = builder.getMaxLinearMatchLength();
            while (true) {
                int i = this.length;
                if (i <= maxLinearMatchLength) {
                    break;
                }
                this.length = i - maxLinearMatchLength;
                LinearMatchNode suffixNode = new LinearMatchNode(this.strings, (this.stringOffset + i) - maxLinearMatchLength, maxLinearMatchLength, this.next);
                suffixNode.setHashCode();
                this.next = builder.registerNode(suffixNode);
            }
            if (!this.hasValue || builder.matchNodesCanHaveValues()) {
                setHashCode();
                result = this;
            } else {
                int intermediateValue = this.value;
                this.value = 0;
                this.hasValue = false;
                setHashCode();
                result = new IntermediateValueNode(intermediateValue, builder.registerNode(this));
            }
            return builder.registerNode(result);
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset != 0) {
                return edgeNumber;
            }
            int edgeNumber2 = this.next.markRightEdgesFirst(edgeNumber);
            this.offset = edgeNumber2;
            return edgeNumber2;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder builder) {
            this.next.write(builder);
            builder.write(this.stringOffset, this.length);
            this.offset = builder.writeValueAndType(this.hasValue, this.value, (builder.getMinLinearMatch() + this.length) - 1);
        }

        private void setHashCode() {
            this.hash = ((this.length + 124151391) * 37) + this.next.hashCode();
            if (this.hasValue) {
                this.hash = (this.hash * 37) + this.value;
            }
            int limit = this.stringOffset + this.length;
            for (int i = this.stringOffset; i < limit; i++) {
                this.hash = (this.hash * 37) + this.strings.charAt(i);
            }
        }
    }

    private static final class DynamicBranchNode extends ValueNode {
        private StringBuilder chars = new StringBuilder();
        private ArrayList<Node> equal = new ArrayList<>();

        public void add(char c, Node node) {
            int i = find(c);
            this.chars.insert(i, c);
            this.equal.add(i, node);
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public Node add(StringTrieBuilder builder, CharSequence s, int start, int sValue) {
            if (start != s.length()) {
                int start2 = start + 1;
                char c = s.charAt(start);
                int i = find(c);
                if (i >= this.chars.length() || c != this.chars.charAt(i)) {
                    this.chars.insert(i, c);
                    this.equal.add(i, builder.createSuffixNode(s, start2, sValue));
                } else {
                    ArrayList<Node> arrayList = this.equal;
                    arrayList.set(i, arrayList.get(i).add(builder, s, start2, sValue));
                }
                return this;
            } else if (!this.hasValue) {
                setValue(sValue);
                return this;
            } else {
                throw new IllegalArgumentException("Duplicate string.");
            }
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public Node register(StringTrieBuilder builder) {
            BranchHeadNode head = new BranchHeadNode(this.chars.length(), register(builder, 0, this.chars.length()));
            Node result = head;
            if (this.hasValue) {
                if (builder.matchNodesCanHaveValues()) {
                    head.setValue(this.value);
                } else {
                    result = new IntermediateValueNode(this.value, builder.registerNode(head));
                }
            }
            return builder.registerNode(result);
        }

        private Node register(StringTrieBuilder builder, int start, int limit) {
            int length = limit - start;
            if (length > builder.getMaxBranchLinearSubNodeLength()) {
                int middle = (length / 2) + start;
                return builder.registerNode(new SplitBranchNode(this.chars.charAt(middle), register(builder, start, middle), register(builder, middle, limit)));
            }
            ListBranchNode listNode = new ListBranchNode(length);
            do {
                char c = this.chars.charAt(start);
                Node node = this.equal.get(start);
                if (node.getClass() == ValueNode.class) {
                    listNode.add(c, ((ValueNode) node).value);
                } else {
                    listNode.add(c, node.register(builder));
                }
                start++;
            } while (start < limit);
            return builder.registerNode(listNode);
        }

        private int find(char c) {
            int start = 0;
            int limit = this.chars.length();
            while (start < limit) {
                int i = (start + limit) / 2;
                char middleChar = this.chars.charAt(i);
                if (c < middleChar) {
                    limit = i;
                } else if (c == middleChar) {
                    return i;
                } else {
                    start = i + 1;
                }
            }
            return start;
        }
    }

    private static abstract class BranchNode extends Node {
        protected int firstEdgeNumber;
        protected int hash;

        @Override // android.icu.util.StringTrieBuilder.Node
        public int hashCode() {
            return this.hash;
        }
    }

    /* access modifiers changed from: private */
    public static final class ListBranchNode extends BranchNode {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Node[] equal;
        private int length;
        private char[] units;
        private int[] values;

        public ListBranchNode(int capacity) {
            this.hash = 165535188 + capacity;
            this.equal = new Node[capacity];
            this.values = new int[capacity];
            this.units = new char[capacity];
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            ListBranchNode o = (ListBranchNode) other;
            for (int i = 0; i < this.length; i++) {
                if (!(this.units[i] == o.units[i] && this.values[i] == o.values[i] && this.equal[i] == o.equal[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.icu.util.StringTrieBuilder.BranchNode, android.icu.util.StringTrieBuilder.Node
        public int hashCode() {
            return super.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset == 0) {
                this.firstEdgeNumber = edgeNumber;
                int step = 0;
                int i = this.length;
                do {
                    i--;
                    Node edge = this.equal[i];
                    if (edge != null) {
                        edgeNumber = edge.markRightEdgesFirst(edgeNumber - step);
                    }
                    step = 1;
                } while (i > 0);
                this.offset = edgeNumber;
            }
            return edgeNumber;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder builder) {
            boolean isFinal;
            int value;
            int unitNumber = this.length - 1;
            Node rightEdge = this.equal[unitNumber];
            int rightEdgeNumber = rightEdge == null ? this.firstEdgeNumber : rightEdge.getOffset();
            do {
                unitNumber--;
                Node[] nodeArr = this.equal;
                if (nodeArr[unitNumber] != null) {
                    nodeArr[unitNumber].writeUnlessInsideRightEdge(this.firstEdgeNumber, rightEdgeNumber, builder);
                    continue;
                }
            } while (unitNumber > 0);
            int unitNumber2 = this.length - 1;
            if (rightEdge == null) {
                builder.writeValueAndFinal(this.values[unitNumber2], true);
            } else {
                rightEdge.write(builder);
            }
            this.offset = builder.write(this.units[unitNumber2]);
            while (true) {
                unitNumber2--;
                if (unitNumber2 >= 0) {
                    if (this.equal[unitNumber2] == null) {
                        value = this.values[unitNumber2];
                        isFinal = true;
                    } else {
                        value = this.offset - this.equal[unitNumber2].getOffset();
                        isFinal = false;
                    }
                    builder.writeValueAndFinal(value, isFinal);
                    this.offset = builder.write(this.units[unitNumber2]);
                } else {
                    return;
                }
            }
        }

        public void add(int c, int value) {
            char[] cArr = this.units;
            int i = this.length;
            cArr[i] = (char) c;
            this.equal[i] = null;
            this.values[i] = value;
            this.length = i + 1;
            this.hash = (((this.hash * 37) + c) * 37) + value;
        }

        public void add(int c, Node node) {
            char[] cArr = this.units;
            int i = this.length;
            cArr[i] = (char) c;
            this.equal[i] = node;
            this.values[i] = 0;
            this.length = i + 1;
            this.hash = (((this.hash * 37) + c) * 37) + node.hashCode();
        }
    }

    /* access modifiers changed from: private */
    public static final class SplitBranchNode extends BranchNode {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Node greaterOrEqual;
        private Node lessThan;
        private char unit;

        public SplitBranchNode(char middleUnit, Node lessThanNode, Node greaterOrEqualNode) {
            this.hash = ((((206918985 + middleUnit) * 37) + lessThanNode.hashCode()) * 37) + greaterOrEqualNode.hashCode();
            this.unit = middleUnit;
            this.lessThan = lessThanNode;
            this.greaterOrEqual = greaterOrEqualNode;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            SplitBranchNode o = (SplitBranchNode) other;
            if (this.unit == o.unit && this.lessThan == o.lessThan && this.greaterOrEqual == o.greaterOrEqual) {
                return true;
            }
            return false;
        }

        @Override // android.icu.util.StringTrieBuilder.BranchNode, android.icu.util.StringTrieBuilder.Node
        public int hashCode() {
            return super.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset != 0) {
                return edgeNumber;
            }
            this.firstEdgeNumber = edgeNumber;
            int edgeNumber2 = this.lessThan.markRightEdgesFirst(this.greaterOrEqual.markRightEdgesFirst(edgeNumber) - 1);
            this.offset = edgeNumber2;
            return edgeNumber2;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder builder) {
            this.lessThan.writeUnlessInsideRightEdge(this.firstEdgeNumber, this.greaterOrEqual.getOffset(), builder);
            this.greaterOrEqual.write(builder);
            builder.writeDeltaTo(this.lessThan.getOffset());
            this.offset = builder.write(this.unit);
        }
    }

    private static final class BranchHeadNode extends ValueNode {
        private int length;
        private Node next;

        public BranchHeadNode(int len, Node subNode) {
            this.length = len;
            this.next = subNode;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return ((this.length + 248302782) * 37) + this.next.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!super.equals(other)) {
                return false;
            }
            BranchHeadNode o = (BranchHeadNode) other;
            if (this.length == o.length && this.next == o.next) {
                return true;
            }
            return false;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int edgeNumber) {
            if (this.offset != 0) {
                return edgeNumber;
            }
            int edgeNumber2 = this.next.markRightEdgesFirst(edgeNumber);
            this.offset = edgeNumber2;
            return edgeNumber2;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder builder) {
            this.next.write(builder);
            if (this.length <= builder.getMinLinearMatch()) {
                this.offset = builder.writeValueAndType(this.hasValue, this.value, this.length - 1);
                return;
            }
            builder.write(this.length - 1);
            this.offset = builder.writeValueAndType(this.hasValue, this.value, 0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ValueNode createSuffixNode(CharSequence s, int start, int sValue) {
        ValueNode node = registerFinalValue(sValue);
        if (start >= s.length()) {
            return node;
        }
        int offset = this.strings.length();
        this.strings.append(s, start, s.length());
        return new LinearMatchNode(this.strings, offset, s.length() - start, node);
    }
}
