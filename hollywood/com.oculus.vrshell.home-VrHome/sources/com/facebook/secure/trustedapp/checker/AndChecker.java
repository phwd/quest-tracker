package com.facebook.secure.trustedapp.checker;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

public class AndChecker extends CheckerBase {
    private Checker mLeftChecker;
    private Checker mRightChecker;

    AndChecker(Checker left, Checker right) {
        this.mLeftChecker = left;
        this.mRightChecker = right;
    }

    @Override // com.facebook.secure.trustedapp.checker.Checker
    public boolean shouldAllowIntent(Context context, Object endpoint, Intent intent, @Nullable Reporter reporter) {
        return this.mLeftChecker.shouldAllowIntent(context, endpoint, intent, reporter) && this.mRightChecker.shouldAllowIntent(context, endpoint, intent, reporter);
    }
}
