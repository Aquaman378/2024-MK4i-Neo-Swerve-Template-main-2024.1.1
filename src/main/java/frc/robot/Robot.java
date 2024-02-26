package frc.robot;

//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {
    
    private RobotContainer robotContainer;

    private Command autonomousCommand;

    // private UsbCamera camera;


    @Override
    public void robotInit() {
        robotContainer = new RobotContainer();
        ledstrip.LEDStripController.competitionEnd();
        // camera = new UsbCamera("driver camera", 0);
        
        // CameraServer.startAutomaticCapture(camera);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        robotContainer.configDriverBindings();
        robotContainer.updateInterface();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();
        ledstrip.LEDStripController.autonomousMode();

        if(autonomousCommand != null) autonomousCommand.schedule();
    }

    @Override
    public void teleopInit() {
        ledstrip.LEDStripController.teleopMode();
        if(autonomousCommand != null) autonomousCommand.cancel();
    }

}
