package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.repositories.PeriodsRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import javax.inject.Inject

class ToggleTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val periodsRepository: PeriodsRepository,
) {
    suspend fun execute(taskId: Long) {
        val taskData = tasksRepository.get(taskId)
        val endTime = System.currentTimeMillis()

        if (taskData.endedAt == null) {
            tasksRepository.update(taskData.copy(endedAt = endTime))
            finishAllPeriods(endTime)
            finishAllTasks(endTime)
        } else {
            finishAllPeriods(endTime)
            finishAllTasks(endTime)
            val startTime = System.currentTimeMillis()
            tasksRepository.update(taskData.copy(endedAt = null))
            val period = PeriodData(id = 0, taskId = taskId, startedAt = startTime, endedAt = null)
            periodsRepository.add(period)
        }
    }

    private suspend fun finishAllPeriods(endTime : Long) {
        val periods = periodsRepository.getAllUnfinishedPeriods()
        periods.forEach { item -> periodsRepository.update(item.copy(endedAt = endTime)) }
    }

    private suspend fun finishAllTasks(endTime : Long) {
        val tasks = tasksRepository.getAllUnfinishedTasks()
        tasks.forEach { item -> tasksRepository.update(item.copy(endedAt = endTime)) }
    }
}