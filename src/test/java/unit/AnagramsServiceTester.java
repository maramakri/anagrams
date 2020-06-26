package unit;

import com.anagrams.common.Constants;
import com.anagrams.service.AnagramsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnagramsServiceTester {

    static AnagramsService anagramsService;

    @BeforeAll
    static void initUseCase() {
        anagramsService = new AnagramsService();
    }

    @Test
    void areAnagramsShouldReturnOk() {
        assertEquals(Constants.errors.OK.toString(), anagramsService.areAnagrams("blah", "halb"));
    }

    @Test
    void areAnagramsShouldReturnBadRequest() {
        assertEquals(Constants.errors.BAD_REQUEST.toString(), anagramsService.areAnagrams("blah", "--"));
    }

    @Test
    void areAnagramsShouldReturnNotAnagrams() {
        assertEquals(Constants.errors.NOT_ANAGRAMS.toString(), anagramsService.areAnagrams("blah", "ramk"));
    }

    @Test
    void getAnagramsShouldReturnEmptyList() {
        assertEquals(Collections.emptySet(), anagramsService.getAnagrams("--"));
    }

    @Test
    void getAnagramsShouldReturnNonEmptyList() {
        assertNotEquals(Collections.emptySet(), anagramsService.getAnagrams("blah"));
    }
}
