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

public class SimpleCalculator extends JFrame implements ActionListener {
    // Panel (container)
    JPanel container = new JPanel();
    // Display screen
    JLabel screen = new JLabel();
    //Commands and result
    String num1, op, num2;
    //Checks if there is already a dot
    private boolean hasDot = false;

    // buttons
    Button[] numbers = new Button[10];
    Button dot = new Button(".");
    Button equality = new Button("=");
    Button C = new Button("C");
    Button add = new Button("+");
    Button subtract = new Button("-");
    Button multiply = new Button("x");
    Button divide = new Button("÷");

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
    }

    public SimpleCalculator() {
        //Setting the commands to nothing by default
        num1 = op = num2 = "";

        // Window properties
        this.setTitle("Calculator");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel
        container.setBackground(new Color(51, 102, 0));
        container.setLayout(new BorderLayout());

        /*** Layout and disposition of buttons and screen **********************************************************/

        // screen
        screen.setPreferredSize(new Dimension(2, 175)); //makes it visible
        screen.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
        screen.setForeground(new Color(16, 37, 3));
        screen.setHorizontalAlignment(JLabel.RIGHT);
        screen.setBorder(new LineBorder(Color.black, 17)); //Note that EmptyBorder does not work
        container.add(screen, BorderLayout.NORTH); //screen at the top

        // layout and panel for the numpad, "." and "="
        GridLayout grid1 = new GridLayout(4, 3);
        grid1.setVgap(5);
        grid1.setHgap(5);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setLayout(grid1);

        // instantiate the numpad
        for(int i=0; i<10; i++) {
            numbers[i] = new Button(Integer.toString(i));
            numbers[i].addActionListener(this);
            buttonPanel.add(numbers[i]);
        }
        dot.addActionListener(this);
        buttonPanel.add(dot);
        equality.addActionListener(this);
        buttonPanel.add(equality);

        // format it and then add it to the container
        buttonPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        container.add(buttonPanel, BorderLayout.CENTER);

        // layout and panel for the operators and C
        GridLayout grid2 = new GridLayout(5, 1);
        grid2.setVgap(5);
        grid2.setHgap(5);
        JPanel operators = new JPanel();
        operators.setBackground(Color.black);
        operators.setLayout(grid2);
        // add the operators
        C.addActionListener(this);
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        operators.add(C);
        operators.add(add);
        operators.add(subtract);
        operators.add(multiply);
        operators.add(divide);
        // format and then add the panel to the container
        operators.setBorder(new EmptyBorder(20, 0, 20, 40));
        operators.setPreferredSize(new Dimension(125, 600));
        container.add(operators, BorderLayout.EAST);

        // put everything together
        this.setContentPane(container);
        this.setVisible(true);
    }

    // helper method for setting the screen display
    private void setDisplay(String command, String num){
        if (command.charAt(0) == '.' && hasDot) {
        // if we are not introducing a second dot
        } else if (command.charAt(0) == '.' && !hasDot) {
            num = num + command;
            screen.setText(num + "  ");
            hasDot = true;
        } else {
            num = num + command;
            screen.setText(num + "  ");
        }
    }

    private void compute(String cmd){
        if (op.equals("+")) {
            num1 = String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2));
            op = cmd;
            num2 = "";
            screen.setText(num1 + "  ");
        } else if (op.equals("-")) {
            num1 = String.valueOf(Double.parseDouble(num1) - Double.parseDouble(num2));
            op = cmd;
            num2 = "";
            screen.setText(num1 + "  ");
        } else if (op.equals("x")) {
            num1 = String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2));
            op = cmd;
            num2 = "";
            screen.setText(num1 + "  ");
        } else if (op.equals("÷")) {
            // edge case : we are trying to divide by zero
            if( num2.equals("0")) {
                num1 = op = num2 = "";
                screen.setText("ERROR : NaN");
            } else {
                num1 = String.valueOf(Double.parseDouble(num1) / Double.parseDouble(num2));
                op = cmd;
                num2 = "";
                screen.setText(num1 + "  ");
            }
        }
    }

    //action listener interface's method
    public void actionPerformed(ActionEvent arg0) {
        // button the user pressed
        String command = arg0.getActionCommand();
        // if the command is a digit or a dot
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.charAt(0) == '.') {
            // if no operator was pressed before to this digit
            if (op.equals("")) setDisplay(command, num1);
                // if an operator had been pressed
            else setDisplay(command, num2);
        // if C was pressed
        } else if (command.charAt(0) == 'C') {
            // reset everything
            num1 = op = num2 = "";
            screen.setText("");
            hasDot = false;
        } // if the equal operator is pressed
        else if (command.charAt(0) == '=') {
            // perform the computation
            hasDot = false;
            if (op.equals("+")) {
                num1 = String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2));
                op = num2 = "";
                screen.setText(num1 + "  ");
            } else if (op.equals("-")) {
                num1 = String.valueOf(Double.parseDouble(num1) - Double.parseDouble(num2));
                op = num2 = "";
                screen.setText(num1 + "  ");
            } else if (op.equals("x")) {
                num1 = String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2));
                op = num2 = "";
                screen.setText(num1 + "  ");
            } else if (op.equals("÷")) {
                // edge case : we are trying to divide by zero
                if( num2.equals("0")) {
                    num1 = op = num2 = "";
                    screen.setText("ERROR : NaN");
                } else {
                    num1 = String.valueOf(Double.parseDouble(num1) / Double.parseDouble(num2));
                    op = num2 = "";
                    screen.setText(num1 + "  ");
                }
            }
        // if the command is an operator
        } else {
            hasDot = false;
            if (op.equals("") || num2.equals(""))
                op = command;
            else {
                if (op.equals("+")) {
                    num1 = String.valueOf(Double.parseDouble(num1) + Double.parseDouble(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1 + "  ");

                } else if (op.equals("-")) {
                    num1 = String.valueOf(Double.parseDouble(num1) - Double.parseDouble(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1 + "  ");

                } else if (op.equals("x")) {
                    num1 = String.valueOf(Double.parseDouble(num1) * Double.parseDouble(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1 + "  ");

                } else if (op.equals("÷") && !(num2.equals("0"))) {
                    num1 = String.valueOf(Double.parseDouble(num1) / Double.parseDouble(num2));
                    op = command;
                    num2 = "";
                    screen.setText(num1 + "  ");
                } else if (op.equals("÷") && num2.equals("0")) {
                    num1 = op = num2 = "";
                    screen.setText("ERROR  ");
                }
            }//continues to do operations even if equals was not pressed
            screen.setText(num1 + "  ");
        }
    }
}
	