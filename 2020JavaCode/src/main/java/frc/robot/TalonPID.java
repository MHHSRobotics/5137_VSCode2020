package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonPID {
    
    public TalonPID() {

    }

    public static void configTalonPIDValues(TalonSRX _talon, double f, double p, double i, double d) {
        _talon.config_kF(Constants.kPIDLoopIdx, f);
        _talon.config_kP(Constants.kPIDLoopIdx, p);
        _talon.config_kI(Constants.kPIDLoopIdx, i);
        _talon.config_kD(Constants.kPIDLoopIdx, d);
    }

    public static double printOutTalonPIDValues(TalonSRX _talon, double targetValue) {
        StringBuilder _sb= new StringBuilder();
            double motorOutput= _talon.getMotorOutputVoltage(); //needs to change 
            /* prepare line to print */
            _sb.append("\tout:");
            _sb.append(motorOutput);
            _sb.append("\tspd:");
            _sb.append(_talon.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
            double targetVelocity_UnitsPer100ms= targetValue * 4096 * 500.0 / 600;
            /* 1500 RPM in either direction */
            _talon.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
            /* append more signals to print when in speed mode. */
            _sb.append("\terr:");
            double error = _talon.getClosedLoopError(Constants.kPIDLoopIdx);
            _sb.append(error);
            _sb.append("\ttrg:");
            _sb.append(targetVelocity_UnitsPer100ms);
            return error;
    }

    public void TalonPIDExampleFunction(WPI_TalonSRX talon) {
        int quadVel = talon.getSensorCollection().getQuadratureVelocity();
    }

    
}