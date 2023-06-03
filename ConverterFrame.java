

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

/**
 * Title:       ConverterFrame
 * Semester:    COP3337-Summer2022
 * @author:     Dianelys Rocha
 * PantherID:   6272943
 *
 * I affirm that this program is entirely my own work.
 * Nothing, but the classes/methods given by the professor,
 * is the work of any other person.
 *
 * This class sets up the GUI for a Currency Converter.
 * It allows the user to convert between 4 currencies: USD, CAD, EUR, GBP
 * It also displays a message label if the user is using the same currency in both fields
 * It also displays a messagge label if the value entered is not a number
 */

public class ConverterFrame extends JFrame
{
    //fields
    private JTextField input;
    private JLabel result, errorLabel;
    private JButton okButton;
    private JComboBox <String> fromBox, toBox;
    private JPanel inputPanel, resultPanel, okPanel;
    private final int WINDOW_WIDTH = 300, // Window width in pixels
                      WINDOW_HEIGHT = 150; // Window height in pixels

    /**
     * Constructor.
     */
    public ConverterFrame()
    {
        //size
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //initialize and create everything
        CreateGrid();
    }

    /**
     * This method is a helper to create and put in place all elements:
     * labels, text field, button, combo boxes
     */
    private void CreateGrid()
    {
        //set grid row, col
        setLayout(new GridLayout(2, 2));

        //create the text Field and answer label
        input = new JTextField(5);
        input.requestFocus();
        result = new JLabel("..."); // add border, or set not editable text Field;
        errorLabel = new JLabel(" ");

        //create buttons
        okButton = new JButton("Accept");
        okButton.addActionListener(new okListener());

        //create Combo boxes
        String[] unitOptions = {"USD", "EUR", "CAD", "GBP"};
        fromBox = new JComboBox<>(unitOptions);
        toBox = new JComboBox<>(unitOptions);

        //create pannels to store each item (3 panels)
        inputPanel = new JPanel();
        resultPanel = new JPanel();
        okPanel = new JPanel();

        //add items to its respective Panel
        inputPanel.add(input);
        inputPanel.add(fromBox);
        inputPanel.setBorder(new TitledBorder(new EtchedBorder(), "Input"));

        resultPanel.add(result);
        resultPanel.add(toBox);
        resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Result"));

        okPanel.add(okButton);

        //add everything to grid
        add(inputPanel);
        add(resultPanel);
        add(errorLabel);
        add(okPanel);

    }

    /**
     *
     * @param original original currency
     * @param target desired currency
     * @param ownedMoney value to convert
     * @return the value after conversion
     */
    public float ConversionChoice(String original, String target, float ownedMoney)
    {
        float conversionMoney = 0;
        final float USD = 1;
        final float EUR = 1.42f * USD;
        final float GBP = 1.64f * USD;
        final float CAD = 0.77f * USD;


        if(original.equals(target))
        {
            errorLabel.setVisible(true);
            errorLabel.setText("Same Currency!");
            errorLabel.setForeground(Color.red);
        }

        switch (original)
        {
            case "USD" -> conversionMoney = switch (target)
            {
                case "USD" -> ownedMoney;
                case "CAD" -> ownedMoney / CAD;
                case "EUR" -> ownedMoney / EUR;
                case "GBP" -> ownedMoney / GBP;
                default    -> -1;
            };
            case "CAD" -> conversionMoney = switch (target)
            {
                case "USD" -> ownedMoney * CAD;
                case "CAD" -> ownedMoney;
                case "EUR" -> ownedMoney * EUR * CAD;
                case "GBP" -> ownedMoney * GBP * CAD;
                default    -> -1;
            };
            case "GBP" -> conversionMoney = switch (target)
            {
                case "USD" -> ownedMoney * GBP;
                case "CAD" -> ownedMoney * CAD * GBP;
                case "EUR" -> ownedMoney * EUR * GBP;
                case "GBP" -> ownedMoney;
                default    -> -1;
            };
            case "EUR" -> conversionMoney = switch (target)
            {
                case "USD" -> ownedMoney * EUR;
                case "CAD" -> ownedMoney * CAD * EUR;
                case "EUR" -> ownedMoney;
                case "GBP" -> ownedMoney * GBP * EUR;
                default    -> -1;
            };
        }
        return conversionMoney;
    }

    /**
     * This listener controls the flow of the program when the button
     * "accept" is clicked.
     */
    class okListener implements ActionListener
    {
        float money;
        String original, target;

        @Override
        public void actionPerformed(ActionEvent event)
        {
            errorLabel.setVisible(false);//reset error Label when not needed

            //handle any format other than numbers, and negative
            try
            {
                money = Float.parseFloat(input.getText());
                if(money<0)
               {
                   throw new IllegalArgumentException("Negative Value");
               }
                original = (String) fromBox.getSelectedItem();
                target = (String) toBox.getSelectedItem();
                result.setText(String.format("%.2f", ConversionChoice(original, target, money)));
            }
            catch (NumberFormatException e)
            {
                errorLabel.setVisible(true);
                errorLabel.setText("Number Field Only!");
                errorLabel.setForeground(Color.red);
                result.setText("0");
            }
            catch (IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(null, "Negative Value!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
