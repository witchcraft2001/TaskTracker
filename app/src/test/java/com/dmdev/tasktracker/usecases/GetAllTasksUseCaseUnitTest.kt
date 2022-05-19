package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.Colors
import com.dmdev.tasktracker.data.entities.PeriodEntity
import com.dmdev.tasktracker.data.entities.TaskEntity
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
        //Arrange
        Mockito.`when`(mockTasksRepository.getAllTasks()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        TaskEntity(1, "Task1", 2, 0, 5, null),
                        TaskEntity(2, "Task2", 1, 10, null, null)
                    )
                )
            )
        })

        Mockito.`when`(mockCategoriesRepository.getAllCategories()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        CategoryEntity(1, "Category1", Colors.CIAN.value.value.toLong(), CategoryIcon.BROOM),
                        CategoryEntity(2, "Category2", Colors.AMBER.value.value.toLong(), CategoryIcon.CALL)
                    )
                )
            )
        })

        Mockito.`when`(mockPeriodsRepository.getAllPeriods()).thenReturn(flow {
            emit(
                ResultWrapper.Success(
                    listOf(
                        PeriodEntity(1, 1, 0, 5),
                        PeriodEntity(2, 2, 10, null),
                    )
                )
            )
        })

        val useCase = GetAllTasksUseCase(mockTasksRepository, mockCategoriesRepository, mockPeriodsRepository)

        //Act
        val result = useCase.execute().toList()

        //Assert
        assertTrue(result.last() is ResultWrapper.Success<*>)

        val expectedList = listOf(
            Task(
                1, "Task1", Category(2, "Category2", Colors.AMBER, CategoryIcon.CALL), 0, 5, null, listOf(
                    Period(1, 1, 0, 5)
                )
            ),
            Task(
                2, "Task2", Category(1, "Category1", Colors.CIAN, CategoryIcon.BROOM), 10, null, null, listOf(
                    Period(2, 2, 10, null)
                )
            ),
        )
        assertEquals(expectedList, (result.last() as ResultWrapper.Success).result)
    }
}