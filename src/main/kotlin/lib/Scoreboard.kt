package lib

import lib.model.Match
import java.util.*

class Scoreboard {
  private val matches = mutableMapOf<UUID, Match>()

  fun startMatch(home: String, away: String): UUID {

  }

  fun updateScore(matchId: UUID, homeScore: Int, awayScore: Int) {

  }

  fun finishMatch(matchId: UUID) {

  }

  fun getScoreboard(): List<Match> {

  }
}
