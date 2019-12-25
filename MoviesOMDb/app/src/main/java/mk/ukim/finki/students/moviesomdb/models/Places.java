package mk.ukim.finki.students.moviesomdb.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Places implements Serializable {
    public List<Place> results = new ArrayList<>();
    public String status;
}
