package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

@Beta
@GwtIncompatible
public abstract class LinearTransformation {
    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d);

    public static LinearTransformationBuilder mapping(double x1, double y1) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
        return new LinearTransformationBuilder(x1, y1);
    }

    public static final class LinearTransformationBuilder {
        private final double x1;
        private final double y1;

        private LinearTransformationBuilder(double x12, double y12) {
            this.x1 = x12;
            this.y1 = y12;
        }

        public LinearTransformation and(double x2, double y2) {
            boolean z = true;
            Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
            if (x2 != this.x1) {
                return withSlope((y2 - this.y1) / (x2 - this.x1));
            }
            if (y2 == this.y1) {
                z = false;
            }
            Preconditions.checkArgument(z);
            return new VerticalLinearTransformation(this.x1);
        }

        public LinearTransformation withSlope(double slope) {
            Preconditions.checkArgument(!Double.isNaN(slope));
            if (DoubleUtils.isFinite(slope)) {
                return new RegularLinearTransformation(slope, this.y1 - (this.x1 * slope));
            }
            return new VerticalLinearTransformation(this.x1);
        }
    }

    public static LinearTransformation vertical(double x) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x));
        return new VerticalLinearTransformation(x);
    }

    public static LinearTransformation horizontal(double y) {
        Preconditions.checkArgument(DoubleUtils.isFinite(y));
        return new RegularLinearTransformation(0.0d, y);
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final class RegularLinearTransformation extends LinearTransformation {
        @LazyInit
        LinearTransformation inverse;
        final double slope;
        final double yIntercept;

        RegularLinearTransformation(double slope2, double yIntercept2) {
            this.slope = slope2;
            this.yIntercept = yIntercept2;
            this.inverse = null;
        }

        RegularLinearTransformation(double slope2, double yIntercept2, LinearTransformation inverse2) {
            this.slope = slope2;
            this.yIntercept = yIntercept2;
            this.inverse = inverse2;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.slope == 0.0d;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.slope;
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return (this.slope * x) + this.yIntercept;
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation result2 = createInverse();
            this.inverse = result2;
            return result2;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.slope), Double.valueOf(this.yIntercept));
        }

        private LinearTransformation createInverse() {
            if (this.slope != 0.0d) {
                return new RegularLinearTransformation(1.0d / this.slope, (-1.0d * this.yIntercept) / this.slope, this);
            }
            return new VerticalLinearTransformation(this.yIntercept, this);
        }
    }

    /* access modifiers changed from: private */
    public static final class VerticalLinearTransformation extends LinearTransformation {
        @LazyInit
        LinearTransformation inverse;
        final double x;

        VerticalLinearTransformation(double x2) {
            this.x = x2;
            this.inverse = null;
        }

        VerticalLinearTransformation(double x2, LinearTransformation inverse2) {
            this.x = x2;
            this.inverse = inverse2;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x2) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation result2 = createInverse();
            this.inverse = result2;
            return result2;
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.x));
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.x, this);
        }
    }

    private static final class NaNLinearTransformation extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return Double.NaN;
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        public String toString() {
            return "NaN";
        }
    }
}
