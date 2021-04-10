package com.oculus.android.exoplayer2.extractor.mkv;

import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.Stack;

/* access modifiers changed from: package-private */
public final class DefaultEbmlReader implements EbmlReader {
    private static final int ELEMENT_STATE_READ_CONTENT = 2;
    private static final int ELEMENT_STATE_READ_CONTENT_SIZE = 1;
    private static final int ELEMENT_STATE_READ_ID = 0;
    private static final int MAX_ID_BYTES = 4;
    private static final int MAX_INTEGER_ELEMENT_SIZE_BYTES = 8;
    private static final int MAX_LENGTH_BYTES = 8;
    private static final int VALID_FLOAT32_ELEMENT_SIZE_BYTES = 4;
    private static final int VALID_FLOAT64_ELEMENT_SIZE_BYTES = 8;
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private final Stack<MasterElement> masterElementsStack = new Stack<>();
    private EbmlReaderOutput output;
    private final byte[] scratch = new byte[8];
    private final VarintReader varintReader = new VarintReader();

    DefaultEbmlReader() {
    }

    @Override // com.oculus.android.exoplayer2.extractor.mkv.EbmlReader
    public void init(EbmlReaderOutput ebmlReaderOutput) {
        this.output = ebmlReaderOutput;
    }

    @Override // com.oculus.android.exoplayer2.extractor.mkv.EbmlReader
    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }

    @Override // com.oculus.android.exoplayer2.extractor.mkv.EbmlReader
    public boolean read(ExtractorInput extractorInput) throws IOException, InterruptedException {
        Assertions.checkState(this.output != null);
        while (true) {
            if (this.masterElementsStack.isEmpty() || extractorInput.getPosition() < this.masterElementsStack.peek().elementEndPosition) {
                if (this.elementState == 0) {
                    long readUnsignedVarint = this.varintReader.readUnsignedVarint(extractorInput, true, false, 4);
                    if (readUnsignedVarint == -2) {
                        readUnsignedVarint = maybeResyncToNextLevel1Element(extractorInput);
                    }
                    if (readUnsignedVarint == -1) {
                        return false;
                    }
                    this.elementId = (int) readUnsignedVarint;
                    this.elementState = 1;
                }
                if (this.elementState == 1) {
                    this.elementContentSize = this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                    this.elementState = 2;
                }
                int elementType = this.output.getElementType(this.elementId);
                if (elementType == 0) {
                    extractorInput.skipFully((int) this.elementContentSize);
                    this.elementState = 0;
                } else if (elementType == 1) {
                    long position = extractorInput.getPosition();
                    this.masterElementsStack.add(new MasterElement(this.elementId, this.elementContentSize + position));
                    this.output.startMasterElement(this.elementId, position, this.elementContentSize);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 2) {
                    long j = this.elementContentSize;
                    if (j <= 8) {
                        this.output.integerElement(this.elementId, readInteger(extractorInput, (int) j));
                        this.elementState = 0;
                        return true;
                    }
                    throw new ParserException("Invalid integer size: " + this.elementContentSize);
                } else if (elementType == 3) {
                    long j2 = this.elementContentSize;
                    if (j2 <= 2147483647L) {
                        this.output.stringElement(this.elementId, readString(extractorInput, (int) j2));
                        this.elementState = 0;
                        return true;
                    }
                    throw new ParserException("String element size: " + this.elementContentSize);
                } else if (elementType == 4) {
                    this.output.binaryElement(this.elementId, (int) this.elementContentSize, extractorInput);
                    this.elementState = 0;
                    return true;
                } else if (elementType == 5) {
                    long j3 = this.elementContentSize;
                    if (j3 == 4 || j3 == 8) {
                        this.output.floatElement(this.elementId, readFloat(extractorInput, (int) this.elementContentSize));
                        this.elementState = 0;
                        return true;
                    }
                    throw new ParserException("Invalid float size: " + this.elementContentSize);
                } else {
                    throw new ParserException("Invalid element type " + elementType);
                }
            } else {
                this.output.endMasterElement(this.masterElementsStack.pop().elementId);
                return true;
            }
        }
    }

    private long maybeResyncToNextLevel1Element(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.resetPeekPosition();
        while (true) {
            extractorInput.peekFully(this.scratch, 0, 4);
            int parseUnsignedVarintLength = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if (parseUnsignedVarintLength != -1 && parseUnsignedVarintLength <= 4) {
                int assembleVarint = (int) VarintReader.assembleVarint(this.scratch, parseUnsignedVarintLength, false);
                if (this.output.isLevel1Element(assembleVarint)) {
                    extractorInput.skipFully(parseUnsignedVarintLength);
                    return (long) assembleVarint;
                }
            }
            extractorInput.skipFully(1);
        }
    }

    private long readInteger(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        extractorInput.readFully(this.scratch, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.scratch[i2] & 255));
        }
        return j;
    }

    private double readFloat(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        long readInteger = readInteger(extractorInput, i);
        if (i == 4) {
            return (double) Float.intBitsToFloat((int) readInteger);
        }
        return Double.longBitsToDouble(readInteger);
    }

    private String readString(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        extractorInput.readFully(bArr, 0, i);
        while (i > 0 && bArr[i - 1] == 0) {
            i--;
        }
        return new String(bArr, 0, i);
    }

    private static final class MasterElement {
        private final long elementEndPosition;
        private final int elementId;

        private MasterElement(int i, long j) {
            this.elementId = i;
            this.elementEndPosition = j;
        }
    }
}
