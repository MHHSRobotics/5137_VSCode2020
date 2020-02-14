package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Storage_Command extends CommandBase {
    
    public Storage_Command() {
        addRequirements(RobotContainer.storage_Subsystem);
    }

    @Override
  public void initialize() {
      System.out.println("Storage Running...");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.storage_Subsystem.store();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.lstorageVictor.set(0);
    RobotContainer.rstorageVictor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //may need to change, may want it to end naitively some other way
  }
}