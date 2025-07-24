package Controller;

public class PID(){
    private double p,i,d;
    private double LastError = 0
    private final ElapsedTime timer = new ElapsedTime();
    public PID(double p, double i, double d){
        this.p = p;
        this.i = i;
        this.d = d;
        timer.reset(); //start the timer
    }
    public void setP(double p){
        this.p = p;
    }
    public void setI(double i){
        this.i = i;
    }
    public void setD(double d) {
        this.d = d;
    }
    public double getP(){
        return p;
    }
    public double getI(){
        return i;
    }
    public double getD(){
        return d;
    }
    public double calculate(double error){
        double deltaTime = timer.seconds();
        timer.reset();
        if(deltaTime <= 0) {deltaTime = 1e-3}; // if the time is negative set to 0.001
        double errorSum = error * deltaTime;
        double dError = (errorSum - LastError) / deltaTime // speed of the error
        double output = p * error + i * errorSum + d * dError;
        LastError = errorSum;
        return output;
    }
}