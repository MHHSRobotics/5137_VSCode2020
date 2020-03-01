package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.TalonPID;


public class Shooter_Subsystem extends SubsystemBase {
    //Variable declarations go here...
    DifferentialDrive BMoneysDriveBase;

    WPI_TalonSRX shooterTalon;
    WPI_TalonSRX shooterFollowerTalon;

    Joystick XBoxController;

    double distAway;

    Solenoid shootPneumaticPistonOne;

    boolean horizontalTurnGood;
    boolean velocityRunningGood;
    boolean toggle;

    double motorSpeed;
    
    public Shooter_Subsystem() {
        //Variable assignment goes here...
        XBoxController = RobotContainer.XBoxController;
        BMoneysDriveBase = RobotContainer.BMoneysDriveBase;
        shooterTalon = RobotContainer.shooterTalon;
        shooterFollowerTalon = RobotContainer.followerShooterTalon;
        shootPneumaticPistonOne = RobotContainer.shootPneumaticPistonOne;
        toggle = false;
        horizontalTurnGood = false;
        velocityRunningGood = false;
        motorSpeed = 1.0;
    }

    @Override
    public void periodic() {
        distAway = findDistance();
            //shooterTalon.set(motorSpeed);
            //shooterFollowerTalon.set(motorSpeed);
        //shooterFollowerTalon.set(motorSpeed);
        if (XBoxController.getRawAxis(Constants.RTAxisPort) < 0.1) {
            shooterTalon.set(0);
            shooterFollowerTalon.set(0);
        }
    }

    public void shoot(double angle) { //called by command (constantly)      
        RobotContainer.storage_Subsystem.store(false, false); 
        shooterTalon.set(motorSpeed);
        shooterFollowerTalon.set(motorSpeed);
       if (checkReadyShoot(angle)) { //pnce ready to shoot
        //RobotContainer.storage_Subsystem.store(false, false);
       }
       else { //pnce not ready to shoot
        //RobotContainer.storage_Subsystem.store(false, true);
       }
    }

    public void endShoot() { /*
        shooterTalon.set(0);
        shooterFollowerTalon.set(0); */ 
    }

    public boolean setVelo(double angle) { //return when velocity is running optimally 
        double setVelo = convertVelo(veloCalc(angle)); 
        double magVelo = Robot.convertLinearVelocityToMag(setVelo, Constants.currentShooterRadius); //may need to use PHASE with the MAG Encoders

        //Closed-Loop Control of Talons

        //shooterTalon.set(mode, demand0, demand1Type, demand1);
        
        //shooterTalon.set(-0.8);
        //shooterFollowerTalon.set(0);
        //double error = TalonPID.printOutTalonPIDValues(shooterTalon, setVelo);

        //Return true if within a degree of error, or else don't
        /*
        if (error <= Constants.veloError && error >= -Constants.veloError) 
         {
            return true; 
        }
        else {
            return false;
        }  */
        /*
        if (1000 <= shooterTalon.getSensorCollection().getQuadratureVelocity()) {
            System.out.println("Encoder says: " + shooterTalon.getSensorCollection().getQuadratureVelocity());
            return true;
        }
        else {
            return false;
        } */
        return true;
        
    }

    public double veloCalc(double angle) {
        double exitVelo = 0.0;
        distAway = findDistance();

        //used to debug distance value
        System.out.println("Distance from target is : " + distAway);
        SmartDashboard.putNumber("Distance from Target", distAway);

        //put calculations with everything here... (thanks to Ashwin) (subject to change, since these calcs assume that the launch is from the ground)
        //exitVelo = Math.sqrt((Constants.gravitationalAccel * Math.pow(distAway, 2)) / ((distAway * Math.sin(2 * angle)) - (2 * (Constants.towerHeight - Constants.limelightHeight) * Math.pow(Math.cos(Constants.shooterAngle), 2))));

        //BMoney's Equation including different heights
        exitVelo = (distAway * Math.cos(Constants.shooterAngle *Constants.PI / 180)) / 
        (Math.sqrt((2 * (Constants.towerHeight - Constants.limelightHeight)) / 
        Constants.gravitationalAccel));
        return exitVelo;
    }

    public double convertVelo(double velo) { //converts given velocity into MAG velocity
        return velo * 4096 * 500 / 600;
    }

    public double findDistance() { //finds distance away from target (tower) in inches...
        //May need to move onto Limelight
        double angle = (Constants.PI * (Robot.targety + Constants.limelightAngle)) / 180;
        return ((Constants.towerHeight - Constants.limelightHeight) / Math.tan(angle));
    }
    
    public boolean checkReadyShoot(double angle) {
        velocityRunningGood = setVelo(angle);
        //horizontalTurnGood = RobotContainer.driveBase_Subsystem.orientHorizontalTurn();
        horizontalTurnGood = true;
     
        if (horizontalTurnGood && velocityRunningGood) {
            return true;
        }
        else {
            return false;
        }
    }
}
