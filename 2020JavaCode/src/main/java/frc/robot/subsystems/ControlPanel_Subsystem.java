package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlPanel_Subsystem extends SubsystemBase {
    WPI_VictorSPX controlPanelVictor = RobotContainer.controlPanelVictor;

    SmartDashboard smartDashboard;

    public ControlPanel_Subsystem() {
        controlPanelVictor = RobotContainer.controlPanelVictor;
        smartDashboard = RobotContainer.smartDashboard;
    }

    public void controlPanelPeriodic() {

    }

    public char adjustColorSignal() {

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'B':
                // Blue case code

                break;
                //smartDashboard.setImage();
            case 'G':
                // Green case code

                break;
            case 'R':
                // Red case code

                break;
            case 'Y':
                // Yellow case code

                break;
            default:
                // This is corrupt data
                break;
            }
        } else {
            // Code for no data received yet
        }
        return ' ';
    }

}
