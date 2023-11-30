import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/*
 * Create Swing Application of Checkers game
 * This class houses the main window and game options.
 * It also calls the game functionality in Board.java
 */
class Checkers extends JPanel {
    static private Map<String, Integer> windowDimensions = new HashMap<>() {{
        put("width", Constants.defaultWindowWidth);
        put("height", Constants.defaultWindowHeight);
    }};
    private static int numRowsAndColumns = Constants.defaultNumRowsAndColumns;
    private static Color backgroundColor = Constants.backgroundColor;
    private final Board board = new Board(backgroundColor);


    private static JMenuBar menuBar;
    private static JMenu gameMenu, helpMenu, difficultyMenu;
    private static String humanVsHuman = Constants.difficultyLevels[0],
            computerVsHuman = Constants.computerVsHuman,
            computerVsComputer = Constants.computerVsComputer,
            computerDifficulty_Text = Constants.computerDifficulty_Text,
            difficultyLevel1 = Constants.difficultyLevels[1],
            difficultyLevel2 = Constants.difficultyLevels[2],
            difficultyLevel3 = Constants.difficultyLevels[3],
            difficultyLevel4 = Constants.difficultyLevels[4];
    // Integer difficulty levels
    private static int difficulty_ZERO = Constants.difficulty_ZERO,
            difficulty_Easy = Constants.difficulty_Easy,
            difficulty_Medium = Constants.difficulty_Medium,
            difficulty_Intermediate = Constants.difficulty_Intermediate,
            difficulty_Hard = Constants.difficulty_Hard;
    private String[] playersColorString = {"<html>Colors: ",
            "<font color=\"", "red", "\"><strong>", "Player 1", "</strong></font>",
            ", ",
            "<font color=\"", "black", "\"><strong>", "Player 2", "</strong></font>",
            "</html>"};
    private static JLabel messageToUser;
    private static ButtonGroup difGroup;
    private static JRadioButtonMenuItem easyDifficultyMenuItem, mediumDifficultyMenuItem, intermediateDifficultyMenuItem, hardDifficultyMenuItem;

    //Main JFrame window created here. Most of the work is passed into Board
    public static void main(String[] args) {
        JFrame window = new JFrame("Checkers");
        window.setSize(windowDimensions.get("width"), windowDimensions.get("height"));
        // Game Menu
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        Checkers content = new Checkers();
        window.setContentPane(content);


        window.pack();

        // Put Window in Center of Screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screenSize.width - window.getWidth()) / 2,
                (screenSize.height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }

    private Checkers() {
        setPreferredSize(new Dimension(windowDimensions.get("width"), windowDimensions.get("height")));

        setBackground(backgroundColor);

        /* Create the components and add them to the panel. */
        add(board);
        createMenuBar();

        board.setPreferredSize(new Dimension(windowDimensions.get("width"), windowDimensions.get("height")));
    }

    private void createMenuBar() {
        // Game Menu
        gameMenu = new JMenu("Game"); // Create a menu with name "Tools"

        JMenu newGameMenu = new JMenu("New");
        JMenuItem HumanVSHuman = new JMenuItem(humanVsHuman);
        JMenuItem ComputerVSHuman = new JMenuItem(computerVsHuman);
        JMenuItem ComputerVsComputer = new JMenuItem(computerVsComputer);
        newGameMenu.add(HumanVSHuman);
        newGameMenu.add(ComputerVSHuman);
        newGameMenu.add(ComputerVsComputer);
        gameMenu.add(newGameMenu);

        // Add New Game Actions
        HumanVSHuman.addActionListener(e -> {
            board.setComputerDifficulty(Constants.difficulty_ZERO);
            board.performDoNewGame();

            // update messageToUser & deselect difficulty level
            messageToUser.setText(humanVsHuman);
        });
        ComputerVSHuman.addActionListener(e -> {
            System.out.println("ADD AI");
            int difficulty = difficulty_ZERO;
            if (easyDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Easy;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel1);
            } else if (mediumDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Medium;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel2);
            } else if (intermediateDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Intermediate;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel3);
            } else if (hardDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Hard;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel4);
            }
            board.setSingleAI(true);
            board.setComputerDifficulty(difficulty);
            board.performDoNewGame();
        });
        ComputerVsComputer.addActionListener(e -> {
            System.out.println("ADD AI & AI");
            int difficulty = difficulty_ZERO;
            if (easyDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Easy;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel1);
            } else if (mediumDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Medium;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel2);
            } else if (intermediateDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Intermediate;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel3);
            } else if (hardDifficultyMenuItem.isSelected()) {
                difficulty = difficulty_Hard;
                messageToUser.setText(computerDifficulty_Text + difficultyLevel4);
            }
            board.setSingleAI(false);

            board.setComputerDifficulty(difficulty);
            board.performDoNewGame();
        });

        JMenuItem resignCommand = new JMenuItem("Resign"); // Create a menu item.
        // Add listener to menu item.
        resignCommand.addActionListener(e -> board.performDoResign());
        gameMenu.add(resignCommand); // Add menu item to menu.

        menuBar.add(gameMenu);


        // Difficulty Menu
        messageToUser = new JLabel(computerDifficulty_Text + difficultyLevel2); // Medium is Default Game Difficulty
        difficultyMenu = new JMenu("Difficulty");
        difficultyMenu.setMnemonic(KeyEvent.VK_F);

        difGroup = new ButtonGroup();

        easyDifficultyMenuItem = new JRadioButtonMenuItem(difficultyLevel1);
        easyDifficultyMenuItem.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!messageToUser.getText().contains(humanVsHuman)) {
                    messageToUser.setText(computerDifficulty_Text + difficultyLevel1);
                }
            }
        });

        mediumDifficultyMenuItem = new JRadioButtonMenuItem(difficultyLevel2);
        mediumDifficultyMenuItem.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!messageToUser.getText().contains(humanVsHuman)) {
                    messageToUser.setText(computerDifficulty_Text + difficultyLevel2);
                }
            }
        });

        intermediateDifficultyMenuItem = new JRadioButtonMenuItem(difficultyLevel3);
        intermediateDifficultyMenuItem.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!messageToUser.getText().contains(humanVsHuman)) {
                    messageToUser.setText(computerDifficulty_Text + difficultyLevel3);
                }
            }
        });

        hardDifficultyMenuItem = new JRadioButtonMenuItem(difficultyLevel4);
        hardDifficultyMenuItem.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!messageToUser.getText().contains(humanVsHuman)) {
                    messageToUser.setText(computerDifficulty_Text + difficultyLevel4);
                }
            }
        });


        mediumDifficultyMenuItem.setSelected(true);


        difficultyMenu.add(easyDifficultyMenuItem);
        difficultyMenu.add(mediumDifficultyMenuItem);
        difficultyMenu.add(intermediateDifficultyMenuItem);
        difficultyMenu.add(hardDifficultyMenuItem);

        difGroup.add(easyDifficultyMenuItem);
        difGroup.add(mediumDifficultyMenuItem);
        difGroup.add(intermediateDifficultyMenuItem);
        difGroup.add(hardDifficultyMenuItem);

        menuBar.add(difficultyMenu);


        // Help Menu
        helpMenu = new JMenu("Help");
        JMenuItem helpCommand = new JMenuItem("Game Instructions"); // Create a menu item.
        helpCommand.addActionListener(e -> showGameRulesScreen());

        JRadioButtonMenuItem toggleLegalMoveColorsCommand = new JRadioButtonMenuItem("Color Legal Moves"); // Create a menu item.
        toggleLegalMoveColorsCommand.addActionListener(e -> toggleLegalMoveColors());
        toggleLegalMoveColorsCommand.setSelected(false);

        JRadioButtonMenuItem playerColors = new JRadioButtonMenuItem(String.join("", playersColorString)); // Create a menu item.
        playerColors.addActionListener(e -> {
            playerColors(playerColors.isSelected());
            playerColors.setText(String.join("", playersColorString));
        });
        playerColors.setSelected(true);

        JRadioButtonMenuItem gameEndWindowToggle = new JRadioButtonMenuItem("Show Game Over Window"); // Create a menu item.
        gameEndWindowToggle.addActionListener(e -> gameEndWindowToggle());


        // Add menu items to menu.
        helpMenu.add(helpCommand);
        helpMenu.add(toggleLegalMoveColorsCommand);
        helpMenu.add(playerColors);
        helpMenu.add(gameEndWindowToggle);

        menuBar.add(helpMenu);
    }

    /**
     * This displays the game rules
     */
    private void showGameRulesScreen() {
        JOptionPane.showMessageDialog(null,
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "p {" +
                        "  width: " + windowDimensions.get("width") / 3 + "px;" +
                        "}" +
                        "ul {" +
                        "  width: " + windowDimensions.get("width") / 3 + "px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h2>Як грати:</h2>" +
                        "<p>Виберіть гравця, який буде йти першим. У свій хід перемістіть будь-яку зі своїх шашок за правилами руху, описаними нижче. " +
                        "Після того, як ви перемістите одну шашку, ваша черга закінчиться. Гра продовжується чергуванням гравців.</p>" +
                        "<h2>" +
                        "Правила руху:" +
                        "</h2>" +
                        "<ul>" +
                        "<li>Завжди рухайте свою шашку по діагоналі вперед, у бік ігрової дошки від суперника</li>" +
                        "<li>Після того як шашка стає «королем», вона повертається по діагоналі вперед або назад.</li>" +
                        "<li>Перемістіть свою шашку на одну клітинку по діагоналі до відкритого сусіднього квадрата.</li>" +
                        "<li>Перестрибніть одну або кілька шашок по діагоналі на відкрите поле поруч із захопленою вами шашкою.</li>" +
                        "<li>Якщо всі поля, що прилягають до вашої шашки, зайняті, ваша шашка заблокована і не може рухатися.</li>" +
                        "</ul>" +
                        "<h2>" +
                        "Захоплення шашки суперника:" +
                        "</h2>" +
                        "<p>" +
                        "Якщо ви можете перестрибнути шашку суперника, ви повинні її захопити. Потім зніміть його з ігрового поля та покладіть перед собою. " +
                        "Якщо можливий ще один стрибок, після першого потрібно стрибнути ще раз." +
                        "</p>" +
                        "<h2>" +
                        "Ставши Королем" +
                        "</h2>" +
                        "<p>" +
                        "Як тільки одна з ваших шашок досягає першого ряду на стороні вашого суперника ігрового поля, вона стає королем. " +
                        "Це представлено золотою короною. Тепер ця частина може рухатися вперед або назад." +
                        "</p>" +
                        "<h2>Як виграти:</h2>" +
                        "<p>" +
                        "Гравець, який першим захопить усі протилежні шашки з ігрового поля, виграє гру" +
                        "</p>" +
                        "</html>",
                "Інструкція з гри в шашки",
                JOptionPane.PLAIN_MESSAGE);
    }

    //This displays the valid moves for the user
    private void toggleLegalMoveColors() {
        board.toggleLegalMoveColors();
    }


    private void playerColors(boolean isSelected) {
        // Player 1 Color
        playersColorString[2] = isSelected ? "red" : "black";
        // Player 2 Color
        playersColorString[8] = isSelected ? "black" : "red";
        board.setPlayerTwoIsBlack(isSelected);
    }

    private void gameEndWindowToggle() {
        board.gameEndWindowToggle();
    }
}