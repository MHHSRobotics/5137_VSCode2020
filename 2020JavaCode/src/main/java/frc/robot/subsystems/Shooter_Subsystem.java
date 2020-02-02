package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class Shooter_Subsystem extends SubsystemBase {
    //Variable declarations go here...
    WPI_TalonSRX shooterTalon;
    
    public Shooter_Subsystem() {
        //Variable assignment goes here...
        shooterTalon = RobotContainer.shooterTalon;
    }

    @Override
    public void periodic() {
        System.out.println("RTAxis is : " + RobotContainer.XBoxController.getRawAxis(Constants.RTAxisPort));
    }

    public void setVelo() {
        shooterTalon.set(RobotContainer.XBoxController.getRawAxis(Constants.RTAxisPort));
    }

    public double veloCalc(double angle) {
        double finalVal = 0.0;
        return finalVal;
    }
}