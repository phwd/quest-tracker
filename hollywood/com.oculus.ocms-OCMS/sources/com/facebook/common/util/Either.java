package com.facebook.common.util;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.parcels.ParcelUtil;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

public class Either<L, R> implements Parcelable, Supplier<Object> {
    public static final Parcelable.Creator<Either> CREATOR = new Parcelable.Creator<Either>() {
        /* class com.facebook.common.util.Either.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public Either createFromParcel(Parcel parcel) {
            return new Either(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public Either[] newArray(int i) {
            return new Either[i];
        }
    };
    private final boolean isLeft;
    private final L left;
    private final R right;

    public int describeContents() {
        return 0;
    }

    private Either(L l, R r, boolean z) {
        this.left = l;
        this.right = r;
        this.isLeft = z;
    }

    private Either(Parcel parcel) {
        this(ParcelUtil.readNext(parcel), ParcelUtil.readNext(parcel), ParcelUtil.readBool(parcel));
    }

    public static <L, R> Either<L, R> left(L l) {
        return new Either<>(l, null, true);
    }

    public static <L, R> Either<L, R> right(R r) {
        return new Either<>(null, r, false);
    }

    public boolean isLeft() {
        return this.isLeft;
    }

    public boolean isRight() {
        return !this.isLeft;
    }

    public L getLeft() {
        Preconditions.checkState(isLeft());
        return this.left;
    }

    public R getRight() {
        Preconditions.checkState(isRight());
        return this.right;
    }

    @Override // com.google.common.base.Supplier
    public Object get() {
        return this.isLeft ? this.left : this.right;
    }

    public <T> T cast() {
        return (T) get();
    }

    public <X> X fold(Function<? super L, ? extends X> function, Function<? super R, ? extends X> function2) {
        return this.isLeft ? (X) function.apply(this.left) : (X) function2.apply(this.right);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.base.Function<? super R, ? extends L> */
    /* JADX WARN: Multi-variable type inference failed */
    public L toLeft(Function<? super R, ? extends L> function) {
        return (L) fold(Functions.identity(), function);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.base.Function<? super L, ? extends R> */
    /* JADX WARN: Multi-variable type inference failed */
    public R toRight(Function<? super L, ? extends R> function) {
        return (R) fold(function, Functions.identity());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.left);
        parcel.writeValue(this.right);
        ParcelUtil.writeBool(parcel, this.isLeft);
    }

    public String toString() {
        return StringFormatUtil.formatStrLocaleSafe("Either.%s(%s)", this.isLeft ? "left" : "right", String.valueOf(get()));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Either)) {
            return false;
        }
        return Objects.equal(get(), ((Either) obj).get());
    }

    public int hashCode() {
        return Objects.hashCode(get());
    }
}
