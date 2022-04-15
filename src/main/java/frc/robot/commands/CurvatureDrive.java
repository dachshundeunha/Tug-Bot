// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class CurvatureDrive extends CommandBase {

  private DrivetrainSubsystem sub;
  private Supplier<Double> speed, rotation;
  private Supplier<Boolean> canRotateInPlace, slowMode;

  private double time = 0.0;
  private Timer timer;

  public CurvatureDrive(DrivetrainSubsystem mew, Supplier<Double> speed, Supplier<Double> rotation, Supplier<Boolean> canRotateInPlace, Supplier<Boolean> slowMode) {
    this.sub = mew;
    this.speed = speed;
    this.rotation = rotation;
    this.canRotateInPlace = canRotateInPlace;
    this.slowMode = slowMode;
    addRequirements(sub);
  }

  public CurvatureDrive(DrivetrainSubsystem mew, Supplier<Double> speed, Supplier<Double> rotation, Supplier<Boolean> canRotateInPlace, Supplier<Boolean> slowMode, double time) {
    this.sub = mew;
    this.speed = speed;
    this.rotation = rotation;
    this.canRotateInPlace = canRotateInPlace;
    this.slowMode = slowMode;
    this.time = time;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    double driveSpeed = speed.get();
    double rotationSpeed = rotation.get();
    boolean canRotate = canRotateInPlace.get();
    boolean slow = slowMode.get();

    if (slow) {
      driveSpeed *= 0.5;
      rotationSpeed *= 0.5;
    }

    sub.driveCurvature(driveSpeed, rotationSpeed, canRotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (time != 0.0) ? timer.get() >= time : false;
  }
}
