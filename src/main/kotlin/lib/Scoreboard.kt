package lib

import lib.model.Match
import java.util.*

class Scoreboard {
  private val activeMatches = mutableMapOf<UUID, Match>()
  private val activeTeams = mutableSetOf<String>()
  private val previousMatches = mutableMapOf<UUID, Match>()

  fun startMatch(home: String, away: String): UUID {
    if (home in activeTeams) {
      throw IllegalStateException("$home is already in a match")
    }
    if (away in activeTeams) {
      throw IllegalStateException("$away is already in a match")
    }

    val match = Match(homeTeam = home, awayTeam = away)
    activeMatches[match.id] = match
    activeTeams.add(home)
    activeTeams.add(away)
    return match.id
  }

  fun updateScore(matchId: UUID, homeScore: Int, awayScore: Int): String {
    val match = activeMatches[matchId] ?: throw NoSuchElementException("Match not found")
    if (previousMatches.containsKey(matchId)) {
      throw IllegalArgumentException("Match already finished")
    }
    match.homeScore = homeScore
    match.awayScore = awayScore
    return "Match updated: ${match.homeTeam} $homeScore - ${match.awayTeam} $awayScore"
  }

  fun finishMatch(matchId: UUID) {

  }

  fun getScoreboard() {

  }
}
