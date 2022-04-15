// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {
  // Subsystems
  private final DrivetrainSubsystem myDrive = new DrivetrainSubsystem();

  // Controllers
  private XboxController cont = new XboxController(Constants.CONT_ID);

  public RobotContainer() {
    myDrive.setDefaultCommand(new CurvatureDrive(myDrive,
    () -> cont.getLeftY(),            // Speed
    () -> cont.getRightX(),           // Rotation
    () -> cont.getLeftBumper(),       // Turn in Place
    () -> cont.getRightBumper()));    // Slow Mode

    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getAutonomousCommand() {
    return new ArcadeDrive(myDrive,
    () -> 0.7,                        // Speed
    () -> 0.0,                        // Rotation
    () -> false,                      // Slow Mode
    5.0);                             // Time
  }
}