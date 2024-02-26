package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.SerialPort;
 
/**
 * This class represents a Camera connected via USB Serial 2.
 */
public class USBSerialCamera {
    private SerialPort cameraSerialPort;
    public CameraServer {

     public SerialPort getCameraSerialPort() {
         return cameraSerialPort;
     }

     public void setCameraSerialPort(SerialPort cameraSerialPort) {
        this.cameraSerialPort = cameraSerialPort;
     }

     /**
     * Constructor to initialize the USB Serial Camera.
     */
     public USBSerialCamera() {
        cameraSerialPort = new SerialPort(9600, SerialPort.Port.kUSB2);
     }
 
     /**
     * Method to capture an image from the USB Serial Camera.
     *
     * @return Returns the captured image as a byte array.
     */
     public byte[] captureImage() {
        // Code to capture image from the camera
        return new byte[0]; // Placeholder return
     }
 
    /**
     * Method to process the captured image from the USB Serial Camera.
     *
     * @param image The captured image as a byte array.
     * @return Returns the processed image as a byte array.
     */
    public byte[] processImage(byte[] image) {
        // Code to process the captured image
        return new byte[0]; // Placeholder return
    }
 
     /**
      * Method to display the processed image from the USB Serial Camera.
      *
      * @param processedImage The processed image as a byte array.
       */
     public void displayImage(byte[] processedImage) {
         // Code to display the processed image
     }
    }
}