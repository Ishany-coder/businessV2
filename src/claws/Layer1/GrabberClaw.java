public class GrabberClaw extends MainClaw {
    private double xDist;
    private double yDist;
    private double zDist; // Assuming zDist is not used in this claw

    private PID wristPID = new PID(1, 1, 1);
    private PID elbowPID = new PID(1, 1, 1);
    private PID turretPID = new PID(1, 1, 1);
    private PID grabberPID = new PID(1, 1, 1);

    private ServoController turret = new ServoController(turretPID);
    private ServoController elbow = new ServoController(elbowPID);
    private ServoController wrist = new ServoController(wristPID);
    private ServoController grabber = new ServoController(grabberPID);

    private static final double WRIST_RELEASE_POS = 180;
    private static final double TURRET_RELEASE_POS = 0;
    private static final double ELBOW_RELEASE_POS = 0;
    private static final double GRABBER_RELEASE_POS = 0;
    public void setMovementDist(double x, double y, double z) {
        this.xDist = x;
        this.yDist = y;
        this.zDist = z;
    }

    public void Move(boolean grab) {
        //conver to polar coordinates
        if(grab){
        double theta = Math.atan2(yDist, xDist);
        double phi = Math.atan2(zDist, Math.sqrt(xDist * xDist + yDist * yDist));
        double turretAngle = Math.toDegrees(theta);
        double elbowAngle = Math.toDegrees(phi);

        //move
        elbow.moveServo(elbowAngle);
        turret.moveServo(turretAngle);
        wrist.moveServo(90);
        grabber.moveServo(100);
        } else {
            // Release positions
            wrist.moveServo(WRIST_RELEASE_POS);
            turret.moveServo(TURRET_RELEASE_POS);
            elbow.moveServo(ELBOW_RELEASE_POS);
            grabber.moveServo(GRABBER_RELEASE_POS);
        }
        // Use the output to move the grabber claw
    }

    public Double[] getMovementDist() {
        return new Double[]{xDist, yDist, zDist};
    }
}