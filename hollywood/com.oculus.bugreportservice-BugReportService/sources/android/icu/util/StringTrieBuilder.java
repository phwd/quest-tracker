package android.icu.util;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class StringTrieBuilder {
    private ValueNode lookupFinalValueNode = new ValueNode();
    private HashMap nodes = new HashMap();
    private Node root;
    private State state = State.ADDING;
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
    public abstract int getMaxBranchLinearSubNodeLength();

    /* access modifiers changed from: protected */
    public abstract int getMaxLinearMatchLength();

    /* access modifiers changed from: protected */
    public abstract int getMinLinearMatch();

    /* access modifiers changed from: protected */
    public abstract boolean matchNodesCanHaveValues();

    /* access modifiers changed from: protected */
    public abstract int write(int i);

    /* access modifiers changed from: protected */
    public abstract int write(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract int writeDeltaTo(int i);

    /* access modifiers changed from: protected */
    public abstract int writeValueAndFinal(int i, boolean z);

    /* access modifiers changed from: protected */
    public abstract int writeValueAndType(boolean z, int i, int i2);

    protected StringTrieBuilder() {
    }

    /* access modifiers changed from: protected */
    public void addImpl(CharSequence charSequence, int i) {
        if (this.state != State.ADDING) {
            throw new IllegalStateException("Cannot add (string, value) pairs after build().");
        } else if (charSequence.length() <= 65535) {
            Node node = this.root;
            if (node == null) {
                this.root = createSuffixNode(charSequence, 0, i);
            } else {
                this.root = node.add(this, charSequence, 0, i);
            }
        } else {
            throw new IndexOutOfBoundsException("The maximum string length is 0xffff.");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.StringTrieBuilder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$StringTrieBuilder$State = new int[State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.icu.util.StringTrieBuilder$State[] r0 = android.icu.util.StringTrieBuilder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.util.StringTrieBuilder.AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State = r0
                int[] r0 = android.icu.util.StringTrieBuilder.AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.StringTrieBuilder$State r1 = android.icu.util.StringTrieBuilder.State.ADDING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.util.StringTrieBuilder.AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.StringTrieBuilder$State r1 = android.icu.util.StringTrieBuilder.State.BUILDING_FAST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.util.StringTrieBuilder.AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.util.StringTrieBuilder$State r1 = android.icu.util.StringTrieBuilder.State.BUILDING_SMALL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = android.icu.util.StringTrieBuilder.AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.icu.util.StringTrieBuilder$State r1 = android.icu.util.StringTrieBuilder.State.BUILT     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.StringTrieBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final void buildImpl(Option option) {
        int i = AnonymousClass1.$SwitchMap$android$icu$util$StringTrieBuilder$State[this.state.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                throw new IllegalStateException("Builder failed and must be clear()ed.");
            } else if (i == 4) {
                return;
            }
        } else if (this.root == null) {
            throw new IndexOutOfBoundsException("No (string, value) pairs were added.");
        } else if (option == Option.FAST) {
            this.state = State.BUILDING_FAST;
        } else {
            this.state = State.BUILDING_SMALL;
        }
        this.root = this.root.register(this);
        this.root.markRightEdgesFirst(-1);
        this.root.write(this);
        this.state = State.BUILT;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final Node registerNode(Node node) {
        if (this.state == State.BUILDING_FAST) {
            return node;
        }
        Node node2 = (Node) this.nodes.get(node);
        if (node2 != null) {
            return node2;
        }
        Node node3 = (Node) this.nodes.put(node, node);
        return node;
    }

    private final ValueNode registerFinalValue(int i) {
        this.lookupFinalValueNode.setFinalValue(i);
        Node node = (Node) this.nodes.get(this.lookupFinalValueNode);
        if (node != null) {
            return (ValueNode) node;
        }
        ValueNode valueNode = new ValueNode(i);
        Node node2 = (Node) this.nodes.put(valueNode, valueNode);
        return valueNode;
    }

    /* access modifiers changed from: private */
    public static abstract class Node {
        protected int offset = 0;

        public Node add(StringTrieBuilder stringTrieBuilder, CharSequence charSequence, int i, int i2) {
            return this;
        }

        public Node register(StringTrieBuilder stringTrieBuilder) {
            return this;
        }

        public abstract void write(StringTrieBuilder stringTrieBuilder);

        public boolean equals(Object obj) {
            return this == obj || getClass() == obj.getClass();
        }

        public int markRightEdgesFirst(int i) {
            if (this.offset == 0) {
                this.offset = i;
            }
            return i;
        }

        public final void writeUnlessInsideRightEdge(int i, int i2, StringTrieBuilder stringTrieBuilder) {
            int i3 = this.offset;
            if (i3 >= 0) {
                return;
            }
            if (i3 < i2 || i < i3) {
                write(stringTrieBuilder);
            }
        }

        public final int getOffset() {
            return this.offset;
        }
    }

    /* access modifiers changed from: private */
    public static class ValueNode extends Node {
        protected boolean hasValue;
        protected int value;

        public ValueNode() {
        }

        public ValueNode(int i) {
            this.hasValue = true;
            this.value = i;
        }

        public final void setValue(int i) {
            this.hasValue = true;
            this.value = i;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setFinalValue(int i) {
            this.hasValue = true;
            this.value = i;
        }

        public int hashCode() {
            if (this.hasValue) {
                return 41383797 + this.value;
            }
            return 1118481;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            ValueNode valueNode = (ValueNode) obj;
            boolean z = this.hasValue;
            return z == valueNode.hasValue && (!z || this.value == valueNode.value);
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public Node add(StringTrieBuilder stringTrieBuilder, CharSequence charSequence, int i, int i2) {
            if (i != charSequence.length()) {
                ValueNode createSuffixNode = stringTrieBuilder.createSuffixNode(charSequence, i, i2);
                createSuffixNode.setValue(this.value);
                return createSuffixNode;
            }
            throw new IllegalArgumentException("Duplicate string.");
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder stringTrieBuilder) {
            this.offset = stringTrieBuilder.writeValueAndFinal(this.value, true);
        }
    }

    private static final class IntermediateValueNode extends ValueNode {
        private Node next;

        public IntermediateValueNode(int i, Node node) {
            this.next = node;
            setValue(i);
        }

        @Override // android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return ((this.value + 82767594) * 37) + this.next.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            return this.next == ((IntermediateValueNode) obj).next;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int i) {
            if (this.offset != 0) {
                return i;
            }
            int markRightEdgesFirst = this.next.markRightEdgesFirst(i);
            this.offset = markRightEdgesFirst;
            return markRightEdgesFirst;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder stringTrieBuilder) {
            this.next.write(stringTrieBuilder);
            this.offset = stringTrieBuilder.writeValueAndFinal(this.value, false);
        }
    }

    /* access modifiers changed from: private */
    public static final class LinearMatchNode extends ValueNode {
        private int hash;
        private int length;
        private Node next;
        private int stringOffset;
        private CharSequence strings;

        public LinearMatchNode(CharSequence charSequence, int i, int i2, Node node) {
            this.strings = charSequence;
            this.stringOffset = i;
            this.length = i2;
            this.next = node;
        }

        @Override // android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return this.hash;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            LinearMatchNode linearMatchNode = (LinearMatchNode) obj;
            int i = this.length;
            if (i != linearMatchNode.length || this.next != linearMatchNode.next) {
                return false;
            }
            int i2 = this.stringOffset;
            int i3 = linearMatchNode.stringOffset;
            int i4 = i + i2;
            while (i2 < i4) {
                if (this.strings.charAt(i2) != this.strings.charAt(i3)) {
                    return false;
                }
                i2++;
                i3++;
            }
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: android.icu.util.StringTrieBuilder$DynamicBranchNode */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v4 */
        /* JADX WARN: Type inference failed for: r10v7 */
        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public Node add(StringTrieBuilder stringTrieBuilder, CharSequence charSequence, int i, int i2) {
            LinearMatchNode linearMatchNode;
            LinearMatchNode linearMatchNode2;
            if (i != charSequence.length()) {
                int i3 = this.stringOffset;
                int i4 = this.length + i3;
                while (i3 < i4) {
                    if (i == charSequence.length()) {
                        int i5 = i3 - this.stringOffset;
                        LinearMatchNode linearMatchNode3 = new LinearMatchNode(this.strings, i3, this.length - i5, this.next);
                        linearMatchNode3.setValue(i2);
                        this.length = i5;
                        this.next = linearMatchNode3;
                        return this;
                    }
                    char charAt = this.strings.charAt(i3);
                    char charAt2 = charSequence.charAt(i);
                    if (charAt != charAt2) {
                        DynamicBranchNode dynamicBranchNode = new DynamicBranchNode();
                        int i6 = this.stringOffset;
                        if (i3 == i6) {
                            if (this.hasValue) {
                                dynamicBranchNode.setValue(this.value);
                                this.value = 0;
                                this.hasValue = false;
                            }
                            this.stringOffset++;
                            this.length--;
                            int i7 = this.length;
                            ?? r10 = this;
                            if (i7 <= 0) {
                                r10 = this.next;
                            }
                            linearMatchNode2 = dynamicBranchNode;
                            linearMatchNode = r10;
                        } else if (i3 == i4 - 1) {
                            this.length--;
                            Node node = this.next;
                            this.next = dynamicBranchNode;
                            linearMatchNode2 = this;
                            linearMatchNode = node;
                        } else {
                            int i8 = i3 - i6;
                            LinearMatchNode linearMatchNode4 = new LinearMatchNode(this.strings, i3 + 1, this.length - (i8 + 1), this.next);
                            this.length = i8;
                            this.next = dynamicBranchNode;
                            linearMatchNode2 = this;
                            linearMatchNode = linearMatchNode4;
                        }
                        ValueNode createSuffixNode = stringTrieBuilder.createSuffixNode(charSequence, i + 1, i2);
                        dynamicBranchNode.add(charAt, linearMatchNode);
                        dynamicBranchNode.add(charAt2, createSuffixNode);
                        return linearMatchNode2;
                    }
                    i3++;
                    i++;
                }
                this.next = this.next.add(stringTrieBuilder, charSequence, i, i2);
                return this;
            } else if (!this.hasValue) {
                setValue(i2);
                return this;
            } else {
                throw new IllegalArgumentException("Duplicate string.");
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [android.icu.util.StringTrieBuilder$IntermediateValueNode] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // android.icu.util.StringTrieBuilder.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.icu.util.StringTrieBuilder.Node register(android.icu.util.StringTrieBuilder r6) {
            /*
                r5 = this;
                android.icu.util.StringTrieBuilder$Node r0 = r5.next
                android.icu.util.StringTrieBuilder$Node r0 = r0.register(r6)
                r5.next = r0
                int r0 = r6.getMaxLinearMatchLength()
            L_0x000c:
                int r1 = r5.length
                if (r1 <= r0) goto L_0x002a
                int r2 = r5.stringOffset
                int r2 = r2 + r1
                int r2 = r2 - r0
                int r1 = r1 - r0
                r5.length = r1
                android.icu.util.StringTrieBuilder$LinearMatchNode r1 = new android.icu.util.StringTrieBuilder$LinearMatchNode
                java.lang.CharSequence r3 = r5.strings
                android.icu.util.StringTrieBuilder$Node r4 = r5.next
                r1.<init>(r3, r2, r0, r4)
                r1.setHashCode()
                android.icu.util.StringTrieBuilder$Node r1 = android.icu.util.StringTrieBuilder.access$200(r6, r1)
                r5.next = r1
                goto L_0x000c
            L_0x002a:
                boolean r0 = r5.hasValue
                if (r0 == 0) goto L_0x0049
                boolean r0 = r6.matchNodesCanHaveValues()
                if (r0 != 0) goto L_0x0049
                int r0 = r5.value
                r1 = 0
                r5.value = r1
                r5.hasValue = r1
                r5.setHashCode()
                android.icu.util.StringTrieBuilder$IntermediateValueNode r1 = new android.icu.util.StringTrieBuilder$IntermediateValueNode
                android.icu.util.StringTrieBuilder$Node r5 = android.icu.util.StringTrieBuilder.access$200(r6, r5)
                r1.<init>(r0, r5)
                r5 = r1
                goto L_0x004c
            L_0x0049:
                r5.setHashCode()
            L_0x004c:
                android.icu.util.StringTrieBuilder$Node r5 = android.icu.util.StringTrieBuilder.access$200(r6, r5)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.StringTrieBuilder.LinearMatchNode.register(android.icu.util.StringTrieBuilder):android.icu.util.StringTrieBuilder$Node");
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int i) {
            if (this.offset != 0) {
                return i;
            }
            int markRightEdgesFirst = this.next.markRightEdgesFirst(i);
            this.offset = markRightEdgesFirst;
            return markRightEdgesFirst;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder stringTrieBuilder) {
            this.next.write(stringTrieBuilder);
            stringTrieBuilder.write(this.stringOffset, this.length);
            this.offset = stringTrieBuilder.writeValueAndType(this.hasValue, this.value, (stringTrieBuilder.getMinLinearMatch() + this.length) - 1);
        }

        private void setHashCode() {
            this.hash = ((this.length + 124151391) * 37) + this.next.hashCode();
            if (this.hasValue) {
                this.hash = (this.hash * 37) + this.value;
            }
            int i = this.stringOffset;
            int i2 = this.length + i;
            while (i < i2) {
                this.hash = (this.hash * 37) + this.strings.charAt(i);
                i++;
            }
        }
    }

    private static final class DynamicBranchNode extends ValueNode {
        private StringBuilder chars = new StringBuilder();
        private ArrayList equal = new ArrayList();

        public void add(char c, Node node) {
            int find = find(c);
            this.chars.insert(find, c);
            this.equal.add(find, node);
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public Node add(StringTrieBuilder stringTrieBuilder, CharSequence charSequence, int i, int i2) {
            if (i != charSequence.length()) {
                int i3 = i + 1;
                char charAt = charSequence.charAt(i);
                int find = find(charAt);
                if (find >= this.chars.length() || charAt != this.chars.charAt(find)) {
                    this.chars.insert(find, charAt);
                    this.equal.add(find, stringTrieBuilder.createSuffixNode(charSequence, i3, i2));
                } else {
                    ArrayList arrayList = this.equal;
                    arrayList.set(find, ((Node) arrayList.get(find)).add(stringTrieBuilder, charSequence, i3, i2));
                }
                return this;
            } else if (!this.hasValue) {
                setValue(i2);
                return this;
            } else {
                throw new IllegalArgumentException("Duplicate string.");
            }
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public Node register(StringTrieBuilder stringTrieBuilder) {
            Node node;
            BranchHeadNode branchHeadNode = new BranchHeadNode(this.chars.length(), register(stringTrieBuilder, 0, this.chars.length()));
            if (this.hasValue) {
                if (stringTrieBuilder.matchNodesCanHaveValues()) {
                    branchHeadNode.setValue(this.value);
                } else {
                    node = new IntermediateValueNode(this.value, stringTrieBuilder.registerNode(branchHeadNode));
                    return stringTrieBuilder.registerNode(node);
                }
            }
            node = branchHeadNode;
            return stringTrieBuilder.registerNode(node);
        }

        private Node register(StringTrieBuilder stringTrieBuilder, int i, int i2) {
            int i3 = i2 - i;
            if (i3 > stringTrieBuilder.getMaxBranchLinearSubNodeLength()) {
                int i4 = (i3 / 2) + i;
                return stringTrieBuilder.registerNode(new SplitBranchNode(this.chars.charAt(i4), register(stringTrieBuilder, i, i4), register(stringTrieBuilder, i4, i2)));
            }
            ListBranchNode listBranchNode = new ListBranchNode(i3);
            do {
                char charAt = this.chars.charAt(i);
                Node node = (Node) this.equal.get(i);
                if (node.getClass() == ValueNode.class) {
                    listBranchNode.add(charAt, ((ValueNode) node).value);
                } else {
                    listBranchNode.add(charAt, node.register(stringTrieBuilder));
                }
                i++;
            } while (i < i2);
            return stringTrieBuilder.registerNode(listBranchNode);
        }

        private int find(char c) {
            int length = this.chars.length();
            int i = 0;
            while (i < length) {
                int i2 = (i + length) / 2;
                char charAt = this.chars.charAt(i2);
                if (c < charAt) {
                    length = i2;
                } else if (c == charAt) {
                    return i2;
                } else {
                    i = i2 + 1;
                }
            }
            return i;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class BranchNode extends Node {
        protected int firstEdgeNumber;
        protected int hash;

        public int hashCode() {
            return this.hash;
        }
    }

    /* access modifiers changed from: private */
    public static final class ListBranchNode extends BranchNode {
        private Node[] equal;
        private int length;
        private char[] units;
        private int[] values;

        public ListBranchNode(int i) {
            this.hash = 165535188 + i;
            this.equal = new Node[i];
            this.values = new int[i];
            this.units = new char[i];
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            ListBranchNode listBranchNode = (ListBranchNode) obj;
            for (int i = 0; i < this.length; i++) {
                if (!(this.units[i] == listBranchNode.units[i] && this.values[i] == listBranchNode.values[i] && this.equal[i] == listBranchNode.equal[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override // android.icu.util.StringTrieBuilder.BranchNode
        public int hashCode() {
            return super.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int i) {
            if (this.offset == 0) {
                this.firstEdgeNumber = i;
                int i2 = 0;
                int i3 = this.length;
                do {
                    i3--;
                    Node node = this.equal[i3];
                    if (node != null) {
                        i = node.markRightEdgesFirst(i - i2);
                    }
                    i2 = 1;
                } while (i3 > 0);
                this.offset = i;
            }
            return i;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder stringTrieBuilder) {
            boolean z;
            int i;
            int i2 = this.length - 1;
            Node node = this.equal[i2];
            int offset = node == null ? this.firstEdgeNumber : node.getOffset();
            do {
                i2--;
                Node[] nodeArr = this.equal;
                if (nodeArr[i2] != null) {
                    nodeArr[i2].writeUnlessInsideRightEdge(this.firstEdgeNumber, offset, stringTrieBuilder);
                    continue;
                }
            } while (i2 > 0);
            int i3 = this.length - 1;
            if (node == null) {
                stringTrieBuilder.writeValueAndFinal(this.values[i3], true);
            } else {
                node.write(stringTrieBuilder);
            }
            this.offset = stringTrieBuilder.write(this.units[i3]);
            while (true) {
                i3--;
                if (i3 >= 0) {
                    Node[] nodeArr2 = this.equal;
                    if (nodeArr2[i3] == null) {
                        i = this.values[i3];
                        z = true;
                    } else {
                        i = this.offset - nodeArr2[i3].getOffset();
                        z = false;
                    }
                    stringTrieBuilder.writeValueAndFinal(i, z);
                    this.offset = stringTrieBuilder.write(this.units[i3]);
                } else {
                    return;
                }
            }
        }

        public void add(int i, int i2) {
            char[] cArr = this.units;
            int i3 = this.length;
            cArr[i3] = (char) i;
            this.equal[i3] = null;
            this.values[i3] = i2;
            this.length = i3 + 1;
            this.hash = (((this.hash * 37) + i) * 37) + i2;
        }

        public void add(int i, Node node) {
            char[] cArr = this.units;
            int i2 = this.length;
            cArr[i2] = (char) i;
            this.equal[i2] = node;
            this.values[i2] = 0;
            this.length = i2 + 1;
            this.hash = (((this.hash * 37) + i) * 37) + node.hashCode();
        }
    }

    /* access modifiers changed from: private */
    public static final class SplitBranchNode extends BranchNode {
        private Node greaterOrEqual;
        private Node lessThan;
        private char unit;

        public SplitBranchNode(char c, Node node, Node node2) {
            this.hash = ((((206918985 + c) * 37) + node.hashCode()) * 37) + node2.hashCode();
            this.unit = c;
            this.lessThan = node;
            this.greaterOrEqual = node2;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            SplitBranchNode splitBranchNode = (SplitBranchNode) obj;
            return this.unit == splitBranchNode.unit && this.lessThan == splitBranchNode.lessThan && this.greaterOrEqual == splitBranchNode.greaterOrEqual;
        }

        @Override // android.icu.util.StringTrieBuilder.BranchNode
        public int hashCode() {
            return super.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int i) {
            if (this.offset != 0) {
                return i;
            }
            this.firstEdgeNumber = i;
            int markRightEdgesFirst = this.lessThan.markRightEdgesFirst(this.greaterOrEqual.markRightEdgesFirst(i) - 1);
            this.offset = markRightEdgesFirst;
            return markRightEdgesFirst;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public void write(StringTrieBuilder stringTrieBuilder) {
            this.lessThan.writeUnlessInsideRightEdge(this.firstEdgeNumber, this.greaterOrEqual.getOffset(), stringTrieBuilder);
            this.greaterOrEqual.write(stringTrieBuilder);
            stringTrieBuilder.writeDeltaTo(this.lessThan.getOffset());
            this.offset = stringTrieBuilder.write(this.unit);
        }
    }

    private static final class BranchHeadNode extends ValueNode {
        private int length;
        private Node next;

        public BranchHeadNode(int i, Node node) {
            this.length = i;
            this.next = node;
        }

        @Override // android.icu.util.StringTrieBuilder.ValueNode
        public int hashCode() {
            return ((this.length + 248302782) * 37) + this.next.hashCode();
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!super.equals(obj)) {
                return false;
            }
            BranchHeadNode branchHeadNode = (BranchHeadNode) obj;
            return this.length == branchHeadNode.length && this.next == branchHeadNode.next;
        }

        @Override // android.icu.util.StringTrieBuilder.Node
        public int markRightEdgesFirst(int i) {
            if (this.offset != 0) {
                return i;
            }
            int markRightEdgesFirst = this.next.markRightEdgesFirst(i);
            this.offset = markRightEdgesFirst;
            return markRightEdgesFirst;
        }

        @Override // android.icu.util.StringTrieBuilder.Node, android.icu.util.StringTrieBuilder.ValueNode
        public void write(StringTrieBuilder stringTrieBuilder) {
            this.next.write(stringTrieBuilder);
            if (this.length <= stringTrieBuilder.getMinLinearMatch()) {
                this.offset = stringTrieBuilder.writeValueAndType(this.hasValue, this.value, this.length - 1);
                return;
            }
            stringTrieBuilder.write(this.length - 1);
            this.offset = stringTrieBuilder.writeValueAndType(this.hasValue, this.value, 0);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ValueNode createSuffixNode(CharSequence charSequence, int i, int i2) {
        ValueNode registerFinalValue = registerFinalValue(i2);
        if (i >= charSequence.length()) {
            return registerFinalValue;
        }
        int length = this.strings.length();
        this.strings.append(charSequence, i, charSequence.length());
        return new LinearMatchNode(this.strings, length, charSequence.length() - i, registerFinalValue);
    }
}
