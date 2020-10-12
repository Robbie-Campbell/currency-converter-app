// Author: Robbie Campbell
// Date: 12/10/2020
// Description:
// This is a project which coverts 3 currencies values into eachother, it will have a GUI and it is to be made in 2 hours

// Class Description: Sets up the GUI and runs the main function

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class run implements ActionListener
{

    // Create variable pointers
    JFrame frame;
    JPanel mainPanel, title, mainApp;
    JTextField amount;
    JLabel mainTitle, output, result, conversionRate, rateValue, inputPrompt1, inputPrompt2, inputPrompt3;
    JButton convert, exit;
    Border raised;
    JComboBox convertFrom, convertTo;
    Font titleFont, normalFont, resultFont;
    Color lightGrey, black, dangerRed;

    // Constructor method
    public run()
    {
        // Create all style elements
        raised = BorderFactory.createRaisedBevelBorder();
        titleFont = new Font("TimesRoman", Font.BOLD, 28);
        normalFont = new Font("TimesRoman", Font.ITALIC, 14);
        resultFont = new Font("TimesRoman", Font.BOLD, 16);
        lightGrey = new Color(50, 50,50);
        black = new Color(14, 2,8);
        dangerRed = new Color(255, 36,0);

        // Create the frame object
        frame = new JFrame("Currency Converter");
        frame.setBounds(100, 100, 600, 400);
        frame.setResizable(false);

        // Create all panel objects
        mainPanel = new JPanel();
        title = new JPanel();
        mainApp = new JPanel();

        // Add panel styling
        title.setBorder(raised);
        mainApp.setBorder(raised);
        mainPanel.setBorder(raised);
        mainPanel.setBackground(lightGrey);
        mainApp.setBackground(black);
        title.setBackground(black);

        // Add the panels to the frame
        mainPanel.setLayout(null);
        mainApp.setLayout(null);
        title.setBounds(20,20, 540,50);
        mainApp.setBounds(20, 80, 540, 270);
        mainPanel.add(title);
        frame.add(mainPanel);
        mainPanel.add(mainApp);

        // Create the label objects
        mainTitle = new JLabel("Currency Converter".toUpperCase());
        output = new JLabel("");
        rateValue = new JLabel("");
        inputPrompt1 = new JLabel("Amount");
        inputPrompt2 = new JLabel("Convert from");
        inputPrompt3 = new JLabel("Convert to");
        result = new JLabel("Converted amount: ");
        conversionRate = new JLabel("At a rate of: ");


        // Create the text area and add to panel
        amount = new JTextField("Enter an amount");
        amount.setBounds(40,60,120,30);
        mainApp.add(amount);

        // Create the currency names
        String currencies[] = {"British GBP", "Chinese Yen", "Euro", "USA USD"};

        // Create the combobox objects and add to panel
        convertFrom = new JComboBox(currencies);
        convertFrom.setBounds(200, 60, 100, 30);
        convertTo = new JComboBox(currencies);
        convertTo.setBounds(330, 60, 100, 30);
        mainApp.add(convertFrom);
        mainApp.add(convertTo);


        // Style the labels
        mainTitle.setFont(titleFont);
        output.setFont(normalFont);
        rateValue.setFont(normalFont);
        inputPrompt1.setFont(normalFont);
        inputPrompt2.setFont(normalFont);
        inputPrompt3.setFont(normalFont);
        result.setFont(resultFont);
        conversionRate.setFont(resultFont);

        // Set all label colors
        mainTitle.setForeground(dangerRed);
        output.setForeground(dangerRed);
        rateValue.setForeground(dangerRed);
        inputPrompt1.setForeground(dangerRed);
        inputPrompt2.setForeground(dangerRed);
        inputPrompt3.setForeground(dangerRed);
        result.setForeground(dangerRed);
        conversionRate.setForeground(dangerRed);

        // Set the bounds of all labels
        inputPrompt1.setBounds(40,35,100,30);
        inputPrompt2.setBounds(200, 35, 100, 30);
        inputPrompt3.setBounds(330, 35, 100, 30);
        result.setBounds(100, 120, 180, 30);
        conversionRate.setBounds(100,155, 150, 30);
        output.setBounds(250, 120, 220, 30);
        rateValue.setBounds(250, 155, 100, 30);

        // Place all of the labels
        title.add(mainTitle);
        mainApp.add(inputPrompt1);
        mainApp.add(inputPrompt2);
        mainApp.add(inputPrompt3);
        mainApp.add(result);
        mainApp.add(conversionRate);
        mainApp.add(output);
        mainApp.add(rateValue);

        // Create the button objects
        convert = new JButton("Convert");
        exit = new JButton("Exit");

        // Set the bounds of the button objects
        convert.setBounds(280, 200, 80, 30);
        exit.setBounds(380, 200, 80, 30);

        // Add buttons to panel
        mainApp.add(convert);
        mainApp.add(exit);

        frame.setVisible(true);

        // Add action listeners to the buttons
        convert.addActionListener(this);
        amount.addActionListener(this);
        exit.addActionListener(this);
    }


    // A function to correctly format the returning monetary value
    public String convertToFormat(String currencyType, double rate)
    {
        try {
            double amount_for_convert = Double.parseDouble(amount.getText());
            return String.format("%,.2f %s.", amount_for_convert * rate, currencyType);
        }catch(Exception e)
        {
            return "Please enter a number value";
        }
    }

    // The larger switch statement, checks against all convertTo combobox conditions and reveals the correct answer
    // The switches revolve around each of the currently selected combobox index options.
    public void conversionType()
    {
        switch(convertFrom.getSelectedIndex())
        {

            // GBP
            case 0:
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        output.setText(convertToFormat("gbp", 1));
                        rateValue.setText("1 to 1");
                        break;
                    case 1:
                        output.setText(convertToFormat("yen", 137.31));
                        rateValue.setText("1 to 137.31");
                        break;
                    case 2:
                        output.setText(convertToFormat("euro", 1.1));
                        rateValue.setText("1 to 1.1");
                        break;
                    case 3:
                        output.setText(convertToFormat("usd", 1.3));
                        rateValue.setText("1 to 1.3");
                        break;
                }
                break;

            // Japanese Yen
            case 1:
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        output.setText(convertToFormat("gbp", 0.0073));
                        rateValue.setText("1 to 0.0073");
                        break;
                    case 1:
                        output.setText(convertToFormat("yen", 1));
                        rateValue.setText("1 to 1");
                        break;
                    case 2:
                        output.setText(convertToFormat("euro", 0.0080));
                        rateValue.setText("1 to 0.0080");
                        break;
                    case 3:
                        output.setText(convertToFormat("usd", 0.0095));
                        rateValue.setText("1 to 0.0095");
                        break;
                }
                break;
            case 2:

                // Euro
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        output.setText(convertToFormat("gbp", 0.91));
                        rateValue.setText("1 to 0.91");
                        break;
                    case 1:
                        output.setText(convertToFormat("yen", 124.39));
                        rateValue.setText("1 to 124.39");
                        break;
                    case 2:
                        output.setText(convertToFormat("euro", 1));
                        rateValue.setText("1 to 1");
                        break;
                    case 3:
                        output.setText(convertToFormat("usd", 1.18));
                        rateValue.setText("1 to 1.18");
                        break;
                }
                break;

            // USD
            case 3:
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        output.setText(convertToFormat("gbp", 0.77));
                        rateValue.setText("1 to 0.77");
                        break;
                    case 1:
                        output.setText(convertToFormat("euro", 105.49));
                        rateValue.setText("1 to 105.49");
                        break;
                    case 2:
                        output.setText(convertToFormat("euro", 0.85));
                        rateValue.setText("1 to 0.85");
                        break;
                    case 3:
                        output.setText(convertToFormat("euro", 1));
                        rateValue.setText("1 to 1");
                        break;
                }
                break;
        }
    }

    // The action listener that converts the amounts to the desired currency
    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() == convert)
        {
            conversionType();
        }

        // The exit button of the application, offers a decision box where the user can leave or continue with the app
        else if (e.getSource() == exit) {
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit the program?", "Exit Program Message Box",
                    JOptionPane.YES_NO_OPTION);

            // Just for fun :)
            if (confirmed == JOptionPane.YES_OPTION) {
                int sure = JOptionPane.showConfirmDialog(null,
                        "But are you really sure about that?", "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);
                if (sure == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        }
    }


    // Run the main application
    public static void main(String[] args)
    {
        run startApp = new run();
    }
}
