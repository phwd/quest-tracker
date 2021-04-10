package com.facebook.gk.store;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
final class StateFileContent {
    public final String gatekeeperNamesHash;
    public final byte[] gatekeeperStates;

    public StateFileContent(String str, byte[] bArr) {
        this.gatekeeperNamesHash = str;
        this.gatekeeperStates = bArr;
    }

    public final boolean hashMatches(String str) {
        return TextUtils.equals(this.gatekeeperNamesHash, str);
    }
}
