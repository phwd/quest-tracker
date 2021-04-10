package com.facebook.gk.store;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;

@Nullsafe(Nullsafe.Mode.LOCAL)
final class NamesFileContent {
    public final ArrayList<String> gatekeeperNames;
    public final String gatekeeperNamesHash;

    NamesFileContent(String str, ArrayList<String> arrayList) {
        this.gatekeeperNamesHash = str;
        this.gatekeeperNames = arrayList;
    }
}
