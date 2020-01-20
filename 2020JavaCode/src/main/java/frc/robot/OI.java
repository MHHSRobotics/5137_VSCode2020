package frc.robot;

import frc.robot.commands.*; //import any and all commands
import edu.wpi.first.wpilibj.Joystick; //edu.wpi.first.wpilibj.* has any and all wpi libraries (used for basically everything)
import edu.wpi.first.wpilibj.XboxController; //import xBox Controller library
import edu.wpi.first.wpilibj2.command.button.POVButton; //import Joystick button Class that goes with Joystick Class
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;


public class OI {
    //create Joystick variable name
    public static Joystick Controller; //may need to go public if private doesn't work. Static means that the method/class the variable or method belongs too doesn't need to be created

    //All Digital Input Buttons on XBox (names)
    private JoystickButton AButton; //A
    private JoystickButton Button; //B
    private JoystickButton XButton; //...
    private JoystickButton YButton;
    private JoystickButton SelectButton;
    private JoystickButton StartButton;
    private JoystickButton LBButton;
    private JoystickButton RBButton;

    //DPad Buttons (names)
    private POVButton uDPadButton; //Up DPad
    private POVButton dDPadButton; //Down DPad
    private POVButton lDPadButton; //Left DPad
    private POVButton rDPadButton; //Right DPad
    private POVButton ulDPadButton; //Up-Left DPad
    private POVButton urDPadButton; //Up-Right DPad
    private POVButton dlDPadButton; //Down-Left DPad
    private POVButton drDPadButton; //Down-Right DPad
    private POVButton nDPadButton; //no press on DPad

    //All Analog Buttons (names)
    

    public OI() {
        /* 
        Summary: When called, creates all objects/commands related to OI
        Parameters: None
        */

        //Constants to replace port numbers
        final int JoystickPort = 0;

        Controller = new Joystick(JoystickPort);

        //Sets A button from Joystick Controller to Positional Control Command
        AButton = new JoystickButton(Controller, 0);
        AButton.whileActive(new PositionalControl_Command());
    }





}

