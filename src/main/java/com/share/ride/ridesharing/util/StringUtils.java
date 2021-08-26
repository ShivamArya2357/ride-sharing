package com.share.ride.ridesharing.util;

/**
 * The Class StringUtils.
 */
public class StringUtils {

    private StringUtils() {

    }

    /**
     * Checks if it is empty or null.
     *
     * @param s the s
     * @return true, if is empty or null
     */
    public static boolean isEmptyOrNull(String s) {
        return null == s || s.trim().isEmpty();
    }

    /**
     * Checks if it is not empty.
     *
     * @param s the s
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(String s) {
        return null != s && !s.trim().isEmpty();
    }

    /**
     * Checks if both are same.
     *
     * @param s1 the s 1
     * @param s2 the s 2
     * @return true, if is same
     */
    public static boolean isSame(String s1, String s2) {

        return (null == s1 && null == s2) || (null != s1 && null != s2 && s1.equals(s2));

    }

    /**
     * to validate if input is null or not
     *
     * @param input
     * @return String
     */
    public static String coalesce(String input) {
        return coalesce(input, "");
    }

    public static String coalesce(String input, String defaultValue) {
        return input == null ? defaultValue : input;
    }

    public static String coalesceWithEmpty(String input, String defaultValue) {
        return (input == null || input.isEmpty()) ? defaultValue : input;
    }

    /**
     * multiple validate checker for list of string
     * @param arg
     * @return
     */
    public static boolean areEmptyOrNull(String... arg) {
        for (String s : arg) {
            if (!isEmptyOrNull(s)) return false;
        }
        return true;
    }
}
