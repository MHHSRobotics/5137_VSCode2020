package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake_Subsystem extends SubsystemBase {
    WPI_TalonSRX intakeTalon;

    SmartDashboard smartDashboard;

    double errorDefaultValue = -1.0;

    DoubleSolenoid leftPiston;
    DoubleSolenoid rightPiston;

    boolean intakeDown = false;
    
    public Intake_Subsystem() {
        intakeTalon = RobotContainer.intakeTalon;
        leftPiston = RobotContainer.leftPneumaticPiston;
        rightPiston = RobotContainer.rightPneumaticPiston;
        System.out.println("Intake Running...");
    }

    @Override
    public void periodic() {
        getAmmoCount();
    }

    public void toggleIntake() {
        if (intakeDown) {
            leftPiston.set(DoubleSolenoid.Value.kReverse);
            rightPiston.set(DoubleSolenoid.Value.kReverse);
            endIntake();
        }
        else if (!intakeDown) {
            leftPiston.set(DoubleSolenoid.Value.kForward);
            rightPiston.set(DoubleSolenoid.Value.kForward);
            intakeBalls();
        }
        else {
            //weird catch code
        }
    }

    public static DoubleSolenoid.Value getIntakeStatus(DoubleSolenoid piston) {
    return piston.get();
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

    public void refreshAmmoCount() {
        boolean catchError = false;

        if (SmartDashboard.getBoolean("Add 1 Ball", catchError) == true) {
            SmartDashboard.putNumber("Ball Count", (SmartDashboard.getNumber("Ball Count", errorDefaultValue) + 1.0));
            SmartDashboard.putBoolean("Add 1 Ball", false);
        }
        else if (SmartDashboard.getBoolean("Subtract 1 Ball", catchError) == true) {
            SmartDashboard.putNumber("Ball Count", (SmartDashboard.getNumber("Ball Count", errorDefaultValue) - 1.0));
            SmartDashboard.putBoolean("Subtract 1 Ball", false);
        }
    }

    public void endIntake() {
        intakeTalon.set(0);
    }
}