/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComparisonAlgo.src.com.ducphamle.algo.view;

import ComparisonAlgo.src.com.ducphamle.algo.IO.InputChecking;
import ComparisonAlgo.src.com.ducphamle.algo.controller.AlgoController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Duc Pham Le
 */
public class AlgoView {

    /* Define components */
    private JButton bExecute = null; //execute button
    private JFrame frame = null; //our frame
    private JLabel setSize = null; //"size" label text
    private JLabel algo = null; //"Algorithm" label text
    private JLabel size = null; //"The size of the set ..." label text
    private JLabel insertionT = null; //algo insertion sort time text
    private JLabel mergeT = null; //algo merge sort time text
    private JLabel bubbleT = null; //algo bubble sort time text
    private JLabel customSize = null; //custom size text
    private JTextField customSizeText = null; //text field for custom size
    private JComboBox<Integer> cbSize = null; //combobox for size
    private JComboBox cbSort = null; //combobox for sorting algorithms
    private JMenuBar menuBar = null; //create a menu bar for the app
    private JMenu menu = null; //create menu inside the menu bar
    private JMenuItem aboutItem = null; //a JMenuItem 'about' - explain about the app
    private JMenuItem colorItem = null; //'change color' item - for users to change colors if they wish
    private JMenuItem exitItem = null; //exit item to exit the program

    /* Define value String */
    private final String INSERTION = "Insertion sort";
    private final String BUBBLE = "Bubble sort";
    private final String MERGE = "Merge sort";
    private final String RED = "Red", GREEN = "Green", YELLOW = "Yellow", BLUE = "Blue", WHITE = "White", BLACK = "Black";
    private final String[] algoName = {INSERTION, BUBBLE, MERGE};
    private final String[] colorName = {RED, GREEN, YELLOW, BLUE, WHITE, BLACK};
    private StringBuilder sizeText = null; //catch the previous custom text

    /* Define Object */
    private AlgoController controller = null;
    private ViewRun viewRun = null;
    private ActionListener menuItemListener = null; //created for catching events of JMenuItems
    private KeyListener keyListener = null; //created for using hot keys in the app
    
    boolean s1=false;
    boolean s2=false;

    /* Create objects */
    private void dataInit() {
        //controller init
        controller = new AlgoController();
        controller.init();

        viewRun = new ViewRun();

        //Swing init
        frame = new JFrame();
        controller = new AlgoController();
        setSize = new JLabel("Set size");
        cbSize = new JComboBox();
        cbSort = new JComboBox(algoName);
        algo = new JLabel("Algorithm");
        customSize = new JLabel("Custom size");
        customSize.setToolTipText(" Custom size for merge sort only "); //it's like a runtime comment for user
        customSizeText = new JTextField("");
        customSizeText.setEnabled(false);
        size = new JLabel();
        insertionT = new JLabel();
        mergeT = new JLabel();
        bubbleT = new JLabel();
        bExecute = new JButton("Execute");
        sizeText = new StringBuilder("");
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        aboutItem = new JMenuItem("About");
        colorItem = new JMenuItem("Change color");
        exitItem = new JMenuItem("Exit");
        exitItem.setToolTipText("Alt + F4");

        /* ActionListener init */
        menuItemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItemPerformed(e);
            }
        };

        /* KeyListener init */
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ALT) 
                    s1 = true;
                if (e.getKeyCode() == KeyEvent.VK_F4)
                    s2 = true;
                if (s1 == true && s2 == true) 
                    System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                s1 = false;
                s2 = false;
            }
        };
    }

    /**
     * <b><i>menuItemPerformed</i></b>
     * <p>
     * private void menuItemPerformed(ActionEvent e)
     * <p>
     * This method is used to implement action listener of menuItemListener
     *
     * @param e - action event that occurred
     */
    private void menuItemPerformed(ActionEvent e) {
        if (e.getSource() == aboutItem) {
            JOptionPane.showMessageDialog(null, String.format("Version: 1.2\nDeveloper: Pham Le Duc\n"));
        }
        if (e.getSource() == colorItem) {
            String color = (String) JOptionPane.showInputDialog(null, "Choose a color to change: ", "Change color",
                    JOptionPane.INFORMATION_MESSAGE, null, colorName, BLACK);
            if (color != null) {
                setJLabelColor(color);
            }
        }
        if (e.getSource() == exitItem) {
            JOptionPane.showMessageDialog(null, "Bye, and hope to see you again ^^");
            System.exit(0);
        }
    }

    /* Define constructor */
    public AlgoView() {
        dataInit();
    }

    /**
     * <b><i>setResult</i></b>
     * <p>
     * protected void setResult(JLabel label, String name, double time)
     * <p>
     * This methods is used to change result output depending on the parameters
     * passed
     *
     * @param label - JLabel that we want to fix text
     * @param name - name of the algorithm
     * @param time - sorting time of that algorithm
     */
    protected void setResult(JLabel label, String name, double time) {
        label.setText("The time of the " + name + " algorithm is " + time + "ms");
    }

    /**
     * <b><i>buildFrame</i></b>
     * <p>
     * protected void buildFrame()
     * <p>
     * This method is used to set up frame and UI.
     */
    protected void buildFrame() {
        controller.arraySizeGen();

        //build frame
        frame.setBounds(700, 300, 600, 400);
        frame.setResizable(false);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //build combobox
        Integer[] temp = new Integer[controller.getArraySize().size()];
        temp = controller.arrCopy(controller.getArraySize()).toArray(temp);
        for (Integer temp1 : temp) {
            cbSize.addItem(temp1);
        }
        cbSize.setBounds(220, 37, 200, 20);
        cbSort.setBounds(220, 87, 200, 20);

        int sizeTemp = (int) cbSize.getSelectedItem();
        controller.arrGen(sizeTemp);
        controller.setSortAlgoInfo(sizeTemp);

        //build JLabel
        setSize.setBounds(100, 30, 60, 30);
        algo.setBounds(100, 80, 70, 30);
        customSize.setBounds(100, 130, 70, 30);
        size.setBounds(50, 180, 200, 30);
        insertionT.setBounds(50, 210, 300, 30);
        bubbleT.setBounds(50, 240, 300, 30);
        mergeT.setBounds(50, 270, 300, 30);

        size.setText("The size of the set is " + controller.getArraySizeElement(0));
        viewRun.resetResult();

        //build JTextField
        customSizeText.setBounds(220, 137, 200, 20);

        //build JButtons
        bExecute.setBounds(319, 170, 100, 30);

        //build menu
        aboutItem.addActionListener(menuItemListener);
        colorItem.addActionListener(menuItemListener);
        exitItem.addActionListener(menuItemListener); //catch event in menu item (when click 'exit')
        menu.add(aboutItem);
        menu.add(colorItem);
        menu.addSeparator();
        menu.add(exitItem);
        menuBar.add(menu);

        //add into frame
        frame.add(setSize);
        frame.add(algo);
        frame.add(size);
        frame.add(cbSize);
        frame.add(cbSort);
        frame.add(customSizeText);
        frame.add(insertionT);
        frame.add(mergeT);
        //frame.add(quickT);
        frame.add(bubbleT);
        frame.add(customSize);
        frame.add(bExecute);
        frame.setFocusable(true);
        frame.addKeyListener(keyListener);
        frame.setVisible(true);
    }

    /**
     * <b><i>removeTimeChanged</i></b>
     * <p>
     * protected void removeTimeChanged(int sizeTemp)
     * <p>
     * This method is used to reset all the sorting time to 0 when changing the
     * size.
     *
     * @param sizeTemp - size that user changes
     */
    protected void removeTimeChanged(int sizeTemp) {
        size.setText("The size of the set is " + sizeTemp);
        viewRun.resetResult();
    }

    /**
     * <b><i>JOptionPaneMessageDialog</i></b>
     * <p>
     * protected void JOptionPaneMessageDialog(String algo)
     * <p>
     * This method is used to give out message dialog when users try to execute
     * a time sorting algorithm more than once.
     *
     * @param algo - name of the algorithm
     */
    protected void jOptionPaneMessageDialog(String algo) {
        JOptionPane.showMessageDialog(null, "You have generated " + algo + " algorithm already");
    }

    protected void setJLabelColor(String color) {
        switch (color) {
            case RED:
                setSize.setForeground(Color.RED);
                algo.setForeground(Color.RED);
                insertionT.setForeground(Color.RED);
                mergeT.setForeground(Color.RED);
                bubbleT.setForeground(Color.RED);
                size.setForeground(Color.RED);
                customSize.setForeground(Color.RED);
                break;
            case GREEN:
                setSize.setForeground(Color.GREEN);
                algo.setForeground(Color.GREEN);
                insertionT.setForeground(Color.GREEN);
                mergeT.setForeground(Color.GREEN);
                bubbleT.setForeground(Color.GREEN);
                size.setForeground(Color.GREEN);
                customSize.setForeground(Color.GREEN);
                break;
            case YELLOW:
                setSize.setForeground(Color.YELLOW);
                algo.setForeground(Color.YELLOW);
                insertionT.setForeground(Color.YELLOW);
                mergeT.setForeground(Color.YELLOW);
                bubbleT.setForeground(Color.YELLOW);
                size.setForeground(Color.YELLOW);
                customSize.setForeground(Color.YELLOW);
                break;
            case BLACK:
                setSize.setForeground(Color.BLACK);
                algo.setForeground(Color.BLACK);
                insertionT.setForeground(Color.BLACK);
                mergeT.setForeground(Color.BLACK);
                bubbleT.setForeground(Color.BLACK);
                size.setForeground(Color.BLACK);
                customSize.setForeground(Color.BLACK);
                break;
            case WHITE:
                setSize.setForeground(Color.WHITE);
                algo.setForeground(Color.WHITE);
                insertionT.setForeground(Color.WHITE);
                mergeT.setForeground(Color.WHITE);
                bubbleT.setForeground(Color.WHITE);
                size.setForeground(Color.WHITE);
                customSize.setForeground(Color.WHITE);
                break;
            case BLUE:
                setSize.setForeground(Color.BLUE);
                algo.setForeground(Color.BLUE);
                insertionT.setForeground(Color.BLUE);
                mergeT.setForeground(Color.BLUE);
                bubbleT.setForeground(Color.BLUE);
                size.setForeground(Color.BLUE);
                customSize.setForeground(Color.BLUE);
                break;
            default:
                break;
        }
    }

    /**
     * <b><i>run</i></b>
     * <p>
     * public void run()
     * <p>
     * This method is used to sequentially build frame, generate algorithm model
     * and run the program.
     */
    public void run() {
        buildFrame();
        viewRun.sizeItemListenerPerformed();
        viewRun.customTextEnabled();
        viewRun.buttonExecutionPerformed();
    }

    class ViewRun {

        /**
         * <b><i>resetResult</i></b>
         * <p>
         * protected void resetResult()
         * <p>
         * This method is used to reset all the sorting algorithm to default.
         */
        protected void resetResult() {
            setResult(insertionT, INSERTION, 0);
            setResult(bubbleT, BUBBLE, 0);
            setResult(mergeT, MERGE, 0);
        }

        /**
         * <b><i>buttonExecutionPerformed</i></b>
         * <p>
         * protected void buttonExecutionPerformed()
         * <p>
         * This method is used for execute button to get action listener.
         */
        protected void buttonExecutionPerformed() {
            bExecute.addActionListener((ActionEvent e) -> {
                executeTime();
            });
        }

        /**
         * <b><i>genModel</i></b>
         * <p>
         * protected void genModel()
         * <p>
         * This method is used to get item listener when users change size of
         * array.
         */
        protected void sizeItemListenerPerformed() {
            controller.assignCheckSort();
            int currentSize = (int) cbSize.getSelectedItem();
            cbSize.addItemListener((ItemEvent evt) -> {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    controller.removeAllTime(currentSize);
                    int sizeTemp = (int) cbSize.getSelectedItem();
                    controller.setSortAlgoInfo(sizeTemp);
                    removeTimeChanged(sizeTemp);
                    controller.resetCheckSort();
                }
            });
        }

        /**
         * <b><i>customTextEnabled</i></b>
         * <p>
         * protected void customTextEnabled()
         * <p>
         * This method is used to enable or disable custom size for merge algo.
         */
        protected void customTextEnabled() {
            String currentAlgoText = (String) cbSort.getSelectedItem();
            if (currentAlgoText.equals(MERGE)) {
                customSizeText.setEnabled(true);
                customSizeText.requestFocus(); //used to focus on that textfield for users
            } else {
                cbSort.addItemListener((ItemEvent evt) -> {
                    if (evt.getStateChange() == ItemEvent.SELECTED) {
                        if (cbSort.getSelectedItem().equals(MERGE)) {
                            customSizeText.setEnabled(true);
                            customSizeText.requestFocus();
                        } else {
                            customSizeText.setEnabled(false);
                        }
                    }
                });
            }
        }

        /**
         * <b><i>executeTime</i></b>
         * <p>
         * protected void executeTime()
         * <p>
         * This method is used to check algorithm choice from user and calculate
         * the timing for sorting of each algo.
         */
        protected void executeTime() {
            String temp = (String) cbSort.getSelectedItem();
            int sizeTemp = (int) cbSize.getSelectedItem();
            controller.arrGen(sizeTemp);
            switch (temp) {
                case INSERTION:
                    if (controller.checkSortValue(INSERTION) == 0) {
                        controller.setTimeInsertion(sizeTemp);
                        setResult(insertionT, INSERTION, controller.getAlgoModel().getInsertionTime());
                        controller.setCheckSort(INSERTION);
                    } else {
                        jOptionPaneMessageDialog(INSERTION);
                    }
                    break;
                case BUBBLE:
                    if (controller.checkSortValue(BUBBLE) == 0) {
                        controller.setTimeBubble(sizeTemp);
                        setResult(bubbleT, BUBBLE, controller.getAlgoModel().getBubbleTime());
                        controller.setCheckSort(BUBBLE);
                    } else {
                        jOptionPaneMessageDialog(BUBBLE);
                    }
                    break;
                case MERGE:
                    //case when users want to use fixed sizes
                    if (customSizeText.getText().equals("") && controller.checkSortValue(MERGE) == 0) {
                        controller.setTimeMerge(sizeTemp);
                        controller.setCheckSort(MERGE);
                    } //check if the users type the correct input or not
                    else if (InputChecking.checkInput(customSizeText.getText(), 0) == null && !customSizeText.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, String.format("%s is not an integer, pls type again!!\n", customSizeText.getText()),
                                "Error Message", JOptionPane.ERROR_MESSAGE);
                    } //Finally when users have typed the correct input
                    else {
                        //if (previous text is different from current text
                        if (!sizeText.toString().equals(customSizeText.getText())) {
                            controller.resetOneValueCheckSort(MERGE);
                            Integer size = Integer.parseInt(customSizeText.getText());
                            controller.arrGen(size);
                            controller.setTimeMerge(size);
                        } //if they are identical => give message error
                        else {
                            jOptionPaneMessageDialog(MERGE);
                        }
                    }
                    //after one round we set the time, and set sizeText to the current text.
                    setResult(mergeT, MERGE, controller.getAlgoModel().getMergeTime());
                    sizeText.delete(0, sizeText.length());
                    sizeText.append(customSizeText.getText());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Something wrong has occured");
                    break;
            }
        }
    }
}
