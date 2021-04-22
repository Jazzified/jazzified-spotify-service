package karate.features.artist;

import com.intuit.karate.junit5.Karate;

public class ArtistRunner {

    @Karate.Test
    Karate testAll(){
        return Karate.run().relativeTo(getClass());
    }

}
