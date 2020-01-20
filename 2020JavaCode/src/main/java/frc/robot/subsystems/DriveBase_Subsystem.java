package frc.robot.subsystems;

//New API may not need to import dependable commands
import frc.robot.Robot; //Import Timed Robot methods
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class DriveBase_Subsystem {
	
	TalonSRX leftTalon = new TalonSRX(1); //change CAN Bus port #

	
}