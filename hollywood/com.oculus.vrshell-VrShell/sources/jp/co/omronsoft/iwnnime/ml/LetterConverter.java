package jp.co.omronsoft.iwnnime.ml;

import android.content.SharedPreferences;

public interface LetterConverter {
    boolean convert(ComposingText composingText);

    void setPreferences(SharedPreferences sharedPreferences);
}
