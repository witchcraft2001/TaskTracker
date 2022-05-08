package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Period
import com.dmdev.tasktracker.data.data.PeriodData

object PeriodMapper {
    fun mapToDomain(data: PeriodData) = Period(data.id, data.taskId, data.startedAt, data.endedAt)
}
