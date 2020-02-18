package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climb_Subsystem extends SubsystemBase {

    WPI_TalonSRX lclimbTalon;
    WPI_TalonSRX rclimbTalon;


    public Climb_Subsystem() {
        lclimbTalon = RobotContainer.lclimbTalon;
        rclimbTalon = RobotContainer.rclimbTalon;
    }

    public void init() {
        //CommandScheduler.registerSubsystem(RobotContainer.climb_Subsystem);
        //may need to use this in other subsystems
    }

    public void goUp() {
        
    }

    public void goDown() {

    }
}