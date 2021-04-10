package com.facebook.secure.trustedapp.checker;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.Reporter;
import javax.annotation.Nullable;

class OrChecker extends CheckerBase {
    private Checker mLeftChecker;
    private Checker mRightChecker;

    public OrChecker(Checker checker, Checker checker2) {
        this.mLeftChecker = checker;
        this.mRightChecker = checker2;
    }

    @Override // com.facebook.secure.trustedapp.checker.Checker
    public boolean shouldAllowIntent(Context context, Object obj, Intent intent, @Nullable Reporter reporter) {
        return this.mLeftChecker.shouldAllowIntent(context, obj, intent, reporter) || this.mRightChecker.shouldAllowIntent(context, obj, intent, reporter);
    }
}
