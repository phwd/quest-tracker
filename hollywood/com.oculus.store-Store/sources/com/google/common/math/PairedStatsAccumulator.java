package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;

@Beta
@GwtIncompatible
public final class PairedStatsAccumulator {
    private double sumOfProductsOfDeltas = 0.0d;
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();

    public void add(double x, double y) {
        this.xStats.add(x);
        if (!Doubles.isFinite(x) || !Doubles.isFinite(y)) {
            this.sumOfProductsOfDeltas = Double.NaN;
        } else if (this.xStats.count() > 1) {
            this.sumOfProductsOfDeltas += (x - this.xStats.mean()) * (y - this.yStats.mean());
        }
        this.yStats.add(y);
    }

    public void addAll(PairedStats values) {
        if (values.count() != 0) {
            this.xStats.addAll(values.xStats());
            if (this.yStats.count() == 0) {
                this.sumOfProductsOfDeltas = values.sumOfProductsOfDeltas();
            } else {
                this.sumOfProductsOfDeltas += values.sumOfProductsOfDeltas() + ((values.xStats().mean() - this.xStats.mean()) * (values.yStats().mean() - this.yStats.mean()) * ((double) values.count()));
            }
            this.yStats.addAll(values.yStats());
        }
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public long count() {
        return this.xStats.count();
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / ((double) count());
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / ((double) (count() - 1));
    }

    public final double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        double ySumOfSquaresOfDeltas = this.yStats.sumOfSquaresOfDeltas();
        Preconditions.checkState(xSumOfSquaresOfDeltas > 0.0d);
        Preconditions.checkState(ySumOfSquaresOfDeltas > 0.0d);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(xSumOfSquaresOfDeltas * ySumOfSquaresOfDeltas)));
    }

    public final LinearTransformation leastSquaresFit() {
        boolean z = true;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double xSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        if (xSumOfSquaresOfDeltas <= 0.0d) {
            if (this.yStats.sumOfSquaresOfDeltas() <= 0.0d) {
                z = false;
            }
            Preconditions.checkState(z);
            return LinearTransformation.vertical(this.xStats.mean());
        } else if (this.yStats.sumOfSquaresOfDeltas() > 0.0d) {
            return LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / xSumOfSquaresOfDeltas);
        } else {
            return LinearTransformation.horizontal(this.yStats.mean());
        }
    }

    private double ensurePositive(double value) {
        if (value > 0.0d) {
            return value;
        }
        return Double.MIN_VALUE;
    }

    private static double ensureInUnitRange(double value) {
        if (value >= 1.0d) {
            return 1.0d;
        }
        if (value <= -1.0d) {
            return -1.0d;
        }
        return value;
    }
}
