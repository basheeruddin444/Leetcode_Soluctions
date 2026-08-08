import java.util.*;

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode current = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';

                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }

                current = current.children[index];
            }

            current.word = word;
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                search(board, row, col, root, result);
            }
        }

        return result;
    }

    private void search(char[][] board, int row, int col,
                        TrieNode node, List<String> result) {

        if (row < 0 || col < 0 ||
            row >= board.length || col >= board[0].length ||
            board[row][col] == '#') {
            return;
        }

        char ch = board[row][col];
        TrieNode next = node.children[ch - 'a'];

        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[row][col] = '#';

        search(board, row + 1, col, next, result);
        search(board, row - 1, col, next, result);
        search(board, row, col + 1, next, result);
        search(board, row, col - 1, next, result);

        board[row][col] = ch;
    }
}