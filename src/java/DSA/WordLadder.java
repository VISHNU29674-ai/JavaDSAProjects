package java.DSA;
import java.util.*;

class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0; // No possible transformation

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int steps = 1; // Start with 1 because the beginWord is counted

        while (!queue.isEmpty()) {
            int size = queue.size(); // Process level by level
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return steps; // Found the endWord

                List<String> neighbors = getNeighbors(word, wordSet);
                for (String neighbor : neighbors) {
                    queue.offer(neighbor);
                    wordSet.remove(neighbor); // Mark as visited
                }
            }
            steps++; // Move to next level
        }

        return 0; // No valid transformation sequence
    }

    private static List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue; // Skip same character
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = original; // Restore original character
        }

        return neighbors;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        int result = ladderLength(beginWord, endWord, wordList);
        System.out.println(result); // Output: 5
    }
}
