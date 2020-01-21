package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
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
    public static WPI_TalonSRX leftDriveTalon;
    public static WPI_TalonSRX rightDriveTalon;
    public static WPI_VictorSPX frontLeftTalon;
    public static WPI_VictorSPX backLeftTalon;
    public static WPI_VictorSPX frontRightTalon;
    public static WPI_VictorSPX backRightTalon;

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

    /**
    * The container for the robot. Contains subsystems, OI devices, and commands.
    */
    public RobotContainer() {
        // Configure the button bindings
        // Similar to RobotMap, now we are going to create all the subsystem objects
        // (for commands)
        climb_Subsystem = new Climb_Subsystem();
        controlPanel_Subsystem = new ControlPanel_Subsystem();
        driveBase_Subsystem = new DriveBase_Subsystem();
        intake_Subsystem = new Intake_Subsystem();
        shooter_Subsystem = new Shooter_Subsystem();

        // Set creation of other objects (like cameras)

        // For cameras, set defaults here (like resolution, framerate, ...)

        // Configure the button bindings
        configureButtonBindings();
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
        // create Joystick variable name
        final Joystick Controller; // may need to delete final if it doesn't work. Static means that the
                                   // method/class the variable or method belongs too doesn't need to be created

        // All Digital Input Buttons on XBox (names)
        final JoystickButton AButton; // A
        final JoystickButton BButton; // B
        final JoystickButton XButton; // ...
        final JoystickButton YButton;
        final JoystickButton SelectButton;
        final JoystickButton StartButton;
        final JoystickButton LBButton; // Left bumper button
        final JoystickButton RBButton; // ...

        // DPad Buttons (names)
        final POVButton uDPadButton; // Up DPad
        final POVButton dDPadButton; // Down DPad
        final POVButton lDPadButton; // Left DPad
        final POVButton rDPadButton; // Right DPad
        final POVButton ulDPadButton; // Up-Left DPad
        final POVButton urDPadButton; // Up-Right DPad
        final POVButton dlDPadButton; // Down-Left DPad
        final POVButton drDPadButton; // Down-Right DPad
        final POVButton nDPadButton; // no press on DPad

        Controller = new Joystick(Constants.JoystickPort);

        // Test this, may be good for using axes
        if (Controller.getRawAxis(Constants.LTAxisPort) > .70) {
            new RotationalControl_Command();
        }

        // Sets A button from Joystick Controller to Positional Control Command
        AButton = new JoystickButton(Controller, Constants.AButtonPort);
        AButton.whenPressed(new PositionalControl_Command());

        // Sets B Button to Rotational Control Command
        BButton = new JoystickButton(Controller, Constants.BButtonPort);
        BButton.whenPressed(new RotationalControl_Command());

    }

    public void InitMap() {
        // Drive Base Moter Initialization:
        leftDriveTalon = new WPI_TalonSRX(0);
        // leftDriveTalon."function name()"
        leftDriveTalon.set(ControlMode.PercentOutput, Controller.getRawAxis(1));
        // ... (continue for other drive base motor controllers)
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
}