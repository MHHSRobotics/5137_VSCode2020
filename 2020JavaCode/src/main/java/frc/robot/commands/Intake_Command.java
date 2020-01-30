package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Intake_Command extends CommandBase {

    public Intake_Command() {
        addRequirements(RobotContainer.intake_Subsystem);
    }

    //Called when the command is initially scheduled
    @Override
    public void initialize() {

    }

    //Called every time the scheduler runs while the command is scheduled
    @Override
    public void execute() {
        RobotContainer.intake_Subsystem.intakeBalls();
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.intakeTalon.set(0);
    }

    @Override
    public boolean isFinished() {
        return true; //maybe change to false to keep going like drivebase 
    }
}