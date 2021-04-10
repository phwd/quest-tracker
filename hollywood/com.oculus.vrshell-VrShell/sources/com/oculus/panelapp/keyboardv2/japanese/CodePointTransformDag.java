package com.oculus.panelapp.keyboardv2.japanese;

import android.util.Log;
import java.util.HashMap;

abstract class CodePointTransformDag {
    public static final int MAX_SEQUENCE_SIZE = 16;
    public static final String TAG = "com.oculus.panelapp.keyboardv2.japanese.CodePointTransformDag";
    private HashMap<String, Node> mCodePointMap = new HashMap<>();

    /* access modifiers changed from: private */
    public static class Node {
        private final String mCodePoint;
        private Node mNextNode;

        public Node(String str) {
            this.mCodePoint = str;
        }

        public String getCodePoint() {
            return this.mCodePoint;
        }

        public void setNext(Node node) {
            this.mNextNode = node;
        }

        public Node getNextNode() {
            return this.mNextNode;
        }
    }

    public CodePointTransformDag(String[][] strArr) {
        for (String[] strArr2 : strArr) {
            if (strArr2.length <= 16) {
                makeNodeList(strArr2);
            } else {
                throw new RuntimeException("Sequence length exceeds MAX_SEQUENCE_SIZE. If this is expected, please expand MAX_SEQUENCE_SIZE");
            }
        }
    }

    public String next(String str) {
        Node node = this.mCodePointMap.get(str);
        if (node == null) {
            return str;
        }
        return node.getNextNode().getCodePoint();
    }

    private void makeNodeList(String[] strArr) {
        int length = strArr.length;
        Node node = null;
        int i = 0;
        Node node2 = null;
        while (i < length) {
            String str = strArr[i];
            Node node3 = new Node(str);
            if (node2 == null) {
                node2 = node3;
            } else {
                node.setNext(node3);
            }
            this.mCodePointMap.put(str, node3);
            i++;
            node = node3;
        }
        node.setNext(node2);
        verifyCycle(node2);
    }

    private void verifyCycle(Node node) {
        Node nextNode = node.getNextNode();
        for (int i = 1; i < 16; i++) {
            if (nextNode != node) {
                nextNode = nextNode.getNextNode();
            } else {
                return;
            }
        }
        throw new RuntimeException("Improper cycle detected!");
    }

    private void visualize(String[][] strArr) {
        Log.d(TAG, String.format("Visualizing %s", getClass().getSimpleName()));
        for (String[] strArr2 : strArr) {
            Node node = this.mCodePointMap.get(strArr2[0]);
            StringBuilder sb = new StringBuilder();
            Node node2 = node;
            do {
                sb.append(node2.getCodePoint());
                sb.append("=>");
                node2 = node2.getNextNode();
            } while (node2 != node);
            Log.d(TAG, sb.toString());
        }
    }
}
