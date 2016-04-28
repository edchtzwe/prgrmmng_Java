
/**
 * Write a description of class GUI here.
 * 
 * @author (Edmund) 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GUI extends JFrame implements ActionListener
{
    private static String[] mediaTypes = {"Please Choose","Books", "CDs", "Movies"};
    private static JComboBox mediaCombo = null;
    private static SuperTable itemTable = null;
    private static JButton save = new JButton("Save");
    private static JButton cancel = new JButton("Cancel");
    private static JButton show = new JButton("Show");
    private static JButton rating = new JButton("Rating");
    private static Shop shop = null;
    private static ArrayList<Media> selectedItems = new ArrayList<Media>();
    private static String type = null;
    private static JComboBox cbox = null;
    private static List<TableCellEditor> editors = new ArrayList<TableCellEditor>();
    private static int selRow = 0;
    private static JPanel p1;            

    public GUI()
    {
        super("Books n Media");            
        mediaCombo = new JComboBox(mediaTypes);     
        itemTable = new SuperTable();
        itemTable.setSize(50,10);
        p1 = new JPanel(new GridLayout());    
        JScrollPane scrollPane = new JScrollPane(itemTable);
        p1.add(scrollPane);        
        setLayout(new BorderLayout());
        add(mediaCombo, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);
        JPanel p2 = new JPanel();
        p2.add(save);
        p2.add(show);
        p2.add(rating);
        p2.add(cancel);        
        add(p2, BorderLayout.SOUTH);
        shop = new Shop();        

        mediaCombo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String text = (String) mediaCombo.getSelectedItem();
                    type = text;
                    updateTable(text);                    
                }
            }
        );

        save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    sell();
                }
            });

        cancel.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    destroy();
                }
            });

        show.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    showItems();
                }
            });

        rating.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    RateGUI rGui = new RateGUI();
                }
            });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
    }       

    /**
     * lists all chosen items and the number of copies bought
     */
    public static void showItems()
    {
        String text = "";
        //books
        for(int i = 0; i < shop.getBooks().size(); ++i){
            if(shop.getBooks().get(i).getSelected() > 0){
                text += shop.getBooks().get(i).getTitle() + " $" + shop.getBooks().get(i).getPrice() + " " + shop.getBooks().get(i).getSelected() + "\n";
            }
        }
        //CDs
        for(int i = 0; i < shop.getCDs().size(); ++i){
            if(shop.getCDs().get(i).getSelected() > 0){
                text += shop.getCDs().get(i).getTitle() + " $" + shop.getCDs().get(i).getPrice() + " " + shop.getCDs().get(i).getSelected() + "\n";
            }
        }
        //Movies
        for(int i = 0; i < shop.getMovies().size(); ++i){
            if(shop.getMovies().get(i).getSelected() > 0){
                text += shop.getMovies().get(i).getTitle() + " $" + shop.getMovies().get(i).getPrice() + " " + shop.getMovies().get(i).getSelected() + "\n";
            }
        }
        if(text.equals("")){
            text += "Nothing selected";
        }
        JOptionPane.showMessageDialog(show, text);
    }

    /**
     * attempts to sell every book
     **/
    public static void sell()
    {
        //sell books
        for(int i = 0; i < shop.getBooks().size(); ++i){
            if(shop.getBooks().get(i).getSelected() > 0){
                shop.getBooks().get(i).sellCopies(shop.getBooks().get(i).getSelected());
            }
        }
        //reset
        for(int i = 0; i < shop.getBooks().size(); ++i){
            if(shop.getBooks().get(i).getSelected() > 0){
                shop.getBooks().get(i).setSelected(0);
            }
        }
        //sell CDs
        for(int i = 0; i < shop.getCDs().size(); ++i){
            if(shop.getCDs().get(i).getSelected() > 0){
                shop.getCDs().get(i).sellCopies(shop.getCDs().get(i).getSelected());
            }
        }
        //reset
        for(int i = 0; i < shop.getCDs().size(); ++i){
            if(shop.getCDs().get(i).getSelected() > 0){
                shop.getCDs().get(i).setSelected(0);
            }
        }
        
        //Sell Movies
         for(int i = 0; i < shop.getMovies().size(); ++i){
            if(shop.getMovies().get(i).getSelected() > 0){
                shop.getMovies().get(i).sellCopies(shop.getMovies().get(i).getSelected());
            }
        }
        //reset
        for(int i = 0; i < shop.getMovies().size(); ++i){
            if(shop.getMovies().get(i).getSelected() > 0){
                shop.getMovies().get(i).setSelected(0);
            }
        }

        //reset GUI
        JOptionPane.showMessageDialog(mediaCombo, shop.show());
        mediaCombo.setSelectedIndex(0);
        updateTable("None");
        rate();   
        shop.updateList();
    }

    /**
     * resets the selected number of books
     * does nothing to sold items and available
     */
    public static void destroy()
    {
        //books
        for(int i = 0; i < shop.getBooks().size(); ++i){
            if(shop.getBooks().get(i).getSelected() > 0){
                shop.getBooks().get(i).setSelected(0);
            }
        }
        //CDs
        for(int i = 0; i < shop.getCDs().size(); ++i){
            if(shop.getCDs().get(i).getSelected() > 0){
                shop.getCDs().get(i).setSelected(0);
            }
        }
        
        //Movies
        for(int i = 0; i < shop.getMovies().size(); ++i){
            if(shop.getMovies().get(i).getSelected() > 0){
                shop.getMovies().get(i).setSelected(0);
            }
        }
        mediaCombo.setSelectedIndex(0);
        updateTable("None");
    }

    /**
     * updates the table with list of items
     * based on passed in parameter
     * from the ComboBox
     */
    public static void updateTable(String ptext)
    {      
        editors.clear();
        if(ptext.equals("None")){
            DefaultTableModel model = new DefaultTableModel();
            itemTable.setModel(model);
        }
        else if(ptext.equals("Books")){ //sell books               
            updateBooks();
        }      
        else if(ptext.equals("CDs")){
            updateCDs();
        }
        else if(ptext.equals("Movies")){
            updateMovies();
        }
    }

    private static void updateBooks()
    {        
        //get book toString() and populate table
        DefaultTableModel model = new DefaultTableModel();
        int size = shop.getBooks().size();
        ArrayList<String> s1 = new ArrayList<String>();
        for(int i = 0; i < size; ++i){
            String s2 = shop.getBooks().get(i).toString();
            s1.add(s2);
        }
        Object[] s3 = s1.toArray();
        model.addColumn("Book", s3);
        itemTable.setModel(model);

        //make JComboBox for all available books        
        ArrayList<Integer> avail = new ArrayList();
        for(int i = 0; i < size; ++i){
            avail.add(shop.getBooks().get(i).getAvailable()); //get available copies of book, to populate JComboBox
        }
        ArrayList<JComboBox> cbox = new ArrayList<JComboBox>();
        //make the JComboBoxes
        for(int i = 0; i < size; ++i){
            String[] count = new String[avail.get(i) + 1];       
            count[0] = "0";
            for(int j = 1; j < count.length; ++j){
                count[j] = "" + j;
            }
            final JComboBox lcbox = new JComboBox(count);
            lcbox.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){                            
                        int selNum = Integer.parseInt((String) lcbox.getSelectedItem());
                        shop.getBooks().get(selRow).setSelected(selNum);                                                
                    }
                }
            );
            cbox.add(lcbox);
        }
        for(int i = 0; i < cbox.size(); ++i){
            DefaultCellEditor dce = new DefaultCellEditor(cbox.get(i));
            editors.add(dce);
        }
        itemTable.setModel(model);
    }

    private static void updateCDs()
    {                           
        //get book toString() and populate table
        DefaultTableModel model = new DefaultTableModel();
        int size = shop.getCDs().size();
        ArrayList<String> s1 = new ArrayList<String>();
        for(int i = 0; i < size; ++i){
            String s2 = shop.getCDs().get(i).toString();
            s1.add(s2);
        }
        Object[] s3 = s1.toArray();
        model.addColumn("CD", s3);
        itemTable.setModel(model);

        //make JComboBox for all available books
        int cdCount = shop.getCDs().size();
        ArrayList<Integer> avail = new ArrayList();
        for(int i = 0; i < cdCount; ++i){
            avail.add(shop.getCDs().get(i).getAvailable()); //get available copies of book, to populate JComboBox
        }
        ArrayList<JComboBox> cbox = new ArrayList<JComboBox>();
        //make the JComboBoxes
        for(int i = 0; i < cdCount; ++i){
            String[] count = new String[avail.get(i) + 1];       
            count[0] = "0";
            for(int j = 1; j < count.length; ++j){
                count[j] = "" + j;
            }
            final JComboBox lcbox = new JComboBox(count);
            lcbox.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){                            
                        int selNum = Integer.parseInt((String) lcbox.getSelectedItem());
                        shop.getCDs().get(selRow).setSelected(selNum);                                                
                    }
                }
            );
            cbox.add(lcbox);
        }
        for(int i = 0; i < cbox.size(); ++i){
            DefaultCellEditor dce = new DefaultCellEditor(cbox.get(i));
            editors.add(dce);
        }
        itemTable.setModel(model);
    }

    private static void updateMovies()
    {
        //get movie toString() and populate table
        DefaultTableModel model = new DefaultTableModel();
        int size = shop.getMovies().size();
        ArrayList<String> s1 = new ArrayList<String>();
        for(int i = 0; i < size; ++i){
            String s2 = shop.getMovies().get(i).toString();
            s1.add(s2);
        }
        Object[] s3 = s1.toArray();
        model.addColumn("Movie", s3);
        itemTable.setModel(model);

        //make JComboBox for all available movies        
        ArrayList<Integer> avail = new ArrayList();
        for(int i = 0; i < size; ++i){
            avail.add(shop.getMovies().get(i).getAvailable()); //get available copies of movie, to populate JComboBox
        }
        ArrayList<JComboBox> cbox = new ArrayList<JComboBox>();
        //make the JComboBoxes
        for(int i = 0; i < size; ++i){
            String[] count = new String[avail.get(i) + 1];       
            count[0] = "0";
            for(int j = 1; j < count.length; ++j){
                count[j] = "" + j;
            }
            final JComboBox lcbox = new JComboBox(count);
            lcbox.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){                            
                        int selNum = Integer.parseInt((String) lcbox.getSelectedItem());
                        shop.getMovies().get(selRow).setSelected(selNum);                                                
                    }
                }
            );
            cbox.add(lcbox);
        }
        for(int i = 0; i < cbox.size(); ++i){
            DefaultCellEditor dce = new DefaultCellEditor(cbox.get(i));
            editors.add(dce);
        }
        itemTable.setModel(model);
    }

    private static JSlider getSlider(final JOptionPane popPane) {
        JSlider slider = new JSlider();
        slider.setMajorTickSpacing(1);
        slider.setMaximum(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider theSlider = (JSlider) changeEvent.getSource();
                    if (!theSlider.getValueIsAdjusting()) {
                        popPane.setInputValue(new Integer(theSlider.getValue()));
                    }
                }
            };
        slider.addChangeListener(changeListener);
        return slider;
    }

    /**
     * asks for ratings of purchased items
     */
    public static void rate()
    {
        for(int i = 0; i < shop.getBooks().size(); ++i){
            //the item has not been rated but it is sold
            JOptionPane lopPane = new JOptionPane();
            JSlider slider = getSlider(lopPane);

            if(shop.getBooks().get(i).getRated() == false && shop.getBooks().get(i).getSold() > 0){
                String msg = "Please rate\n" + shop.getBooks().get(i).getTitle();
                lopPane.setMessage(new Object[] {msg, slider});
                lopPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                lopPane.setMessageType(JOptionPane.OK_CANCEL_OPTION);
                JDialog ldialog = lopPane.createDialog("Rating");
                ldialog.setVisible(true);
                shop.rateBook(i, slider.getValue());
//                 shop.getBooks().get(i).setRated(true);
            }
        }

        for(int i = 0; i < shop.getCDs().size(); ++i){
            //the item has not been rated but it is sold
            JOptionPane lopPane = new JOptionPane();
            JSlider slider = getSlider(lopPane);

            if(shop.getCDs().get(i).getRated() == false && shop.getCDs().get(i).getSold() > 0){
                String msg = "Please rate\n" + shop.getCDs().get(i).getTitle();
                lopPane.setMessage(new Object[] {msg, slider});
                lopPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                lopPane.setMessageType(JOptionPane.OK_CANCEL_OPTION);
                JDialog ldialog = lopPane.createDialog("Rating");
                ldialog.setVisible(true);
                shop.rateCD(i, slider.getValue());
//                 shop.getCDs().get(i).setRated(true);
            }
        }

        for(int i = 0; i < shop.getMovies().size(); ++i){
            //the item has not been rated but it is sold
            JOptionPane lopPane = new JOptionPane();
            JSlider slider = getSlider(lopPane);

            if(shop.getMovies().get(i).getRated() == false && shop.getMovies().get(i).getSold() > 0){
                String msg = "Please rate\n" + shop.getMovies().get(i).getTitle();
                lopPane.setMessage(new Object[] {msg, slider});
                lopPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                lopPane.setMessageType(JOptionPane.OK_CANCEL_OPTION);
                JDialog ldialog = lopPane.createDialog("Rating");
                ldialog.setVisible(true);
                shop.rateMovie(i, slider.getValue());
//                 shop.getMovies().get(i).setRated(true);
            }
        }
    }

    public static void main(String[] args)
    {
        GUI main = new GUI();
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save){

        }
        else if(e.getSource() == cancel){
            for(Media m : selectedItems){
                m.setSold(0);
            }
        }
    }      

    private class SuperTable extends JTable
    {
        SuperTable(){};

        SuperTable(DefaultTableModel m){
            super(m);
        }

        public TableCellEditor getCellEditor(int row, int column)
        {
            int modelColumn = convertColumnIndexToModel( column );

            if (modelColumn > -1){                        
                selRow = row;
                return editors.get(row);                         
            }
            else{
                selRow = row;
                return super.getCellEditor(row, column);                         
            }
        }    

        public TableCellRenderer getCellRenderer(int row, int column) {
            if(row == 1 && column == 1) {
                return this.getDefaultRenderer(Double.class);
            }

            return super.getCellRenderer(row, column);
        }
    }

    private class RateGUI extends JFrame
    {
        JTable table1 = new JTable();
        JTable table2 = new JTable();
        JTable table3 = new JTable();
        
        DefaultTableModel model1 = new DefaultTableModel(){ //disable table edit
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };  
        
        DefaultTableModel model2 = new DefaultTableModel(){ //disable table edit
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };  
        
        DefaultTableModel model3 = new DefaultTableModel(){ //disable table edit
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };  

        RateGUI()
        {
            super("Rating Table");
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(640, 480);
            table1.setSize(3, 5);
            table2.setSize(3, 5);
            table3.setSize(3, 5);
            makeTable();
        }

        private void makeTable()
        {                
            booksTable();
            CDsTable();
            MoviesTable();            
            JScrollPane scroll1 = new JScrollPane(table1);
            JScrollPane scroll2= new JScrollPane(table2);
            JScrollPane scroll3 = new JScrollPane(table3);
            GridLayout g = new GridLayout();
            g.setColumns(0);
            g.setRows(mediaTypes.length - 1);
            JPanel p1 = new JPanel(g);
            p1.add(scroll1);            
            p1.add(scroll2);            
            p1.add(scroll3);
            add(p1);
            setVisible(true);
        }

        private void booksTable()
        {            
            ArrayList<String> s1 = new ArrayList<String>();
            if(shop.getBooks().size() > 0){
                for(int i = 0; i < shop.getBooks().size(); ++i){
                    String s2 = shop.getBooks().get(i).toString();
                    s1.add(s2);
                }
            }
            if(shop.books2.size() > 0){
                for(int i = 0; i < shop.books2.size(); ++i){
                    String s2 = shop.books2.get(i).toString();
                    s1.add(s2);
                }
            }
            Object[] s3 = s1.toArray();
            model1.addColumn("Book", s3);
            ArrayList<String> s2 = new ArrayList<String>();
            if(shop.getBooks().size() > 0){
                for(int i = 0; i < shop.getBooks().size(); ++i){
                    String s4 = "";
                    s4 += shop.getBooks().get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            if(shop.books2.size() > 0){
                for(int i = 0; i < shop.books2.size(); ++i){
                    String s4 = "";
                    s4 += shop.books2.get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            Object[] s5 = s2.toArray();
            model1.addColumn("Rating", s5);
            table1.setModel(model1);
        }

        private void CDsTable()
        {
             ArrayList<String> s1 = new ArrayList<String>();
            if(shop.getCDs().size() > 0){
                for(int i = 0; i < shop.getCDs().size(); ++i){
                    String s2 = shop.getCDs().get(i).toString();
                    s1.add(s2);
                }
            }
            if(shop.cds2.size() > 0){
                for(int i = 0; i < shop.cds2.size(); ++i){
                    String s2 = shop.cds2.get(i).toString();
                    s1.add(s2);
                }
            }
            Object[] s3 = s1.toArray();
            model2.addColumn("Book", s3);
            ArrayList<String> s2 = new ArrayList<String>();
            if(shop.getCDs().size() > 0){
                for(int i = 0; i < shop.getCDs().size(); ++i){
                    String s4 = "";
                    s4 += shop.getCDs().get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            if(shop.cds2.size() > 0){
                for(int i = 0; i < shop.cds2.size(); ++i){
                    String s4 = "";
                    s4 += shop.cds2.get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            Object[] s5 = s2.toArray();
            model2.addColumn("Rating", s5);
            table2.setModel(model2);
        }
        
        private void MoviesTable()
        {
             ArrayList<String> s1 = new ArrayList<String>();
            if(shop.getMovies().size() > 0){
                for(int i = 0; i < shop.getMovies().size(); ++i){
                    String s2 = shop.getMovies().get(i).toString();
                    s1.add(s2);
                }
            }
            if(shop.movies2.size() > 0){
                for(int i = 0; i < shop.movies2.size(); ++i){
                    String s2 = shop.movies2.get(i).toString();
                    s1.add(s2);
                }
            }
            Object[] s3 = s1.toArray();
            model3.addColumn("Book", s3);
            ArrayList<String> s2 = new ArrayList<String>();
            if(shop.getMovies().size() > 0){
                for(int i = 0; i < shop.getMovies().size(); ++i){
                    String s4 = "";
                    s4 += shop.getMovies().get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            if(shop.movies2.size() > 0){
                for(int i = 0; i < shop.movies2.size(); ++i){
                    String s4 = "";
                    s4 += shop.movies2.get(i).getRating().getAverage();
                    s2.add(s4);
                }
            }
            Object[] s5 = s2.toArray();
            model3.addColumn("Rating", s5);
            table3.setModel(model3);
        }
    }
}
