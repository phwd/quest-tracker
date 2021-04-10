package com.facebook.oxygen.common.verification;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum ExternalSignatureType {
    NONE(AlgorithmConstants.LEGACY_ALG),
    OZONE(AlgorithmConstants.LEGACY_ALG),
    FULL(AlgorithmConstants.FULL_HASH),
    V2COMPAT(AlgorithmConstants.ASBC_MERKLE_TREE_HASH),
    OCULUS_FULL(AlgorithmConstants.FULL_HASH);
    
    public final int signatureAlgorithm;

    public static class AlgorithmConstants {
        public static int ASBC_MERKLE_TREE_HASH = 1;
        public static int FULL_HASH = 0;
        public static int LEGACY_ALG = -1;
    }

    private ExternalSignatureType(int i) {
        this.signatureAlgorithm = i;
    }
}
