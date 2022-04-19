// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TankDrive extends CommandBase {

  private DrivetrainSubsystem sub;
  private Supplier<Double> leftSpeed, rightSpeed;
  private Supplier<Boolean> slowMode = () -> false;

  private double time = 0.0;
  private Timer timer = new Timer();

  public TankDrive(DrivetrainSubsystem mew, Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    this.sub = mew;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    addRequirements(sub);
  }

  public TankDrive(DrivetrainSubsystem mew, Supplier<Double> leftSpeed, Supplier<Double> rightSpeed, Supplier<Boolean> slowMode) {
    this.sub = mew;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    this.slowMode = slowMode;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    double left = leftSpeed.get();
    double right = rightSpeed.get();
    boolean slow = slowMode.get();

    if (slow) {
      left *= 0.5;
      right *= 0.5;
    }

    sub.driveTank(left, right);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return (time != 0.0) ? timer.get() >= time : false;
  }
}