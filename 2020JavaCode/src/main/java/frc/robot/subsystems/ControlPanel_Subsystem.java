package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ControlPanel_Subsystem extends SubsystemBase {
    WPI_VictorSPX controlPanelVictor = RobotContainer.controlPanelVictor;

    public ControlPanel_Subsystem() {

    }

    public void convertGameSignal() {

    }
}

