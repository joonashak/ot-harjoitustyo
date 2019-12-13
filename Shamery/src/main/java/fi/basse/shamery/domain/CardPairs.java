package fi.basse.shamery.domain;

import java.io.InputStream;
import java.io.InputStreamReader;
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
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("cardpairs.json");

        try {
            pairData = new Gson()
                .fromJson(
                    new InputStreamReader(stream),
                    new TypeToken<List<PairData>>() { }.getType()
            );
        } catch (Exception e) {
            System.out.println("\n*** ERROR ***");
            System.out.println("Could not load card pairs from json.");
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Get the next card pair in line.
     * @param id id for the first Card, other will have id+1 as id.
     * @return List of two Cards.
     */
    public List<Card> next(int id) {
        List<Card> res = new ArrayList<>();
        PairData pd = pairData.get(pointer);
        int type = pd.getType();
        String name = pd.getName();

        // Card icons are suffixed with _1 and _2 for each pair.
        res.add(new Card(id, 1, type, name.concat("_1")));
        res.add(new Card(id + 1, 2, type, name.concat("_2")));

        // Loop pointer indefinitely over pairData contents.
        pointer = pointer == pairData.size() - 1 ? 0 : pointer + 1;

        return res;
    }
}
