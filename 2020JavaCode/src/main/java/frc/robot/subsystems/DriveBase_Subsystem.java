package frc.robot.subsystems;

//New API may not need to import dependable commands
import frc.robot.Robot; //Import Timed Robot methods (from overall robot)
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; //Check which motor controller would work better (WPI Talons v. Talons)
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase; //Import Subsystem Class (*new this year*)
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //Import DifferentialDrive (a way to have an arcade drive)

public class DriveBase_Subsystem extends SubsystemBase {

	public void DriveBase_Subsystem() {

	}

	@Override
	public void periodic() {
		//This method will be called once per scheduler run
	}
}