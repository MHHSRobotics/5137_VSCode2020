package frc.robot;

import frc.robot.commands.*; //import any and all commands
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick; //edu.wpi.first.wpilibj.* has any and all wpi libraries (used for basically everything)
import edu.wpi.first.wpilibj.XboxController; //import xBox Controller library
import edu.wpi.first.wpilibj2.command.button.POVButton; //import Joystick button Class that goes with Joystick Class
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class OI {
    //create Joystick variable name
    public static Joystick Controller; //may need to go public if private doesn't work. Static means that the method/class the variable or method belongs too doesn't need to be created

    //All Digital Input Buttons on XBox (names)
    private JoystickButton AButton; //A
    private JoystickButton BButton; //B
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
    
    public OI() {
        /* 
        Summary: When called, creates all objects/commands related to OI
        Parameters: None
        */
        
        Controller = new Joystick(Constants.JoystickPort);

        //Test this, may be good for using axes
        if (Controller.getRawAxis(Constants.LXStickAxisPort) > .70) {
            new RotationalControl_Command();
        } 

        //Sets A button from Joystick Controller to Positional Control Command
        AButton = new JoystickButton(Controller, Constants.AButtonPort);
        AButton.whenPressed(new PositionalControl_Command());

        //Sets B Button to Rotational Control Command
        BButton = new JoystickButton(Controller, Constants.BButtonPort);
        BButton.whenPressed(new RotationalControl_Command());


    }





}

