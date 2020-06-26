package com.anagrams.service;

import com.anagrams.common.Constants;

import java.util.Set;

public interface Anagrams {

    default String areAnagrams(String string1, String string2) {

        return Constants.errors.BAD_REQUEST.toString();
    }

    Set<String> getAnagrams(String string1);
}
