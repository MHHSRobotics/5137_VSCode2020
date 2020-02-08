package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import frc.robot.RobotContainer;
/*
Class to provide place for robot-wide numerical or boolean constants.
Declare all constants globally.
Also, statically import the class/inner classes wherever the constants are needed, to reduce verbosity.
*/
public final class Constants {
    public final static double PI = 3.1415;
    public final static double CPWheelCircum = 25.1; //8P inches
    public final static double SpinWheel = 4.0;
    //---------------------------------------//
    //Used for OI
    public final static int JoystickPort = 0;
    //---------------------------------------//
    public final static int AButtonPort = 1;
    public final static int BButtonPort = 2;
    public final static int XButtonPort = 3;
    public final static int YButtonPort = 4;
    public final static int LBButtonPort = 5;
    public final static int RButtonPort = 6;
    public final static int StartButtonPort = 7;
    public final static int SelectButtonPort = 8;
    //---------------------------------------//
    public final static int LXStickAxisPort = 0;
    public final static int LYStickAxisPort = 1;
    public final static int LTAxisPort = 2;
    public final static int RTAxisPort = 3;
    public final static int RXStickAxisPort = 4;
    public final static int RYStickAxisPort = 5;
    //---------------------------------------//
    //Drive Base
    public final static int leftDriveTalonCAN = 1;
    public final static int rightDriveTalonCAN = 2;
    public final static int fLeftDriveVictorCAN = 3;
    public final static int fRightDriveVictorCAN = 4;
    public final static int bLeftDriveVictorCAN = 5;
    public final static int bRightDriveVictorCAN = 6;
    //Control Panel
    public final static int controlPanelCAN = 7;
    //Intake 
    public final static int intakeCAN = 8;
    //Shooter
    public final static int shooterCAN = 9;
    //---------------------------------------//
    //accelerate() method (not currently being used)
    public final static double expirationTime = 0.5;
    public final static int delay = 25; //used for accelerate() method to delay the method by 25 ms
    public final static double minSpeed = 0.5; 
    public final static double maxSpeed = 1.0;
    public final static int accelFactor = 1;
    //---------------------------------------//
    public final static double driveSensitivity = 2.0; //bigger # means less sensitivity, from 0.5 to 2.0
    //10.0: baby speed, 9.0: tdddler mode, 7.0: fast toddler mode, 5.0: optimal turn speed, 4.5:
    public final static double turnSensitivity = 4.0; //4.5 seems nice
    public final static boolean isQuickTurn = true; //makes turning the drive base able to override constant-curvature turning for turn-in-place maneuvers.
    //Used for ...
    //---------------------------------------//
    public final static double cpRPMROT = 400; //test velocity variable (since gearbox screws with it) less means more for some stupid reason
    public final static double cpRPMPOS = 225; //test velocity variable (since gearbox screws with it) less means more for some stupid reason
    public final static double blueTargetMin = 0.4; //uses blue value
    public final static double redTargetMin = 0.5; //uses red value
    public final static double greenTargetMin = 0.55; //uses green value
    public final static double yellowGreenTargetMin = 0.5;
    public final static double yellowRedTargetMax = 0.15;
    public final static double veloConversion = 1.0;
    public final static double rpmToPercentOutFactor = 0.07867; //DONOTCHANGEOHMAHGAHH
    //---------------------------------------//
    public final static double startingBallCount = 3.0;
    public final static double intakeTalonOutput = 0.4;
    //---------------------------------------//


}