package com.oculus.config.interfaces;

import X.DB;

public interface Configuration {
    DB<Void> fetchAsync();
}
