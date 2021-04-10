package oculus.internal.ui;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.android.internal.app.ResolverActivity;
import java.util.List;

public class VrResolverActivity extends ResolverActivity {
    private final VrUiWrapper mVrLifecycle = new VrUiWrapper();

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: oculus.internal.ui.VrResolverActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        VrResolverActivity.super.onCreate(savedInstanceState);
        this.mVrLifecycle.onCreate(this, getWindow());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: oculus.internal.ui.VrResolverActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState, Intent intent, CharSequence title, Intent[] initialIntents, List<ResolveInfo> rList, boolean supportsAlwaysUseOption) {
        VrResolverActivity.super.onCreate(savedInstanceState, intent, title, initialIntents, rList, supportsAlwaysUseOption);
        this.mVrLifecycle.onCreate(this, getWindow());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mVrLifecycle.onPause();
        VrResolverActivity.super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        VrResolverActivity.super.onResume();
        this.mVrLifecycle.onResume();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        VrResolverActivity.super.onWindowFocusChanged(hasFocus);
        this.mVrLifecycle.onWindowFocusChanged(hasFocus);
    }

    public void showTargetDetails(ResolveInfo ri) {
    }
}
