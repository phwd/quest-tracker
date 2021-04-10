package com.oculus.companion.server.connection;

import com.oculus.companion.server.Protocol$Method;
import com.oculus.companion.server.Protocol$Request;
import java.util.HashSet;

/* access modifiers changed from: package-private */
public class SequenceRecorder {
    private final HashSet<Integer> mSequenceSeen = new HashSet<>();

    SequenceRecorder() {
    }

    /* access modifiers changed from: package-private */
    public boolean addAndVerifySequence(Protocol$Request protocol$Request) {
        return this.mSequenceSeen.add(Integer.valueOf(protocol$Request.getSeq())) || protocol$Request.getMethod() == Protocol$Method.HELLO;
    }

    /* access modifiers changed from: package-private */
    public void requestSequence() {
        this.mSequenceSeen.clear();
    }
}
