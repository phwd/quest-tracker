package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.R;

/* access modifiers changed from: package-private */
public class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    private final FragmentManager mFragmentManager;

    FragmentLayoutInflaterFactory(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Fragment fragment;
        int i;
        if (FragmentContainerView.class.getName().equals(name)) {
            return new FragmentContainerView(context, attrs, this.mFragmentManager);
        }
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue(null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Fragment);
        if (fname == null) {
            fname = a.getString(R.styleable.Fragment_android_name);
        }
        int id = a.getResourceId(R.styleable.Fragment_android_id, -1);
        String tag = a.getString(R.styleable.Fragment_android_tag);
        a.recycle();
        if (fname == null || !FragmentFactory.isFragmentClass(context.getClassLoader(), fname)) {
            return null;
        }
        int containerId = parent != null ? parent.getId() : 0;
        if (containerId == -1 && id == -1 && tag == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + fname);
        }
        if (id != -1) {
            fragment = this.mFragmentManager.findFragmentById(id);
        } else {
            fragment = null;
        }
        if (fragment == null && tag != null) {
            fragment = this.mFragmentManager.findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = this.mFragmentManager.findFragmentById(containerId);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(id) + " fname=" + fname + " existing=" + fragment);
        }
        if (fragment == null) {
            fragment = this.mFragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), fname);
            fragment.mFromLayout = true;
            if (id != 0) {
                i = id;
            } else {
                i = containerId;
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this.mFragmentManager;
            fragment.mHost = this.mFragmentManager.mHost;
            fragment.onInflate(this.mFragmentManager.mHost.getContext(), attrs, fragment.mSavedFragmentState);
            this.mFragmentManager.addFragment(fragment);
            this.mFragmentManager.moveToState(fragment);
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(id) + ", tag " + tag + ", or parent id 0x" + Integer.toHexString(containerId) + " with another fragment for " + fname);
        } else {
            fragment.mInLayout = true;
            fragment.mHost = this.mFragmentManager.mHost;
            fragment.onInflate(this.mFragmentManager.mHost.getContext(), attrs, fragment.mSavedFragmentState);
        }
        if (this.mFragmentManager.mCurState >= 1 || !fragment.mFromLayout) {
            this.mFragmentManager.moveToState(fragment);
        } else {
            this.mFragmentManager.moveToState(fragment, 1);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + fname + " did not create a view.");
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }
}
