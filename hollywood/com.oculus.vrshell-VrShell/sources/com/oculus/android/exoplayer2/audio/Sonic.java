package com.oculus.android.exoplayer2.audio;

import com.oculus.android.exoplayer2.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputBufferSize;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequired = (this.maxPeriod * 2);
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private final int numChannels;
    private int numInputSamples;
    private int numOutputSamples;
    private int numPitchSamples;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputBufferSize;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchBufferSize;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopy;
    private final float speed;

    public Sonic(int i, int i2, float f, float f2, int i3) {
        this.inputSampleRateHz = i;
        this.numChannels = i2;
        this.minPeriod = i / MAXIMUM_PITCH;
        this.maxPeriod = i / MINIMUM_PITCH;
        int i4 = this.maxRequired;
        this.downSampleBuffer = new short[i4];
        this.inputBufferSize = i4;
        this.inputBuffer = new short[(i4 * i2)];
        this.outputBufferSize = i4;
        this.outputBuffer = new short[(i4 * i2)];
        this.pitchBufferSize = i4;
        this.pitchBuffer = new short[(i4 * i2)];
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.prevPeriod = 0;
        this.speed = f;
        this.pitch = f2;
        this.rate = ((float) i) / ((float) i3);
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i = this.numChannels;
        int i2 = remaining / i;
        enlargeInputBufferIfNeeded(i2);
        shortBuffer.get(this.inputBuffer, this.numInputSamples * this.numChannels, ((i * i2) * 2) / 2);
        this.numInputSamples += i2;
        processStreamInput();
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.numChannels, this.numOutputSamples);
        shortBuffer.put(this.outputBuffer, 0, this.numChannels * min);
        this.numOutputSamples -= min;
        short[] sArr = this.outputBuffer;
        int i = this.numChannels;
        System.arraycopy(sArr, min * i, sArr, 0, this.numOutputSamples * i);
    }

    public void queueEndOfStream() {
        int i;
        int i2 = this.numInputSamples;
        float f = this.speed;
        float f2 = this.pitch;
        int i3 = this.numOutputSamples + ((int) ((((((float) i2) / (f / f2)) + ((float) this.numPitchSamples)) / (this.rate * f2)) + 0.5f));
        enlargeInputBufferIfNeeded((this.maxRequired * 2) + i2);
        int i4 = 0;
        while (true) {
            i = this.maxRequired;
            int i5 = this.numChannels;
            if (i4 >= i * 2 * i5) {
                break;
            }
            this.inputBuffer[(i5 * i2) + i4] = 0;
            i4++;
        }
        this.numInputSamples += i * 2;
        processStreamInput();
        if (this.numOutputSamples > i3) {
            this.numOutputSamples = i3;
        }
        this.numInputSamples = 0;
        this.remainingInputToCopy = 0;
        this.numPitchSamples = 0;
    }

    public int getSamplesAvailable() {
        return this.numOutputSamples;
    }

    private void enlargeOutputBufferIfNeeded(int i) {
        int i2 = this.numOutputSamples + i;
        int i3 = this.outputBufferSize;
        if (i2 > i3) {
            this.outputBufferSize = i3 + (i3 / 2) + i;
            this.outputBuffer = Arrays.copyOf(this.outputBuffer, this.outputBufferSize * this.numChannels);
        }
    }

    private void enlargeInputBufferIfNeeded(int i) {
        int i2 = this.numInputSamples + i;
        int i3 = this.inputBufferSize;
        if (i2 > i3) {
            this.inputBufferSize = i3 + (i3 / 2) + i;
            this.inputBuffer = Arrays.copyOf(this.inputBuffer, this.inputBufferSize * this.numChannels);
        }
    }

    private void removeProcessedInputSamples(int i) {
        int i2 = this.numInputSamples - i;
        short[] sArr = this.inputBuffer;
        int i3 = this.numChannels;
        System.arraycopy(sArr, i * i3, sArr, 0, i3 * i2);
        this.numInputSamples = i2;
    }

    private void copyToOutput(short[] sArr, int i, int i2) {
        enlargeOutputBufferIfNeeded(i2);
        int i3 = this.numChannels;
        System.arraycopy(sArr, i * i3, this.outputBuffer, this.numOutputSamples * i3, i3 * i2);
        this.numOutputSamples += i2;
    }

    private int copyInputToOutput(int i) {
        int min = Math.min(this.maxRequired, this.remainingInputToCopy);
        copyToOutput(this.inputBuffer, i, min);
        this.remainingInputToCopy -= min;
        return min;
    }

    private void downSampleInput(short[] sArr, int i, int i2) {
        int i3 = this.maxRequired / i2;
        int i4 = this.numChannels;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.downSampleBuffer[i7] = (short) (i8 / i5);
        }
    }

    private int findPitchPeriodInRange(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.numChannels;
        int i5 = 1;
        int i6 = 0;
        int i7 = 255;
        int i8 = 0;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                i9 += Math.abs(sArr[i4 + i10] - sArr[(i4 + i2) + i10]);
            }
            if (i9 * i8 < i5 * i2) {
                i8 = i2;
                i5 = i9;
            }
            if (i9 * i7 > i6 * i2) {
                i7 = i2;
                i6 = i9;
            }
            i2++;
        }
        this.minDiff = i5 / i8;
        this.maxDiff = i6 / i7;
        return i8;
    }

    private boolean previousPeriodBetter(int i, int i2, boolean z) {
        if (i == 0 || this.prevPeriod == 0) {
            return false;
        }
        if (z) {
            if (i2 <= i * 3 && i * 2 > this.prevMinDiff * 3) {
                return true;
            }
            return false;
        } else if (i <= this.prevMinDiff) {
            return false;
        } else {
            return true;
        }
    }

    private int findPitchPeriod(short[] sArr, int i, boolean z) {
        int i2;
        int i3 = this.inputSampleRateHz;
        int i4 = i3 > AMDF_FREQUENCY ? i3 / AMDF_FREQUENCY : 1;
        if (this.numChannels == 1 && i4 == 1) {
            i2 = findPitchPeriodInRange(sArr, i, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i, i4);
            int findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i4, this.maxPeriod / i4);
            if (i4 != 1) {
                int i5 = findPitchPeriodInRange * i4;
                int i6 = i4 * 4;
                int i7 = i5 - i6;
                int i8 = i5 + i6;
                int i9 = this.minPeriod;
                if (i7 >= i9) {
                    i9 = i7;
                }
                int i10 = this.maxPeriod;
                if (i8 > i10) {
                    i8 = i10;
                }
                if (this.numChannels == 1) {
                    i2 = findPitchPeriodInRange(sArr, i, i9, i8);
                } else {
                    downSampleInput(sArr, i, 1);
                    i2 = findPitchPeriodInRange(this.downSampleBuffer, 0, i9, i8);
                }
            } else {
                i2 = findPitchPeriodInRange;
            }
        }
        int i11 = previousPeriodBetter(this.minDiff, this.maxDiff, z) ? this.prevPeriod : i2;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = i2;
        return i11;
    }

    private void moveNewSamplesToPitchBuffer(int i) {
        int i2 = this.numOutputSamples - i;
        int i3 = this.numPitchSamples + i2;
        int i4 = this.pitchBufferSize;
        if (i3 > i4) {
            this.pitchBufferSize = i4 + (i4 / 2) + i2;
            this.pitchBuffer = Arrays.copyOf(this.pitchBuffer, this.pitchBufferSize * this.numChannels);
        }
        short[] sArr = this.outputBuffer;
        int i5 = this.numChannels;
        System.arraycopy(sArr, i * i5, this.pitchBuffer, this.numPitchSamples * i5, i5 * i2);
        this.numOutputSamples = i;
        this.numPitchSamples += i2;
    }

    private void removePitchSamples(int i) {
        if (i != 0) {
            short[] sArr = this.pitchBuffer;
            int i2 = this.numChannels;
            System.arraycopy(sArr, i * i2, sArr, 0, (this.numPitchSamples - i) * i2);
            this.numPitchSamples -= i;
        }
    }

    private short interpolate(short[] sArr, int i, int i2, int i3) {
        short s = sArr[i];
        short s2 = sArr[i + this.numChannels];
        int i4 = this.newRatePosition * i2;
        int i5 = this.oldRatePosition;
        int i6 = i5 * i3;
        int i7 = (i5 + 1) * i3;
        int i8 = i7 - i4;
        int i9 = i7 - i6;
        return (short) (((s * i8) + ((i9 - i8) * s2)) / i9);
    }

    private void adjustRate(float f, int i) {
        int i2;
        int i3;
        if (this.numOutputSamples != i) {
            int i4 = this.inputSampleRateHz;
            int i5 = (int) (((float) i4) / f);
            while (true) {
                if (i5 <= 16384 && i4 <= 16384) {
                    break;
                }
                i5 /= 2;
                i4 /= 2;
            }
            moveNewSamplesToPitchBuffer(i);
            int i6 = 0;
            while (true) {
                int i7 = this.numPitchSamples;
                boolean z = true;
                if (i6 < i7 - 1) {
                    while (true) {
                        i2 = this.oldRatePosition;
                        int i8 = (i2 + 1) * i5;
                        i3 = this.newRatePosition;
                        if (i8 <= i3 * i4) {
                            break;
                        }
                        enlargeOutputBufferIfNeeded(1);
                        int i9 = 0;
                        while (true) {
                            int i10 = this.numChannels;
                            if (i9 >= i10) {
                                break;
                            }
                            this.outputBuffer[(this.numOutputSamples * i10) + i9] = interpolate(this.pitchBuffer, (i10 * i6) + i9, i4, i5);
                            i9++;
                        }
                        this.newRatePosition++;
                        this.numOutputSamples++;
                    }
                    this.oldRatePosition = i2 + 1;
                    if (this.oldRatePosition == i4) {
                        this.oldRatePosition = 0;
                        if (i3 != i5) {
                            z = false;
                        }
                        Assertions.checkState(z);
                        this.newRatePosition = 0;
                    }
                    i6++;
                } else {
                    removePitchSamples(i7 - 1);
                    return;
                }
            }
        }
    }

    private int skipPitchPeriod(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f >= 2.0f) {
            i3 = (int) (((float) i2) / (f - 1.0f));
        } else {
            this.remainingInputToCopy = (int) ((((float) i2) * (2.0f - f)) / (f - 1.0f));
            i3 = i2;
        }
        enlargeOutputBufferIfNeeded(i3);
        overlapAdd(i3, this.numChannels, this.outputBuffer, this.numOutputSamples, sArr, i, sArr, i + i2);
        this.numOutputSamples += i3;
        return i3;
    }

    private int insertPitchPeriod(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f < 0.5f) {
            i3 = (int) ((((float) i2) * f) / (1.0f - f));
        } else {
            this.remainingInputToCopy = (int) ((((float) i2) * ((2.0f * f) - 1.0f)) / (1.0f - f));
            i3 = i2;
        }
        int i4 = i2 + i3;
        enlargeOutputBufferIfNeeded(i4);
        int i5 = this.numChannels;
        System.arraycopy(sArr, i * i5, this.outputBuffer, this.numOutputSamples * i5, i5 * i2);
        overlapAdd(i3, this.numChannels, this.outputBuffer, this.numOutputSamples + i2, sArr, i + i2, sArr, i);
        this.numOutputSamples += i4;
        return i3;
    }

    private void changeSpeed(float f) {
        int insertPitchPeriod;
        int i = this.numInputSamples;
        if (i >= this.maxRequired) {
            int i2 = 0;
            do {
                if (this.remainingInputToCopy > 0) {
                    insertPitchPeriod = copyInputToOutput(i2);
                } else {
                    int findPitchPeriod = findPitchPeriod(this.inputBuffer, i2, true);
                    if (((double) f) > 1.0d) {
                        insertPitchPeriod = findPitchPeriod + skipPitchPeriod(this.inputBuffer, i2, f, findPitchPeriod);
                    } else {
                        insertPitchPeriod = insertPitchPeriod(this.inputBuffer, i2, f, findPitchPeriod);
                    }
                }
                i2 += insertPitchPeriod;
            } while (this.maxRequired + i2 <= i);
            removeProcessedInputSamples(i2);
        }
    }

    private void processStreamInput() {
        int i = this.numOutputSamples;
        float f = this.speed;
        float f2 = this.pitch;
        float f3 = f / f2;
        float f4 = this.rate * f2;
        double d = (double) f3;
        if (d > 1.00001d || d < 0.99999d) {
            changeSpeed(f3);
        } else {
            copyToOutput(this.inputBuffer, 0, this.numInputSamples);
            this.numInputSamples = 0;
        }
        if (f4 != 1.0f) {
            adjustRate(f4, i);
        }
    }

    private static void overlapAdd(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i4 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i3 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i9] = (short) (((sArr2[i7] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i9 += i2;
                i7 += i2;
                i8 += i2;
            }
        }
    }
}
