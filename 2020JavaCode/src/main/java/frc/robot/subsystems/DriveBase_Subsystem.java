package frc.robot.subsystems;

//New API may not need to import dependable commands
import frc.robot.Robot; //Import Timed Robot methods (from overall robot)

import com.ctre.phoenix.motorcontrol.can.TalonSRX; //Import TalonSRX motor controller 
import com.ctre.phoenix.motorcontrol.can.VictorSPX; //Import VictorSPX motor controller

import edu.wpi.first.wpilibj2.command.SubsystemBase; //Import Subsystem Class (*new this year*)
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //Import DifferentialDrive (a way to have an arcade drive)

public class DriveBase_Subsystem extends SubsystemBase {
	
	TalonSRX leftTalon = new TalonSRX(1); //change CAN Bus port #
	TalonSRX rightTalon = new TalonSRX(2);
	VictorSPX leftVictor1 = new VictorSPX(3);
	VictorSPX rightVictor1 = new VictorSPX(4);
	VictorSPX leftVictor2 = new VictorSPX(5);
	VictorSPX rightVictor2 = new VictorSPX(6);

	DifferentialDrive notKOP;

	public DriveBase_Subsystem() {

	}

	@Override
	public void periodic() {
		//This method will be called once per scheduler run
	}
}