package sun.security.provider.certpath;

import android.icu.impl.number.Padder;
import java.security.cert.PolicyNode;
import java.security.cert.PolicyQualifierInfo;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class PolicyNodeImpl implements PolicyNode {
    private static final String ANY_POLICY = "2.5.29.32.0";
    private boolean isImmutable;
    private HashSet<PolicyNodeImpl> mChildren;
    private boolean mCriticalityIndicator;
    private int mDepth;
    private HashSet<String> mExpectedPolicySet;
    private boolean mOriginalExpectedPolicySet;
    private PolicyNodeImpl mParent;
    private HashSet<PolicyQualifierInfo> mQualifierSet;
    private String mValidPolicy;

    PolicyNodeImpl(PolicyNodeImpl parent, String validPolicy, Set<PolicyQualifierInfo> qualifierSet, boolean criticalityIndicator, Set<String> expectedPolicySet, boolean generatedByPolicyMapping) {
        this.isImmutable = false;
        this.mParent = parent;
        this.mChildren = new HashSet<>();
        if (validPolicy != null) {
            this.mValidPolicy = validPolicy;
        } else {
            this.mValidPolicy = "";
        }
        if (qualifierSet != null) {
            this.mQualifierSet = new HashSet<>(qualifierSet);
        } else {
            this.mQualifierSet = new HashSet<>();
        }
        this.mCriticalityIndicator = criticalityIndicator;
        if (expectedPolicySet != null) {
            this.mExpectedPolicySet = new HashSet<>(expectedPolicySet);
        } else {
            this.mExpectedPolicySet = new HashSet<>();
        }
        this.mOriginalExpectedPolicySet = !generatedByPolicyMapping;
        PolicyNodeImpl policyNodeImpl = this.mParent;
        if (policyNodeImpl != null) {
            this.mDepth = policyNodeImpl.getDepth() + 1;
            this.mParent.addChild(this);
            return;
        }
        this.mDepth = 0;
    }

    PolicyNodeImpl(PolicyNodeImpl parent, PolicyNodeImpl node) {
        this(parent, node.mValidPolicy, node.mQualifierSet, node.mCriticalityIndicator, node.mExpectedPolicySet, false);
    }

    @Override // java.security.cert.PolicyNode
    public PolicyNode getParent() {
        return this.mParent;
    }

    @Override // java.security.cert.PolicyNode
    public Iterator<PolicyNodeImpl> getChildren() {
        return Collections.unmodifiableSet(this.mChildren).iterator();
    }

    @Override // java.security.cert.PolicyNode
    public int getDepth() {
        return this.mDepth;
    }

    @Override // java.security.cert.PolicyNode
    public String getValidPolicy() {
        return this.mValidPolicy;
    }

    @Override // java.security.cert.PolicyNode
    public Set<PolicyQualifierInfo> getPolicyQualifiers() {
        return Collections.unmodifiableSet(this.mQualifierSet);
    }

    @Override // java.security.cert.PolicyNode
    public Set<String> getExpectedPolicies() {
        return Collections.unmodifiableSet(this.mExpectedPolicySet);
    }

    @Override // java.security.cert.PolicyNode
    public boolean isCritical() {
        return this.mCriticalityIndicator;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(asString());
        Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
        while (it.hasNext()) {
            buffer.append((Object) it.next());
        }
        return buffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isImmutable() {
        return this.isImmutable;
    }

    /* access modifiers changed from: package-private */
    public void setImmutable() {
        if (!this.isImmutable) {
            Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
            while (it.hasNext()) {
                it.next().setImmutable();
            }
            this.isImmutable = true;
        }
    }

    private void addChild(PolicyNodeImpl child) {
        if (!this.isImmutable) {
            this.mChildren.add(child);
            return;
        }
        throw new IllegalStateException("PolicyNode is immutable");
    }

    /* access modifiers changed from: package-private */
    public void addExpectedPolicy(String expectedPolicy) {
        if (!this.isImmutable) {
            if (this.mOriginalExpectedPolicySet) {
                this.mExpectedPolicySet.clear();
                this.mOriginalExpectedPolicySet = false;
            }
            this.mExpectedPolicySet.add(expectedPolicy);
            return;
        }
        throw new IllegalStateException("PolicyNode is immutable");
    }

    /* access modifiers changed from: package-private */
    public void prune(int depth) {
        if (this.isImmutable) {
            throw new IllegalStateException("PolicyNode is immutable");
        } else if (this.mChildren.size() != 0) {
            Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
            while (it.hasNext()) {
                PolicyNodeImpl node = it.next();
                node.prune(depth);
                if (node.mChildren.size() == 0 && depth > this.mDepth + 1) {
                    it.remove();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void deleteChild(PolicyNode childNode) {
        if (!this.isImmutable) {
            this.mChildren.remove(childNode);
            return;
        }
        throw new IllegalStateException("PolicyNode is immutable");
    }

    /* access modifiers changed from: package-private */
    public PolicyNodeImpl copyTree() {
        return copyTree(null);
    }

    private PolicyNodeImpl copyTree(PolicyNodeImpl parent) {
        PolicyNodeImpl newNode = new PolicyNodeImpl(parent, this);
        Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().copyTree(newNode);
        }
        return newNode;
    }

    /* access modifiers changed from: package-private */
    public Set<PolicyNodeImpl> getPolicyNodes(int depth) {
        Set<PolicyNodeImpl> set = new HashSet<>();
        getPolicyNodes(depth, set);
        return set;
    }

    private void getPolicyNodes(int depth, Set<PolicyNodeImpl> set) {
        if (this.mDepth == depth) {
            set.add(this);
            return;
        }
        Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().getPolicyNodes(depth, set);
        }
    }

    /* access modifiers changed from: package-private */
    public Set<PolicyNodeImpl> getPolicyNodesExpected(int depth, String expectedOID, boolean matchAny) {
        if (expectedOID.equals(ANY_POLICY)) {
            return getPolicyNodes(depth);
        }
        return getPolicyNodesExpectedHelper(depth, expectedOID, matchAny);
    }

    private Set<PolicyNodeImpl> getPolicyNodesExpectedHelper(int depth, String expectedOID, boolean matchAny) {
        HashSet<PolicyNodeImpl> set = new HashSet<>();
        if (this.mDepth < depth) {
            Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
            while (it.hasNext()) {
                set.addAll(it.next().getPolicyNodesExpectedHelper(depth, expectedOID, matchAny));
            }
        } else if (matchAny) {
            if (this.mExpectedPolicySet.contains(ANY_POLICY)) {
                set.add(this);
            }
        } else if (this.mExpectedPolicySet.contains(expectedOID)) {
            set.add(this);
        }
        return set;
    }

    /* access modifiers changed from: package-private */
    public Set<PolicyNodeImpl> getPolicyNodesValid(int depth, String validOID) {
        HashSet<PolicyNodeImpl> set = new HashSet<>();
        if (this.mDepth < depth) {
            Iterator<PolicyNodeImpl> it = this.mChildren.iterator();
            while (it.hasNext()) {
                set.addAll(it.next().getPolicyNodesValid(depth, validOID));
            }
        } else if (this.mValidPolicy.equals(validOID)) {
            set.add(this);
        }
        return set;
    }

    private static String policyToString(String oid) {
        if (oid.equals(ANY_POLICY)) {
            return "anyPolicy";
        }
        return oid;
    }

    /* access modifiers changed from: package-private */
    public String asString() {
        if (this.mParent == null) {
            return "anyPolicy  ROOT\n";
        }
        StringBuilder sb = new StringBuilder();
        int n = getDepth();
        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        sb.append(policyToString(getValidPolicy()));
        sb.append("  CRIT: ");
        sb.append(isCritical());
        sb.append("  EP: ");
        for (String policy : getExpectedPolicies()) {
            sb.append(policyToString(policy));
            sb.append(Padder.FALLBACK_PADDING_STRING);
        }
        sb.append(" (");
        sb.append(getDepth());
        sb.append(")\n");
        return sb.toString();
    }
}
