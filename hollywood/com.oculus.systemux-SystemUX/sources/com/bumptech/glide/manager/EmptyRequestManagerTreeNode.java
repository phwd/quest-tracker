package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.RequestManager;
import java.util.Collections;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {
    EmptyRequestManagerTreeNode() {
    }

    @Override // com.bumptech.glide.manager.RequestManagerTreeNode
    @NonNull
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
