package entities.uniquewordcount;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UniqueWordCounter {
    public static final Logger LOGGER = LogManager.getLogger(UniqueWordCounter.class);

    public static void wordCount(File file) {
        try {
            List<String> wordList = prepWordList(FileUtils.readFileToString(file));
            Set<String> stringSet = new HashSet<>(wordList);
            String writeStringToFile = "There are " + stringSet.size() + " unique words in the string.\n" + stringSet;
            FileUtils.writeStringToFile(new File("uniqueWords.txt"), writeStringToFile);
            LOGGER.info("Unique word count Written to file: " + file.getName() + "\n Contents: " +  writeStringToFile);
        } catch (IOException ioException) {
            LOGGER.error(ioException.getMessage());
        }
    }

    private static List<String> prepWordList(String rawContentFromFile) {
        String strWithoutLines = rawContentFromFile.replaceAll(System.lineSeparator(), " ");
        String strWithoutSpecialChar = strWithoutLines.replaceAll("[^\\w@ ]", "");
        return Arrays.asList(StringUtils.split(strWithoutSpecialChar.toLowerCase(), " "));
    }
}
