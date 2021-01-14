import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//This is a simple calculator
//Coder : Elea Dufresne 
//Date: 2020-07-17

   public class SimpleCalculator extends JFrame implements ActionListener{
    // Panel (container)
        JPanel container = new JPanel();
    // Display screen
        JLabel screen = new JLabel();
    //Commands and result
        String num1, op, num2;
    //Checks if there is already a dot
        private boolean isDot = false;

    // Buttons
     Button btn0 = new Button("0"); 
     Button btn1 = new Button("1"); 
     Button btn2 = new Button("2"); 
     Button btn3 = new Button("3"); 
     Button btn4 = new Button("4"); 
     Button btn5 = new Button("5"); 
     Button btn6 = new Button("6"); 
     Button btn7 = new Button("7"); 
     Button btn8 = new Button("8"); 
     Button btn9 = new Button("9"); 

     Button dot= new Button(".");
     Button equality = new Button("=");

     Button C = new Button("C");
     Button add = new Button("+");
     Button subtract = new Button("-");
     Button multiply = new Button("x");
     Button divide = new Button("÷");


   public SimpleCalculator() {
	
	//Setting the commands to nothing by default
    
          num1=op=num2="";

    // Window properties 
       this.setTitle("Calculator"); 
          this.setSize(600,700);
             this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Panel
        container.setBackground(new Color(51,102,0));
      container.setLayout(new BorderLayout());
      
/*** Layout and disposition of buttons and screen *********************************************************************************/

      // screen 
        screen.setPreferredSize(new Dimension(2,175)); //makes it visible
          screen.setFont( new Font ("Bahnschrift", Font.PLAIN, 50) );
            screen.setForeground(new Color(16, 37, 3));
              screen.setHorizontalAlignment(JLabel.RIGHT);
                screen.setBorder(new LineBorder(Color.black, 17)); //Note that EmptyBorder does not work
                 container.add(screen, BorderLayout.NORTH); //screen at the top

     
     // numbers, . and = 
       JPanel num = new JPanel();
     
         num.setBackground(Color.black);
     
            GridLayout grid1 = new GridLayout(4,3);
     
        grid1.setVgap(5);
        grid1.setHgap(5);
     
       num.setLayout(grid1);   
     
     num.add(btn7);
     num.add(btn8);
     num.add(btn9);
     num.add(btn4);
     num.add(btn5);
     num.add(btn6);
     num.add(btn1);
     num.add(btn2);
     num.add(btn3);
     num.add(btn0);
     num.add(dot);
     num.add(equality);
     
       num.setBorder(new EmptyBorder(40, 40, 40, 40));

         container.add(num, BorderLayout.CENTER); //numbers in the middle-left
 
     // operators and C
     JPanel operators = new JPanel();
     
      operators.setBackground(Color.black);
     
       GridLayout grid2 = new GridLayout(5,1);
     
        grid2.setVgap(5);
        grid2.setHgap(5);
     
     operators.setLayout(grid2);
   
       operators.add(C);
       operators.add(add);
       operators.add(subtract);
       operators.add(multiply);
       operators.add(divide);
       
     operators.setBorder(new EmptyBorder( 20, 0, 20, 40));
       
       operators.setPreferredSize(new Dimension(125,600));
       
     container.add(operators, BorderLayout.EAST); // operators on the right   
   
     //  So the button listener class can listen to the buttons
             btn0.addActionListener(this);
             btn1.addActionListener(this);
             btn2.addActionListener(this);
             btn3.addActionListener(this); 
             btn4.addActionListener(this); 
             btn5.addActionListener(this); 
             btn6.addActionListener(this); 
             btn7.addActionListener(this); 
             btn8.addActionListener(this); 
             btn9.addActionListener(this); 
             dot.addActionListener(this); 
             equality.addActionListener(this); 
             C.addActionListener(this); 
             add.addActionListener(this); 
             subtract.addActionListener(this); 
             multiply.addActionListener(this); 
             divide.addActionListener(this); 
     
         
         this.setContentPane(container);
         this.setVisible(true);	

    }

   //action listener interface's method
   public void actionPerformed(ActionEvent arg0) {
	//String corresponding to pushed button
        String command = arg0.getActionCommand();
    //if it's a number or a dot
        if ( command.charAt(0)>='0' && command.charAt(0)<='9' || command.charAt(0) == '.' ) {
    	    //if an operator has been pressed
    	    if(!op.equals("")) {
                if(command.charAt(0) == '.' && isDot){
                //prevents having two dots in a given number
                } else if(command.charAt(0) == '.' && !isDot){
                    num2 = num2 + command;
                    screen.setText(num2 + "  ");
                    isDot=true;
                }else{
                    num2 = num2 + command;
                    screen.setText(num2 + "  ");
                }
        //if no operator was pressed prior to this number
        }else
            if(command.charAt(0) == '.' && isDot){

            }else if(command.charAt(0) == '.' && !isDot){
                num1 = num1 + command;
                screen.setText(num1+"  ");
                isDot=true;
            }else {
                num1 = num1 + command;
                screen.setText(num1 + "  ");
            }
        }//if C is pressed
        else if ( command.charAt(0)=='C' ) {
    	//everything is reset
    	    num1=op=num2= "";
    	    screen.setText("");
            isDot=false;
        }//if equal is pressed
        else if ( command.charAt(0)=='=' )  {
            isDot=false;
            //if a given operator was pressed before the equal
            if ( op.equals("+") )  {
    	                                      num1=String.valueOf(Double.valueOf(num1)+Double.valueOf(num2));
    	                                      op=num2="";
    	                                      screen.setText(num1+"  ");
            } else if ( op.equals("-") ) {
    	                                      num1=String.valueOf(Double.valueOf(num1)-Double.valueOf(num2));
    	                                      op=num2="";
    	                                      screen.setText(num1+"  ");
    	                                      
            } else if ( op.equals("x") ) {
    	                                      num1=String.valueOf(Double.valueOf(num1)*Double.valueOf(num2));
    	                                      op=num2="";
    	                                      screen.setText(num1+"  ");
    
            } else if ( op.equals("÷") && !(num2.equals("0")) )  {
    	                                      num1=String.valueOf(Double.valueOf(num1)/Double.valueOf(num2));
    	                                      op=num2="";
    	                                      screen.setText(num1+"  ");
            } else if (op.equals("÷") && num2.equals("0"))  {
    	                                     num1=op=num2="";
    	                                     screen.setText("ERROR  "); 
            }
        } else {
            isDot=false;
            //if no second number was pressed
    	    if( op.equals("") || num2.equals(""))	op=command; //assures that op is an operator
            //else evaluate
            else {
                //if a given operator was pressed
    	        if ( op.equals("+") )  {
                    num1=String.valueOf(Double.valueOf(num1)+Double.valueOf(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1+"  ");

                } else if ( op.equals("-") ) {
                    num1=String.valueOf(Double.valueOf(num1)-Double.valueOf(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1+"  ");
                
                } else if ( op.equals("x") ) {
                    num1=String.valueOf(Double.valueOf(num1)*Double.valueOf(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1+"  ");

                }else if ( op.equals("÷") && !(num2.equals("0")) )  {
                    num1=String.valueOf(Double.valueOf(num1)/Double.valueOf(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1+"  ");
                }else if (op.equals("÷") && num2.equals("0"))  {
                    num1=op=num2="";
                    screen.setText("ERROR  ");
                }
            }//continues to do operations even if equals was not pressed
            screen.setText(num1+"  ");
        }
    	
    }
	public static void main(String[] args) {
		SimpleCalculator calculator = new SimpleCalculator();
	}
}
	