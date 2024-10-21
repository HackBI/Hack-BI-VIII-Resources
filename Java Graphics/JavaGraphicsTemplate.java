import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;


/**
 * 9/16/2024
 * @author Rory McGuire
 * Template for HackBI VIII Java graphics workshop for participants to create graphics program
 */
public class JavaGraphicsTemplate extends JPanel implements ActionListener, KeyListener {
    private int screenWidth, screenHeight;
    private Timer t;
    //sample variable for keyboard interactions (true if the space bar is currently pressed):
    private boolean spacePressed;

    public JavaGraphicsTemplate(int width, int height) {
        super();

        //Initialize fields
        spacePressed = false;
        screenHeight = height;
        screenWidth = width;

        //set up the JFrame window
        JFrame frame = new JFrame("Title of your window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenWidth + 17, screenHeight + 40);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //attach this class's keyboard listener to the frame so the listener can interact with the frame
        frame.addKeyListener(this);

        //set up the contentPane (canvas) found within the JFrame
        Container content = frame.getContentPane();
        content.setBackground(Color.WHITE);
        //attach this JPanel to the canvas so that whatever is drawn on this JPanel is displayed on the canvas
        content.add(this);
        
        //create a timer that fires every 10 milliseconds, attach it to this class so that it triggers the actionPerformed() method when it fires
        t = new Timer(10, this);
        t.start();        
    }

    //This method executes once when the program is run, painting the canvas, then any time after when repaint() is called
    @Override
    public void paintComponent(Graphics g) {
        //draw the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, screenWidth + 1, screenHeight + 1);

        //DRAW YOUR SCREEN
        //Example:
        g.setColor(Color.RED);
        g.drawRect(10, 10, 100, 100);
        g.fillOval(70, 40, 70, 100);
        g.drawString("Hello this is a Test", 200, 200);

        //here we draw text only if the space bar is pressed
        if(spacePressed) {
            g.setColor(Color.GREEN);
            g.drawString("Space is Pressed!", 100, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //DO THE FOLLOWING EACH FRAME (every 10 milliseconds):
        
        //put here whatever you want to change over time
        //(i.e. increment the position of objects to simulate motion):
        //positionOfObject += 10;

        //THEN REDRAW THE UPDATED DISPLAY USING THE CODE DEFINED IN paintComponent()
        repaint();
    }

    //Fill in these methods with code you want to execute when the user interacts with the keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        //look up different key codes online, so you can change the keys that are checked
        //here the code checks that the key pressed was the space bar, but you can make it respond to any key
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            //Do something
            spacePressed = true;
        }

        //you can put code here outside of an if statement to execute whenever any key at all is pressed
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //look up different key codes online, so you can change the keys that are checked
        //here the code checks that the key pressed was the space bar, but you can make it respond to any key
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            //Do something
            spacePressed = false;
        }

        //you can put code here outside of an if statement to execute whenever any key at all is pressed
    }
    @Override
    public void keyTyped(KeyEvent e) {}

}

class RunProgram {
    
    public static void main(String[] args) {
        //Code in this method executes when this file is run:

        //creates an instance of the graphics template, which should display your graphics
        //change the width and height parameters to change the size of your display
        JavaGraphicsTemplate j = new JavaGraphicsTemplate(400, 400);
    }
}