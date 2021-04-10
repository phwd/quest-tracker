package com.facebook.secure.content;

import X.AbstractC0195ed;
import X.AbstractC0201ew;
import javax.annotation.Nullable;

public abstract class FbPermissionsContentProvider extends AbstractC0195ed {
    public abstract String getFbPermission();

    @Nullable
    public AbstractC0201ew getReporter() {
        return null;
    }

    public String getReadOnlyFbPermission() {
        throw null;
    }

    @Override // X.AbstractC0195ed
    public boolean onCheckPermissions() {
        throw null;
    }

    @Override // X.AbstractC0195ed
    public boolean onCheckReadOnlyPermissions() {
        throw null;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:215:0x0140 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r32v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r32v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r32v2, types: [java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onCheckPermissionsHelper(java.lang.String r43) {
        /*
        // Method dump skipped, instructions count: 1373
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.content.FbPermissionsContentProvider.onCheckPermissionsHelper(java.lang.String):boolean");
    }
}
