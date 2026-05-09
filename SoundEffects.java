package com.mycompany.tetrisgame;

public class SoundEffects {
    private SoundEffects() {}

    //background music
    public static void startBackgroundMusic() {
        SoundManager.playMusic("OOP-project/Sounds/tetrismusic.wav");
    }

    public static void stopBackgroundMusic() {
        SoundManager.stopMusic();
    }

    //pause screen
    public static void setBackgroundMusicPaused(boolean paused) {
        if (paused) {
            SoundManager.pauseMusic();
        } else {
            SoundManager.resumeMusic();
        }
    }

    //movement
    public static void onSoftDrop() {
        SoundManager.playSound("Sounds/soundsdrop.wav");
    }

    public static void onHardDrop() {
        SoundManager.playComboSound("OOP-project/Sounds/soundsdrop.wav", 4);
    }

    public static void onPieceLock() {
        SoundManager.playSound("Sounds/soundspiecelock.wav");
    }

    public static void onRotate() {
        SoundManager.playSound("Sounds/soundsrotate.wav");
    }

    public static void onMove() {
        SoundManager.playSound("Sounds/soundslateralmove.wav");
    }

    //UI interactions
    public static void onMenuClick() {
        SoundManager.playSound("Sounds/soundsselect.wav");
    }

    public static void onMenuBack() {
        SoundManager.playSound("Sounds/soundsselect.wav");
    }
    
    //game end and milestones
    public static void onLevelUp() {
        SoundManager.playSound("Sounds/soundslevelup.wav");
    }
    
    public static void onLineClear(int lineCount) {
        SoundManager.playComboSound("OOP-project/Sounds/soundsclear.wav", lineCount);
    }
    
    public static void onTetris() {
        SoundManager.playComboSound("OOP-project/Sounds/soundstetris.wav", 4);
    }
    public static void onGameOver() {
        SoundManager.playSound("Sounds/soundsgameover.wav");
    }

    public static void onHighScore() {
        SoundManager.playSound("Sounds/soundshighscore.wav");
    }
}
