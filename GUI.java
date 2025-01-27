
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {

    static public JFrame frame;
    static public JPanel panel;
    static public JLabel label;
    static public JTextField field;
    static public JButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    static public JButton buttonPlus, buttonMinus, buttonTimes, buttonDivide, buttonEnter, buttonClear;
    static public String currentText;
    static public Integer clicks = 0;
    static public Double firstNumber;
    static public Double secondNumber;
    static public Double result;
    static public Character action;
    static public boolean startNew = true;
    static public boolean isEntered = false;

    public static void main(String[] args) {

        //creating the GUI
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(290, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        label = new JLabel("Laskin");
        label.setBounds(20, 10, 40, 25);
        panel.add(label);

        field = new JTextField();
        field.setBounds(20, 30, 230, 30);
        panel.add(field);

        buttonPlus = new JButton("+");
        buttonPlus.setBounds(20, 70, 50, 50);
        buttonPlus.addActionListener(new GUI());
        panel.add(buttonPlus);

        buttonMinus = new JButton("-");
        buttonMinus.setBounds(80, 70, 50, 50);
        buttonMinus.addActionListener(new GUI());
        panel.add(buttonMinus);

        buttonTimes = new JButton("*");
        buttonTimes.setBounds(140, 70, 50, 50);
        buttonTimes.addActionListener(new GUI());
        panel.add(buttonTimes);

        buttonDivide = new JButton("/");
        buttonDivide.setBounds(200, 70, 50, 50);
        buttonDivide.addActionListener(new GUI());
        panel.add(buttonDivide);

        button1 = new JButton("1");
        button1.setBounds(20, 130, 50, 50);
        button1.addActionListener(new GUI());
        panel.add(button1);

        button2 = new JButton("2");
        button2.setBounds(80, 130, 50, 50);
        button2.addActionListener(new GUI());
        panel.add(button2);

        button3 = new JButton("3");
        button3.setBounds(140, 130, 50, 50);
        button3.addActionListener(new GUI());
        panel.add(button3);

        button4 = new JButton("4");
        button4.setBounds(200, 130, 50, 50);
        button4.addActionListener(new GUI());
        panel.add(button4);

        button5 = new JButton("5");
        button5.setBounds(20, 190, 50, 50);
        button5.addActionListener(new GUI());
        panel.add(button5);

        button6 = new JButton("6");
        button6.setBounds(80, 190, 50, 50);
        button6.addActionListener(new GUI());
        panel.add(button6);

        button7 = new JButton("7");
        button7.setBounds(140, 190, 50, 50);
        button7.addActionListener(new GUI());
        panel.add(button7);

        button8 = new JButton("8");
        button8.setBounds(200, 190, 50, 50);
        button8.addActionListener(new GUI());
        panel.add(button8);

        button9 = new JButton("9");
        button9.setBounds(20, 250, 50, 50);
        button9.addActionListener(new GUI());
        panel.add(button9);

        button0 = new JButton("0");
        button0.setBounds(80, 250, 50, 50);
        button0.addActionListener(new GUI());
        panel.add(button0);

        buttonClear = new JButton("C");
        buttonClear.setBounds(140, 250, 50, 50);
        buttonClear.addActionListener(new GUI());
        panel.add(buttonClear);

        buttonEnter = new JButton("=");
        buttonEnter.setBounds(200, 250, 50, 50);
        buttonEnter.addActionListener(new GUI());
        panel.add(buttonEnter);

        frame.setVisible(true);

    }

    @Override
    //ActionListener for the buttons. Reads the input from the buttons and performs the calculations.
    public void actionPerformed(ActionEvent button) {
        String buttonText = button.getActionCommand();
        Object source = button.getSource();
            //If the button pressed is a number, add it to the currentText
            if (buttonText.matches("^[0-9].*")) {
                if (!startNew) {
                    currentText += buttonText;
                    firstNumber = null;
                } else {  
                    currentText = buttonText;
                    startNew = false;
                }
            // If the button was enter, perform the calculations, and clear memory
            } else if (source == buttonEnter) {
                secondNumber = Double.valueOf(currentText);
                Calculate(action);
                currentText = Double.toString(result);
                reset();
            // If the button was clear, reset the calculator
            } else if (source == buttonClear) {
                reset();
                currentText = null;
            // If the button was an operator, updates tha action and makes calculation if needed
            } else {
                if (firstNumber == null) {
                    firstNumber = Double.valueOf(currentText);
                    action = buttonText.charAt(0);
                } else {
                    secondNumber = Double.valueOf(currentText);
                    Calculate(action);
                    secondNumber = null;
                    firstNumber = result;
                    action = buttonText.charAt(0);
                }
                //updates the currentText and sets startNew to true, so the program knows to start a new calculation
                currentText = Double.toString(firstNumber);
                startNew = true;
                //Delay to make the program more reliable
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            //updates the textfield
            field.setText(currentText);
    }

    //Resets the calculator memory
    public static void reset(){
        firstNumber = null;
        secondNumber = null;
        action = null;
        result = null;
        startNew = true;
    }

    //calls the calculation methods from the Calculations class
    public void Calculate(char calculation) {
        switch (calculation) {
            case '+' ->
                result = Calculations.Plus(firstNumber, secondNumber);
            case '-' ->
                result = Calculations.Minus(firstNumber, secondNumber);
            case '/' ->
                result = Calculations.Divide(firstNumber, secondNumber);
            case '*' ->
                result = Calculations.Times(firstNumber, secondNumber);
        }
    }
}
