// Author: Robbie Campbell
// Date: 12/10/2020
// Description:
// This is a project which coverts 3 currencies values into eachother, it will have a GUI and it is to be made in 2 hours

// Class Description: Sets up the GUI and runs the main function

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.IOException;


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
    Color lightGrey, black, white;

    // Constructor method
    public run()
    {
        // Create all style elements
        raised = BorderFactory.createRaisedBevelBorder();
        titleFont = new Font("TimesRoman", Font.BOLD, 28);
        normalFont = new Font("TimesRoman", Font.ITALIC, 20);
        resultFont = new Font("TimesRoman", Font.BOLD, 28);
        lightGrey = new Color(50, 50,50);
        black = new Color(14, 2,8);
        white = new Color(255, 255,255);

        // Create the frame object
        frame = new JFrame("Currency Converter");
        frame.setBounds(100, 100, 450, 400);
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
        title.setBounds(20,20, 400,50);
        mainApp.setBounds(20, 80, 400, 270);
        mainPanel.add(title);
        frame.add(mainPanel);
        mainApp.setLayout(new GridLayout(0,1));
        mainPanel.add(mainApp);

        // Create the label objects
        mainTitle = new JLabel("Currency Converter".toUpperCase());
        output = new JLabel("_____");
        rateValue = new JLabel("_____");
        inputPrompt1 = new JLabel("Amount");
        inputPrompt2 = new JLabel("Convert from");
        inputPrompt3 = new JLabel("Convert to");
        result = new JLabel("Converted amount: ");
        conversionRate = new JLabel("At a rate of: ");


        // Create the text area
        amount = new JTextField("Enter an amount");

        // Create the currency names
        String currencies[] = {"British GBP", "Japanese Yen", "Euro", "USA USD"};

        // Create the combobox objects
        convertFrom = new JComboBox(currencies);
        convertTo = new JComboBox(currencies);

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
        mainTitle.setForeground(white);
        output.setForeground(white);
        rateValue.setForeground(white);
        inputPrompt1.setForeground(white);
        inputPrompt2.setForeground(white);
        inputPrompt3.setForeground(white);
        result.setForeground(white);
        conversionRate.setForeground(white);

        // Set the bounds of all labels
//        inputPrompt1.setBounds(40,35,100,30);
//        inputPrompt2.setBounds(200, 35, 100, 30);
//        inputPrompt3.setBounds(330, 35, 100, 30);
//        result.setBounds(100, 120, 180, 30);
//        conversionRate.setBounds(100,155, 150, 30);
//        output.setBounds(250, 120, 220, 30);
//        rateValue.setBounds(250, 155, 100, 30);

        // Place all of the labels
        title.add(mainTitle);

        // Enter amount label and textbox
        mainApp.add(inputPrompt1);
        mainApp.add(amount);

        // Convert from combobox
        mainApp.add(inputPrompt2);
        mainApp.add(convertFrom);

        // Convert to combobox
        mainApp.add(inputPrompt3);
        mainApp.add(convertTo);

        // Converted amount
        mainApp.add(result);
        mainApp.add(output);

        // Conversion rate
        mainApp.add(conversionRate);
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

    // Uses google to find the value of the desired exchange rate from url
    public Double getExchangeRate(String url)
    {
        try {
            Document doc = Jsoup.connect(url).get();
            Element exchange = doc.select("input[class=a61j6 vk_gy vk_sh Hg3mWc]").first();
            return Double.valueOf(exchange.attr("value"));
        }catch (IOException e) {
            e.printStackTrace();
            return 0.00;
        }
    }


    // A function to correctly format the returning monetary value
    public void convertToFormat(String currencyType, double rate)
    {
        try {
            double amount_for_convert = Double.parseDouble(amount.getText());
            output.setText(String.format("%,.2f %s.", amount_for_convert * rate, currencyType));
            rateValue.setText("1 to " + rate);
        }catch(Exception e)
        {
            output.setText("Please enter a number value");
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
                        convertToFormat("gbp", 1);
                        break;
                    case 1:
                        convertToFormat("yen", getExchangeRate("https://www.google.com/search?q=gbp+to" +
                                "+yen&oq=gbp+to+yen&aqs=chrome.0.69i59j0l7.3292j0j4&sourceid=chrome&ie=UTF-8"));
                        break;
                    case 2:
                        convertToFormat("euro", getExchangeRate("https://www.google.com/search?sxsrf=A" +
                                "LeKk02XqKqJRNMrUX74Xp9xLo3WnPqnaA%3A1602525400129&ei=2JiEX_W-B4TB8gKwwrqACw&q=gbp+to+eu" +
                                "ro&oq=gbp+to+euro&gs_lcp=CgZwc3ktYWIQAzIHCCMQyQMQJzIHCAAQsQMQCjICCAAyAggAMgIIADICCAAyAg" +
                                "gAMgIIADICCAAyAggAOgcIIxCwAxAnOgcIABCwAxBDOgcIABAUEIcCOgQIABAKOgQIIxAnOggIABCxAxCD" +
                                "AToNCAAQsQMQgwEQFBCHAjoOCAAQsQMQgwEQyQMQkQJQ4uAFWOHrBWCH7gVoBHAAeACAAX2IAbYFkgEDNS4ymAE" +
                                "AoAEBqgEHZ3dzLXdpesgBCsABAQ&sclient=psy-ab&ved=0ahUKEwj1it-p0K_sAhWEoFwKHTChDrAQ4dUDCA0&uact=5"));
                        break;
                    case 3:
                        convertToFormat("usd", getExchangeRate("https://www.google.com/search?sxsrf=AL" +
                                "eKk00jsOqwPAVBQ4SFimCX_TpRep8pAw%3A1602525497010&ei=OZmEX8wYxKDyAr2Qg7gF&q=gbp+to+USD&o" +
                                "q=gbp+to+USD&gs_lcp=CgZwc3ktYWIQAzIHCCMQyQMQJzIFCAAQkQIyCggAELEDEBQQhwIyCAgAELEDEIMBMg" +
                                "IIADICCAAyAggAMgIIADICCAAyAggAOgcIIxCwAxAnOgcIABCwAxBDOgkIABCwAxAKEENQ_6QEWJWnBGDxpwRo" +
                                "AnAAeACAAVKIAdUBkgEBM5gBAKABAaoBB2d3cy13aXrIAQrAAQE&sclient=psy-ab&ved=0ahUKEwiMmfjX0K_" +
                                "sAhVEkFwKHT3IAFcQ4dUDCA0&uact=5"));
                        break;
                }
                break;

            // Japanese Yen
            case 1:
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        convertToFormat("gbp", getExchangeRate("https://www.google.com/search?sxsrf=ALe" +
                                "Kk00aA-zpQPnKVa65qRDipXdhyqflTw%3A1602525568458&ei=gJmEX7fCG5WBhbIP67ijgA0&q=yen+to+gbp" +
                                "&oq=yen+to+gbp&gs_lcp=CgZwc3ktYWIQAzIMCCMQyQMQJxBGEIICMgQIABBDMgIIADICCAAyAggAMgIIADoHC" +
                                "CMQsAMQJzoHCAAQRxCwAzoHCCMQyQMQJzoECCMQJzoICAAQsQMQgwE6CAguELEDEIMBOgIILjoFCAAQsQM6DQgA" +
                                "ELEDEIMBEMkDEEM6BwgAELEDEEM6CggAELEDEBQQhwI6CggAELEDEIMBEENQ0OABWJDtAWD87gFoA3AAeACAAY8" +
                                "BiAGQCJIBAzYuNJgBAKABAaoBB2d3cy13aXrIAQnAAQE&sclient=psy-ab&ved=0ahUKEwi3goH60K_sAhWVQE" +
                                "EAHWvcCNAQ4dUDCA0&uact=5"));
                        break;
                    case 1:
                        convertToFormat("yen", 1);
                        break;
                    case 2:
                        convertToFormat("euro", getExchangeRate("https://www.google.com/search?sxsrf=A" +
                                "LeKk00Wa6ZViWUFuDrUyyq0gjTdxEZiZA%3A1602525599839&ei=n5mEX6bYMsvvgAbr2JrQDw&q=yen+to+eu" +
                                "ro&oq=yen+to+euro&gs_lcp=CgZwc3ktYWIQAzIECCMQJzIOCAAQsQMQgwEQyQMQkQIyBQgAEJECMgIIADICCA" +
                                "AyAggAMgIIADICCAAyBAgAEAoyBAgAEAo6BwgjELADECc6BwgAELADEEM6BwgjEMkDECc6BAgAEEM6CAgAELEDE" +
                                "IMBOg4ILhCxAxCDARDHARCjAjoKCAAQsQMQFBCHAjoICC4QsQMQgwE6BQguELEDOgcIABCxAxBDOgUIABCxAzoM" +
                                "CCMQyQMQJxBGEIICUNvjAVi_8QFgs_MBaAVwAHgAgAF2iAH_B5IBAzguM5gBAKABAaoBB2d3cy13aXrIAQrAAQE" +
                                "&sclient=psy-ab&ved=0ahUKEwjmo_yI0a_sAhXLN8AKHWusBvoQ4dUDCA0&uact=5"));
                        break;
                    case 3:
                        convertToFormat("usd", getExchangeRate("https://www.google.com/search?sxsrf=ALe" +
                                "Kk03GXemmPFIoqTYZYLDyD-nsKMhFMQ%3A1602525631754&ei=v5mEX4vILZWhgAa_nbXoCA&q=yen+to+usd&" +
                                "oq=yen+to+usd&gs_lcp=CgZwc3ktYWIQAzIMCCMQyQMQJxBGEIICMgIIADICCAAyAggAMgIIADICCAAyAggAMg" +
                                "IIADICCAAyAggAOgcIIxCwAxAnOgcIABCwAxBDOgcIIxDJAxAnUK7oAVis6gFgkOsBaAJwAHgAgAGJAYgBngKSA" +
                                "QMyLjGYAQCgAQGqAQdnd3Mtd2l6yAEKwAEB&sclient=psy-ab&ved=0ahUKEwjLo5iY0a_sAhWVEMAKHb9ODY0" +
                                "Q4dUDCA0&uact=5"));
                        break;
                }
                break;
            case 2:

                // Euro
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        convertToFormat("gbp", getExchangeRate("https://www.google.com/search?sxsrf=ALe" +
                                "Kk032mLG2NJhQpY6nEgQGtZTiWFrLcw%3A1602525662706&ei=3pmEX9vgKtSsgQaO-J6ICQ&q=euro+to+gbp" +
                                "&oq=euro+to+gbp&gs_lcp=CgZwc3ktYWIQAzIKCAAQsQMQyQMQCjIFCAAQkQIyBAgAEAoyBAgAEAoyBAgAEAoy" +
                                "BAgAEAoyBAgAEAoyBAgAEAoyBAgAEAoyBAgAEAo6BwgjELADECc6BwgAELADEEM6BAgjECc6CQgAEMkDEAoQQzo" +
                                "KCAAQsQMQgwEQQzoICAAQsQMQgwE6AggAOgQIABBDOgcIABDJAxBDOgYIABAKEEM6CggAELEDEBQQhwI6BwgAE" +
                                "LEDEEM6DwgAELEDEIMBEMkDEAoQQzoFCAAQsQM6CAgAEMkDEJECOgcIABCxAxAKUKq6AVipxwFg3cgBaAZwAHgA" +
                                "gAGQAYgB0AiSAQM1LjaYAQCgAQGqAQdnd3Mtd2l6yAEKwAEB&sclient=psy-ab&ved=0ahUKEwjbx_mm0a_sAh" +
                                "VUVsAKHQ68B5EQ4dUDCA0&uact=5"));
                        break;
                    case 1:
                        convertToFormat("yen", getExchangeRate("https://www.google.com/search?sxsrf=AL" +
                                "eKk00xhyj5y6FEcJaG02TkhIjl8umW0Q%3A1602525689224&ei=-ZmEX6ygDbLD8gKEiazIBA&q=euro+to+y" +
                                "en&oq=euro+to+yen&gs_lcp=CgZwc3ktYWIQAzIICAAQyQMQkQIyAggAMgIIADICCAAyAggAMgIIADICCAAyA" +
                                "ggAMgIIADICCAA6BwgAELADEEM6CQgAELADEAoQQzoFCAAQkQI6BAgAEApQlJEEWMKSBGDTlARoAnAAeACAAVi" +
                                "IAegBkgEBM5gBAKABAaoBB2d3cy13aXrIAQrAAQE&sclient=psy-ab&ved=0ahUKEwjsgMyz0a_sAhWyoVwKHY" +
                                "QEC0kQ4dUDCA0&uact=5"));
                        break;
                    case 2:
                        convertToFormat("euro", 1);
                        break;
                    case 3:
                        convertToFormat("usd", getExchangeRate("https://www.google.com/search?sxsrf=ALe" +
                                "Kk01wz7ACy5eDPrxZeYtSKl__irWm2w%3A1602525758218&ei=PpqEX9j7DIyT8gKe_qLgBQ&q=euro+to+usd" +
                                "&oq=euro+to+usd&gs_lcp=CgZwc3ktYWIQAzIECCMQJzIICAAQyQMQkQIyBAgAEAoyBAgAEAoyBAgAEAoyBAgA" +
                                "EAoyBAgAEAoyBAgAEAoyBAgAEAoyBAgAEAo6BwgAELADEEM6CggAELEDEMkDEAo6AggAUPmpAVj-qwFg16wBaAJ" +
                                "wAHgAgAFxiAHXAZIBAzAuMpgBAKABAaoBB2d3cy13aXrIAQrAAQE&sclient=psy-ab&ved=0ahUKEwjYkr_U0a" +
                                "_sAhWMiVwKHR6_CFwQ4dUDCA0&uact=5"));
                        break;
                }
                break;

            // USD
            case 3:
                switch (convertTo.getSelectedIndex())
                {
                    case 0:
                        convertToFormat("gbp", getExchangeRate("https://www.google.com/search?sxsrf=ALe" +
                                "Kk03i-hSz4usWiBELMH5tB2FlG_fyCQ%3A1602525781197&ei=VZqEX9bPC5Wx8gLG9KTwAQ&q=usd+to+gb" +
                                "p&oq=usd+to+gbp&gs_lcp=CgZwc3ktYWIQAzIMCCMQyQMQJxBGEIICMgIIADICCAAyAggAMgIIADICCAAyAgg" +
                                "AMgIIADICCAAyAggAOgcIIxCwAxAnOgcIABCwAxBDOgkIABCwAxAKEEM6BAgjECc6CQgAEMkDEAoQQzoECAAQQz" +
                                "oKCAAQsQMQgwEQQzoPCAAQsQMQgwEQyQMQChBDOgcIABCxAxBDOg0IABCxAxCDARAUEIcCOgcIIxDJAxAnUIjAA" +
                                "ViI0gFg49MBaANwAHgAgAFiiAG8BpIBAjEwmAEAoAEBqgEHZ3dzLXdpesgBCsABAQ&sclient=psy-ab&ved=0a" +
                                "hUKEwiWzrnf0a_sAhWVmFwKHUY6CR4Q4dUDCA0&uact=5"));
                        break;
                    case 1:
                        convertToFormat("yen", getExchangeRate("https://www.google.com/search?sxsrf=AL" +
                                "eKk00D-PQAjETKU8Inm9SjrwKjmPfWDQ%3A1602525809042&ei=cZqEX8CXApmDhbIP3IacoA4&q=usd+to+y" +
                                "en&oq=usd+to+yen&gs_lcp=CgZwc3ktYWIQAzIHCCMQyQMQJzIFCAAQkQIyAggAMgIIADICCAAyAggAMgIIADI" +
                                "CCAAyAggAMgIIADoHCCMQsAMQJzoHCAAQsAMQQzoLCAAQsQMQgwEQkQI6BAgAEApQ078BWPfAAWDgwgFoAnAA" +
                                "eACAAX-IAbUCkgEDMS4ymAEAoAEBqgEHZ3dzLXdpesgBCsABAQ&sclient=psy-ab&ved=0ahUKEwiAlN3s0a_" +
                                "sAhWZQUEAHVwDB-QQ4dUDCA0&uact=5"));
                        break;
                    case 2:
                        convertToFormat("euro", getExchangeRate("https://www.google.com/search?sxsrf=AL" +
                                "eKk02JwE9NpURBEtZ7tclxocHGSmYvxg%3A1602525834686&ei=ipqEX6-4KZG78gLcgLTQBA&q=usd+to+eur" +
                                "&oq=usd+to+eur&gs_lcp=CgZwc3ktYWIQAzIECCMQJzIPCAAQsQMQgwEQyQMQChBDMgcIABAUEIcCMgIIADICC" +
                                "AAyAggAMgIIADICCAAyAggAMgIIADoHCCMQsAMQJzoHCAAQsAMQQzoICAAQsQMQgwFQn5ACWM-RAmDSlAJoAnAA" +
                                "eACAAU6IAd8BkgEBM5gBAKABAaoBB2d3cy13aXrIAQrAAQE&sclient=psy-ab&ved=0ahUKEwivpfr40a_sAhW" +
                                "RnVwKHVwADUoQ4dUDCA0&uact=5"));
                        break;
                    case 3:
                        convertToFormat("usd", 1);
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
            int confirmed = JOptionPane.showConfirmDialog(frame,
                    "Are you sure you want to exit the program?", "Exit Program Message Box",
                    JOptionPane.YES_NO_OPTION);

            // Just for fun :)
            if (confirmed == JOptionPane.YES_OPTION) {
                int sure = JOptionPane.showConfirmDialog(frame,
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
        System.out.println(Toolkit.getDefaultToolkit().getFontList());
        run startApp = new run();
    }
}
