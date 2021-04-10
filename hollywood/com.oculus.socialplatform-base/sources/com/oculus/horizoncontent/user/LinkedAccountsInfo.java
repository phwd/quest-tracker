package com.oculus.horizoncontent.user;

import X.AnonymousClass006;

public class LinkedAccountsInfo {
    public String mFbLinkedStatus;

    public static class FbLinkedStatus {
        public static final String LINKED = "LINKED";
        public static final String LINKED_BUT_INSUFFICIENT_TERMS = "LINKED_BUT_INSUFFICIENT_TERMS";
    }

    public boolean isFbLinked() {
        return this.mFbLinkedStatus.equals(FbLinkedStatus.LINKED);
    }

    public boolean isInsufficientTerms() {
        return this.mFbLinkedStatus.equals(FbLinkedStatus.LINKED_BUT_INSUFFICIENT_TERMS);
    }

    public String toString() {
        return AnonymousClass006.A07("fbLinkedStatus: ", this.mFbLinkedStatus);
    }

    public LinkedAccountsInfo(String str) {
        this.mFbLinkedStatus = str;
    }
}
