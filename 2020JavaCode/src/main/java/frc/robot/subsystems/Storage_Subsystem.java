package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Storage_Subsystem extends SubsystemBase {
    
    WPI_VictorSPX lstorageVictor;
    WPI_VictorSPX rstorageVictor;

    public Storage_Subsystem() {
        lstorageVictor = RobotContainer.lstorageVictor;
        rstorageVictor = RobotContainer.rstorageVictor;
    }
    public void store() {
        if(checkReadyToMove()) {
            lstorageVictor.set(Constants.storageSpeed);
            rstorageVictor.set(Constants.storageSpeed);
        }
    }

    public boolean checkReadyToMove() {
        /*check for the following items:

        1. pixy says that we have too many balls (may need to change intake code)
        (idk i thought i would need more items)
        */
        return true;
    }
}