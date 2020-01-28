package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Sendable;

public class ControlPanel_Subsystem extends SubsystemBase {
    WPI_VictorSPX controlPanelVictor;

    SmartDashboard smartDashboard;

    ColorSensorV3 colorSensor;

    public ControlPanel_Subsystem() {
        controlPanelVictor = RobotContainer.controlPanelVictor;
        smartDashboard = RobotContainer.smartDashboard;
        colorSensor = RobotContainer.colorSensor;
    }

    public void controlPanelPeriodic() {
        getColorSignal();
    }


    public char getColorSignal() { //maybe move this method to periodic

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'B':
                // Blue case code
                
                break;
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
