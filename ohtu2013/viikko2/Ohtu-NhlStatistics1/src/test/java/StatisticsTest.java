import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void loytaaOlemassaOlevanPelaajan() {
        Player player = stats.search("Gretzky");
        assertEquals("EDM", player.getTeam());
        assertEquals(89, player.getAssists());
    }
    
    @Test
    public void eiLoydaPelaajaaJotaEiOle(){
        Player player = stats.search("gaf");
        assertNull(player);
    }
    
    @Test
    public void loytaaKaikkiJoukkueenPelaajat(){
        List<Player> pelaajat = stats.team("EDM");
        assertEquals(3, pelaajat.size());
    }
    
    @Test
    public void loytaaKaksiParastaPisteMiesta(){
        List<Player> pelaajat = stats.topScorers(2);
        assertEquals("Gretzky", pelaajat.get(0).getName());
        assertEquals("Lemieux", pelaajat.get(1).getName());
    }
}