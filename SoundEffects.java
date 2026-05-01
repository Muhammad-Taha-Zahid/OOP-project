/**
 * SoundEffects.java — Stub/dummy sound effect hooks for Tetris.
 *
 * HOW TO IMPLEMENT:
 *   All methods in this class are intentionally empty stubs.
 *   A programmer wishing to add real audio should:
 *     1. Add audio libraries (e.g. javax.sound.sampled, or a third-party lib).
 *     2. Load audio resources in the static initialiser or constructor.
 *     3. Replace each method body with the appropriate playback call.
 *   No other file needs to be modified — TetrisGame, GameBoard and
 *   UserInterface already call these methods at the correct moments.
 *
 * All methods are static for easy call-site ergonomics (SoundEffects.onDrop()).
 */
public class SoundEffects {

    // ── Private constructor — static utility class, not instantiated ──────────
    private SoundEffects() {}

    // ─────────────────────────────────────────────────────────────────────────
    // Background Music
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Start the background music loop.
     * Called once when the gameplay screen is shown.
     * Should loop continuously until stopBackgroundMusic() is called.
     */
    public static void startBackgroundMusic() {
        // TODO: load and loop a background music track
    }

    /**
     * Stop the background music.
     * Called when the game ends, is paused, or the player returns to a menu.
     */
    public static void stopBackgroundMusic() {
        // TODO: stop the background music track
    }

    /**
     * Pause/resume the background music (e.g. on P key).
     * @param paused true to pause, false to resume
     */
    public static void setBackgroundMusicPaused(boolean paused) {
        // TODO: pause or resume the background music
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Piece Movement & Placement
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Played when a piece is soft-dropped (player holds the down key).
     */
    public static void onSoftDrop() {
        // TODO: play a short soft-drop tick sound
    }

    /**
     * Played when a piece is hard-dropped (player presses Space).
     * Typically a louder, sharper impact sound than a soft drop.
     */
    public static void onHardDrop() {
        // TODO: play a hard-drop impact sound
    }

    /**
     * Played when a piece naturally locks into position (touches the stack).
     */
    public static void onPieceLock() {
        // TODO: play a piece-lock sound
    }

    /**
     * Played when a piece is rotated.
     */
    public static void onRotate() {
        // TODO: play a rotate swoosh/click sound
    }

    /**
     * Played when a piece is moved left or right.
     */
    public static void onMove() {
        // TODO: play a lateral-move tick sound
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Line Clears
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Played when one, two, or three lines are cleared at once.
     * @param lineCount the number of lines cleared (1, 2, or 3)
     */
    public static void onLineClear(int lineCount) {
        // TODO: play a line-clear sound scaled to lineCount
    }

    /**
     * Played when exactly four lines are cleared simultaneously (Tetris!).
     * Should be distinct and more dramatic than onLineClear().
     */
    public static void onTetris() {
        // TODO: play a special Tetris four-line-clear fanfare
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Level & Score Milestones
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Played when the player advances to a new level.
     */
    public static void onLevelUp() {
        // TODO: play a level-up jingle or sound
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Menu & UI Interactions
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Played when any menu button is clicked.
     */
    public static void onMenuClick() {
        // TODO: play a UI button click sound
    }

    /**
     * Played when the player navigates back from a screen.
     */
    public static void onMenuBack() {
        // TODO: play a back-navigation sound (may be same as onMenuClick)
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Game State Events
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Played when the game ends (stack reaches the top).
     */
    public static void onGameOver() {
        // TODO: play a game-over sound or jingle
    }

    /**
     * Played when the player achieves or beats a high score.
     */
    public static void onHighScore() {
        // TODO: play a high-score celebration sound
    }
}
