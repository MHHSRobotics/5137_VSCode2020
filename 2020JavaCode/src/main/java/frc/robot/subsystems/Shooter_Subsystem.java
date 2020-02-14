package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;


public class Shooter_Subsystem extends SubsystemBase {
    //Variable declarations go here...
    DifferentialDrive BMoneysDriveBase;

    WPI_TalonSRX shooterTalon;

    Joystick XBoxController;

    double distAway;

    DoubleSolenoid shootPneumaticPistonOne;
    DoubleSolenoid shootPneumaticPistonTwo;
    DoubleSolenoid shootPneumaticPistonThree;
    
    public Shooter_Subsystem() {
        //Variable assignment goes here...
        XBoxController = RobotContainer.XBoxController;
        BMoneysDriveBase = RobotContainer.BMoneysDriveBase;
        shooterTalon = RobotContainer.shooterTalon;
        shootPneumaticPistonOne = RobotContainer.shootPneumaticPistonOne;
        shootPneumaticPistonTwo = RobotContainer.shootPneumaticPistonTwo;
        shootPneumaticPistonThree = RobotContainer.shootPneumaticPistonThree;
    }

    @Override
    public void periodic() {
        distAway = findDistance();
    }

    public void shoot(double angle) { //called by command (constantly)
       while (checkReadyShoot(angle)) { //pnce ready to shoot
            shootPneumaticPistonOne.set(DoubleSolenoid.Value.kReverse);
            shootPneumaticPistonTwo.set(DoubleSolenoid.Value.kReverse);
            shootPneumaticPistonThree.set(DoubleSolenoid.Value.kReverse);
       }
       //pnce not ready to shoot
        shootPneumaticPistonOne.set(DoubleSolenoid.Value.kForward);
        shootPneumaticPistonTwo.set(DoubleSolenoid.Value.kForward);
        shootPneumaticPistonThree.set(DoubleSolenoid.Value.kForward);
    }

    public boolean setVelo(double angle) { //return when velocity is running optimally 
        double setVelo = veloCalc(angle); 
        double magVelo = Robot.convertLinearVelocityToMag(setVelo, Constants.currentShooterRadius); //may need to use PHASE with the MAG Encoders

        shooterTalon.set(magVelo);

        if (Robot.convertMagToLinearVelocity(shooterTalon.getSelectedSensorVelocity(), (Constants.currentShooterRadius)) <= (setVelo + Constants.veloError) || 
         Robot.convertMagToLinearVelocity(shooterTalon.getSelectedSensorVelocity(), (Constants.currentShooterRadius)) >= (setVelo - Constants.veloError)) 
         {
            return true; 
        }
        else {
            return false;
        }
    }

    public double veloCalc(double angle) {
        double exitVelo = 0.0;
        distAway = findDistance();

        //used to debug distance value
        System.out.println("Distance from target is : " + distAway);
        SmartDashboard.putNumber("Distance from Target", distAway);

        //put calculations with everything here... (thanks to Ashwin) (subject to change, since these calcs assume that the launch is from the ground)
        //exitVelo = Math.sqrt((Constants.gravitationalAccel * Math.pow(distAway, 2)) / ((distAway * Math.sin(2 * angle)) - (2 * (Constants.towerHeight - Constants.limelightHeight) * Math.pow(Math.cos(Constants.shooterAngle), 2))));

        return exitVelo;
    }

    public double findDistance() { //finds distance away from target (tower) in inches...
        //May need to move onto Limelight
        return ((Constants.towerHeight - Constants.limelightHeight) / Math.tan(Robot.targety));
    }
    
    public boolean checkReadyShoot(double angle) {
        boolean horizontalTurnGood = false;
        boolean velocityRunningGood = false;

        do {
            horizontalTurnGood = orientHorizontalTurn();
            velocityRunningGood = setVelo(angle);
            
        } while (!horizontalTurnGood && !velocityRunningGood);

        return true;
        
    }

    public boolean orientHorizontalTurn() { //returns true if the robot is horizontally oriented, false if interrupted
        if (Robot.targetx >= Constants.marginAngleError) { //if too far to the left
            do {
                BMoneysDriveBase.curvatureDrive(0.0, -Constants.turnRate, true); //turn cntrclockwise

            } while (Robot.targetx >= Constants.marginAngleError);
            
            return true;
            
        }
        else if (Robot.targetx <= -Constants.marginAngleError) { //too far to the right
            
            do {
                BMoneysDriveBase.curvatureDrive(0.0, Constants.turnRate, true); //turn clockwise
            } while (Robot.targetx <= -Constants.marginAngleError);
            
            return true;
        }
        else {

            
            while (Robot.targetx >= Constants.marginAngleError) { //check this will run forever without trigger updates
                BMoneysDriveBase.curvatureDrive(0.0, Constants.turnRate, true); //turn clockwise
            }

            if (Robot.targetx >= -Constants.marginAngleError && Robot.targetx <= Constants.marginAngleError) {
                BMoneysDriveBase.curvatureDrive(XBoxController.getRawAxis(Constants.LYStickAxisPort), 0.0, false);
            }
            return true;
            
        }
    }
}
