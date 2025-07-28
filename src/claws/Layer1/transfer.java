public class transfer{

    private PID wristPID = new PID(1, 1, 1);
    private PID elbowPID = new PID(1, 1, 1);
    private PID ShoulderPID = new PID(1, 1, 1);
    private PID otherWristPID = new PID(1, 1, 1);

    private ServoController wrist = new ServoController(wristPID);
    private ServoController elbow = new ServoController(elbowPID);
    private ServoController shoulder = new ServoController(ShoulderPID);
    private ServoController otherWrist = new ServoController(otherWristPID);

    //Constants for the transfer claw
    private static WRIST_GRAB_POS = 0;
    private static WRIST_RELEASE_POS = 180;
    private static ELBOW_GRAB_POS = 90;
    private static ELBOW_RELEASE_POS = 0;
    private static SHOULDER_GRAB_POS = 90;
    private static SHOULDER_RELEASE_POS = 0;
    private static OTHER_WRIST_GRAB_POS = 90;
    private static OTHER_WRIST_RELEASE_POS = 0;

    public void Move(boolean grab) {
        //move
        if (grab) {
            wrist.moveServo(WRIST_GRAB_POS);
            elbow.moveServo(ELBOW_GRAB_POS);
            shoulder.moveServo(SHOULDER_GRAB_POS);
            otherWrist.moveServo(OTHER_WRIST_GRAB_POS);
        } else {
            wrist.moveServo(WRIST_RELEASE_POS);
            elbow.moveServo(ELBOW_RELEASE_POS);
            shoulder.moveServo(SHOULDER_RELEASE_POS);
            otherWrist.moveServo(OTHER_WRIST_RELEASE_POS);
        }
        // Use the output to move the grabber claw
    }
}
