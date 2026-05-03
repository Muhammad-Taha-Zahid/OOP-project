package com.mycompany.tetrisgame;
//factory layout for concrete gamemodes

public class GameModes {
    public enum Difficulty { //changes drop speed
        SLOW   ("Slow",   1),
        MEDIUM ("Medium", 5),
        FAST   ("Fast",  10);

        private final String displayName;
        private final int startingLevel;

        Difficulty(String displayName, int startingLevel) {
            this.displayName  = displayName;
            this.startingLevel = startingLevel;
        }

        public String getDisplayName()  { return displayName;  }
        public int    getStartingLevel() { return startingLevel; }
    }

    //abstract class all concrete modes and built on
    public static abstract class GameMode {

        protected int    lines      = 0;
        protected int    score      = 0;
        protected long   startTimeMs = 0;
        protected int    level      = 1;
        protected boolean started   = false;
        public boolean shouldClearOnTopOut() { return false; } //for zen mode
        public abstract String getModeName(); //name in UI

        /*checks if gameover condition is met every tick,
        lines cleared for 40 lines and elapsed ms for time trial*/
        public abstract boolean isGameOver(int linesCleared, long elapsedMs);
        
        //below 3 functions return false for zen mode
        public abstract boolean recordsHighScore();
        public boolean showScore() { return true; }
        public boolean fixedLevel() { return false; }
        
        public void onStart(int startingLevel) { //starting conditions noted
            this.startTimeMs = System.currentTimeMillis();
            this.level       = startingLevel;
            this.started     = true;
        }

        //constructs game over screen
        public String[][] getResultStats(int finalScore, int finalLines, long elapsedMs) {
            return new String[][] {
                {"SCORE", String.valueOf(finalScore)},
                {"LINES", String.valueOf(finalLines)},
                {"TIME",  formatTime(elapsedMs)}
            };
        }

        //formats time in minutes and seconds
        protected static String formatTime(long ms) {
            long totalSec = ms / 1000;
            long min      = totalSec / 60;
            long sec      = totalSec % 60;
            return String.format("%d:%02d", min, sec);
        }
    }

    //standard mode
    public static class StandardMode extends GameMode {

        @Override
        public String getModeName() { return "Standard"; }

        @Override
        public boolean isGameOver(int linesCleared, long elapsedMs) {
            //termination is handled by GameBoard when spawn fails.
            return false;
        }

        @Override
        public boolean recordsHighScore() { return true; }
    }
    
    //fourty lines
    public static class FourtyLines extends GameMode {

        private static final int TARGET_LINES = 40;

        @Override
        public String getModeName() { return "40 Lines"; }

        @Override
        public boolean isGameOver(int linesCleared, long elapsedMs) {
            return linesCleared >= TARGET_LINES; //lines incremented in TetrisGame.java checkLines()
        }

        @Override
        public boolean recordsHighScore() { return true; }
    }

    //time trial
    public static class TimeTrial extends GameMode {

        private static final long DURATION_MS = 120_000L; // 2 minutes

        @Override
        public String getModeName() { return "Time Trial"; }

        @Override
        public boolean isGameOver(int linesCleared, long elapsedMs) {
            return elapsedMs >= DURATION_MS;
        }

        @Override
        public boolean recordsHighScore() { return true; }

        public long getRemainingMs(long elapsedMs) {
            return Math.max(0, DURATION_MS - elapsedMs);
        }

        public long getDurationMs() { return DURATION_MS; } //2 minutes
    }

    //zen mode
    public static class ZenMode extends GameMode {
        
        @Override
        public boolean shouldClearOnTopOut() { return true; }

        @Override
        public String getModeName() { return "Zen"; }

        @Override
        public boolean isGameOver(int linesCleared, long elapsedMs) {
            return false; // Never game over
        }

        @Override
        public boolean recordsHighScore() { return false; }

        @Override
        public boolean showScore() { return false; }

        @Override
        public boolean fixedLevel() { return true; }
        }
    }
