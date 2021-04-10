package com.facebook.stetho.inspector.elements.android;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.stetho.common.android.AccessibilityUtil;
import com.oculus.horizon.logging.QPLogEventBuilder;

public final class AccessibilityNodeInfoWrapper {
    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        ViewCompat.onInitializeAccessibilityNodeInfo(view, obtain);
        return obtain;
    }

    public static boolean getIsAccessibilityFocused(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        boolean isAccessibilityFocused = createNodeInfoFromView.isAccessibilityFocused();
        createNodeInfoFromView.recycle();
        return isAccessibilityFocused;
    }

    public static boolean getIgnored(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2 || importantForAccessibility == 4) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return true;
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!createNodeInfoFromView.isVisibleToUser()) {
                return true;
            }
            if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else if (AccessibilityUtil.isSpeakingNode(createNodeInfoFromView, view)) {
                    createNodeInfoFromView.recycle();
                    return false;
                } else {
                    createNodeInfoFromView.recycle();
                    return true;
                }
            } else if (AccessibilityUtil.hasFocusableAncestor(createNodeInfoFromView, view) || !AccessibilityUtil.hasText(createNodeInfoFromView)) {
                createNodeInfoFromView.recycle();
                return true;
            } else {
                createNodeInfoFromView.recycle();
                return false;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    public static String getIgnoredReasons(View view) {
        int importantForAccessibility = ViewCompat.getImportantForAccessibility(view);
        if (importantForAccessibility == 2) {
            return "View has importantForAccessibility set to 'NO'.";
        }
        if (importantForAccessibility == 4) {
            return "View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (ViewCompat.getImportantForAccessibility((View) parent) == 4) {
                return "An ancestor View has importantForAccessibility set to 'NO_HIDE_DESCENDANTS'.";
            }
        }
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            if (!createNodeInfoFromView.isVisibleToUser()) {
                return "View is not visible.";
            }
            if (AccessibilityUtil.isAccessibilityFocusable(createNodeInfoFromView, view)) {
                createNodeInfoFromView.recycle();
                return "View is actionable, but has no description.";
            } else if (AccessibilityUtil.hasText(createNodeInfoFromView)) {
                createNodeInfoFromView.recycle();
                return "View is not actionable, and an ancestor View has co-opted its description.";
            } else {
                createNodeInfoFromView.recycle();
                return "View is not actionable and has no description.";
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getFocusableReasons(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            boolean hasText = AccessibilityUtil.hasText(createNodeInfoFromView);
            boolean isCheckable = createNodeInfoFromView.isCheckable();
            boolean hasNonActionableSpeakingDescendants = AccessibilityUtil.hasNonActionableSpeakingDescendants(createNodeInfoFromView, view);
            if (AccessibilityUtil.isActionableForAccessibility(createNodeInfoFromView)) {
                if (createNodeInfoFromView.getChildCount() <= 0) {
                    return "View is actionable and has no children.";
                }
                if (hasText) {
                    createNodeInfoFromView.recycle();
                    return "View is actionable and has a description.";
                } else if (isCheckable) {
                    createNodeInfoFromView.recycle();
                    return "View is actionable and checkable.";
                } else if (hasNonActionableSpeakingDescendants) {
                    createNodeInfoFromView.recycle();
                    return "View is actionable and has non-actionable descendants with descriptions.";
                }
            }
            if (AccessibilityUtil.isTopLevelScrollItem(createNodeInfoFromView, view)) {
                if (hasText) {
                    createNodeInfoFromView.recycle();
                    return "View is a direct child of a scrollable container and has a description.";
                } else if (isCheckable) {
                    createNodeInfoFromView.recycle();
                    return "View is a direct child of a scrollable container and is checkable.";
                } else if (hasNonActionableSpeakingDescendants) {
                    createNodeInfoFromView.recycle();
                    return "View is a direct child of a scrollable container and has non-actionable descendants with descriptions.";
                }
            }
            if (hasText) {
                createNodeInfoFromView.recycle();
                return "View has a description and is not actionable, but has no actionable ancestor.";
            }
            createNodeInfoFromView.recycle();
            return null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static String getActions(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            StringBuilder sb = new StringBuilder();
            for (AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat : createNodeInfoFromView.getActionList()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                int id = accessibilityActionCompat.getId();
                if (id == 1) {
                    sb.append("focus");
                } else if (id != 2) {
                    switch (id) {
                        case 4:
                            sb.append("select");
                            continue;
                        case 8:
                            sb.append("clear-selection");
                            continue;
                        case 16:
                            sb.append(QPLogEventBuilder.EVENT_ACTION);
                            continue;
                        case 32:
                            sb.append("long-click");
                            continue;
                        case 64:
                            sb.append("accessibility-focus");
                            continue;
                        case 128:
                            sb.append("clear-accessibility-focus");
                            continue;
                        case 256:
                            sb.append("next-at-movement-granularity");
                            continue;
                        case 512:
                            sb.append("previous-at-movement-granularity");
                            continue;
                        case 1024:
                            sb.append("next-html-element");
                            continue;
                        case 2048:
                            sb.append("previous-html-element");
                            continue;
                        case 4096:
                            sb.append("scroll-forward");
                            continue;
                        case 8192:
                            sb.append("scroll-backward");
                            continue;
                        case 16384:
                            sb.append("copy");
                            continue;
                        case 32768:
                            sb.append("paste");
                            continue;
                        case 65536:
                            sb.append("cut");
                            continue;
                        case 131072:
                            sb.append("set-selection");
                            continue;
                        default:
                            CharSequence label = accessibilityActionCompat.getLabel();
                            if (label == null) {
                                sb.append("unknown");
                                break;
                            } else {
                                sb.append(label);
                                continue;
                            }
                    }
                } else {
                    sb.append("clear-focus");
                }
            }
            return sb.length() > 0 ? sb.toString() : null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    @Nullable
    public static CharSequence getDescription(View view) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = createNodeInfoFromView(view);
        try {
            CharSequence contentDescription = createNodeInfoFromView.getContentDescription();
            CharSequence text = createNodeInfoFromView.getText();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = view instanceof EditText;
            if (!(TextUtils.isEmpty(contentDescription) || (z2 && z))) {
                return contentDescription;
            }
            if (z) {
                createNodeInfoFromView.recycle();
                return text;
            }
            String str = null;
            if (view instanceof ViewGroup) {
                StringBuilder sb = new StringBuilder();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                    CharSequence description = (!AccessibilityUtil.isSpeakingNode(obtain, childAt) || AccessibilityUtil.isAccessibilityFocusable(obtain, childAt)) ? null : getDescription(childAt);
                    if (!TextUtils.isEmpty(description)) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(description);
                    }
                    obtain.recycle();
                }
                if (sb.length() > 0) {
                    str = sb.toString();
                }
                createNodeInfoFromView.recycle();
                return str;
            }
            createNodeInfoFromView.recycle();
            return null;
        } finally {
            createNodeInfoFromView.recycle();
        }
    }
}
