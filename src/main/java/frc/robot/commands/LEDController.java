import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.OutputStream;
import java.util.Enumeration;

public class LEDControl {
    private static final int BAUD_RATE = 9600;
    private static final int DATA_BITS = SerialPort.DATABITS_8;
    private static final int STOP_BITS = SerialPort.STOPBITS_1;
    private static final int PARITY = SerialPort.PARITY_NONE;

    public static void main(String[] args) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("/dev/ttyUSB0");
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                SerialPort serialPort = (SerialPort) portIdentifier.open("LEDControl", 2000);
                serialPort.setSerialPortParams(BAUD_RATE, DATA_BITS, STOP_BITS, PARITY);

                OutputStream outputStream = serialPort.getOutputStream();

                // Send signals based on robot's operational state
                outputStream.write("Autonomous".getBytes());
                outputStream.write("Telop".getBytes());
                outputStream.write("20-second warning".getBytes());
                outputStream.write("Telop completed".getBytes());

                serialPort.close();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
