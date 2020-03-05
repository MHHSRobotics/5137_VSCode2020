package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Climb_Subsystem extends SubsystemBase {

    WPI_TalonSRX climbTalon;

    DigitalInput limitSwitchUpper;
    DigitalInput limitSwitchLower;
    
    public Climb_Subsystem() {
        limitSwitchUpper = RobotContainer.LimitSwitchUpper;
        limitSwitchLower = RobotContainer.LimitSwitchLower;
        climbTalon = RobotContainer.climbTalon;
    }

    public void periodic() {
    }

    public void goUp() { //true (1) is not pressed, false is pressed
        System.out.println("UPPER SWITCH IS GOING UP:" + limitSwitchUpper.get());
        if (limitSwitchUpper.get()) { //ready to climb
            climbTalon.set(-0.4);
        }        
        else {//limitSwitch is good
            climbTalon.set(0);
        }
    }

    public void goDown() {
        if (limitSwitchLower.get()) {
            climbTalon.set(0.4);
        }
        else { //limitSwitch is good
            climbTalon.set(0);
        }
    }
}