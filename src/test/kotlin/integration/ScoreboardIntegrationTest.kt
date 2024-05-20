package integration

import lib.Scoreboard
import lib.model.Match
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScoreBoardIntegrationTest {

  private lateinit var scoreboard: Scoreboard

  @BeforeEach
  fun setUp() {
    scoreboard = Scoreboard()
  }

  @Test
  fun `should return World Cup Scoreboard in expected order`() {
    // Start matches with delays to ensure unique start times
    val mexicoCanada = scoreboard.startMatch("Mexico", "Canada")
    Thread.sleep(10)
    val spainBrazil = scoreboard.startMatch("Spain", "Brazil")
    Thread.sleep(10)
    val germanyFrance = scoreboard.startMatch("Germany", "France")
    Thread.sleep(10)
    val uruguayItaly = scoreboard.startMatch("Uruguay", "Italy")
    Thread.sleep(10)
    val argentinaAustralia = scoreboard.startMatch("Argentina", "Australia")

    // Update scores
    scoreboard.updateScore(mexicoCanada, 0, 5)
    scoreboard.updateScore(spainBrazil, 10, 2)
    scoreboard.updateScore(germanyFrance, 2, 2)
    scoreboard.updateScore(uruguayItaly, 6, 6)
    scoreboard.updateScore(argentinaAustralia, 3, 1)

    // Get scoreboard
    val scoreboardList: List<Match> = scoreboard.getScoreboard()


    assertEquals(5, scoreboardList.size)

    // Check the order of matches
    assertEquals("Uruguay", scoreboardList[0].homeTeam)
    assertEquals("Italy", scoreboardList[0].awayTeam)
    assertEquals(6, scoreboardList[0].homeScore)
    assertEquals(6, scoreboardList[0].awayScore)

    assertEquals("Spain", scoreboardList[1].homeTeam)
    assertEquals("Brazil", scoreboardList[1].awayTeam)
    assertEquals(10, scoreboardList[1].homeScore)
    assertEquals(2, scoreboardList[1].awayScore)

    assertEquals("Mexico", scoreboardList[2].homeTeam)
    assertEquals("Canada", scoreboardList[2].awayTeam)
    assertEquals(0, scoreboardList[2].homeScore)
    assertEquals(5, scoreboardList[2].awayScore)

    assertEquals("Argentina", scoreboardList[3].homeTeam)
    assertEquals("Australia", scoreboardList[3].awayTeam)
    assertEquals(3, scoreboardList[3].homeScore)
    assertEquals(1, scoreboardList[3].awayScore)

    assertEquals("Germany", scoreboardList[4].homeTeam)
    assertEquals("France", scoreboardList[4].awayTeam)
    assertEquals(2, scoreboardList[4].homeScore)
    assertEquals(2, scoreboardList[4].awayScore)
  }
}
