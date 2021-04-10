package com.oculus.config.interfaces;

import bolts.Task;

public interface Configuration {
    Task<Void> fetchAsync();
}
