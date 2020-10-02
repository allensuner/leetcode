package org.example.leetcode.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {
    
    static class TreeNode {
        private final int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(final int x) {
            this.val = x;
        }
    }

    // [0,1, 2,   3,  4,5, 6,   7,  8, 9,   10 ]
    // [1,2,null,null,3,4,null,null,5,null,null]

    /*
            1
          /   \
         2     3
             /   \
            4     5

            1: 0  -> [1,4]  level 1
            2: 1  -> [2,3]  level 2
         null: 2  -> []     level 3
         null: 3  -> []     level 3
            3: 4  -> [5,8]  level 2
            4: 5  -> [6,7]  level 3
         null: 6  -> []     level 4
         null: 7  -> []     level 4
            5: 8  -> [9,10] level 3
         null: 9  -> []     level 4
         null: 10 -> []     level 4
     */

    // Encodes a tree to a single string.
    public String serialize(final TreeNode root) {
        final List<String> nodes = new LinkedList<>();
        serializeTraversal(root, nodes);
        return String.join(",", nodes);
    }

    private void serializeTraversal(final TreeNode root, final List<String> nodes) {
        if (root == null) {
            nodes.add("null");
            return;
        }
        nodes.add(String.valueOf(root.val));
        serializeTraversal(root.left, nodes);
        serializeTraversal(root.right, nodes);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(final String data) {
        // first get data into an array of strings
        final LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeTraversal(nodes);
    }

    public TreeNode deserializeTraversal(LinkedList<String> nodes) {
        if (nodes.getFirst().equals("null")) {
            nodes.removeFirst();
            return null;
        }
        final TreeNode root = new TreeNode(Integer.parseInt(nodes.removeFirst()));
        root.left = deserializeTraversal(nodes);
        root.right = deserializeTraversal(nodes);
        return root;
    }
}