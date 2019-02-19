package datacompression.hufmann;

public class HuffmanNode extends HuffmanTree {
    private HuffmanTree left;
    private HuffmanTree right;

    public HuffmanNode(HuffmanTree right, HuffmanTree left) {
        super(right.getFrequency() + left.getFrequency());
        this.right = right;
        this.left = left;
    }

    public HuffmanTree getRight() {
        return right;
    }

    public void setRight(HuffmanTree right) {
        this.right = right;
    }

    public HuffmanTree getLeft() {
        return left;
    }

    public void setLeft(HuffmanTree left) {
        this.left = left;
    }

}
