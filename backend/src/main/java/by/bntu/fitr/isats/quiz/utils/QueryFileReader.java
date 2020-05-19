package by.bntu.fitr.isats.quiz.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QueryFileReader {

    private static final String QUERIES_LOCATION = "sql/query/";
    private static final char NEW_LINE_CHARACTER = '\n';

    public static String getQuery(String fileName) {
        try {
            StringBuilder builder = new StringBuilder();
            Path path = Paths.get(buildFileName(fileName));
            Files.lines(path)
                    .forEach(l -> builder.append(l).append(NEW_LINE_CHARACTER));
            return builder.toString();
        } catch (URISyntaxException | IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static URI buildFileName(String file) throws URISyntaxException {
        return ClassLoader.getSystemResource(QUERIES_LOCATION + file)
                .toURI();
    }

}
