package fi.basse.shamery.domain;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Read card pair configuration and iterate over the loaded set.
 * Used to generate any number of cards for the game.
 */
public class CardPairs {
    List<PairData> pairData;
    int pointer = 0;

    /**
     * Initialize class.
     * Loads available card pairs from json file.
     */
    public CardPairs() {
        try {
            pairData = new Gson()
                .fromJson(
                    new FileReader("src/main/resources/cardpairs.json"),
                    new TypeToken<List<PairData>>() {}.getType()
            );
        } catch (Exception e) {
            System.out.println("\n*** ERROR ***");
            System.out.println("Could not load card pairs from json.");
            System.out.println(e);
            System.exit(1);
        }
    }

    public List<Card> next(int id) {
        List<Card> res = new ArrayList<>();
        PairData pd = pairData.get(pointer);
        int type = pd.getType();
        String name = pd.getName();

        // Card icons are suffixed with _1 and _2 for each pair.
        res.add(new Card(id, type, name.concat("_1")));
        res.add(new Card(id + 1, type, name.concat("_2")));

        // Loop pointer indefinitely over pairData contents.
        pointer = pointer == pairData.size() - 1 ? 0 : pointer + 1;

        return res;
    }
}
