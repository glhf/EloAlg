import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.entities.Player;
import com.glhf.elo.entities.PlayersList;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class TestPlayersEntities {

    @Test
    public void testPlayersLoading() throws Exception {
        Scanner sc = new Scanner(new File("/home/goodvin/IdeaProjects/EloAlg/src/main/resources/names.txt"));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        PlayersProcessor pp = new PlayersList(lines);
        pp.load();

        Assert.assertEquals(pp.getCountOfPlayers(), 40);

        Assert.assertEquals(((Player)pp.getPlayer(33)).getName().intern(), "Wm".intern());
    }
}
