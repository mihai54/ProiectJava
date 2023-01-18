//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class TableEventDemo implements ActionListener, ListSelectionListener {
    String[] headings = new String[]{"Name", "Costumer ID", "Order #", "Status"};
    Object[][] data = new Object[][]{{"Tom", new Integer(34756), "T-01023", "Shipped"}, {"Wendy", new Integer(67263), "S-98301", "Shipped"}, {"Steve", new Integer(87856), "S-45670", "Back Ordered"}, {"Adam", new Integer(70851), "A-19875", "Pending"}, {"Larry", new Integer(40952), "L-18567", "Shipped"}, {"Mark", new Integer(88992), "M-22881", "Cancelled"}, {"Terry", new Integer(67492), "T-18269", "Shipped"}};
    JTable jtabOrders;
    JRadioButton jrbRows;
    JRadioButton jrbColumns;
    JRadioButton jrbCells;
    JCheckBox jcbSingle;
    JLabel jlab;
    JLabel jlab2;
    TableModel tm;

    TableEventDemo() {
        JFrame jfrm = new JFrame("JTable Demo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(480, 220);
        jfrm.setDefaultCloseOperation(3);
        this.jlab = new JLabel();
        this.jlab.setPreferredSize(new Dimension(400, 20));
        this.jlab2 = new JLabel();
        this.jlab2.setPreferredSize(new Dimension(400, 20));
        this.jtabOrders = new JTable(this.data, this.headings);
        JScrollPane jscrlp = new JScrollPane(this.jtabOrders);
        this.jtabOrders.setPreferredScrollableViewportSize(new Dimension(400, 60));
        ListSelectionModel rowSelMod = this.jtabOrders.getSelectionModel();
        ListSelectionModel colSelMod = this.jtabOrders.getColumnModel().getSelectionModel();
        rowSelMod.addListSelectionListener(this);
        colSelMod.addListSelectionListener(this);
        this.tm = this.jtabOrders.getModel();
        this.tm.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent tme) {
                if (tme.getType() == 0) {
                    JLabel var10000 = TableEventDemo.this.jlab2;
                    int var10001 = tme.getFirstRow();
                    var10000.setText("Cell " + var10001 + ", " + tme.getColumn() + " changed.The new value: " + String.valueOf(TableEventDemo.this.tm.getValueAt(tme.getFirstRow(), tme.getColumn())));
                }

            }
        });
        this.jrbRows = new JRadioButton("Select Rows", true);
        this.jrbColumns = new JRadioButton("Select Columns", true);
        this.jrbCells = new JRadioButton("Select Cells");
        ButtonGroup bg = new ButtonGroup();
        bg.add(this.jrbRows);
        bg.add(this.jrbColumns);
        bg.add(this.jrbCells);
        this.jrbRows.addActionListener(this);
        this.jrbColumns.addActionListener(this);
        this.jrbCells.addActionListener(this);
        this.jcbSingle = new JCheckBox("Sibgle Selection Mode");
        this.jcbSingle.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (TableEventDemo.this.jcbSingle.isSelected()) {
                    TableEventDemo.this.jtabOrders.setSelectionMode(0);
                } else {
                    TableEventDemo.this.jtabOrders.setSelectionMode(2);
                }

            }
        });
        jfrm.add(jscrlp);
        jfrm.add(this.jrbRows);
        jfrm.add(this.jrbColumns);
        jfrm.add(this.jrbCells);
        jfrm.add(this.jcbSingle);
        jfrm.add(this.jlab);
        jfrm.add(this.jlab2);
        jfrm.setVisible(true);
    }

    public void actionPerformed(ActionEvent ie) {
        if (this.jrbRows.isSelected()) {
            this.jtabOrders.setColumnSelectionAllowed(false);
            this.jtabOrders.setRowSelectionAllowed(true);
        } else if (this.jrbColumns.isSelected()) {
            this.jtabOrders.setColumnSelectionAllowed(true);
            this.jtabOrders.setRowSelectionAllowed(false);
        } else {
            this.jtabOrders.setCellSelectionEnabled(true);
            this.jcbSingle.setSelected(true);
            this.jtabOrders.setSelectionMode(0);
        }

    }

    public void valueChanged(ListSelectionEvent le) {
        String str;
        int[] cols;
        int i;
        if (this.jrbRows.isSelected()) {
            str = "Selected row(s): ";
            cols = this.jtabOrders.getSelectedRows();

            for(i = 0; i < cols.length; ++i) {
                str = str + cols[i] + " ";
            }
        } else if (this.jrbColumns.isSelected()) {
            str = "Selected column(s): ";
            cols = this.jtabOrders.getSelectedColumns();

            for(i = 0; i < cols.length; ++i) {
                str = str + cols[i] + " ";
            }
        } else {
            int var10000 = this.jtabOrders.getSelectedRow();
            str = "Selected cell: (View Relative) " + var10000 + ", " + this.jtabOrders.getSelectedColumn();
        }

        this.jlab.setText(str);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TableEventDemo();
            }
        });
    }
}
