package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Period
import com.dmdev.tasktracker.data.entities.PeriodEntity

object PeriodMapper {
    fun mapToDomain(data: PeriodEntity) = Period(data.id, data.taskId, data.startedAt, data.endedAt)
}
