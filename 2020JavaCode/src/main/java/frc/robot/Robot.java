/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.RobotContainer;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static RobotContainer ExistingRobot;

  private Command autoCommand; //Need to use for auto? Does this act like a placeholder?

  //Template Declarations, CHANGES NEEDED?
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    /*robotInit() is a method. Methods are the "what it does" part of a robot--
    everything inside the method's curly brackets is read and executed whenever the method is called
    (told by the code to do its thing). robotInit() is called first whenever the code is compiled.
    The first thing robotInit() does is call RobotContainer. This means it calls the method RobotContainer(). 
    Let's head over to it right now to see what it's doing.*/
    System.out.println("Created RobotContainer.");
    ExistingRobot = new RobotContainer();

    /*
    //Possibly add inverted method for all motors (TODO need to test!!!)
    ExistingRobot.m_leftDrive.setInverted(true);
    ExistingRobot.m_rightDrive.setInverted(true); */
    //Default template stuff
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet (20ms), no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
    /*Goodness me, all this time and I still haven't gotten into what peri-
    odic mode is!
  
    Anything referred to as "periodic" means that it will be executed every 20 milliseconds, which is 50
    times per second. It's quite useful to have something execute periodically if a value needs minute
    changes over time, such as when quickly adjusting your speed. Every 20ms the speed value will change
    depending on what the controller is inputting. Or you may want to read the distance from the ultra-
    sonic periodically and adjust depending on what the distance is. Bottom line: when you see "peri-
    odic", you think "every 20ms".
    
    This method, robotPeriodic(), runs the code inside the brackets every 20ms, regardless of what mode
    the robot is in. There are other modes, such as Autonomous and Tele-Op, that run only when the robot
    is in that particular mode. Take a look at your Driver Station and click the uppermost tab on the
    left, the one that looks like a steering wheel. This tab allows you to change the mode of your robot
    to Tele-Op, Autonomous, etc.*/
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    senseColor();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() { /*
    This method is called one time when entering Autonomous mode, in
    which the robot drives itself without any input from human drivers.  */
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    /* Check these out
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }*/
  }

  /**
   * This function is called periodically during autonomous. This method is called one time when entering Autonomous mode, in
    which the robot drives itself without any input from human drivers. We use multiple different branching
    paths that we want our robot to take during autonomous, which explains the switch-case format.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) { //m_autonomousCommand is a command
      //m_autonomousCommand.cancel();
    //}
  }

  /**
   * This function is called periodically during operator control. This method is called periodically during Tele-Op mode. Tele-Op is the mode in which you actually
  control the robot yourself.
  
  The Scheduler is in charge of scheduling commands and putting them into execution. If you press a
  button, the scheduler will put the corresponding command in queue until the command finishes execut-
  ing. Once execution finishes, the command is removed from the scheduler. If two buttons are pressed,
  the scheduler puts both of them into queue and then runs each of them alternately every 20ms. (Say
  for instance you hold the buttons to lift the robot and open the pincher at the same time. The sched-
  uler will execute the lift command during the first 20ms, then the pincher command during the next
  20ms, then the lift command again during the next 20ms, so on and so forth until the pincher command
  finishes executing and gets removed. From that point on, the lift command will be the only one in
  queue and run every 20ms.) There is a notable exception to this, which we'll see later.*/ 
  @Override
  public void teleopPeriodic() {
    //TODO put scheduler get instance and run function
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  @Override
  public void disabledInit() {/**
    * This function is called once each time the robot enters Disabled mode.
    */
    //TODO figure out what this is...
  }

  @Override
  public void disabledPeriodic() {
    //TODO figure out what this is...
  }

  public void senseColor() {
    Color detectedColor = RobotContainer.colorSensor.getColor();
    double IR = RobotContainer.colorSensor.getIR();

    RobotContainer.smartDashboard.putNumber("Red", detectedColor.red);
    RobotContainer.smartDashboard.putNumber("Green", detectedColor.green);
    RobotContainer.smartDashboard.putNumber("Blue", detectedColor.blue);
    RobotContainer.smartDashboard.putNumber("IR", IR);
  }
}
