package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.data.CategoryData
import com.dmdev.tasktracker.data.data.CategoryIcon
import com.dmdev.tasktracker.data.data.PeriodData
import com.dmdev.tasktracker.data.data.TaskData
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.data.domain.Period
import com.dmdev.tasktracker.data.domain.Task
import com.dmdev.tasktracker.repositories.CategoriesRepository
import com.dmdev.tasktracker.repositories.PeriodsRepository
import com.dmdev.tasktracker.repositories.TasksRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAllTasksUseCaseUnitTest {

    private val mockTasksRepository: TasksRepository = mock()
    private val mockPeriodsRepository: PeriodsRepository = mock()
    private val mockCategoriesRepository: CategoriesRepository = mock()

    @Test
    fun `UseCase return the same items mapped to the domain model`() = runBlocking {

        Mockito.`when`(mockTasksRepository.getAllTasks()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        TaskData(1, "Task1", 2, 0, 5, null),
                        TaskData(2, "Task2", 1, 10, null, null)
                    )
                )
            )
        })

        Mockito.`when`(mockCategoriesRepository.getAllCategories()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        CategoryData(1, "Category1", 0, CategoryIcon.BROOM),
                        CategoryData(2, "Category2", 1, CategoryIcon.CALL)
                    )
                )
            )
        })

        Mockito.`when`(mockPeriodsRepository.getAllPeriods()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        PeriodData(1, 1, 0, 5),
                        PeriodData(2, 2, 10, null),
                    )
                )
            )
        })

        val useCase = GetAllTasksUseCase(mockTasksRepository, mockCategoriesRepository, mockPeriodsRepository)
        val result = useCase.execute().toList()

        assertTrue(result.last() is ResultWrapper.Success<*>)

        val expectedList = listOf(
            Task(
                1, "Task1", Category(2, "Category2", 1, CategoryIcon.CALL), 0, 5, null, listOf(
                    Period(1, 1, 0, 5)
                )
            ),
            Task(
                2, "Task2", Category(1, "Category1", 0, CategoryIcon.BROOM), 10, null, null, listOf(
                    Period(2, 2, 10, null)
                )
            ),
        )
        assertEquals(expectedList, (result.last() as ResultWrapper.Success).result)
    }
}