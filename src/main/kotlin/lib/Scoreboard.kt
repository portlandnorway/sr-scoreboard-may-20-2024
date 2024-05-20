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
    if (previousMatches.containsKey(matchId)) {
      throw IllegalArgumentException("Match already finished")
    }
    val match = activeMatches[matchId] ?: throw NoSuchElementException("Match not found")
    match.homeScore = homeScore
    match.awayScore = awayScore
    return "Match updated: ${match.homeTeam} $homeScore - ${match.awayTeam} $awayScore"
  }

  fun finishMatch(matchId: UUID): String {
    if (previousMatches.containsKey(matchId)) {
      throw NoSuchElementException("Match is already finished")
    }
    val match = activeMatches.remove(matchId) ?: throw NoSuchElementException("Match does not exist")
    previousMatches[matchId] = match
    activeTeams.remove(match.homeTeam)
    activeTeams.remove(match.awayTeam)
    return "Match finished: ${match.homeTeam} vs ${match.awayTeam}"
  }

  fun getScoreboard(): List<Match> {
    val matches = activeMatches.values
    val sortedMatches = matches.sortedWith(
        compareByDescending<Match> { it.homeScore + it.awayScore }
          .thenByDescending { it.startTime }
      )
    // If expectation is to return a printed output of:
    // 1. Uruguay 6 - Italy 6
    // 2. Spain 10 - Brazil 2
    // 3. Mexico 0 - Canada 5
    // 4. Argentina 3 - Australia 1
    // 5. Germany 2 - France 2
    // Then modify/join sortedMatches as a string and return
    return sortedMatches
  }

}
