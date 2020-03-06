package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoAutoShoot_Command;
import frc.robot.commands.AutoDriveForward_Command;
import frc.robot.commands.AutoShoot_Command;
import frc.robot.commands.OffIntake_Command;
import frc.robot.commands.OnIntake_Command;
import frc.robot.commands.UpwardStorage_Command;
import frc.robot.subsystems.DriveBase_Subsystem;

public class StartCenter_TurnShootTurnPickup extends SequentialCommandGroup {

    public StartCenter_TurnShootTurnPickup(DriveBase_Subsystem db) {
        addCommands(
            new AutoDriveForward_Command(2.0, 1.0),
            new OnIntake_Command(),
            new AutoAutoShoot_Command(1)
            );//,
           /* new ArcadeDrive(), 
            new OnIntake_Command(), 
            new OffIntake_Command()
            */
    }

    
}
