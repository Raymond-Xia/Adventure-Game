/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaadventuregame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Raymond
 */
public class Adventure {
    JFrame introFrame;
    JPanel introPanel;
    JLabel introTitle1;
    JLabel introTitle2;
    JLabel introYeet;
    JButton playButton;
    
    JFrame frame;
    JPanel gridPanel;
    JPanel masterPanel;
    JMenuBar menubar;
    JMenu file, help;
    JMenuItem save, load, quit, controls, about;    
    JLabel[][] grid;
    private final int WIDTH = 15;
    private final int HEIGHT = 9;
    Color background;
    ImageIcon[] tiles;
    ImageIcon[][] walkIcons;
    ImageIcon[][] yeetIcons;
    JLabel infoText;
    Timer timeText;
    Timer timeYeet;
    Timer updateTimer;
    private int walkCount;
    private int yeetCount;
    Room currentRoom;
    Room[][] level;
    Player yeetKid;
    Player crackKid;
    ImageIcon[] crackKidIcons;
    Timer crackKidStep;
    Item yeet;    
    private int[][] maze;
    
    public Adventure() {
        frame = new JFrame("Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new MovementListener()); 
        
        /* Menu Bar */
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        file = new JMenu("File");
        save = new JMenuItem("Save");
        save.addActionListener(new MenuListener());
        save.setActionCommand("Save");
        file.add(save);
        load = new JMenuItem("Load");
        load.addActionListener(new MenuListener());
        load.setActionCommand("Load");
        file.add(load);
        quit = new JMenuItem("Quit");
        quit.addActionListener(new MenuListener());
        quit.setActionCommand("Quit");
        file.add(quit);
        menubar.add(file);

        help = new JMenu("Help");
        controls = new JMenuItem("Controls");
        controls.addActionListener(new MenuListener());
        controls.setActionCommand("Controls");
        help.add(controls);
        about = new JMenuItem("About");
        about.addActionListener(new MenuListener());
        about.setActionCommand("About");
        help.add(about);
        menubar.add(help);
        
        /* Master Panel */
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridBagLayout());
//        masterPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        masterPanel.setBackground(Color.DARK_GRAY);
//        GridBagConstraints c = new GridBagConstraints();
        
//        introTitle1 = new JLabel("The Adventures of");
//        Font f = new Font("Impact", Font.PLAIN, 24);
//        introTitle1.setFont(f);
//        introTitle1.setForeground(Color.WHITE);
//        c.gridx = 0;
//        c.gridy = 0;        
//        c.gridwidth = 3;      
//        c.gridheight = 1;      
//        masterPanel.add(introTitle1, c);
//                
//        introTitle2 = new JLabel("YEET KID");
//        f = new Font("Impact", Font.PLAIN, 100);
//        introTitle2.setFont(f);
//        introTitle2.setForeground(Color.YELLOW);
//        c.gridx = 1;
//        c.gridy = 1;        
//        c.gridwidth = 1;   
//        c.gridheight = 1; 
//        masterPanel.add(introTitle2, c);
//        
//        introYeet = new JLabel(new ImageIcon("yeetR2.png"));
//        c.gridx = 1;
//        c.gridy = 2;        
//        c.gridwidth = 1;   
//        c.gridheight = 1; 
//        c.insets = new Insets(0,0,10,0);
//        masterPanel.add(introYeet, c);
//        
//        playButton = new JButton("Play");
//        playButton.addActionListener(this);
//        f = new Font("Century Gothic", Font.PLAIN, 24);
//        playButton.setFont(f);        
//        c.gridx = 1;
//        c.gridy = 3;        
//        c.gridwidth = 1;   
//        c.gridheight = 1;   
//        masterPanel.add(playButton, c);
        
        
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        introTitle1.setVisible(false);
//        introTitle2.setVisible(false);
//        introYeet.setVisible(false);
//        playButton.setVisible(false);
        
               
        GridBagConstraints c = new GridBagConstraints();
        
        /* Game Grid */
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(HEIGHT,WIDTH,0,0));
        
        grid = new JLabel[HEIGHT][WIDTH];
        background = new Color(255,216,160);
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = new JLabel();
                grid[row][column].setPreferredSize(new Dimension(50,50));
                grid[row][column].setOpaque(true);
                grid[row][column].setBackground(background);
                gridPanel.add(grid[row][column]);
            }
        }
        
        /* Create rooms */
        tiles = new ImageIcon[9];
        tiles[0] = null;
        tiles[1] = new ImageIcon("img/treeB.png");
        tiles[2] = new ImageIcon("img/treeT.png");
        tiles[3] = new ImageIcon("img/treeBL.png");
        tiles[4] = new ImageIcon("img/treeBR.png");
        tiles[5] = new ImageIcon("img/treeTL.png");
        tiles[6] = new ImageIcon("img/treeTR.png");
        tiles[7] = new ImageIcon("img/jeff.png");
        tiles[8] = new ImageIcon("img/yeet.png");
        
        level = new Room[1][4];      
        
        level[0][0] = new Room(new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 2},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 4, 1, 1, 1},
            {1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 4, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1},
        }, 0, 0, new String[]{"You awake in the middle of the forest.", "<html><b>Yeet Kid:</b> Whoa! Where am I?</html>", "Find your way out of the forest."});
        
        maze = new int[HEIGHT+2][WIDTH+2];
        for (int row = 0; row < maze.length; row++) {
            for (int column = 0; column < maze[row].length; column++) {
                maze[row][column] = 1;
            }
        }
        for (int row = 2; row < maze.length-2; row++) {
            for (int column = 2; column < maze[row].length-2; column++) {
                maze[row][column] = 2;
            }
        }
        generate(new Cell(2, 2));
        
        int[][] tempMap = new int[HEIGHT][WIDTH];
        for (int row = 1; row < maze.length-1; row++) {
            for (int column = 1; column < maze[row].length-1; column++) {
                if (maze[row][column] == 2 && maze[row-1][column] != 0) {
                    tempMap[row-1][column-1] = 1;
                } else {
                    tempMap[row-1][column-1] = maze[row][column];
                }
            }
        }
        tempMap[1][0] = 0;
        tempMap[HEIGHT-2][WIDTH-1] = 0;
        
        level[0][1] = new Room(tempMap, 1, 0, new String[]{"Find your way out of the forest."});
                
        level[0][2] = new Room(new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
            {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1},
        }, 2, 0, new String[]{"<html><b>Jeff</b>: Hey! You!</html>", "<html><b>Jeff</b>: It's dangerous to go alone! Take this.</html>"}, new Item[]{new Item(WIDTH/2, HEIGHT/2, false, tiles[8])});
        
        level[0][3] = new Room(new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1},
            {1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1},
        }, 3, 0, new String[]{"<html><b>Crack Kid</b>: Hey!</html>", "<html><b>Crack Kid</b>: This is your last yeet, Yeet Kid!</html>", "<html><b>Crack Kid</b>: You're goin' down!</html>"});
        
        currentRoom = level[0][0];
        
        /* Create characters */        
        walkIcons = new ImageIcon[][] {
            {new ImageIcon("img/walkL1.png"), new ImageIcon("img/walkL2.png")},
            {new ImageIcon("img/walkR1.png"), new ImageIcon("img/walkR2.png")},
        };        
        walkCount = 0;
        
        yeetIcons = new ImageIcon[][] {
            {new ImageIcon("img/yeetL1.png"), new ImageIcon("img/yeetL2.png"), new ImageIcon("img/yeetL3.png")},
            {new ImageIcon("img/yeetR1.png"), new ImageIcon("img/yeetR2.png"), new ImageIcon("img/yeetR3.png")},
        };
        yeetCount = 0;
        timeYeet = new Timer(250, new YeetListener());        
        
        yeetKid = new Player(WIDTH/2, HEIGHT/2, true, 0, walkIcons[1][0]);
                
        crackKidIcons = new ImageIcon[]{new ImageIcon("img/crackKidL.png"), new ImageIcon("img/crackKidR.png")};
        crackKid = new Player(WIDTH/2,HEIGHT/2, false, 1, crackKidIcons[0]);
        crackKidStep = new Timer(1000, new CrackKidListener());
        crackKidStep.setRepeats(true);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;        
//        c.insets = new Insets(3,3,3,3);
        masterPanel.add(gridPanel, c);
        
        /* Text info box */
        Font f = new Font("Courier New", Font.PLAIN, 16);
        infoText = new JLabel();
        infoText.setFont(f);
        infoText.setOpaque(true);
        infoText.setBackground(Color.WHITE);
        infoText.setHorizontalAlignment(JLabel.CENTER);
        infoText.setBorder(BorderFactory.createLineBorder(Color.BLACK,3,true));
        
        timeText = new Timer(3000, new TextListener());
        timeText.setInitialDelay(0);
        timeText.setRepeats(true);
        timeText.start();
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 60;
        masterPanel.add(infoText, c);
                
        /* Game updater */
        updateTimer = new Timer(20, new UpdateListener());
        updateTimer.setRepeats(true);
        updateTimer.start();
        
        frame.setContentPane(masterPanel);
        frame.pack();
//        frame.setAlwaysOnTop(true);
        frame.setSize(frame.getWidth(), frame.getHeight()+19);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
    }
    
    class MenuListener implements ActionListener, KeyListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String menuItem = e.getActionCommand();
            switch (menuItem) {
                case "Save":
                    JFrame saveWindow = new JFrame("Save");
                    saveWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    saveWindow.addKeyListener(this);
                    JPanel saveContent = new JPanel();
                    
                    File gameFile = new File("gameSave.txt");
                    FileOutputStream out;
                    ObjectOutputStream writeObjects;
                    
                    try {
                        /* Write objects */
                        out = new FileOutputStream(gameFile);
                        writeObjects = new ObjectOutputStream(out);  
                        writeObjects.writeObject(level);
                        writeObjects.writeObject(currentRoom);
                        writeObjects.writeObject(yeetKid);
                        writeObjects.writeObject(crackKid);
                        writeObjects.close();
                        out.close();
                        
                        JLabel saveText = new JLabel("Game saved.");
                        saveText.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                        saveContent.add(saveText);
                    } catch (FileNotFoundException error) {
                        System.err.println("FileNotFoundException: " + error.getMessage());
                    } catch (IOException error) {
                        System.err.println("IOException: " + error.getMessage());
                    }
                    
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    saveWindow.setContentPane(saveContent);
                    saveWindow.pack();
                    saveWindow.setBounds(screenSize.width / 2 - saveWindow.getWidth() / 2, screenSize.height / 2 - saveWindow.getHeight() / 2, saveWindow.getWidth(), saveWindow.getHeight());
                    saveWindow.setVisible(true);
                    break;
                case "Load":
                    gameFile = new File("gameSave.txt");
                    FileInputStream in;
                    ObjectInputStream readObjects;
                    
                    if (gameFile.exists()) {
                        try {
                            /* Read objects */
                            in = new FileInputStream(gameFile);
                            readObjects = new ObjectInputStream(in);  
                            level = (Room[][])readObjects.readObject();
                            Room savedRoom = (Room)readObjects.readObject();
                            setRoom(savedRoom.getX(), savedRoom.getY());
                            yeetKid = (Player)readObjects.readObject();
                            crackKid = (Player)readObjects.readObject();
                            readObjects.close();
                            in.close();

                        } catch (ClassNotFoundException error) {
                            System.err.println("ClassNotFoundException: " + error.getMessage());
                        } catch (FileNotFoundException error) {
                            System.err.println("FileNotFoundException: " + error.getMessage());
                        } catch (IOException error) {
                            System.err.println("IOException: " + error.getMessage());
                        }
                    } 
                    break;
                case "Quit":
                    System.exit(0);
                    break;
                case "Controls":
                    JFrame controlsWindow = new JFrame("Controls");
                    controlsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    controlsWindow.addKeyListener(this);
                    JPanel controlsContent = new JPanel();

                    JLabel controlsText = new JLabel("<html>Use arrow keys to move.<br>Press space to yeet.</html>");
                    controlsText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    controlsContent.add(controlsText);

                    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    controlsWindow.setContentPane(controlsContent);
                    controlsWindow.pack();
                    controlsWindow.setBounds(screenSize.width / 2 - controlsWindow.getWidth() / 2, screenSize.height / 2 - controlsWindow.getHeight() / 2, controlsWindow.getWidth(), controlsWindow.getHeight());
                    controlsWindow.setVisible(true);
                    break;
                case "About":
                    JFrame aboutWindow = new JFrame("About");
                    aboutWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    aboutWindow.addKeyListener(this);
                    JPanel aboutContent = new JPanel();

                    JLabel aboutText = new JLabel("Written by Raymond Xia. All rights reserved.");
                    aboutText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    aboutContent.add(aboutText);

                    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    aboutWindow.setContentPane(aboutContent);
                    aboutWindow.pack();
                    aboutWindow.setBounds(screenSize.width / 2 - aboutWindow.getWidth() / 2, screenSize.height / 2 - aboutWindow.getHeight() / 2, aboutWindow.getWidth(), aboutWindow.getHeight());
                    aboutWindow.setVisible(true);
                    break;
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER) {
                e.getComponent().setVisible(false);
            }
        }
        
        @Override
        public void keyTyped(KeyEvent e) {   
        }        
        @Override
        public void keyReleased(KeyEvent e) {   
        }
    }
    
    class MovementListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (yeetKid.isAlive() && !timeText.isRunning()) {
                switch (key) {
                    case KeyEvent.VK_LEFT:
                        if (!timeYeet.isRunning()) {
                            yeetKid.setDirection(0);
                            if (yeetKid.getX() == 0) {
                                yeetKid.setX(WIDTH-1);     
                                setRoom(currentRoom.getX()-1,currentRoom.getY());
                            } else if (currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()-1] == 0 || currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()-1] == 8) {
                                yeetKid.setX(yeetKid.getX()-1); 
                                grid[yeetKid.getY()][yeetKid.getX()].setIcon(null);
                            }

                            yeetKid.setIcon(walkIcons[0][walkCount]);                            
                            if (walkCount == 0) {
                                walkCount = 1;
                            } else {
                                walkCount = 0;
                            }
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!timeYeet.isRunning()) {
                            yeetKid.setDirection(1);
                            if (yeetKid.getX() == WIDTH-1) {
                                yeetKid.setX(0);                                
                                setRoom(currentRoom.getX()+1,currentRoom.getY());
                            } else if (currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()+1] == 0 || currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()+1] == 8) {
                                yeetKid.setX(yeetKid.getX()+1); 
                                grid[yeetKid.getY()][yeetKid.getX()].setIcon(null);                           
                            }

                            yeetKid.setIcon(walkIcons[1][walkCount]);
                            if (walkCount == 0) {
                                walkCount = 1;
                            } else {
                                walkCount = 0;
                            }
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!timeYeet.isRunning()) {
                            if (yeetKid.getY() == 0) {
                                yeetKid.setY(HEIGHT-1);
                                setRoom(currentRoom.getX(),currentRoom.getY()-1);
                            } else if (currentRoom.getMap()[yeetKid.getY()-1][yeetKid.getX()] == 0 || currentRoom.getMap()[yeetKid.getY()-1][yeetKid.getX()] == 8) {
                                yeetKid.setY(yeetKid.getY()-1);
                                grid[yeetKid.getY()][yeetKid.getX()].setIcon(null);
                            }
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!timeYeet.isRunning()) {
                            if (yeetKid.getY() == HEIGHT-1) {
                                yeetKid.setY(0);
                                setRoom(currentRoom.getX(),currentRoom.getY()+1);  
                            } else if (currentRoom.getMap()[yeetKid.getY()+1][yeetKid.getX()] == 0 || currentRoom.getMap()[yeetKid.getY()+1][yeetKid.getX()] == 8) {
                                yeetKid.setY(yeetKid.getY()+1);
                                grid[yeetKid.getY()][yeetKid.getX()].setIcon(null);
                            }            
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (yeetKid.getItem(0) != null && yeetKid.getItem(0).isObtained()) {
                            if (!timeYeet.isRunning()) {
                                timeYeet.start();
                            }
                        }
                        break;
                } 
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
    
    class TextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            infoText.setText(currentRoom.getDialogue()[currentRoom.getDialogueLine()]);
            if (currentRoom == level[0][2] && currentRoom.getDialogueLine() == 1 && !currentRoom.getItems()[0].isObtained()) {
                currentRoom.getItems()[0].setVisible(true);
            }
            if (currentRoom.getDialogueLine() == currentRoom.getDialogue().length-1) {
                timeText.stop();
            } else {
                currentRoom.setDialogueLine(currentRoom.getDialogueLine()+1);
            }
        }
    }
    
    class YeetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (yeetCount < 3) {
                yeetKid.setIcon(yeetIcons[yeetKid.getDirection()][yeetCount]); 
                if (yeetCount == 1) {
                    if (yeetKid.getDirection() == 0 && yeetKid.getX() > 0) { 
                        if (currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()-1] > 6) {
                            currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()-1] = 0;
                            grid[yeetKid.getY()][yeetKid.getX()-1].setIcon(null);    
                        }
                        if (yeetKid.getX() - 1 == crackKid.getX() && yeetKid.getY() == crackKid.getY() && crackKid.isVisible()) {
                            crackKidStep.stop();
                            crackKid.setAlive(false);
                        }
                    } else if (yeetKid.getDirection() == 1 && yeetKid.getX() < WIDTH - 1) {  
                        if (currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()+1] > 6) {
                            currentRoom.getMap()[yeetKid.getY()][yeetKid.getX()+1] = 0;
                            grid[yeetKid.getY()][yeetKid.getX()+1].setIcon(null);
                        }
                        if (yeetKid.getX() + 1 == crackKid.getX() && yeetKid.getY() == crackKid.getY() && crackKid.isVisible()) {
                            crackKidStep.stop();
                            crackKid.setAlive(false);
                        }
                    }
                }
                yeetCount++;
            } else {                
                timeYeet.stop();
                walkCount = 0;
                yeetKid.setIcon(walkIcons[yeetKid.getDirection()][1]);
                yeetCount = 0;
            }
        }
    }
    
    class CrackKidListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!timeText.isRunning()) {
                if (crackKid.getX() < yeetKid.getX()) {
                    crackKid.setDirection(1);
                    crackKid.setX(crackKid.getX()+1);
                    crackKid.setIcon(crackKidIcons[1]);
                } else if (crackKid.getX() > yeetKid.getX()){
                    crackKid.setDirection(0);
                    crackKid.setX(crackKid.getX()-1);
                    crackKid.setIcon(crackKidIcons[0]);
                } 
                if (crackKid.getY() < yeetKid.getY()) {
                    crackKid.setY(crackKid.getY()+1);
                } else if (crackKid.getY() > yeetKid.getY()){
                    crackKid.setY(crackKid.getY()-1);
                }
            }
        }
    }
    
    class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (yeetKid.isAlive()) {
                drawRoom();
                drawCharacter(yeetKid);
                checkItems();
                if (crackKid.isAlive()) {
                    if (crackKid.isVisible()) {                        
                        drawCharacter(crackKid);
                        if (crackKid.equals(yeetKid)) {
                            yeetKid.setAlive(false);
                        }
                    }
                } else {
                    endGame();
                }
            } else {
                endGame();              
            }
        }
    }
       
    private void checkItems() {
        for (int i = 0; i < currentRoom.getItems().length; i++) {
            if (currentRoom.getItems()[i].isVisible()) {
                if (yeetKid.equals(currentRoom.getItems()[i])) {
                    currentRoom.getMap()[currentRoom.getItems()[i].getY()][currentRoom.getItems()[i].getX()] = 0;
                    yeetKid.addItem(currentRoom.getItems()[i], 0);
                    infoText.setText("Press SPACEBAR to hit that YEET!");  
                }
            }
        }
    }    
    
    private Cell generate(Cell c) {
        int x = c.getX();
        int y = c.getY();
        int newX = x;
        int newY = y;
        Random r = new Random();
        ArrayList neighbours = new ArrayList();
//        System.out.println(x + " " + y);
        maze[y][x] = 0;
        if(x == maze[0].length-3 & y == maze.length-3) {
            return new Cell(x,y);
        } else {
            if (maze[y-2][x] == 2) {
                neighbours.add(new Cell(x,y-2));
            }
            if (maze[y][x-2] == 2) {
                neighbours.add(new Cell(x-2,y));
            }
            if (maze[y][x+2] == 2) {
                neighbours.add(new Cell(x+2,y));
            }
            if (maze[y+2][x] == 2) {
                neighbours.add(new Cell(x,y+2));
            }
            if (!neighbours.isEmpty()) {
                Cell randNeighbour = (Cell)neighbours.get(r.nextInt(neighbours.size())); 
                newX = randNeighbour.getX();
                newY = randNeighbour.getY();
            } else {
                if(x<maze[0].length-3) {
                    newX+=2;
                } else {
                    newY+=2;
                }
            }
            maze[y+(newY-y)/2][x+(newX-x)/2] = 0;
            return(generate(new Cell(newX,newY)));
        }       
    }
    
    private void setRoom(int x, int y) {
        if (timeText.isRunning()) {
            timeText.stop();
        }
        currentRoom = level[y][x];
        timeText.restart();
        
        if (currentRoom == level[0][3] && crackKid.isAlive()) {
            crackKidStep.start();
            crackKid.setVisible(true);
        } else {
            crackKidStep.stop();
            crackKid.setVisible(false);
        }
    }
    
    private void drawCharacter(Player p) {
        grid[p.getY()][p.getX()].setIcon(p.getIcon());
    }
    
    private void drawRoom() {
        int[][] map = currentRoom.getMap();
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[row].length; column++) {
                grid[row][column].setIcon(tiles[map[row][column]]);
            }
        }
        for (int i = 0; i < currentRoom.getItems().length; i++) {
            if (currentRoom.getItems()[i].isVisible()){
                grid[currentRoom.getItems()[i].getY()][currentRoom.getItems()[i].getX()].setIcon(currentRoom.getItems()[i].getIcon());
            }
        }
    }
    
    private void endGame() {
        gridPanel.setVisible(false);
        infoText.setVisible(false);
        updateTimer.stop();
        GridBagConstraints c = new GridBagConstraints();
        Font f = new Font("Impact", Font.PLAIN, 72);
        JLabel endGameText1 = new JLabel();
        JLabel endGameText2 = new JLabel();
        endGameText1.setFont(f);
        endGameText1.setForeground(Color.WHITE);
        endGameText2.setForeground(Color.WHITE);
        
        if (yeetKid.isAlive()) { 
            endGameText1.setText("YOU WON");
            c.gridx = 0;
            c.gridy = 0;
            masterPanel.add(endGameText1, c);
            
            endGameText2.setText("You yeeted on Crack Kid");
            c.gridx = 0;
            c.gridy = 1;
            masterPanel.add(endGameText2, c);            
        } else {
            endGameText1.setText("GAME OVER");
            c.gridx = 0;
            c.gridy = 0;
            masterPanel.add(endGameText1, c);
            
            endGameText2.setText("You were beat by Crack Kid");
            c.gridx = 0;
            c.gridy = 1;
            masterPanel.add(endGameText2, c);
        }
        masterPanel.setBackground(Color.BLACK);
        masterPanel.update(masterPanel.getGraphics());
    }
    
    private static void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        Adventure game = new Adventure();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }
    
}