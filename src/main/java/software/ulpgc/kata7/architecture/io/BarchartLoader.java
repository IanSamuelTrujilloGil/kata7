package software.ulpgc.kata7.architecture.io;

import software.ulpgc.kata7.architecture.model.Barchart;

public interface BarchartLoader {
    Barchart load(int id);
}
