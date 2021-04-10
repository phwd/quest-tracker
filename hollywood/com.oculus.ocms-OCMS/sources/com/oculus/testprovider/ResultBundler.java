package com.oculus.testprovider;

import android.os.Bundle;
import com.oculus.horizon.logging.LoggingKeys;

public class ResultBundler {
    private static Bundle makeBundle(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("method", str);
        return bundle;
    }

    private static Bundle makeResultBundle(String str, boolean z) {
        Bundle makeBundle = makeBundle(str);
        makeBundle.putBoolean("result", z);
        return makeBundle;
    }

    private static Bundle makeResultBundle(String str, int i) {
        Bundle makeBundle = makeBundle(str);
        makeBundle.putInt("result", i);
        return makeBundle;
    }

    private static Bundle makeResultBundle(String str, String str2) {
        Bundle makeBundle = makeBundle(str);
        makeBundle.putString("result", str2);
        return makeBundle;
    }

    private static Bundle makeResultBundle(String str, Exception exc) {
        Bundle makeBundle = makeBundle(str);
        makeBundle.putString("error", exc.getMessage());
        return makeBundle;
    }

    public static Bundle makeErrorResult(String str, Exception exc) {
        return makeResultBundle(str, exc);
    }

    public static Bundle makeVoidResult(String str, TestResult<Void> testResult) {
        if (testResult.isError()) {
            return makeResultBundle(str, testResult.getError());
        }
        return makeResultBundle(str, LoggingKeys.SUCCESS);
    }

    public static Bundle makeStringResult(String str, TestResult<String> testResult) {
        if (testResult.isError()) {
            return makeResultBundle(str, testResult.getError());
        }
        if (testResult.getResult() == null) {
            return makeResultBundle(str, LoggingKeys.SUCCESS);
        }
        return makeResultBundle(str, testResult.getResult());
    }

    public static Bundle makeBooleanResult(String str, TestResult<Boolean> testResult) {
        if (testResult.isError()) {
            return makeResultBundle(str, testResult.getError());
        }
        if (testResult.getResult() == null) {
            return makeResultBundle(str, LoggingKeys.SUCCESS);
        }
        return makeResultBundle(str, testResult.getResult().booleanValue());
    }

    public static Bundle makeIntegerResult(String str, TestResult<Integer> testResult) {
        if (testResult.isError()) {
            return makeResultBundle(str, testResult.getError());
        }
        if (testResult.getResult() == null) {
            return makeResultBundle(str, LoggingKeys.SUCCESS);
        }
        return makeResultBundle(str, testResult.getResult().intValue());
    }
}
