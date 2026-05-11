// package com.mycompany.tetrisgame;
 
import java.awt.*;
import javax.swing.*;
 
public class UserInterface {
    static final Color BG = new Color(252, 250, 245);
    static final Color BORDER   = Color.GRAY;
    static final Color BOARD_BG = Color.LIGHT_GRAY;
 
    private static Font uiFont(int size) {
        return new Font("Segoe UI Semibold", Font.BOLD, size);
    }
 
    private static Color[] rainbow = {
        new Color(255, 80, 80), new Color(255, 160, 50), new Color(255, 210, 60),
        new Color(80, 200, 80), new Color(80, 150, 255), new Color(180, 80, 200)
    };
 
    private static Color[] btnColors = {
        new Color(100, 200, 100), new Color(100, 150, 230), new Color(230, 100, 100),
        new Color(230, 170, 70), new Color(160, 100, 200), new Color(80, 190, 190)
    };
 
    // Round Button
    static class RoundBtn extends JButton {
        Color c;
        RoundBtn(String text, Color color) {
            super(text);
            c = color;
            setForeground(Color.WHITE);
            setFont(uiFont(18));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(180, 50));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.setColor(Color.WHITE);
            g2.setFont(uiFont(18));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText()))/2;
            int y = (getHeight() - fm.getHeight())/2 + fm.getAscent();
            g2.drawString(getText(), x, y);
        }
    }
 
    // Arrow Button
    static class ArrowBtn extends JButton {
        Color c;
        String dir;
        ArrowBtn(String direction, Color color) {
            super();
            c = color;
            dir = direction;
            setForeground(Color.WHITE);
            setFont(uiFont(16));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(110, 45));
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.setColor(Color.WHITE);
            g2.setFont(uiFont(16));
            if(dir.equals("left")) {
                g2.drawString("← BACK", 25, 28);
            } else {
                g2.drawString("NEXT →", 22, 28);
            }
        }
    }
 
    // Rainbow Text
    static class RainbowText extends JLabel {
        String text;
        int fontSize;
        RainbowText(String text, int size) {
            super();
            this.text = text;
            this.fontSize = size;
            setHorizontalAlignment(CENTER);
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(uiFont(fontSize));
            FontMetrics fm = g2.getFontMetrics();
            int w = fm.stringWidth(text);
            int x = (getWidth() - w)/2;
            int y = (getHeight() - fm.getHeight())/2 + fm.getAscent();
            for(int i=0; i<text.length(); i++) {
                String ch = text.substring(i, i+1);
                g2.setColor(rainbow[i % rainbow.length]);
                g2.drawString(ch, x, y);
                x += fm.stringWidth(ch);
            }
        }
    }
 
    // Score Card
    static class ScoreCard extends JPanel {
        private Color cardColor;
        ScoreCard(String name, int score, Color color) {
            cardColor = color;
            setOpaque(false);
            setLayout(new BorderLayout());
            JLabel nameLbl = new JLabel(name);
            nameLbl.setForeground(Color.WHITE);
            nameLbl.setFont(uiFont(20));
            nameLbl.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
            JLabel scoreLbl = new JLabel(String.valueOf(score > 0 ? score : 0));
            scoreLbl.setForeground(Color.WHITE);
            scoreLbl.setFont(uiFont(24));
            scoreLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));
            add(nameLbl, BorderLayout.WEST);
            add(scoreLbl, BorderLayout.EAST);
        }
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(cardColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);
        }
    }
 
    // ==================== MAIN MENU ====================
 
    public static class MainMenuScreen extends JPanel {
        private final TetrisGame.TetrisFrame frame;
        public MainMenuScreen(TetrisGame.TetrisFrame frame) {
            this.frame = frame;
            setBackground(BG);
            setPreferredSize(new Dimension(550, 600));
            setLayout(null);
            RainbowText title = new RainbowText("TETRIS", 54);
            title.setBounds(120, 70, 300, 70);
            add(title);
            RoundBtn startBtn = new RoundBtn("START", btnColors[0]);
            startBtn.setBounds(180, 180, 180, 55);
            startBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showModeSelect();});
            RoundBtn highBtn = new RoundBtn("HIGH SCORE", btnColors[1]);
            highBtn.setBounds(180, 260, 180, 55);
            highBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showHighScores();});
            RoundBtn exitBtn = new RoundBtn("EXIT", btnColors[2]);
            exitBtn.setBounds(180, 340, 180, 55);
            exitBtn.addActionListener(e -> {SoundEffects.onMenuClick();System.exit(0);});
            add(startBtn);
            add(highBtn);
            add(exitBtn);
        }
    }
 
    // ==================== MODE SCREEN ====================
 
    public static class ModeSelectScreen extends JPanel {
        private final TetrisGame.TetrisFrame frame;
 
        public ModeSelectScreen(TetrisGame.TetrisFrame frame) {
            this.frame = frame;
            setBackground(BG);
            setPreferredSize(new Dimension(550, 600));
            setLayout(null);
 
            RainbowText heading = new RainbowText("MODES", 48);
            heading.setBounds(160, 40, 220, 60);
            add(heading);
 
            GameModes.GameMode[] modes = {
                new GameModes.StandardMode(),
                new GameModes.FourtyLines(),
                new GameModes.TimeTrial(),
                new GameModes.ZenMode()
            };
            String[] modeNames = {"STANDARD", "40 LINES", "TIME TRIAL", "ZEN"};
 
            for (int i = 0; i < modeNames.length; i++) {
                final int idx = i;
                RoundBtn btn = new RoundBtn(modeNames[i], btnColors[i]);
                btn.setBounds(180, 130 + i * 70, 180, 50);
                btn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showDifficultySelect(modes[idx]);});
                add(btn);
            }
 
            ArrowBtn backBtn = new ArrowBtn("left", btnColors[4]);
            backBtn.setBounds(50, 500, 120, 50);
            backBtn.addActionListener(e -> frame.showStartScreen());
 
            add(backBtn);
        }
    }
 
    // ==================== DIFFICULTY SCREEN ====================
 
    public static class DifficultyScreen extends JPanel {
        private final TetrisGame.TetrisFrame frame;
        private final GameModes.GameMode mode;
        public DifficultyScreen(TetrisGame.TetrisFrame frame, GameModes.GameMode mode) {
            this.frame = frame;
            this.mode = mode;
            setBackground(BG);
            setPreferredSize(new Dimension(550, 600));
            setLayout(null);
            RainbowText heading = new RainbowText("DIFFICULTY", 44);
            heading.setBounds(120, 50, 300, 60);
            add(heading);
            RoundBtn slowBtn = new RoundBtn("SLOW", btnColors[0]);
            slowBtn.setBounds(180, 150, 180, 55);
            slowBtn.addActionListener(e -> frame.startGame(mode, GameModes.Difficulty.SLOW));
            RoundBtn medBtn = new RoundBtn("MEDIUM", btnColors[1]);
            medBtn.setBounds(180, 230, 180, 55);
            medBtn.addActionListener(e -> frame.startGame(mode, GameModes.Difficulty.MEDIUM));
            RoundBtn fastBtn = new RoundBtn("FAST", btnColors[2]);
            fastBtn.setBounds(180, 310, 180, 55);
            fastBtn.addActionListener(e -> frame.startGame(mode, GameModes.Difficulty.FAST));
            ArrowBtn backBtn = new ArrowBtn("left", btnColors[4]);
            backBtn.setBounds(50, 500, 120, 50);
            backBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showModeSelect();});
            add(slowBtn);
            add(medBtn);
            add(fastBtn);
            add(backBtn);
        }
    }
 
    // ==================== HIGH SCORE SCREEN ====================
 
    public static class HighScoreScreen extends JPanel {
        private final TetrisGame.TetrisFrame frame;
        public HighScoreScreen(TetrisGame.TetrisFrame frame) {
            this.frame = frame;
            setBackground(BG);
            setPreferredSize(new Dimension(550, 600));
            setLayout(null);
            
            RainbowText heading = new RainbowText("HIGH SCORE", 44);
            heading.setBounds(120, 40, 300, 60);
            add(heading);
            
            // Get scores for each GAME MODE
            
            int standardScore   = TetrisGame.HighScoreManager.getHighScore(new GameModes.StandardMode());
            int fortyLinesScore = TetrisGame.HighScoreManager.getHighScore(new GameModes.FourtyLines());
            int timeTrialScore  = TetrisGame.HighScoreManager.getHighScore(new GameModes.TimeTrial());
            
            // Show GAME MODE names with their scores
            ScoreCard standardCard = new ScoreCard("STANDARD", standardScore, btnColors[0]);
            standardCard.setBounds(140, 140, 260, 55);
            add(standardCard);
            
            ScoreCard fortyCard = new ScoreCard("40 LINES", fortyLinesScore, btnColors[1]);
            fortyCard.setBounds(140, 210, 260, 55);
            add(fortyCard);
            
            ScoreCard timeCard = new ScoreCard("TIME TRIAL", timeTrialScore, btnColors[2]);
            timeCard.setBounds(140, 280, 260, 55);
            add(timeCard);

            ArrowBtn backBtn = new ArrowBtn("left", btnColors[4]);
            backBtn.setBounds(50, 500, 120, 50);
            backBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showStartScreen();});
            add(backBtn);
        }
    }
 
    // ==================== GAME OVER SCREEN ====================
 
    public static class GameOverScreen extends JPanel {
        private final TetrisGame.TetrisFrame frame;
        public GameOverScreen(TetrisGame.TetrisFrame frame, String[][] stats,
                              GameModes.GameMode mode, GameModes.Difficulty difficulty) {
            this.frame = frame;
            setBackground(BG);
            setPreferredSize(new Dimension(550, 600));
            setLayout(null);
            RainbowText heading = new RainbowText("GAME OVER", 46);
            heading.setBounds(120, 40, 300, 60);
            add(heading);
            int y = 130;
            for (int i = 0; i < stats.length && i < 5; i++) {
                if (stats[i] != null) {
                    int val = 0;
                    try { val = Integer.parseInt(stats[i][1].replaceAll("[^0-9]", "")); } catch(Exception e) {}
                    ScoreCard card = new ScoreCard(stats[i][0], val, btnColors[i % btnColors.length]);
                    card.setBounds(140, y, 260, 50);
                    add(card);
                    y += 70;
                }
            }
            RoundBtn replayBtn = new RoundBtn("PLAY AGAIN", btnColors[0]);
            replayBtn.setBounds(120, 450, 140, 50);
            replayBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.startGame(freshMode(mode), difficulty);});
            RoundBtn menuBtn = new RoundBtn("MENU", btnColors[1]);
            menuBtn.setBounds(280, 450, 120, 50);
            menuBtn.addActionListener(e -> {SoundEffects.onMenuClick();frame.showStartScreen();});
            add(replayBtn);
            add(menuBtn);
        }
        private GameModes.GameMode freshMode(GameModes.GameMode mode) {
            if (mode instanceof GameModes.StandardMode) return new GameModes.StandardMode();
            if (mode instanceof GameModes.FourtyLines) return new GameModes.FourtyLines();
            if (mode instanceof GameModes.TimeTrial) return new GameModes.TimeTrial();
            if (mode instanceof GameModes.ZenMode) return new GameModes.ZenMode();
            return new GameModes.StandardMode();
        }
    }
}