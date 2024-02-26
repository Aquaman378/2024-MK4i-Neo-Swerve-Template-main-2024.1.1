package ledstrip;

import edu.wpi.first.wpilibj.SerialPort;
 
/**
 * This class establishes a connection between a RoboRIO and an Arduino Uno via USB Serial 1.
 */
public class RoboRioArduino {
    
    private SerialPort arduinoSerial;
 
    /**
     * Constructor to initialize the connection between RoboRIO and Arduino Uno via USB Serial 1.
     */
    public RoboRioArduino() {
        arduinoSerial = new SerialPort(9600, SerialPort.Port.kUSB1);
    }
 
    /**
     * Sends data to the Arduino Uno over the established serial connection.
     *
     * @param data The data to be sent to the Arduino Uno.
     */
    public void sendDataToArduino(String data) {
        arduinoSerial.writeString(data);
    }
 
    /**
     * Receives data from the Arduino Uno over the established serial connection.
     *
     * @return Returns the data received from the Arduino Uno.
     */
    public String receiveDataFromArduino() {
        return arduinoSerial.readString();
    }
 
    /**
     * Closes the serial connection between RoboRIO and Arduino Uno.
     */
    public void closeConnection() {
        arduinoSerial.close();
    }
 
    // Usage Example for RoboRioArduinoConnection
 
    public static void main(String[] args) {
        RoboRioArduino connection = new RoboRioArduino();
 
        // Sending data to Arduino Uno
        connection.sendDataToArduino("Hello from RoboRIO!");
 
        // Receiving data from Arduino Uno
        String receivedData = connection.receiveDataFromArduino();
        System.out.println("Received data from Arduino Uno: " + receivedData);
 
        // Closing the connection
        connection.closeConnection();
    }
}