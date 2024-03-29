package ledstrip;
 
/**
 *
 * This class represents a LED strip connected to a RoboRIO controller via Digital Input/Output (DIO) ports.
 * The LED strip will display different colors based on the robot's state (autonomous, teleop, competition end).
 */
public class LEDStripController {
    private static boolean autonomousMode = false;
    public boolean isAutonomousMode() {
        return autonomousMode;
    }

    public void setAutonomousMode(boolean autonomousMode) {
        LEDStripController.autonomousMode = autonomousMode;
    }

    private static boolean teleopMode = false;
    public boolean isTeleopMode() {
        return teleopMode;
    }

    public void setTeleopMode(boolean teleopMode) {
        LEDStripController.teleopMode = teleopMode;
    }

    private static boolean competitionEnd = false;
 
    /**
     * Simulates the robot being in autonomous mode by strobing shades of green and orange on the LED strip.
     */
    public static void autonomousMode() {
        autonomousMode = true;
        teleopMode = false;
        competitionEnd = false;
 
        // Strobe shades of green and orange
        while (autonomousMode) {
            // Code to control LED strip for autonomous mode
            // Green and orange strobing pattern
        }
    }
 
    /**
     * Simulates the robot being in teleop mode by strobing shades of blue and green on the LED strip.
     */
    public static void teleopMode() {
        autonomousMode = false;
        teleopMode = true;
        competitionEnd = false;
 
        // Strobe shades of blue and green
        while (teleopMode) {
            // Code to control LED strip for teleop mode
            // Blue and green strobing pattern
        }
    }
 
    /**
     * Simulates the end of the competition by displaying green on the LED strip.
     */
    public static void competitionEnd() {
        autonomousMode = false;
        teleopMode = false;
        competitionEnd = true;
 
        // Display green color
        if (competitionEnd) {
            // Code to control LED strip for competition end
            // Show green color
        }
    }
 
    // Additional methods for controlling the LED strip can be added here
}