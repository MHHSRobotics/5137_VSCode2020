package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoShoot_Command;
import frc.robot.commands.OffIntake_Command;
import frc.robot.commands.OnIntake_Command;
import frc.robot.commands.UpwardStorage_Command;

public class StartCenter_TurnShootTurnPickup extends SequentialCommandGroup {

    AutoShoot_Command autoShoot_Command;
    ArcadeDrive arcadeDrive;
    OnIntake_Command onIntake_Command;
    OffIntake_Command offIntake_Command;
    UpwardStorage_Command upwardStorage_Command;
    Timer timer;

    //WaitUntilCommand waitCommand;

    public StartCenter_TurnShootTurnPickup() {
        addCommands(
            new AutoShoot_Command(1.0) //,
           /* new ArcadeDrive(), 
            new OnIntake_Command(), 
            new OffIntake_Command()
            */);
        
        
    }

    
}
