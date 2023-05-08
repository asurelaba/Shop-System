package entities.uniquewordcount;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueWordCounter {
    public static final Logger LOGGER = LogManager.getLogger(UniqueWordCounter.class);
    public static void wordCount(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file);
        LOGGER.info("File contents \n" + lines + " " + lines.size());
        Set<String> stringSet = new HashSet<>();
        for (String s : lines) {
            stringSet.addAll(Arrays.asList(StringUtils.split(s, ' ')));
        }
        FileUtils.writeStringToFile(new File("uniqueWords.txt"), "There are " + stringSet.size() + " unique words in the string.\n" + stringSet);
    }
}
