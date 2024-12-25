package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.FilmIndustryPerson;

import java.util.List;

public interface FilmIndustryPersonReader {
    public List<FilmIndustryPerson> read();
}
