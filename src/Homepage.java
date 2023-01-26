
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class Homepage extends javax.swing.JFrame {

    public Homepage() throws SQLException {
        
        initComponents();
        reset_Val();
    }

    public void addTable(String n, Double p, String c, String s ){
        
        
        // Add product to cart
        DefaultTableModel dt = (DefaultTableModel)Order_Table.getModel();
        
                    Vector v = new Vector();
                    v.add(n);
                    v.add(c);
                    v.add(s);
                    v.add(p);
                    dt.addRow(v);
                    
         cart_cal();
                    
    }
    
    public final void reset_Val()
    {
        DefaultTableModel dt = (DefaultTableModel)Order_Table.getModel();
        dt.setRowCount(0);
        
        subTotal_value.setText("0.00");
        Tax_value.setText("0.00");
        Receipt_Text.setText("");
        Pay_But.setText("   PAY        RM 0.00");
        member = false;
    }
    
    public void cart_cal(){
 
          int Row_num = Order_Table.getRowCount();
          double subtotal = 0;
          double discount = 0;
          
          for(int i = 0; i<Row_num; i++)
          {
              double value =  Double.parseDouble(Order_Table.getValueAt(i,3).toString());
              subtotal += value;
          }
          
          DecimalFormat df = new DecimalFormat("0.00");
          DecimalFormat tax_df = new DecimalFormat("0.0");
          String displaySubtotal = df.format(subtotal);
          subTotal_value.setText(displaySubtotal);
          
          if(member)
          {
              discount = 0.1  * subtotal;
              String displayDis = df.format(discount);
              Discount_lbl.setText(displayDis);
          }
              
              
          subtotal -= discount;
          double tax = 0.15 * subtotal ;
          String displayTax = tax_df.format(tax);
          displayTax = df.format(tax);
          Tax_value.setText(displayTax);
          
          double total = subtotal + tax;
          String displayTotal = df.format(total);
          Pay_But.setText("   PAY        RM "+displayTotal);
          Receipt_Text.setText("");
          display_print();
 }
   
    
 public void display_print(){
 
     Receipt_Text.setText("                         The Coffee Story \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\t589/ King Road, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\tColombo, Srilanka, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\t+9411 123456789, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + " Item\tSize\t Description \tPrice \n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     DefaultTableModel df = (DefaultTableModel) Order_Table.getModel();
     for (int i = 0; i < Order_Table.getRowCount(); i++) {
         
         String name = df.getValueAt(i, 0).toString();
         String condition = df.getValueAt(i, 1).toString();
         String size = df.getValueAt(i, 2).toString();
         double prc = Double.parseDouble(df.getValueAt(i, 3).toString());
         
         Receipt_Text.setText(Receipt_Text.getText() + name+"\t"+size+"\t"+condition+"\tRM"+String.format("%.2f",prc)+" \n");
         
     }
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + "SubTotal (" +Order_Table.getRowCount() + "):\tRM"+subTotal_value.getText()+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Tax 10%: " + "\tRM"+Tax_value.getText()+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Discount :\tRM"+Discount_lbl.getText()+"\n");
     Receipt_Text.setText(Receipt_Text.getText() +"                     Thanks For Your Business...!"+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() +"                     Software by Techinbox"+"\n");
 
 }
 
 public void bill_print() throws PrinterException{
 
     Double amt =Double.valueOf(Amount_txt.getText());
     Double ttl = Double.valueOf(Total_txt.getText());
     Double Balance = amt - ttl;
     
     Receipt_Text.setText("                         The Coffee Story \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\t589/ King Road, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\tColombo, Srilanka, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "\t+9411 123456789, \n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + " Item\tSize\t Description \tPrice \n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     DefaultTableModel df = (DefaultTableModel) Order_Table.getModel();
     for (int i = 0; i < Order_Table.getRowCount(); i++) {
         
         String name = df.getValueAt(i, 0).toString();
         String condition = df.getValueAt(i, 1).toString();
         String size = df.getValueAt(i, 2).toString();
         double prc = Double.parseDouble(df.getValueAt(i, 3).toString());
         
         Receipt_Text.setText(Receipt_Text.getText() + name+"\t"+size+"\t"+condition+"\tRM"+String.format("%.2f",prc)+" \n");
         
     }
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + "SubTotal (" +Order_Table.getRowCount() + "):\tRM"+subTotal_value.getText()+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Tax 10%: " + "\tRM"+Tax_value.getText()+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Total:" + "\tRM"+String.format("%.2f",ttl)+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Cash :\tRM"+ String.format("%.2f", amt)+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "Ballance :\tRM"+ String.format("%.2f",Balance) +"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "====================================\n");
     Receipt_Text.setText(Receipt_Text.getText() +"                     Thanks For Your Business...!"+"\n");
     Receipt_Text.setText(Receipt_Text.getText() + "----------------------------------------------------------------\n");
     Receipt_Text.setText(Receipt_Text.getText() +"                     Software by Coffee Story"+"\n");
 
     Receipt_Text.print();
 }
 
 public String set_AmountText( String set)
 {
        String data = Amount_txt.getText();
        if(data.equals("0.00")) data = set;
        else    data = data +set;
        return data;
 }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Payment = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        Key1_but = new javax.swing.JButton();
        Key2_but = new javax.swing.JButton();
        Key3_but = new javax.swing.JButton();
        Key4_but = new javax.swing.JButton();
        Key5_but = new javax.swing.JButton();
        Key6_but = new javax.swing.JButton();
        Key7_but = new javax.swing.JButton();
        Key8_but = new javax.swing.JButton();
        Key9_but = new javax.swing.JButton();
        KeyClear_but = new javax.swing.JButton();
        Key0_but = new javax.swing.JButton();
        KeyDot_but = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Total_lbl = new javax.swing.JLabel();
        Amount_lbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Amount_txt = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Total_txt = new javax.swing.JTextPane();
        KeyEnter_but = new javax.swing.JButton();
        Coffee = new javax.swing.JFrame();
        Coffee_panel = new javax.swing.JPanel();
        Coffee_title = new javax.swing.JLabel();
        Ctype_Panel = new javax.swing.JPanel();
        Type = new javax.swing.JLabel();
        Latte_btn = new javax.swing.JRadioButton();
        Espresso_btn = new javax.swing.JRadioButton();
        Cappuccino_btn = new javax.swing.JRadioButton();
        Latte_price = new javax.swing.JLabel();
        Espresso_price = new javax.swing.JLabel();
        Cappuccino_price = new javax.swing.JLabel();
        CTypeR_lbl = new javax.swing.JLabel();
        Csize_Panel = new javax.swing.JPanel();
        CSize = new javax.swing.JLabel();
        CSizeR_lbl = new javax.swing.JLabel();
        Small_size_btn = new javax.swing.JRadioButton();
        Med_size_btn = new javax.swing.JRadioButton();
        Big_size_btn = new javax.swing.JRadioButton();
        Cmed_price = new javax.swing.JLabel();
        Cbig_price = new javax.swing.JLabel();
        Csmall_price = new javax.swing.JLabel();
        Csugar_lvl = new javax.swing.JPanel();
        CSugar_lbl = new javax.swing.JLabel();
        Zero_lvl_btn = new javax.swing.JRadioButton();
        Low_lvl_btn = new javax.swing.JRadioButton();
        Med_lvl_btn = new javax.swing.JRadioButton();
        High_lvl_btn = new javax.swing.JRadioButton();
        Full_lvl_btn = new javax.swing.JRadioButton();
        CSugarR_lbl = new javax.swing.JLabel();
        Cice_lvl = new javax.swing.JPanel();
        CIce_lbl = new javax.swing.JLabel();
        CIceR_lbl = new javax.swing.JLabel();
        CHot_btn = new javax.swing.JRadioButton();
        CCold_btn = new javax.swing.JRadioButton();
        CNo_ice_btn = new javax.swing.JRadioButton();
        Cice_price = new javax.swing.JLabel();
        Ccold_price = new javax.swing.JLabel();
        Ccream_lvl = new javax.swing.JPanel();
        CCream = new javax.swing.JLabel();
        CCream_btn = new javax.swing.JRadioButton();
        CWithout_cream_btn = new javax.swing.JRadioButton();
        CCreamR_lbl = new javax.swing.JLabel();
        Cadd_btn = new javax.swing.JButton();
        Tea = new javax.swing.JFrame();
        Tea_panel = new javax.swing.JPanel();
        Tea_title = new javax.swing.JLabel();
        Ttype_Panel = new javax.swing.JPanel();
        TType = new javax.swing.JLabel();
        TTypeR_lbl = new javax.swing.JLabel();
        Egtea_btn = new javax.swing.JRadioButton();
        Egtea_price = new javax.swing.JLabel();
        Jasmine_but = new javax.swing.JRadioButton();
        Jasmine_price = new javax.swing.JLabel();
        Oolong_but = new javax.swing.JRadioButton();
        Oolong_price = new javax.swing.JLabel();
        TSize_Panel = new javax.swing.JPanel();
        TSize_lbl = new javax.swing.JLabel();
        TSmall_size = new javax.swing.JRadioButton();
        Tmed_size = new javax.swing.JRadioButton();
        TBig_size = new javax.swing.JRadioButton();
        TMedSize_price = new javax.swing.JLabel();
        TBigSoze_price = new javax.swing.JLabel();
        TSmallSize_but = new javax.swing.JLabel();
        TSizeR_lbl = new javax.swing.JLabel();
        TSugar_lvl = new javax.swing.JPanel();
        Tsugar_lbl = new javax.swing.JLabel();
        TZero_btn = new javax.swing.JRadioButton();
        TLow_btn = new javax.swing.JRadioButton();
        TMed_btn = new javax.swing.JRadioButton();
        THIgh_btn = new javax.swing.JRadioButton();
        TFull_btn = new javax.swing.JRadioButton();
        TsugarR_lbl = new javax.swing.JLabel();
        Tice_lvl1 = new javax.swing.JPanel();
        TIce_lbl = new javax.swing.JLabel();
        THot_btn = new javax.swing.JRadioButton();
        TCold_btn = new javax.swing.JRadioButton();
        TNo_ice_btn = new javax.swing.JRadioButton();
        Tice_price = new javax.swing.JLabel();
        TWice_price = new javax.swing.JLabel();
        TiceR_lbl = new javax.swing.JLabel();
        Tmilk_lvl = new javax.swing.JPanel();
        Milklevel = new javax.swing.JLabel();
        Milk_btn = new javax.swing.JRadioButton();
        Without_milk_btn = new javax.swing.JRadioButton();
        TMilkR_lbl = new javax.swing.JLabel();
        Tmilk_price = new javax.swing.JLabel();
        Twithout_milk_price = new javax.swing.JLabel();
        Tadd_btn1 = new javax.swing.JButton();
        Bagel = new javax.swing.JFrame();
        Bagel_panel = new javax.swing.JPanel();
        Bagel_title1 = new javax.swing.JLabel();
        BagelDough_Panel = new javax.swing.JPanel();
        BagelDoughSection = new javax.swing.JLabel();
        BagelDough_plain = new javax.swing.JRadioButton();
        BagelDough_Mixed = new javax.swing.JRadioButton();
        BagelDough_WholeWheat = new javax.swing.JRadioButton();
        BagelDough_PlainPrice = new javax.swing.JLabel();
        BagelDough_MixedPrice = new javax.swing.JLabel();
        BagelDough_WholeWheatPrice = new javax.swing.JLabel();
        required_label7 = new javax.swing.JLabel();
        BagelSizePanel = new javax.swing.JPanel();
        BagelSizeSection = new javax.swing.JLabel();
        BagelSize_Small = new javax.swing.JRadioButton();
        BagelSize_Medium = new javax.swing.JRadioButton();
        BagelSize_Big = new javax.swing.JRadioButton();
        BagelSize_Mediumprice = new javax.swing.JLabel();
        BagelSize_BigPrice = new javax.swing.JLabel();
        BagelSize_Smallprice = new javax.swing.JLabel();
        required_label10 = new javax.swing.JLabel();
        BagelFlavour_Panel = new javax.swing.JPanel();
        BagelFlavourSection = new javax.swing.JLabel();
        BagelFlavour_Plain = new javax.swing.JRadioButton();
        BagelFlavour_MixedNuts = new javax.swing.JRadioButton();
        BagelFlavour_CinnamonRaisin = new javax.swing.JRadioButton();
        BagelFlavour_BlueBerry = new javax.swing.JRadioButton();
        BagelFlavour_Asiago = new javax.swing.JRadioButton();
        required_label5 = new javax.swing.JLabel();
        BagelFlavour_PlainPrice = new javax.swing.JLabel();
        BagelFlavour_MixedNutsPrice = new javax.swing.JLabel();
        BagelFlavour_BlueBerryPrice = new javax.swing.JLabel();
        BagelFlavour_AsiagoPrice = new javax.swing.JLabel();
        BagelFlavour_CinnamonRaisinPrice = new javax.swing.JLabel();
        BagelToppingSauce_Panel = new javax.swing.JPanel();
        BagelToppingSauceSection = new javax.swing.JLabel();
        BagelToppingSauce_CreamCheese = new javax.swing.JRadioButton();
        BagelToppingSauce_PeanutButter = new javax.swing.JRadioButton();
        BagelToppingSauce_Butter = new javax.swing.JRadioButton();
        BagelToppingSauce_PeanutButterPrice = new javax.swing.JLabel();
        BagelToppingSauce_ButterPrice = new javax.swing.JLabel();
        BagelToppingSauce_CreamCheesePrice = new javax.swing.JLabel();
        required_label6 = new javax.swing.JLabel();
        BagelToppingSauce_Jam = new javax.swing.JRadioButton();
        BagelToppingSauce_JamPrice = new javax.swing.JLabel();
        BagelToppingSauce_Honey = new javax.swing.JRadioButton();
        BagelToppingSauce_HoneyPrice = new javax.swing.JLabel();
        BagelToppingIngredient_Panel = new javax.swing.JPanel();
        BagelToppingIngredientSection = new javax.swing.JLabel();
        BagelToppingIngredient_Lox = new javax.swing.JRadioButton();
        BagelToppingIngredient_Bacon = new javax.swing.JRadioButton();
        BagelToppingIngredient_Egg = new javax.swing.JRadioButton();
        BagelToppingIngredient_BaconPrice = new javax.swing.JLabel();
        BagelToppingIngredient_EggPrice = new javax.swing.JLabel();
        BagelToppingIngredient_LoxPrice = new javax.swing.JLabel();
        required_label11 = new javax.swing.JLabel();
        BagelToppingIngredient_Tomato = new javax.swing.JRadioButton();
        BagelToppingIngredient_TomatoPrice = new javax.swing.JLabel();
        BagelToppingIngredient_Lettuce = new javax.swing.JRadioButton();
        BagelToppingIngredient_LettucePrice = new javax.swing.JLabel();
        Bagel_add_btn1 = new javax.swing.JButton();
        Pastry = new javax.swing.JFrame();
        Pastry_panel = new javax.swing.JPanel();
        Pastry_title2 = new javax.swing.JLabel();
        PastryDonuts_Panel = new javax.swing.JPanel();
        PastryDonutSection4 = new javax.swing.JLabel();
        PastryDonut_PSugar = new javax.swing.JRadioButton();
        PastryDonut_Glazed = new javax.swing.JRadioButton();
        PastryDonut_JellyFilled = new javax.swing.JRadioButton();
        PastryDonut_PSugarPrice = new javax.swing.JLabel();
        PastryDonut_GlazedPrice = new javax.swing.JLabel();
        PastryDonut_JellyFilledPrice = new javax.swing.JLabel();
        Optional_label15 = new javax.swing.JLabel();
        PastryTarts_Panel = new javax.swing.JPanel();
        PastryTartSection6 = new javax.swing.JLabel();
        PastryTart_LemonTart = new javax.swing.JRadioButton();
        PastryTarts_FruitTart = new javax.swing.JRadioButton();
        PastryTart_ChocolateTart = new javax.swing.JRadioButton();
        PastryTart_LemonTartPrice = new javax.swing.JLabel();
        PastryTart_FruitTartPrice = new javax.swing.JLabel();
        PastryTart_ChocolateTartPrice = new javax.swing.JLabel();
        Optional_label17 = new javax.swing.JLabel();
        PastryMuffin_Panel = new javax.swing.JPanel();
        PastryMuffinSection3 = new javax.swing.JLabel();
        PastryMuffin_Blueberry = new javax.swing.JRadioButton();
        PastryMuffin_ChocolateChip = new javax.swing.JRadioButton();
        PastryMuffin_BananaNut = new javax.swing.JRadioButton();
        PastryMuffin_blueberryPrice = new javax.swing.JLabel();
        PastryMuffin_ChocolateChipPrice = new javax.swing.JLabel();
        PastryMuffin_BananaNutPrice = new javax.swing.JLabel();
        Optional_label14 = new javax.swing.JLabel();
        PastrySpecial_Panel = new javax.swing.JPanel();
        PastrySpecialSection5 = new javax.swing.JLabel();
        PastrySpecial_macarons = new javax.swing.JRadioButton();
        PastrySpecial_Cannoli = new javax.swing.JRadioButton();
        PastrySpecial_Baklava = new javax.swing.JRadioButton();
        PastrySpecial_macaronsPrice = new javax.swing.JLabel();
        PastrySpecial_CannoliPrice = new javax.swing.JLabel();
        PastrySpecial_BaklavaPrice = new javax.swing.JLabel();
        Optional_label16 = new javax.swing.JLabel();
        Pastry_add_btn2 = new javax.swing.JButton();
        Smoothie = new javax.swing.JFrame();
        Smoothie_panel = new javax.swing.JPanel();
        Smoothie_title1 = new javax.swing.JLabel();
        Ttype_Panel1 = new javax.swing.JPanel();
        TType1 = new javax.swing.JLabel();
        TTypeR_lbl1 = new javax.swing.JLabel();
        apple_btn = new javax.swing.JRadioButton();
        apple_price = new javax.swing.JLabel();
        orange_but = new javax.swing.JRadioButton();
        orange_price = new javax.swing.JLabel();
        ctl_but = new javax.swing.JRadioButton();
        ctl_price = new javax.swing.JLabel();
        mp_but = new javax.swing.JRadioButton();
        mp_price = new javax.swing.JLabel();
        ob_but = new javax.swing.JRadioButton();
        ob_price = new javax.swing.JLabel();
        pl_but = new javax.swing.JRadioButton();
        pl_price = new javax.swing.JLabel();
        bs_but = new javax.swing.JRadioButton();
        bs_price = new javax.swing.JLabel();
        TSize_Panel1 = new javax.swing.JPanel();
        TSize_lbl1 = new javax.swing.JLabel();
        TSmall_size1 = new javax.swing.JRadioButton();
        Tmed_size1 = new javax.swing.JRadioButton();
        TBig_size1 = new javax.swing.JRadioButton();
        TMedSize_price1 = new javax.swing.JLabel();
        TBigSoze_price1 = new javax.swing.JLabel();
        TSmallSize_but1 = new javax.swing.JLabel();
        TSizeR_lbl1 = new javax.swing.JLabel();
        TSugar_lvl1 = new javax.swing.JPanel();
        Tsugar_lbl1 = new javax.swing.JLabel();
        TZero_btn1 = new javax.swing.JRadioButton();
        TLow_btn1 = new javax.swing.JRadioButton();
        TMed_btn1 = new javax.swing.JRadioButton();
        THIgh_btn1 = new javax.swing.JRadioButton();
        TFull_btn1 = new javax.swing.JRadioButton();
        TsugarR_lbl1 = new javax.swing.JLabel();
        Tmilk_lvl1 = new javax.swing.JPanel();
        topping = new javax.swing.JLabel();
        granola_btn = new javax.swing.JRadioButton();
        gojiberries_btn = new javax.swing.JRadioButton();
        optional_lbl1 = new javax.swing.JLabel();
        granola_price1 = new javax.swing.JLabel();
        almonds_price1 = new javax.swing.JLabel();
        almonds_btn = new javax.swing.JRadioButton();
        raspberries_btn = new javax.swing.JRadioButton();
        kiwi_btn = new javax.swing.JRadioButton();
        raspberries_price2 = new javax.swing.JLabel();
        kiwi_price3 = new javax.swing.JLabel();
        gojiberries_price2 = new javax.swing.JLabel();
        Cadd_btn2 = new javax.swing.JButton();
        Specialties = new javax.swing.JFrame();
        Specialties_panel = new javax.swing.JPanel();
        Specialties_title1 = new javax.swing.JLabel();
        Stype_Panel1 = new javax.swing.JPanel();
        Type1 = new javax.swing.JLabel();
        margarita_btn1 = new javax.swing.JRadioButton();
        mojito_btn1 = new javax.swing.JRadioButton();
        pinacolada_btn1 = new javax.swing.JRadioButton();
        margarita_price1 = new javax.swing.JLabel();
        mojito_price1 = new javax.swing.JLabel();
        pinacolada_price1 = new javax.swing.JLabel();
        STypeR_lbl1 = new javax.swing.JLabel();
        vodkasoda_btn2 = new javax.swing.JRadioButton();
        longisland_btn3 = new javax.swing.JRadioButton();
        vodkasoda_price2 = new javax.swing.JLabel();
        longisland_price3 = new javax.swing.JLabel();
        Cadd_btn3 = new javax.swing.JButton();
        Sizegroup = new javax.swing.ButtonGroup();
        Typegroup = new javax.swing.ButtonGroup();
        Sugarlevelgroup = new javax.swing.ButtonGroup();
        Icegroup = new javax.swing.ButtonGroup();
        Creamgroup = new javax.swing.ButtonGroup();
        Home_Panel = new javax.swing.JPanel();
        Order_Panel = new javax.swing.JPanel();
        Cof_But = new javax.swing.JButton();
        Other_But = new javax.swing.JButton();
        Smoothie_But = new javax.swing.JButton();
        Tea_But = new javax.swing.JButton();
        lbl_Drink = new javax.swing.JLabel();
        lbl_food = new javax.swing.JLabel();
        Pastry_But = new javax.swing.JButton();
        Bagel_But = new javax.swing.JButton();
        Member_lbl = new javax.swing.JLabel();
        Member_but = new javax.swing.JButton();
        Member_txt = new javax.swing.JTextField();
        Table_Pane = new javax.swing.JScrollPane();
        Order_Table = new javax.swing.JTable();
        Function_Panel = new javax.swing.JPanel();
        Pay_But = new javax.swing.JButton();
        subTotal_value = new javax.swing.JLabel();
        lbl_Subtotal = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Tax_value = new javax.swing.JLabel();
        lbl_Tax = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Receipt_Pane = new javax.swing.JScrollPane();
        Receipt_Text = new javax.swing.JTextPane();
        lbl_Subtotal1 = new javax.swing.JLabel();
        Discount_lbl = new javax.swing.JLabel();
        Delete_but = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        System_menu = new javax.swing.JMenu();
        Sys_item = new javax.swing.JMenuItem();
        Regis_item = new javax.swing.JMenuItem();

        Payment.setTitle("Payment");
        Payment.setResizable(false);
        Payment.setSize(new java.awt.Dimension(308, 414));

        jPanel1.setPreferredSize(new java.awt.Dimension(430, 386));

        Key1_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key1_but.setText("1");
        Key1_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key1_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key1_butActionPerformed(evt);
            }
        });

        Key2_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key2_but.setText("2");
        Key2_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key2_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key2_butActionPerformed(evt);
            }
        });

        Key3_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key3_but.setText("3");
        Key3_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key3_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key3_butActionPerformed(evt);
            }
        });

        Key4_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key4_but.setText("4");
        Key4_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key4_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key4_butActionPerformed(evt);
            }
        });

        Key5_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key5_but.setText("5");
        Key5_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key5_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key5_butActionPerformed(evt);
            }
        });

        Key6_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key6_but.setText("6");
        Key6_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key6_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key6_butActionPerformed(evt);
            }
        });

        Key7_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key7_but.setText("7");
        Key7_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key7_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key7_butActionPerformed(evt);
            }
        });

        Key8_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key8_but.setText("8");
        Key8_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key8_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key8_butActionPerformed(evt);
            }
        });

        Key9_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key9_but.setText("9");
        Key9_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key9_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key9_butActionPerformed(evt);
            }
        });

        KeyClear_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        KeyClear_but.setText("CLEAR");
        KeyClear_but.setPreferredSize(new java.awt.Dimension(70, 70));
        KeyClear_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyClear_butActionPerformed(evt);
            }
        });

        Key0_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Key0_but.setText("0");
        Key0_but.setPreferredSize(new java.awt.Dimension(70, 70));
        Key0_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Key0_butActionPerformed(evt);
            }
        });

        KeyDot_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        KeyDot_but.setText(".");
        KeyDot_but.setPreferredSize(new java.awt.Dimension(70, 70));
        KeyDot_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyDot_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Key1_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key2_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key3_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Key4_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key5_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key6_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Key7_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key8_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key9_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(KeyClear_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Key0_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KeyDot_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Key1_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key2_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key3_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Key4_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key5_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key6_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Key7_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key8_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key9_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KeyClear_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Key0_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KeyDot_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Total_lbl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Total_lbl.setText("Total       : RM");

        Amount_lbl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Amount_lbl.setText("Amount   : RM");

        Amount_txt.setEditable(false);
        jScrollPane1.setViewportView(Amount_txt);

        Total_txt.setEditable(false);
        jScrollPane2.setViewportView(Total_txt);

        KeyEnter_but.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        KeyEnter_but.setText("Pay");
        KeyEnter_but.setPreferredSize(new java.awt.Dimension(70, 70));
        KeyEnter_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyEnter_butActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Amount_lbl)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Total_lbl)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(KeyEnter_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KeyEnter_but, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Total_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Amount_lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PaymentLayout = new javax.swing.GroupLayout(Payment.getContentPane());
        Payment.getContentPane().setLayout(PaymentLayout);
        PaymentLayout.setHorizontalGroup(
            PaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaymentLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PaymentLayout.setVerticalGroup(
            PaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaymentLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Coffee.setResizable(false);
        Coffee.setSize(new java.awt.Dimension(890, 620));

        Coffee_title.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Coffee_title.setText("Coffee");

        Ctype_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Type.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Type.setText("Type");

        Typegroup.add(Latte_btn);
        Latte_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Latte_btn.setText("Latte");

        Typegroup.add(Espresso_btn);
        Espresso_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Espresso_btn.setText("Espresso");

        Typegroup.add(Cappuccino_btn);
        Cappuccino_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Cappuccino_btn.setText("Cappucinno");

        Latte_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Latte_price.setForeground(new java.awt.Color(102, 102, 102));
        Latte_price.setText("RM9.00");

        Espresso_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Espresso_price.setForeground(new java.awt.Color(102, 102, 102));
        Espresso_price.setText("RM8.00");

        Cappuccino_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Cappuccino_price.setForeground(new java.awt.Color(102, 102, 102));
        Cappuccino_price.setText("RM8.00");

        CTypeR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        CTypeR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        CTypeR_lbl.setText("(required)");

        javax.swing.GroupLayout Ctype_PanelLayout = new javax.swing.GroupLayout(Ctype_Panel);
        Ctype_Panel.setLayout(Ctype_PanelLayout);
        Ctype_PanelLayout.setHorizontalGroup(
            Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ctype_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Ctype_PanelLayout.createSequentialGroup()
                        .addComponent(Type)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CTypeR_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Ctype_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Ctype_PanelLayout.createSequentialGroup()
                                .addComponent(Espresso_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Espresso_price))
                            .addGroup(Ctype_PanelLayout.createSequentialGroup()
                                .addComponent(Latte_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Latte_price))
                            .addGroup(Ctype_PanelLayout.createSequentialGroup()
                                .addComponent(Cappuccino_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cappuccino_price)))))
                .addContainerGap())
        );
        Ctype_PanelLayout.setVerticalGroup(
            Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ctype_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Type)
                    .addComponent(CTypeR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Latte_btn)
                    .addComponent(Latte_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Espresso_btn)
                    .addComponent(Espresso_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ctype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cappuccino_btn)
                    .addComponent(Cappuccino_price))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        Csize_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Csize_Panel.setPreferredSize(new java.awt.Dimension(272, 242));

        CSize.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        CSize.setText("Size");

        CSizeR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        CSizeR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        CSizeR_lbl.setText("(required)");

        Sizegroup.add(Small_size_btn);
        Small_size_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Small_size_btn.setText("Small");

        Sizegroup.add(Med_size_btn);
        Med_size_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Med_size_btn.setText("Medium");

        Sizegroup.add(Big_size_btn);
        Big_size_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Big_size_btn.setText("Big");

        Cmed_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Cmed_price.setForeground(new java.awt.Color(102, 102, 102));
        Cmed_price.setText("+RM1.00");

        Cbig_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Cbig_price.setForeground(new java.awt.Color(102, 102, 102));
        Cbig_price.setText("+RM2.00");

        Csmall_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Csmall_price.setForeground(new java.awt.Color(102, 102, 102));
        Csmall_price.setText("+RM0.00");

        javax.swing.GroupLayout Csize_PanelLayout = new javax.swing.GroupLayout(Csize_Panel);
        Csize_Panel.setLayout(Csize_PanelLayout);
        Csize_PanelLayout.setHorizontalGroup(
            Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Csize_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Csize_PanelLayout.createSequentialGroup()
                        .addComponent(CSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CSizeR_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Csize_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Csize_PanelLayout.createSequentialGroup()
                                .addComponent(Med_size_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(Cmed_price))
                            .addGroup(Csize_PanelLayout.createSequentialGroup()
                                .addComponent(Small_size_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Csmall_price))
                            .addGroup(Csize_PanelLayout.createSequentialGroup()
                                .addComponent(Big_size_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Cbig_price)))))
                .addContainerGap())
        );
        Csize_PanelLayout.setVerticalGroup(
            Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Csize_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSize)
                    .addComponent(CSizeR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Small_size_btn)
                    .addComponent(Csmall_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Med_size_btn)
                    .addComponent(Cmed_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Csize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Big_size_btn)
                    .addComponent(Cbig_price))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Csugar_lvl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CSugar_lbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        CSugar_lbl.setText("Sugar Level");

        Sugarlevelgroup.add(Zero_lvl_btn);
        Zero_lvl_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Zero_lvl_btn.setText("0%");

        Sugarlevelgroup.add(Low_lvl_btn);
        Low_lvl_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Low_lvl_btn.setText("25%");

        Sugarlevelgroup.add(Med_lvl_btn);
        Med_lvl_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Med_lvl_btn.setText("50%");

        Sugarlevelgroup.add(High_lvl_btn);
        High_lvl_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        High_lvl_btn.setText("75%");

        Sugarlevelgroup.add(Full_lvl_btn);
        Full_lvl_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Full_lvl_btn.setText("100%");

        CSugarR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        CSugarR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        CSugarR_lbl.setText("(required)");

        javax.swing.GroupLayout Csugar_lvlLayout = new javax.swing.GroupLayout(Csugar_lvl);
        Csugar_lvl.setLayout(Csugar_lvlLayout);
        Csugar_lvlLayout.setHorizontalGroup(
            Csugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Csugar_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Csugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Csugar_lvlLayout.createSequentialGroup()
                        .addComponent(CSugar_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CSugarR_lbl))
                    .addGroup(Csugar_lvlLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Csugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Low_lvl_btn)
                            .addComponent(Zero_lvl_btn)
                            .addComponent(Med_lvl_btn)
                            .addComponent(High_lvl_btn)
                            .addComponent(Full_lvl_btn))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        Csugar_lvlLayout.setVerticalGroup(
            Csugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Csugar_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Csugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSugar_lbl)
                    .addComponent(CSugarR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Zero_lvl_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Low_lvl_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Med_lvl_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(High_lvl_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Full_lvl_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Cice_lvl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cice_lvl.setPreferredSize(new java.awt.Dimension(272, 242));

        CIce_lbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        CIce_lbl.setText("Ice Level");

        CIceR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        CIceR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        CIceR_lbl.setText("(required)");

        Icegroup.add(CHot_btn);
        CHot_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CHot_btn.setText("Hot");

        Icegroup.add(CCold_btn);
        CCold_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CCold_btn.setText("Cold (with ice)");

        Icegroup.add(CNo_ice_btn);
        CNo_ice_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CNo_ice_btn.setText("Cold (without ice)");

        Cice_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Cice_price.setForeground(new java.awt.Color(102, 102, 102));
        Cice_price.setText("+RM1.00");

        Ccold_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Ccold_price.setForeground(new java.awt.Color(102, 102, 102));
        Ccold_price.setText("+RM1.00");

        javax.swing.GroupLayout Cice_lvlLayout = new javax.swing.GroupLayout(Cice_lvl);
        Cice_lvl.setLayout(Cice_lvlLayout);
        Cice_lvlLayout.setHorizontalGroup(
            Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cice_lvlLayout.createSequentialGroup()
                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cice_lvlLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(CIce_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CIceR_lbl))
                    .addGroup(Cice_lvlLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CHot_btn)
                            .addGroup(Cice_lvlLayout.createSequentialGroup()
                                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CNo_ice_btn)
                                    .addComponent(CCold_btn))
                                .addGap(18, 18, 18)
                                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Cice_price)
                                    .addComponent(Ccold_price))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Cice_lvlLayout.setVerticalGroup(
            Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cice_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CIce_lbl)
                    .addComponent(CIceR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CHot_btn)
                .addGap(33, 33, 33)
                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CCold_btn)
                    .addComponent(Cice_price))
                .addGap(39, 39, 39)
                .addGroup(Cice_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CNo_ice_btn)
                    .addComponent(Ccold_price))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Ccream_lvl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Ccream_lvl.setPreferredSize(new java.awt.Dimension(272, 242));

        CCream.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        CCream.setText("Cream");

        Creamgroup.add(CCream_btn);
        CCream_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CCream_btn.setText("With cream");

        Creamgroup.add(CWithout_cream_btn);
        CWithout_cream_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CWithout_cream_btn.setText("Without cream");

        CCreamR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        CCreamR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        CCreamR_lbl.setText("(optional)");

        javax.swing.GroupLayout Ccream_lvlLayout = new javax.swing.GroupLayout(Ccream_lvl);
        Ccream_lvl.setLayout(Ccream_lvlLayout);
        Ccream_lvlLayout.setHorizontalGroup(
            Ccream_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ccream_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ccream_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Ccream_lvlLayout.createSequentialGroup()
                        .addComponent(CCream)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CCreamR_lbl))
                    .addGroup(Ccream_lvlLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Ccream_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CWithout_cream_btn)
                            .addComponent(CCream_btn))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        Ccream_lvlLayout.setVerticalGroup(
            Ccream_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ccream_lvlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Ccream_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CCream)
                    .addComponent(CCreamR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CCream_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CWithout_cream_btn))
        );

        Cadd_btn.setBackground(new java.awt.Color(255, 153, 51));
        Cadd_btn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Cadd_btn.setText("Add");
        Cadd_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cadd_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadd_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Coffee_panelLayout = new javax.swing.GroupLayout(Coffee_panel);
        Coffee_panel.setLayout(Coffee_panelLayout);
        Coffee_panelLayout.setHorizontalGroup(
            Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Coffee_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cadd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(Coffee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Ctype_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Coffee_panelLayout.createSequentialGroup()
                            .addGroup(Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Csize_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Ccream_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(Csugar_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Cice_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Coffee_title))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Coffee_panelLayout.setVerticalGroup(
            Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Coffee_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Coffee_title)
                .addGap(18, 18, 18)
                .addComponent(Ctype_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Coffee_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Coffee_panelLayout.createSequentialGroup()
                        .addComponent(Csize_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ccream_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Csugar_lvl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cice_lvl, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Cadd_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CoffeeLayout = new javax.swing.GroupLayout(Coffee.getContentPane());
        Coffee.getContentPane().setLayout(CoffeeLayout);
        CoffeeLayout.setHorizontalGroup(
            CoffeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoffeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Coffee_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        CoffeeLayout.setVerticalGroup(
            CoffeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoffeeLayout.createSequentialGroup()
                .addComponent(Coffee_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        Tea.setResizable(false);
        Tea.setSize(new java.awt.Dimension(1213, 573));

        Tea_title.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Tea_title.setText("Tea");

        Ttype_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TType.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TType.setText("Type");

        TTypeR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TTypeR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        TTypeR_lbl.setText("(required)");

        Typegroup.add(Egtea_btn);
        Egtea_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Egtea_btn.setText("Earl grey");

        Egtea_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Egtea_price.setForeground(new java.awt.Color(102, 102, 102));
        Egtea_price.setText("RM7.00");

        Typegroup.add(Jasmine_but);
        Jasmine_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Jasmine_but.setText("Jasmine");

        Jasmine_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Jasmine_price.setForeground(new java.awt.Color(102, 102, 102));
        Jasmine_price.setText("RM7.00");

        Typegroup.add(Oolong_but);
        Oolong_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Oolong_but.setText("Oolong peach");

        Oolong_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Oolong_price.setForeground(new java.awt.Color(102, 102, 102));
        Oolong_price.setText("RM8.00");

        javax.swing.GroupLayout Ttype_PanelLayout = new javax.swing.GroupLayout(Ttype_Panel);
        Ttype_Panel.setLayout(Ttype_PanelLayout);
        Ttype_PanelLayout.setHorizontalGroup(
            Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ttype_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Ttype_PanelLayout.createSequentialGroup()
                        .addComponent(TType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TTypeR_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Ttype_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Ttype_PanelLayout.createSequentialGroup()
                                .addComponent(Jasmine_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Jasmine_price))
                            .addGroup(Ttype_PanelLayout.createSequentialGroup()
                                .addComponent(Egtea_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Egtea_price))
                            .addGroup(Ttype_PanelLayout.createSequentialGroup()
                                .addComponent(Oolong_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 683, Short.MAX_VALUE)
                                .addComponent(Oolong_price)))))
                .addContainerGap())
        );
        Ttype_PanelLayout.setVerticalGroup(
            Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ttype_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TType)
                    .addComponent(TTypeR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Egtea_btn)
                    .addComponent(Egtea_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jasmine_but)
                    .addComponent(Jasmine_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Oolong_but)
                    .addComponent(Oolong_price))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TSize_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TSize_Panel.setPreferredSize(new java.awt.Dimension(272, 242));

        TSize_lbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TSize_lbl.setText("Size");

        Sizegroup.add(TSmall_size);
        TSmall_size.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TSmall_size.setText("Small");

        Sizegroup.add(Tmed_size);
        Tmed_size.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Tmed_size.setText("Medium");

        Sizegroup.add(TBig_size);
        TBig_size.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TBig_size.setText("Big");

        TMedSize_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TMedSize_price.setForeground(new java.awt.Color(102, 102, 102));
        TMedSize_price.setText("+RM1.00");

        TBigSoze_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TBigSoze_price.setForeground(new java.awt.Color(102, 102, 102));
        TBigSoze_price.setText("+RM2.00");

        TSmallSize_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TSmallSize_but.setForeground(new java.awt.Color(102, 102, 102));
        TSmallSize_but.setText("+RM0.00");

        TSizeR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TSizeR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        TSizeR_lbl.setText("(required)");

        javax.swing.GroupLayout TSize_PanelLayout = new javax.swing.GroupLayout(TSize_Panel);
        TSize_Panel.setLayout(TSize_PanelLayout);
        TSize_PanelLayout.setHorizontalGroup(
            TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSize_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TSize_PanelLayout.createSequentialGroup()
                        .addComponent(TSize_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TSizeR_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TSize_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TSize_PanelLayout.createSequentialGroup()
                                .addComponent(Tmed_size)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(TMedSize_price))
                            .addGroup(TSize_PanelLayout.createSequentialGroup()
                                .addComponent(TSmall_size)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TSmallSize_but))
                            .addGroup(TSize_PanelLayout.createSequentialGroup()
                                .addComponent(TBig_size)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TBigSoze_price)))))
                .addContainerGap())
        );
        TSize_PanelLayout.setVerticalGroup(
            TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSize_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TSize_lbl)
                    .addComponent(TSizeR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TSmall_size)
                    .addComponent(TSmallSize_but))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tmed_size)
                    .addComponent(TMedSize_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TBig_size)
                    .addComponent(TBigSoze_price)))
        );

        TSugar_lvl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Tsugar_lbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Tsugar_lbl.setText("Sugar Level");

        Sugarlevelgroup.add(TZero_btn);
        TZero_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TZero_btn.setText("0%");

        Sugarlevelgroup.add(TLow_btn);
        TLow_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TLow_btn.setText("25%");

        Sugarlevelgroup.add(TMed_btn);
        TMed_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TMed_btn.setText("50%");

        Sugarlevelgroup.add(THIgh_btn);
        THIgh_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        THIgh_btn.setText("75%");

        Sugarlevelgroup.add(TFull_btn);
        TFull_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TFull_btn.setText("100%");

        TsugarR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TsugarR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        TsugarR_lbl.setText("(required)");

        javax.swing.GroupLayout TSugar_lvlLayout = new javax.swing.GroupLayout(TSugar_lvl);
        TSugar_lvl.setLayout(TSugar_lvlLayout);
        TSugar_lvlLayout.setHorizontalGroup(
            TSugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSugar_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TSugar_lvlLayout.createSequentialGroup()
                        .addComponent(Tsugar_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TsugarR_lbl))
                    .addGroup(TSugar_lvlLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(TSugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TLow_btn)
                            .addComponent(TZero_btn)
                            .addComponent(TMed_btn)
                            .addComponent(THIgh_btn)
                            .addComponent(TFull_btn))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        TSugar_lvlLayout.setVerticalGroup(
            TSugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSugar_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSugar_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tsugar_lbl)
                    .addComponent(TsugarR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TZero_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TLow_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TMed_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(THIgh_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFull_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tice_lvl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tice_lvl1.setPreferredSize(new java.awt.Dimension(272, 242));

        TIce_lbl.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TIce_lbl.setText("Ice Level");

        Icegroup.add(THot_btn);
        THot_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        THot_btn.setSelected(true);
        THot_btn.setText("Hot");

        Icegroup.add(TCold_btn);
        TCold_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TCold_btn.setText("Cold (with ice)");

        Icegroup.add(TNo_ice_btn);
        TNo_ice_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TNo_ice_btn.setText("Cold (without ice)");

        Tice_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Tice_price.setForeground(new java.awt.Color(102, 102, 102));
        Tice_price.setText("+RM1.00");

        TWice_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TWice_price.setForeground(new java.awt.Color(102, 102, 102));
        TWice_price.setText("+RM1.00");

        TiceR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TiceR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        TiceR_lbl.setText("(required)");

        javax.swing.GroupLayout Tice_lvl1Layout = new javax.swing.GroupLayout(Tice_lvl1);
        Tice_lvl1.setLayout(Tice_lvl1Layout);
        Tice_lvl1Layout.setHorizontalGroup(
            Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tice_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tice_lvl1Layout.createSequentialGroup()
                        .addComponent(TIce_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TiceR_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Tice_lvl1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Tice_lvl1Layout.createSequentialGroup()
                                .addComponent(TCold_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Tice_price))
                            .addGroup(Tice_lvl1Layout.createSequentialGroup()
                                .addComponent(THot_btn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(Tice_lvl1Layout.createSequentialGroup()
                                .addComponent(TNo_ice_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(TWice_price)))))
                .addContainerGap())
        );
        Tice_lvl1Layout.setVerticalGroup(
            Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tice_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TIce_lbl)
                    .addComponent(TiceR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(THot_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TCold_btn)
                    .addComponent(Tice_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tice_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TNo_ice_btn)
                    .addComponent(TWice_price))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tmilk_lvl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tmilk_lvl.setPreferredSize(new java.awt.Dimension(272, 242));

        Milklevel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Milklevel.setText("Milk");

        Creamgroup.add(Milk_btn);
        Milk_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Milk_btn.setText("With milk");

        Creamgroup.add(Without_milk_btn);
        Without_milk_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Without_milk_btn.setText("Without milk");

        TMilkR_lbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TMilkR_lbl.setForeground(new java.awt.Color(102, 102, 102));
        TMilkR_lbl.setText("(optional)");

        Tmilk_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Tmilk_price.setForeground(new java.awt.Color(102, 102, 102));
        Tmilk_price.setText("+RM1.00");

        Twithout_milk_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Twithout_milk_price.setForeground(new java.awt.Color(102, 102, 102));
        Twithout_milk_price.setText("+RM0.00");

        javax.swing.GroupLayout Tmilk_lvlLayout = new javax.swing.GroupLayout(Tmilk_lvl);
        Tmilk_lvl.setLayout(Tmilk_lvlLayout);
        Tmilk_lvlLayout.setHorizontalGroup(
            Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                        .addComponent(Milklevel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TMilkR_lbl))
                    .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                                .addComponent(Without_milk_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(Twithout_milk_price))
                            .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                                .addComponent(Milk_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Tmilk_price)))))
                .addContainerGap())
        );
        Tmilk_lvlLayout.setVerticalGroup(
            Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tmilk_lvlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Milklevel)
                    .addComponent(TMilkR_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Milk_btn)
                    .addComponent(Tmilk_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Without_milk_btn)
                    .addComponent(Twithout_milk_price))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tadd_btn1.setBackground(new java.awt.Color(255, 153, 51));
        Tadd_btn1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Tadd_btn1.setText("Add");
        Tadd_btn1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tadd_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tadd_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tea_panelLayout = new javax.swing.GroupLayout(Tea_panel);
        Tea_panel.setLayout(Tea_panelLayout);
        Tea_panelLayout.setHorizontalGroup(
            Tea_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tea_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tea_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tea_panelLayout.createSequentialGroup()
                        .addGroup(Tea_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TSize_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tmilk_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(TSugar_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Tice_lvl1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Tea_title)
                    .addComponent(Ttype_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tea_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tadd_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        Tea_panelLayout.setVerticalGroup(
            Tea_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tea_panelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Tea_title)
                .addGap(18, 18, 18)
                .addComponent(Ttype_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Tea_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Tea_panelLayout.createSequentialGroup()
                        .addComponent(TSize_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tmilk_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TSugar_lvl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tice_lvl1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Tadd_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TeaLayout = new javax.swing.GroupLayout(Tea.getContentPane());
        Tea.getContentPane().setLayout(TeaLayout);
        TeaLayout.setHorizontalGroup(
            TeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tea_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TeaLayout.setVerticalGroup(
            TeaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tea_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Bagel.setResizable(false);
        Bagel.setSize(new java.awt.Dimension(1315, 577));

        Bagel_title1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Bagel_title1.setText("Bagel");

        BagelDough_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BagelDoughSection.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BagelDoughSection.setText("Dough");

        Typegroup.add(BagelDough_plain);
        BagelDough_plain.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_plain.setText("Plain");

        Typegroup.add(BagelDough_Mixed);
        BagelDough_Mixed.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_Mixed.setText("Mixed");

        Typegroup.add(BagelDough_WholeWheat);
        BagelDough_WholeWheat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_WholeWheat.setText("Whole Wheat");

        BagelDough_PlainPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_PlainPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelDough_PlainPrice.setText("RM9.00");

        BagelDough_MixedPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_MixedPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelDough_MixedPrice.setText("RM8.00");

        BagelDough_WholeWheatPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelDough_WholeWheatPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelDough_WholeWheatPrice.setText("RM8.00");

        required_label7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        required_label7.setForeground(new java.awt.Color(102, 102, 102));
        required_label7.setText("(required)");

        javax.swing.GroupLayout BagelDough_PanelLayout = new javax.swing.GroupLayout(BagelDough_Panel);
        BagelDough_Panel.setLayout(BagelDough_PanelLayout);
        BagelDough_PanelLayout.setHorizontalGroup(
            BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                        .addComponent(BagelDoughSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(required_label7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                                .addComponent(BagelDough_Mixed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelDough_MixedPrice))
                            .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                                .addComponent(BagelDough_plain)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelDough_PlainPrice))
                            .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                                .addComponent(BagelDough_WholeWheat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelDough_WholeWheatPrice)))))
                .addContainerGap())
        );
        BagelDough_PanelLayout.setVerticalGroup(
            BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelDough_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelDoughSection)
                    .addComponent(required_label7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelDough_plain)
                    .addComponent(BagelDough_PlainPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelDough_Mixed)
                    .addComponent(BagelDough_MixedPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelDough_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelDough_WholeWheat)
                    .addComponent(BagelDough_WholeWheatPrice))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        BagelSizePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BagelSizePanel.setPreferredSize(new java.awt.Dimension(272, 242));

        BagelSizeSection.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BagelSizeSection.setText("Size");

        Sizegroup.add(BagelSize_Small);
        BagelSize_Small.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_Small.setText("Small");

        Sizegroup.add(BagelSize_Medium);
        BagelSize_Medium.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_Medium.setText("Medium");

        Sizegroup.add(BagelSize_Big);
        BagelSize_Big.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_Big.setText("Big");

        BagelSize_Mediumprice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_Mediumprice.setForeground(new java.awt.Color(102, 102, 102));
        BagelSize_Mediumprice.setText("+RM1.00");

        BagelSize_BigPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_BigPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelSize_BigPrice.setText("+RM2.00");

        BagelSize_Smallprice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelSize_Smallprice.setForeground(new java.awt.Color(102, 102, 102));
        BagelSize_Smallprice.setText("+RM0.00");

        required_label10.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        required_label10.setForeground(new java.awt.Color(102, 102, 102));
        required_label10.setText("(required)");

        javax.swing.GroupLayout BagelSizePanelLayout = new javax.swing.GroupLayout(BagelSizePanel);
        BagelSizePanel.setLayout(BagelSizePanelLayout);
        BagelSizePanelLayout.setHorizontalGroup(
            BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelSizePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelSizePanelLayout.createSequentialGroup()
                        .addComponent(BagelSizeSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(required_label10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BagelSizePanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BagelSizePanelLayout.createSequentialGroup()
                                .addComponent(BagelSize_Medium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(BagelSize_Mediumprice))
                            .addGroup(BagelSizePanelLayout.createSequentialGroup()
                                .addComponent(BagelSize_Small)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelSize_Smallprice))
                            .addGroup(BagelSizePanelLayout.createSequentialGroup()
                                .addComponent(BagelSize_Big)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelSize_BigPrice)))))
                .addContainerGap())
        );
        BagelSizePanelLayout.setVerticalGroup(
            BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelSizePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelSizeSection)
                    .addComponent(required_label10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelSize_Small)
                    .addComponent(BagelSize_Smallprice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelSize_Medium)
                    .addComponent(BagelSize_Mediumprice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelSize_Big)
                    .addComponent(BagelSize_BigPrice))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        BagelFlavour_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BagelFlavourSection.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BagelFlavourSection.setText("Flavours");

        Sugarlevelgroup.add(BagelFlavour_Plain);
        BagelFlavour_Plain.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_Plain.setText("Plain");

        Sugarlevelgroup.add(BagelFlavour_MixedNuts);
        BagelFlavour_MixedNuts.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_MixedNuts.setText("Mixed nuts");

        Sugarlevelgroup.add(BagelFlavour_CinnamonRaisin);
        BagelFlavour_CinnamonRaisin.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_CinnamonRaisin.setText("Cinnamonon Raisin");

        Sugarlevelgroup.add(BagelFlavour_BlueBerry);
        BagelFlavour_BlueBerry.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_BlueBerry.setText("Blue Berry");

        Sugarlevelgroup.add(BagelFlavour_Asiago);
        BagelFlavour_Asiago.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_Asiago.setText("Asiago");

        required_label5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        required_label5.setForeground(new java.awt.Color(102, 102, 102));
        required_label5.setText("(required)");

        BagelFlavour_PlainPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_PlainPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelFlavour_PlainPrice.setText("+RM0.00");

        BagelFlavour_MixedNutsPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_MixedNutsPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelFlavour_MixedNutsPrice.setText("+RM3.00");

        BagelFlavour_BlueBerryPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_BlueBerryPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelFlavour_BlueBerryPrice.setText("+RM2.00");

        BagelFlavour_AsiagoPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_AsiagoPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelFlavour_AsiagoPrice.setText("+RM2.00");

        BagelFlavour_CinnamonRaisinPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelFlavour_CinnamonRaisinPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelFlavour_CinnamonRaisinPrice.setText("+RM2.00");

        javax.swing.GroupLayout BagelFlavour_PanelLayout = new javax.swing.GroupLayout(BagelFlavour_Panel);
        BagelFlavour_Panel.setLayout(BagelFlavour_PanelLayout);
        BagelFlavour_PanelLayout.setHorizontalGroup(
            BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                        .addComponent(BagelFlavourSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(required_label5))
                    .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                                .addComponent(BagelFlavour_Asiago)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelFlavour_AsiagoPrice))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BagelFlavour_PanelLayout.createSequentialGroup()
                                .addComponent(BagelFlavour_BlueBerry)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelFlavour_BlueBerryPrice))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BagelFlavour_PanelLayout.createSequentialGroup()
                                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BagelFlavour_MixedNuts)
                                    .addComponent(BagelFlavour_Plain)
                                    .addComponent(BagelFlavour_CinnamonRaisin))
                                .addGap(18, 18, 18)
                                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BagelFlavour_PlainPrice)
                                    .addComponent(BagelFlavour_MixedNutsPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BagelFlavour_CinnamonRaisinPrice))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        BagelFlavour_PanelLayout.setVerticalGroup(
            BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelFlavourSection)
                    .addComponent(required_label5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelFlavour_PanelLayout.createSequentialGroup()
                        .addComponent(BagelFlavour_Plain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BagelFlavour_MixedNuts)
                            .addComponent(BagelFlavour_MixedNutsPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BagelFlavour_CinnamonRaisin)
                            .addComponent(BagelFlavour_CinnamonRaisinPrice)))
                    .addComponent(BagelFlavour_PlainPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelFlavour_BlueBerry)
                    .addComponent(BagelFlavour_BlueBerryPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelFlavour_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelFlavour_Asiago)
                    .addComponent(BagelFlavour_AsiagoPrice))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        BagelToppingSauce_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BagelToppingSauce_Panel.setPreferredSize(new java.awt.Dimension(272, 242));

        BagelToppingSauceSection.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BagelToppingSauceSection.setText("Toppings Sauce");

        BagelToppingSauce_CreamCheese.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_CreamCheese.setText("Cream Chesee");

        BagelToppingSauce_PeanutButter.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_PeanutButter.setText("Peanut Butter");

        BagelToppingSauce_Butter.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_Butter.setText("Butter");

        BagelToppingSauce_PeanutButterPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_PeanutButterPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingSauce_PeanutButterPrice.setText("+RM1.00");

        BagelToppingSauce_ButterPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_ButterPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingSauce_ButterPrice.setText("+RM1.00");

        BagelToppingSauce_CreamCheesePrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_CreamCheesePrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingSauce_CreamCheesePrice.setText("+RM2.00");

        required_label6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        required_label6.setForeground(new java.awt.Color(102, 102, 102));
        required_label6.setText("( optional )");

        BagelToppingSauce_Jam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_Jam.setText("Jam");

        BagelToppingSauce_JamPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_JamPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingSauce_JamPrice.setText("+RM1.00");

        BagelToppingSauce_Honey.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_Honey.setText("Honey");

        BagelToppingSauce_HoneyPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingSauce_HoneyPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingSauce_HoneyPrice.setText("+RM2.00");

        javax.swing.GroupLayout BagelToppingSauce_PanelLayout = new javax.swing.GroupLayout(BagelToppingSauce_Panel);
        BagelToppingSauce_Panel.setLayout(BagelToppingSauce_PanelLayout);
        BagelToppingSauce_PanelLayout.setHorizontalGroup(
            BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                        .addComponent(BagelToppingSauceSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(required_label6)
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingSauce_Honey)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingSauce_HoneyPrice))
                            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingSauce_Jam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingSauce_JamPrice))
                            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingSauce_PeanutButter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingSauce_PeanutButterPrice))
                            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingSauce_CreamCheese)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingSauce_CreamCheesePrice))
                            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingSauce_Butter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingSauce_ButterPrice)))))
                .addContainerGap())
        );
        BagelToppingSauce_PanelLayout.setVerticalGroup(
            BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelToppingSauce_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauceSection)
                    .addComponent(required_label6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauce_CreamCheese)
                    .addComponent(BagelToppingSauce_CreamCheesePrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauce_PeanutButter)
                    .addComponent(BagelToppingSauce_PeanutButterPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauce_Butter)
                    .addComponent(BagelToppingSauce_ButterPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauce_Jam)
                    .addComponent(BagelToppingSauce_JamPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingSauce_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingSauce_Honey)
                    .addComponent(BagelToppingSauce_HoneyPrice))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        BagelToppingIngredient_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BagelToppingIngredient_Panel.setPreferredSize(new java.awt.Dimension(272, 242));

        BagelToppingIngredientSection.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BagelToppingIngredientSection.setText("Toppings Ingredients");

        BagelToppingIngredient_Lox.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_Lox.setText("Lox ( smoked salmon )");

        BagelToppingIngredient_Bacon.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_Bacon.setText("Bacon");

        BagelToppingIngredient_Egg.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_Egg.setText("Egg ");

        BagelToppingIngredient_BaconPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_BaconPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingIngredient_BaconPrice.setText("+RM2.00");

        BagelToppingIngredient_EggPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_EggPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingIngredient_EggPrice.setText("+RM1.00");

        BagelToppingIngredient_LoxPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_LoxPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingIngredient_LoxPrice.setText("+RM3.00");

        required_label11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        required_label11.setForeground(new java.awt.Color(102, 102, 102));
        required_label11.setText("( optional )");

        BagelToppingIngredient_Tomato.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_Tomato.setText("Tomato");

        BagelToppingIngredient_TomatoPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_TomatoPrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingIngredient_TomatoPrice.setText("+RM0.50");

        BagelToppingIngredient_Lettuce.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_Lettuce.setText("Lettuce");

        BagelToppingIngredient_LettucePrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        BagelToppingIngredient_LettucePrice.setForeground(new java.awt.Color(102, 102, 102));
        BagelToppingIngredient_LettucePrice.setText("+RM0.50");

        javax.swing.GroupLayout BagelToppingIngredient_PanelLayout = new javax.swing.GroupLayout(BagelToppingIngredient_Panel);
        BagelToppingIngredient_Panel.setLayout(BagelToppingIngredient_PanelLayout);
        BagelToppingIngredient_PanelLayout.setHorizontalGroup(
            BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                        .addComponent(BagelToppingIngredientSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(required_label11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingIngredient_Lettuce)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingIngredient_LettucePrice))
                            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingIngredient_Tomato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingIngredient_TomatoPrice))
                            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingIngredient_Bacon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingIngredient_BaconPrice))
                            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingIngredient_Lox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingIngredient_LoxPrice))
                            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                                .addComponent(BagelToppingIngredient_Egg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BagelToppingIngredient_EggPrice)))))
                .addContainerGap())
        );
        BagelToppingIngredient_PanelLayout.setVerticalGroup(
            BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelToppingIngredient_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredientSection)
                    .addComponent(required_label11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredient_Lox)
                    .addComponent(BagelToppingIngredient_LoxPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredient_Bacon)
                    .addComponent(BagelToppingIngredient_BaconPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredient_Egg)
                    .addComponent(BagelToppingIngredient_EggPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredient_Tomato)
                    .addComponent(BagelToppingIngredient_TomatoPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BagelToppingIngredient_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BagelToppingIngredient_Lettuce)
                    .addComponent(BagelToppingIngredient_LettucePrice))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        Bagel_add_btn1.setBackground(new java.awt.Color(255, 153, 51));
        Bagel_add_btn1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Bagel_add_btn1.setText("Add");
        Bagel_add_btn1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Bagel_add_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bagel_add_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Bagel_panelLayout = new javax.swing.GroupLayout(Bagel_panel);
        Bagel_panel.setLayout(Bagel_panelLayout);
        Bagel_panelLayout.setHorizontalGroup(
            Bagel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bagel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Bagel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BagelDough_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Bagel_panelLayout.createSequentialGroup()
                        .addComponent(Bagel_title1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Bagel_panelLayout.createSequentialGroup()
                        .addComponent(BagelSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BagelFlavour_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BagelToppingSauce_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BagelToppingIngredient_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Bagel_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Bagel_add_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        Bagel_panelLayout.setVerticalGroup(
            Bagel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Bagel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bagel_title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BagelDough_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(Bagel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BagelFlavour_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BagelSizePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BagelToppingSauce_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BagelToppingIngredient_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bagel_add_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BagelLayout = new javax.swing.GroupLayout(Bagel.getContentPane());
        Bagel.getContentPane().setLayout(BagelLayout);
        BagelLayout.setHorizontalGroup(
            BagelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BagelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Bagel_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BagelLayout.setVerticalGroup(
            BagelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BagelLayout.createSequentialGroup()
                .addComponent(Bagel_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        Pastry.setResizable(false);
        Pastry.setSize(new java.awt.Dimension(936, 526));

        Pastry_title2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Pastry_title2.setText("Pastry");

        PastryDonuts_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PastryDonutSection4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        PastryDonutSection4.setText("Donuts");

        PastryDonut_PSugar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_PSugar.setText("Powdered Sugar");

        PastryDonut_Glazed.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_Glazed.setText("Glazed");

        PastryDonut_JellyFilled.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_JellyFilled.setText("Jelly Filled");

        PastryDonut_PSugarPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_PSugarPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryDonut_PSugarPrice.setText("RM3.00");

        PastryDonut_GlazedPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_GlazedPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryDonut_GlazedPrice.setText("RM3.00");

        PastryDonut_JellyFilledPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryDonut_JellyFilledPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryDonut_JellyFilledPrice.setText("RM4.00");

        Optional_label15.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Optional_label15.setForeground(new java.awt.Color(102, 102, 102));
        Optional_label15.setText("(optional)");

        javax.swing.GroupLayout PastryDonuts_PanelLayout = new javax.swing.GroupLayout(PastryDonuts_Panel);
        PastryDonuts_Panel.setLayout(PastryDonuts_PanelLayout);
        PastryDonuts_PanelLayout.setHorizontalGroup(
            PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                        .addComponent(PastryDonutSection4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Optional_label15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryDonut_Glazed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryDonut_GlazedPrice))
                            .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryDonut_PSugar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                                .addComponent(PastryDonut_PSugarPrice))
                            .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryDonut_JellyFilled)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryDonut_JellyFilledPrice)))))
                .addContainerGap())
        );
        PastryDonuts_PanelLayout.setVerticalGroup(
            PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryDonuts_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryDonutSection4)
                    .addComponent(Optional_label15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryDonut_PSugar)
                    .addComponent(PastryDonut_PSugarPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryDonut_Glazed)
                    .addComponent(PastryDonut_GlazedPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryDonuts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryDonut_JellyFilled)
                    .addComponent(PastryDonut_JellyFilledPrice))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        PastryTarts_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PastryTartSection6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        PastryTartSection6.setText("Tarts");

        PastryTart_LemonTart.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTart_LemonTart.setText("Lemon Tart");

        PastryTarts_FruitTart.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTarts_FruitTart.setText("FruitTart");

        PastryTart_ChocolateTart.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTart_ChocolateTart.setText("Chocolate Tart");

        PastryTart_LemonTartPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTart_LemonTartPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryTart_LemonTartPrice.setText("RM3.00");

        PastryTart_FruitTartPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTart_FruitTartPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryTart_FruitTartPrice.setText("RM3.00");

        PastryTart_ChocolateTartPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryTart_ChocolateTartPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryTart_ChocolateTartPrice.setText("RM4.00");

        Optional_label17.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Optional_label17.setForeground(new java.awt.Color(102, 102, 102));
        Optional_label17.setText("(optional)");

        javax.swing.GroupLayout PastryTarts_PanelLayout = new javax.swing.GroupLayout(PastryTarts_Panel);
        PastryTarts_Panel.setLayout(PastryTarts_PanelLayout);
        PastryTarts_PanelLayout.setHorizontalGroup(
            PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                        .addComponent(PastryTartSection6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Optional_label17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryTarts_FruitTart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryTart_FruitTartPrice))
                            .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryTart_LemonTart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryTart_LemonTartPrice))
                            .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                                .addComponent(PastryTart_ChocolateTart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                                .addComponent(PastryTart_ChocolateTartPrice)))))
                .addContainerGap())
        );
        PastryTarts_PanelLayout.setVerticalGroup(
            PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryTarts_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryTartSection6)
                    .addComponent(Optional_label17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryTart_LemonTart)
                    .addComponent(PastryTart_LemonTartPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryTarts_FruitTart)
                    .addComponent(PastryTart_FruitTartPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryTarts_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryTart_ChocolateTart)
                    .addComponent(PastryTart_ChocolateTartPrice))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        PastryMuffin_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PastryMuffinSection3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        PastryMuffinSection3.setText("Muffins");

        PastryMuffin_Blueberry.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_Blueberry.setText("BlueBerry");

        PastryMuffin_ChocolateChip.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_ChocolateChip.setText("Chocolate Chip");

        PastryMuffin_BananaNut.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_BananaNut.setText("Jelly Filled");

        PastryMuffin_blueberryPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_blueberryPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryMuffin_blueberryPrice.setText("RM3.00");

        PastryMuffin_ChocolateChipPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_ChocolateChipPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryMuffin_ChocolateChipPrice.setText("RM3.00");

        PastryMuffin_BananaNutPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastryMuffin_BananaNutPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastryMuffin_BananaNutPrice.setText("RM4.00");

        Optional_label14.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Optional_label14.setForeground(new java.awt.Color(102, 102, 102));
        Optional_label14.setText("(optional)");

        javax.swing.GroupLayout PastryMuffin_PanelLayout = new javax.swing.GroupLayout(PastryMuffin_Panel);
        PastryMuffin_Panel.setLayout(PastryMuffin_PanelLayout);
        PastryMuffin_PanelLayout.setHorizontalGroup(
            PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                        .addComponent(PastryMuffinSection3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Optional_label14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                                .addComponent(PastryMuffin_ChocolateChip)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryMuffin_ChocolateChipPrice))
                            .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                                .addComponent(PastryMuffin_Blueberry)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                                .addComponent(PastryMuffin_blueberryPrice))
                            .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                                .addComponent(PastryMuffin_BananaNut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastryMuffin_BananaNutPrice)))))
                .addContainerGap())
        );
        PastryMuffin_PanelLayout.setVerticalGroup(
            PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryMuffin_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryMuffinSection3)
                    .addComponent(Optional_label14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryMuffin_Blueberry)
                    .addComponent(PastryMuffin_blueberryPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryMuffin_ChocolateChip)
                    .addComponent(PastryMuffin_ChocolateChipPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastryMuffin_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastryMuffin_BananaNut)
                    .addComponent(PastryMuffin_BananaNutPrice))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        PastrySpecial_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PastrySpecialSection5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        PastrySpecialSection5.setText("Specials");

        PastrySpecial_macarons.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_macarons.setText("Macarons");

        PastrySpecial_Cannoli.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_Cannoli.setText("Cannoli");

        PastrySpecial_Baklava.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_Baklava.setText("Baklava");

        PastrySpecial_macaronsPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_macaronsPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastrySpecial_macaronsPrice.setText("RM6.00");

        PastrySpecial_CannoliPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_CannoliPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastrySpecial_CannoliPrice.setText("RM6.00");

        PastrySpecial_BaklavaPrice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PastrySpecial_BaklavaPrice.setForeground(new java.awt.Color(102, 102, 102));
        PastrySpecial_BaklavaPrice.setText("RM4.00");

        Optional_label16.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        Optional_label16.setForeground(new java.awt.Color(102, 102, 102));
        Optional_label16.setText("(optional)");

        javax.swing.GroupLayout PastrySpecial_PanelLayout = new javax.swing.GroupLayout(PastrySpecial_Panel);
        PastrySpecial_Panel.setLayout(PastrySpecial_PanelLayout);
        PastrySpecial_PanelLayout.setHorizontalGroup(
            PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                        .addComponent(PastrySpecialSection5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Optional_label16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                                .addComponent(PastrySpecial_Cannoli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastrySpecial_CannoliPrice))
                            .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                                .addComponent(PastrySpecial_macarons)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastrySpecial_macaronsPrice))
                            .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                                .addComponent(PastrySpecial_Baklava)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PastrySpecial_BaklavaPrice)))))
                .addContainerGap())
        );
        PastrySpecial_PanelLayout.setVerticalGroup(
            PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastrySpecial_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastrySpecialSection5)
                    .addComponent(Optional_label16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastrySpecial_macarons)
                    .addComponent(PastrySpecial_macaronsPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastrySpecial_Cannoli)
                    .addComponent(PastrySpecial_CannoliPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PastrySpecial_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PastrySpecial_Baklava)
                    .addComponent(PastrySpecial_BaklavaPrice))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Pastry_add_btn2.setBackground(new java.awt.Color(255, 153, 51));
        Pastry_add_btn2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Pastry_add_btn2.setText("Add");
        Pastry_add_btn2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Pastry_add_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pastry_add_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Pastry_panelLayout = new javax.swing.GroupLayout(Pastry_panel);
        Pastry_panel.setLayout(Pastry_panelLayout);
        Pastry_panelLayout.setHorizontalGroup(
            Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pastry_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pastry_title2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Pastry_panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PastryDonuts_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PastryTarts_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PastryMuffin_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PastrySpecial_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Pastry_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pastry_add_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        Pastry_panelLayout.setVerticalGroup(
            Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pastry_panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PastrySpecial_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Pastry_panelLayout.createSequentialGroup()
                        .addComponent(Pastry_title2)
                        .addGap(18, 18, 18)
                        .addGroup(Pastry_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PastryDonuts_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PastryMuffin_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(PastryTarts_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Pastry_add_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PastryLayout = new javax.swing.GroupLayout(Pastry.getContentPane());
        Pastry.getContentPane().setLayout(PastryLayout);
        PastryLayout.setHorizontalGroup(
            PastryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pastry_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PastryLayout.setVerticalGroup(
            PastryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PastryLayout.createSequentialGroup()
                .addComponent(Pastry_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Smoothie.setResizable(false);
        Smoothie.setSize(new java.awt.Dimension(865, 690));

        Smoothie_title1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Smoothie_title1.setText("Smoothie");

        Ttype_Panel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TType1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TType1.setText("Type");

        TTypeR_lbl1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TTypeR_lbl1.setForeground(new java.awt.Color(102, 102, 102));
        TTypeR_lbl1.setText("(required)");

        Typegroup.add(apple_btn);
        apple_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        apple_btn.setSelected(true);
        apple_btn.setText("Apple");

        apple_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        apple_price.setForeground(new java.awt.Color(102, 102, 102));
        apple_price.setText("RM9.00");

        Typegroup.add(orange_but);
        orange_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orange_but.setText("Orange");

        orange_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orange_price.setForeground(new java.awt.Color(102, 102, 102));
        orange_price.setText("RM9.00");

        Typegroup.add(ctl_but);
        ctl_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ctl_but.setText("Cantaloupe");

        ctl_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ctl_price.setForeground(new java.awt.Color(102, 102, 102));
        ctl_price.setText("RM10.00");

        Typegroup.add(mp_but);
        mp_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mp_but.setText("Mango Passionfruit");

        mp_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mp_price.setForeground(new java.awt.Color(102, 102, 102));
        mp_price.setText("RM11.00");

        Typegroup.add(ob_but);
        ob_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ob_but.setText("Oaty banana");

        ob_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ob_price.setForeground(new java.awt.Color(102, 102, 102));
        ob_price.setText("RM11.00");

        Typegroup.add(pl_but);
        pl_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pl_but.setText("Pineapple lychee");

        pl_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pl_price.setForeground(new java.awt.Color(102, 102, 102));
        pl_price.setText("RM11.00");

        Typegroup.add(bs_but);
        bs_but.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bs_but.setText("Blueberry Strawberry ");

        bs_price.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        bs_price.setForeground(new java.awt.Color(102, 102, 102));
        bs_price.setText("RM12.00");

        javax.swing.GroupLayout Ttype_Panel1Layout = new javax.swing.GroupLayout(Ttype_Panel1);
        Ttype_Panel1.setLayout(Ttype_Panel1Layout);
        Ttype_Panel1Layout.setHorizontalGroup(
            Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                        .addComponent(TType1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TTypeR_lbl1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(orange_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orange_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(apple_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(apple_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(ctl_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ctl_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(bs_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 522, Short.MAX_VALUE)
                                .addComponent(bs_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(mp_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mp_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(ob_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ob_price))
                            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                                .addComponent(pl_but)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pl_price)))))
                .addContainerGap())
        );
        Ttype_Panel1Layout.setVerticalGroup(
            Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Ttype_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TType1)
                    .addComponent(TTypeR_lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apple_btn)
                    .addComponent(apple_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orange_but)
                    .addComponent(orange_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ctl_but)
                    .addComponent(ctl_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mp_but)
                    .addComponent(mp_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ob_but)
                    .addComponent(ob_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pl_but)
                    .addComponent(pl_price))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Ttype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bs_but)
                    .addComponent(bs_price))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        TSize_Panel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TSize_Panel1.setPreferredSize(new java.awt.Dimension(272, 242));

        TSize_lbl1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        TSize_lbl1.setText("Size");

        Sizegroup.add(TSmall_size1);
        TSmall_size1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TSmall_size1.setSelected(true);
        TSmall_size1.setText("Small");

        Sizegroup.add(Tmed_size1);
        Tmed_size1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Tmed_size1.setText("Medium");

        Sizegroup.add(TBig_size1);
        TBig_size1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TBig_size1.setText("Big");

        TMedSize_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TMedSize_price1.setForeground(new java.awt.Color(102, 102, 102));
        TMedSize_price1.setText("+RM1.00");

        TBigSoze_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TBigSoze_price1.setForeground(new java.awt.Color(102, 102, 102));
        TBigSoze_price1.setText("+RM2.00");

        TSmallSize_but1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TSmallSize_but1.setForeground(new java.awt.Color(102, 102, 102));
        TSmallSize_but1.setText("+RM0.00");

        TSizeR_lbl1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TSizeR_lbl1.setForeground(new java.awt.Color(102, 102, 102));
        TSizeR_lbl1.setText("(required)");

        javax.swing.GroupLayout TSize_Panel1Layout = new javax.swing.GroupLayout(TSize_Panel1);
        TSize_Panel1.setLayout(TSize_Panel1Layout);
        TSize_Panel1Layout.setHorizontalGroup(
            TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSize_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TSize_Panel1Layout.createSequentialGroup()
                        .addComponent(TSize_lbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TSizeR_lbl1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TSize_Panel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TSize_Panel1Layout.createSequentialGroup()
                                .addComponent(Tmed_size1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(TMedSize_price1))
                            .addGroup(TSize_Panel1Layout.createSequentialGroup()
                                .addComponent(TSmall_size1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TSmallSize_but1))
                            .addGroup(TSize_Panel1Layout.createSequentialGroup()
                                .addComponent(TBig_size1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TBigSoze_price1)))))
                .addContainerGap())
        );
        TSize_Panel1Layout.setVerticalGroup(
            TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSize_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TSize_lbl1)
                    .addComponent(TSizeR_lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TSmall_size1)
                    .addComponent(TSmallSize_but1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tmed_size1)
                    .addComponent(TMedSize_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TSize_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TBig_size1)
                    .addComponent(TBigSoze_price1))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        TSugar_lvl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Tsugar_lbl1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Tsugar_lbl1.setText("Sugar Level");

        Sugarlevelgroup.add(TZero_btn1);
        TZero_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TZero_btn1.setSelected(true);
        TZero_btn1.setText("0%");

        Sugarlevelgroup.add(TLow_btn1);
        TLow_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TLow_btn1.setText("25%");

        Sugarlevelgroup.add(TMed_btn1);
        TMed_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TMed_btn1.setText("50%");

        Sugarlevelgroup.add(THIgh_btn1);
        THIgh_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        THIgh_btn1.setText("75%");

        Sugarlevelgroup.add(TFull_btn1);
        TFull_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TFull_btn1.setText("100%");

        TsugarR_lbl1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        TsugarR_lbl1.setForeground(new java.awt.Color(102, 102, 102));
        TsugarR_lbl1.setText("(required)");

        javax.swing.GroupLayout TSugar_lvl1Layout = new javax.swing.GroupLayout(TSugar_lvl1);
        TSugar_lvl1.setLayout(TSugar_lvl1Layout);
        TSugar_lvl1Layout.setHorizontalGroup(
            TSugar_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSugar_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSugar_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TSugar_lvl1Layout.createSequentialGroup()
                        .addComponent(Tsugar_lbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TsugarR_lbl1))
                    .addGroup(TSugar_lvl1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(TSugar_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TLow_btn1)
                            .addComponent(TZero_btn1)
                            .addComponent(TMed_btn1)
                            .addComponent(THIgh_btn1)
                            .addComponent(TFull_btn1))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        TSugar_lvl1Layout.setVerticalGroup(
            TSugar_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TSugar_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TSugar_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tsugar_lbl1)
                    .addComponent(TsugarR_lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TZero_btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TLow_btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TMed_btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(THIgh_btn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFull_btn1)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        Tmilk_lvl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tmilk_lvl1.setPreferredSize(new java.awt.Dimension(272, 242));

        topping.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        topping.setText("Toppings");

        granola_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        granola_btn.setText("Granola");

        gojiberries_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        gojiberries_btn.setText("Goji berries");

        optional_lbl1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        optional_lbl1.setForeground(new java.awt.Color(102, 102, 102));
        optional_lbl1.setText("(optional)");

        granola_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        granola_price1.setForeground(new java.awt.Color(102, 102, 102));
        granola_price1.setText("+RM1.00");

        almonds_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        almonds_price1.setForeground(new java.awt.Color(102, 102, 102));
        almonds_price1.setText("+RM2.00");

        almonds_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        almonds_btn.setText("Almonds");

        raspberries_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        raspberries_btn.setText("Raspberries");

        kiwi_btn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        kiwi_btn.setText("Kiwi");

        raspberries_price2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        raspberries_price2.setForeground(new java.awt.Color(102, 102, 102));
        raspberries_price2.setText("+RM2.00");

        kiwi_price3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        kiwi_price3.setForeground(new java.awt.Color(102, 102, 102));
        kiwi_price3.setText("+RM2.00");

        gojiberries_price2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        gojiberries_price2.setForeground(new java.awt.Color(102, 102, 102));
        gojiberries_price2.setText("+RM1.00");

        javax.swing.GroupLayout Tmilk_lvl1Layout = new javax.swing.GroupLayout(Tmilk_lvl1);
        Tmilk_lvl1.setLayout(Tmilk_lvl1Layout);
        Tmilk_lvl1Layout.setHorizontalGroup(
            Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                        .addComponent(topping)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optional_lbl1))
                    .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                                .addComponent(gojiberries_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(gojiberries_price2))
                            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                                .addComponent(granola_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(granola_price1))
                            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                                .addComponent(kiwi_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(kiwi_price3))
                            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                                .addComponent(almonds_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(almonds_price1))
                            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                                .addComponent(raspberries_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(raspberries_price2)))))
                .addContainerGap())
        );
        Tmilk_lvl1Layout.setVerticalGroup(
            Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tmilk_lvl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topping)
                    .addComponent(optional_lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(granola_btn)
                    .addComponent(granola_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gojiberries_btn)
                    .addComponent(gojiberries_price2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(almonds_btn)
                    .addComponent(almonds_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(raspberries_btn)
                    .addComponent(raspberries_price2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Tmilk_lvl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kiwi_btn)
                    .addComponent(kiwi_price3))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        Cadd_btn2.setBackground(new java.awt.Color(255, 153, 51));
        Cadd_btn2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Cadd_btn2.setText("Add");
        Cadd_btn2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cadd_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadd_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Smoothie_panelLayout = new javax.swing.GroupLayout(Smoothie_panel);
        Smoothie_panel.setLayout(Smoothie_panelLayout);
        Smoothie_panelLayout.setHorizontalGroup(
            Smoothie_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Smoothie_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Smoothie_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Smoothie_title1)
                    .addGroup(Smoothie_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Smoothie_panelLayout.createSequentialGroup()
                            .addComponent(TSize_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TSugar_lvl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Tmilk_lvl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Ttype_Panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Smoothie_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cadd_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        Smoothie_panelLayout.setVerticalGroup(
            Smoothie_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Smoothie_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Smoothie_title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ttype_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Smoothie_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TSize_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TSugar_lvl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tmilk_lvl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cadd_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SmoothieLayout = new javax.swing.GroupLayout(Smoothie.getContentPane());
        Smoothie.getContentPane().setLayout(SmoothieLayout);
        SmoothieLayout.setHorizontalGroup(
            SmoothieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SmoothieLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Smoothie_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        SmoothieLayout.setVerticalGroup(
            SmoothieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SmoothieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Smoothie_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Specialties.setResizable(false);
        Specialties.setSize(new java.awt.Dimension(604, 372));

        Specialties_title1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Specialties_title1.setText("Specialties");

        Stype_Panel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Type1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Type1.setText("Type");

        Typegroup.add(margarita_btn1);
        margarita_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        margarita_btn1.setText("Margarita");

        Typegroup.add(mojito_btn1);
        mojito_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mojito_btn1.setText("Mojito");

        Typegroup.add(pinacolada_btn1);
        pinacolada_btn1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pinacolada_btn1.setText("Pina Colada");

        margarita_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        margarita_price1.setForeground(new java.awt.Color(102, 102, 102));
        margarita_price1.setText("RM12.00");

        mojito_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        mojito_price1.setForeground(new java.awt.Color(102, 102, 102));
        mojito_price1.setText("RM12.00");

        pinacolada_price1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pinacolada_price1.setForeground(new java.awt.Color(102, 102, 102));
        pinacolada_price1.setText("RM13.00");

        STypeR_lbl1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        STypeR_lbl1.setForeground(new java.awt.Color(102, 102, 102));
        STypeR_lbl1.setText("(required)");

        Typegroup.add(vodkasoda_btn2);
        vodkasoda_btn2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        vodkasoda_btn2.setText("Vodka Soda");

        Typegroup.add(longisland_btn3);
        longisland_btn3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        longisland_btn3.setText("Long island");

        vodkasoda_price2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        vodkasoda_price2.setForeground(new java.awt.Color(102, 102, 102));
        vodkasoda_price2.setText("RM15.00");

        longisland_price3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        longisland_price3.setForeground(new java.awt.Color(102, 102, 102));
        longisland_price3.setText("RM13.00");

        javax.swing.GroupLayout Stype_Panel1Layout = new javax.swing.GroupLayout(Stype_Panel1);
        Stype_Panel1.setLayout(Stype_Panel1Layout);
        Stype_Panel1Layout.setHorizontalGroup(
            Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Stype_Panel1Layout.createSequentialGroup()
                        .addComponent(Type1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(STypeR_lbl1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(Stype_Panel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                                .addComponent(mojito_btn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mojito_price1))
                            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                                .addComponent(margarita_btn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(margarita_price1))
                            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                                .addComponent(pinacolada_btn1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pinacolada_price1))
                            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                                .addComponent(longisland_btn3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(longisland_price3))
                            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                                .addComponent(vodkasoda_btn2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                                .addComponent(vodkasoda_price2)))))
                .addContainerGap())
        );
        Stype_Panel1Layout.setVerticalGroup(
            Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Stype_Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Type1)
                    .addComponent(STypeR_lbl1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(margarita_btn1)
                    .addComponent(margarita_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mojito_btn1)
                    .addComponent(mojito_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pinacolada_btn1)
                    .addComponent(pinacolada_price1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vodkasoda_btn2)
                    .addComponent(vodkasoda_price2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Stype_Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(longisland_btn3)
                    .addComponent(longisland_price3))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        Cadd_btn3.setBackground(new java.awt.Color(255, 153, 51));
        Cadd_btn3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Cadd_btn3.setText("Add");
        Cadd_btn3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Cadd_btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cadd_btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Specialties_panelLayout = new javax.swing.GroupLayout(Specialties_panel);
        Specialties_panel.setLayout(Specialties_panelLayout);
        Specialties_panelLayout.setHorizontalGroup(
            Specialties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Specialties_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Stype_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(597, 597, 597))
            .addGroup(Specialties_panelLayout.createSequentialGroup()
                .addGroup(Specialties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Specialties_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Specialties_title1))
                    .addGroup(Specialties_panelLayout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(Cadd_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Specialties_panelLayout.setVerticalGroup(
            Specialties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Specialties_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Specialties_title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stype_Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Cadd_btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SpecialtiesLayout = new javax.swing.GroupLayout(Specialties.getContentPane());
        Specialties.getContentPane().setLayout(SpecialtiesLayout);
        SpecialtiesLayout.setHorizontalGroup(
            SpecialtiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SpecialtiesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Specialties_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        SpecialtiesLayout.setVerticalGroup(
            SpecialtiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SpecialtiesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Specialties_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coffee Shop Cashier System");
        setFont(new java.awt.Font("Book Antiqua", 0, 10)); // NOI18N
        setResizable(false);

        Home_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Order_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 255, 204), null, null));

        Cof_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Cof_But.setText("Coffee");
        Cof_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cof_ButActionPerformed(evt);
            }
        });

        Other_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Other_But.setText("Others");
        Other_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Other_ButActionPerformed(evt);
            }
        });

        Smoothie_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Smoothie_But.setText("Smoothie");
        Smoothie_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Smoothie_ButActionPerformed(evt);
            }
        });

        Tea_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Tea_But.setText("Tea");
        Tea_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tea_ButActionPerformed(evt);
            }
        });

        lbl_Drink.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbl_Drink.setText("Drink");

        lbl_food.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbl_food.setText("Food");

        Pastry_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Pastry_But.setText("Pastry");
        Pastry_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pastry_ButActionPerformed(evt);
            }
        });

        Bagel_But.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        Bagel_But.setText("Bagel");
        Bagel_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bagel_ButActionPerformed(evt);
            }
        });

        Member_lbl.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        Member_lbl.setText("Member code: ");

        Member_but.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        Member_but.setText("Enter");
        Member_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Member_butActionPerformed(evt);
            }
        });

        Member_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Member_txtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout Order_PanelLayout = new javax.swing.GroupLayout(Order_Panel);
        Order_Panel.setLayout(Order_PanelLayout);
        Order_PanelLayout.setHorizontalGroup(
            Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Order_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Order_PanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Smoothie_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cof_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bagel_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Pastry_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Other_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tea_But, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(Order_PanelLayout.createSequentialGroup()
                        .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Drink, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_food, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Order_PanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(Member_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Member_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(Order_PanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(Member_but)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Order_PanelLayout.setVerticalGroup(
            Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Order_PanelLayout.createSequentialGroup()
                .addComponent(lbl_Drink)
                .addGap(14, 14, 14)
                .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cof_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tea_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Other_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Smoothie_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_food)
                .addGap(18, 18, 18)
                .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Pastry_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bagel_But, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(Order_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Member_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Member_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(Member_but)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        Home_Panel.add(Order_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 504));

        Order_Table.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Order_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item", "Condition", "Size", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Table_Pane.setViewportView(Order_Table);

        Home_Panel.add(Table_Pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 0, 351, 380));

        Function_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Function_Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));

        Pay_But.setBackground(new java.awt.Color(255, 153, 51));
        Pay_But.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Pay_But.setText("   PAY        RM 0.00");
        Pay_But.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Pay_But.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Pay_But.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Pay_But.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pay_ButActionPerformed(evt);
            }
        });

        subTotal_value.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        subTotal_value.setText("0.00");

        lbl_Subtotal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_Subtotal.setText("SubTotal :   RM");

        Tax_value.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Tax_value.setText("0.00");

        lbl_Tax.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_Tax.setForeground(new java.awt.Color(51, 0, 204));
        lbl_Tax.setText("Tax          :   RM ");

        Receipt_Pane.setViewportView(Receipt_Text);

        lbl_Subtotal1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_Subtotal1.setText("Discount :   RM");

        Discount_lbl.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Discount_lbl.setText("0.00");

        javax.swing.GroupLayout Function_PanelLayout = new javax.swing.GroupLayout(Function_Panel);
        Function_Panel.setLayout(Function_PanelLayout);
        Function_PanelLayout.setHorizontalGroup(
            Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(Function_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Function_PanelLayout.createSequentialGroup()
                        .addComponent(Receipt_Pane)
                        .addContainerGap())
                    .addGroup(Function_PanelLayout.createSequentialGroup()
                        .addComponent(Pay_But, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(Function_PanelLayout.createSequentialGroup()
                        .addComponent(lbl_Subtotal)
                        .addGap(111, 111, 111)
                        .addComponent(subTotal_value, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addGroup(Function_PanelLayout.createSequentialGroup()
                        .addComponent(lbl_Subtotal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Discount_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(Function_PanelLayout.createSequentialGroup()
                        .addComponent(lbl_Tax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Tax_value, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
        Function_PanelLayout.setVerticalGroup(
            Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Function_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Receipt_Pane, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Subtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Discount_lbl))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subTotal_value))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(Function_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Tax)
                    .addComponent(Tax_value))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pay_But, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Home_Panel.add(Function_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, -1, -1));

        Delete_but.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Delete_but.setText("REMOVE");
        Delete_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_butActionPerformed(evt);
            }
        });
        Home_Panel.add(Delete_but, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 386, 140, -1));

        System_menu.setText("System");

        Sys_item.setText("Cashier System");
        Sys_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sys_itemActionPerformed(evt);
            }
        });
        System_menu.add(Sys_item);

        Regis_item.setText("Member Registration");
        Regis_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Regis_itemActionPerformed(evt);
            }
        });
        System_menu.add(Regis_item);

        jMenuBar1.add(System_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Home_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Cof_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cof_ButActionPerformed
        Coffee.setVisible(true);
        Coffee.setLocationRelativeTo(null);;
    }//GEN-LAST:event_Cof_ButActionPerformed

    private void Bagel_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bagel_ButActionPerformed
         Bagel.setVisible(true);
         Bagel.setLocationRelativeTo(null);
    }//GEN-LAST:event_Bagel_ButActionPerformed

    private void Tea_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tea_ButActionPerformed
          Tea.setVisible(true);
          Tea.setLocationRelativeTo(null);
    }//GEN-LAST:event_Tea_ButActionPerformed

    private void Other_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Other_ButActionPerformed
          Specialties.setVisible(true);
          Specialties.setLocationRelativeTo(null);
    }//GEN-LAST:event_Other_ButActionPerformed

    private void Smoothie_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Smoothie_ButActionPerformed
         Smoothie.setVisible(true);
         Smoothie.setLocationRelativeTo(null);
    }//GEN-LAST:event_Smoothie_ButActionPerformed

    private void Pastry_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pastry_ButActionPerformed
         Pastry.setVisible(true);
         Pastry.setLocationRelativeTo(null);
    }//GEN-LAST:event_Pastry_ButActionPerformed

    private void Pay_ButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pay_ButActionPerformed
         String amount = Pay_But.getText();
        amount = amount.substring(17);
        
        Payment.setVisible(true);
        Payment.setLocationRelativeTo(null);
        
        Total_txt.setText(amount);
        Amount_txt.setText("0.00");
        
         
    }//GEN-LAST:event_Pay_ButActionPerformed

    private void Delete_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_butActionPerformed
        DefaultTableModel dt = (DefaultTableModel)Order_Table.getModel();
        
        int Remove_item = Order_Table.getSelectedRowCount();
        if(Remove_item >0)
        {
            dt.removeRow(Remove_item);
        }
        cart_cal();
    }//GEN-LAST:event_Delete_butActionPerformed

    private void Key1_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key1_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("1");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key1_butActionPerformed

    private void Key2_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key2_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("2");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key2_butActionPerformed

    private void Key3_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key3_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("3");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key3_butActionPerformed

    private void Key4_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key4_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("4");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key4_butActionPerformed

    private void Key5_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key5_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("5");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key5_butActionPerformed

    private void Key6_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key6_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("6");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key6_butActionPerformed

    private void Key7_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key7_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("7");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key7_butActionPerformed

    private void Key8_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key8_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("8");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key8_butActionPerformed

    private void Key9_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key9_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("9");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key9_butActionPerformed

    private void KeyClear_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyClear_butActionPerformed
        // TODO add your handling code here:
        String result = Amount_txt.getText();
        String output =  result.substring(0, result.length() - 1);
        Amount_txt.setText(output);
    }//GEN-LAST:event_KeyClear_butActionPerformed

    private void Key0_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Key0_butActionPerformed
        // TODO add your handling code here:
        String result = set_AmountText("0");
        Amount_txt.setText(result);
    }//GEN-LAST:event_Key0_butActionPerformed

    private void KeyDot_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyDot_butActionPerformed
        // TODO add your handling code here:
        String data = Amount_txt.getText();
        if(!data.contains("."))
        {
            String result_data= data + ".";
            Amount_txt.setText(result_data);
        }
    }//GEN-LAST:event_KeyDot_butActionPerformed

    private void KeyEnter_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyEnter_butActionPerformed
        // TODO add your handling code here:
        Double Amount = Double.valueOf(Amount_txt.getText());
        Double Total = Double.valueOf(Total_txt.getText());
        JFrame f = new JFrame();
        if(Amount-Total <0)
        {
            JOptionPane.showMessageDialog(f,"Invalid Input.");
            Amount_txt.setText("0.00");
        }
        else
        {
            JOptionPane.showMessageDialog(f,"Payment Success."); 
            try {
                bill_print();
            } catch (PrinterException ex) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
            }
            Payment.setVisible(false);
            reset_Val();
        }
        
    }//GEN-LAST:event_KeyEnter_butActionPerformed

    private void Cadd_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadd_btnActionPerformed
        // TODO add your handling code here:
        String coffee_name = null;
        double coffee_price = 0;
        String size = "small";
        String cond = "Hot";
        Boolean accept = true;
     
                if(Latte_btn.isSelected())
                {
                    coffee_name = "Latte";
                    coffee_price = 9;
                }
                else if( Espresso_btn.isSelected())
                {
                    coffee_name = "Espresso";
                    coffee_price = 8;
                }
                else if(Cappuccino_btn.isSelected())
                {
                    coffee_name = "Cappuccino";
                    coffee_price = 8;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Select coffee type","Coffee type error", JOptionPane.ERROR_MESSAGE);
                    accept = false;
                }

               if(Small_size_btn.isSelected())
               {
                    size = "Small";
               }
               else if(Med_size_btn.isSelected())
                {
                    coffee_price += 1;
                    size = "Medium";

                }
                else if(Big_size_btn.isSelected())
                {
                    coffee_price += 2;
                    size = "Big";
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Select coffee size","Coffee type error", JOptionPane.ERROR_MESSAGE);
                    accept = false;
                }


                if(!Zero_lvl_btn.isSelected() && !Low_lvl_btn.isSelected() && !Med_lvl_btn.isSelected() && !High_lvl_btn.isSelected() && !Full_lvl_btn.isSelected())
                {
                    JOptionPane.showMessageDialog(null, "Please Select Sugar Level","Sugar level error", JOptionPane.ERROR_MESSAGE);
                    accept = false;
                }
                
                if(CCold_btn.isSelected() || CNo_ice_btn.isSelected())
                {
                    cond = "Cold";
                    coffee_price += 1;
                }
                
                if(accept)
                {
                    addTable(coffee_name, coffee_price, cond, size);
                    Coffee.setVisible(false);
                }
            
    }//GEN-LAST:event_Cadd_btnActionPerformed

    private void Bagel_add_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bagel_add_btn1ActionPerformed
        // TODO add your handling code here:
        
         String bagel_name = "";
        double bagel_price = 0;
        String size = "small";
        String flavour = "Plain";
    
                if(BagelDough_plain.isSelected())
                {
                    bagel_name = "Plain";
                    bagel_price = 9;
                }
                else if( BagelDough_Mixed.isSelected())
                {
                    bagel_name = "Mixed";
                    bagel_price = 8;
                }
                else if(BagelDough_WholeWheat.isSelected())
                {
                    bagel_name = "Whole Wheat";
                    bagel_price = 8;
                }

               if(BagelSize_Small.isSelected())
               {
                    size = "Small";
               }
               else if(BagelSize_Medium.isSelected())
                {
                    bagel_price += 1;
                    size = "Medium";

                }
                else if(BagelSize_Big.isSelected())
                {
                    bagel_price += 2;
                    size = "Big";
                }
               
                if(BagelFlavour_Plain.isSelected())
                {
                    flavour = "plain";
                }
                else if (BagelFlavour_MixedNuts.isSelected())
                {
                    flavour = "Mixed Nuts";
                    bagel_price +=3;
                }
                else if(BagelFlavour_CinnamonRaisin.isSelected())
                {
                    flavour = "Cinnamon Raisin";
                    bagel_price +=2;
                }
                else if(BagelFlavour_BlueBerry.isSelected())
                {
                    flavour = "Blue Berry";
                    bagel_price +=2;
                }
                else if (BagelFlavour_BlueBerry.isSelected())
                {
                    flavour = "Blue Berry";
                    bagel_price +=2;
                }
                
                if(BagelToppingSauce_CreamCheese.isSelected()) bagel_price +=2;
                if(BagelToppingSauce_PeanutButter.isSelected()) bagel_price +=1;
                if(BagelToppingSauce_Butter.isSelected()) bagel_price +=1;
                if(BagelToppingSauce_Jam.isSelected()) bagel_price +=1;
                if(BagelToppingSauce_Honey.isSelected()) bagel_price +=2;
                
                if(BagelToppingIngredient_Lox.isSelected()) bagel_price +=3;
                if(BagelToppingIngredient_Lox.isSelected()) bagel_price +=2;
                if(BagelToppingIngredient_Lox.isSelected()) bagel_price +=1;
                if(BagelToppingIngredient_Tomato.isSelected()) bagel_price +=0.5;
                if(BagelToppingIngredient_Lettuce.isSelected()) bagel_price +=0.5;
                
                addTable(bagel_name,bagel_price,flavour,size);
                Bagel.setVisible(false);    
                
    }//GEN-LAST:event_Bagel_add_btn1ActionPerformed

    private void Pastry_add_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pastry_add_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pastry_add_btn2ActionPerformed


    private void Member_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Member_butActionPerformed
        // TODO add your handling code here:
       int member_code = Integer.parseInt(Member_txt.getText());
       member = false;
        try {
            cons = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/coffeshop_cashier","root","Leeterqin1126"); 
            PreparedStatement pst;
            ResultSet rs;
            
            pst = cons.prepareStatement("Select member_ID, member_LNAME, member_FNAME from member_table;");
            rs = pst.executeQuery();
            
            while(rs.next())
            {
               
               int id = rs.getInt("member_ID");
                
                if(id ==member_code )
                {
                    member = true;
                    String mss = "Member founded (" +rs.getString("member_LNAME")+ " " + rs.getString("member_FNAME") + ")";
                    JOptionPane.showMessageDialog(this,mss);
                    cart_cal();
                    break;
                }
            }
            
            if(member == false)
            {
                JOptionPane.showMessageDialog(this,"Member not founded");
                Member_txt.setText("");
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_Member_butActionPerformed

    private void Regis_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Regis_itemActionPerformed
        // TODO add your handling code here:
        new Registration().setVisible(true);
    }//GEN-LAST:event_Regis_itemActionPerformed

    private void Member_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Member_txtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            member = false;
            int member_code = Integer.parseInt(Member_txt.getText());
            try {
                cons = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/coffeshop_cashier","root","Leeterqin1126"); 
                PreparedStatement pst;
                ResultSet rs;

                pst = cons.prepareStatement("Select member_ID, member_LNAME, member_FNAME from member_table;");
                rs = pst.executeQuery();

                while(rs.next())
                {
                    int id = rs.getInt("member_ID");

                    if(id == member_code)
                    {
                        member = true;
                        String mss = "Member founded (" +rs.getString("member_LNAME")+ " " + rs.getString("member_FNAME") + ")";
                        JOptionPane.showMessageDialog(this,mss);
                        cart_cal();
                        break;
                    }
                }

                if(member == false)
                {
                    JOptionPane.showMessageDialog(this,"Member not founded");
                    Member_txt.setText("");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_Member_txtKeyPressed

    private void Sys_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sys_itemActionPerformed
        // TODO add your handling code her
        
    }//GEN-LAST:event_Sys_itemActionPerformed

    private void Tadd_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tadd_btn1ActionPerformed
        // TODO add your handling code here
         String tea_name = "";
        double tea_price = 0;
        String size = "small";
        String cond = "Hot";
        
     
                if(Egtea_btn.isSelected())
                {
                    tea_name = "Earl Grey";
                    tea_price = 7;
                }
                else if( Jasmine_but.isSelected())
                {
                    tea_name = "Jasmine";
                    tea_price = 8;
                }
                else if(Oolong_but.isSelected())
                {
                    tea_name = "Oolong Peach";
                    tea_price = 8;
                }

               if(TSmall_size.isSelected())
               {
                    size = "Small";
               }
               else if(Tmed_size.isSelected())
                {
                    tea_price += 1;
                    size = "Medium";

                }
                else if(TBig_size.isSelected())
                {
                    tea_price += 2;
                    size = "Big";
                }


                if(!TBig_size.isSelected() && !TLow_btn.isSelected() && !TMed_btn.isSelected() && !THIgh_btn.isSelected() && !TFull_btn.isSelected())
                {
                    JOptionPane.showMessageDialog(null, "Please Select Sugar Level","Sugar level error", JOptionPane.ERROR_MESSAGE);
                }
                
                if(TCold_btn.isSelected() || TNo_ice_btn.isSelected())
                {
                    cond = "Cold";
                    tea_price += 1;
                }
                
                    addTable(tea_name, tea_price, cond, size);
                    Tea.setVisible(false);

    }//GEN-LAST:event_Tadd_btn1ActionPerformed

    private void Cadd_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadd_btn2ActionPerformed
        // TODO add your handling code here:
         String Smoothie_name = null;
        double Smoothie_price = 0;
        String size = "small";
        String topping = "";
     
                if(apple_btn.isSelected())
                {
                    Smoothie_name = "Apple Smoothie";
                    Smoothie_price = 9;
                }
                else if( orange_but.isSelected())
                {
                    Smoothie_name = "Orange Smoothie";
                    Smoothie_price = 9;
                }
                else if(ctl_but.isSelected())
                {
                    Smoothie_name = "Cantaloupe Smoothie";
                    Smoothie_price = 10;
                }
                else if(mp_but.isSelected())
                {
                    Smoothie_name = "Mango Passionfruit Smoothie";
                    Smoothie_price = 11;
                }
                else if(ob_but.isSelected())
                {
                    Smoothie_name = "Oaty Banana Smoothie";
                    Smoothie_price = 11;
                }
                else if(pl_but.isSelected())
                {
                    Smoothie_name = "Pineaplle Lychee Smoothie";
                    Smoothie_price = 11;
                }
                else if(bs_but.isSelected())
                {
                    Smoothie_name = "Blueberry Strawberry Smoothie";
                    Smoothie_price = 12;
                }
                

               if(TSmall_size1.isSelected())
               {
                    size = "Small";
               }
               else if(Tmed_size1.isSelected())
                {
                    Smoothie_price+= 1;
                    size = "Medium";

                }
                else if(TBig_size1.isSelected())
                {
                    Smoothie_price+= 2;
                    size = "Big";
                }
                
                if(!TBig_size1.isSelected() && !TLow_btn1.isSelected() && !TMed_btn1.isSelected() && !THIgh_btn1.isSelected() && !TFull_btn1.isSelected())
                {
                    JOptionPane.showMessageDialog(null, "Please Select Sugar Level","Sugar level error", JOptionPane.ERROR_MESSAGE);
                }
                
                
                if(granola_btn.isSelected())Smoothie_price+= 1;   
                if(gojiberries_btn.isSelected())Smoothie_price+= 1;
                if(almonds_btn.isSelected())Smoothie_price+= 2; 
                if(raspberries_btn.isSelected())Smoothie_price+= 2;  
                if(kiwi_btn.isSelected())Smoothie_price+= 2;
                   
                 addTable(Smoothie_name,Smoothie_price,topping , size);
                 Smoothie.setVisible(false);
                
        
    }//GEN-LAST:event_Cadd_btn2ActionPerformed

    private void Cadd_btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cadd_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cadd_btn3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Amount_lbl;
    private javax.swing.JTextPane Amount_txt;
    private javax.swing.JFrame Bagel;
    private javax.swing.JLabel BagelDoughSection;
    private javax.swing.JRadioButton BagelDough_Mixed;
    private javax.swing.JLabel BagelDough_MixedPrice;
    private javax.swing.JPanel BagelDough_Panel;
    private javax.swing.JLabel BagelDough_PlainPrice;
    private javax.swing.JRadioButton BagelDough_WholeWheat;
    private javax.swing.JLabel BagelDough_WholeWheatPrice;
    private javax.swing.JRadioButton BagelDough_plain;
    private javax.swing.JLabel BagelFlavourSection;
    private javax.swing.JRadioButton BagelFlavour_Asiago;
    private javax.swing.JLabel BagelFlavour_AsiagoPrice;
    private javax.swing.JRadioButton BagelFlavour_BlueBerry;
    private javax.swing.JLabel BagelFlavour_BlueBerryPrice;
    private javax.swing.JRadioButton BagelFlavour_CinnamonRaisin;
    private javax.swing.JLabel BagelFlavour_CinnamonRaisinPrice;
    private javax.swing.JRadioButton BagelFlavour_MixedNuts;
    private javax.swing.JLabel BagelFlavour_MixedNutsPrice;
    private javax.swing.JPanel BagelFlavour_Panel;
    private javax.swing.JRadioButton BagelFlavour_Plain;
    private javax.swing.JLabel BagelFlavour_PlainPrice;
    private javax.swing.JPanel BagelSizePanel;
    private javax.swing.JLabel BagelSizeSection;
    private javax.swing.JRadioButton BagelSize_Big;
    private javax.swing.JLabel BagelSize_BigPrice;
    private javax.swing.JRadioButton BagelSize_Medium;
    private javax.swing.JLabel BagelSize_Mediumprice;
    private javax.swing.JRadioButton BagelSize_Small;
    private javax.swing.JLabel BagelSize_Smallprice;
    private javax.swing.JLabel BagelToppingIngredientSection;
    private javax.swing.JRadioButton BagelToppingIngredient_Bacon;
    private javax.swing.JLabel BagelToppingIngredient_BaconPrice;
    private javax.swing.JRadioButton BagelToppingIngredient_Egg;
    private javax.swing.JLabel BagelToppingIngredient_EggPrice;
    private javax.swing.JRadioButton BagelToppingIngredient_Lettuce;
    private javax.swing.JLabel BagelToppingIngredient_LettucePrice;
    private javax.swing.JRadioButton BagelToppingIngredient_Lox;
    private javax.swing.JLabel BagelToppingIngredient_LoxPrice;
    private javax.swing.JPanel BagelToppingIngredient_Panel;
    private javax.swing.JRadioButton BagelToppingIngredient_Tomato;
    private javax.swing.JLabel BagelToppingIngredient_TomatoPrice;
    private javax.swing.JLabel BagelToppingSauceSection;
    private javax.swing.JRadioButton BagelToppingSauce_Butter;
    private javax.swing.JLabel BagelToppingSauce_ButterPrice;
    private javax.swing.JRadioButton BagelToppingSauce_CreamCheese;
    private javax.swing.JLabel BagelToppingSauce_CreamCheesePrice;
    private javax.swing.JRadioButton BagelToppingSauce_Honey;
    private javax.swing.JLabel BagelToppingSauce_HoneyPrice;
    private javax.swing.JRadioButton BagelToppingSauce_Jam;
    private javax.swing.JLabel BagelToppingSauce_JamPrice;
    private javax.swing.JPanel BagelToppingSauce_Panel;
    private javax.swing.JRadioButton BagelToppingSauce_PeanutButter;
    private javax.swing.JLabel BagelToppingSauce_PeanutButterPrice;
    private javax.swing.JButton Bagel_But;
    private javax.swing.JButton Bagel_add_btn1;
    private javax.swing.JPanel Bagel_panel;
    private javax.swing.JLabel Bagel_title1;
    private javax.swing.JRadioButton Big_size_btn;
    private javax.swing.JRadioButton CCold_btn;
    private javax.swing.JLabel CCream;
    private javax.swing.JLabel CCreamR_lbl;
    private javax.swing.JRadioButton CCream_btn;
    private javax.swing.JRadioButton CHot_btn;
    private javax.swing.JLabel CIceR_lbl;
    private javax.swing.JLabel CIce_lbl;
    private javax.swing.JRadioButton CNo_ice_btn;
    private javax.swing.JLabel CSize;
    private javax.swing.JLabel CSizeR_lbl;
    private javax.swing.JLabel CSugarR_lbl;
    private javax.swing.JLabel CSugar_lbl;
    private javax.swing.JLabel CTypeR_lbl;
    private javax.swing.JRadioButton CWithout_cream_btn;
    private javax.swing.JButton Cadd_btn;
    private javax.swing.JButton Cadd_btn2;
    private javax.swing.JButton Cadd_btn3;
    private javax.swing.JRadioButton Cappuccino_btn;
    private javax.swing.JLabel Cappuccino_price;
    private javax.swing.JLabel Cbig_price;
    private javax.swing.JLabel Ccold_price;
    private javax.swing.JPanel Ccream_lvl;
    private javax.swing.JPanel Cice_lvl;
    private javax.swing.JLabel Cice_price;
    private javax.swing.JLabel Cmed_price;
    private javax.swing.JButton Cof_But;
    private javax.swing.JFrame Coffee;
    private javax.swing.JPanel Coffee_panel;
    private javax.swing.JLabel Coffee_title;
    private javax.swing.ButtonGroup Creamgroup;
    private javax.swing.JPanel Csize_Panel;
    private javax.swing.JLabel Csmall_price;
    private javax.swing.JPanel Csugar_lvl;
    private javax.swing.JPanel Ctype_Panel;
    private javax.swing.JButton Delete_but;
    private javax.swing.JLabel Discount_lbl;
    private javax.swing.JRadioButton Egtea_btn;
    private javax.swing.JLabel Egtea_price;
    private javax.swing.JRadioButton Espresso_btn;
    private javax.swing.JLabel Espresso_price;
    private javax.swing.JRadioButton Full_lvl_btn;
    private javax.swing.JPanel Function_Panel;
    private javax.swing.JRadioButton High_lvl_btn;
    private javax.swing.JPanel Home_Panel;
    private javax.swing.ButtonGroup Icegroup;
    private javax.swing.JRadioButton Jasmine_but;
    private javax.swing.JLabel Jasmine_price;
    private javax.swing.JButton Key0_but;
    private javax.swing.JButton Key1_but;
    private javax.swing.JButton Key2_but;
    private javax.swing.JButton Key3_but;
    private javax.swing.JButton Key4_but;
    private javax.swing.JButton Key5_but;
    private javax.swing.JButton Key6_but;
    private javax.swing.JButton Key7_but;
    private javax.swing.JButton Key8_but;
    private javax.swing.JButton Key9_but;
    private javax.swing.JButton KeyClear_but;
    private javax.swing.JButton KeyDot_but;
    private javax.swing.JButton KeyEnter_but;
    private javax.swing.JRadioButton Latte_btn;
    private javax.swing.JLabel Latte_price;
    private javax.swing.JRadioButton Low_lvl_btn;
    private javax.swing.JRadioButton Med_lvl_btn;
    private javax.swing.JRadioButton Med_size_btn;
    private javax.swing.JButton Member_but;
    private javax.swing.JLabel Member_lbl;
    private javax.swing.JTextField Member_txt;
    private javax.swing.JRadioButton Milk_btn;
    private javax.swing.JLabel Milklevel;
    private javax.swing.JRadioButton Oolong_but;
    private javax.swing.JLabel Oolong_price;
    private javax.swing.JLabel Optional_label14;
    private javax.swing.JLabel Optional_label15;
    private javax.swing.JLabel Optional_label16;
    private javax.swing.JLabel Optional_label17;
    private javax.swing.JPanel Order_Panel;
    private javax.swing.JTable Order_Table;
    private javax.swing.JButton Other_But;
    private javax.swing.JFrame Pastry;
    private javax.swing.JLabel PastryDonutSection4;
    private javax.swing.JRadioButton PastryDonut_Glazed;
    private javax.swing.JLabel PastryDonut_GlazedPrice;
    private javax.swing.JRadioButton PastryDonut_JellyFilled;
    private javax.swing.JLabel PastryDonut_JellyFilledPrice;
    private javax.swing.JRadioButton PastryDonut_PSugar;
    private javax.swing.JLabel PastryDonut_PSugarPrice;
    private javax.swing.JPanel PastryDonuts_Panel;
    private javax.swing.JLabel PastryMuffinSection3;
    private javax.swing.JRadioButton PastryMuffin_BananaNut;
    private javax.swing.JLabel PastryMuffin_BananaNutPrice;
    private javax.swing.JRadioButton PastryMuffin_Blueberry;
    private javax.swing.JRadioButton PastryMuffin_ChocolateChip;
    private javax.swing.JLabel PastryMuffin_ChocolateChipPrice;
    private javax.swing.JPanel PastryMuffin_Panel;
    private javax.swing.JLabel PastryMuffin_blueberryPrice;
    private javax.swing.JLabel PastrySpecialSection5;
    private javax.swing.JRadioButton PastrySpecial_Baklava;
    private javax.swing.JLabel PastrySpecial_BaklavaPrice;
    private javax.swing.JRadioButton PastrySpecial_Cannoli;
    private javax.swing.JLabel PastrySpecial_CannoliPrice;
    private javax.swing.JPanel PastrySpecial_Panel;
    private javax.swing.JRadioButton PastrySpecial_macarons;
    private javax.swing.JLabel PastrySpecial_macaronsPrice;
    private javax.swing.JLabel PastryTartSection6;
    private javax.swing.JRadioButton PastryTart_ChocolateTart;
    private javax.swing.JLabel PastryTart_ChocolateTartPrice;
    private javax.swing.JLabel PastryTart_FruitTartPrice;
    private javax.swing.JRadioButton PastryTart_LemonTart;
    private javax.swing.JLabel PastryTart_LemonTartPrice;
    private javax.swing.JRadioButton PastryTarts_FruitTart;
    private javax.swing.JPanel PastryTarts_Panel;
    private javax.swing.JButton Pastry_But;
    private javax.swing.JButton Pastry_add_btn2;
    private javax.swing.JPanel Pastry_panel;
    private javax.swing.JLabel Pastry_title2;
    private javax.swing.JButton Pay_But;
    private javax.swing.JFrame Payment;
    private javax.swing.JScrollPane Receipt_Pane;
    private javax.swing.JTextPane Receipt_Text;
    private javax.swing.JMenuItem Regis_item;
    private javax.swing.JLabel STypeR_lbl1;
    private javax.swing.ButtonGroup Sizegroup;
    private javax.swing.JRadioButton Small_size_btn;
    private javax.swing.JFrame Smoothie;
    private javax.swing.JButton Smoothie_But;
    private javax.swing.JPanel Smoothie_panel;
    private javax.swing.JLabel Smoothie_title1;
    private javax.swing.JFrame Specialties;
    private javax.swing.JPanel Specialties_panel;
    private javax.swing.JLabel Specialties_title1;
    private javax.swing.JPanel Stype_Panel1;
    private javax.swing.ButtonGroup Sugarlevelgroup;
    private javax.swing.JMenuItem Sys_item;
    private javax.swing.JMenu System_menu;
    private javax.swing.JLabel TBigSoze_price;
    private javax.swing.JLabel TBigSoze_price1;
    private javax.swing.JRadioButton TBig_size;
    private javax.swing.JRadioButton TBig_size1;
    private javax.swing.JRadioButton TCold_btn;
    private javax.swing.JRadioButton TFull_btn;
    private javax.swing.JRadioButton TFull_btn1;
    private javax.swing.JRadioButton THIgh_btn;
    private javax.swing.JRadioButton THIgh_btn1;
    private javax.swing.JRadioButton THot_btn;
    private javax.swing.JLabel TIce_lbl;
    private javax.swing.JRadioButton TLow_btn;
    private javax.swing.JRadioButton TLow_btn1;
    private javax.swing.JLabel TMedSize_price;
    private javax.swing.JLabel TMedSize_price1;
    private javax.swing.JRadioButton TMed_btn;
    private javax.swing.JRadioButton TMed_btn1;
    private javax.swing.JLabel TMilkR_lbl;
    private javax.swing.JRadioButton TNo_ice_btn;
    private javax.swing.JLabel TSizeR_lbl;
    private javax.swing.JLabel TSizeR_lbl1;
    private javax.swing.JPanel TSize_Panel;
    private javax.swing.JPanel TSize_Panel1;
    private javax.swing.JLabel TSize_lbl;
    private javax.swing.JLabel TSize_lbl1;
    private javax.swing.JLabel TSmallSize_but;
    private javax.swing.JLabel TSmallSize_but1;
    private javax.swing.JRadioButton TSmall_size;
    private javax.swing.JRadioButton TSmall_size1;
    private javax.swing.JPanel TSugar_lvl;
    private javax.swing.JPanel TSugar_lvl1;
    private javax.swing.JLabel TType;
    private javax.swing.JLabel TType1;
    private javax.swing.JLabel TTypeR_lbl;
    private javax.swing.JLabel TTypeR_lbl1;
    private javax.swing.JLabel TWice_price;
    private javax.swing.JRadioButton TZero_btn;
    private javax.swing.JRadioButton TZero_btn1;
    private javax.swing.JScrollPane Table_Pane;
    private javax.swing.JButton Tadd_btn1;
    private javax.swing.JLabel Tax_value;
    private javax.swing.JFrame Tea;
    private javax.swing.JButton Tea_But;
    private javax.swing.JPanel Tea_panel;
    private javax.swing.JLabel Tea_title;
    private javax.swing.JLabel TiceR_lbl;
    private javax.swing.JPanel Tice_lvl1;
    private javax.swing.JLabel Tice_price;
    private javax.swing.JRadioButton Tmed_size;
    private javax.swing.JRadioButton Tmed_size1;
    private javax.swing.JPanel Tmilk_lvl;
    private javax.swing.JPanel Tmilk_lvl1;
    private javax.swing.JLabel Tmilk_price;
    private javax.swing.JLabel Total_lbl;
    private javax.swing.JTextPane Total_txt;
    private javax.swing.JLabel TsugarR_lbl;
    private javax.swing.JLabel TsugarR_lbl1;
    private javax.swing.JLabel Tsugar_lbl;
    private javax.swing.JLabel Tsugar_lbl1;
    private javax.swing.JPanel Ttype_Panel;
    private javax.swing.JPanel Ttype_Panel1;
    private javax.swing.JLabel Twithout_milk_price;
    private javax.swing.JLabel Type;
    private javax.swing.JLabel Type1;
    private javax.swing.ButtonGroup Typegroup;
    private javax.swing.JRadioButton Without_milk_btn;
    private javax.swing.JRadioButton Zero_lvl_btn;
    private javax.swing.JRadioButton almonds_btn;
    private javax.swing.JLabel almonds_price1;
    private javax.swing.JRadioButton apple_btn;
    private javax.swing.JLabel apple_price;
    private javax.swing.JRadioButton bs_but;
    private javax.swing.JLabel bs_price;
    private javax.swing.JRadioButton ctl_but;
    private javax.swing.JLabel ctl_price;
    private javax.swing.JRadioButton gojiberries_btn;
    private javax.swing.JLabel gojiberries_price2;
    private javax.swing.JRadioButton granola_btn;
    private javax.swing.JLabel granola_price1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton kiwi_btn;
    private javax.swing.JLabel kiwi_price3;
    private javax.swing.JLabel lbl_Drink;
    private javax.swing.JLabel lbl_Subtotal;
    private javax.swing.JLabel lbl_Subtotal1;
    private javax.swing.JLabel lbl_Tax;
    private javax.swing.JLabel lbl_food;
    private javax.swing.JRadioButton longisland_btn3;
    private javax.swing.JLabel longisland_price3;
    private javax.swing.JRadioButton margarita_btn1;
    private javax.swing.JLabel margarita_price1;
    private javax.swing.JRadioButton mojito_btn1;
    private javax.swing.JLabel mojito_price1;
    private javax.swing.JRadioButton mp_but;
    private javax.swing.JLabel mp_price;
    private javax.swing.JRadioButton ob_but;
    private javax.swing.JLabel ob_price;
    private javax.swing.JLabel optional_lbl1;
    private javax.swing.JRadioButton orange_but;
    private javax.swing.JLabel orange_price;
    private javax.swing.JRadioButton pinacolada_btn1;
    private javax.swing.JLabel pinacolada_price1;
    private javax.swing.JRadioButton pl_but;
    private javax.swing.JLabel pl_price;
    private javax.swing.JRadioButton raspberries_btn;
    private javax.swing.JLabel raspberries_price2;
    private javax.swing.JLabel required_label10;
    private javax.swing.JLabel required_label11;
    private javax.swing.JLabel required_label5;
    private javax.swing.JLabel required_label6;
    private javax.swing.JLabel required_label7;
    private javax.swing.JLabel subTotal_value;
    private javax.swing.JLabel topping;
    private javax.swing.JRadioButton vodkasoda_btn2;
    private javax.swing.JLabel vodkasoda_price2;
    // End of variables declaration//GEN-END:variables
    
    private boolean member;
    private Connection cons;
}
