package com.oculus.video.extractor.mkv;

import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface EbmlReaderOutput {
    void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException;

    void endMasterElement(int i) throws ParserException;

    void floatElement(int i, double d) throws ParserException;

    int getElementType(int i);

    void integerElement(int i, long j) throws ParserException;

    boolean isLevel1Element(int i);

    void startMasterElement(int i, long j, long j2) throws ParserException;

    void stringElement(int i, String str) throws ParserException;
}
