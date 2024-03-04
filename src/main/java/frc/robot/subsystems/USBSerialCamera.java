package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;

/**
 *
 * This class sets up a USB camera server for a RoboRIO using the 2024 WPILib imports.
 */
public class USBSerialCamera {

    /**
     * Initializes and starts a USB camera server for a RoboRIO.
     */
    public static void setupCameraServer() {
        // Create a new instance of CameraServer

        // Set the resolution and frame rate for the camera (adjust as needed)
        CameraServer.startAutomaticCapture("USB Camera", 0);

        // Print a message indicating that the camera server has been set up successfully
        System.out.println("USB Camera Server for RoboRIO has been set up.");
    }

    // Main method to demonstrate setting up the USB camera server
    public static void main(String[] args) {
        setupCameraServer();
    }

    @Override
    public String toString() {
        return "USBSerialCamera []";
    }
}