package com.oculus.ocui.logging;

public interface OCSelectLogger<T> {
    void onLogClick();

    void onLogOptionSelected(T t, T t2);
}
