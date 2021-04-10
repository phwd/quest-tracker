package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    private final FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment.SavedState> mSavedState = new ArrayList<>();

    public abstract Fragment getItem(int i);

    public FragmentStatePagerAdapter(FragmentManager fm) {
        this.mFragmentManager = fm;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void startUpdate(ViewGroup container) {
        if (container.getId() == -1) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment.SavedState fss;
        Fragment f;
        if (this.mFragments.size() > position && (f = this.mFragments.get(position)) != null) {
            return f;
        }
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment fragment = getItem(position);
        if (this.mSavedState.size() > position && (fss = this.mSavedState.get(position)) != null) {
            fragment.setInitialSavedState(fss);
        }
        while (this.mFragments.size() <= position) {
            this.mFragments.add(null);
        }
        fragment.setMenuVisibility(DEBUG);
        fragment.setUserVisibleHint(DEBUG);
        this.mFragments.set(position, fragment);
        this.mCurTransaction.add(container.getId(), fragment);
        return fragment;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        while (this.mSavedState.size() <= position) {
            this.mSavedState.add(null);
        }
        this.mSavedState.set(position, fragment.isAdded() ? this.mFragmentManager.saveFragmentInstanceState(fragment) : null);
        this.mFragments.set(position, null);
        this.mCurTransaction.remove(fragment);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(DEBUG);
                this.mCurrentPrimaryItem.setUserVisibleHint(DEBUG);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public void finishUpdate(ViewGroup container) {
        FragmentTransaction fragmentTransaction = this.mCurTransaction;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        if (((Fragment) object).getView() == view) {
            return true;
        }
        return DEBUG;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:17:0x0058 */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: android.support.v4.app.FragmentManager */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.os.Parcelable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // android.support.v4.view.PagerAdapter
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Parcelable saveState() {
        /*
            r5 = this;
            r0 = 0
            java.util.ArrayList<android.support.v4.app.Fragment$SavedState> r1 = r5.mSavedState
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0021
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r0 = r1
            java.util.ArrayList<android.support.v4.app.Fragment$SavedState> r1 = r5.mSavedState
            int r1 = r1.size()
            android.support.v4.app.Fragment$SavedState[] r1 = new android.support.v4.app.Fragment.SavedState[r1]
            java.util.ArrayList<android.support.v4.app.Fragment$SavedState> r2 = r5.mSavedState
            r2.toArray(r1)
            java.lang.String r2 = "states"
            r0.putParcelableArray(r2, r1)
        L_0x0021:
            r1 = 0
        L_0x0022:
            java.util.ArrayList<android.support.v4.app.Fragment> r2 = r5.mFragments
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x005b
            java.util.ArrayList<android.support.v4.app.Fragment> r2 = r5.mFragments
            java.lang.Object r2 = r2.get(r1)
            android.support.v4.app.Fragment r2 = (android.support.v4.app.Fragment) r2
            if (r2 == 0) goto L_0x0058
            boolean r3 = r2.isAdded()
            if (r3 == 0) goto L_0x0058
            if (r0 != 0) goto L_0x0042
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            r0 = r3
        L_0x0042:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "f"
            r3.append(r4)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            android.support.v4.app.FragmentManager r4 = r5.mFragmentManager
            r4.putFragment(r0, r3, r2)
        L_0x0058:
            int r1 = r1 + 1
            goto L_0x0022
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentStatePagerAdapter.saveState():android.os.Parcelable");
    }

    @Override // android.support.v4.view.PagerAdapter
    public void restoreState(Parcelable state, ClassLoader loader) {
        if (state != null) {
            Bundle bundle = (Bundle) state;
            bundle.setClassLoader(loader);
            Parcelable[] fss = bundle.getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if (fss != null) {
                for (Parcelable parcelable : fss) {
                    this.mSavedState.add((Fragment.SavedState) parcelable);
                }
            }
            for (String key : bundle.keySet()) {
                if (key.startsWith("f")) {
                    int index = Integer.parseInt(key.substring(1));
                    Fragment f = this.mFragmentManager.getFragment(bundle, key);
                    if (f != null) {
                        while (this.mFragments.size() <= index) {
                            this.mFragments.add(null);
                        }
                        f.setMenuVisibility(DEBUG);
                        this.mFragments.set(index, f);
                    } else {
                        Log.w(TAG, "Bad fragment at key " + key);
                    }
                }
            }
        }
    }
}
