package states;

public enum GameState {
    PLAYING, MENU, OPTIONS;

    private static GameState currentState = PLAYING;

    public static GameState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(GameState currentState) {
        GameState.currentState = currentState;
    }
}
