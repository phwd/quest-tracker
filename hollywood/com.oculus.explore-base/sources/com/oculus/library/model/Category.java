package com.oculus.library.model;

public enum Category {
    UNKNOWN(-1),
    APPS(0),
    CONCEPTS(1),
    EARLY_ACCESS(2),
    ENTERTAINMENT(3),
    ENVIRONMENTS(4),
    GALLERY(5),
    GAMES(6),
    INTERNAL(7),
    SYSTEM(8);
    
    public final int value;

    private Category(int value2) {
        this.value = value2;
    }

    public static Category fromString(String value2) {
        Category[] values = values();
        for (Category category : values) {
            if (category.name().equals(value2)) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
