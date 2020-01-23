package frc.robot.subsystems;

//New API may not need to import dependable commands
import frc.robot.RobotContainer; //Import Timed Robot methods (from overall robot)
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.Robot;

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

	double newDriveSpeed = 0;
	double actualDriveSpeed = 0;
	double previousDriveSpeed = 0;

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
		System.out.println("LYStick Value: " + driveValue);
		double turnValue = XBoxController.getRawAxis(Constants.RXStickAxisPort);
		System.out.println("RXStickAxisPort: " + turnValue);

		newDriveSpeed = accelerate(driveValue, Constants.minSpeed, Constants.maxSpeed, previousDriveSpeed, Constants.accelFactor);
		actualDriveSpeed = newDriveSpeed;
		previousDriveSpeed = actualDriveSpeed;

		BMoneysDifferentialDrive.arcadeDrive(newDriveSpeed/Constants.driveSensitivity, turnValue/Constants.turnSensitivity);
	}

	public double accelerate(double driveValue, double minSpeed, double maxSpeed, double previousSpeed, double accelFactor) { //NEEDS TO BE TESTED
		double newSpeed;

		//effectively keeps the speed variable depended in motor control the same if controller goes in 
		if (Math.abs(driveValue) > minSpeed && Math.abs(previousSpeed) <  maxSpeed) {
			newSpeed = Math.signum(driveValue) *  driveValue; 
			//Signum returns 1 if value is >0, 0 if = 0, and -1 if <0
		}
		else {
			newSpeed = previousSpeed;
		}
		
		//Acceleration factor
		if (previousSpeed < driveValue) {
			newSpeed += accelFactor;
		}
		else if (previousSpeed > driveValue) {
			newSpeed -= accelFactor;
		}
		else {
			newSpeed = previousSpeed; //necessary??
		}

		//Catch 21 situation
		try {
			Thread.sleep(Constants.delay);
		}
		catch (InterruptedException e) {

		}

		return newSpeed;
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