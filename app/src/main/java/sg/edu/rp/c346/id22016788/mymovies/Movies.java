package sg.edu.rp.c346.id22016788.mymovies;

import java.io.Serializable;

public class Movies implements Serializable {

    private int id;
    private String title;
    private String genre;
    private String year;
    private String rating;


    public Movies(int id, String title, String genre, String year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {  return id;  }

    public String getMovieTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public String getMovieYear() {
        return year;
    }
    public String getMovieRating() {
        return rating;
    }

    public void setMovieContent(int id, String title, String genre, String year, String rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ID:" + id + ", " + title;
    }

}
