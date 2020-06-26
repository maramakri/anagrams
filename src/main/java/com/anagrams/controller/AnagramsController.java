package com.anagrams.controller;

import com.anagrams.common.Constants;
import com.anagrams.service.AnagramsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AnagramsController {

    AnagramsService anagramsService;

    public AnagramsController(AnagramsService anagramsService) {
        this.anagramsService = anagramsService;
    }

    @GetMapping("/anagrams/{string1}/{string2}")
    public ResponseEntity<Object> areAnagrams(@PathVariable("string1") String string1,
                             @PathVariable("string2") String string2) {

        if(Constants.errors.OK.toString()
                .equalsIgnoreCase(anagramsService.areAnagrams(string1, string2))) {
            return new ResponseEntity<Object>("{areAnagrams:true}", HttpStatus.OK);
        }
        else if(Constants.errors.NOT_ANAGRAMS.toString()
                .equalsIgnoreCase(anagramsService.areAnagrams(string1, string2))) {
            return new ResponseEntity<Object>("{areAnagrams:false}", HttpStatus.OK);
        }

        return new ResponseEntity<Object>("{areAnagrams:false}", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/anagrams/{string1}")
    public ResponseEntity<Object> getAnagrams(@PathVariable("string1") String string1) {

        Set<String> anagrams = anagramsService.getAnagrams(string1);

        if(anagrams.size() == 0) {
            return new ResponseEntity<Object>("{anagrams:[]}", HttpStatus.BAD_REQUEST);
        }

        String anagramsSet = anagrams.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return new ResponseEntity<Object>("{anagrams:[" + anagramsSet + "]}", HttpStatus.OK);
    }
}
