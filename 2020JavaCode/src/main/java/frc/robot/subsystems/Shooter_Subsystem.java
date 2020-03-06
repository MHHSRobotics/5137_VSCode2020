package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Shooter_Subsystem extends SubsystemBase {
    // Variable declarations go here...
    DifferentialDrive BMoneysDriveBase;

    WPI_TalonSRX shooterTalon;
    WPI_TalonSRX shooterFollowerTalon;

    Joystick XBoxController;

    double distAway;

    Solenoid shootPneumaticPistonOne;

    boolean horizontalTurnGood;
    boolean velocityRunningGood;
    boolean LimelightKnowsManual;

    NetworkTable table;

    public Shooter_Subsystem() {
        // Variable assignment goes here...
        XBoxController = RobotContainer.XBoxController;
        BMoneysDriveBase = RobotContainer.BMoneysDriveBase;
        shooterTalon = RobotContainer.shooterTalon;
        shooterFollowerTalon = RobotContainer.followerShooterTalon;
        shootPneumaticPistonOne = RobotContainer.shootPneumaticPistonOne;
        horizontalTurnGood = false;
        velocityRunningGood = false;
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    @Override
    public void periodic() {
        
        distAway = findDistance();
        //System.out.println("Distance away is: " + distAway);
            //shooterTalon.set(motorSpeed);
            //shooterFollowerTalon.set(motorSpeed);
        //shooterFollowerTalon.set(motorSpeed);
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public boolean shoot(double angle, boolean overrideDB, boolean manual) { //called by command (constantly)  
        LimelightKnowsManual = manual;  
        if (LimelightKnowsManual) {
            table.getEntry("pipeline").setNumber(2); //sets pipeline number 1-9. 1 isnt limelight, 2 is 
        } 
        else if (!LimelightKnowsManual) {
            table.getEntry("pipeline").setNumber(1); //sets pipeline number 1-9. 1 isnt limelight, 2 is 
        }
        else {
            table.getEntry("pipeline").setNumber(2); //sets pipeline number 1-9. 1 isnt limelight, 2 is 
        }  
        return checkReadyShoot(angle, overrideDB, manual);
    }

    public void endShoot() { 
        shooterTalon.set(ControlMode.Velocity, 0);
        shooterFollowerTalon.set(ControlMode.Velocity, 0); 
    }

    public boolean setVelo(double angle, boolean manual) { //return when velocity is running optimally 
        double setVelo = convertLinearVeloToMAG(veloCalc(angle)); 

        double controllerMAGVelo = Constants.maxVeloShooter * XBoxController.getRawAxis(Constants.LTAxisPort);

        if (manual) {

            shooterTalon.set(ControlMode.Velocity, controllerMAGVelo);
            shooterFollowerTalon.set(ControlMode.Follower, Constants.shooterCAN);

            System.out.println("Shooters Running at: " + XBoxController.getRawAxis(Constants.LTAxisPort) + "%");
            System.out.println("Velocity is reading as: " + shooterTalon.getSelectedSensorVelocity());
            
            //Return true if within a degree of error, or else don't
            double encoderValue = shooterTalon.getSelectedSensorVelocity();

            if ((controllerMAGVelo <= (encoderValue + Constants.veloError)) && 
            (controllerMAGVelo >= (encoderValue - Constants.veloError))) {
            return true; 
            }
            else {
            return false;
            }  
        }
        else { //AutoShootingAlgorithm
            
            shooterTalon.set(ControlMode.Velocity, setVelo);
            shooterFollowerTalon.set(ControlMode.Velocity, setVelo); 
        
            /*
            double motorOutput = shooterTalon.getMotorOutputPercent();

            System.out.println("\tout: " + (int) (motorOutput * 100) + "%");
            System.out.println("\tspd: " + shooterTalon.getSelectedSensorVelocity(Constants.kPIDLoopIdx) + "u");
            
            double targetVelocity_UniterPer100ms = setVelo * 500.0 * 4096 / 600;
            shooterTalon.set(ControlMode.Velocity, targetVelocity_UniterPer100ms, DemandType.AuxPID, targetVelocity_UniterPer100ms);

            System.out.println("\terr: " + shooterTalon.getClosedLoopError(Constants.kPIDLoopIdx) + "\ttrg: " + targetVelocity_UniterPer100ms);

            */

            /*
            double targetVelocity_UnitsPer100ms = leftYstick * 500.0 * 4096 / 600;
			// 500 RPM in either direction 
			_talon.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

			// Append more signals to print when in speed mode. 
			_sb.append("\terr:");
			_sb.append(_talon.getClosedLoopError(Constants.kPIDLoopIdx));
			_sb.append("\ttrg:");
            _sb.append(targetVelocity_UnitsPer100ms); 
            */

            System.out.println("Shooters Running at: " + shooterTalon.getMotorOutputPercent() + "%");
            System.out.println("Velocity is reading as: " + shooterTalon.getSelectedSensorVelocity());

            double encoderValue = shooterTalon.getSelectedSensorVelocity();

            if ((setVelo <= (encoderValue + Constants.veloError)) && 
            (setVelo >= (encoderValue - Constants.veloError))) {
            return true; 
            }
            else {
            return false;
            }  
        }
    }

    public double veloCalc(double angle) {//returns value of shooter velocity in inches per second (returns NaN if negative)
        double exitVelo = 0.0;
        distAway = findDistance() + Constants.limelightAwayShooter;
        double heightTraveledUp = Constants.towerHeight - Constants.shooterHeight; //needs changes

        //used to debug distance value
        System.out.println("Distance from target is : " + distAway);
        SmartDashboard.putNumber("Distance from Target", distAway);

        //put calculations with everything here... (thanks to Ashwin) (subject to change, since these calcs assume that the launch is from the ground)
        //exitVelo = Math.sqrt((Constants.gravitationalAccel * Math.pow(distAway, 2)) / ((distAway * Math.sin(2 * angle)) - (2 * (Constants.towerHeight - Constants.limelightHeight) * Math.pow(Math.cos(Constants.shooterAngle), 2))));

        //BMoney's Equation including different heights 
        /*
        exitVelo = (distAway * Math.cos(Math.toRadians(Constants.shooterAngle))) / 
        (Math.sqrt((2 * (Constants.towerHeight - Constants.limelightHeight)) / 
        Constants.gravitationalAccel)); */

        //This is the REAL BMoney's Equation including different heights (don't believe the other one, it's fake), ask him for proof or something
        /*

        h = TowerHeight - ShooterHeight
        a = shooter Angle

            v(d) = Math.sqrt             (   g * d^2     )
                                          ---------------
                            (  h * cos(a)^2 - cos(a)^2 * tan(a) * d  )
        */
        exitVelo = Math.sqrt((Constants.gravitationalAccel * Math.pow(distAway, 2)) / 
        (heightTraveledUp * Math.pow(Math.cos(Math.toRadians(Constants.shooterAngle)), 2) - Math.pow(Math.cos(Math.toRadians(Constants.shooterAngle)), 2)
        * Math.tan(Math.toRadians(Constants.shooterAngle)) * distAway));
        return exitVelo;
    }

    public double convertLinearVeloToMAG(double lin_velo) { //converts linear velocity (in/s) on the end of the wheel to 
        return (4096 * lin_velo) / (60 * Constants.PI);
    }


    public double findDistance() { //finds distance away from target (tower) to shooter in inches...
        //May need to move onto Limelight

        //shooter is 14 inches higher than limelight (as of 3/4/2020)

        //shooter is 10.2 in away from limelight (as of 3/4/2020)

        double angle = Math.toRadians(Robot.targety + Constants.limelightAngle);
        return ((Constants.towerHeight - Constants.limelightHeight) / Math.tan(angle)) + Constants.limelightAwayShooter;
    }
    
    public boolean checkReadyShoot(double angle, boolean horizontalTurnEnabled, boolean manual) {
        velocityRunningGood = setVelo(angle, manual);
        if (horizontalTurnEnabled) {
            horizontalTurnGood = RobotContainer.driveBase_Subsystem.orientHorizontalTurn();
        }
        else {
            horizontalTurnGood = true;
        }
     
        if (horizontalTurnGood && velocityRunningGood) {
            return true;
        }
        else {
            return false;
        }
    }
}
