package com.dmdev.tasktracker.data.mappers

import com.dmdev.tasktracker.data.domain.Period
import com.dmdev.tasktracker.data.models.PeriodModel

object PeriodMapper {
    fun mapToDomain(model: PeriodModel) = Period(model.id, model.taskId, model.startedAt, model.endedAt)
}
