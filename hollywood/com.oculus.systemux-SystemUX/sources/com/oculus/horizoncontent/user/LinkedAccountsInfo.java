package com.oculus.horizoncontent.user;

public class LinkedAccountsInfo {
    private String mFbLinkedStatus;

    public static class FbLinkedStatus {
        public static final String LINKED = "LINKED";
        public static final String LINKED_BUT_INSUFFICIENT_TERMS = "LINKED_BUT_INSUFFICIENT_TERMS";
    }

    public LinkedAccountsInfo(String str) {
        this.mFbLinkedStatus = str;
    }

    public String toString() {
        return "fbLinkedStatus: " + this.mFbLinkedStatus;
    }

    public boolean isFbLinked() {
        return this.mFbLinkedStatus.equals(FbLinkedStatus.LINKED);
    }

    public boolean isInsufficientTerms() {
        return this.mFbLinkedStatus.equals(FbLinkedStatus.LINKED_BUT_INSUFFICIENT_TERMS);
    }
}
