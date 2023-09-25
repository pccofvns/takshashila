package com.pccofvns.ts.utils;

public class StringUtils {

    public static final int INDEX_NOT_FOUND = -1;

    public static String substringBeforeLast(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    public static boolean isEmpty(String input){
        return input == null || input.isEmpty();
    }
}
