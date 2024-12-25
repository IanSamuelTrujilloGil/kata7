package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.FilmIndustryPerson;
import software.ulpgc.kata7.architecture.model.TsvFilmIndustryPersonDeserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TsvFilmIndustryPersonReader implements FilmIndustryPersonReader{

    private final InputStream fileStream;

    public TsvFilmIndustryPersonReader(InputStream fileStream) {
        this.fileStream = fileStream;
    }

    @Override
    public List<FilmIndustryPerson> read() {
        try(BufferedReader reader = new BufferedReader( new InputStreamReader(fileStream))){
            reader.readLine();
            return readWith(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<FilmIndustryPerson> readWith(BufferedReader reader) throws IOException {
        TsvFilmIndustryPersonDeserializer deserializer = new TsvFilmIndustryPersonDeserializer();
        List<FilmIndustryPerson> filmIndustryPeople = new ArrayList<>();
        while (true){
            String field = reader.readLine();
            if(field == null) break;
            filmIndustryPeople.add(deserializer.deserialize(field));
        }
        return filmIndustryPeople;
    }
}
