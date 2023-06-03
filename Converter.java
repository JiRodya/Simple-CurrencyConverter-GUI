
import javax.swing.JFrame;

/**
 * Title:       Converter
 * Semester:    COP3337-Summer2022
 * @author:     Dianelys Rocha
 * PantherID:   6272943
 *
 * I affirm that this program is entirely my own work.
 * Nothing, but the classes/methods given by the professor,
 * is the work of any other person.
 *
 * This class is the main class of the program
 * it creates the frame as a ConverterFrame Object
 * sets the defuault exit Action
 * sets the title for the Frame
 * sets the frame visible
 */
public class Converter
{

    public static void main(String[] args)
    {

        JFrame frame = new ConverterFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Currency Converter");
        frame.setVisible(true);

    }

}
