package zenithmods.AdaptiveMechanics.api.tile;

public interface ILifterReceiver {
    public void lifterStateChange(boolean lifting);
    public void addLifterTicks(int ticks);
}
