package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveBase_Subsystem;

public class ArcadeDrive extends CommandBase {

    public ArcadeDrive() {
        addRequirements(RobotContainer.driveBase_Subsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        @Override
        RobotContainer.driveBase_Subsystem.rampArcadeDrive(Robot.ExistingRobot.Controller);
	}
	
	// Called when isFinished returns true (which never happens) or the command gets interrupted
	protected void end() {
        RobotContainer.driveBase_Subsystem.stop();
	}

	protected boolean isFinished() {
		return false;
	}