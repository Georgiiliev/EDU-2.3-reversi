package model;

public interface GameState {

}
class GameEndedDraw implements GameState{

}
class GameEndedWin implements GameState{

}
class GameEndedLoss implements GameState{

}
class GameStarted implements GameState{

}
class YourMove implements GameState{

}
class ServerMove implements GameState{

}
class Idle implements GameState{

}