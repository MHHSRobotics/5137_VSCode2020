package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class PositionalControl_Command extends Command {

    //Method called to create this object
    public void PositionalControl_Command() {
        requires(Robot.ControlPanel_Subsystem);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.driveBase_Subsystem.rampArcadeDrive(Robot.OI.Controller);
    }

    //Called 
}
