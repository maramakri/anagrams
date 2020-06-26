package com.anagrams.service;

import com.anagrams.common.AnagramUtils;
import com.anagrams.common.Constants;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class AnagramsService implements Anagrams{

    public String areAnagrams(String string1, String string2) {

        if(string1.isEmpty() || string2.isEmpty() ||
        !string1.chars().allMatch(Character::isLetter) ||
        !string2.chars().allMatch(Character::isLetter)) {
            return Constants.errors.BAD_REQUEST.toString();
        }
        else if(AnagramUtils.checkForAnagrams(string1, string2)) {
            return Constants.errors.OK.toString();
        }
        else {
            return Constants.errors.NOT_ANAGRAMS.toString();
        }
    }

    public Set<String> getAnagrams(String string1) {

        if(string1.isEmpty() || !string1.chars().allMatch(Character::isLetter)) {
            return Collections.emptySet();
        }

        return AnagramUtils.getPermutation(string1);
    }
}
