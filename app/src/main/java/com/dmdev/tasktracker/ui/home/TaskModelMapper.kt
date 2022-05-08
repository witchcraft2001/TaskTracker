package com.dmdev.tasktracker.ui.home

import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.utils.TimeUtils

object TaskModelMapper {
    fun mapToModel(task: Task, timeUtils: TimeUtils): TaskModel {
        val elapsed = task.periods.sumOf { period ->
            (period.endedAt ?: System.currentTimeMillis()) - period.startedAt
        }
        return task.run {
            TaskModel(id, name, category, startedAt, endedAt, deadline, timeUtils.millisToTimeString(elapsed))
        }
    }
}