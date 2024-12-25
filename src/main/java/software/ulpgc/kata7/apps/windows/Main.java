package software.ulpgc.kata7.apps.windows;

import software.ulpgc.kata7.architecture.control.ToggleGraphCommand;
import software.ulpgc.kata7.architecture.io.ProfessionBarchartLoader;
import software.ulpgc.kata7.architecture.io.TsvFilmIndustryPersonReader;
import software.ulpgc.kata7.architecture.model.FilmIndustryPerson;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<FilmIndustryPerson> filmIndustryPeople = new TsvFilmIndustryPersonReader(getFileStream()).read();
        HashMap<String, Integer> professionToPeopleCount = new HashMap<>();
        HashMap<String, Integer> professionCountToPeopleCount = new HashMap<>();
        for(FilmIndustryPerson filmIndustryPerson: filmIndustryPeople){
            for(FilmIndustryPerson.Profession profession: filmIndustryPerson.getPrimaryProfession()){
                professionToPeopleCount.put(String.valueOf(profession), professionToPeopleCount.getOrDefault(String.valueOf(profession)
                        ,0) +1);
            }
            professionCountToPeopleCount.put(String.valueOf(filmIndustryPerson.getPrimaryProfession().length),
                    professionCountToPeopleCount.getOrDefault(String.valueOf(filmIndustryPerson.getPrimaryProfession().length), 0) +1);
        }

        MainFrame mainFrame = new MainFrame();
        ProfessionBarchartLoader loader = new ProfessionBarchartLoader(professionToPeopleCount, professionCountToPeopleCount);
        mainFrame.put("toggle", new ToggleGraphCommand(mainFrame.getDisplay(), loader));
        mainFrame.getDisplay().show(loader.load(0));
        mainFrame.setVisible(true);
    }

    private static InputStream getFileStream() {
        return Main.class.getResourceAsStream("/name.basics.tsv");
    }
}
