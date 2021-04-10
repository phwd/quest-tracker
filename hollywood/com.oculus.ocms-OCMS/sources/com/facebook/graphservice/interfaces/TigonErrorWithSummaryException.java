package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.tigon.TigonErrorException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonErrorWithSummaryException extends TigonErrorException {
    @Nullable
    public final Summary summary;

    public TigonErrorWithSummaryException(TigonErrorException tigonErrorException, @Nullable Summary summary2) {
        super(tigonErrorException.tigonError);
        this.summary = summary2;
    }
}
