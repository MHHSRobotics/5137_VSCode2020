package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Shooter_Subsystem extends SubsystemBase {
    //Variable declarations go here...
    WPI_TalonSRX shooterTalon;
    
    public Shooter_Subsystem() {
        //Variable assignment goes here...
        shooterTalon = RobotContainer.shooterTalon;
    }

    @Override
    public void periodic() {
        
    }

    public void setVelo(double angle) {
        if (checkReadyShoot()) {
            shooterTalon.set(veloCalc(angle)); //RobotContainer.XBoxController.getRawAxis(Constants.RTAxisPort));
        }
        else {
            shooterTalon.set(0);
        }
    }

    public double veloCalc(double angle) {
        double exitVelo = 0.0;
        double distAway = findDistance();

        //used to debug distance value
        System.out.println("Distance from target is : " + distAway);
        SmartDashboard.putNumber("Distance from Target", distAway);

        //put calculations with everything here... (thanks to Ashwin) (subject to change, since these calcs assume that the launch is from the ground)
        exitVelo = Math.sqrt((Constants.gravitationalAccel * Math.pow(distAway, 2)) / ((distAway * Math.sin(2 * angle)) - (2 * (Constants.towerHeight - Constants.limelightHeight) * Math.pow(Math.cos(Constants.shooterAngle), 2))));
        return exitVelo;
    }

    public double findDistance() { //finds distance away from target (tower) in inches...
        //May need to move onto Limelight
        return ((Constants.towerHeight - Constants.limelightHeight) / Math.tan(Robot.targety));
    }
    
    public boolean checkReadyShoot() {
        
        return true; //needs changes obvs
    }

    public void orientHorizontalTurn() {
        if (Robot.targetx >= 0.1) {
            //RobotContainer.driveBase_Subsystem.rampArcadeDrive(Robot.ExistingRobot.XBoxController); ?
        }
        else if (Robot.targetx <= -0.1) {

        }
    }
}