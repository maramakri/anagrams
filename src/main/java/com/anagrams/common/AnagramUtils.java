package com.anagrams.common;

import java.util.*;

public class AnagramUtils {

    public static boolean checkForAnagrams(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        char[] a1 = string1.toCharArray();
        char[] a2 = string2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    public static HashSet<String> getPermutation(String str)
    {
        if (str.length() == 0) {

            HashSet<String> empty = new HashSet<>();
            empty.add("");
            return empty;
        }

        char ch = str.charAt(0);
        String subStr = str.substring(1);

        HashSet<String> prevResult = getPermutation(subStr);

        HashSet<String> result = new HashSet<>();
        for (String val : prevResult) {
            for (int i = 0; i <= val.length(); i++) {
                result.add(val.substring(0, i) + ch + val.substring(i));
            }
        }

        return result;
    }
}
