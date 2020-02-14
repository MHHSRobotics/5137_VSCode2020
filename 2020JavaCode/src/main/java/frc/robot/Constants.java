package frc.robot;

/*Class to provide place for robot-wide numerical or boolean constants.
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
    //CAN Items

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
    public static final int PCMCAN = 10; //PCM CAN items are subject to change
    public static final int leftPneuForwardChannel = 1;
    public static final int leftPneuBackChannel = 2;
    public static final int rightPneuForwardChannel = 3;
    public static final int rightPneuBackChannel = 4;

    //Shooter
    public final static int shooterCAN = 9;
    public static final int shootPneumaticP1ForwardChannel = 5;
    public static final int shootPneumaticP1BackChannel = 6;
	public static final int shootPneumaticP2ForwardChannel = 7;
	public static final int shootPneumaticP2BackChannel = 8;
	public static final int shootPneumaticP3ForwardChannel = 9;
    public static final int shootPneumaticP3BackChannel = 10;
    
    //Storage
    public final static int lstorageCAN = 10;
    public final static int rstorageCAN = 11;

    //---------------------------------------//
    public final static double driveSensitivity = 2.0; //bigger # means less sensitivity, from 0.5 to 2.0
    //10.0: baby speed, 9.0: tdddler mode, 7.0: fast toddler mode, 5.0: optimal turn speed, 4.5:
    public final static double turnSensitivity = 4.0; //4.5 seems nice
    public final static boolean isQuickTurn = true; //makes turning the drive base able to override constant-curvature turning for turn-in-place maneuvers.
    //Used for ...

    //Control Panel
    //---------------------------------------//
    public final static double cpRPMROT = 400; //test velocity variable (since gearbox screws with it) less means more for some stupid reason
    public final static double cpRPMPOS = 225; //test velocity variable (since gearbox screws with it) less means more for some stupid reason
    public final static double blueTargetMin = 0.4; //uses blue value
    public final static double redTargetMin = 0.5; //uses red value
    public final static double greenTargetMin = 0.55; //uses green value
    public final static double yellowGreenTargetMin = 0.5; //uses green value
    public final static double yellowRedTargetMax = 0.15; //uses red value
    public final static double rpmToPercentOutFactor = 0.07867; //DONOTCHANGEOHMAHGAHH

    //Intake
    //---------------------------------------//
    public final static double startingBallCount = 3.0;
    public final static double intakeTalonOutput = 0.4;

    //Shooter
    //---------------------------------------//
    public final static int packetsAmnt = 180; //amount of packets gathered to find an average limelight value. Used for all types of limelight values. Packets should be in increments of ~18.
    //---------------------------------------//
    public final static double shooterAngle = 69.0; //angle of shooter in degrees of the horizontal (right now is (69.0 * PI) / 180 radians)
    public final static double towerHeight = 98.25; //height of power tower in inches
    public final static double limelightHeight = 10.5; //height of limelight in inches
    public final static double gravitationalAccel = 9.8; //acceleration due to gravity
    public final static double marginAngleError = 0.1; //amount in degrees that robot is allowed to be offset from target when shooting
    public final static double turnRate = 0.2; //turn speed based on percentage of drive base output
    public final static double veloError = 0.05; //amount of variance in m/s that the motor will allow to shoot
    public final static double currentShooterRadius = 3.0 * 0.0254; //radius of the shooter wheel (in meters) (0.2032 is conversion factor)

    //Storage
    //---------------------------------------//
    public final static double storageSpeed = 2; //speed of the belts in AMPS!!!


}