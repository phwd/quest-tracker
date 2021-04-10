package com.facebook.gk.store;

import com.facebook.common.util.TriState;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Arrays;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class GatekeeperEditor implements GatekeeperWriter.Editor {
    protected TriState[] mGatekeeperOverriddenStates;
    protected TriState[] mGatekeeperStates;
    protected boolean mSkipInitialized;

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public abstract void commit();

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public abstract void commit(boolean z);

    /* access modifiers changed from: protected */
    public abstract int gatekeeperByName(String str);

    public GatekeeperEditor(int i) {
        this.mGatekeeperStates = new TriState[i];
        this.mGatekeeperOverriddenStates = new TriState[i];
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor set(int i, boolean z) {
        this.mGatekeeperStates[i] = TriState.valueOf(z);
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor set(String str, boolean z) {
        return set(gatekeeperByName(str), z);
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor set(int i, TriState triState) {
        this.mGatekeeperStates[i] = triState;
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor set(String str, TriState triState) {
        return set(gatekeeperByName(str), triState);
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor setAll(boolean[] zArr) {
        if (zArr.length != this.mGatekeeperStates.length) {
            return this;
        }
        int length = this.mGatekeeperStates.length;
        for (int i = 0; i < length; i++) {
            this.mGatekeeperStates[i] = TriState.valueOf(zArr[i]);
        }
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor override(int i, TriState triState) {
        this.mGatekeeperOverriddenStates[i] = triState;
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor override(int i, boolean z) {
        return override(i, TriState.valueOf(z));
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor override(String str, boolean z) {
        return override(gatekeeperByName(str), z);
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor override(String str, TriState triState) {
        return override(gatekeeperByName(str), triState);
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor unset(int i) {
        this.mGatekeeperStates[i] = TriState.UNSET;
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor unset(String str) {
        return unset(gatekeeperByName(str));
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor clearOverride(int i) {
        this.mGatekeeperOverriddenStates[i] = TriState.UNSET;
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor clearOverride(String str) {
        return clearOverride(gatekeeperByName(str));
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor clearOverrideAll() {
        Arrays.fill(this.mGatekeeperOverriddenStates, TriState.UNSET);
        return this;
    }

    @Override // com.facebook.gk.store.GatekeeperWriter.Editor
    public synchronized GatekeeperEditor skipInitialized() {
        this.mSkipInitialized = true;
        return this;
    }
}
