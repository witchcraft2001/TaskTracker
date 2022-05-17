package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeCategoriesRepository : CategoriesRepository {
    override suspend fun getAllCategories(): Flow<ResultWrapper<List<CategoryEntity>>> = flow {
        emit(ResultWrapper.Success(listOf(
            CategoryEntity(1, "Test", 0, CategoryIcon.BROOM),
            CategoryEntity(2, "Test2", 1, CategoryIcon.BROOM)
        )))
    }
}

class GetAllCategoriesUseCaseUnitTest {

    private val useCase = GetAllCategoriesUseCase(FakeCategoriesRepository())

    @Test
    fun `UseCase return the same items mapped to the domain model`() = runBlocking {
        val result = useCase.execute().toList()

        assertTrue(result.last() is ResultWrapper.Success<*>)

        val expectedList = listOf(
            Category(1, "Test", 0, CategoryIcon.BROOM),
            Category(2, "Test2", 1, CategoryIcon.BROOM)
        )
        assertEquals(expectedList, (result.last() as ResultWrapper.Success).result)
    }
}