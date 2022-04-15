// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  
    private WPI_VictorSPX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
    private MotorControllerGroup leftSide, rightSide;
    private DifferentialDrive myDrive;

  public DrivetrainSubsystem() {
    leftFrontMotor = new WPI_VictorSPX(Constants.LEFT_FRONT_MOTOR_ID);
    leftRearMotor = new WPI_VictorSPX(Constants.LEFT_REAR_MOTOR_ID);
    rightFrontMotor = new WPI_VictorSPX(Constants.RIGHT_FRONT_MOTOR_ID);
    rightRearMotor = new WPI_VictorSPX(Constants.RIGHT_REAR_MOTOR_ID);

    leftSide = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
    rightSide = new MotorControllerGroup(rightFrontMotor, rightRearMotor);
    myDrive = new DifferentialDrive(leftSide, rightSide);

    leftFrontMotor.setInverted(false);
    leftRearMotor.setInverted(false);
    rightFrontMotor.setInverted(false);
    rightRearMotor.setInverted(false);

    leftFrontMotor.setNeutralMode(NeutralMode.Coast);
    leftRearMotor.setNeutralMode(NeutralMode.Coast);
    rightFrontMotor.setNeutralMode(NeutralMode.Coast);
    rightFrontMotor.setNeutralMode(NeutralMode.Coast);
  }

  public void driveArcade(double speed, double rotation) {
    myDrive.arcadeDrive(speed, rotation);
  }

  public void driveTank(double leftSpeed, double rightSpeed) {
    myDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void driveCurvature(double speed, double rotation, boolean canTurnInPlace) {
    myDrive.curvatureDrive(speed, rotation, canTurnInPlace);
  }
}
