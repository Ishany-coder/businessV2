package src.claws.Layer1;

public abstract class MainClaw {
    public abstract void setMovementDist(double x, double y, double z);
    public abstract void Move(boolean grab);
    public abstract Double[] getMovementDist();
};