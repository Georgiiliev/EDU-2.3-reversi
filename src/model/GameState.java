package model;

public interface GameState {
    boolean action();
}
class GameEndedDraw implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}
class GameEndedWin implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}
class GameEndedLoss implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}
class GameStarted implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}
class YourMove implements GameState{

    @Override
    public boolean action() {
        return true;
    }
}
class ServerMove implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}
class Idle implements GameState{

    @Override
    public boolean action() {
        return false;
    }
}