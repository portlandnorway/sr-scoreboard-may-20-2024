package lib.model

import java.util.*

data class Match(
  val id: UUID = UUID.randomUUID(),
  val homeTeam: String,
  val awayTeam: String,
  var homeScore: Int = 0,
  var awayScore: Int = 0,
  val startTime: Long = System.currentTimeMillis()
)
