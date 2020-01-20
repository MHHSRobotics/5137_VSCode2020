package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX; //should we use WPI_VictorSPX (caries same functionality, yet extends itself for WPI stuff)
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;//do we need to use???

import frc.robot.OI;

/* The robot map is used to define and categorize all things on the robot...
 * This includes: motors, gyros and specialty things such as the
 * DifferentialDrive- This allows the drive motors (not the slidedrive) to 
 * function together and do things such as arcade drive and tank drive 
 * more readily as seen in the DriveBase code.
 */
public class RobotMap {
    //Motor Controllers:

        //Drive Base
        public static WPI_TalonSRX leftDriveTalon;
        public static TalonSRX rightDriveTalon;
        public static VictorSPX frontLeftTalon;
        public static VictorSPX backLeftTalon;
        public static VictorSPX frontRightTalon;
        public static VictorSPX backRightTalon;
    
        //Shooter
        public static TalonSRX shooterTalon;
    
        //Control Panel
        public static VictorSPX controlPanelVictor;

        //Intake
        public static TalonSRX intakeTalon;

    //Solenoids:
        //Compressor (if doing)
        //public static Compressor compressor;

        //Climb
        
        //public static DoubleSolenoid climbPiston;

    //Switches
        //Limit Switches
        //public static DigitalInput limitSwitch;
    public static void RobotMapInit() {

        //Drive Base Moter Initialization:
        leftDriveTalon = new WPI_TalonSRX(0);
        //leftDriveTalon."function name()"
        leftDriveTalon.set(ControlMode.PercentOutput, OI.Controller.getRawAxis(1));
        //... (continue for other drive base motor controllers)
    }
}