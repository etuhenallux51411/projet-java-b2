package main.modelPackage;

import javax.swing.table.DefaultTableModel;

public class ListingTableModel extends DefaultTableModel {
        public ListingTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
}
