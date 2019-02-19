package datacompression.hufmann;

import java.util.PriorityQueue;

/* entropy coding technique:
   the length of each codeword is ~ proportional to the negative logarithm of the probability
   (more frequent words, shorter codes)
 */
public class HuffmanEncoding {

    public HuffmanTree buildTree(int[] frequencies) {
        PriorityQueue<HuffmanTree> queue = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanLeaf(frequencies[i], (char) i));
            }
        }

        while (queue.size() > 1) {
            HuffmanTree tree1 = queue.poll();
            HuffmanTree tree2 = queue.poll();
            queue.add(new HuffmanNode(tree1, tree2));
        }

        return queue.poll();
    }

    public void print(HuffmanTree tree, StringBuilder prefix) {
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf) tree;
            System.out.println(leaf.getValue() + "\t" + leaf.getFrequency() + "\t" + prefix);
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode) tree;
            // traverse left
            prefix.append("0");
            print(node.getLeft(), prefix);
            prefix.deleteCharAt(prefix.length() - 1);
            // traverse right
            prefix.append("1");
            print(node.getRight(), prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

}
