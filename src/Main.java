import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class Main implements
        ActionListener, ListSelectionListener
{
    String[] headings = { "Numele",
            "Anul Nasteri",
            "Orasul",
            "IP#"
    };

    Object[][]data = {
            { "Cristian", new Integer(2000), "Chisinau", "#5644" },
            { "Mihai", new Integer(1990), "Oradea", "#4434" },
            { "Daniel", new Integer(1999), "Oradea", "#6666" },
            { "Sergiu", new Integer(1978), "Cluj", "#8787" },
            { "Andrei", new Integer(1979), "Galati", "#5555" },
            { "Rares", new Integer(2002), "Craiova", "#9999" }
    };

    JTable jtabOrders;
    JRadioButton jrbRows;
    JRadioButton jrbColumns;
    JRadioButton jrbCells;

    JCheckBox jcbSingle;
    JLabel jlab;
    JLabel jlab2;
    TableModel tm;

    Main()
    {
        JFrame jfrm = new JFrame("JTable Demo");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(480, 220);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlab = new JLabel();
        jlab.setPreferredSize(new Dimension(400,20));

        jlab2 = new JLabel();
        jlab2.setPreferredSize(new Dimension(400,20));

        jtabOrders = new JTable(data, headings);

        JScrollPane jscrlp = new JScrollPane(jtabOrders);
        jtabOrders.setPreferredScrollableViewportSize(
                new Dimension(400, 60));

        ListSelectionModel rowSelMod =
                jtabOrders.getSelectionModel();

        ListSelectionModel colSelMod =
                jtabOrders.getColumnModel().getSelectionModel();

        rowSelMod.addListSelectionListener(this);
        colSelMod.addListSelectionListener(this);

        tm = jtabOrders.getModel();

        tm.addTableModelListener(new TableModelListener()
        {
            public void tableChanged(TableModelEvent tme)
            {

                if(tme.getType() == TableModelEvent.UPDATE)
                {
                    jlab2.setText("Cell " + tme.getFirstRow() + ", " +
                            tme.getColumn() + " changed." +
                            "The new value: " +
                            tm.getValueAt(tme.getFirstRow(),
                                    tme.getColumn()));
                }
            }
        });


        jrbRows = new JRadioButton("Select Rows", true);
        jrbColumns = new JRadioButton("Select Columns", true);
        jrbCells = new JRadioButton("Select Cells");


        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbRows);
        bg.add(jrbColumns);
        bg.add(jrbCells);

        jrbRows.addActionListener(this);
        jrbColumns.addActionListener(this);
        jrbCells.addActionListener(this);


        jcbSingle = new JCheckBox("Sibgle Selection Mode");


        jcbSingle.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                if(jcbSingle.isSelected())



                    jtabOrders.setSelectionMode(
                            ListSelectionModel.SINGLE_SELECTION);
                else



                    jtabOrders.setSelectionMode(
                            ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            }
        });


        jfrm.add(jscrlp);
        jfrm.add(jrbRows);
        jfrm.add(jrbColumns);
        jfrm.add(jrbCells);
        jfrm.add(jcbSingle);
        jfrm.add(jlab);
        jfrm.add(jlab2);

        jfrm.setVisible(true);
    }


    public void actionPerformed(ActionEvent ie)
    {


        if(jrbRows.isSelected())
        {


            jtabOrders.setColumnSelectionAllowed(false);
            jtabOrders.setRowSelectionAllowed(true);
        }

        else if(jrbColumns.isSelected())
        {


            jtabOrders.setColumnSelectionAllowed(true);
            jtabOrders.setRowSelectionAllowed(false);
        }

        else
        {


            jtabOrders.setCellSelectionEnabled(true);
            jcbSingle.setSelected(true);
            jtabOrders.setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);
        }
    }


    public void valueChanged(ListSelectionEvent le)
    {
        String str;



        if(jrbRows.isSelected())
        {
            str = "Selected row(s): ";



            int[] rows = jtabOrders.getSelectedRows();



            for(int i=0; i < rows.length; i++)
                str += rows[i] + " ";
        }
        else if(jrbColumns.isSelected())
        {
            str = "Selected column(s): ";



            int[] cols = jtabOrders.getSelectedColumns();


            for(int i=0; i < cols.length; i++)
                str += cols[i] + " ";
        }
        else
        {
            str = "Selected cell: (View Relative) " +
                    jtabOrders.getSelectedRow() +
                    ", " +
                    jtabOrders.getSelectedColumn();
        }



        jlab.setText(str);
    }

    public static void main (String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Main();
            }
        });
    }
}

