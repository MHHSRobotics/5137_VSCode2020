package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.InterruptableSensorBase.WaitResult;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.Sendable;

public class ControlPanel_Subsystem extends SubsystemBase {
    WPI_VictorSPX controlPanelVictor;

    ColorSensorV3 colorSensor;
    Color fieldsSensedColor;
    Color FMSColor;

    public ControlPanel_Subsystem() {
        controlPanelVictor = RobotContainer.controlPanelVictor;
        colorSensor = RobotContainer.colorSensor;
    }

    public void controlPanelPeriodic() {
        getColorSignal();
        System.out.println("ControlPanel Going");
    }


    public Color getColorSignal() { 

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (gameData.length() > 0) {
            switch (gameData.charAt(0)) {
            case 'B':
                // Blue case code
                return Color.kBlue;

            case 'G':
                // Green case code
                return Color.kGreen;

            case 'R':
                // Red case code
                return Color.kRed;

            case 'Y':
                // Yellow case code
                return Color.kYellow;
                
            default:
                // This is corrupt data
                return Color.kBlack;
            }
        } else {
            // Code for no data received yet
            return Color.kWhite;
        }
    }//need to send output of determined color to smartdashboard

    public void controlPosition(double velocity) { //calculates the amount of time the motor should run at a given velocity
        fieldsSensedColor = guessCurrentColorOnSensor();
        FMSColor = getColorSignal();

        spinMotor(calcNumberRotations(fieldsSensedColor, FMSColor), velocity);

    }

    public void controlRotation(double velocity) { //calculates the amount of time the motor should run to complete rotation control
        spinMotor(Constants.SpinWheel, velocity);
    }

    public Color guessCurrentColorOnSensor() {
        if (RobotContainer.colorSensor.getColor() == Color.kRed) {
            return Color.kBlue;
        }
        else if (RobotContainer.colorSensor.getColor() == Color.kBlue) {
            return Color.kRed;
        }
        else if (RobotContainer.colorSensor.getColor() == Color.kYellow) {
            return Color.kGreen;
        }
        else if (RobotContainer.colorSensor.getColor() == Color.kGreen) {
            return Color.kYellow;
        }
        else {
            return Color.kBlack;
        }
    }

    public double calcNumberRotations(Color kColorInput, Color kColorFMS) { 
        //takes in parameters of field's sensed color, and the fields desired color

        //Color wheel colors in order (ccw): red, green, blue, yellow
        if (kColorInput == kColorFMS) {
            return 0.0;
        }
        else {
            if ((kColorInput == Color.kRed && kColorInput == Color.kGreen) || 
            (kColorInput == Color.kGreen && kColorInput == Color.kBlue) || 
            (kColorInput == Color.kBlue && kColorInput == Color.kYellow) ||
            (kColorInput == Color.kYellow && kColorInput == Color.kRed)) {
                return 0.125;
            }
            if ((kColorInput == Color.kRed && kColorInput == Color.kBlue) ||
            (kColorInput == Color.kBlue && kColorInput == Color.kRed) ||
            (kColorInput == Color.kGreen && kColorInput == Color.kYellow) ||
            (kColorInput == Color.kYellow && kColorInput == Color.kGreen)) {
                return 0.25;
            }
            if ((kColorInput == Color.kRed && kColorInput == Color.kYellow) ||
            (kColorInput == Color.kGreen && kColorInput == Color.kRed) ||
            (kColorInput == Color.kBlue && kColorInput == Color.kGreen) ||
            (kColorInput == Color.kYellow && kColorInput == Color.kBlue)) {
                return -0.125;
            }
        }
        return 0.0;
    }
    public void spinMotor(double numberRotationsBig, double velocity) {
        //Note, currently, using 4 in. wheels on the control panel thing will make the ratio of curcumference 1:8, meaning one motor 
        //output spin means EXACTLY one color change on the wheel
        double distanceTravel = 0.0;
        double waitTime = 0.0;
        double numberRotationsSmall = 0.0;

        //0.125 as a rotation of the wheel = 1 spin of the actual controlled motor
        numberRotationsSmall = numberRotationsBig / 8.0; //converts return value into # rotations of gear wheel

        //Finds circumference
        distanceTravel = numberRotationsSmall * Constants.CPWheelCircum; //circuference of wheel is 8PI. Dist travel is in in.

        //Finds time to set motors for (in mms)
        waitTime = 1000 * distanceTravel/velocity;

        controlPanelVictor.set(velocity);
        try {
            Thread.sleep((long) waitTime);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        controlPanelVictor.set(0);

        //ex. controlPanelVictor.set(30);
        
    }

}
