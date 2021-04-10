package com.bumptech.glide.manager;

import androidx.annotation.NonNull;
import com.bumptech.glide.RequestManager;
import java.util.Set;

public interface RequestManagerTreeNode {
    @NonNull
    Set<RequestManager> getDescendants();
}
