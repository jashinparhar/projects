package ui;

import model.Car;
import model.Event;
import model.EventLog;
import model.Warehouse;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener {

    private Warehouse warehouse;
    JFrame jframe;
    DefaultListModel<Car> defaultSelections;
    DefaultListModel<Car> defaultInvList;
    DefaultListModel<Car> defaultSoldList;
    DefaultListModel<Car> defaultImpList;
    JList<Car> selections;
    JList<Car> invList;
    JList<Car> soldList;
    JList<Car> impList;
    JButton invListBut;
    JButton impListBut;
    JButton soldListBut;
    JButton saveBut;
    JButton loadBut;
    JButton detailsBut;
    JButton clearBut;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    JLabel invLabel;
    JLabel impLabel;
    JLabel soldLabel;
    JLabel selectedLabel;
    JLabel detailsLabel;
    JLabel rangeLabel;
    JLabel hondaLabel;
    JLabel jaguarLabel;
    BufferedImage rangeRoverPic;
    BufferedImage hondaCivicPic;
    BufferedImage jaguarFPacePic;
    private static final String JSON_STORE = "./data/warehouse.json";

    // Interactive GUI Application for Car Dealership Warehouse
    public GUI() throws IOException {
        initializeObjects();
        setSettings();
        setBounds();
        addActionListener();
        initializeLists();
        addCarsToSelection();

        invList = new JList<>(defaultInvList);
        invList.setBounds(350, 50, 130, 250);
        soldList = new JList<>(defaultSoldList);
        soldList.setBounds(500, 50, 130, 250);
        impList = new JList<>(defaultImpList);
        impList.setBounds(650, 50, 130, 250);
        selections = new JList<>(defaultSelections);
        selections.setBounds(30, 50, 130, 250);

        addToGUI();

        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitProcedure();
                System.exit(0);
            }
        });
    }

    // EFFECTS: Gives commands to all buttons once each respective button is clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == invListBut) {
            invListButtonMethod();
        }

        if (e.getSource() == soldListBut) {
            soldListButtonMethod();
        }

        if (e.getSource() == impListBut) {
            impListButtonMethod();
        }

        if (e.getSource() == detailsBut) {
            detailsButMethod();
        }

        if (e.getSource() == clearBut) {
            clearLists();
            warehouse.clear();
        }

        if (e.getSource() == saveBut) {
            saveButMethod();
        }

        if (e.getSource() == loadBut) {
            loadButMethod();
        }
    }

    // EFFECTS: Loads workroom from file, also clears the lists before loading for so no interruption with existing
    // methods
    public void loadButMethod() {
        try {
            warehouse = jsonReader.read();
            JOptionPane.showMessageDialog(jframe, "Loaded file from: " + JSON_STORE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(jframe, "Unable to read file from: " + JSON_STORE);
        }
        clearLists();
        loadLists();
    }

    // EFFECTS: Adds selection to inventory list
    public void invListButtonMethod() {
        if (selections.getSelectedIndex() != -1) {
            defaultInvList.addElement(selections.getSelectedValue());
            warehouse.addCarToInventoryList(selections.getSelectedValue());
        }
    }

    // EFFECTS: Adds selection to importing list
    public void impListButtonMethod() {
        if (selections.getSelectedIndex() != -1) {
            defaultImpList.addElement(selections.getSelectedValue());
            warehouse.addCarToImportingList(selections.getSelectedValue());
        }
    }

    // EFFECTS: Adds selection to sold list
    public void soldListButtonMethod() {
        if (selections.getSelectedIndex() != -1) {
            defaultSoldList.addElement(selections.getSelectedValue());
            warehouse.addCarToSoldList(selections.getSelectedValue());
        }
    }

    // EFFECTS: Prints event log to console on exit and exits system.
    public void exitProcedure() {
        printLog(EventLog.getInstance());
        jframe.dispose();
        System.exit(0);
    }

    // EFFECTS: Loads up the warehouse with the default java lists and saves the warehouse
    public void saveButMethod() {
//        clearWarehouse();
//        loadWarehouse();
        try {
            jsonWriter.open();
            jsonWriter.write(warehouse);
            jsonWriter.close();
            JOptionPane.showMessageDialog(jframe, "Saved to: " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(jframe, "Cannot write file from:" + JSON_STORE);
        }
    }

    // EFFECTS: Puts the to string of the selected object and also brings up the picture of the selected car
    public void detailsButMethod() {
        String detailsMsg;
        if (selections.getSelectedIndex() != -1) {
            detailsMsg = selections.getSelectedValue().toString();
            detailsLabel.setText(detailsMsg);
            if (selections.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, rangeLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
            }
            if (selections.getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, hondaLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
            }
            if (selections.getSelectedIndex() == 2) {
                JOptionPane.showMessageDialog(null, jaguarLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
            }
        }
    }


    // EFFECTS: load the default list models from existing saved warehouse file.
    public void loadLists() {
        for (int i = 0; i < warehouse.getInventoryList().size(); i++) {
            defaultInvList.addElement(warehouse.getInventoryList().get(i));
        }
        for (int i = 0; i < warehouse.getImportingList().size(); i++) {
            defaultImpList.addElement(warehouse.getImportingList().get(i));
        }
        for (int i = 0; i < warehouse.getSoldList().size(); i++) {
            defaultSoldList.addElement(warehouse.getSoldList().get(i));
        }
    }


    // EFFECTS: clears all default list models
    public void clearLists() {
        defaultInvList.clear();
        defaultImpList.clear();
        defaultSoldList.clear();
    }

    // EFFECTS: add all buttons, lists, and labels to gui interface
    public void addToGUI() {
        this.add(invListBut);
        this.add(soldListBut);
        this.add(impListBut);
        this.add(selections);
        this.add(invList);
        this.add(soldList);
        this.add(impList);
        this.add(saveBut);
        this.add(loadBut);
        this.add(detailsBut);
        this.add(invLabel);
        this.add(selectedLabel);
        this.add(impLabel);
        this.add(soldLabel);
        this.add(detailsLabel);
        this.add(clearBut);
    }

    public void addCarsToSelection() {
        defaultSelections.addElement(new Car(2020, "Range Rover", "Sport"));
        defaultSelections.addElement(new Car(2012, "Honda", "Civic"));
        defaultSelections.addElement(new Car(2019, "Jaguar", "F-Pace"));
    }

    // EFFECTS:
    public void setSettings() {
        this.setTitle("Car Dealership Warehouse Manager");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
    }

    // EFFECTS: intializes all objects
    public void initializeObjects() throws IOException {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jframe = new JFrame();
        warehouse = new Warehouse();
        rangeRoverPic = ImageIO.read(new File("data/rangeroversport.jpeg"));
        hondaCivicPic = ImageIO.read(new File("data/hondacivic.jpeg"));
        jaguarFPacePic = ImageIO.read(new File("data/jaguarfpace.jpeg"));
        invListBut = new JButton("Move to Inventory");
        soldListBut = new JButton("Move to Sold");
        impListBut = new JButton("Move to Importing");
        saveBut = new JButton("Save");
        loadBut = new JButton("Load");
        detailsBut = new JButton("Details");
        clearBut = new JButton("Clear");
        invLabel = new JLabel("Inventory List");
        soldLabel = new JLabel("Sold List");
        impLabel = new JLabel("Importing List");
        selectedLabel = new JLabel("Selections");
        detailsLabel = new JLabel("Details");
        rangeLabel = new JLabel(new ImageIcon(rangeRoverPic));
        jaguarLabel = new JLabel(new ImageIcon(jaguarFPacePic));
        hondaLabel = new JLabel(new ImageIcon(hondaCivicPic));
    }

    // EFFECTS: set all positions and sizes for all the elements on gui
    public void setBounds() {
        invListBut.setBounds(15, 520, 140, 40);
        soldListBut.setBounds(155, 520, 140, 40);
        impListBut.setBounds(295, 520, 140, 40);
        saveBut.setBounds(710, 520, 75, 40);
        loadBut.setBounds(635, 520, 75, 40);
        detailsBut.setBounds(560, 520, 75, 40);
        clearBut.setBounds(485,520,75,40);
        invLabel.setBounds(350, -10, 200, 100);
        soldLabel.setBounds(500, -10, 200, 100);
        impLabel.setBounds(650, -10, 200, 100);
        selectedLabel.setBounds(30, -10, 200, 100);
        detailsLabel.setBounds(20, 460, 500, 100);
    }

    // EFFECTS: adds action listeners to all the buttons
    public void addActionListener() {
        invListBut.addActionListener(this);
        soldListBut.addActionListener(this);
        impListBut.addActionListener(this);
        saveBut.addActionListener(this);
        loadBut.addActionListener(this);
        detailsBut.addActionListener(this);
        clearBut.addActionListener(this);
    }

    // EFFECTS: initializes the default list models.
    public void initializeLists() {
        defaultSelections = new DefaultListModel<>();
        defaultInvList = new DefaultListModel<>();
        defaultSoldList = new DefaultListModel<>();
        defaultImpList = new DefaultListModel<>();
    }

    // EFFECTS: Iterates through the list of events in event logs and prints them
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

}




