import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmType;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiBus;
import com.pi4j.io.spi.SpiConfig;

public class ServoController {
    private final Context pi4j;
    private final Pwm pwm;
    private final Spi spi;
    private final PID pid;

    public ServoController(PID pid) {
        this.pi4j = Pi4J.newAutoContext();
        this.pid = pid;
        // PWM Setup for GPIO18 (PWM0)
        PwmConfig pwmConfig = Pwm.newConfigBuilder(pi4j)
                .id("servo-pwm")
                .name("Servo PWM")
                .address(18)
                .pwmType(PwmType.HARDWARE)
                .frequency(50) // 50Hz for servo
                .initial(0)
                .shutdown(0)
                .build();

        this.pwm = pi4j.create(pwmConfig);
        pwm.on();

        // SPI Setup for MCP3008
        SpiConfig spiConfig = Spi.newConfigBuilder(pi4j)
                .id("mcp3008")
                .name("MCP3008 ADC")
                .bus(SpiBus.BUS_0)
                .chipSelect(0)
                .build();

        this.spi = pi4j.create(spiConfig);
    }

    // Read analog value from MCP3008 channel (0–7)
    public int readADC(int channel) {
        byte[] tx = new byte[3];
        tx[0] = 0b00000001;
        tx[1] = (byte) ((8 + channel) << 4);
        tx[2] = 0;

        byte[] rx = spi.transfer(tx);
        int value = ((rx[1] & 0x03) << 8) | (rx[2] & 0xFF);
        return value;
    }

    // Maps ADC value (0–1023) to 0–360°
    public double getCurrentAngle() {
        int adcValue = readADC(0); // Use channel 0
        return (adcValue / 1023.0) * 360.0;
    }

    // Maps angle (0–360°) to PWM pulse width and sends it
    public void setPWMForAngle(double angle) {
        angle = Math.max(0, Math.min(360, angle));
        int pulseWidthUs = (int)(500 + (angle / 360.0) * (2500 - 500));
        double dutyCycle = (pulseWidthUs / 20000.0) * 100;
        pwm.dutyCycle(dutyCycle);
    }

    public void stop() {
        pwm.off();
        pi4j.shutdown();
    }
    public void moveServo(double angle, double tolerance){
        while(true){
            double currentAng = getCurrentAngle();
            double error = targetAngle - currentAngle;
            error = ((error + 180) % 360) - 180;

            if (Math.abs(error) < TOLERANCE) break;

            double correction = pid.calculate(error);
            double newAngle = currentAngle + correction;
            newAngle = (newAngle + 360) % 360;

            controller.setPWMForAngle(newAngle);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}