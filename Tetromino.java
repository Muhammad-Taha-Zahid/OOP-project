import java.awt.Color;

/**
 * Abstract base class representing a Tetromino piece.
 * All specific tetromino shapes must extend this class.
 */
public abstract class Tetromino {

    // The 4x4 grid shape of the tetromino (1 = filled, 0 = empty)
    protected int[][] shape;

    // Color of the tetromino
    protected Color color;

    // Current rotation state (0-3)
    protected int rotationState;

    // All rotation states of the piece (4 rotations)
    protected int[][][] rotations;

    public Tetromino() {
        this.rotationState = 0;
        this.rotations = defineRotations();
        this.shape = rotations[0];
        this.color = defineColor();
    }

    /**
     * Each subclass defines its four rotation states.
     * @return 4 rotation matrices, each a 4x4 int grid
     */
    protected abstract int[][][] defineRotations();

    /**
     * Each subclass defines its unique color.
     * @return Color of the piece
     */
    protected abstract Color defineColor();

    /**
     * Returns the name/type of the tetromino (e.g., "I", "T", "L").
     */
    public abstract String getType();

    /**
     * Rotate the piece clockwise by advancing the rotation state.
     */
    public void rotateClockwise() {
        rotationState = (rotationState + 1) % 4;
        shape = rotations[rotationState];
    }

    /**
     * Rotate the piece counter-clockwise.
     */
    public void rotateCounterClockwise() {
        rotationState = (rotationState + 3) % 4;
        shape = rotations[rotationState];
    }

    /**
     * Returns the current shape grid.
     */
    public int[][] getShape() {
        return shape;
    }

    /**
     * Returns the color of this tetromino.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the current rotation index (0–3).
     */
    public int getRotationState() {
        return rotationState;
    }

    /**
     * Returns a deep copy of the shape for preview or ghost rendering.
     */
    public int[][] getShapeCopy() {
        int[][] copy = new int[shape.length][];
        for (int i = 0; i < shape.length; i++) {
            copy[i] = shape[i].clone();
        }
        return copy;
    }

    /**
     * Returns a preview shape for the "next piece" panel (smallest bounding box).
     */
    public int[][] getPreviewShape() {
        return rotations[0];
    }

    @Override
    public String toString() {
        return "Tetromino[" + getType() + ", rotation=" + rotationState + "]";
    }
}
