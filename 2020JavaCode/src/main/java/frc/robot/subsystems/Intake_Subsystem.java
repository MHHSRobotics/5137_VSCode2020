package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake_Subsystem extends SubsystemBase {
    WPI_TalonSRX intakeTalon;

    SmartDashboard smartDashboard;

    double errorDefaultValue = -1.0;
    
    public Intake_Subsystem() {
        intakeTalon = RobotContainer.intakeTalon;
        System.out.println("Intake Running...");
    }

    @Override
    public void periodic() {
        getAmmoCount();
    }

    public void intakeBalls() {
        if (getAmmoCount() < 5.0 && getAmmoCount() >= 0.0) {
            intakeTalon.set(Constants.intakeTalonOutput);
        }
        else {
            intakeTalon.set(-Constants.intakeTalonOutput);
        }
    }

    public double getAmmoCount() {
        //smartDashboard.putNumber("Title", double number);
        refreshAmmoCount();
        return SmartDashboard.getNumber("Ball Count", errorDefaultValue);
    }

    public void refreshAmmoCount() {//TODO make so that counter won't continuously add 1 or subtract 1
        boolean catchError = false;

        if (SmartDashboard.getBoolean("Add 1 Ball", catchError) == true) {
            SmartDashboard.putNumber("Ball Count", (SmartDashboard.getNumber("Ball Count", errorDefaultValue) + 1.0));
        }
        else if (SmartDashboard.getBoolean("Subtract 1 Ball", catchError) == true) {
            SmartDashboard.putNumber("Ball Count", (SmartDashboard.getNumber("Ball Count", errorDefaultValue) - 1.0));
        }
    }
}