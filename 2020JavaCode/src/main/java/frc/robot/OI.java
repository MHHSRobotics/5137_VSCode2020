package frc.robot;

import frc.robot.commands.*; //import any and all commands
import edu.wpi.first.wpilibj.Joystick; //edu.wpi.first.wpilibj.* has any and all wpi libraries (used for basically everything)
import edu.wpi.first.wpilibj.XboxController; //import xBox Controller library
import edu.wpi.first.wpilibj.buttons.JoystickButton; //import Joystick button Class that goes with Joystick Class
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.POVButton;


public class OI {
    private Joystick Controller; //may need to go public if private doesn't work

    //All Digital Input on XBox
    private JoystickButton AButton; //A
    private JoystickButton Button; //B
    private JoystickButton XButton; //...
    private JoystickButton YButton;
    private JoystickButton SelectButton;
    private JoystickButton StartButton;
    private JoystickButton LBButton;
    private JoystickButton RBButton;

    //DPad Buttons
    private POVButton uDPadButton; //Up DPad
    private POVButton dDPadButton; //Down DPad
    private POVButton lDPadButton; //Left DPad
    private POVButton rDPadButton; //Right DPad
    private POVButton ulDPadButton; //Up-Left DPad
    private POVButton urDPadButton; //Up-Right DPad
    private POVButton dlDPadButton; //Down-Left DPad
    private POVButton drDPadButton; //Down-Right DPad
    private POVButton nDPadButton; //no press on DPad

    //All Analog Buttons
    





}

