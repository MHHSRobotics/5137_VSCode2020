package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;

public class Storage_Subsystem extends SubsystemBase {

    Pixy2 pixy;
    
    WPI_VictorSPX lstorageVictor;
    WPI_VictorSPX rstorageVictor;

    boolean invertStore;

    public Storage_Subsystem() {
        lstorageVictor = RobotContainer.lstorageVictor;
        rstorageVictor = RobotContainer.rstorageVictor;
        pixy = RobotContainer.cartridgePixy;
    }

    public void store(boolean overriden, boolean shooting) {
        
        
        if(!overriden && !shooting) { //switch to checkReadyMove() for true. Does top belt (not shooting, but picking up)
            lstorageVictor.set(Constants.storageSpeed);
            rstorageVictor.set(0);
            //rstorageVictor.set(Constants.storageSpeed);
        }
        else if (overriden) { //want to override storage belts, runs both based on override controller, and in the opposite direction
            lstorageVictor.set(-1 * RobotContainer.XBoxController.getRawAxis(Constants.LTAxisPort));
            rstorageVictor.set(-1 * RobotContainer.XBoxController.getRawAxis(Constants.LTAxisPort));
        }
        else if (shooting){ //if the storage doesn't need to be moving
            //lstorageVictor.set(Constants.storageSpeed);
            
            lstorageVictor.set(Constants.storageSpeed);
            rstorageVictor.set(Constants.storageSpeed);
        }

        else {
            try { //may need changes for when balls are added, but the cartridge isn't full
            Thread.sleep((long) Constants.storageWaitTime);
            }
            catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            }
            lstorageVictor.set(ControlMode.Current, 0);
            rstorageVictor.set(ControlMode.Current, 0);
        }
    }

    public boolean checkReadyToMove() {

        if (pixy.getCCC().getBlocks(false, 1, 1) == 1) { //check if it sees the target (pink colored paper)
            return true;
        }
        else {
            return false;
        }
    }
}