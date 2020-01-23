package frc.robot.subsystems;

//New API may not need to import dependable commands
import frc.robot.RobotContainer; //Import Timed Robot methods (from overall robot)
import frc.robot.commands.ArcadeDrive;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; //Check which motor controller would work better (WPI Talons v. Talons)
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase; //Import Subsystem Class (*new this year*)
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //Import DifferentialDrive (a way to have an arcade drive)

public class DriveBase_Subsystem extends SubsystemBase {
	SpeedControllerGroup m_leftDrive = RobotContainer.m_leftDrive;
	SpeedControllerGroup m_rightDrive = RobotContainer.m_rightDrive;
	DifferentialDrive BMoneysDifferentialDrive = RobotContainer.BMoneysDriveBase;

	public void DriveBase_Subsystem() {

		
	}

	@Override
	public void periodic() {
		//This method will be called once per scheduler run
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public double adjustJoystickValue(double joystick, double deadZone) {
		double adjustedJoystick;
		if (Math.abs(joystick) < deadZone) {
			adjustedJoystick = 0;
		} else {
			adjustedJoystick = ((1 / (1 - deadZone)) * (joystick - deadZone));
		}
		return adjustedJoystick;
		//	An algorithm developed by the fantastic Sarah C. Lincoln that adjusts the joysticks
		//	to run scaled to the deadZone
	}

	public void rampArcadeDrive(Joystick XBoxController) {
		double driveValue = XBoxController.getRawAxis(Constants.LYStickAxisPort);
		double turnValue = XBoxController.getRawAxis(Constants.RXStickAxisPort);

		
	}

	public void drivePivot(double speed) { //TODO may need to make this negative
		BMoneysDifferentialDrive.arcadeDrive(0, speed);
	}

	public void driveStraight(double speed) {
		BMoneysDifferentialDrive.arcadeDrive(speed,0);
	}

	public void stop() {
		BMoneysDifferentialDrive.arcadeDrive(0,0);
	}
}