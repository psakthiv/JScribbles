import java.text.Normalizer;
import java.util.*;
import java.util.regex.*;

public class OcrCleaner {

    // You can add/remove any stopwords you want here
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
        "yes", "was", "am", "is", "are",
        "the", "a", "an",
        "this", "that", "these", "those",
        "and", "or", "but",
        "to", "for", "on", "in", "with", "at", "by", "from",
        "it", "its",
        "have", "has", "had",
        "be", "been", "being",
        "do", "does", "did",
        "will", "would", "can", "could",
        "shall", "should", "may", "might",
        "you", "i", "we", "they", "he", "she", "them", "him", "her", "me", "my",
        "our", "your", "their"
    ));

    public static String cleanOcrText(String text) {

        if (text == null) return "";

        // 1. Normalize unicode
        text = Normalizer.normalize(text, Normalizer.Form.NFKC);

        // 2. Remove repeated lines (such as duplicate headings)
        String[] lines = text.split("\\R+");
        Set<String> seen = new HashSet<>();
        StringBuilder unique = new StringBuilder();

        for (String line : lines) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty() && seen.add(trimmed)) {
                unique.append(trimmed).append(" ");
            }
        }

        text = unique.toString().trim();

        // 3. Fix hyphen + newline OCR issues (word-breaking)
        text = text.replaceAll("-\\s+", "");

        // 4. Replace multiple whitespace characters with single space
        text = text.replaceAll("\\s+", " ");

        // 5. Remove repeated punctuation //// → /, --- → -
        text = text.replaceAll("([/\\-:;,._])\\1+", "$1");

        // 6. Remove random garbage (keep letters, digits, space, / - . , : ;)
        text = text.replaceAll("[^\\w\\s/\\-.,:;]", "");

        // 7. Final spacing cleanup
        text = text.replaceAll("\\s+", " ").trim();

        // 8. Stopword removal ("yes", "was", "am", etc.)
        text = removeStopwords(text);

        return text;
    }

    private static String removeStopwords(String text) {
        StringBuilder result = new StringBuilder();

        for (String word : text.split("\\s+")) {
            String cleaned = word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""); // strip punctuation
            
            if (!STOPWORDS.contains(cleaned.toLowerCase())) {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }

}
