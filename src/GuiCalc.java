

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;


public class GuiCalc{
    
  
   static final GuiCalcLogic guiCalcLogic = new GuiCalcLogic();
    static JTextPane panel = new JTextPane();
   static JTextPane panel1 = new JTextPane();
   
   public static void setPanel(String str){
       panel.setText(str);
   }
   public static void setPanel1(String str){
       panel1.setText(str);
   }

    public static void addComponentsToPane(Container pane) {
        
        CalcActionListener cal = new CalcActionListener();
        
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        panel.setEditable(false);
        
        gc.weightx = 0.5;
        gc.gridwidth = 5;
        gc.gridx = 0;
        gc.gridy = 0;
        pane.add(panel, gc);

        panel1 = new JTextPane();
        
        panel1.setEditable(false);

        gc.weightx = 0.5;
        gc.gridwidth = 5;
        gc.gridx = 0;
        gc.gridy = 1;
        pane.add(panel1, gc);
        
        panel1.setText(guiCalcLogic.getCurrentNumDisplay());
        
        
        gc.insets = new Insets(4, 4, 4, 4);
        gc.gridwidth = 1;
        gc.weightx = 0.5;
        
         JButton button;
        //memory
        button = new JButton("MC");
        gc.gridx = 0;
        gc.gridy = 2;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("MR");
        gc.gridx = 1;
        gc.gridy = 2;
        pane.add(button, gc);
        button.addActionListener(cal);
        
        button = new JButton("MS");
        gc.gridx = 2;
        gc.gridy = 2;
        pane.add(button, gc);
         button.addActionListener(cal);
         
         
        button = new JButton("M+");
        gc.gridx = 3;
        gc.gridy = 2;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        
        button = new JButton("M-");
        gc.gridx = 4;
        gc.gridy = 2;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        //operation
        //\u2190 would use this but action command doesn't recognize
        button = new JButton("<--");
        gc.gridx = 0;
        gc.gridy = 3;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("CE");
        gc.gridx = 1;
        gc.gridy = 3;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("C");
        gc.gridx = 2;
        gc.gridy = 3;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("\u00B1");
        gc.gridx = 3;
        gc.gridy = 3;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("Sqr");
        gc.gridx = 4;
        gc.gridy = 3;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        //newLine 4 
        button = new JButton("7");
        gc.gridx = 0;
        gc.gridy = 4;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("8");
        gc.gridx = 1;
        gc.gridy = 4;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("9");
        gc.gridx = 2;
        gc.gridy = 4;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("/");
        gc.gridx = 3;
        gc.gridy = 4;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("\u0025");
        gc.gridx = 4;
        gc.gridy = 4;
        button.addActionListener(cal);
        pane.add(button, gc);
       
        //newline 5
        button = new JButton("4");
        gc.gridx = 0;
        gc.gridy = 5;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("5");
        gc.gridx = 1;
        gc.gridy = 5;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("6");
        gc.gridx = 2;
        gc.gridy = 5;
        button.addActionListener(cal);
        pane.add(button, gc);
        button = new JButton("\u002A");
        gc.gridx = 3;
        gc.gridy = 5;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("1/x");
        gc.gridx = 4;
        gc.gridy = 5;
        button.addActionListener(cal);
        pane.add(button, gc);
       //  
        button = new JButton("1");
        gc.gridx = 0;
        gc.gridy = 6;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("2");
        gc.gridx = 1;
        gc.gridy = 6;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("3");
        gc.gridx = 2;
        gc.gridy = 6;
        button.addActionListener(cal);
        pane.add(button, gc);
        button = new JButton("-");
        gc.gridx = 3;
        gc.gridy = 6;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("=");
        gc.gridx = 4;
        gc.gridy = 6;
        gc.gridheight = 2;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        //
        gc.gridheight = 1;
        button = new JButton("0");
        gc.gridwidth = 2;
        gc.gridx = 0;
        gc.gridy = 7;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        gc.gridwidth = 1;
        button = new JButton(".");
        gc.gridx = 2;
        gc.gridy = 7;
        gc.gridheight = 2;
        button.addActionListener(cal);
        pane.add(button, gc);
        
        button = new JButton("+");
        gc.gridx = 3;
        gc.gridy = 7;
        button.addActionListener(cal);
        pane.add(button, gc);
       
    }
    
    
    
    
    private static class CalcActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton jb = (JButton)ae.getSource();
           guiCalcLogic.parseInput(jb.getActionCommand());
            panel.setText(guiCalcLogic.getEquationDisplay());
            panel1.setText(guiCalcLogic.getCurrentNumDisplay());
        }
        
    }

    private static void createAndShowGUI() {
        
        JFrame frame = new JFrame("Calculator");
        frame.setSize(250, 300);
        frame.setResizable(false);
    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());

       
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
       
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

}
