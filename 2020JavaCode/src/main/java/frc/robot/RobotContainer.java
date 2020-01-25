package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.Watchdog;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 * 
 * If you aren't familiar with this (since this is new to this year), this is a
 * combination of OI.java and RobotMap.java, both of which are gone this this
 * year into this class. If desired, this class can be subdivided back into the
 * previous two files.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    // Commands go here...

    // Subsystems (actual object name, not class names)
    public static Climb_Subsystem climb_Subsystem;
    public static ControlPanel_Subsystem controlPanel_Subsystem;
    public static DriveBase_Subsystem driveBase_Subsystem;
    public static Intake_Subsystem intake_Subsystem;
    public static Shooter_Subsystem shooter_Subsystem;

    public static Command m_autoCommand;

    // After these, put object declarations (for cameras, ultrasonic, ...)

    // Motor Controllers:

    // Drive Base

    // Do I need this?
    public static WPI_TalonSRX m_leftDriveTalon;
    public static WPI_TalonSRX m_rightDriveTalon;
    public static WPI_VictorSPX m_frontLeftVic;
    public static WPI_VictorSPX m_backLeftVic;
    public static WPI_VictorSPX m_frontRightVic;
    public static WPI_VictorSPX m_backRightVic;

    public static SpeedControllerGroup m_leftDrive;
    public static SpeedControllerGroup m_rightDrive;

    public static DifferentialDrive BMoneysDriveBase;

    // Shooter
    public static WPI_TalonSRX shooterTalon;

    // Control Panel
    public static WPI_VictorSPX controlPanelVictor;

    // Intake
    public static WPI_TalonSRX intakeTalon;

    // Solenoids:

    // Compressor (if doing)
    // public static Compressor compressor;

    // Climb

    // public static DoubleSolenoid climbPiston;

    // Switches

    // Limit Switches
    // public static DigitalInput limitSwitch;

    public static JoystickButton AButton; // A
    public static JoystickButton BButton; // B
    public static JoystickButton XButton; // ...
    public static JoystickButton YButton;
    public static JoystickButton SelectButton;
    public static JoystickButton StartButton;
    public static JoystickButton LBButton; // Left bumper button
    public static JoystickButton RBButton; // ...

    // DPad Buttons (names)
    public static POVButton uDPadButton; // Up DPad
    public static POVButton dDPadButton; // Down DPad
    public static POVButton lDPadButton; // Left DPad
    public static POVButton rDPadButton; // Right DPad
    public static POVButton ulDPadButton; // Up-Left DPad
    public static POVButton urDPadButton; // Up-Right DPad
    public static POVButton dlDPadButton; // Down-Left DPad
    public static POVButton drDPadButton; // Down-Right DPad
    public static POVButton nDPadButton; // no press on DPad

    // create Joystick variable name
    public static Joystick XBoxController; // may need to delete final if it doesn't work. Static means that the
    // method/class the variable or method belongs too doesn't need to be created

    //Motor safety watchdog
    Watchdog watchdog;
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        // Similar to RobotMap, now we are going to create all the subsystem objects
        // (for commands)
        InitMap();

        // Configure the button bindings
        configureButtonBindings();
        
        climb_Subsystem = new Climb_Subsystem();
        controlPanel_Subsystem = new ControlPanel_Subsystem();
        driveBase_Subsystem = new DriveBase_Subsystem();
        intake_Subsystem = new Intake_Subsystem();
        shooter_Subsystem = new Shooter_Subsystem();

        // Set creation of other objects (like cameras)

        // For cameras, set defaults here (like resolution, framerate, ...)

        
        

        System.out.println("Robot is init...");
        //For motor safety error
        //watchdog.enable();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     * 
     * This is the exact same as OI from previous years.
     */
    private void configureButtonBindings() {

        XBoxController = new Joystick(Constants.JoystickPort);

        /* Test this, may be good for using axes
        if (XBoxController.getRawAxis(Constants.LTAxisPort) > .70) {
            new RotationalControl_Command();
        }*/

        // Sets A button from Joystick Controller to Positional Control Command
        //AButton = new JoystickButton(XBoxController, Constants.AButtonPort);
        //AButton.whenPressed(new PositionalControl_Command());

    }

    public void InitMap() {
        /* Parameter: Joystick used in game */
        // Drive Base Moter Initialization:
        m_leftDriveTalon = new WPI_TalonSRX(Constants.leftDriveTalonCAN); //other motor controllers will follow this controller
        m_leftDriveTalon.set(ControlMode.PercentOutput, 0);

        m_frontLeftVic = new WPI_VictorSPX(Constants.fLeftDriveVictorCAN);
        m_frontLeftVic.set(ControlMode.Follower, Constants.leftDriveTalonCAN);

        m_backLeftVic = new WPI_VictorSPX(Constants.bLeftDriveVictorCAN);
        m_backLeftVic.set(ControlMode.Follower, Constants.leftDriveTalonCAN);

        m_rightDriveTalon = new WPI_TalonSRX(Constants.rightDriveTalonCAN); //other motor controllers will follow this controller
        m_rightDriveTalon.set(ControlMode.PercentOutput, 0);

        m_frontRightVic = new WPI_VictorSPX(Constants.fRightDriveVictorCAN);
        m_frontRightVic.set(ControlMode.Follower, Constants.rightDriveTalonCAN);

        m_backRightVic = new WPI_VictorSPX(Constants.bRightDriveVictorCAN);
        m_backRightVic.set(ControlMode.Follower, Constants.rightDriveTalonCAN);

        m_leftDrive = new SpeedControllerGroup(m_leftDriveTalon, m_frontLeftVic, m_backLeftVic); // Ports in order:
                                                                                                     // 1, 2, 3
        m_rightDrive = new SpeedControllerGroup(m_rightDriveTalon, m_frontRightVic, m_backRightVic); // 4, 5, 6

        BMoneysDriveBase = new DifferentialDrive(m_leftDrive, m_rightDrive);

        // Init Shooter Motors
        // ...

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_autoCommand;
    }
    /*
    m_leftDriveTalon.setExpiration(Constants.expirationTime);
        m_frontLeftVic.setExpiration(Constants.expirationTime);
        m_backLeftVic.setExpiration(Constants.expirationTime);
        m_rightDriveTalon.setExpiration(Constants.expirationTime);
        m_frontRightVic.setExpiratio
        n(Constants.expirationTime);
        m_backRightVic.setExpiration(Constants.expirationTime);
    */
}