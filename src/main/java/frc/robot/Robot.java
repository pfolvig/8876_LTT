// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// import custom CAN libraries 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  //  set CAN addresses
  private static final int CANMotor1 = 1; 
  private static final int CANMotor2 = 2;
  private static final int CANMotor3 = 3; 
  private static final int CANMotor4 = 4;
  // create new motor objects
  private CANSparkMax motor1 = new CANSparkMax(CANMotor1, MotorType.kBrushed);
  private CANSparkMax motor2 = new CANSparkMax(CANMotor2, MotorType.kBrushed);
  private CANSparkMax motor3 = new CANSparkMax(CANMotor3, MotorType.kBrushed);
  private CANSparkMax motor4 = new CANSparkMax(CANMotor4, MotorType.kBrushed);

  // create MotorControllerGroups (1 & 2 is right and 3 & 4 is left)
  private final MotorControllerGroup rightMotorGroup = new MotorControllerGroup(motor1, motor2);
  private final MotorControllerGroup leftMotorGroup = new MotorControllerGroup(motor3, motor4);

  // set up escs
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  private Joystick joystick1 = new Joystick(0);
  // private Joystick m_rightStick = new Joystick(1);


  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    
    // Need to invert as gearbox is setup in reverse configuration
    leftMotorGroup.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //m_robotDrive.arcadeDrive(joystick1.getRawAxis(1), joystick1.getRawAxis(3));
    m_robotDrive.tankDrive(-joystick1.getRawAxis(1), -joystick1.getRawAxis(3));
  }
}
