package main.components;

import jdk.nashorn.internal.scripts.JO;
import main.Converter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Ram {

    private int[] data = new int[1024];
    private int[] programData = new int[1024];

    private JTable table;

    public Ram(JTable table, JButton loadButton) {
        this.table = table;
        /*programData[0] = 36865;
        programData[1] = 37889;
        programData[2] = 48128;
        programData[3] = 51200;
        programData[4] = 9224;
        programData[5] = 3072;
        programData[6] = 12296;
        programData[7] = 16386;


        data[0] = 36865;
        data[1] = 37889;
        data[2] = 48128;
        data[3] = 51200;
        data[4] = 9224;
        data[5] = 3072;
        data[6] = 12296;
        data[7] = 16386;*/
        updateTable();
        loadButton.addActionListener(e -> loadDataFromFile());
    }

    public void readDataFromRam() {
        if(ControllUnit.addressBus.getData() < 0 || ControllUnit.addressBus.getData() >= data.length) {
            System.err.println("An error has occurred on the address bus. Data on address bus: " + ControllUnit.addressBus.getData());
            ControllUnit.dataBus.putDataOnBus(0);
            return;
        }
        ControllUnit.dataBus.putDataOnBus(data[ControllUnit.addressBus.getData()]);
    }

    public void writeDataToRam() {
        if(ControllUnit.addressBus.getData() < 0 || ControllUnit.addressBus.getData() >= data.length) {
            System.err.println("An error has occurred on the address bus. Data on address bus: " + ControllUnit.addressBus.getData());
            return;
        }
        data[ControllUnit.addressBus.getData()] = ControllUnit.dataBus.getData();
        updateTable();
    }

    public int getDataAtIndex(int index) {
        if (index < 0 || index >= data.length) {
            return 0;
        }
        return data[index];
    }

    public void setDataAtIndex(int index, int value) {
        if (index < 0 || index >= data.length || value < 0 || value > 65535) {
            return;
        }
        data[index] = value;
        updateTable();
    }

    public void loadDataFromFile() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            try {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(selectedFile));
                } catch (Exception e) {
                    throw new Exception("File couldn't be found or opened");
                }

                String line = "";
                int index = 0;

                while ((line = reader.readLine()) != null) {
                    //read line
                    if (line.length() != 16) {
                        throw new Exception("A line is not an 16 bit binary number");
                    }
                    try {
                        int code = Integer.parseInt(line, 2);
                        programData[index] = code;
                        data[index] = code;
                        index++;
                    } catch (Exception e) {
                        throw new Exception("A line could not be parsed.");
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error while loading file", JOptionPane.WARNING_MESSAGE);
            }

        }

        updateTable();
    }

    public void reset() {
        data = programData;
        updateTable();
    }

    public void updateTable() {
        String[][] tableData = new String[1024][2];

        for (int i = 0; i < data.length; i++) {
            tableData[i][0] = Integer.toString(i);
            tableData[i][1] = Converter.getConvertedString(data[i]);
        }

        table.setModel(new DefaultTableModel(
                tableData,
                new String[] {
                        "Index", "Data"
                }
        ));
    }

}
