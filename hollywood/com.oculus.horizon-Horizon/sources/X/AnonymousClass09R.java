package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/* renamed from: X.09R  reason: invalid class name */
public final class AnonymousClass09R implements LayoutInflater.Factory2 {
    public final AbstractC003209a A00;

    public AnonymousClass09R(AbstractC003209a r1) {
        this.A00 = r1;
    }

    @Nullable
    public final View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        boolean z;
        int i;
        String obj;
        AbstractC003209a r3;
        if (AnonymousClass09O.class.getName().equals(str)) {
            return new AnonymousClass09O(context, attributeSet, this.A00);
        }
        Fragment fragment = null;
        if ("fragment".equals(str)) {
            String attributeValue = attributeSet.getAttributeValue(null, "class");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AnonymousClass25l.A00);
            if (attributeValue == null) {
                attributeValue = obtainStyledAttributes.getString(0);
            }
            int resourceId = obtainStyledAttributes.getResourceId(1, -1);
            String string = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            if (attributeValue != null) {
                ClassLoader classLoader = context.getClassLoader();
                try {
                    C000602o<String, Class<?>> r7 = AnonymousClass09Q.A00;
                    Class<?> cls = r7.get(attributeValue);
                    if (cls == null) {
                        cls = Class.forName(attributeValue, false, classLoader);
                        r7.put(attributeValue, cls);
                    }
                    z = Fragment.class.isAssignableFrom(cls);
                } catch (ClassNotFoundException unused) {
                    z = false;
                }
                if (z) {
                    if (view != null) {
                        i = view.getId();
                    } else {
                        i = 0;
                    }
                    if (i == -1 && resourceId == -1 && string == null) {
                        obj = AnonymousClass006.A07(attributeSet.getPositionDescription(), ": Must specify unique android:id, android:tag, or have a parent with an id for ", attributeValue);
                    } else {
                        if (resourceId != -1) {
                            fragment = this.A00.A0H(resourceId);
                        }
                        if (fragment == null && string != null) {
                            fragment = this.A00.A0J(string);
                        }
                        if (fragment == null && i != -1) {
                            fragment = this.A00.A0H(i);
                        }
                        if (fragment == null) {
                            AbstractC003209a r9 = this.A00;
                            r3 = r9;
                            fragment = r9.A0L().A01(context.getClassLoader(), attributeValue);
                            fragment.mFromLayout = true;
                            int i2 = i;
                            if (resourceId != 0) {
                                i2 = resourceId;
                            }
                            fragment.mFragmentId = i2;
                            fragment.mContainerId = i;
                            fragment.mTag = string;
                            fragment.mInLayout = true;
                            fragment.mFragmentManager = r9;
                            AnonymousClass0s9<?> r0 = r9.A05;
                            fragment.mHost = r0;
                            fragment.onInflate(r0.A01, attributeSet, fragment.mSavedFragmentState);
                            r9.A0V(fragment);
                            r9.A0e(fragment, r9.A00);
                        } else if (!fragment.mInLayout) {
                            fragment.mInLayout = true;
                            r3 = this.A00;
                            AnonymousClass0s9<?> r02 = r3.A05;
                            fragment.mHost = r02;
                            fragment.onInflate(r02.A01, attributeSet, fragment.mSavedFragmentState);
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(attributeSet.getPositionDescription());
                            sb.append(": Duplicate id 0x");
                            sb.append(Integer.toHexString(resourceId));
                            sb.append(", tag ");
                            sb.append(string);
                            sb.append(", or parent id 0x");
                            sb.append(Integer.toHexString(i));
                            sb.append(" with another fragment for ");
                            sb.append(attributeValue);
                            obj = sb.toString();
                        }
                        int i3 = r3.A00;
                        if (i3 >= 1 || !fragment.mFromLayout) {
                            r3.A0e(fragment, i3);
                        } else {
                            r3.A0e(fragment, 1);
                        }
                        View view2 = fragment.mView;
                        if (view2 != null) {
                            if (resourceId != 0) {
                                view2.setId(resourceId);
                            }
                            if (fragment.mView.getTag() == null) {
                                fragment.mView.setTag(string);
                            }
                            return fragment.mView;
                        }
                        throw new IllegalStateException(AnonymousClass006.A07("Fragment ", attributeValue, " did not create a view."));
                    }
                    throw new IllegalArgumentException(obj);
                }
            }
        }
        return null;
    }

    @Nullable
    public final View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
