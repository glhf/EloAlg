import com.glhf.elo.algorithm.Elo;
import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.Playing;
import com.glhf.elo.entities.Player;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class TestEloCalculatings {
    @Ignore
    @Test
    public void testElo(){
        Playing p1 = new Player(1,"Player1",2400);
        Playing p2 = new Player(2,"Player2",2000);

        Elo alg = new Elo(p1,p2);
        //alg.processMatchResult(MatchStatus.HOME_WIN);
        System.out.println(p1.toString() + p2.toString());

        Elo.eloProcessing(p1, p2, MatchStatus.GUEST_WIN);
        System.out.println(p1.toString() + p2.toString());
        Assert.assertEquals(p2.getCountOfWins(), 1);
        Assert.assertEquals(p1.getCountOfLoses(), 1);
    }
}
