import java.awt.Color;
import java.util.Random;

/**
 * Factory class responsible for creating all 7 standard Tetromino types.
 * Uses the Factory design pattern to abstract piece instantiation.
 */
public class TetrominoFactory {

    private static final Random random = new Random();

    // Piece type constants
    public static final String I_PIECE = "I";
    public static final String O_PIECE = "O";
    public static final String T_PIECE = "T";
    public static final String S_PIECE = "S";
    public static final String Z_PIECE = "Z";
    public static final String L_PIECE = "L";
    public static final String J_PIECE = "J";

    private static final String[] ALL_TYPES = {
        I_PIECE, O_PIECE, T_PIECE, S_PIECE, Z_PIECE, L_PIECE, J_PIECE
    };

    /**
     * Creates a specific tetromino by type string.
     */
    public static Tetromino create(String type) {
        return switch (type) {
            case I_PIECE -> new IPiece();
            case O_PIECE -> new OPiece();
            case T_PIECE -> new TPiece();
            case S_PIECE -> new SPiece();
            case Z_PIECE -> new ZPiece();
            case L_PIECE -> new LPiece();
            case J_PIECE -> new JPiece();
            default -> throw new IllegalArgumentException("Unknown tetromino type: " + type);
        };
    }

    /**
     * Creates a random tetromino from all 7 types.
     */
    public static Tetromino createRandom() {
        String type = ALL_TYPES[random.nextInt(ALL_TYPES.length)];
        return create(type);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Concrete Tetromino Subclasses
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * I-Piece: Long straight piece — cyan
     *
     *  □□□□
     *  ████
     *  □□□□
     *  □□□□
     */
    static class IPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0} },
                { {0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0} },
                { {0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {0,0,0,0} },
                { {0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(0, 240, 240); // Cyan
        }

        @Override
        public String getType() { return I_PIECE; }
    }

    /**
     * O-Piece: Square piece — yellow
     *
     *  ██
     *  ██
     */
    static class OPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            int[][] shape = { {0,1,1,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0} };
            return new int[][][] { shape, shape, shape, shape };
        }

        @Override
        protected Color defineColor() {
            return new Color(240, 240, 0); // Yellow
        }

        @Override
        public String getType() { return O_PIECE; }
    }

    /**
     * T-Piece: T-shaped — purple
     *
     *  □█□
     *  ███
     *  □□□
     */
    static class TPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {0,1,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0} },
                { {0,1,0,0}, {0,1,1,0}, {0,1,0,0}, {0,0,0,0} },
                { {0,0,0,0}, {1,1,1,0}, {0,1,0,0}, {0,0,0,0} },
                { {0,1,0,0}, {1,1,0,0}, {0,1,0,0}, {0,0,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(160, 0, 240); // Purple
        }

        @Override
        public String getType() { return T_PIECE; }
    }

    /**
     * S-Piece: S-shaped — green
     *
     *  □██
     *  ██□
     *  □□□
     */
    static class SPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0} },
                { {0,1,0,0}, {0,1,1,0}, {0,0,1,0}, {0,0,0,0} },
                { {0,0,0,0}, {0,1,1,0}, {1,1,0,0}, {0,0,0,0} },
                { {1,0,0,0}, {1,1,0,0}, {0,1,0,0}, {0,0,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(0, 240, 0); // Green
        }

        @Override
        public String getType() { return S_PIECE; }
    }

    /**
     * Z-Piece: Z-shaped — red
     *
     *  ██□
     *  □██
     *  □□□
     */
    static class ZPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0} },
                { {0,0,1,0}, {0,1,1,0}, {0,1,0,0}, {0,0,0,0} },
                { {0,0,0,0}, {1,1,0,0}, {0,1,1,0}, {0,0,0,0} },
                { {0,1,0,0}, {1,1,0,0}, {1,0,0,0}, {0,0,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(240, 0, 0); // Red
        }

        @Override
        public String getType() { return Z_PIECE; }
    }

    /**
     * L-Piece: L-shaped — orange
     *
     *  █□□
     *  █□□
     *  ██□
     */
    static class LPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {1,0,0,0}, {1,0,0,0}, {1,1,0,0}, {0,0,0,0} },
                { {0,0,0,0}, {1,1,1,0}, {1,0,0,0}, {0,0,0,0} },
                { {1,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,0,0,0} },
                { {0,0,1,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(240, 160, 0); // Orange
        }

        @Override
        public String getType() { return L_PIECE; }
    }

    /**
     * J-Piece: J-shaped — blue
     *
     *  □█□
     *  □█□
     *  ██□
     */
    static class JPiece extends Tetromino {
        @Override
        protected int[][][] defineRotations() {
            return new int[][][] {
                { {0,1,0,0}, {0,1,0,0}, {1,1,0,0}, {0,0,0,0} },
                { {1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0} },
                { {1,1,0,0}, {1,0,0,0}, {1,0,0,0}, {0,0,0,0} },
                { {0,0,0,0}, {1,1,1,0}, {0,0,1,0}, {0,0,0,0} }
            };
        }

        @Override
        protected Color defineColor() {
            return new Color(0, 0, 240); // Blue
        }

        @Override
        public String getType() { return J_PIECE; }
    }
}
