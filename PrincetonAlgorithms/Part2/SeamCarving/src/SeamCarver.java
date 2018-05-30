import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    private double[][] energies;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }

        this.picture = new Picture(picture);
        this.energies = new double[this.picture.height()][this.picture.width()];

        for (int i = 0; i < energies.length; i++) {
            for (int j = 0; j < energies[i].length; j++) {
                energies[i][j] = calculateEnergy(j, i);
            }
        }
    }

    public Picture picture() {
        return new Picture(picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        if (x < 0 || y < 0 || y > picture.height() || x > picture.width()) {
            throw new IllegalArgumentException();
        }

        return energies[y][x];
    }

    public int[] findHorizontalSeam() {
        this.energies = transform();

        int[] verticalSeam = findVerticalSeam();

        this.energies = transform();

        return verticalSeam;
    }

    public int[] findVerticalSeam() {
        int[] indices = new int[energies.length];

        for (int i = 0; i < energies.length; i++) {
            int minIndexInRow = Integer.MAX_VALUE;
            double minValue = Integer.MAX_VALUE;

            for (int j = 1; j < energies[i].length - 1; j++) {
                if (energies[i][j] < minValue && i == 0) {
                    minValue = energies[i][j];
                    minIndexInRow = j;
                    indices[0] = j;
                }

                if (energies[i][j] < minValue && (j + 1 == indices[i - 1] || j - 1 == indices[i - 1] || j == indices[i - 1])) {
                    minIndexInRow = j;
                    minValue = energies[i][j];
                }
            }
            indices[i] = minIndexInRow;
        }

        return indices;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || !isValidHorizontalSeam(seam)) {
            throw new IllegalArgumentException();
        }

        if (picture.height() <= 1) {
            throw new IllegalArgumentException();
        }

        double[][] recalculatedEnergies = new double[picture.height() - 1][picture.width()];
        Picture resized = new Picture(this.picture.width(), this.picture.height() - 1);

        for (int i = 0; i < resized.width(); i++) {
            int k = 0;
            for (int j = 0; j < resized.height() - 1; j++) {
                if (j == seam[i]) {
                    k++;
                }
                recalculatedEnergies[j][i] = energies[k][i];
                resized.setRGB(i, j, this.picture.getRGB(i, k++));
            }
        }

        this.picture = resized;

        recalculateAfterHorizontalRemove(seam, recalculatedEnergies);
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || !isValidVerticalSeam(seam)) {
            throw new IllegalArgumentException();
        }

        if (picture.width() <= 1) {
            throw new IllegalArgumentException();
        }

        double[][] recalculatedEnergies = new double[picture.height()][picture.width() - 1];
        Picture resized = new Picture(this.picture.width() - 1, this.picture.height());

        for (int i = 0; i < resized.height(); i++) {
            int k = 0;
            for (int j = 0; j < resized.width(); j++) {
                if (j == seam[i]) {
                    k++;
                }
                resized.setRGB(j, i, this.picture.getRGB(k++, i));
            }
        }

        this.picture = resized;

        recalculateAfterVerticalRemove(seam, recalculatedEnergies);
    }

    private boolean isValidHorizontalSeam(int[] seam) {
        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] > picture.height() - 1 || seam[i] < 0) {
                return false;
            }
            if (seam[i] + 1 < seam[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidVerticalSeam(int[] seam) {
        for (int i = 0; i < seam.length - 1; i++) {
            if (seam[i] > picture.width() - 1 || seam[i] < 0) {
                return false;
            }
            if (seam[i] + 1 < seam[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private double calculateEnergy(int x, int y) {
        if (x == 0 || x == picture.width() - 1 || y == 0 || y == picture.height() - 1) {
            return 1000;
        }

        return Math.sqrt(calculateVerticalEnergy(x, y) + calculateHorizontalEnergy(x, y));
    }

    private double calculateHorizontalEnergy(int x, int y) {
        double redRight = 0;
        double greenRight = 0;
        double blueRight = 0;

        double redLeft = 0;
        double greenLeft = 0;
        double blueLeft = 0;

        if (x < picture.width() - 1) {
            int rgbOfRightNeighbor = picture.getRGB(x + 1, y);
            redRight = (rgbOfRightNeighbor >> 16) & 0xFF;
            greenRight = (rgbOfRightNeighbor >> 8) & 0xFF;
            blueRight = (rgbOfRightNeighbor) & 0xFF;
        }

        if (x > 0) {
            int rgbOfLeftNeighbor = picture.getRGB(x - 1, y);
            redLeft = (rgbOfLeftNeighbor >> 16) & 0xFF;
            greenLeft = (rgbOfLeftNeighbor >> 8) & 0xFF;
            blueLeft = (rgbOfLeftNeighbor) & 0xFF;
        }

        return Math.pow(redRight - redLeft, 2) + Math.pow(blueRight - blueLeft, 2) + Math.pow(greenRight - greenLeft, 2);
    }

    private double calculateVerticalEnergy(int x, int y) {
        double redTop = 0;
        double greenTop = 0;
        double blueTop = 0;

        double redBottom = 0;
        double greenBottom = 0;
        double blueBottom = 0;

        if (y > 0) {
            int rgbOfTopNeighbor = picture.getRGB(x, y - 1);
            redTop = (rgbOfTopNeighbor >> 16) & 0xFF;
            greenTop = (rgbOfTopNeighbor >> 8) & 0xFF;
            blueTop = (rgbOfTopNeighbor) & 0xFF;
        }

        if (y < picture.height() - 1) {
            int rgbOfBottomNeighbor = picture.getRGB(x, y + 1);
            redBottom = (rgbOfBottomNeighbor >> 16) & 0xFF;
            greenBottom = (rgbOfBottomNeighbor >> 8) & 0xFF;
            blueBottom = (rgbOfBottomNeighbor) & 0xFF;
        }

        return Math.pow(redTop - redBottom, 2) + Math.pow(blueTop - blueBottom, 2) + Math.pow(greenTop - greenBottom, 2);
    }

    private void recalculateAfterHorizontalRemove(int[] seam, double[][] recalculatedEnergies) {
        for (int i = 0; i < recalculatedEnergies[0].length; i++) {
            if (seam[i] > 0) {
                recalculatedEnergies[seam[i] - 1][i] = calculateEnergy(i, seam[i] - 1);
            }
            recalculatedEnergies[seam[i]][i] = calculateEnergy(i, seam[i]);
        }

        this.energies = recalculatedEnergies;
    }

    private void recalculateAfterVerticalRemove(int[] seam, double[][] recalculatedEnergies) {
        for (int i = 0; i < recalculatedEnergies.length; i++) {
            if (seam[i] > 0) {
                recalculatedEnergies[i][seam[i] - 1] = calculateEnergy(seam[i] - 1, i);
            }
            recalculatedEnergies[i][seam[i]] = calculateEnergy(seam[i], i);
        }

        this.energies = recalculatedEnergies;
    }

    private double[][] transform() {
        double[][] transformed = new double[energies[0].length][energies.length];

        for (int i = 0; i < transformed.length; i++) {
            for (int j = 0; j < transformed[i].length; j++) {
                transformed[i][j] = energies[j][i];
            }
        }

        return transformed;
    }

}
