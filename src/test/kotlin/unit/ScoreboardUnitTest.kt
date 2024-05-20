package unit

import lib.Scoreboard
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.util.UUID

class ScoreBoardUnitTest {

  private lateinit var scoreboard: Scoreboard

  @BeforeEach
  fun setUp() {
    scoreboard = Scoreboard()
  }

  @Test
  fun `should start a new match successfully and return UUID`() {
    val matchId = scoreboard.startMatch("Team A", "Team B")
    assertNotNull(matchId)
  }

  @Test
  fun `should fail to start a new match if team is already in a match`() {
    scoreboard.startMatch("Team A", "Team B")
    val exception = assertThrows<IllegalStateException> {
      scoreboard.startMatch("Team A", "Team C")
    }
    assertEquals("Team A is already in a match", exception.message)
  }

  @Test
  fun `should update match score successfully and return confirmation`() {
    val matchId = scoreboard.startMatch("Team A", "Team B")
    val updateMessage = scoreboard.updateScore(matchId, 1, 0)
    assertEquals("Match updated: Team A 1 - Team B 0", updateMessage)
  }

  @Test
  fun `should fail to update score if match not found`() {
    val nonExistentMatchId = UUID.randomUUID()
    val exception = assertThrows<NoSuchElementException> {
      scoreboard.updateScore(nonExistentMatchId, 0, 1)
    }
    assertEquals("Match not found", exception.message)
  }

  @Test
  fun `should fail to update score if match is already finished`() {
    val completedMatchId = scoreboard.startMatch("Team A", "Team B")
    scoreboard.finishMatch(completedMatchId)
    val exception = assertThrows<IllegalArgumentException> {
      scoreboard.updateScore(completedMatchId, 1, 0)
    }
    assertEquals("Match already finished", exception.message)
  }
//
//  @Test
//  fun `should finish match successfully and return confirmation`() {
//    val matchId = scoreboard.startMatch("Team A", "Team B")
//    val finishMessage = scoreboard.finishMatch(matchId)
//    assertEquals("Match finished: Team A vs Team B", finishMessage)
//  }
//
//  @Test
//  fun `should fail to finish match if match does not exist`() {
//    val nonExistentMatchId = UUID.randomUUID()
//    val exception = assertThrows<NoSuchElementException> {
//      scoreboard.finishMatch(nonExistentMatchId)
//    }
//    assertEquals("Match does not exist", exception.message)
//  }
//
//  @Test
//  fun `should fail to finish match if match is already completed`() {
//    val matchId = scoreboard.startMatch("Team A", "Team B")
//    scoreboard.finishMatch(matchId)
//    val exception = assertThrows<NoSuchElementException> {
//      scoreboard.finishMatch(matchId)
//    }
//    assertEquals("Match is already completed", exception.message)
//  }
//
//  @Test
//  fun `should get scoreboard`() {
//    val matchId1 = scoreboard.startMatch("Team A", "Team B")
//    val matchId2 = scoreboard.startMatch("Team C", "Team D")
//    scoreboard.updateScore(matchId1, 2, 1)
//    scoreboard.updateScore(matchId2, 3, 2)
//
//    val summary = scoreboard.getScoreboard()
//    assertEquals(2, summary.size)
//
//    assertEquals("Team C", summary[0].homeTeam)
//    assertEquals(3, summary[0].homeScore)
//    assertEquals("Team D", summary[0].awayTeam)
//    assertEquals(2, summary[0].awayScore)
//
//    assertEquals("Team A", summary[1].homeTeam)
//    assertEquals(2, summary[1].homeScore)
//    assertEquals("Team B", summary[1].awayTeam)
//    assertEquals(1, summary[1].awayScore)
//  }

}
