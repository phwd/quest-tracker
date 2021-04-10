package com.oculus.fitnesstracker.database;

public class FitnessUserInfo {
    private String mCaloriesUnit;
    private String mDateOfBirth;
    private String mHeightUnit;
    private String mSex;
    private String mWeightUnit;

    public FitnessUserInfo(String str, String str2, String str3, String str4, String str5) {
        this.mSex = str;
        this.mDateOfBirth = str2;
        this.mHeightUnit = str3;
        this.mWeightUnit = str4;
        this.mCaloriesUnit = str5;
    }

    public String getSex() {
        return this.mSex;
    }

    public String getDateOfBirth() {
        return this.mDateOfBirth;
    }

    public String getHeightUnit() {
        return this.mHeightUnit;
    }

    public String getWeightUnit() {
        return this.mWeightUnit;
    }

    public String getCaloriesUnit() {
        return this.mCaloriesUnit;
    }

    public String toString() {
        return "UserInfo{mSex=" + this.mSex + ", mDateOfBirth=" + this.mDateOfBirth + ", mHeightUnit=" + this.mHeightUnit + ", mWeightUnit=" + this.mWeightUnit + ", mCaloriesUnit=" + this.mCaloriesUnit + '}';
    }
}
