package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.adobe.xmp.options.PropertyOptions;

/* access modifiers changed from: package-private */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new Parcelable.Creator<FragmentState>() {
        /* class androidx.fragment.app.FragmentState.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FragmentState createFromParcel(Parcel in) {
            return new FragmentState(in);
        }

        @Override // android.os.Parcelable.Creator
        public FragmentState[] newArray(int size) {
            return new FragmentState[size];
        }
    };
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    FragmentState(Fragment frag) {
        this.mClassName = frag.getClass().getName();
        this.mWho = frag.mWho;
        this.mFromLayout = frag.mFromLayout;
        this.mFragmentId = frag.mFragmentId;
        this.mContainerId = frag.mContainerId;
        this.mTag = frag.mTag;
        this.mRetainInstance = frag.mRetainInstance;
        this.mRemoving = frag.mRemoving;
        this.mDetached = frag.mDetached;
        this.mArguments = frag.mArguments;
        this.mHidden = frag.mHidden;
        this.mMaxLifecycleState = frag.mMaxState.ordinal();
    }

    FragmentState(Parcel in) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        this.mClassName = in.readString();
        this.mWho = in.readString();
        this.mFromLayout = in.readInt() != 0;
        this.mFragmentId = in.readInt();
        this.mContainerId = in.readInt();
        this.mTag = in.readString();
        if (in.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mRetainInstance = z;
        if (in.readInt() != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mRemoving = z2;
        if (in.readInt() != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mDetached = z3;
        this.mArguments = in.readBundle();
        this.mHidden = in.readInt() == 0 ? false : z4;
        this.mSavedFragmentState = in.readBundle();
        this.mMaxLifecycleState = in.readInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) PropertyOptions.HAS_TYPE);
        sb.append("FragmentState{");
        sb.append(this.mClassName);
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")}:");
        if (this.mFromLayout) {
            sb.append(" fromLayout");
        }
        if (this.mContainerId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mContainerId));
        }
        if (this.mTag != null && !this.mTag.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        if (this.mRetainInstance) {
            sb.append(" retainInstance");
        }
        if (this.mRemoving) {
            sb.append(" removing");
        }
        if (this.mDetached) {
            sb.append(" detached");
        }
        if (this.mHidden) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        dest.writeString(this.mClassName);
        dest.writeString(this.mWho);
        dest.writeInt(this.mFromLayout ? 1 : 0);
        dest.writeInt(this.mFragmentId);
        dest.writeInt(this.mContainerId);
        dest.writeString(this.mTag);
        if (this.mRetainInstance) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (this.mRemoving) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (this.mDetached) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        dest.writeInt(i3);
        dest.writeBundle(this.mArguments);
        if (!this.mHidden) {
            i4 = 0;
        }
        dest.writeInt(i4);
        dest.writeBundle(this.mSavedFragmentState);
        dest.writeInt(this.mMaxLifecycleState);
    }
}
