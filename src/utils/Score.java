package utils;

public class Score {
    private int score;
    
    // Constructeur Score
    public Score() {
        score = 0;
    }
    
    // Methode pour ajouter des points 
    public void addPoints(int points) {
        score += points;
    }

    // Getter
    public int getScore() {
        return score;
    }
}