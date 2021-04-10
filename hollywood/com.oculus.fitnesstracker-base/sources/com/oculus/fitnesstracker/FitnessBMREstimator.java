package com.oculus.fitnesstracker;

import java.util.Calendar;

public class FitnessBMREstimator {
    static final String FEMALE_STR = "female";
    static final String MALE_STR = "male";

    private static double getHeightValue(double d) {
        if (d == 0.0d) {
            return 168.4d;
        }
        return d;
    }

    private static double getWeightValue(double d) {
        if (d == 0.0d) {
            return 82.6d;
        }
        return d;
    }

    public static double calculateUserBMR(String str, String str2, double d, double d2) {
        return (getWeightValue(d2) * 10.0d) + (getHeightValue(d) * 6.25d) + (getAgeValue(str2) * -5.0d) + getCalculatedSexConstant(str);
    }

    private static double getCalculatedSexConstant(String str) {
        if (MALE_STR.equals(str)) {
            return 5.0d;
        }
        return FEMALE_STR.equals(str) ? -161.0d : -78.0d;
    }

    private static double getAgeValue(String str) {
        if (str == null) {
            return 35.0d;
        }
        String[] split = str.split("-");
        Calendar instance = Calendar.getInstance();
        if (((double) (instance.get(2) + 1)) < Double.valueOf(split[1]).doubleValue()) {
            double d = (double) instance.get(1);
            double doubleValue = Double.valueOf(split[0]).doubleValue();
            Double.isNaN(d);
            return (d - doubleValue) - 1.0d;
        } else if (((double) (instance.get(2) + 1)) != Double.valueOf(split[1]).doubleValue()) {
            double d2 = (double) instance.get(1);
            double doubleValue2 = Double.valueOf(split[0]).doubleValue();
            Double.isNaN(d2);
            return d2 - doubleValue2;
        } else if (((double) instance.get(5)) < Double.valueOf(split[2]).doubleValue()) {
            double d3 = (double) instance.get(1);
            double doubleValue3 = Double.valueOf(split[0]).doubleValue();
            Double.isNaN(d3);
            return (d3 - doubleValue3) - 1.0d;
        } else {
            double d4 = (double) instance.get(1);
            double doubleValue4 = Double.valueOf(split[0]).doubleValue();
            Double.isNaN(d4);
            return d4 - doubleValue4;
        }
    }
}
